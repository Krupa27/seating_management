package com.example.seating_management.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.seating_management.Feigns.BatchFeign;
import com.example.seating_management.dto.CohortDetail;
import com.example.seating_management.dto.RoomSizeWrapper;
import com.example.seating_management.model.Batch;
import com.example.seating_management.model.Room;
import com.example.seating_management.model.RoomId;
import com.example.seating_management.repository.RoomRepository;
 
@Service
public class AllocationService {
	@Autowired
    private BatchFeign batchFeign;
	
	@Autowired
    private RoomRepository roomRepository;
	
	@Autowired
	private RoomServiceImpl roomService;
	
    public AllocationService(BatchFeign batchFeign,RoomRepository roomRepository,RoomServiceImpl roomService) {
    	super();
    	this.batchFeign = batchFeign;
    	this.roomRepository=roomRepository;
    	this.roomService = roomService;
    }
    
    
    public ResponseEntity<CohortDetail> allocateRoom(CohortDetail request) {
    	ResponseEntity<CohortDetail> response = batchFeign.createBatch(request);
        return response;
    }
    
    
    public String dynamicAllocation(Batch batch, int fitting) {
        List<Room> allRooms = roomRepository.findAll();
        return allocateAndCreateCohort(batch, fitting, allRooms);
    }

    /**
     * Allocates a room from a specific location using a binary search approach for best-fit.
     *
     * @param batch    The batch to be allocated.
     * @param fitting  The fitting strategy.
     * @param location The specific location to search for rooms.
     * @return A string indicating the result of the allocation.
     */
    public String dynamicAllocationByLocation(Batch batch, int fitting, String location) {
        List<Room> roomsInLocation = roomRepository.findByIdLocation(location);
        if (roomsInLocation.isEmpty()) {
            return "No rooms found for location: " + location;
        }
        return allocateAndCreateCohort(batch, fitting, roomsInLocation);
    }

    /**
     * Allocates a room from a specific building within a location using a binary search approach for best-fit.
     *
     * @param batch    The batch to be allocated.
     * @param fitting  The fitting strategy.
     * @param location The location of the building.
     * @param building The specific building to search for rooms.
     * @return A string indicating the result of the allocation.
     */
    public String dynamicAllocationByBuilding(Batch batch, int fitting, String location, String building) {
        List<Room> roomsInBuilding = roomRepository.findByIdLocationAndIdBuilding(location, building);
        if (roomsInBuilding.isEmpty()) {
            return "No rooms found for building: " + building + " in location: " + location;
        }
        return allocateAndCreateCohort(batch, fitting, roomsInBuilding);
    }

    /**
     * Core private helper method to handle the allocation and cohort creation logic.
     * It calculates available capacity, sorts rooms, and then applies the chosen fitting strategy.
     *
     * @param batch          The batch to be allocated.
     * @param fitting        The fitting strategy.
     * @param availableRooms The list of rooms to perform allocation on.
     * @return A string indicating the allocation result.
     */
    private String allocateAndCreateCohort(Batch batch, int fitting, List<Room> availableRooms) {
        List<RoomSizeWrapper> roomsWithCapacity = new ArrayList<>();
        for (Room room : availableRooms) {
            int availableCapacity = room.getSeatCount() - roomService.getRoomOccupancy(room.getId(), batch.getTrainingStartDate());
            // We can add all rooms and let bestFit/worstFit handle size check.
            RoomSizeWrapper roomWrapper = new RoomSizeWrapper(room.getId(), availableCapacity);
            roomsWithCapacity.add(roomWrapper);
        }

        // Sort by available capacity in ascending order to enable binary search for best-fit
        Collections.sort(roomsWithCapacity, (a1, a2) -> a1.getOccupancy() - a2.getOccupancy());

        int size = batch.getInTrainingCount();
        RoomId roomId;

        if (fitting == 1) {
            roomId = bestFit(size, roomsWithCapacity);
        } else {
            roomId = worstFit(size, roomsWithCapacity);
        }

        if (roomId == null) {
            return "No Vacancy for the required size in the selected area.";
        }

        // --- Create Cohort and make Feign Call ---
        createCohort(batch, roomId);

        return "Allocated to Room: " + roomId.toString();
    }

    /**
     * Finds the best-fit room (smallest room that can accommodate the size) using binary search.
     *
     * @param size        The required size.
     * @param roomSummary A list of rooms sorted by occupancy.
     * @return The RoomId of the best-fit room, or null if no suitable room is found.
     */
    public RoomId bestFit(int size, List<RoomSizeWrapper> roomSummary) {
        int low = 0;
        int high = roomSummary.size() - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Avoid potential overflow
            if (roomSummary.get(mid).getOccupancy() >= size) {
                ans = mid;
                high = mid - 1; // Try to find a smaller room (tighter fit)
            } else {
                low = mid + 1;
            }
        }

        if (ans == -1) {
            return null; // No room found that can fit the size
        }
        return roomSummary.get(ans).getRoomId();
    }

    /**
     * Finds the worst-fit room (largest room that can accommodate the size).
     *
     * @param size        The required size.
     * @param roomSummary A list of rooms sorted by occupancy.
     * @return The RoomId of the worst-fit room, or null if no suitable room is found.
     */
    public RoomId worstFit(int size, List<RoomSizeWrapper> roomSummary) {
        if (roomSummary.isEmpty()) {
            return null;
        }
        int idx = roomSummary.size() - 1;
        // Check if the largest room has enough capacity
        if (roomSummary.get(idx).getOccupancy() < size) {
            return null;
        }
        return roomSummary.get(idx).getRoomId();
    }

    /**
     * Populates and sends the CohortCreationRequest to the batch service.
     */
    private void createCohort(Batch batch, RoomId roomId) {
        CohortDetail cohortDetail = new CohortDetail();
        cohortDetail.setBatchOwner(batch.getBatchOwner());
        cohortDetail.setCohortCode(batch.getCohortCode());
        cohortDetail.setDateOfJoining(batch.getDateOfJoining());
        cohortDetail.setExitCount(batch.getExitCount());
        cohortDetail.setGraduatedCount(batch.getGraduatedCount());
        cohortDetail.setInTrainingCount(batch.getInTrainingCount());
        cohortDetail.setTrainingStartDate(batch.getTrainingStartDate());
        cohortDetail.setTrainingEndDate(batch.getTrainingEndDate());
        cohortDetail.setSl(batch.getSl());
        cohortDetail.setPractice(batch.getPractice());

        // Set room details from the allocated roomId
        cohortDetail.setBuilding(roomId.getBuilding());
        cohortDetail.setFacility(roomId.getFacility());
        cohortDetail.setLocation(roomId.getLocation());
        cohortDetail.setFloorNumber(roomId.getFloorNumber());
        cohortDetail.setRoomNo(roomId.getRoomNumber());

        batchFeign.createBatch(cohortDetail);
    }
 
}
 
 

package com.example.seating_management.service;

import com.example.seating_management.dto.LocationStatsDto;
import com.example.seating_management.dto.RoomWrapper;
import com.example.seating_management.model.Room;
import com.example.seating_management.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingsService {

    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private RoomServiceImpl roomService;

//    public List<Room> getSortedRooms(String location, String sortBy, String order) {
//        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
//        String sortField = sortBy.equalsIgnoreCase("building") ? "id.building" : sortBy;
//        Sort sort = Sort.by(direction, sortField);
//        return roomRepository.findByLocation(location, sort);
//    }
//
//    public List<String> getDistinctBuildingNames(String location) {
//        return roomRepository.findDistinctBuildingsByLocation(location);
//    }
//
//    public List<Room> getRoomsByMinSeats(Integer minSeats) {
//        return roomRepository.findByAvailableSeatsGreaterThanEqual(minSeats);
//    }
//
//    public List<Room> getRoomsByLocationBuildingAndFloor(String location, String building, Integer floorNumber) {
//        return roomRepository.findRoomsByLocationAndBuildingAndFloor(location, building, floorNumber);
//    }
    public List<RoomWrapper> getRoomsByLocation(String location) {
    	List<RoomWrapper> rwList= new ArrayList<>();
    	for(Room r: roomRepository.findByIdLocation(location)) {
    		 LocalDate today = LocalDate.now();
    		RoomWrapper rw=new RoomWrapper(r.getId(),r.getSeatCount(),roomService.getRoomOccupancy(r.getId(), today),r.getRoomType(),r.getRoomType());
    		rwList.add(rw);
    	}
    	
    
    	return rwList;
    }
    
 // --- NEW METHOD FOR UNIQUE BUILDING NAMES ---
    public List<String> getUniqueBuildingNamesByLocation(String location) {
        List<Room> rooms = roomRepository.findByIdLocation(location); // Fetch all rooms for the location
        return rooms.stream()
                .map(room -> room.getId().getBuilding()) // Assuming RoomId has a getBuilding() method
                .distinct() // Get only unique building names
                .collect(Collectors.toList());
    }
    
    
    
    public List<LocationStatsDto> getAllLocations(){
    	
    	
    	List<LocationStatsDto> locationList = new ArrayList<>();
    	
    	for(String loc: roomRepository.findAllLocations()) {
    		LocationStatsDto location = new LocationStatsDto();
    		
    		HashSet<String> buildings = new HashSet<>();
    		location.setLocation(loc);
    		for(RoomWrapper r:getRoomsByLocation(loc)) {
    			int totalSeats=location.getTotalSeats() + r.getTotalSeats();
    			location.setTotalSeats(totalSeats);
    			
    			int OccupiedSeats= location.getOccupiedSeats() + r.getOccupiedSeats();
    			location.setOccupiedSeats(OccupiedSeats);
    			
    			buildings.add(r.getRoomId().getBuilding());
    			 
    		
    		}
    		location.setNumberOfBuildings(buildings.size());
    		locationList.add(location);
    	}
    	return locationList;
    }
    
    
    public List<LocationStatsDto> getRoomsFilteredAndSorted(
            Double minOccupancy, Double maxOccupancy, int sortBy, int alpha) {

        List<LocationStatsDto> locationList = getAllLocations();

        // ✔️ Filter using proper decimal division
        locationList = locationList.stream()
            .filter(x -> {
                double occupancy = x.getTotalSeats() > 0
                    ? (double) x.getOccupiedSeats() / x.getTotalSeats()
                    : 0.0;
                return occupancy >= minOccupancy && occupancy <= maxOccupancy;
            })
            .collect(Collectors.toList());

        // ✔️ Sort by occupancy
        if (sortBy == 1) {
            locationList.sort((x, y) -> {
                double occ1 = x.getTotalSeats() > 0
                    ? (double) x.getOccupiedSeats() / x.getTotalSeats()
                    : 0.0;
                double occ2 = y.getTotalSeats() > 0
                    ? (double) y.getOccupiedSeats() / y.getTotalSeats()
                    : 0.0;
                return Double.compare(occ1, occ2); // ascending
            });
        } else if (sortBy == 0) {
            locationList.sort((x, y) -> {
                double occ1 = x.getTotalSeats() > 0
                    ? (double) x.getOccupiedSeats() / x.getTotalSeats()
                    : 0.0;
                double occ2 = y.getTotalSeats() > 0
                    ? (double) y.getOccupiedSeats() / y.getTotalSeats()
                    : 0.0;
                return Double.compare(occ2, occ1); // descending
            });
        }

        // ✔️ Alpha sort by location name
        if (alpha == 1) {
            locationList.sort(Comparator.comparing(LocationStatsDto::getLocation));
        } else {
            locationList.sort((x, y) -> y.getLocation().compareTo(x.getLocation()));
        }

        return locationList;
    }

    
    
    
    
    
}

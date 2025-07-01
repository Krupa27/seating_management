
package com.example.seating_management.service;

import com.example.seating_management.dto.RoomCategorySummary;
import com.example.seating_management.model.Room;
import com.example.seating_management.model.RoomId;
import com.example.seating_management.repository.RoomRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
// import java.util.stream.LongStream; // Still not needed with Collectors.summingLong

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

	

//    @Override
//    public Room addRoom(Room room) {
//        if (room.getStatus() == null || room.getStatus().isEmpty()) {
//            room.setStatus("ACTIVE");
//        }
//        return roomRepository.save(room);
//    }
//
//    @Override
//    public Room deactivateRoom(RoomId roomId) {
//        Optional<Room> roomOptional = roomRepository.findById(roomId);
//        if (roomOptional.isPresent()) {
//            Room room = roomOptional.get();
//            room.setStatus("INACTIVE");
//            return roomRepository.save(room);
//        }
//        throw new RuntimeException("Room not found for deactivation with ID: " + roomId);
//    }
//
//    @Override
//    public Optional<Room> getRoomById(RoomId roomId) {
//        return roomRepository.findById(roomId);
//    }
//
//    @Override
//    public List<Room> getAllRooms() {
//        return roomRepository.findAll().stream()
//                .map(this::calculateAndSetOccupancy)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Page<Room> getRooms(Pageable pageable) {
//        Page<Room> page = roomRepository.findAll(pageable);
//        page.getContent().forEach(this::calculateAndSetOccupancy);
//        return page;
//    }
//
//    @Override
//    public Page<Room> getRoomsFilteredAndSorted(
//            Double minOccupancy, Double maxOccupancy, String sortBy, String sortDirection, Pageable pageable) {
//
//        Specification<Room> spec = (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//
//        Sort sort = Sort.unsorted();
//        if (sortBy != null && !sortBy.isEmpty()) {
//            Sort.Direction direction = "desc".equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;
//            sort = Sort.by(direction, sortBy);
//        }
//
//        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
//        Page<Room> roomsPage = roomRepository.findAll(spec, sortedPageable);
//        List<Room> content = roomsPage.getContent().stream()
//                .map(this::calculateAndSetOccupancy)
//                .collect(Collectors.toList());
//
//        if (minOccupancy != null || maxOccupancy != null) {
//            content = content.stream()
//                    .filter(room -> {
//                        if (room.getOccupancyPercentage() == null) return false;
//                        boolean meetsMin = minOccupancy == null || room.getOccupancyPercentage() >= minOccupancy;
//                        boolean meetsMax = maxOccupancy == null || room.getOccupancyPercentage() <= maxOccupancy;
//                        return meetsMin && meetsMax;
//                    })
//                    .collect(Collectors.toList());
//
//            if ("occupancyPercentage".equalsIgnoreCase(sortBy)) {
//                content.sort((r1, r2) -> {
//                    if (r1.getOccupancyPercentage() == null && r2.getOccupancyPercentage() == null) return 0;
//                    if (r1.getOccupancyPercentage() == null) return -1;
//                    if (r2.getOccupancyPercentage() == null) return 1;
//
//                    int comparison = Double.compare(r1.getOccupancyPercentage(), r2.getOccupancyPercentage());
//                    return "desc".equalsIgnoreCase(sortDirection) ? -comparison : comparison;
//                });
//            }
//            return new PageImpl<>(content, pageable, roomsPage.getTotalElements());
//        }
//        return roomsPage;
//    }
//
//    @Override
//    public List<RoomCategorySummary> getRoomCategorySummaries(String location, String building) {
//        List<Room> roomsInBuilding = roomRepository.findByIdLocationAndIdBuilding(location, building);
//
//        Map<String, List<Room>> roomsGroupedByType = roomsInBuilding.stream()
//                .collect(Collectors.groupingBy(Room::getRoomType));
//
//        List<RoomCategorySummary> summaries = new ArrayList<>();
//
//        for (Map.Entry<String, List<Room>> entry : roomsGroupedByType.entrySet()) {
//            String roomType = entry.getKey();
//            List<Room> roomsOfType = entry.getValue();
//
//            long totalRoomsCount = roomsOfType.size(); // This is the count of rooms for this type
//
//            long totalCapacitySeats = roomsOfType.stream()
//                    .filter(room -> room.getSeatCount() != null)
//                    .collect(Collectors.summingLong(room -> room.getSeatCount().longValue()));
//
//            long totalVacantSeatsInType = roomsOfType.stream() // This is `canBeUtilizedSeats` (vacant)
//                    .filter(room -> room.getCanBeUtilizedSeats() != null)
//                    .collect(Collectors.summingLong(room -> room.getCanBeUtilizedSeats().longValue()));
//
//            // Total occupied seats for the category is total capacity minus total vacant
//            long totalOccupiedSeatsInType = totalCapacitySeats - totalVacantSeatsInType;
//            if (totalOccupiedSeatsInType < 0) { // Ensure it's not negative
//                totalOccupiedSeatsInType = 0;
//            }
//
//            // Corrected constructor call using new DTO fields and calculated values
//            summaries.add(new RoomCategorySummary(
//                roomType,
//                totalRoomsCount,
//                totalCapacitySeats,
//                totalOccupiedSeatsInType,
//                totalVacantSeatsInType
//            ));
//        }
//        return summaries;
//    }
//
//    private Room calculateAndSetOccupancy(Room room) {
//        double totalSeats = room.getSeatCount() != null ? room.getSeatCount() : 0;
//        double vacantSeats = room.getCanBeUtilizedSeats() != null ? room.getCanBeUtilizedSeats() : 0;
//        double occupiedSeats = totalSeats - vacantSeats;
//        if (totalSeats > 0) {
//            double occupancy = (occupiedSeats / totalSeats) * 100.0;
//            room.setOccupancyPercentage(occupancy);
//        } else {
//            room.setOccupancyPercentage(0.0);
//        }
//        return room;
//    }
//    
//    
//    @Override
//    public List<Room> getRoomsByLocationBuildingAndType(String location, String building, String roomType) {
//        List<Room> rooms = roomRepository.findByIdLocationAndIdBuildingAndRoomType(location, building, roomType);
//        // Ensure occupancy is calculated for individual rooms as well
//        return rooms.stream()
//                .map(this::calculateAndSetOccupancy)
//                .collect(Collectors.toList());
//    }
//    
//    @Override
//    public List<Room> getRoomsBySize(int size) {
//        return roomRepository.findRoomsByMinimumSize(size);
//    }
    
    
}
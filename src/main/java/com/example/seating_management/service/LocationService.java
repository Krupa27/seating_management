package com.example.seating_management.service;
 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.example.seating_management.dto.LocationStatsDto;
import com.example.seating_management.model.Room;
import com.example.seating_management.repository.RoomRepository;
 
import java.util.*;
import java.util.stream.Collectors;
 
/**
* Service layer for computing location statistics.
*/
@Service
public class LocationService {
 
 
    @Autowired
    private RoomRepository roomRepository;
 
    /**
     * Get all location statistics.
     */

//    public List<Room> getRoomsByLocation(String location) {
//        return roomRepository.findByIdLocation(location);
//    }
// 
//    public List<Room> getAllRooms() {
//        return roomRepository.findAll();
//    }
// 
//    public List<String> getAllLocations() {
//        return roomRepository.findAllLocations();
//    }
//
//    public List<LocationStatsDto> getAllLocationStats() {
//        List<Room> rooms = getAllRooms();
//        return groupRoomsByLocation(rooms);
//    }
// 
//    /**
//     * Search locations by name using JPQL.
//     */
//    public List<LocationStatsDto> searchByLocationName(String name) {
//        List<Room> rooms = roomRepository.searchRoomsByLocationName(name);
//        return groupRoomsByLocation(rooms);
//    }
// 
//    
//    public List<LocationStatsDto> sortLocations(String sortBy, String order) {
//        List<LocationStatsDto> stats = getAllLocationStats();
// 
//        Comparator<LocationStatsDto> comparator;
// 
//        if ("occupancy".equalsIgnoreCase(sortBy)) {
//            comparator = Comparator.comparingDouble(LocationStatsDto::getOccupancyPercentage);
//        } else {
//            comparator = Comparator.comparing(LocationStatsDto::getLocation, String.CASE_INSENSITIVE_ORDER);
//        }
// 
//        if ("desc".equalsIgnoreCase(order)) {
//            comparator = comparator.reversed();
//        }
// 
//        return stats.stream()
//                .sorted(comparator)
//                .collect(Collectors.toList());
//    }
// 
//    /**
//     * Filter locations by occupancy percentage using JPQL.
//     */
//    public List<LocationStatsDto> filterByOccupancy(String type, double value) {
//        List<Room> rooms;
// 
//        if ("greater".equalsIgnoreCase(type)) {
//            rooms = roomRepository.findRoomsByOccupancyGreaterThan(value);
//        } else {
//            rooms = roomRepository.findRoomsByOccupancyLessThan(value);
//        }
// 
//        return groupRoomsByLocation(rooms);
//    }
// 
//    /**
//     * Utility method to group rooms by location and compute stats.
//     */
//    private List<LocationStatsDto> groupRoomsByLocation(List<Room> rooms) {
//        Map<String, List<Room>> groupedByLocation = rooms.stream()
//                .collect(Collectors.groupingBy(room -> room.getId().getLocation()));
// 
//        List<LocationStatsDto> stats = new ArrayList<>();
// 
//        for (Map.Entry<String, List<Room>> entry : groupedByLocation.entrySet()) {
//            String location = entry.getKey();
//            List<Room> locationRooms = entry.getValue();
// 
//            int totalSeats = locationRooms.stream().mapToInt(Room::getSeatCount).sum();
//            int occupiedSeats = locationRooms.stream().mapToInt(Room::getCanBeUtilizedSeats).sum();
//            int unoccupiedSeats = totalSeats - occupiedSeats;
//            double occupancyPercentage = totalSeats == 0 ? 0 : (occupiedSeats * 100.0) / totalSeats;
////            int numberOfFacilities = (int) locationRooms.stream()
////                    .map(room -> room.getId().getFacility())
////                    .distinct()
////                    .count();
//            int numberOfBuildings = (int) locationRooms.stream()
//                    .map(room -> room.getId().getBuilding())
//                    .distinct()
//                    .count();
// 
//            LocationStatsDto dto = new LocationStatsDto();
//            dto.setLocation(location);
//            dto.setTotalSeats(totalSeats);
//            dto.setOccupiedSeats(occupiedSeats);
//            dto.setUnoccupiedSeats(unoccupiedSeats);
//            dto.setOccupancyPercentage(occupancyPercentage);
//            //dto.setNumberOfFacilities(numberOfFacilities);
//            dto.setNumberOfBuildings(numberOfBuildings);
//            stats.add(dto);
//        }
// 
//        return stats;
//    }
}
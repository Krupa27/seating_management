package com.example.seating_management.service;
 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.example.seating_management.dto.LocationStatsDto;
import com.example.seating_management.dto.RoomWrapper;
import com.example.seating_management.model.Room;
import com.example.seating_management.repository.RoomRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
 
/**
* Service layer for computing location statistics.
*/
@Service
public class LocationService {
 
 
    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private RoomServiceImpl roomService;
    
    
    
 
    /**
     * Get all location statistics.
     */

    public List<RoomWrapper> getRoomsByLocation(String location) {
    	List<RoomWrapper> rwList= new ArrayList<>();
    	for(Room r: roomRepository.findByIdLocation(location)) {
    		 LocalDate today = LocalDate.now();
    		RoomWrapper rw=new RoomWrapper(r.getId(),r.getSeatCount(),roomService.getRoomOccupancy(r.getId(), today),r.getRoomType(),r.getRoomType());
    		rwList.add(rw);
    	}
    	
    
    	return rwList;
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
    	System.out.println(locationList);
    	return locationList;
    }
    
    
//    public List<LocationStatsDto> getRoomsFilteredAndSorted(
//            Double minOccupancy, Double maxOccupancy, int sortBy, int alpha) {
//
//        List<LocationStatsDto> locationList = getAllLocations();
//
//        // ✅ Filter by occupancy range (corrected with proper type casting)
//        locationList = locationList.stream()
//            .filter(x -> {
//                double occupancy = x.getTotalSeats() > 0
//                    ? (double) x.getOccupiedSeats() / x.getTotalSeats()
//                    : 0.0;
//                return occupancy >= minOccupancy && occupancy <= maxOccupancy;
//            })
//            .collect(Collectors.toList());
//
//        // ✅ Sort by occupancy (ascending or descending)
//        if (sortBy == 1) {
//            locationList.sort((x, y) -> {
//                double occ1 = x.getTotalSeats() > 0
//                    ? (double) x.getOccupiedSeats() / x.getTotalSeats()
//                    : 0.0;
//                double occ2 = y.getTotalSeats() > 0
//                    ? (double) y.getOccupiedSeats() / y.getTotalSeats()
//                    : 0.0;
//                return Double.compare(occ1, occ2);
//            });
//        } else {
//            locationList.sort((x, y) -> {
//                double occ1 = x.getTotalSeats() > 0
//                    ? (double) x.getOccupiedSeats() / x.getTotalSeats()
//                    : 0.0;
//                double occ2 = y.getTotalSeats() > 0
//                    ? (double) y.getOccupiedSeats() / y.getTotalSeats()
//                    : 0.0;
//                return Double.compare(occ2, occ1);
//            });
//        }
//
//        // ✅ Secondary sort alphabetically (ascending or descending)
//        if (alpha == 1) {
//            locationList.sort(Comparator.comparing(LocationStatsDto::getLocation));
//        } else {
//            locationList.sort((x, y) -> y.getLocation().compareTo(x.getLocation()));
//        }
//
//        return locationList;
//    }
//    public List<LocationStatsDto> getRoomsFilteredAndSorted(
//            Double minOccupancy, Double maxOccupancy, int sortBy, int order) { // Renamed 'alpha' to 'order' for clarity
//
//        List<LocationStatsDto> locationList = getAllLocations();
//
//        // ✅ Filter by occupancy range (looks correct)
//        locationList = locationList.stream()
//            .filter(x -> {
//                double occupancy = x.getTotalSeats() > 0
//                    ? (double) x.getOccupiedSeats() / x.getTotalSeats()
//                    : 0.0;
//                return occupancy >= minOccupancy && occupancy <= maxOccupancy;
//            })
//            .collect(Collectors.toList());
//
//        // --- Apply Sorting ---
//        // sortBy: 0 = Alphabetical, 1 = Occupancy
//        // order: 0 = Ascending, 1 = Descending
//
//        Comparator<LocationStatsDto> comparator = null;
//
//        if (sortBy == 0) { // Sort by Alphabetical
//            if (order == 0) { // Ascending (A-Z)
//                comparator = Comparator.comparing(LocationStatsDto::getLocation);
//            } else { // Descending (Z-A)
//                comparator = (x, y) -> y.getLocation().compareTo(x.getLocation());
//            }
//        } else if (sortBy == 1) { // Sort by Occupancy
//            if (order == 0) { // Ascending (Low to High)
//                comparator = (x, y) -> {
//                    double occ1 = x.getTotalSeats() > 0 ? (double) x.getOccupiedSeats() / x.getTotalSeats() : 0.0;
//                    double occ2 = y.getTotalSeats() > 0 ? (double) y.getOccupiedSeats() / y.getTotalSeats() : 0.0;
//                    return Double.compare(occ1, occ2); // Low to High
//                };
//            } else { // Descending (High to Low)
//                comparator = (x, y) -> {
//                    double occ1 = x.getTotalSeats() > 0 ? (double) x.getOccupiedSeats() / x.getTotalSeats() : 0.0;
//                    double occ2 = y.getTotalSeats() > 0 ? (double) y.getOccupiedSeats() / x.getTotalSeats() : 0.0;
//                    return Double.compare(occ2, occ1); // High to Low
//                };
//            }
//        }
//
//        if (comparator != null) {
//            locationList.sort(comparator);
//        }
//
//        return locationList;
//    }
    public List<LocationStatsDto> getRoomsFilteredAndSorted(
            Double minOccupancy, Double maxOccupancy, int sortBy, int order) { // Renamed 'alpha' to 'order' for clarity

        List<LocationStatsDto> locationList = getAllLocations();

        // ✅ Filter by occupancy range (looks correct)
        locationList = locationList.stream()
            .filter(x -> {
                double occupancy = x.getTotalSeats() > 0
                    ? (double) x.getOccupiedSeats() / x.getTotalSeats()
                    : 0.0;
                return occupancy >= minOccupancy && occupancy <= maxOccupancy;
            })
            .collect(Collectors.toList());

        // --- Apply Sorting ---
        // sortBy: 0 = Alphabetical, 1 = Occupancy
        // order: 0 = Ascending, 1 = Descending

        Comparator<LocationStatsDto> comparator = null;

        if (sortBy == 0) { // Sort by Alphabetical
            if (order == 0) { // Ascending (A-Z)
                comparator = Comparator.comparing(LocationStatsDto::getLocation);
            } else { // Descending (Z-A)
                comparator = (x, y) -> y.getLocation().compareTo(x.getLocation());
            }
        } else if (sortBy == 1) { // Sort by Occupancy
            if (order == 0) { // Ascending (Low to High)
                comparator = (x, y) -> {
                    double occ1 = x.getTotalSeats() > 0 ? (double) x.getOccupiedSeats() / x.getTotalSeats() : 0.0;
                    double occ2 = y.getTotalSeats() > 0 ? (double) y.getOccupiedSeats() / y.getTotalSeats() : 0.0;
                    return Double.compare(occ1, occ2); // Low to High
                };
            } else { // Descending (High to Low)
                comparator = (x, y) -> {
                    double occ1 = x.getTotalSeats() > 0 ? (double) x.getOccupiedSeats() / x.getTotalSeats() : 0.0;
                    // FIX HERE: Changed x.getTotalSeats() to y.getTotalSeats() for occ2
                    double occ2 = y.getTotalSeats() > 0 ? (double) y.getOccupiedSeats() / y.getTotalSeats() : 0.0;
                    return Double.compare(occ2, occ1); // High to Low
                };
            }
        }

        if (comparator != null) {
            locationList.sort(comparator);
        }

        return locationList;
    }
    
//    public List<LocationStatsDto> getRoomsFilteredAndSorted(
//            Double minOccupancy, Double maxOccupancy, int sortBy,int alpha){
//    	
//    	List<LocationStatsDto> locationList = getAllLocations();
//    	
//    	locationList = locationList.stream().filter(x-> {double occupancy = x.getOccupiedSeats()/x.getTotalSeats(); 
//    	return occupancy >= minOccupancy && occupancy <= maxOccupancy;}).collect(Collectors.toList());
//    	
//    	if(sortBy==1) {
//    		Collections.sort(locationList,(x,y)->{
//    			double occ1=x.getOccupiedSeats()/x.getTotalSeats();
//    			double occ2=y.getOccupiedSeats()/y.getTotalSeats();
//    			return Double.compare(occ1,occ2);
//    		});
//    	}
//    	else {
//    		Collections.sort(locationList,(x,y)->{
//    			double occ1=x.getOccupiedSeats()/x.getTotalSeats();
//    			double occ2=y.getOccupiedSeats()/y.getTotalSeats();
//    			return Double.compare(occ2,occ1);
//    		});
//    	}
//    	
//    	
//    	if(alpha == 1) {
//    		Collections.sort(locationList,(x,y)->{
//    		
//    			String s1 = x.getLocation();
//    			String s2 = y.getLocation();
//    			return s1.compareTo(s2);
//    		});
//    	}else {
//    		Collections.sort(locationList,(x,y)->{
//        		
//    			String s1 = x.getLocation();
//    			String s2 = y.getLocation();
//    			return s2.compareTo(s1);
//    		});
//    		
//    	}
//    			
//    	return locationList;
//    	
//    }
    
    
    
// 
//    public List<Room> getAllRooms() {
//        return roomRepository.findAll();
//    }
//// 
//    public List<String> getAllLocations() {
//        return roomRepository.findAllLocations();
//    }
////
//    public List<LocationStatsDto> getAllLocationStats() {
////        List<Room> rooms = getAllRooms();
////        return groupRoomsByLocation(rooms);
//    	
//    	
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
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
    public List<RoomWrapper> getRoomsByLocation(String location,String building) {
    	List<RoomWrapper> rwList= new ArrayList<>();
    	for(Room r: roomRepository.findByIdLocationAndIdBuilding(location,building)) {
    		 LocalDate today = LocalDate.now();
    		RoomWrapper rw=new RoomWrapper(r.getId(),r.getSeatCount(),roomService.getRoomOccupancy(r.getId(), today));
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
    		for(RoomWrapper r:getRoomsByLocation(loc, loc)) {
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
            Double minOccupancy, Double maxOccupancy, int sortBy,int alpha){
    	
    	List<LocationStatsDto> locationList = getAllLocations();
    	
    	locationList = locationList.stream().filter(x-> {double occupancy = x.getOccupiedSeats()/x.getTotalSeats(); 
    	return occupancy >= minOccupancy && occupancy <= maxOccupancy;}).collect(Collectors.toList());
    	
    	if(sortBy==1) {
    		Collections.sort(locationList,(x,y)->{
    			double occ1=x.getOccupiedSeats()/x.getTotalSeats();
    			double occ2=y.getOccupiedSeats()/y.getTotalSeats();
    			return Double.compare(occ1,occ2);
    		});
    	}
    	else {
    		Collections.sort(locationList,(x,y)->{
    			double occ1=x.getOccupiedSeats()/x.getTotalSeats();
    			double occ2=y.getOccupiedSeats()/y.getTotalSeats();
    			return Double.compare(occ2,occ1);
    		});
    	}
    	
    	
    	if(alpha == 1) {
    		Collections.sort(locationList,(x,y)->{
    		
    			String s1 = x.getLocation();
    			String s2 = y.getLocation();
    			return s1.compareTo(s2);
    		});
    	}else {
    		Collections.sort(locationList,(x,y)->{
        		
    			String s1 = x.getLocation();
    			String s2 = y.getLocation();
    			return s2.compareTo(s1);
    		});
    		
    	}
    			
    	return locationList;
    	
    }
    
    
    
}

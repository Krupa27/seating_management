
package com.example.seating_management.service;

import com.example.seating_management.Feigns.BatchFeign;
import com.example.seating_management.dto.RoomWrapper;
import com.example.seating_management.model.Room;
import com.example.seating_management.model.RoomId;
import com.example.seating_management.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl  {

	private RoomRepository roomRepository;
    
	
	private BatchFeign batchFeign;

   
    
    
    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, BatchFeign batchFeign) {
		this.roomRepository = roomRepository;
		this.batchFeign = batchFeign;
	}


    public int getRoomOccupancy(RoomId roomId,String date) {
    	return batchFeign.getRoomOccupancy(roomId, date);
    }
	public int getRoomOccupancy(RoomId roomId , LocalDate date) {
    	return getRoomOccupancy(roomId, date.toString());
    }
	
	
	// *** NEW METHOD FOR BATCH ADD ***
    public List<Room> saveAllRooms(List<Room> rooms) {
        // You might add additional logic here, like setting default status
        // or validating rooms before saving.
        rooms.forEach(room -> {
            if (room.getStatus() == null || room.getStatus().isEmpty()) {
                room.setStatus("ACTIVE");
            }
        });
        return roomRepository.saveAll(rooms);
    }


    public Room addRoom(Room room) {
        if (room.getStatus() == null || room.getStatus().isEmpty()) {
            room.setStatus("ACTIVE");
        }
        return roomRepository.save(room);
    }


    public Room deactivateRoom(RoomId roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            room.setStatus("INACTIVE");
            return roomRepository.save(room);
        }
        throw new RuntimeException("Room not found for deactivation with ID: " + roomId);
    }

    public Optional<Room> getRoomById(RoomId roomId) {
        return roomRepository.findById(roomId);
    }

    
    public List<RoomWrapper> getAllRooms() {
    	List<RoomWrapper> rwList= new ArrayList<>();
    	for(Room r:roomRepository.findAll()) {
    		 LocalDate today = LocalDate.now();
    		RoomWrapper rw=new RoomWrapper(r.getId(),r.getSeatCount(),this.getRoomOccupancy(r.getId(), today),r.getRoomType(),r.getRoomType());
    		rwList.add(rw);
    	}
    
    	return rwList;
    	
    }
    public List<RoomWrapper> getRoomsFilteredAndSorted(
          Double minOccupancy, Double maxOccupancy, int sortBy){
    	List<RoomWrapper> rwList=this.getAllRooms();
    	rwList=rwList.stream().filter((x)->{
    		double occupancy=x.getOccupiedSeats()/x.getTotalSeats();
    		return occupancy>=minOccupancy && occupancy<=maxOccupancy;
    	}).collect(Collectors.toList());
    	
    	if(sortBy==1) {
    		Collections.sort(rwList,(x,y)->{
    			double occ1=x.getOccupiedSeats()/x.getTotalSeats();
    			double occ2=y.getOccupiedSeats()/y.getTotalSeats();
    			return Double.compare(occ1,occ2);
    		});
    	}
    	else {
    		Collections.sort(rwList,(x,y)->{
    			double occ1=x.getOccupiedSeats()/x.getTotalSeats();
    			double occ2=y.getOccupiedSeats()/y.getTotalSeats();
    			return Double.compare(occ2,occ1);
    		});
    	}
    	return rwList;
    	
    }




    public Map<String,List<RoomWrapper>> getRoomCategorySummaries(String location, String building) {
        List<Room> roomsInBuilding = roomRepository.findByIdLocationAndIdBuilding(location, building);

        Map<String, List<Room>> roomsGroupedByType = roomsInBuilding.stream()
                .collect(Collectors.groupingBy(Room::getRoomType));

        Map<String,List<RoomWrapper>> mapForRoomWrapper = new HashMap<>();
        
        for(String type:roomsGroupedByType.keySet()) {
        	List<RoomWrapper> rwList = new ArrayList<>();
        	for(Room r:roomsGroupedByType.get(type)) {
       		 	LocalDate today = LocalDate.now();
	       		RoomWrapper rw=new RoomWrapper(r.getId(),r.getSeatCount(),this.getRoomOccupancy(r.getId(), today),r.getRoomType(),r.getRoomType());
	       		rwList.add(rw);
        	}
        	mapForRoomWrapper.put(type, rwList);
        	
        }
   
        return mapForRoomWrapper;
    }


    public List<RoomWrapper> getRoomsByLocationBuildingAndType(String location, String building, String roomType) {
        // Ensure occupancy is calculated for individual rooms as well
        List<RoomWrapper> rwList= new ArrayList<>();
        
    	for(Room r:roomRepository.findByIdLocationAndIdBuildingAndRoomType(location, building, roomType)) {
    		 LocalDate today = LocalDate.now();
    		RoomWrapper rw=new RoomWrapper(r.getId(),r.getSeatCount(),this.getRoomOccupancy(r.getId(), today),r.getRoomType(),r.getRoomType());
    		rwList.add(rw);
    	}
    
    	return rwList;
        
    }
    
    public Optional<RoomWrapper> getRoomByLocationBuildingTypeAndNumber(String location, String building, String roomType, Integer roomNumber) {
        List<Room> matches = roomRepository
            .findByIdLocationAndIdBuildingAndRoomTypeAndIdRoomNumber(location, building, roomType, roomNumber);

        if (matches.isEmpty()) {
            return Optional.empty();
        }

        Room r = matches.get(0);
        LocalDate today = LocalDate.now();
        RoomWrapper rw = new RoomWrapper(r.getId(), r.getSeatCount(), this.getRoomOccupancy(r.getId(), today),r.getRoomType(),r.getRoomType());
        System.out.println("→ location = " + location);
        System.out.println("→ building = " + building);
        System.out.println("→ roomType = " + roomType);
        System.out.println("→ roomNumber = " + roomNumber);
        System.out.println("→ " + rw);

        return Optional.of(rw);
    }
    
    
    
    
    
    public List<RoomWrapper> getRoomsBySizeAndAvailability(int size, LocalDate date) {
        List<RoomWrapper> result = new ArrayList<>();

        List<Room> allRooms = roomRepository.findAll(); // or filter by location/building if needed

        for (Room room : allRooms) {
            int capacity = room.getSeatCount();
            int occupied = this.getRoomOccupancy(room.getId(), date); // uses BatchFeign
            int vacant = capacity - occupied;

            if (vacant >= size) {
                RoomWrapper wrapper = new RoomWrapper(room.getId(), capacity, occupied,room.getRoomType(),room.getRoomType());
                result.add(wrapper);
            }
        }

        return result;
    }




//    public List<Room> getRoomsBySize(int size) {
//        return roomRepository.findRoomsByMinimumSize(size);
//    }
    
    
}
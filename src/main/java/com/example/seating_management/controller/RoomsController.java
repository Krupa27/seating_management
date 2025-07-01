

package com.example.seating_management.controller;

import com.example.seating_management.dto.RoomWrapper;
import com.example.seating_management.model.Room;
import com.example.seating_management.model.RoomId;
import com.example.seating_management.service.RoomServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin("*")
public class RoomsController {
	
	 private final RoomServiceImpl roomService;

	    @Autowired
	    public RoomsController(RoomServiceImpl roomService) {
	        this.roomService = roomService;
	    }

	    @PostMapping("/add")
	    public Room addRoom(@RequestBody Room room) {
	        return roomService.addRoom(room);
	    }

	    @PutMapping("/deactivate")
	    public Room deactivateRoom(@RequestBody RoomId roomId) {
	        return roomService.deactivateRoom(roomId);
	    }

	    @GetMapping("/by-room-type")
	    public List<RoomWrapper> getRoomsByLocationBuildingAndType(
	            @RequestParam String location,
	            @RequestParam String building,
	            @RequestParam String roomType) {
	        
	        return roomService.getRoomsByLocationBuildingAndType(location, building, roomType);
	    }


	    @GetMapping("/all")
	    public List<RoomWrapper> getAllRooms() {
	        return roomService.getAllRooms();
	    }

	    @GetMapping("/sorted")
	    public List<RoomWrapper> getRoomsFilteredAndSorted(
	            @RequestParam Double minOccupancy,
	            @RequestParam Double maxOccupancy,
	            @RequestParam int sortBy) {
	        return roomService.getRoomsFilteredAndSorted(minOccupancy, maxOccupancy, sortBy);
	    }

	    @GetMapping("/category-summary")
	    public Map<String, List<RoomWrapper>> getRoomCategorySummaries(
	            @RequestParam String location,
	            @RequestParam String building) {
	        return roomService.getRoomCategorySummaries(location, building);
	    }

	    @GetMapping("/occupancy")
	    public int getRoomOccupancy(
	            @RequestParam String location,
	            @RequestParam String facility,
	            @RequestParam String building,
	            @RequestParam Integer floorNumber,
	            @RequestParam String wing,
	            @RequestParam Integer roomNumber,
	            @RequestParam String date) {

	        RoomId roomId = new RoomId(location, facility, building, floorNumber, wing, roomNumber);
	        LocalDate parsedDate = LocalDate.parse(date);
	        return roomService.getRoomOccupancy(roomId, parsedDate);
	    }

	    @GetMapping("/find")
	    public Optional<Room> getRoomById(@RequestBody RoomId roomId) {
	        return roomService.getRoomById(roomId);
	    }

//    private final RoomService roomService;
//
//    @Autowired
//    public RoomsController(RoomService roomService) {
//        this.roomService = roomService;
//    }

//    @PostMapping("/add")
//    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
//        Room newRoom = roomService.addRoom(room);
//        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/deactivate")
//    public ResponseEntity<Room> deactivateRoom(
//            @RequestParam String location,
//            @RequestParam String facility,
//            @RequestParam String building,
//            @RequestParam Integer floorNumber,
//            @RequestParam String wing,
//            @RequestParam Integer roomNumber) {
//        RoomId roomId = new RoomId(location, facility, building, floorNumber, wing, roomNumber);
//        try {
//            Room deactivatedRoom = roomService.deactivateRoom(roomId);
//            return ResponseEntity.ok(deactivatedRoom);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//
//    @GetMapping("/find")
//    public ResponseEntity<Room> getRoomDetails(
//            @RequestParam String location,
//            @RequestParam String facility,
//            @RequestParam String building,
//            @RequestParam Integer floorNumber,
//            @RequestParam String wing,
//            @RequestParam Integer roomNumber
//            ) {
//        RoomId roomId = new RoomId(location, facility, building, floorNumber, wing, roomNumber);
//        Optional<Room> room = roomService.getRoomById(roomId);
//        return room.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<Page<Room>> getRooms(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(required = false) String sortBy,
//            @RequestParam(defaultValue = "asc") String sortDirection,
//            @RequestParam(required = false) Double minOccupancy,
//            @RequestParam(required = false) Double maxOccupancy) {
//
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Room> roomsPage = roomService.getRoomsFilteredAndSorted(
//                minOccupancy, maxOccupancy, sortBy, sortDirection, pageable);
//        return ResponseEntity.ok(roomsPage);
//    }
//
//    @GetMapping("/summary")
//    public ResponseEntity<List<RoomCategorySummary>> getRoomSummaries(
//            @RequestParam String location,
//            @RequestParam String building) {
//        List<RoomCategorySummary> summaries = roomService.getRoomCategorySummaries(location, building);
//        return ResponseEntity.ok(summaries);
//    }
//    
//    
//    @GetMapping("/by-type")
//    public ResponseEntity<List<Room>> getRoomsByLocationBuildingAndType(
//            @RequestParam String location,
//            @RequestParam String building,
//            @RequestParam String roomType) {
//        List<Room> rooms = roomService.getRoomsByLocationBuildingAndType(location, building, roomType);
//        System.out.println(rooms);
//        if (rooms.isEmpty()) {
//            return ResponseEntity.noContent().build(); // Or HttpStatus.NOT_FOUND if you prefer
//        }
//        return ResponseEntity.ok(rooms);
//    }
//    
//    @GetMapping("/by-size")
//    public List<Room> getRoomsBySize(@RequestParam int size) {
//    	System.out.println(roomService.getRoomsBySize(size));
//        return roomService.getRoomsBySize(size);
//    }
}
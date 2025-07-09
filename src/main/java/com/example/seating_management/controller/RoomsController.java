

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
	    
	    // *** NEW ENDPOINT FOR BATCH ADD ***
	    @PostMapping("/add-batch")
	    public ResponseEntity<String> addRoomsBatch(@RequestBody List<Room> rooms) {
	        try {
	            if (rooms == null || rooms.isEmpty()) {
	                return new ResponseEntity<>("No rooms provided for batch add.", HttpStatus.BAD_REQUEST);
	            }
	            roomService.saveAllRooms(rooms); // Use the new service method
	            return new ResponseEntity<>("Rooms added in batch successfully!", HttpStatus.CREATED);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>("Failed to add rooms in batch: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
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

	    @PostMapping("/find")
	    public Optional<Room> getRoomById(@RequestBody RoomId roomId) {
	        return roomService.getRoomById(roomId);
	    }
	    
	    // --- MODIFIED ENDPOINT ---
	    @GetMapping("/by-location-building-roomtype-details") // New, more specific endpoint name
	    public ResponseEntity<List<RoomWrapper>> getRoomsByLocationBuildingAndTypeWithDetails(
	            @RequestParam String location,
	            @RequestParam String building,
	            @RequestParam String roomType) {

	        List<RoomWrapper> rooms = roomService.getRoomsByLocationBuildingAndType(location, building, roomType);

	        if (rooms.isEmpty()) {
	            return ResponseEntity.noContent().build(); // 204 No Content
	        }
	        return ResponseEntity.ok(rooms); // 200 OK with list of RoomWrapper
	    }
	    
//	    @GetMapping("/view")
//	    public ResponseEntity<RoomWrapper> getRoomDetailsByParams(
//	            @RequestParam String location,
//	            @RequestParam String building,
//	            @RequestParam String roomType,
//	            @RequestParam Integer roomNumber) {
//	    	
//	        return roomService.getRoomByLocationBuildingTypeAndNumber(location, building, roomType, roomNumber)
//	                .map(ResponseEntity::ok)
//	                .orElse(ResponseEntity.notFound().build());
//	    }
	    @GetMapping("/view")
	    public ResponseEntity<RoomWrapper> getRoomDetailsByParams(
	            @RequestParam String location,
	            @RequestParam String building,
	            @RequestParam String roomType,
	            @RequestParam Integer floorNumber, // <--- NEW PARAMETER
	            @RequestParam Integer roomNumber) {

	        System.out.println("Received request for room details:");
	        System.out.println("  Location: " + location);
	        System.out.println("  Building: " + building);
	        System.out.println("  Room Type: " + roomType);
	        System.out.println("  Floor Number: " + floorNumber); // Log new param
	        System.out.println("  Room Number: " + roomNumber);

	        return roomService.getRoomByLocationBuildingTypeAndNumber(location, building, roomType, floorNumber, roomNumber) // <--- Pass floorNumber
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
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
	    @GetMapping("/by-size-date")
	    public ResponseEntity<List<RoomWrapper>> getRoomsBySizeAndDate(
	            @RequestParam int size,
	            @RequestParam String date) {

	        LocalDate parsedDate = LocalDate.parse(date);
	        List<RoomWrapper> availableRooms = roomService.getRoomsBySizeAndAvailability(size, parsedDate);

	        return availableRooms.isEmpty()
	            ? ResponseEntity.noContent().build()
	            : ResponseEntity.ok(availableRooms);
	    }

}
package com.example.seating_management.controller;

import com.example.seating_management.model.Room;
import com.example.seating_management.model.RoomId;
import com.example.seating_management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms") 
public class RoomsController {

    private final RoomService roomService;
    
    @Autowired
    public RoomsController(RoomService roomService) {
        this.roomService = roomService;
    }
    
    
    @PostMapping("/add")
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        Room newRoom = roomService.addRoom(room);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }
    
    
    @PutMapping("/deactivate") // all are mandatory
    public ResponseEntity<Room> deactivateRoom(
            @RequestParam String location,
            @RequestParam String facility,
            @RequestParam String building,
            @RequestParam Integer floorNumber,
            @RequestParam String wing,
            @RequestParam Integer roomNumber) {
        RoomId roomId = new RoomId(location, facility, building, floorNumber, wing, roomNumber);
        try {
            Room deactivatedRoom = roomService.deactivateRoom(roomId);
            return ResponseEntity.ok(deactivatedRoom);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    
    @GetMapping("/find") // all : mandatory
    public ResponseEntity<Room> getRoomDetails(
            @RequestParam String location,
            @RequestParam String facility,
            @RequestParam String building,
            @RequestParam Integer floorNumber,
            @RequestParam String wing,
            @RequestParam Integer roomNumber) {
        RoomId roomId = new RoomId(location, facility, building, floorNumber, wing, roomNumber);
        Optional<Room> room = roomService.getRoomById(roomId);
        return room.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search") // any 1 
    public ResponseEntity<Page<Room>> getRooms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(required = false) Double minOccupancy,
            @RequestParam(required = false) Double maxOccupancy) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Room> roomsPage = roomService.getRoomsFilteredAndSorted(
                minOccupancy, maxOccupancy, sortBy, sortDirection, pageable);
        return ResponseEntity.ok(roomsPage);
    }
}
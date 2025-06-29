
package com.example.seating_management.service;

import com.example.seating_management.model.Room;
import com.example.seating_management.model.RoomId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room addRoom(Room room);
    Room deactivateRoom(RoomId roomId); 
    
    Optional<Room> getRoomById(RoomId roomId);
    
    List<Room> getAllRooms();
    Page<Room> getRooms(Pageable pageable); 
    Page<Room> getRoomsFilteredAndSorted(
        Double minOccupancy, Double maxOccupancy, String sortBy, String sortDirection, Pageable pageable);
}
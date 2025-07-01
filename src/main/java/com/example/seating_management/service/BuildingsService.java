package com.example.seating_management.service;

import com.example.seating_management.model.Room;
import com.example.seating_management.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingsService {

    @Autowired
    private RoomRepository roomRepository;

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
}

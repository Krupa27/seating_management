package com.example.seating_management.controller;

import com.example.seating_management.model.Room;
import com.example.seating_management.service.BuildingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buildings")
@CrossOrigin("*")
public class BuildingsController {

//    @Autowired
//    private BuildingsService buildingsService;
//
//    @GetMapping("/sorted")
//    public List<Room> getSortedRooms(@RequestParam String location,
//                                     @RequestParam(defaultValue = "building") String sortBy,
//                                     @RequestParam(defaultValue = "asc") String order) {
//        return buildingsService.getSortedRooms(location, sortBy, order);
//    }
//
//    @GetMapping("/names")
//    public List<String> getDistinctBuildingNames(@RequestParam String location) {
//        return buildingsService.getDistinctBuildingNames(location);
//    }
//
//    @GetMapping("/by-min-seats")
//    public List<Room> getRoomsByMinSeats(@RequestParam Integer minSeats) {
//        return buildingsService.getRoomsByMinSeats(minSeats);
//    }
//
//    @GetMapping("/by-location-building-floor")
//    public List<Room> getRoomsByLocationBuildingAndFloor(@RequestParam String location,
//                                                         @RequestParam String building,
//                                                         @RequestParam Integer floorNumber) {
//        return buildingsService.getRoomsByLocationBuildingAndFloor(location, building, floorNumber);
//    }
}

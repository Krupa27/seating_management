package com.example.seating_management.controller;

import com.example.seating_management.dto.LocationStatsDto;
import com.example.seating_management.dto.RoomWrapper;
import com.example.seating_management.model.Room;
import com.example.seating_management.service.BuildingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buildings")
@CrossOrigin("*")
public class BuildingsController {

    @Autowired
    private BuildingsService buildingsService;
    
    
 // 🔹 GET all buildings grouped by location stats
    @GetMapping
    public List<LocationStatsDto> getAllBuildings() {
        return buildingsService.getAllLocations();
    }

    
    // 🔹 GET all rooms in a given location (e.g. Chennai)
    @GetMapping("/{location}")
    public List<RoomWrapper> getRoomsByLocation(@RequestParam String location) {
    	System.out.println(buildingsService.getRoomsByLocation(location));
        return buildingsService.getRoomsByLocation(location);
    }
    
 // --- NEW ENDPOINT FOR UNIQUE BUILDING NAMES ---
    @GetMapping("/namesByLocation") // A more descriptive path
    public List<String> getUniqueBuildingNamesByLocation(@RequestParam String location) {
        return buildingsService.getUniqueBuildingNamesByLocation(location);
    }

    // 🔹 GET filtered and sorted list based on occupancy and name order
    @GetMapping("/filter")
    public List<LocationStatsDto> getFilteredAndSortedBuildings(
            @RequestParam Double minOccupancy,
            @RequestParam Double maxOccupancy,
            @RequestParam(defaultValue = "1") int sortBy,   // 1 for ascending, 0 for descending
            @RequestParam(defaultValue = "1") int alpha      // 1 for A-Z, 0 for Z-A
    ) {
        return buildingsService.getRoomsFilteredAndSorted(minOccupancy, maxOccupancy, sortBy, alpha);
    }

//    @GetMapping("/sorted")
//    public List<LocationStatsDto> getSortedRooms(@RequestParam(defaultValue = "1.0") Double minOccupancy,
//    								@RequestParam(defaultValue = "1.0") Double maxOccupancy,
//                                     @RequestParam(defaultValue = "1") int sortBy,
//                                     @RequestParam(defaultValue = "1") int order) {
//        return buildingsService.getRoomsFilteredAndSorted(minOccupancy,maxOccupancy, sortBy, order);
//    }
//
//    @GetMapping("/names")
//    public List<RoomWrapper> getDistinctBuildingNames(@RequestParam String location) {
//        return buildingsService.getRoomsByLocation(location);
//    }

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

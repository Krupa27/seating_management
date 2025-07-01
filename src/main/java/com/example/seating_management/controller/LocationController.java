//package com.example.demo.controllers;
//
//
//
//import com.example.demo.dto.LocationDetailsDTO;
//import com.example.demo.services.LocationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/locations")
//public class LocationController {
//
//    @Autowired
//    private LocationService locationService;
//
//    /**
//     * Get all location details including:
//     * - Total seats
//     * - Occupied seats
//     * - Unoccupied seats
//     * - Occupancy percentage
//     * - Facility count
//     */
//    @GetMapping("/details")
//    public ResponseEntity<List<LocationDetailsDTO>> getAllLocationDetails() {
//        List<LocationDetailsDTO> details = locationService.getAllLocationDetails();
//        return ResponseEntity.ok(details);
//    }
//
//    /**
//     * Search locations by name (case-insensitive)
//     */
//    @GetMapping("/search")
//    public ResponseEntity<List<LocationDetailsDTO>> searchByName(@RequestParam String name) {
//        List<LocationDetailsDTO> results = locationService.searchLocationByName(name);
//        return ResponseEntity.ok(results);
//    }
//
//    /**
//     * Sort locations by name or occupancy percentage
//     * @param sortBy "name" or "occupancy"
//     * @param order "asc" or "desc"
//     */
//    @GetMapping("/sort")
//    public ResponseEntity<List<LocationDetailsDTO>> sortLocations(
//            @RequestParam String sortBy,
//            @RequestParam String order) {
//        List<LocationDetailsDTO> sorted = locationService.sortLocations(sortBy, order);
//        return ResponseEntity.ok(sorted);
//    }
//
//    /**
//     * Filter locations by occupancy percentage
//     * @param condition "greater" or "less"
//     * @param value threshold value for filtering
//     */
//    @GetMapping("/filter")
//    public ResponseEntity<List<LocationDetailsDTO>> filterByOccupancy(
//            @RequestParam String condition,
//            @RequestParam double value) {
//        List<LocationDetailsDTO> filtered = locationService.filterByOccupancy(condition, value);
//        return ResponseEntity.ok(filtered);
//    }
//}
package com.example.seating_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.seating_management.dto.LocationStatsDto;
import com.example.seating_management.dto.RoomWrapper;
import com.example.seating_management.model.Room;
import com.example.seating_management.service.LocationService;

import java.util.List;

/**
 * REST controller for location-related endpoints.
 */
@RestController
@RequestMapping("/locations")
@CrossOrigin("*")
public class LocationController {

    @Autowired
    private LocationService locationService;

    /**
     * Get all location statistics.
     */
    @GetMapping("/")
    public List<LocationStatsDto> getAllLocationStats() {
        return locationService.getAllLocations();
    }

    /**
     * Search locations by name.
     */
    @GetMapping("/search")
    public List<RoomWrapper> searchByLocationName(@RequestParam String name) {
        return locationService.getRoomsByLocation(name);
    }

    /**
     * Sort locations by name or occupancy.
     * @param sortBy name or occupancy
     * @param order asc or desc
     */
    @GetMapping("/sort")
    public List<LocationStatsDto> sort(
    		@RequestParam(defaultValue = "0.0") Double minOccupancy,
    		@RequestParam(defaultValue = "100.0") Double maxOccupancy,
            @RequestParam(defaultValue = "1") int sortBy,
            @RequestParam(defaultValue = "1") int order) {
        return locationService.getRoomsFilteredAndSorted(minOccupancy,maxOccupancy,sortBy, order);
    }

    /**
     * Filter locations by occupancy percentage.
     * @param type greater or less
     * @param value threshold value
     */
//    @GetMapping("/filter")
//    public List<LocationStatsDto> filterByOccupancy(
//            @RequestParam String type,
//            @RequestParam double value) {
//        return locationService.filterByOccupancy(type, value);
//    }
}


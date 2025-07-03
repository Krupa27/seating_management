package com.example.seating_management.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.seating_management.dto.CohortDetail;
import com.example.seating_management.model.Batch;
import com.example.seating_management.service.AllocationService;
 
 
@RestController
@RequestMapping("/api/batches")
@CrossOrigin("*")
public class AllocationController {
    
	private AllocationService allocationService;
	public AllocationController(AllocationService allocationService) {
		this.allocationService=allocationService;
	}
	
	 @PostMapping("/allocate")
	    public ResponseEntity<CohortDetail> allocateRoom(
	            @RequestBody CohortDetail request
//	            @RequestParam Integer size,
//	            @RequestParam String date) {
	        ){
	        // Optional: add business logic to check if `size` fits or if date is valid
		 ResponseEntity<CohortDetail> result = allocationService.allocateRoom(request);
		 System.out.println(result);
	        return result;
	    }
	 
	 
	 
	 
	 
	 
	 @PostMapping("/dynamic")
	    public ResponseEntity<String> allocateDynamically(
	            @RequestBody Batch batch,
	            @RequestParam("fitting") int fitting) {
	        String result = allocationService.dynamicAllocation(batch, fitting);
	        return ResponseEntity.ok(result);
	    }

	    
	    @PostMapping("/location/{location}")
	    public ResponseEntity<String> allocateByLocation(
	            @RequestBody Batch batch,
	            @RequestParam("fitting") int fitting,
	            @PathVariable("location") String location) {
	        String result = allocationService.dynamicAllocationByLocation(batch, fitting, location);
	        return ResponseEntity.ok(result);
	    }

	    
	    @PostMapping("/location/{location}/building/{building}")
	    public ResponseEntity<String> allocateByBuilding(
	            @RequestBody Batch batch,
	            @RequestParam("fitting") int fitting,
	            @PathVariable("location") String location,
	            @PathVariable("building") String building) {
	        String result = allocationService.dynamicAllocationByBuilding(batch, fitting, location, building);
	        return ResponseEntity.ok(result);
	    }

}
 
 

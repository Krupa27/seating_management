package com.example.seating_management.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.seating_management.service.AllocationService;
 
 
@RestController
public class AllocationController {
    
	private AllocationService allocationService;
	public AllocationController(AllocationService allocationService) {
		this.allocationService=allocationService;
	}
//	@GetMapping("/dynamicAllocation")
//	public ResponseEntity<String> dynamicAllocation(@RequestParam String batchCode,@RequestParam int fitting){
//		return ResponseEntity.ok(allocationService.dynamicAllocation(batchCode,fitting));
//	}
}
 
 

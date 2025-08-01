package com.example.seating_management.Feigns;

import java.time.LocalDate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.seating_management.dto.CohortDetail;
import com.example.seating_management.model.RoomId;


@FeignClient("batch-service")
public interface BatchFeign {
	
	@PostMapping("/roomOccupancy/{date}")
	public int getRoomOccupancy(@RequestBody RoomId roomId,@PathVariable String date);
	
	 @PostMapping("api/batches/add_batch") // Adjust path based on actual controller
	 ResponseEntity<CohortDetail> createBatch( @RequestBody CohortDetail request);
}

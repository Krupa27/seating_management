
package com.example.seating_management.repository;

import com.example.seating_management.model.Room;
import com.example.seating_management.model.RoomId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, RoomId>, JpaSpecificationExecutor<Room> {
   
    List<Room> findById_LocationAndId_Building(String location, String building);

    // You might need methods for sorting and filtering.
    // JpaRepository.findAll(Sort sort) and findAll(Pageable pageable) are very useful.
    // For filtering by occupancy percentage, we'll use Specifications in the service layer.
}
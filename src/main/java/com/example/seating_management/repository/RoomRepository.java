
package com.example.seating_management.repository;

import com.example.seating_management.model.Room;
import com.example.seating_management.model.RoomId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, RoomId>, JpaSpecificationExecutor<Room> {
   
//    List<Room> findById_LocationAndId_Building(String location, String building);
//    
//    
//    List<Room> findByIdLocationAndIdBuilding(String id_location, String id_building);
//    
//    
//    @Query("SELECT r FROM Room r WHERE LOWER(r.roomType) = LOWER(:roomType) AND r.id.location = :location AND r.id.building = :building")
//    List<Room> findByIdLocationAndIdBuildingAndRoomType(String location, String building, String roomType);
//    
//    
//    // You might need methods for sorting and filtering.
//    // JpaRepository.findAll(Sort sort) and findAll(Pageable pageable) are very useful.
//    // For filtering by occupancy percentage, we'll use Specifications in the service layer.
//    
//    @Query("SELECT r FROM Room r WHERE r.id.location = :location")
//	List<Room> findByLocation(@Param("location") String location, Sort sort);
//
//	@Query("SELECT DISTINCT r.id.building FROM Room r WHERE r.id.location = :location")
//	List<String> findDistinctBuildingsByLocation(@Param("location") String location);
//	
//	@Query("SELECT r FROM Room r WHERE r.canBeUtilizedSeats >= :minSeats")
//	List<Room> findByAvailableSeatsGreaterThanEqual(@Param("minSeats") Integer minSeats);
//
//	@Query("""
//			    SELECT r FROM Room r
//			    WHERE r.id.location = :location
//			      AND r.id.building = :building
//			      AND r.id.floorNumber = :floorNumber
//			""")
//	List<Room> findRoomsByLocationAndBuildingAndFloor(@Param("location") String location,
//			@Param("building") String building, @Param("floorNumber") Integer floorNumber);
//	
//	
//	
//	
//	
//	
//	List<Room> findByIdLocation(String location);
//
//    // Get all distinct locations
//    @Query("SELECT DISTINCT r.id.location FROM Room r")
//    List<String> findAllLocations();
//
//
//
//    // Filter rooms where occupancy percentage is greater than a value
//    @Query("SELECT r FROM Room r WHERE r.seatCount > 0 AND (r.canBeUtilizedSeats * 1.0 / r.seatCount) * 100 > :value")
//    List<Room> findRoomsByOccupancyGreaterThan(@Param("value") double value);
//    
//    @Query("SELECT r FROM Room r WHERE LOWER(r.id.location) LIKE LOWER(CONCAT('%', :name, '%'))")
//    List<Room> searchRoomsByLocationName(@Param("name") String name);
//
//
//    // Filter rooms where occupancy percentage is less than a value
//    @Query("SELECT r FROM Room r WHERE r.seatCount > 0 AND (r.canBeUtilizedSeats * 1.0 / r.seatCount) * 100 < :value")
//    List<Room> findRoomsByOccupancyLessThan(@Param("value") double value);
//    
//    
//
//    
//    
//    @Query("SELECT r FROM Room r WHERE r.canBeUtilizedSeats >= :size AND LOWER(r.status) = 'available'")
//    List<Room> findRoomsByMinimumSize(@Param("size") int size);

}
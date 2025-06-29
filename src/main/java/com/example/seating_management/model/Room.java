
package com.example.seating_management.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "roomdetails") 
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @EmbeddedId 
    private RoomId id;


    private String roomType;
    private String roomTypeNameStandardized;
    private Integer seatCount;
    private Integer countExcludingTrainer;
    private Integer seatingSetup;
    private Integer seatingForCalculation;
    private String priority;
    private String status;
    private String internGenc; 
    private Integer canBeUtilizedSeats; 
    private String learningTrack;
    private LocalDate availableFrom; 
     
    
    public RoomId getId() {
		return id;
	}
	public void setId(RoomId id) {
		this.id = id;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getRoomTypeNameStandardized() {
		return roomTypeNameStandardized;
	}
	public void setRoomTypeNameStandardized(String roomTypeNameStandardized) {
		this.roomTypeNameStandardized = roomTypeNameStandardized;
	}
	public Integer getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}
	public Integer getCountExcludingTrainer() {
		return countExcludingTrainer;
	}
	public void setCountExcludingTrainer(Integer countExcludingTrainer) {
		this.countExcludingTrainer = countExcludingTrainer;
	}
	public Integer getSeatingSetup() {
		return seatingSetup;
	}
	public void setSeatingSetup(Integer seatingSetup) {
		this.seatingSetup = seatingSetup;
	}
	public Integer getSeatingForCalculation() {
		return seatingForCalculation;
	}
	public void setSeatingForCalculation(Integer seatingForCalculation) {
		this.seatingForCalculation = seatingForCalculation;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInternGenc() {
		return internGenc;
	}
	public void setInternGenc(String internGenc) {
		this.internGenc = internGenc;
	}
	public Integer getCanBeUtilizedSeats() {
		return canBeUtilizedSeats;
	}
	public void setCanBeUtilizedSeats(Integer canBeUtilizedSeats) {
		this.canBeUtilizedSeats = canBeUtilizedSeats;
	}
	public String getLearningTrack() {
		return learningTrack;
	}
	public void setLearningTrack(String learningTrack) {
		this.learningTrack = learningTrack;
	}
	public LocalDate getAvailableFrom() {
		return availableFrom;
	}
	public void setAvailableFrom(LocalDate availableFrom) {
		this.availableFrom = availableFrom;
	}
	
	@Transient
    private Double occupancyPercentage;

    public Double getOccupancyPercentage() {
        return occupancyPercentage;
    }

    public void setOccupancyPercentage(Double occupancyPercentage) {
        this.occupancyPercentage = occupancyPercentage;
    }
}
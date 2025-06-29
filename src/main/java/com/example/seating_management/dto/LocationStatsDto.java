package com.example.seating_management.dto;


/**
 * DTO for location statistics.
 */

public class LocationStatsDto {
    public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getOccupiedSeats() {
		return occupiedSeats;
	}
	public void setOccupiedSeats(int occupiedSeats) {
		this.occupiedSeats = occupiedSeats;
	}
	public int getUnoccupiedSeats() {
		return unoccupiedSeats;
	}
	public void setUnoccupiedSeats(int unoccupiedSeats) {
		this.unoccupiedSeats = unoccupiedSeats;
	}
	public double getOccupancyPercentage() {
		return occupancyPercentage;
	}
	public void setOccupancyPercentage(double occupancyPercentage) {
		this.occupancyPercentage = occupancyPercentage;
	}
	public int getNumberOfFacilities() {
		return numberOfFacilities;
	}
	public void setNumberOfFacilities(int numberOfFacilities) {
		this.numberOfFacilities = numberOfFacilities;
	}
	private String location;
    private int totalSeats;
    private int occupiedSeats;
    private int unoccupiedSeats;
    private double occupancyPercentage;
    private int numberOfFacilities;
}

package com.example.seating_management.dto;
 
 
/**
* DTO for location statistics.
*/
 
public class LocationStatsDto {
	private String location;
	private int totalSeats;
	private int occupiedSeats;
	private int numberOfBuildings;
	
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


	public int getNumberOfBuildings() {
		return numberOfBuildings;
	}
	public void setNumberOfBuildings(int numberOfBuildings) {
		this.numberOfBuildings = numberOfBuildings;
	}
	public LocationStatsDto() {}
	public LocationStatsDto(String location, int totalSeats, int occupiedSeats, int numberOfBuildings) {
		super();
		this.location = location;
		this.totalSeats = totalSeats;
		this.occupiedSeats = occupiedSeats;
		this.numberOfBuildings = numberOfBuildings;
	}
	@Override
	public String toString() {
		return "LocationStatsDto [location=" + location + ", totalSeats=" + totalSeats + ", occupiedSeats="
				+ occupiedSeats + ", numberOfBuildings=" + numberOfBuildings + "]";
	}
	
	
}
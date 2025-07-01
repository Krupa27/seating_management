package com.example.seating_management.dto;

import com.example.seating_management.model.RoomId;

public class RoomWrapper {
	
	private RoomId roomId;
	private int totalSeats;
	private int occupiedSeats;
	public RoomId getRoomId() {
		return roomId;
	}
	public void setRoomId(RoomId roomId) {
		this.roomId = roomId;
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
	public RoomWrapper() {}
	public RoomWrapper(RoomId roomId, int totalSeats, int occupiedSeats) {
		super();
		this.roomId = roomId;
		this.totalSeats = totalSeats;
		this.occupiedSeats = occupiedSeats;
	}
	@Override
	public String toString() {
		return "RoomWrapper [roomId=" + roomId + ", totalSeats=" + totalSeats + ", occupiedSeats=" + occupiedSeats
				+ "]";
	}
	
	
	
}

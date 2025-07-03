package com.example.seating_management.dto;

import com.example.seating_management.model.RoomId;

public class RoomSizeWrapper {
	    private RoomId roomId;
	    private int occupancy;
		public RoomId getRoomId() {
			return roomId;
		}
		public void setRoomId(RoomId roomId) {
			this.roomId = roomId;
		}
		public int getOccupancy() {
			return occupancy;
		}
		public void setOccupancy(int occupancy) {
			this.occupancy = occupancy;
		}
		public RoomSizeWrapper(RoomId roomId, int occupancy) {
			super();
			this.roomId = roomId;
			this.occupancy = occupancy;
		}
		public RoomSizeWrapper() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "RoomSizeWrapper [roomId=" + roomId + ", occupancy=" + occupancy + "]";
		}
	    
	}
	 
	 


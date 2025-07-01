//package com.example.seating_management.dto;
//
//public class RoomCategorySummary {
//    private String roomType;
//    private Long totalRooms;
//    private Long occupiedRooms;
//    private Long vacantRooms;
//    private Double occupancyPercentage;
//
//    public RoomCategorySummary(String roomType, Long totalRooms, Long occupiedRooms, Long vacantRooms) {
//        this.roomType = roomType;
//        this.totalRooms = totalRooms;
//        this.occupiedRooms = occupiedRooms;
//        this.vacantRooms = vacantRooms;
//        if (totalRooms > 0) {
//            this.occupancyPercentage = (double) occupiedRooms / totalRooms * 100.0;
//        } else {
//            this.occupancyPercentage = 0.0;
//        }
//    }
//
//    // --- Getters and Setters ---
//    public String getRoomType() {
//        return roomType;
//    }
//
//    public void setRoomType(String roomType) {
//        this.roomType = roomType;
//    }
//
//    public Long getTotalRooms() {
//        return totalRooms;
//    }
//
//    public void setTotalRooms(Long totalRooms) {
//        this.totalRooms = totalRooms;
//    }
//
//    public Long getOccupiedRooms() {
//        return occupiedRooms;
//    }
//
//    public void setOccupiedRooms(Long occupiedRooms) {
//        this.occupiedRooms = occupiedRooms;
//    }
//
//    public Long getVacantRooms() {
//        return vacantRooms;
//    }
//
//    public void setVacantRooms(Long vacantRooms) {
//        this.vacantRooms = vacantRooms;
//    }
//
//    public Double getOccupancyPercentage() {
//        return occupancyPercentage;
//    }
//
//    public void setOccupancyPercentage(Double occupancyPercentage) {
//        this.occupancyPercentage = occupancyPercentage;
//    }
//}

package com.example.seating_management.dto;

public class RoomCategorySummary {
    private String roomType;
    private Long totalRoomsCount; // Renamed for clarity: count of rooms
    private Long totalCapacitySeats; // New field: total seats in all rooms of this type
    private Long totalOccupiedSeats; // Renamed for clarity: total occupied seats in all rooms of this type
    private Long totalVacantSeats;   // Renamed for clarity: total vacant seats in all rooms of this type
    private Double occupancyPercentage;

    // Updated constructor to reflect seat-based totals for occupancy calculation
    public RoomCategorySummary(String roomType, Long totalRoomsCount, Long totalCapacitySeats, Long totalOccupiedSeats, Long totalVacantSeats) {
        this.roomType = roomType;
        this.totalRoomsCount = totalRoomsCount;
        this.totalCapacitySeats = totalCapacitySeats;
        this.totalOccupiedSeats = totalOccupiedSeats;
        this.totalVacantSeats = totalVacantSeats;

        if (totalCapacitySeats > 0) {
            this.occupancyPercentage = (double)((totalOccupiedSeats*100.0) / totalCapacitySeats);
        } else {
            this.occupancyPercentage = 0.0;
        }
    }

    // --- Getters and Setters ---
    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Long getTotalRoomsCount() { // Getter for totalRoomsCount
        return totalRoomsCount;
    }

    public void setTotalRoomsCount(Long totalRoomsCount) { // Setter for totalRoomsCount
        this.totalRoomsCount = totalRoomsCount;
    }

    public Long getTotalCapacitySeats() { // Getter for totalCapacitySeats
        return totalCapacitySeats;
    }

    public void setTotalCapacitySeats(Long totalCapacitySeats) { // Setter for totalCapacitySeats
        this.totalCapacitySeats = totalCapacitySeats;
    }

    public Long getTotalOccupiedSeats() { // Getter for totalOccupiedSeats
        return totalOccupiedSeats;
    }

    public void setTotalOccupiedSeats(Long totalOccupiedSeats) { // Setter for totalOccupiedSeats
        this.totalOccupiedSeats = totalOccupiedSeats;
    }

    public Long getTotalVacantSeats() { // Getter for totalVacantSeats
        return totalVacantSeats;
    }

    public void setTotalVacantSeats(Long totalVacantSeats) { // Setter for totalVacantSeats
        this.totalVacantSeats = totalVacantSeats;
    }

    public Double getOccupancyPercentage() {
        return occupancyPercentage;
    }

    public void setOccupancyPercentage(Double occupancyPercentage) {
        this.occupancyPercentage = occupancyPercentage;
    }
}
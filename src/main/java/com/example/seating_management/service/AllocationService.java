package com.example.seating_management.service;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 

 
import com.example.seating_management.repository.RoomRepository;
 
@Service
public class AllocationService {
//	@Autowired
//    private BatchFeign batchFeign;
//	
	@Autowired
    private RoomRepository roomRepository;
	
//    public AllocationService(BatchFeign batchFeign,RoomRepository roomRepository) {
//    	super();
//    	this.batchFeign = batchFeign;
//    	this.roomRepository=roomRepository;
//    }
    
//	public String dynamicAllocation(String batchCode ,int fitting) {
//		 Batch b=batchFeign.getBatch(batchCode);
//		 System.out.println(b.toString());
//		 List<Room> rooms= roomRepository.findAll();
//		 Collections.sort(rooms,(a1,a2)->{
//				return a1.getCanBeUtilizedSeats()-a2.getCanBeUtilizedSeats();
//		 });
//		 int size=b.getInTrainingCount();
//		 RoomId roomId=null;
//		 if(fitting==1)
//			  roomId=bestFit(size,rooms);
//		 else
//			  roomId=worstFit(size,rooms);
//		 if(roomId!=null) {
//			 Room room=roomRepository.findById(roomId).get();
//			 room.setCanBeUtilizedSeats(room.getCanBeUtilizedSeats()-b.getInTrainingCount());
//			 roomRepository.save(room);
//			 CohortDetail cohortDetail=new CohortDetail();
//			 cohortDetail.setBatchOwner(b.getBatchOwner());
//			 cohortDetail.setCohortCode(b.getCohortCode());
//			 cohortDetail.setDateOfJoining(b.getDateOfJoining());
//			 cohortDetail.setExitCount(b.getExitCount());
//			 cohortDetail.setGraduatedCount(b.getGraduatedCount());
//			 cohortDetail.setInTrainingCount(b.getInTrainingCount());
//			cohortDetail.setTrainingStartDate(b.getTrainingStartDate());
//			cohortDetail.setTrainingEndDate(b.getTrainingEndDate());
//			cohortDetail.setSl(b.getSl());
//			cohortDetail.setPractice(b.getPractice());
//			cohortDetail.setBuilding(roomId.getBuilding());
//			cohortDetail.setFacility(roomId.getFacility());
//			cohortDetail.setLocation(roomId.getLocation());
//			cohortDetail.setFloorNumber(roomId.getFloorNumber());
//			cohortDetail.setRoomNo(roomId.getRoomNumber());
//			 return  roomRepository.getById(roomId).toString();
//		  }
//		 return "Room not Avaliable for this Batch size";
//	}
//	
//	public RoomId bestFit(int size,List<Room> roomSummary) {
//		int low=0;
//		int high=roomSummary.size()-1;
//		int ans=-1;
//		while(low<=high) {
//		 int mid=(low+high)/2;
//		 if(roomSummary.get(mid).getCanBeUtilizedSeats()>=size) {
//			 ans=mid;
//			 high=ans-1;
//		 }
//		 else {
//			 low=mid+1;
//		 }
//		}
//		if(ans==-1) return new RoomId("dummy", "dummy", "dummy", -1, "dummy", -1);
//		return roomSummary.get(ans).getId();
//		
//	}
//	
//	public RoomId worstFit(int size, List<Room> roomSummary) {
//		int idx=roomSummary.size()-1;
//		if(roomSummary.get(idx).getCanBeUtilizedSeats()<size)
//			return new RoomId("dummy", "dummy", "dummy", -1, "dummy", -1);
//		return roomSummary.get(idx).getId();
//	}
 
}
 
 

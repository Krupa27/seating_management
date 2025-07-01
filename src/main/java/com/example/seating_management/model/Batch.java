package com.example.seating_management.model;


import java.time.LocalDate;
 
public class Batch{
	private String cohortCode;
 
    private Integer inTrainingCount;
    private Integer graduatedCount;
    private Integer exitCount;
 
    private LocalDate trainingStartDate;
    private LocalDate trainingEndDate;
    private String batchOwner;
    private LocalDate dateOfJoining;
    private String sl;
    private String practice;
    public Batch() {}
	public Batch(String cohortCode, Integer inTrainingCount, Integer graduatedCount, Integer exitCount,
			LocalDate trainingStartDate, LocalDate trainingEndDate, String batchOwner, LocalDate dateOfJoining,
			String sl, String practice) {
		super();
		this.cohortCode = cohortCode;
		this.inTrainingCount = inTrainingCount;
		this.graduatedCount = graduatedCount;
		this.exitCount = exitCount;
		this.trainingStartDate = trainingStartDate;
		this.trainingEndDate = trainingEndDate;
		this.batchOwner = batchOwner;
		this.dateOfJoining = dateOfJoining;
		this.sl = sl;
		this.practice = practice;
	}
 
	@Override
	public String toString() {
		return "Batch [cohortCode=" + cohortCode + ", inTrainingCount=" + inTrainingCount + ", graduatedCount="
				+ graduatedCount + ", exitCount=" + exitCount + ", trainingStartDate=" + trainingStartDate
				+ ", trainingEndDate=" + trainingEndDate + ", batchOwner=" + batchOwner + ", dateOfJoining="
				+ dateOfJoining + ", sl=" + sl + ", practice=" + practice + "]";
	}
 
	public String getCohortCode() {
		return cohortCode;
	}
 
	public void setCohortCode(String cohortCode) {
		this.cohortCode = cohortCode;
	}
 
	public Integer getInTrainingCount() {
		return inTrainingCount;
	}
 
	public void setInTrainingCount(Integer inTrainingCount) {
		this.inTrainingCount = inTrainingCount;
	}
 
	public Integer getGraduatedCount() {
		return graduatedCount;
	}
 
	public void setGraduatedCount(Integer graduatedCount) {
		this.graduatedCount = graduatedCount;
	}
 
	public Integer getExitCount() {
		return exitCount;
	}
 
	public void setExitCount(Integer exitCount) {
		this.exitCount = exitCount;
	}
 
	public LocalDate getTrainingStartDate() {
		return trainingStartDate;
	}
 
	public void setTrainingStartDate(LocalDate trainingStartDate) {
		this.trainingStartDate = trainingStartDate;
	}
 
	public LocalDate getTrainingEndDate() {
		return trainingEndDate;
	}
 
	public void setTrainingEndDate(LocalDate trainingEndDate) {
		this.trainingEndDate = trainingEndDate;
	}
 
	public String getBatchOwner() {
		return batchOwner;
	}
 
	public void setBatchOwner(String batchOwner) {
		this.batchOwner = batchOwner;
	}
 
	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}
 
	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
 
	public String getSl() {
		return sl;
	}
 
	public void setSl(String sl) {
		this.sl = sl;
	}
 
	public String getPractice() {
		return practice;
	}
 
	public void setPractice(String practice) {
		this.practice = practice;
	}
	
	
	
	
    
}
 

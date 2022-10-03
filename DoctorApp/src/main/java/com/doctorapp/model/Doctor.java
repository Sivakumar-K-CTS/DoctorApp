package com.doctorapp.model;

import java.sql.Timestamp;

public class Doctor {
	private String doctorName;
	private Integer doctorId;
	private String speciality;
	private double fees;
	private int experience;
	private Timestamp startTime;
	private Timestamp endTime;
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Doctor(String doctorName, Integer doctorId, String speciality, double fees, int experience,
			Timestamp startTime, Timestamp endTime) {
		super();
		this.doctorName = doctorName;
		this.doctorId = doctorId;
		this.speciality = speciality;
		this.fees = fees;
		this.experience = experience;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public Doctor(String doctorName, String speciality, double fees, int experience,
			Timestamp startTime, Timestamp endTime) {
		super();
		this.doctorName = doctorName;
		this.speciality = speciality;
		this.fees = fees;
		this.experience = experience;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "Doctor [doctorName=" + doctorName + ", doctorId=" + doctorId + ", speciality=" + speciality + ", fees="
				+ fees + ", experience=" + experience + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
	

}

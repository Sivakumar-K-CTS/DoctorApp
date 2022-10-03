package com.doctorapp.util;

public class Queries {
	public static final String ADD = "insert into doctor (Name,Speciality,Fees,Experience,startTime,endTime)values(?,?,?,?,?,?)";
	public static final String UPDATE = "Update doctor set Fees=? where Id=?";
	public static final String GETBYID = "Select * from doctor where Id=?";
	public static final String GETALL = "Select * from doctor";
	public static final String DELETE = "delete from doctor where Id=?";
	public static final String GETBYSPEC = "Select * from doctor where Speciality=?";
	public static final String GETBYSPECANDEXP = "Select * from doctor where Speciality=? and experience=?";
	public static final String GETBYSPECANDFEES = "Select * from doctor where Speciality=? and fees=?";
	public static final String GETBYAVAIL = "Select * from doctor where startTime=?";

}

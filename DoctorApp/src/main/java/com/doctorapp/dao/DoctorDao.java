package com.doctorapp.dao;

import java.sql.Timestamp;
import java.util.List;

import com.doctorapp.model.Doctor;

public interface DoctorDao {
	
	boolean addDoctor(Doctor doctor);
	boolean updateDoctor(int doctorId,double fees);
	
	Doctor getById(int doctorId);
	boolean deleteDoctor(int doctorId);
	
	List<Doctor> getAllDoctors();
	List<Doctor> getBySpecialization(String speciality);
	List<Doctor> getBySpecilityAndExp(String specility, int experience);
	List<Doctor> getBySpecilityAndFees(String specility, Double fees);
	List<Doctor> getByAvailablity(Timestamp startTime);

}

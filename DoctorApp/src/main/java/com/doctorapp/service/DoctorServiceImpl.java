package com.doctorapp.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.doctorapp.dao.DoctorDao;
import com.doctorapp.dao.DoctorDaoImpl;
import com.doctorapp.exception.DoctorNotFoundException;
import com.doctorapp.exception.IdNotFoundException;
import com.doctorapp.model.Doctor;

public class DoctorServiceImpl implements DoctorService{
	
	DoctorDao service = new DoctorDaoImpl();

	@Override
	public boolean addDoctor(Doctor doctor) {
		return service.addDoctor(doctor);
	}

	@Override
	public boolean updateDoctor(int doctorId, double fees){
		boolean result = true;
		result = service.updateDoctor(doctorId, fees);
		if(result==false) {
			return true;
		}else {
			throw new IdNotFoundException();
		}
			
	}


	@Override
	public boolean deleteDoctor(int doctorId) {
		return !(service.deleteDoctor(doctorId));
	}

	@Override
	public List<Doctor> getAllDoctors() {
		List<Doctor>doctorsList= service.getAllDoctors();
		doctorsList = doctorsList.stream()
				.sorted((name1,name2)->name1.getDoctorName().compareTo(name2.getDoctorName()))
				.collect(Collectors.toList());
		return doctorsList;
	}


	@Override
	public Doctor getById(int doctorId) {
		Doctor doctor = new Doctor();		
	
		try {
			doctor = service.getById(doctorId);
		}catch(DoctorNotFoundException e){
			System.out.println(e.getMessage());
		}
		return doctor;
	}
		
	@Override
	public List<Doctor> getBySpecialization(String speciality) {
		List<Doctor> doctorsListbySpec = new ArrayList<>();
		try {
		doctorsListbySpec= service.getBySpecialization(speciality);
		doctorsListbySpec = doctorsListbySpec.stream()
				.sorted((name1,name2)->name1.getDoctorName().compareTo(name2.getDoctorName()))
				.collect(Collectors.toList());
		}catch(DoctorNotFoundException e){
			System.out.println(e.getMessage());
		}
		return doctorsListbySpec;
	}

	@Override
	public List<Doctor> getBySpecilityAndExp(String specility, int experience) {
		List<Doctor> doctorsList = new ArrayList<>();
		try {
		doctorsList = service.getBySpecilityAndExp(specility, experience);
		doctorsList = doctorsList.stream()
				.sorted((name1,name2)->name1.getDoctorName().compareTo(name2.getDoctorName()))
				.collect(Collectors.toList());
		
		}catch(DoctorNotFoundException e){
			System.out.println(e.getMessage());
		}
		return doctorsList;
	}

	@Override
	public List<Doctor> getBySpecilityAndFees(String specility, Double fees) {
		List<Doctor> doctorsListSpecnFee = new ArrayList<>();
		try {
		doctorsListSpecnFee = service.getBySpecilityAndFees(specility, fees);
		doctorsListSpecnFee = doctorsListSpecnFee.stream()
				.sorted((name1,name2)->name1.getDoctorName().compareTo(name2.getDoctorName()))
				.collect(Collectors.toList());
		}catch(DoctorNotFoundException e){
			System.out.println(e.getMessage());
		}
		return doctorsListSpecnFee;
	}

	@Override
	public List<Doctor> getByAvailablity(Timestamp startTime) {
		List<Doctor> doctorsListAvail = new ArrayList<>();
		try {
			doctorsListAvail = service.getByAvailablity(startTime);
			doctorsListAvail = doctorsListAvail.stream()
				.sorted((name1,name2)->name1.getDoctorName().compareTo(name2.getDoctorName()))
				.collect(Collectors.toList());
		}catch(DoctorNotFoundException e){
				System.out.println(e.getMessage());
			}
		return doctorsListAvail;
	}

}

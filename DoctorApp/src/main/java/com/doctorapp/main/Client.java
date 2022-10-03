package com.doctorapp.main;

import java.sql.Timestamp;
import java.util.*;

import com.doctorapp.model.Doctor;
import com.doctorapp.model.Specialisation;
import com.doctorapp.service.DoctorService;
import com.doctorapp.service.DoctorServiceImpl;

public class Client {

	public static void main(String[] args) {
		
		DoctorService service = new DoctorServiceImpl();
		Scanner sc = new Scanner(System.in);
		System.out.println("!!!Welcome Doctor Database!!!\nChoose options:");
		System.out.println("Press 1 from Manager / Press 2 for Patients");
		int role = sc.nextInt();
		sc.nextLine();
		if(role ==1 )
		{
			System.out.println("1.Add New Doctor\n2.Update Doctor Fees\n3.Delete Doctor records\n4.Get Doctors list by ID");
			int option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				System.out.println("Enter Name:");
				String name=sc.nextLine();
				System.out.println("Choose Speciality:(1.Orthopeadicist\n2.Pediatrician\n3.Diabeticist\n4.Cardiologist\n5.Dentistery\n6.General physician)");
				int spec = sc.nextInt();
				Specialisation[] specialityArr = Specialisation.values();
				String speciality = specialityArr[spec].type;
				System.out.println("Enter Fees:");
				double fees = sc.nextDouble();
				sc.nextLine();
				System.out.println("Enter Years of Experience:");
				int exp = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Start Date&Time(yyyy-mm-dd hh:mm:ss):");
				String timestart = sc.nextLine();
				Timestamp startTime = Timestamp.valueOf(timestart);
				System.out.println("Enter Start Date&Time(yyyy-mm-dd hh:mm:ss):");
				String timeend = sc.nextLine();
				Timestamp endTime = Timestamp.valueOf(timeend);
				
				Doctor doc = new Doctor(name,speciality,fees,exp,startTime,endTime);
				if(service.addDoctor(doc)){
					System.out.println("Doctor record has been successfully added");
				}else {
					System.out.println("Doctor record has not been added!!");
				}
				break;
			case 2:
				System.out.println("Enter Doctor's ID:");
				int id = sc.nextInt();
				System.out.println("Enter the New Fees:");
				double fee = sc.nextDouble();
				System.out.println(service.updateDoctor(id, fee));
				break;
			case 3:
				System.out.println("Enter DoctorId to delete");
				int doctorId=sc.nextInt();
				service.deleteDoctor(doctorId);
				System.out.println("Deleted");
				break;
			case 4:
				System.out.println("Get Doctor by Id");
		    	int docId=sc.nextInt();
		    	System.out.println(service.getById(docId));
				break;
			default:
				System.out.println("!!!Invalid Input!!!");
			}
			sc.close();
		}else if(role==2) {
			System.out.println("1.Get all Doctors List\n2.Get Doctors list by Specialization\n3.Get Doctors List by Specialization and Experience\n4.Get Doctors List by Specialization and Fees\n5.Get Doctors List by Availablity");
			int option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				List<Doctor> doctorList = service.getAllDoctors();
		    	System.out.println("List of Doctors Working:");
	        	doctorList.forEach(System.out::println);
				break;
			case 2:
				System.out.println("Choose Speciality:(0.Orthopeadicist\n1.Pediatrician\n2.Diabeticist\n3.Cardiologist\n4.Dentistery\n5.General physician)");
				int spec1 = sc.nextInt();
				String speciality1 = Specialisation.values()[spec1].type;
	        	List<Doctor> specialityList = service.getBySpecialization(speciality1);
	        	System.out.println("The list of Doctors based on Speciality:");
				specialityList.forEach(System.out::println);	
				break;
			case 3:
				System.out.println("Choose Speciality:(0.Orthopeadicist\n1.Pediatrician\n2.Diabeticist\n3.Cardiologist\n4.Dentistery\n5.General physician)");
				int specExp = sc.nextInt();
				String doctorSpeciality = Specialisation.values()[specExp].type;
				System.out.println("Enter Years of Experience:");
				int experience=sc.nextInt();
		        List<Doctor> specialityExpList = service.getBySpecilityAndExp(doctorSpeciality,experience);
				specialityExpList.forEach(System.out::println);	
				break;
			case 4:
				System.out.println("Choose Speciality:(0.Orthopeadicist\n1.Pediatrician\n2.Diabeticist\n3.Cardiologist\n4.Dentistery\n5.General physician)");
				int specFee = sc.nextInt();
				String docSpeciality = Specialisation.values()[specFee].type;
				System.out.println("Enter Fees:");
		    	double doctorFee=sc.nextDouble();
		    	List<Doctor> specialityFeeList = service.getBySpecilityAndFees(docSpeciality,doctorFee);
				specialityFeeList.forEach(System.out::println);		
				break;
			case 5:
				System.out.println("Enter Date&Time(yyyy-mm-dd hh:mm:ss):");
				String str = sc.nextLine(); 
				Timestamp dateTime = Timestamp.valueOf(str);
				List<Doctor> availabilityList = service.getByAvailablity(dateTime);
				availabilityList.forEach(System.out::println);
				break;
			default:
				System.out.println("!!!Invalid Input!!!");
			
			}
			sc.close();
		}else {
			System.out.println("Invalid Input!!!");
		}		
	}
}



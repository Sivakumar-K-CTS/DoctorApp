package com.doctorapp.model;


public enum Specialisation {
	ORTHO("Orthopeadicist"),
	PEADO("Pediatrician"),
	DIABETIC("Diabeticist"),
	CARDIO("Cardiologist"),
	DENTIST("Dentistery"),
	PHYSICIAN("General physician");
	
	public String type;
	
	private Specialisation(String type) {
		this.type = type;
	}
}

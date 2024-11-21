package com.backend.getanopinion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.getanopinion.model.Patient;

public interface PatientRepo extends JpaRepository<Patient, Long> {

	Patient findByPatientEmail(String patientEmail);
	
	@Query("SELECT p.patientEmail FROM Patient p WHERE p.patientId = :patientId")
    String findPatientEmailByPatientId(@Param("patientId") Long patientId);
}

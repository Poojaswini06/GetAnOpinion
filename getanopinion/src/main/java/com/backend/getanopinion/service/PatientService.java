package com.backend.getanopinion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.getanopinion.model.Patient;
import com.backend.getanopinion.repository.PatientRepo;

@Service 
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;

    public Patient savePatient(Patient patient) {
        return patientRepo.save(patient);
    }

	public Patient findByEmail(String patientEmail) {
		return patientRepo.findByPatientEmail(patientEmail);
	}

	public String getEmail(Long patientId) {
		// TODO Auto-generated method stub
		return patientRepo.findPatientEmailByPatientId(patientId);
	}
}

package com.backend.getanopinion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.getanopinion.model.Doctor;
import com.backend.getanopinion.repository.DoctorRepo;

@Service
public class DoctorService {
	@Autowired
    private DoctorRepo doctorRepo;
	
	public Doctor findByEmail(String doctorEmail) {
		return doctorRepo.findByDoctorEmail(doctorEmail);
	}

}

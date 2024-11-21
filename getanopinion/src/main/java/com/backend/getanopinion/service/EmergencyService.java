package com.backend.getanopinion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.getanopinion.model.Emergency;
import com.backend.getanopinion.repository.EmergencyRepo;

@Service
public class EmergencyService {
	@Autowired
    private EmergencyRepo eRepo;

	public Emergency saveDetails(Long pid, String pname, Long dId, String demail, String esent) {
		// TODO Auto-generated method stub
		Emergency e = new Emergency();
		e.setPatientId(pid);
		e.setPatientEmail(pname);
		e.setDoctorId(dId);
		e.setDoctortEmail(demail);
		e.setEmailSent(demail);
		return eRepo.save(e);
	}

}

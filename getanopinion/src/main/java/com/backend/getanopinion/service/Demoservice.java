package com.backend.getanopinion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.getanopinion.model.Demomodel;
import com.backend.getanopinion.repository.Demorepo;

@Service
public class Demoservice {
	@Autowired
	private Demorepo demorepo;
	// Method to find user by email
    public Demomodel findByEmail(String email) {
        return demorepo.findByEmail(email);
}
}
package com.backend.getanopinion.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.getanopinion.model.Doctor;
import com.backend.getanopinion.service.DoctorService;



@RestController
@RequestMapping("/doctorapi")
public class DoctorController {
	 @Autowired
	    private DoctorService doctorService;
	 @PostMapping("/doctorlogin")
	    public ResponseEntity<?> dlogin(@RequestBody Doctor doctor) {
	        Doctor user = doctorService.findByEmail(doctor.getDoctorEmail());
	        if (user == null || !user.getDoctorPassword().equals(doctor.getDoctorPassword())) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
	        }
	        // Return patient ID along with success message
	        return ResponseEntity.ok(Map.of("message", "Login successful", "doctorId", user.getDoctorId()));
	    }
}

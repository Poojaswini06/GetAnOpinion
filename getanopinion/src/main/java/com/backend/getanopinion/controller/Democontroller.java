package com.backend.getanopinion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.getanopinion.model.Demomodel;
import com.backend.getanopinion.service.Demoservice;

@RestController
@RequestMapping("/api")
public class Democontroller {
	@Autowired
	private Demoservice demoservice;
	
	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Demomodel demomodel) {
		Demomodel user = demoservice.findByEmail(demomodel.getEmail());

        // Check if the user exists
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Validate password
        if (!user.getPassword().equals(demomodel.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // If credentials are valid, return success message
        return ResponseEntity.ok("Login successful");
    }

}

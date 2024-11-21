package com.backend.getanopinion.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.getanopinion.model.BabyConcernForm;
import com.backend.getanopinion.model.Patient;
import com.backend.getanopinion.service.PatientService;

@RestController
@RequestMapping("/patientapi")
public class PatientController {
    @Autowired
    private PatientService patientService; // Change variable name to patientService

    @PostMapping("/signup")
    public ResponseEntity<Patient> signup(@RequestBody Patient patient) {
        Patient savedPatient = patientService.savePatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }
    @PostMapping("/patientlogin")
    public ResponseEntity<?> plogin(@RequestBody Patient patient) {
        Patient user = patientService.findByEmail(patient.getPatientEmail());
        if (user == null || !user.getPatientPassword().equals(patient.getPatientPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        // Return patient ID along with success message
        return ResponseEntity.ok(Map.of("message", "Login successful", "patientId", user.getPatientId()));
    }
    @GetMapping("/retrievePatientEmail/{patientId}")
    public ResponseEntity<?> getPatientEmail(@PathVariable Long patientId) {
        try {
            String patientEmail = patientService.getEmail(patientId);
            return ResponseEntity.ok(patientEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error occurred while fetching patient email: " + e.getMessage());
        }
    }
}

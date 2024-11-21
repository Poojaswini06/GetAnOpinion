package com.backend.getanopinion.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.getanopinion.model.Emergency;
import com.backend.getanopinion.service.EmergencyService;

@RestController
@RequestMapping("/emergency")
public class EmergencyController {

    @Autowired
    private EmergencyService eService;
	
    @PostMapping("/details")
    public ResponseEntity<?> saveEmergencyRequestDetails(@RequestBody Map<String, Object> emergencyInfo) {
        try {
            Long pid = Long.parseLong(emergencyInfo.get("patientId").toString());
            String pname = emergencyInfo.get("patientEmail").toString();
            Long dId = Long.parseLong(emergencyInfo.get("doctorId").toString());
            String demail = emergencyInfo.get("doctorName").toString();
            String esent = emergencyInfo.get("emailSent").toString();

            // Validate and save Emergency object
            Emergency e = eService.saveDetails(pid, pname, dId, demail, esent);
            return ResponseEntity.status(HttpStatus.OK).body(e);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

}

package com.backend.getanopinion.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;

import com.backend.getanopinion.model.BabyConcernForm;
import com.backend.getanopinion.model.Patient;
import com.backend.getanopinion.service.BabyConcernFormService;


@RestController
@RequestMapping("/babyapi")
public class BabyConcernFormController {
	private BabyConcernFormService service;

    @Autowired
    public void BabyController(BabyConcernFormService service) {
        this.service = service;
    }
    
 // Ensure that the saveSelectedDoctor method expects the correct parameters
    @PostMapping("/selecteddoctor")
    public ResponseEntity<?> saveSelectedDoctor(@RequestBody Map<String, Object> doctorInfo) {
        try {
            Long appointmentNumber = Long.parseLong(doctorInfo.get("appointmentNumber").toString());
            Long doctorId = Long.parseLong(doctorInfo.get("doctorId").toString());
            String doctorName = doctorInfo.get("doctorName").toString();
            
            BabyConcernForm concernForm = service.saveSelectedDoctor(appointmentNumber, doctorId, doctorName);
            return ResponseEntity.status(HttpStatus.OK).body(concernForm);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
    @PostMapping("/datetime")
    public ResponseEntity<?> saveSelectedDateTime(@RequestBody Map<String, Object> dateTimeInfo) {
        try {
            Long appointmentNumber = Long.parseLong(dateTimeInfo.get("appointmentNumber").toString());
            String selectedDate = dateTimeInfo.get("selectedDate").toString();
            String selectedTime = dateTimeInfo.get("selectedTime").toString();
            
            System.out.print(appointmentNumber+selectedDate+selectedTime);

            BabyConcernForm concernForm = service.saveSelectedDateTime(appointmentNumber, selectedDate, selectedTime);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Selected date and time saved successfully");
            response.put("appointmentNumber", concernForm.getAppointmentNumber());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }


    @PostMapping("/concernform")
    public ResponseEntity<?> handleBabyConcernForm(
            @RequestParam("patientId") Long patientId,
            @RequestParam("babyName") String babyName,
            @RequestParam("isBabyActive") boolean isBabyActive,
            @RequestParam("hasFever") boolean hasFever,
            @RequestParam("hasBreathingDifficulty") boolean hasBreathingDifficulty,
            @RequestParam("hasSkinColourChange") boolean hasSkinColourChange,
            @RequestParam("hasVomitings") boolean hasVomitings,
            @RequestParam("concern") String concern,
            @RequestParam("pdfFile") MultipartFile pdfFile
    ) {
        try {
            // Create a new instance of BabyConcernForm
            BabyConcernForm form = new BabyConcernForm();
            form.setPatientId(patientId);
            form.setBabyName(babyName);
            form.setBabyActive(isBabyActive);
            form.setHasFever(hasFever);
            form.setHasBreathingDifficulty(hasBreathingDifficulty);
            form.setHasSkinColourChange(hasSkinColourChange);
            form.setHasVomitings(hasVomitings);
            form.setConcern(concern);

            // Check if pdfFile is not null and not empty
            if (pdfFile != null && !pdfFile.isEmpty()) {
                // Convert the MultipartFile to a byte array and set it in the form
                form.setPdfFile(pdfFile.getBytes());
            }

            // Save the form using the service
            service.submitConcernForm(form);

            // Return a ResponseEntity with success message and appointment number
            return ResponseEntity.ok(Map.of("message", "successfully saved", "appointmentNumber", form.getAppointmentNumber()));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing the PDF file.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
    
    @GetMapping("/upcomingAppointments/{patientId}")
    public ResponseEntity<?> getUpcomingAppointmentsForPatient(@PathVariable Long patientId) {
        try {
            List<BabyConcernForm> appointments = service.getUpcomingAppointmentsForPatient(patientId);
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error occurred while fetching appointments: " + e.getMessage());
        }
    }
    
    
  // Written by Bhanu
    
   
    @GetMapping("/getFreeSlots")
    public ResponseEntity<List<String>> getFreeSlotsForDoctor(@RequestParam Long doctorId, @RequestParam String date) {
        try {
            List<String> freeSlots = service.getFreeSlotsForDoctor(doctorId, date);
            return ResponseEntity.ok(freeSlots);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    
    @GetMapping("/upcomingDoctor/{doctorId}")
    public ResponseEntity<?> getUpcomingAppointmentsForDoctor(@PathVariable Long doctorId) {
        try {
            List<BabyConcernForm> appointments = service.getUpcomingAppointmentsForDoctor(doctorId);
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error occurred while fetching appointments: " + e.getMessage());
        }
    }
    @DeleteMapping("/appointments/{appointmentNumber}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long appointmentNumber) {
    	try {
            service.deleteAppointment(appointmentNumber);
            return ResponseEntity.ok("Appointment deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting appointment: " + e.getMessage());
        }
    }
    @GetMapping("/getPdf/{appointmentNumber}")
    public ResponseEntity<byte[]> getPdfFile(@PathVariable Long appointmentNumber) {
        BabyConcernForm concernForm = service.getAppointmentById(appointmentNumber);
        
        if (concernForm == null || concernForm.getPdfFile() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        byte[] pdfBytes = concernForm.getPdfFile();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "appointment.pdf");  // Display PDF in the browser
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
    
    @PostMapping("/zoomLink/{appointmentNumber}")
    public ResponseEntity<?> saveZoomLink(@PathVariable Long appointmentNumber, @RequestBody BabyConcernForm baby) {
        try {
            // Example of saving zoom link associated with the appointment number
        	service.saveZoomLink(appointmentNumber, baby.getZoomlink());
            return ResponseEntity.ok("Zoom link saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error occurred while saving zoom link: " + e.getMessage());
        }
    }

}

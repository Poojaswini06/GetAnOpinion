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

import com.backend.getanopinion.model.SummaryNotesModel;
import com.backend.getanopinion.service.SummaryNotesService;

@RestController
@RequestMapping("/summary")
public class SummaryNotesController {
    private SummaryNotesService service;

    @Autowired
    public SummaryNotesController(SummaryNotesService service) {
        this.service = service;
    }
    @PostMapping("/notes")
    public ResponseEntity<?> saveNotes(@RequestBody Map<String, Object> notesinfo) {
    	try {
    		Long doctorId=Long.parseLong(notesinfo.get("doctorId").toString());
    		Long patientId=Long.parseLong(notesinfo.get("patientId").toString());
    		String patientName=notesinfo.get("patientName").toString();
    		String selectedDate=notesinfo.get("selectedDate").toString();
    		String summaryNotes=notesinfo.get("summaryNotes").toString();
    		SummaryNotesModel model=service.saveNotes(doctorId,patientId,patientName,selectedDate,summaryNotes);
    		return ResponseEntity.status(HttpStatus.OK).body(model);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getSummaryNotes(@PathVariable Long patientId) {
        try {
            List<SummaryNotesModel> summary = service.getPatientSummaryNotes(patientId);
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error occurred while fetching appointments: " + e.getMessage());
        }
    }
}

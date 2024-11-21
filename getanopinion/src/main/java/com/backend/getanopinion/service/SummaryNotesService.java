package com.backend.getanopinion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.getanopinion.model.SummaryNotesModel;
import com.backend.getanopinion.repository.SummaryNotesRepo;
@Service
public class SummaryNotesService {
	@Autowired
    private SummaryNotesRepo notesRepo;

	public SummaryNotesModel saveNotes(Long doctorId, Long patientId, String patientName, String selectedDate,
			String summaryNotes) {
		SummaryNotesModel model = new SummaryNotesModel();
		model.setDoctorId(doctorId);
		model.setPatientId(patientId);
		model.setPatientName(patientName);
		model.setSelectedDate(selectedDate);
		model.setNotes(summaryNotes);
        return notesRepo.save(model);
		
	}

	public List<SummaryNotesModel> getPatientSummaryNotes(Long patientId) {
		// TODO Auto-generated method stub
		return notesRepo.findAllByPatientId(patientId);
	}

}

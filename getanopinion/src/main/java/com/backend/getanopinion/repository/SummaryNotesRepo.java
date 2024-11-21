package com.backend.getanopinion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.getanopinion.model.SummaryNotesModel;

public interface SummaryNotesRepo extends JpaRepository<SummaryNotesModel, Long> {

	List<SummaryNotesModel> findAllByPatientId(Long patientId);
}

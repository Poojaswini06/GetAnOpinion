package com.backend.getanopinion.repository;

import com.backend.getanopinion.model.BabyConcernForm;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface BabyConcernFormRepo extends JpaRepository<BabyConcernForm, Long>{
	List<BabyConcernForm> findAllByPatientId(Long patientId);
//same alternate query
//@Query("SELECT b FROM BabyConcernForm b WHERE b.patientId = :patientId")
	//List<BabyConcernForm> findAllByPatientId(@Param("patientId") Long patientId);

	@Modifying
    @Transactional
    @Query("DELETE FROM BabyConcernForm b WHERE b.selectedTime IS NULL")
    void deleteIncompleteApp();

	List<BabyConcernForm> findAllByDoctorId(Long doctorId);
	
	
	// Written by Bhanu
	
	@Query(value = "SELECT selected_time FROM baby_appointment WHERE doctor_id = :doctorId AND selected_date = :date", nativeQuery = true)
    List<String> findOccupiedSlots(@Param("doctorId") Long doctorId, @Param("date") String date);

}

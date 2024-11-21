package com.backend.getanopinion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.getanopinion.model.Doctor;


public interface DoctorRepo extends JpaRepository<Doctor, Long>{

	Doctor findByDoctorEmail(String DoctorEmail);

}

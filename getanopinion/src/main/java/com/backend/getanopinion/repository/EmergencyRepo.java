package com.backend.getanopinion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.getanopinion.model.Emergency;



public interface EmergencyRepo  extends JpaRepository<Emergency, Long> {

}

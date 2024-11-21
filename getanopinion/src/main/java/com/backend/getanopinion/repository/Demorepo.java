package com.backend.getanopinion.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.getanopinion.model.Demomodel;

public interface Demorepo extends JpaRepository<Demomodel, Long> {
	Demomodel findByEmail(String email);
}

package com.backend.getanopinion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Emergency {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SNo;
	
	@Column
	private Long patientId;
	
	@Column 
    private String patientEmail;
	
	@Column
	private Long doctorId;
	
	@Column 
    private String doctortEmail;
	
	

	public Long getSNo() {
		return SNo;
	}

	public void setSNo(Long sNo) {
		SNo = sNo;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctortEmail() {
		return doctortEmail;
	}

	public void setDoctortEmail(String doctortEmail) {
		this.doctortEmail = doctortEmail;
	}

	public String getEmailSent() {
		return emailSent;
	}

	public void setEmailSent(String emailSent) {
		this.emailSent = emailSent;
	}

	@Column
	private String emailSent;
	
	public Emergency(){
		
	}
	

}

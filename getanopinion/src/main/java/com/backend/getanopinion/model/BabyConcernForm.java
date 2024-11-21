package com.backend.getanopinion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "babyAppointment")
public class BabyConcernForm {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentNumber;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "baby_name")
    private String babyName;

    @Column(name = "is_baby_active")
    private boolean isBabyActive;

    @Column(name = "has_fever")
    private boolean hasFever;

	@Column(name = "has_breathing_difficulty")
    private boolean hasBreathingDifficulty;

    @Column(name = "has_skin_colour_change")
    private boolean hasSkinColourChange;

    @Column(name = "has_vomitings")
    private boolean hasVomitings;

    @Column(name = "concern")
    private String concern;
    
    @Lob
    @Column(name = "pdf_file", columnDefinition="LONGBLOB")
    private byte[] pdfFile;
    
    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "selected_date")
    private String selectedDate;

    @Column(name = "selected_time")
    private String selectedTime;
    
	public String getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	public String getSelectedTime() {
		return selectedTime;
	}

	public void setSelectedTime(String selectedTime) {
		this.selectedTime = selectedTime;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public byte[] getPdfFile() {
		return pdfFile;
	}

	public void setPdfFile(byte[] pdfFile) {
		this.pdfFile = pdfFile;
	}

	public Long getAppointmentNumber() {
		return appointmentNumber;
	}

	public void setAppointmentNumber(Long appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getBabyName() {
		return babyName;
	}

	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}

	public boolean isBabyActive() {
		return isBabyActive;
	}

	public void setBabyActive(boolean isBabyActive) {
		this.isBabyActive = isBabyActive;
	}

	public boolean isHasFever() {
		return hasFever;
	}

	public void setHasFever(boolean hasFever) {
		this.hasFever = hasFever;
	}

	public boolean isHasBreathingDifficulty() {
		return hasBreathingDifficulty;
	}

	public void setHasBreathingDifficulty(boolean hasBreathingDifficulty) {
		this.hasBreathingDifficulty = hasBreathingDifficulty;
	}

	public boolean isHasSkinColourChange() {
		return hasSkinColourChange;
	}

	public void setHasSkinColourChange(boolean hasSkinColourChange) {
		this.hasSkinColourChange = hasSkinColourChange;
	}

	public boolean isHasVomitings() {
		return hasVomitings;
	}

	public void setHasVomitings(boolean hasVomitings) {
		this.hasVomitings = hasVomitings;
	}

	public String getConcern() {
		return concern;
	}

	public void setConcern(String concern) {
		this.concern = concern;
	}

	@Column(name = "zoomlink")
    private String zoomlink = "Yet to send"; 

    // Getters and setters (required for @RequestBody mapping)
    public String getZoomlink() {
        return zoomlink;
    }

    public void setZoomlink(String zoomlink) {
        this.zoomlink = zoomlink;
    }
	public BabyConcernForm() {
		
	}

}

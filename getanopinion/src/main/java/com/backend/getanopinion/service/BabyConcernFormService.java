package com.backend.getanopinion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.getanopinion.model.BabyConcernForm;
import com.backend.getanopinion.repository.BabyConcernFormRepo;

@Service
public class BabyConcernFormService {
	@Autowired
    private BabyConcernFormRepo concernFormRepo;

    public BabyConcernForm submitConcernForm(BabyConcernForm babyConcernForm) {
        // Perform any additional processing or validation here
        return concernFormRepo.save(babyConcernForm);
    }


    public BabyConcernForm saveSelectedDoctor(Long appointmentNumber, Long doctorId, String doctorName) {
        // Example logic: Retrieve the form by appointment number and update the doctor information
        BabyConcernForm concernForm = concernFormRepo.findById(appointmentNumber).orElse(null);
        if (concernForm != null) {
            concernForm.setDoctorId(doctorId);
            concernForm.setDoctorName(doctorName);
            return concernFormRepo.save(concernForm);
        } else {
            // Handle case when no form is found for the given appointmentNumber
            throw new IllegalArgumentException("No form found for appointmentNumber: " + appointmentNumber);
        }
    }
    public BabyConcernForm saveSelectedDateTime(Long appointmentNumber, String selectedDate, String selectedTime) {
        BabyConcernForm concernForm = concernFormRepo.findById(appointmentNumber).orElse(null);
        if (concernForm != null) {
            concernForm.setSelectedDate(selectedDate);
            concernForm.setSelectedTime(selectedTime);
            return concernFormRepo.save(concernForm);
        } else {
            throw new IllegalArgumentException("No form found for appointmentNumber: " + appointmentNumber);
        }
    }
    public List<BabyConcernForm> getUpcomingAppointmentsForPatient(Long patientId) {
        // Implement logic to retrieve upcoming appointments for the specified patient
        return concernFormRepo.findAllByPatientId(patientId);
    }


	public List<BabyConcernForm> getUpcomingAppointmentsForDoctor(Long doctorId) {
		// TODO Auto-generated method stub
		return concernFormRepo.findAllByDoctorId(doctorId);
	}
	
	
	//Written by Bhanu
	public List<String> getFreeSlotsForDoctor(Long doctorId, String date) {
	    // Retrieve occupied slots using a native SQL query
	    List<String> occupiedSlots = new ArrayList<>(concernFormRepo.findOccupiedSlots(doctorId, date));
	    
	    for (String x : occupiedSlots) {
	        System.out.print("Occupeid Slot" + x);
	    }

	    // Get all possible slots and make sure it's mutable
	    List<String> allPossibleSlots = getAllPossibleSlots();
	    allPossibleSlots.removeAll(occupiedSlots);

	    return allPossibleSlots;
	}

	private List<String> getAllPossibleSlots() {
	    // Return a mutable list of all possible time slots for the day
	    return new ArrayList<>(List.of("11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "4:00 PM", "6:00 PM"));
	}

	

	public void saveZoomLink(Long appointmentNumber, String zoomlink) {
		// TODO Auto-generated method stub
		 BabyConcernForm concernForm = concernFormRepo.findById(appointmentNumber).orElse(null);
		 if (concernForm != null) {
			 concernForm.setZoomlink(zoomlink);
			 concernFormRepo.save(concernForm);
		 }else {
	            // Handle case when no form is found for the given appointmentNumber
	            throw new IllegalArgumentException("No form found for appointmentNumber: " + appointmentNumber);
	        }
	    }
	@Transactional
    public void deleteAppointment(Long appointmentNumber) {
        BabyConcernForm appointment = concernFormRepo.findById(appointmentNumber)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found for appointmentNumber: " + appointmentNumber));
        concernFormRepo.delete(appointment);
    }


	public BabyConcernForm getAppointmentById(Long appointmentNumber) {
		// TODO Auto-generated method stub
		return concernFormRepo.findById(appointmentNumber).orElse(null);
	}
   
}

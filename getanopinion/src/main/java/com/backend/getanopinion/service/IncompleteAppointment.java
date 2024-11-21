package com.backend.getanopinion.service;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.getanopinion.repository.BabyConcernFormRepo;
@Service
public class IncompleteAppointment {

    @Autowired
    public IncompleteAppointment(BabyConcernFormRepo concernFormRepo) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable cleanupTask = () -> {
            concernFormRepo.deleteIncompleteApp();
            System.out.println("Cleanup task executed at: " + java.time.LocalTime.now());
        };

        // Schedule the cleanup task to run every 10 minutes, starting after an initial delay of 0 seconds
        scheduler.scheduleAtFixedRate(cleanupTask, 0, 10, TimeUnit.MINUTES);
    }
}
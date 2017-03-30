/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Calendar;
import java.util.Timer;

/**
 *
 * @author CuongDH
 */
public class EcProcessTimerTask {
    private final static long ONCE_PER_DAY = 1000*60*60*24;
    private final static int START_AT_HOUR = 0;
    private final static int START_AT_MINUTE = 0;
    private final static int START_AT_SECOND = 0;
    
    public void runTask() {
        Timer timer = new Timer();
        Calendar timeToStartJob = Calendar.getInstance();
        timeToStartJob.set(Calendar.HOUR, START_AT_HOUR);
        timeToStartJob.set(Calendar.MINUTE, START_AT_MINUTE);
        timeToStartJob.set(Calendar.SECOND, START_AT_SECOND);
        
        // Schedule to run every day in midnight       
        timer.schedule(new JobScheduler(), timeToStartJob.getTime(), ONCE_PER_DAY);
    }
}

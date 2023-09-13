/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scm.shedule;

import com.scm.process.Schedule;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;



/**
 *
 * @author DineshkumarP
 *  *  * https://savvytime.com/converter/utc-to-united-arab-emirates-dubai-gmt
 * http://localhost:8080/woServices/runapps
 * http://localhost:8080/woServices/runapps?stop=true
 */
public class RunSchedule {
    
//    public static void main(String[] args) throws SchedulerException{
//        JobDetail jd= JobBuilder.newJob(Schedule.class).build();
//        
//        Trigger tr=TriggerBuilder.newTrigger().withIdentity("CronTrigger")
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(02)
//                .repeatForever()).build();
//        
//        Scheduler sc=StdSchedulerFactory.getDefaultScheduler();
//        
//        sc.start();
//        sc.scheduleJob(jd, tr);
//        
//    }
   
    
    public void runScheduler(Boolean stop) throws SchedulerException{
        
        JobDetail jd= JobBuilder.newJob(Schedule.class).build();
        
        Trigger tr=TriggerBuilder.newTrigger().withIdentity("CronTrigger")
                
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")) // Every 1 min
//              .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(02).repeatForever())
                .build();
        
        Scheduler sc=StdSchedulerFactory.getDefaultScheduler();
        
        sc.start();
        sc.scheduleJob(jd, tr);
        
        if(stop){
           sc.shutdown();
        }
        
    }
    
    
    
    
}

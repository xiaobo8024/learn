package com.txb.app.scheduler.demo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class BaseJob implements Job {
    private String jobName;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("1234");
    }

}

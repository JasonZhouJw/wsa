package com.alpha.jobs.schedule;

import com.alpha.jobs.entities.ScheduleJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jzhou237 on 2017-03-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DynamicJobFactoryTest {

    @Autowired
    private DynamicJobFactory dynamicJobFactory;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;


    @Test
    public void refreshJobDetail() throws Exception {
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setId(1L);
        scheduleJob.setName("name");
        scheduleJob.setGroup("test");
        scheduleJob.setCronExpression("0/5 * * * * ?");
        scheduleJob.setJobClassName(SampleJob.class.getName());
        Schedule.getInstance().addJob(scheduleJob);

        dynamicJobFactory.refreshJobDetail();

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getName(), scheduleJob.getGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        assertNotNull(trigger);
    }

    public class SampleJob implements Job {

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {

        }
    }

}
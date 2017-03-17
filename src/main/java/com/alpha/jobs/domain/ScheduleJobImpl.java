package com.alpha.jobs.domain;

import com.alpha.common.exceptions.DataExistException;
import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.jobs.entities.ScheduleJob;
import com.alpha.jobs.exception.JobClassNotFoundException;
import com.alpha.jobs.repository.ScheduleJobRepository;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jzhou237 on 2017-03-15.
 */
@Slf4j
@Component
public class ScheduleJobImpl implements IScheduleJob {

    @Autowired
    private ScheduleJobRepository scheduleJobRepository;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    private void refresh(ScheduleJob scheduleJob) throws JobClassNotFoundException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getName(), scheduleJob.getJobGroup());
        try {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (null == trigger) {
                JobDetail jobDetail = JobBuilder.newJob(scheduleJob.getJobClass())
                        .withIdentity(scheduleJob.getName(), scheduleJob.getJobGroup()).build();
                jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob
                        .getCronExpression());
                trigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getName(), scheduleJob.getJobGroup())
                        .withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob
                        .getCronExpression());
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder).build();
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public ScheduleJob create(ScheduleJob scheduleJob) throws DataExistException, JobClassNotFoundException {
        return saveAndRefresh(scheduleJob);
    }

    @Override
    public ScheduleJob update(ScheduleJob scheduleJob) throws DataExistException, DataNotFoundException, JobClassNotFoundException {
        ScheduleJob updatedScheduleJob = this.scheduleJobRepository.findOne(scheduleJob.getId());
        if (updatedScheduleJob == null) {
            throw new DataNotFoundException();
        }
        return saveAndRefresh(scheduleJob);
    }

    private ScheduleJob checkUnique(Long id, String name, String group) throws DataExistException {
        ScheduleJob existScheduleJob = this.scheduleJobRepository.findByNameAndJobGroup(name, group);
        if (existScheduleJob != null && !existScheduleJob.getId().equals(id)) {
            throw new DataExistException();
        }
        return existScheduleJob;
    }

    @Transactional
    protected ScheduleJob saveAndRefresh(ScheduleJob scheduleJob) throws JobClassNotFoundException, DataExistException {
        this.checkUnique(-1L, scheduleJob.getName(), scheduleJob.getJobGroup());
        ScheduleJob savedScheduleJob = this.scheduleJobRepository.save(scheduleJob);
        this.refresh(savedScheduleJob);
        return savedScheduleJob;
    }

}

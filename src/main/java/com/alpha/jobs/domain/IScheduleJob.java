package com.alpha.jobs.domain;

import com.alpha.common.exceptions.DataExistException;
import com.alpha.common.exceptions.DataNotFoundException;
import com.alpha.jobs.entities.ScheduleJob;
import com.alpha.jobs.exception.JobClassNotFoundException;

/**
 * Created by jzhou237 on 2017-03-15.
 */
public interface IScheduleJob {

    ScheduleJob create(ScheduleJob scheduleJob) throws DataExistException, JobClassNotFoundException;

    ScheduleJob update(ScheduleJob scheduleJob) throws DataExistException, DataNotFoundException, JobClassNotFoundException;
}

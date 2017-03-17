package com.alpha.jobs.repository;

import com.alpha.jobs.entities.ScheduleJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jzhou237 on 2017-03-15.
 */
@Repository
public interface ScheduleJobRepository extends JpaRepository<ScheduleJob, Long> {

    @Query("select scheduleJob from ScheduleJob scheduleJob where scheduleJob.active=1")
    List<ScheduleJob> findAllActive();

    ScheduleJob findByNameAndJobGroup(String name, String jobGroup);
}

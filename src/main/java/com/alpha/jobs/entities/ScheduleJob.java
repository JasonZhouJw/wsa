package com.alpha.jobs.entities;

import com.alpha.jobs.exception.JobClassNotFoundException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * Created by jzhou237 on 2017-03-15.
 */
@Setter
@Getter
@Slf4j
@ToString(of = {"name", "group", "cronExpression", "jobClassName"})
@Entity
@Table(indexes = {@Index(name = "INDEX_NAME_GROUP", columnList = "name,jobGroup", unique = true)})
public class ScheduleJob {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String jobGroup;

    @Column
    private boolean active;

    @Column
    private String cronExpression;

    @Column
    private String description;

    @Column
    private String jobClassName;

    public Class getJobClass() throws JobClassNotFoundException {
        try {
            return Class.forName(this.jobClassName);
        } catch (ClassNotFoundException e) {
            log.error("Can not find Class [" + this.jobClassName + "]", e);
            throw new JobClassNotFoundException("result.class.not.found");
        }
    }
}

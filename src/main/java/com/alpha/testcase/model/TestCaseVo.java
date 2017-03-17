package com.alpha.testcase.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by jzhou237 on 2016-10-06.
 */
@Getter
@Setter
@ToString
public class TestCaseVo {

    private Long id;

    private String name;

    private Long methodId;

    private Long groupId;

    private String requestValue;

    private String verification;

    private String methodName;

    private String groupName;

    private boolean active;

}

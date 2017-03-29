package com.alpha.testcase.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by jzhou237 on 2017-03-29.
 */
@Setter
@Getter
@ToString
public class CaseGroupVo {

    private Long id;

    private String name;

    private Boolean active;
}

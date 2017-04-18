package com.alpha.verifyresult.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by jzhou237 on 2017-04-18.
 */
@Setter
@Getter
public class VerifyResultSearchVo extends VerifyResultVo {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd")
    private Date startExecutedTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endExecutedTime;

}

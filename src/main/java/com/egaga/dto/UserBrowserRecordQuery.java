package com.egaga.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangzhilin
 * @date 2018/7/27
 * @description 查询条件
 */

@Data
public class UserBrowserRecordQuery implements Serializable {

    private static final long serialVersionUID = 7503774409787528372L;

    private String[] terminalCodes;

    private String[] merchantCodes;

    private TimeSlot timeSlot;

    private String bussinessSouce;


}

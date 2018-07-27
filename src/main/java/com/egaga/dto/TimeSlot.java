package com.egaga.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangzhilin
 * @date 2018/7/27
 * @description 时间范围
 */

@Data
class TimeSlot implements Serializable {

    private static final long serialVersionUID = -9018162145868167055L;

    /**
     * 开始时间
     */
    Date beginTime;

    /**
     * 结束时间
     */
    Date endTime;

}

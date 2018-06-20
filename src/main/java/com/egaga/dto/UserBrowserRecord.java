package com.egaga.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangzhilin
 * @date 2018/6/5
 * @description
 */
@Data
public class UserBrowserRecord implements Serializable {

    private static final long serialVersionUID = -6486939588616464531L;

    /**
     * id
     */
    private Integer id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 请求来源
     */
    private RequestSource requestSource;

    /**
     * 机器编号
     */
    private String terminalCode;

    /**
     * 业务来源
     */
    private String bussinessSouce;

    /**
     * 二维码信息
     */
    private String qrCode;

    /**
     * 商品编号
     */
    private String goodsCode;

    /**
     * 访问时间
     */
    private Date browserDate;

}

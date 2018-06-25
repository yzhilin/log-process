package com.egaga.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangzhilin
 * @date 2018/6/21
 * @description
 */
@Data
public class Page implements Serializable {

    private static final long serialVersionUID = -2779478058798063938L;

    //默认一页的行数
    private static final int defaultSize=20;

    //每一页的大小
    private int pageSize;

    //当前页面
    private int currentPage;

}

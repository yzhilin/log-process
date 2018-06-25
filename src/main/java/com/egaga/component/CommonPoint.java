package com.egaga.component;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author yangzhilin
 * @date 2018/6/21
 * @description
 */
@Aspect
public class CommonPoint {

    @Pointcut("within(com.egaga.controller.*)")
    public void controller(){};

    @Pointcut("within(com.egaga.dao.*)")
    public void dao(){}




}

package com.egaga.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author yangzhilin
 * @date 2018/6/27
 * @description
 */
@Aspect
@Component
public class OperateComponent {

    @Before("execution(* com.egaga.aop.Operate.* (..))")
    public void processArgs(JoinPoint joinPoint){
        System.out.println(joinPoint.getArgs());
        System.out.println("dddddddddddddddd");
    }

   @Before("within(com.egaga.aop.*)")
    public void test(){
       System.out.println("ddddddddddddd");
   }

   @Before("target(com.egaga.aop.Operate)")
    public void test1(){

   }
}

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

    //@Before("execution(* com.egaga.aop.Operate.*(..))&&(args(a,..))")
    public void processArgs(String a){
//        System.out.println("===============");
        System.out.println("a"+a);
    }

    @Before(value = "execution(* com.egaga.aop.Operate.*(..))&&(args(..,c))",argNames = "c")
    public void test(String c){
       System.out.println("c"+c);
   }

   //@Before("target(com.egaga.aop.Operate)")
    public void test1(){

   }
}

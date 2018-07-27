package com.egaga.java.bigDecimal;

import com.sun.xml.internal.ws.spi.db.DatabindingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yangzhilin
 * @date 2018/7/16
 * @description
 */
@RunWith(SpringRunner.class)
public class TestBigDecimal {

        @Test
        public void test1(){
            BigDecimal bigDecimal = new BigDecimal(0.1);
            BigDecimal bigDecimal2 = new BigDecimal(0.2);
            BigDecimal add = bigDecimal.add(bigDecimal2);
            BigDecimal bigDecimal1 = add.setScale(1, BigDecimal.ROUND_HALF_UP);
            System.out.println(bigDecimal1);
        }

        @Test
        public void test2(){
            double a=0.1d;
            double b=0.2d;
            System.out.println(a+b);
        }

        @Test
        public void test3(){
            String a="1";
            String[] split = a.split(",");
            System.out.println(split.length);
            for (int i = 0; i <split.length ; i++) {
                System.out.println(split[i]);
            }
        }

        @Test
        public void test9(){
            Date date = new Date();
            System.out.println(date);
        }

        @Test
        public void test10(){
            Double d=0.0d;
            System.out.println("=-=-=-=-=-=-=-=");
            System.out.println(d.compareTo(0.0d));
        }


}

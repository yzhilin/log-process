package com.egaga.io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author yangzhilin
 * @date 2018/6/13
 * @description
 */
public class TestIO {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("userBrowserRecord.log");
        boolean b = fileInputStream.markSupported();
        System.out.println(b);
        byte[]bytes=new byte[100];
        int a=0;
        int i=0;
        while ((a=fileInputStream.read(bytes))!=-1){
            System.out.println(a);
            if(i==1){
                fileInputStream.mark(1000);
            }
            if(i==2){
                fileInputStream.reset();
            }
            String s = new String(bytes);
            System.out.println(s);
            i++;
        }
    }
}

package com.egaga.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author yangzhilin
 * @date 2018/6/13
 * @description
 */
public class TestReader {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("userBrowserRecord.log");
        char[]chars=new char[1000];
        int a=0;
        while ((a=fileReader.read(chars))!=-1){
            String string = new String(chars);
            System.out.println(string);
            System.out.println(a);
            System.out.println("=============------=-=-=-=-=----");
        }
    }
}

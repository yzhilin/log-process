package com.egaga;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yangzhilin
 * @date 2018/6/11
 * @description
 */
public class Test {
    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String buffer=null;
        while((buffer=bufferedReader.readLine())!=null){
            if(buffer.equals("exit")){
                System.exit(1);
            }
            System.out.println(buffer);
        }
    }

}

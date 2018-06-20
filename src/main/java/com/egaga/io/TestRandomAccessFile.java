package com.egaga.io;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author yangzhilin
 * @date 2018/6/13
 * @description
 */
public class TestRandomAccessFile {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("userBrowserRecord.log","r");
        int a=0;
        byte[]bytes=new byte[100];
        int i=0;
        while ((a=randomAccessFile.read(bytes,0,100))!=-1){
            String s = new String(bytes);
            System.out.println(s);
            long filePointer = randomAccessFile.getFilePointer();
            System.out.println("start"+filePointer);
            if(i==1){
                randomAccessFile.seek(2*a);
            }
            long filePointer1 = randomAccessFile.getFilePointer();
            System.out.println("end"+filePointer1);
            System.out.println("====================");
            i++;
        }





    }
}

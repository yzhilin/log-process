package com.egaga.task;

import com.egaga.dto.UserBrowserRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * @author yangzhilin
 * @date 2018/6/13
 * @description
 */
public class ReadLogTask implements Runnable{

    Logger logger=LogManager.getLogger(getClass());
    @Override
    public void run() {
        int bufSize=1000;
        File file = new File("userBrowserRecord.log");
        RandomAccessFile rw = null;
        try {
            rw = new RandomAccessFile(file, "r");
            FileChannel channel = rw.getChannel();
            //创建buffer用于读取
            ByteBuffer byteBuffer = ByteBuffer.allocate(bufSize);

            try {
                readFileByLine(bufSize, channel, byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //创建channel

    }

    private void readFileByLine(Integer bufSize,FileChannel fileChannel,ByteBuffer byteBuffer) throws IOException {
        String enterStr="\n";
        byte[] bytes = new byte[bufSize];
        StringBuffer stringBuffer = new StringBuffer("");
        String temp="";
        int a=0;
        while ((a=fileChannel.read(byteBuffer))!=-1){
            System.out.println(a+"-=-=-=-=-=-=-=-=-=-=-=");
            int position = byteBuffer.position();
            //去除buffer中有可能的空白
            byteBuffer.rewind();
            //取0到postion的字节给bytes数值
            byteBuffer.get(bytes);
            //恢复buffer,为下一次读取做准备
            byteBuffer.clear();
            String temp1=new String(bytes,0,position);
            temp=temp+temp1;
            int fromIndex=0;
            int endIndex;
            int index=0;
            while ((endIndex=temp.indexOf(enterStr,fromIndex))!=-1){
                index=endIndex;
                String line = temp.substring(fromIndex, endIndex);
                logger.info(line);
                line=stringBuffer.toString()+line;
                stringBuffer.delete(0,stringBuffer.length());
                fromIndex=endIndex+1;
            }
            //拼接最后没有匹配到的字符串
            temp=temp.substring(index+1,temp.length());
        }

    }



}

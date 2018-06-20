package com.egaga.task;

import com.egaga.dto.RequestSource;
import com.egaga.dto.UserBrowserRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author yangzhilin
 * @date 2018/6/12
 * @description
 */
public class TestFileNIO {
    private static Logger logger=LogManager.getLogger();

    public static void main(String[] args) throws IOException, ParseException {
        int bufSize=1000;
        File file = new File("userBrowserRecord.log");
        RandomAccessFile rw = new RandomAccessFile(file, "rw");
        //创建channel
        FileChannel channel = rw.getChannel();
        //创建buffer用于读取
        ByteBuffer byteBuffer = ByteBuffer.allocate(bufSize);

        List<UserBrowserRecord> userBrowserRecords = readFileByLine(bufSize, channel, byteBuffer);
        System.out.println(userBrowserRecords);
    }

    private static List<UserBrowserRecord> readFileByLine(Integer bufSize,FileChannel fileChannel,ByteBuffer byteBuffer) throws IOException, ParseException {
        String enterStr="\n";
        byte[] bytes = new byte[bufSize];
        StringBuffer stringBuffer = new StringBuffer("");
        String temp="";
        List<UserBrowserRecord> userBrowserRecords=new ArrayList<>();
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
                UserBrowserRecord userBrowserRecord = processLine(line);
                userBrowserRecords.add(userBrowserRecord);
                stringBuffer.delete(0,stringBuffer.length());
                fromIndex=endIndex+1;
            }
            //拼接最后没有匹配到的字符串
             temp=temp.substring(index+1,temp.length());
        }
        return userBrowserRecords;
    }
    //字符串转换成对象
    private static UserBrowserRecord processLine(String line) throws ParseException {
        if(StringUtils.isBlank(line)){
            logger.info("参数不合法");
        }
        UserBrowserRecord userBrowserRecord = new UserBrowserRecord();
        int startIndex1=0;
        int endIndex1=0;
        int startIndex2=0;
        int endIndex2=0;
        int i=1;
        while ((endIndex1=line.indexOf("=",startIndex1))!=-1&&(endIndex2=line.indexOf(",",startIndex2))!=-1){

             String temp=line.substring(endIndex1+1,endIndex2);
             startIndex1=endIndex1+1;
             startIndex2=endIndex2+1;
             switch (i){
                 case 1:userBrowserRecord.setUserId(temp);break;
                 case 2:userBrowserRecord.setRequestSource(RequestSource.valueOf(temp));break;
                 case 3:userBrowserRecord.setTerminalCode(temp);break;
                 case 4:userBrowserRecord.setBussinessSouce(temp);break;
                 case 5:userBrowserRecord.setQrCode(temp);break;
                 case 6:userBrowserRecord.setGoodsCode(temp);break;
                 case 7:String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern,Locale.US);
                        Date date = simpleDateFormat.parse(temp);
                        userBrowserRecord.setBrowserDate(date);
                        break;
                 default:break;
             }
             i++;
        }
        return userBrowserRecord;
    }


}

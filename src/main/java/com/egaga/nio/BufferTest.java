package com.egaga.nio;

import com.egaga.dto.RequestSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yangzhilin
 * @date 2018/6/11
 * @description
 */
public class BufferTest {
    private static Logger logger=LogManager.getLogger();

    public static void main(String[] args) throws IOException, ParseException {
//        CharBuffer buffer = CharBuffer.allocate(8);
//        System.out.println("capacity: "+buffer.capacity());
//        System.out.println("limit:"+buffer.limit());
//        System.out.println("position:"+buffer.position());
//        //放入元素
//        buffer.put('a');
//        buffer.put('b');
//        buffer.put('c');
//        System.out.println("加入三个元素后，position="+buffer.position());
//        //调用flip（）方法
//        buffer.flip();
//        System.out.println("flip方法后,positon"+buffer.position());
//        System.out.println("capacity: "+buffer.capacity());
//        System.out.println("limit:"+buffer.limit());
//        System.out.println("position:"+buffer.position());
//
//        //取出第一个元素
//        System.out.println("第一个元素（position=0）:"+buffer.get());
//        System.out.println("capacity: "+buffer.capacity());
//        System.out.println("limit:"+buffer.limit());
//        System.out.println("position:"+buffer.position());
//
//        //调用clear
//        buffer.clear();
//        System.out.println("执行clear后,limit="+buffer.limit());
//        System.out.println("capacity: "+buffer.capacity());
//        System.out.println("limit:"+buffer.limit());
//        System.out.println("position:"+buffer.position());
        File file=new File("browserInfo.log");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
        FileOutputStream fileOutputStream = new FileOutputStream("test.log");
        String line = randomAccessFile.readLine();


//        FileChannel channel1 = fileOutputStream.getChannel();
//        FileChannel channel = randomAccessFile.getChannel();
//        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, randomAccessFile.length());
//        long l = System.currentTimeMillis();
//        logger.info("-------------begin"+l);
//        int read = channel.read(mappedByteBuffer);
//        mappedByteBuffer.clear();
//        channel1.write(mappedByteBuffer);
//        logger.info("-------------end"+(System.currentTimeMillis()-l));

//        byte[]bytes={'a','c',0,0,0};
//        String s = new String(bytes, 0, 2);
//        System.out.println(s);
//        int length = s.length();
//        System.out.println(length);
//        System.out.println(s.substring(0,1));
        String s = RequestSource.ALIPAY.toString();
        System.out.println(s);
        String strA = "ALIPAY";
        RequestSource requestSource = RequestSource.valueOf(strA);
        System.out.println(requestSource);

        String date="Tue Jun 12 10:57:27 CST 2018";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date parse = simpleDateFormat.parse(date);
        System.out.println(parse);
    }
}

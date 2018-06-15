package com.egaga.component;

import com.egaga.dto.RequestSource;
import com.egaga.dto.UserBrowserRecord;
import com.egaga.service.UserBrowserRecordService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
 * @date 2018/6/13
 * @description
 */
@Component
public class LogProcessSchedule {
    private static final Logger logger = LogManager.getLogger();
    private static long lastReadIndex = 0;
    private static long lastAnalyseIndex = 0;
    public static final long ONE_MIN = 60 * 1000;

    @Autowired
    private UserBrowserRecordService userBrowserRecordService;

    /**
     * 读取并且保存日志文件
     *
     * @author yangzhilin
     * @date 2018/6/14 10:01
     */
//    @Scheduled(fixedDelay = ONE_MIN / 4)
    public void readLogProess() {
        int bufSize = 1000;
        File file = new File("userBrowserRecord.log");
        File outFile = new File("temp.log");
        RandomAccessFile inrw = null;
        RandomAccessFile outrw = null;
        FileChannel channelIn = null;
        FileChannel channelOut = null;
        try {
            inrw = new RandomAccessFile(file, "r");
            outrw = new RandomAccessFile(outFile, "rw");
            channelIn = inrw.getChannel();
            channelOut = outrw.getChannel();
            //创建buffer用于读取
            ByteBuffer byteBuffer = ByteBuffer.allocate(bufSize);
            readAndWriteFile(channelIn, channelOut, byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inrw.close();
                outrw.close();
                channelIn.close();
                channelOut.close();
            } catch (IOException e) {
                logger.info("channelIn close()关闭失败");
            }
        }
    }

    /**
     * 解析日志文件
     *
     * @author yangzhilin
     * @date 2018/6/14 10:02
     */
//    @Scheduled(fixedDelay = ONE_MIN / 4)
    public void analyseLog() {
        File file = new File("temp.log");
        RandomAccessFile inrw = null;
        FileChannel fileChannel = null;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
        try {
            inrw = new RandomAccessFile(file, "r");
            fileChannel = inrw.getChannel();
            String enterStr = "\n";
            byte[] bytes = null;
            StringBuffer stringBuffer = new StringBuffer("");
            String temp = "";
            List<UserBrowserRecord> userBrowserRecords = new ArrayList<>();
            fileChannel.position(lastAnalyseIndex);
            while ((fileChannel.read(byteBuffer)) != -1) {
                int position = byteBuffer.position();
                bytes = new byte[position];
                //去除buffer中有可能的空白
                byteBuffer.rewind();
                //取0到postion的字节给bytes数值
                byteBuffer.get(bytes);
                //恢复buffer,为下一次读取做准备
                byteBuffer.clear();
                String temp1 = new String(bytes, 0, position);
                temp = temp + temp1;
                int fromIndex = 0;
                int endIndex = 0;
                int index = 0;
                while ((endIndex = temp.indexOf(enterStr, fromIndex)) != -1) {
                    index = endIndex;
                    String line = temp.substring(fromIndex, endIndex);
                    line = stringBuffer.toString() + line;
                    logger.info("line--------" + line);
                    UserBrowserRecord userBrowserRecord = processLine(line);
                    if (userBrowserRecord != null) {
                        userBrowserRecords.add(userBrowserRecord);
                    }
                    stringBuffer.delete(0, stringBuffer.length());
                    fromIndex = endIndex + 1;
                }
                //拼接最后没有匹配到的字符串
                temp = temp.substring(index + 1, temp.length());
                System.out.println(userBrowserRecords.size());
                System.out.println("analyse=====" + userBrowserRecords);
                lastAnalyseIndex = fileChannel.position();
                //批量插入数据库

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            logger.info("日期转换异常");
            e.printStackTrace();
        } finally {
            try {
                inrw.close();
                fileChannel.close();
            } catch (IOException e) {
                logger.info("关闭channel异常");
                e.printStackTrace();
            }
        }
    }


    private void readAndWriteFile(FileChannel fileChannelin, FileChannel fileChannelout, ByteBuffer byteBuffer) throws IOException {
        fileChannelin.position(lastReadIndex);
        System.out.println(fileChannelin.size());
        while (fileChannelin.read(byteBuffer) != -1) {
            System.out.println();
            //去除buffer中有可能的空白
            byteBuffer.flip();
            System.out.println(byteBuffer.toString());
            fileChannelout.write(byteBuffer, fileChannelout.size());
            System.out.println("fileChannelout.size()输出文件大小" + fileChannelout.size());
            //恢复buffer,为下一次读取做准备
            byteBuffer.clear();
        }
        lastReadIndex = fileChannelin.position();
    }


    /**
     * 逐行解析字符串转换成对象
     *
     * @author yangzhilin
     * @date 2018/6/14 10:09
     */
    private UserBrowserRecord processLine(String line) throws ParseException {
        if (StringUtils.isBlank(line)) {
            logger.info("参数不合法");
            return null;
        }
        UserBrowserRecord userBrowserRecord = new UserBrowserRecord();
        int startIndex1 = 0;
        int endIndex1 = 0;
        int startIndex2 = 0;
        int endIndex2 = 0;
        int i = 1;
        while ((endIndex1 = line.indexOf("=", startIndex1)) != -1 && (endIndex2 = line.indexOf(",", startIndex2)) != -1) {

            String temp = line.substring(endIndex1 + 1, endIndex2);
            startIndex1 = endIndex1 + 1;
            startIndex2 = endIndex2 + 1;
            switch (i) {
                case 1:
                    userBrowserRecord.setUserId(temp);
                    break;
                case 2:
                    userBrowserRecord.setRequestSource(RequestSource.valueOf(temp));
                    break;
                case 3:
                    userBrowserRecord.setTerminalCode(temp);
                    break;
                case 4:
                    userBrowserRecord.setBussinessSouce(temp);
                    break;
                case 5:
                    userBrowserRecord.setQrCode(temp);
                    break;
                case 6:
                    userBrowserRecord.setGoodsCode(temp);
                    break;
                case 7:
                    String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.US);
                    Date date = simpleDateFormat.parse(temp);
                    userBrowserRecord.setBrowserDate(date);
                    break;
                default:
                    break;
            }
            i++;
        }
        return userBrowserRecord;
    }

}

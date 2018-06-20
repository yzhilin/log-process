package date;

import com.egaga.dto.RequestSource;

import java.text.ParseException;

/**
 * @author yangzhilin
 * @date 2018/6/13
 * @description
 */
public class TestDate {
    public static void main(String[] args) throws ParseException {
         //date转换成String
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
//        String format = simpleDateFormat.format(new Date());
//        System.out.println(format);
//        Date parse1 = simpleDateFormat.parse(format);
//        System.out.println(parse1.toString());
//        System.out.println("=====================");
//        //date转化成时间
//        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat();
//        String format1 = simpleDateFormat1.format(new Date());
//        System.out.println(format1);
//        Date date = simpleDateFormat1.parse(format1);
//        System.out.println(date);
//        System.out.println("======================");
        RequestSource alipay = RequestSource.valueOf("ALIPAY");
        System.out.println(alipay);


    }
}

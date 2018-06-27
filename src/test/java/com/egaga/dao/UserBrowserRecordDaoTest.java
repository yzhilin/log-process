package com.egaga.dao;

import com.egaga.dto.Page;
import com.egaga.dto.RequestSource;
import com.egaga.dto.UserBrowserRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author yangzhilin
 * @date 2018/6/19
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Import(DataSourceConfiguration.class)
public class UserBrowserRecordDaoTest {

    @Autowired
    UserBrowserRecordDao userBrowserRecordDao;

    @Test
    public void insertUserBrowserRecordTest() {
        UserBrowserRecord userBrowserRecord = new UserBrowserRecord();
        userBrowserRecord.setGoodsCode("goodsCode");
        userBrowserRecord.setBrowserDate(new Date());
        userBrowserRecord.setBussinessSouce("hatch_selector");
        userBrowserRecord.setRequestSource(RequestSource.MICRO_MESSENGER);
        userBrowserRecord.setTerminalCode("77777");
        userBrowserRecord.setUserId("1234567");
        userBrowserRecord.setQrCode("7777777");
        System.out.println(userBrowserRecordDao);
        userBrowserRecordDao.insertUserBrowserRecord(userBrowserRecord);
        System.out.println("end===========");
    }

    @Test
    public void findAllUserBrowserRecordTest() {
        Page page = new Page();
        page.setPageSize(5);
        page.setCurrentPage(1);
        List<UserBrowserRecord> list = userBrowserRecordDao.findAllUserBrowserRecord(null);
        System.out.println("================");
        System.out.println(list);
    }

    @Test
    public void findUserBrowserRecordByTerminal() {
        Page page = new Page();
        String terminalCode = "termianlCode";
        List<String> strings = Collections.singletonList(terminalCode);
        List<UserBrowserRecord> userBrowserRecords = userBrowserRecordDao.findUserBrowserRecordByTerminal(strings, null);
        System.out.println("===================");
        System.out.println(userBrowserRecords);
    }

    @Test
    public void findUserBrowserRecordByMerchant() {
        Page page = new Page();
        String merchantCode="WZ000011";
        List<String> merchantCodes = Collections.singletonList(merchantCode);
        List<UserBrowserRecord> userBrowserRecordByMerchant = userBrowserRecordDao.findUserBrowserRecordByMerchant(merchantCodes, page);
        System.out.println("=======================len"+userBrowserRecordByMerchant.size());
        System.out.println(userBrowserRecordByMerchant);
    }

    @Test
    public void insertTest(){
        ArrayList<UserBrowserRecord> userBrowserRecords = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            UserBrowserRecord userBrowserRecord = new UserBrowserRecord();
            userBrowserRecord.setGoodsCode("goodsCode");
            userBrowserRecord.setBrowserDate(new Date());
            userBrowserRecord.setBussinessSouce("terminal");
            userBrowserRecord.setRequestSource(RequestSource.MICRO_MESSENGER);
            userBrowserRecord.setTerminalCode("88888888");
            userBrowserRecord.setUserId("77");
            userBrowserRecord.setQrCode("7777777");
            userBrowserRecords.add(userBrowserRecord);
        }
    }


}

package com.egaga.service;

import com.egaga.dao.UserBrowserRecordDao;
import com.egaga.dto.RequestSource;
import com.egaga.dto.UserBrowserRecord;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author yangzhilin
 * @date 2018/6/20
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBrowserRecordServiceTest {
    @Autowired
    private  UserBrowserRecordService userBrowserRecordService;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void addUserBrowserTest(){
        long l = System.currentTimeMillis();
        System.out.println("currentTime"+l);
        ArrayList<UserBrowserRecord> userBrowserRecords = new ArrayList<>();
        for (int i = 0; i <10000 ; i++) {
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
        System.out.println("================addUserBrowserTest");
        userBrowserRecordService.addUserBrowser(userBrowserRecords);
        System.out.println("endTime"+(System.currentTimeMillis()-l));
    }

    @Test
    public void batchInsertTest(){
        SqlSession session=sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        UserBrowserRecordDao mapper = session.getMapper(UserBrowserRecordDao.class);

        for (int i = 0; i <100 ; i++) {
            UserBrowserRecord userBrowserRecord = new UserBrowserRecord();
            userBrowserRecord.setGoodsCode("goodsCode");
            userBrowserRecord.setBrowserDate(new Date());
            userBrowserRecord.setBussinessSouce("terminal");
            userBrowserRecord.setRequestSource(RequestSource.MICRO_MESSENGER);
            userBrowserRecord.setTerminalCode("88888888");
            userBrowserRecord.setUserId("77");
            userBrowserRecord.setQrCode("7777777");
            mapper.insertUserBrowserRecord(userBrowserRecord);
        }
        session.commit();
        session.close();
    }

    @Test
    public void batchInsertTest1(){
        SqlSession session=sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);

        for (int i = 0; i <100 ; i++) {
            UserBrowserRecord userBrowserRecord = new UserBrowserRecord();
            userBrowserRecord.setGoodsCode("goodsCode");
            userBrowserRecord.setBrowserDate(new Date());
            userBrowserRecord.setBussinessSouce("terminal");
            userBrowserRecord.setRequestSource(RequestSource.MICRO_MESSENGER);
            userBrowserRecord.setTerminalCode("88888888");
            userBrowserRecord.setUserId("77");
            userBrowserRecord.setQrCode("7777777");
            session.insert("com.egaga.dao.UserBrowserRecordDao.insertUserBrowserRecord",userBrowserRecord);
        }
        session.commit();
        session.clearCache();
        session.close();
    }


    @Test
    public void queryAllUserBrowserRecordTest(){


    }

    @Test
    public void qureryOfTermianlTest(){

    }

    @Test
    public void queryByMerchantTest(){

    }

}

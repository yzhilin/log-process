package com.egaga.service.impl;

import com.egaga.dao.UserBrowserRecordDao;
import com.egaga.dto.UserBrowserRecord;
import com.egaga.service.UserBrowserRecordService;
import com.github.pagehelper.Page;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 浏览记录
 *
 * @author yangzhilin
 * @date 2018/6/14
 * @description
 */
@Service
public class UserBrowserRecordServiceImpl implements UserBrowserRecordService {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    private UserBrowserRecordDao userBrowserRecordDao;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    //SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);

    /**
     * 添加浏览记录
     *
     * @author yangzhilin
     * @date 2018/6/15 10:15
     */
    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = false)
    public int addUserBrowser(List<UserBrowserRecord> userBrowserRecords) {
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        int size=userBrowserRecords.size();
        int result = 1;
        //一次提交的数据行数
        int batchCount = 100;
        for (int i = 0; i <size ; i++) {
            if(size<=batchCount){
                result=session.insert("com.egaga.dao.UserBrowserRecordDao.insertUserBrowserRecord",userBrowserRecords.get(i));
            }else {
                result=session.insert("com.egaga.dao.UserBrowserRecordDao.insertUserBrowserRecord",userBrowserRecords.get(i));
                if((i+1)%batchCount==0){
                    session.commit();
                }
            }
        }
        session.commit();
        session.close();
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserBrowserRecord> queryAllUserBrowserRecord(Page page) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserBrowserRecord> qureryOfTermianl(String termianlCode, Page page) {
        if(termianlCode==null){
            logger.info("机器编号为空");
            return new ArrayList<>();
        }
        List<UserBrowserRecord> userBrowserRecords=userBrowserRecordDao.findUserBrowserRecordByTerminal(Collections.singletonList(termianlCode),page);
        return userBrowserRecords;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserBrowserRecord> queryByMerchant(String merchantCode, Page page) {
        if(merchantCode==null){
            logger.info("商户编号为空");
            return new ArrayList<>();
        }
        List<UserBrowserRecord> userBrowserRecords=userBrowserRecordDao.findUserBrowserRecordByMerchant(Collections.singletonList(merchantCode),page);
        return userBrowserRecords;
    }


}

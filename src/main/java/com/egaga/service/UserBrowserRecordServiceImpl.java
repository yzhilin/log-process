package com.egaga.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.egaga.dao.UserBrowserRecordDao;
import com.egaga.dto.UserBrowserRecord;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** 浏览记录
 * @author yangzhilin
 * @date 2018/6/14
 * @description
 */
@Service
public class UserBrowserRecordServiceImpl implements UserBrowserRecordService {
    private static Logger logger=LogManager.getLogger();

    @Autowired
    private UserBrowserRecordDao userBrowserRecordDao;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);

    /** 添加浏览记录
     * @author yangzhilin
     * @date 2018/6/15 10:15
     */
    public void addUserBrowser(List<UserBrowserRecord> userBrowserRecords){
        int result=1;
        //一次提交的数据行数
        int batchCount=100;

        int batchLastIndex=batchCount;

        for (int i = 0; i <userBrowserRecords.size() ; i++) {
            if(batchLastIndex>=userBrowserRecords.size()){

            }else {

            }


        }


    }
}

package com.egaga.repository;

import com.egaga.dao.UserBrowserRecordDao;
import com.egaga.dto.Page;
import com.egaga.dto.UserBrowserRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * @author yangzhilin
 * @date 2018/6/27
 * @description
 */
@Repository
public class UserBrowserRecordRepository {

    Logger logger=LogManager.getLogger(getClass());

    private static final String cacheName = "UserBrowserRecord";

    @Autowired
    private UserBrowserRecordDao userBrowserRecordDao;

    @Cacheable(value = cacheName, key = "#page.currentPage")
    public List<UserBrowserRecord> all(Page page) {
        return userBrowserRecordDao.findAllUserBrowserRecord(page);
    }

    @Cacheable(value = cacheName, key = "#terminalCodepage.currentPage+page.currentPage")
    public List<UserBrowserRecord> queryByterminalCode(String terminalCode, Page page) {
        return userBrowserRecordDao.findUserBrowserRecordByTerminal(Collections.singletonList(terminalCode), page);
    }

    @Cacheable(value = cacheName, key = "#qrCode+page.currentPage")
    public List<UserBrowserRecord> queryByQrCode(String qrCode,Page page) {
        logger.info("queryByQrcode{},{}",qrCode,page);
        return userBrowserRecordDao.findUserBrowserRecordByQrcode(Collections.singletonList(qrCode),page);
    }


}

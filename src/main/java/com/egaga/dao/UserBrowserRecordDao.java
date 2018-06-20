package com.egaga.dao;

import com.egaga.dto.UserBrowserRecord;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 用户浏览记录
 *
 * @author yangzhilin
 * @date 2018/6/14
 * @description
 */
public interface UserBrowserRecordDao {

    /**
     * 批量添加用户记录
     *
     * @author yangzhilin
     * @date 2018/6/14 17:48
     */
    void insertUserBrowserRecord(UserBrowserRecord userBrowserRecordList);

    /**
     * 查询所有的浏览记录
     *
     * @author yangzhilin
     * @date 2018/6/19 14:25
     */
    List<UserBrowserRecord> findAllUserBrowserRecord(Page page);

    /**
     * 查询当前机器的浏览记录
     *
     * @author yangzhilin
     * @date 2018/6/19 14:37
     */
    List<UserBrowserRecord> findUserBrowserRecordByTerminal(List<String> terminalCodes, Page page);

    /**
     * 查询商户下的浏览记录
     *
     * @author yangzhilin
     * @date 2018/6/19 14:39
     */
    List<UserBrowserRecord> findUserBrowserRecordByMerchant(List<String> merchantCodes, Page page);

    void insert(List<UserBrowserRecord> userBrowserRecords);
}

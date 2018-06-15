package com.egaga.dao;

import com.egaga.dto.UserBrowserRecord;

import java.util.List;

/**  用户浏览记录
 * @author yangzhilin
 * @date 2018/6/14
 * @description
 */
public interface UserBrowserRecordDao {

    /** 批量添加用户记录
     * @author yangzhilin
     * @date 2018/6/14 17:48
     * @param
     * @return
     */
    public void insertUserBrowserRecord(List<UserBrowserRecord> userBrowserRecordList);
}

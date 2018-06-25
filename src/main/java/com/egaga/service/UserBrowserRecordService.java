package com.egaga.service;

import com.egaga.dto.Page;
import com.egaga.dto.UserBrowserRecord;
import java.util.List;

/**
 * @author yangzhilin
 * @date 2018/6/14
 * @description
 */

public interface UserBrowserRecordService {

    /**
     * @author yangzhilin
     * @date 2018/6/19 15:24
     */
    int addUserBrowser(List<UserBrowserRecord> userBrowserRecords);

    /**  查询所有的浏览记录
     * @date 2018/6/19 15:19
     */
    List<UserBrowserRecord> queryAllUserBrowserRecord(Page page);

    /**  查询指定的机器的浏览记录
     * @author yangzhilin
     * @date 2018/6/19 15:20
     */
    List<UserBrowserRecord> qureryOfTermianl(String termianlCode,Page page);

    /**  查询指定商户的浏览记录
     * @author yangzhilin
     * @date 2018/6/19 15:22
     */
    List<UserBrowserRecord> queryByMerchant(String integer,Page page);

}

package com.egaga.controller;

import com.egaga.dto.UserBrowserRecord;
import com.egaga.service.UserBrowserRecordService;
import com.github.pagehelper.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 浏览记录控制器
 *
 * @author yangzhilin
 * @date 2018/6/19
 */
@Controller
@RequestMapping("/browserRecord")
public class UserBrowserRecordController {
    Logger logger=LogManager.getLogger();

    private final UserBrowserRecordService userBrowserRecordService;

    @Autowired
    public UserBrowserRecordController(UserBrowserRecordService userBrowserRecordService) {
        this.userBrowserRecordService = userBrowserRecordService;
    }



    @GetMapping("/all")
    @ResponseBody
    public List<UserBrowserRecord> showAllUserBrowserRecord(Page page) {
        if(page==null){
            logger.info("传入的查询参数有误");
            return new ArrayList<>();
        }
        List<UserBrowserRecord> userBrowserRecords = userBrowserRecordService.queryAllUserBrowserRecord(page);
        return userBrowserRecords;
    }

    @GetMapping("/query/terminal")
    @ResponseBody
    public List<UserBrowserRecord> showTermianlUserBrowserRecord(String terminalCode,Page page){
        if(terminalCode.isEmpty()||page==null){
            logger.info("传入的查询参数有误");
            return new ArrayList<>();
        }
        List<UserBrowserRecord> userBrowserRecords=userBrowserRecordService.qureryOfTermianl(terminalCode,page);
        return userBrowserRecords;
    }

    @GetMapping
    @ResponseBody
    public List<UserBrowserRecord> showMerchantUserBrowserRecord(String merchant,Page page){
        if(merchant.isEmpty()||page==null){
            logger.info("传入的查询参数有误");
            return new ArrayList<>();
        }
        List<UserBrowserRecord> userBrowserRecords=userBrowserRecordService.queryByMerchant(merchant,page);
        return userBrowserRecords;
    }


}

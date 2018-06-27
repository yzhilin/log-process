package com.egaga.controller;

import com.egaga.dto.Page;
import com.egaga.dto.UserBrowserRecord;
import com.egaga.dto.message.QueryMessage;
import com.egaga.repository.UserBrowserRecordRepository;
import com.egaga.service.UserBrowserRecordService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 浏览记录控制器
 *
 * @author yangzhilin
 * @date 2018/6/19
 */
@Controller
@RequestMapping("/browserRecord")
public class UserBrowserRecordController {

    Logger logger = LogManager.getLogger();

    private final UserBrowserRecordService userBrowserRecordService;

    private final UserBrowserRecordRepository userBrowserRecordRepository;

    @Autowired
    public UserBrowserRecordController(UserBrowserRecordService userBrowserRecordService, UserBrowserRecordRepository userBrowserRecordRepository) {
        this.userBrowserRecordService = userBrowserRecordService;
        this.userBrowserRecordRepository=userBrowserRecordRepository;
    }


    @PostMapping("/all")
    @ResponseBody
    public List<UserBrowserRecord> showAllUserBrowserRecord(@RequestBody QueryMessage<?> queryMessage) {
        Page page = queryMessage.getPage();
        if (page == null) {
            logger.info("传入的查询参数有误");
            return null;
        }
        List<UserBrowserRecord> userBrowserRecords = userBrowserRecordService.queryAllUserBrowserRecord(page);
        return userBrowserRecords;
    }

    @PostMapping("/query/terminal")
    @ResponseBody
    public List<UserBrowserRecord> showTermianlUserBrowserRecord(@RequestBody QueryMessage<String> queryMessage) {
        Page page = queryMessage.getPage();
        String terminalCode = queryMessage.getValue();
        if (terminalCode.isEmpty() || page == null) {
            logger.info("传入的查询参数有误");
            return new ArrayList<>();
        }
        List<UserBrowserRecord> userBrowserRecords = userBrowserRecordService.qureryOfTermianl(terminalCode, page);
        return userBrowserRecords;
    }

    @PostMapping("/query/merchant")
    @ResponseBody
    public List<UserBrowserRecord> showMerchantUserBrowserRecord(@RequestBody QueryMessage<String> queryMessage) {
        Page page = queryMessage.getPage();
        String merchant = queryMessage.getValue();
        if (merchant.isEmpty() || page == null) {
            logger.info("传入的查询参数有误");
            return new ArrayList<>();
        }
        List<UserBrowserRecord> userBrowserRecords = userBrowserRecordService.queryByMerchant(merchant, page);
        return userBrowserRecords;
    }

    @PostMapping("/query/qrCode")
    @ResponseBody
    public List<UserBrowserRecord> showQrCodeUserBrowserRecord(@RequestBody QueryMessage<String> queryMessage) {
        if (StringUtils.isEmpty(queryMessage.getValue())) {
            logger.info("输入的参数不合法");
            throw new RuntimeException("输入的参数不合法");
        }
        List<UserBrowserRecord> userBrowserRecords = userBrowserRecordRepository.queryByQrCode(queryMessage.getValue(), queryMessage.getPage());
        return userBrowserRecords;
    }


}

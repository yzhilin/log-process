package com.egaga.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author yangzhilin
 * @date 2018/6/21
 * @description
 */
@RunWith(SpringRunner.class)
//@WebMvcTest(UserBrowserRecordController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserBrowserRecordControllerTest {

    @Autowired
    WebApplicationContext context;


    MockMvc mockMvc;
    @Before
    public void before() {
        //可以对所有的controller来进行测试
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }




//    @Autowired
//    private MockMvc mockMvc;

    @Test
    public void showAllUserBrowserRecord() throws Exception {
        MvcResult mvcResult=this.mockMvc.perform(MockMvcRequestBuilders.get("/browserRecord/all").accept(MediaType.ALL)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        System.out.println("status"+status);
        String resultString = mvcResult.getResponse().getContentAsString();
        System.out.println(resultString);
    }

}

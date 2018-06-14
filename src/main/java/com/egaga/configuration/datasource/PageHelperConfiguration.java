package com.egaga.configuration.datasource;

import com.github.pagehelper.PageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangzhilin
 * @date 2018/6/14
 * @description
 */
@Configuration
@EnableConfigurationProperties(PageHelperProperties.class)
@ConditionalOnProperty(prefix = "mybatis.pagehelper",name = "enabled",matchIfMissing = true)
public class PageHelperConfiguration {

    @Autowired
    private PageHelperProperties pageHelperProperties;

    @Bean
    public PageInterceptor mybatisPageHelper() throws IllegalAccessException {
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(pageHelperProperties.toProperties());
        return pageInterceptor;
    }


}

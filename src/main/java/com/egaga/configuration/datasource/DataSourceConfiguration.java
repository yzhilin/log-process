package com.egaga.configuration.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * @author yangzhilin
 * @date 2018/6/14
 * @description
 */
@Configuration
@EnableConfigurationProperties(DruidDataSourceProperties.class)
@MapperScan(basePackages = {"com.egaga.dao"})
@EnableTransactionManagement
public class DataSourceConfiguration {

    private static Logger logger = LogManager.getLogger();

    @Autowired
    private DruidDataSourceProperties druidDataSourceProperties;

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() throws Exception {
        Properties druidProperties = druidDataSourceProperties.toProperties();
//        String connectionProperties = druidProperties.getProperty("connectionProperties");
//        if (StringUtils.isBlank(connectionProperties)) {
//            logger.info("使用内部*数据库配置");
//            druidProperties.setProperty("connectionProperties",
//                    "config.decrypt=true;config.decrypt.key=MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKhmQNudjXyPpcPnyV+xG9ivdIyR+ijwcgO1mo5NjNVY1EvzhH95bTjH6ZEMsuPtZm6jjDmMzfRuWUWE3hUSqvcCAwEAAQ==");
//        } else {
//            logger.info("使用外部*数据库配置");
//        }

        logger.debug("检查：" + druidDataSourceProperties.getMaxWait());
        if (druidDataSourceProperties.getMaxWait() < 0) {
            Object maxWait = druidProperties.remove("maxWait");
            logger.debug("无效maxWait:" + maxWait);
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        DruidDataSourceFactory.config(druidDataSource, druidProperties);

        return druidDataSource;

    }

    @Autowired(required = false)
    private PageInterceptor mybatisPageInterceptor = null;

    @Bean
    public SqlSessionFactory sqlSessionFactory(ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        Resource resource = applicationContext.getResource("classpath:com/egaga/dao/mapper/UserBrowserRecordMapper.xml");
        sqlSessionFactory.setMapperLocations(new Resource[]{resource});
        // 添加分页插件PageHelper
        if (mybatisPageInterceptor != null) {
            Interceptor[] interceptors = new Interceptor[]{mybatisPageInterceptor};
            sqlSessionFactory.setPlugins(interceptors);
        }

        return sqlSessionFactory.getObject();
    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(ApplicationContext applicationContext) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory(applicationContext));
    }


}

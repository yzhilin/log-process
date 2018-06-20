package com.egaga.configuration.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author yangzhilin
 * @date 2018/6/14
 * @description
 */
@Data
@ConfigurationProperties(prefix = DruidDataSourceProperties.PREFIX)
public class DruidDataSourceProperties {

    public static final String PREFIX = "druid.datasource";

    /**
     * 数据库连接基本属性 urlDemo:
     * jdbc:mysql://127.0.0.1:3306/demo?characterEncoding=utf8
     */
    // @NotNull
    // private String driverClassName = "com.mysql.jdbc.Driver";

    @NotNull
    private String url;

    @NotNull
    private String username;

    @NotNull
    private String password;

    /**
     * 配置初始化大小、最小、最大
     */

    @Min(0)
    int initialSize = 1;

    @Min(0)
    private int minIdle = 1;

    @Min(1)
    private int maxActive = 20;

    /**
     * 配置获取连接等待超时的时间
     */
    private int maxWait = -1;

    /**
     * 超过时间限制是否回收
     */
    private boolean removeAbandoned = true;

    /**
     * 超过时间限制多长
     */
    private int removeAbandonedTimeout = 180;

    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    private int timeBetweenEvictionRunsMillis = 60000;

    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    private int minEvictableIdleTimeMillis = 300000;

    /**
     * 验证连接是否有效
     */
    private String validationQuery = "SELECT 'x'";

    /**
     * 定时对线程池中的链接进行有效性校验
     */
    private boolean testWhileIdle = true;

    /**
     * 对拿到的connection进行有效性校验
     */
    private boolean testOnBorrow = false;

    /**
     * 对返回的connection进行有效性校验
     */
    private boolean testOnReturn = false;
    /**
     * 打开PSCache，并且指定每个连接上PSCache的大小
     */
    private boolean poolPreparedStatements = true;

    private int maxPoolPreparedStatementPerConnectionSize = 20;

    private String connectionProperties = "";

    // private boolean logViolation = true;
    //
    // private boolean multiStatementAllow = true;

    /**
     * 配置监控统计拦截的filters
     */
    private String filters = "stat,log4j";

    public Properties toProperties() throws Exception {
        Class<? extends DruidDataSourceProperties> c = this.getClass();
        Field[] fields = c.getDeclaredFields();
        Properties properties = new Properties();
        for (Field field : fields) {
            if ((field.getModifiers() & java.lang.reflect.Modifier.STATIC) != java.lang.reflect.Modifier.STATIC) {
                properties.setProperty(field.getName(), field.get(this).toString());
            }
        }
        return properties;
    }

}

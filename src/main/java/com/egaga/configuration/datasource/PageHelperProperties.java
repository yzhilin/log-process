package com.egaga.configuration.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author yangzhilin
 * @date 2018/6/14
 * @description
 */
@Data
@ConfigurationProperties(prefix = PageHelperProperties.PREFIX)
public class PageHelperProperties {

    public static final String PREFIX="mybatis.pagehelper";

    public static final String DIALECT_MYSQL="mysql";

    private String helperDialect=PageHelperProperties.DIALECT_MYSQL;

    private boolean offsetAsPageNum=true;

    private boolean rowBoundsWithCount=true;

    private boolean pageSizeZero=true;

    private boolean reasonable=true;

    private String params="";

    public Properties toProperties() throws IllegalAccessException {
        Class<? extends PageHelperProperties> aClass = this.getClass();
        Field[] fields = getClass().getDeclaredFields();
        Properties properties = new Properties();
        for (Field field:fields) {
            if ((field.getModifiers() & java.lang.reflect.Modifier.STATIC) != java.lang.reflect.Modifier.STATIC) {
                properties.setProperty(field.getName(), field.get(this).toString());
            }
        }
        return properties;
    }





}

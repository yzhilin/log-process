package com.egaga.component;

import com.egaga.dto.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/** 分页插件组件
 * @author yangzhilin
 * @date 2018/6/21
 */
@Component
@Aspect
public class PageHelperComponet {


    //数据库分页
    @Before("execution(* com.egaga.dao.*.*(..))&&args(..,page)")
    public void pageHelper(Page page){
        if(page!=null){
            com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        }
    }


    //返回的数据分页
    @AfterReturning(pointcut = "com.egaga.component.CommonPoint.controller()&&args(..,page)")
    public void pageInfoProcess(Page page){
        PageInfo<Object> objectPageInfo = new PageInfo<>((List<Object>) page);
    }



}

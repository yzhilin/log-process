package com.egaga.advice;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

/**
 * @author yangzhilin
 * @date 2018/6/21
 * @description
 */
@ControllerAdvice
public class PageHelperAdvice<T> implements ResponseBodyAdvice {


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Object object=body;
        if(body instanceof Page){
            object=new PageInfo<>((List<Object>)body);
        }
        return object;
    }
}

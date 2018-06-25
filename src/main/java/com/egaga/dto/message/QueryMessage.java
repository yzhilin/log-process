package com.egaga.dto.message;

import com.egaga.dto.Page;
import lombok.Data;

import java.io.Serializable;

/** 封装请求实体
 * @author yangzhilin
 * @date 2018/6/21
 * @description
 */
@Data
public class QueryMessage<T> extends Message<T>implements Serializable {

    private static final long serialVersionUID = -9042472388038138554L;

    private Page page;
}

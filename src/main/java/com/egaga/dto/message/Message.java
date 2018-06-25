package com.egaga.dto.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangzhilin
 * @date 2018/6/21
 * @description
 */
@Data
public class Message<T> implements Serializable {
    T value;
}

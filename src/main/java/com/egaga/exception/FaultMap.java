package com.egaga.exception;

import com.roc.exception.Fault;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangzhilin
 * @date 2018/6/28
 * @description
 */
public class FaultMap {

    private static final Map<String, Fault> FAULT_MAP = new HashMap<>();

    public static Fault fault(String code) {
        Fault fault = FAULT_MAP.get(code);
        // 2015年11月2日 下午6:31:15
        // 增加错误码不存在时的处理
        if (fault != null) {
            return fault;
        } else {
            return FAULT_MAP.get("LP9999");
        }
    }

    public static Fault fault() {
        return FAULT_MAP.get("LP9999");
    }

    static {
        put(new Fault("LP7001", "参数不完整"));
        put(new Fault("LP7002", "系统处理异常"));
    }

    private static void put(Fault fault) {
        FAULT_MAP.put(fault.getCode(), fault);
    }

}

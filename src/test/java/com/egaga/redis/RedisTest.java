package com.egaga.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangzhilin
 * @date 2018/6/28
 * @description
 */
@RunWith(SpringRunner.class)
@DataRedisTest
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test1() {
        ZSetOperations valueOperations = redisTemplate.opsForZSet();
        Set set = new HashSet();
        set.add(1);
        set.add(2);
        valueOperations.add("yzl", set, 1);

        Set yzl = valueOperations.range("yzl", 0, 10);
        System.out.println(yzl);
    }

    @Test
    public void test2() {
        HashOperations hashOperations=redisTemplate.opsForHash();
        Set keys = hashOperations.keys("TS:CC:866855039437926");
        Boolean lightStatus = hashOperations.hasKey("TS:CC:866855039437926", "lightStatus");
        System.out.println(keys);
    }


}

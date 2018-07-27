package com.egaga.redis;

import com.roc.terminal.api.HealthInfo;
import com.roc.terminal.chiccheck.health.redis.RedisTerminalHealthOperator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

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

    @Autowired
    StringRedisTemplate stringRedisTemplate;

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
        StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();
        byte[] serialize = stringRedisSerializer.serialize("TS:CC:866855039437926");
        Long size = hashOperations.size(serialize);
        RedisOperations operations = hashOperations.getOperations();
        RedisSerializer keySerializer = operations.getKeySerializer();
        RedisSerializer valueSerializer = operations.getValueSerializer();

        System.out.println("size"+size);


    }

    @Test
    public void test(){
//        HashOperations hashOperations = redisTemplate.opsForHash();
//        Set keys = hashOperations.keys("TS:CC:866855039437926");
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("yzl","YZlin");

        Object yzl = valueOperations.get("yzl");
        System.out.println(yzl);


    }

    @Test
    public void dd(){
//        HashOperations<String, Object, Object> stringObjectObjectHashOperations = stringRedisTemplate.opsForHash();
//        Long size = stringObjectObjectHashOperations.size("TS:CC:866855039437926");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
    //    redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());


//        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        HashMap<Object, Object> hashMap = new HashMap<>();
//        UserBrowserRecord userBrowserRecord = new UserBrowserRecord();
//        hashMap.put("yzl","cool");
//        hashMap.put("user",userBrowserRecord);
//        hashOperations.putAll("yzl-test",hashMap);

        HashMap entries1 = (HashMap)redisTemplate.opsForHash().entries("TS:CC:866855039645072");
//        Map entries = hashOperations.entries("yzl-test");
     //   HashOperations hashOperations = redisTemplate.opsForHash();

        Jackson2HashMapper jackson2HashMapper=new Jackson2HashMapper(true);
      //  Object temperatureStatus = hashOperations.get("TS:CC:866855039645072", "temperatureStatus");

        System.out.println(entries1);


    }


    @Test
    public void ddd() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        RedisTerminalHealthOperator redisTerminalHealthOperator=new RedisTerminalHealthOperator(redisTemplate);
//        HealthInfo healthInfo3= redisTerminalHealthOperator.loadHealthInfo("866855039542758");

        HealthInfo healthInfo = new HealthInfo();

        BitSet bitSet = new BitSet(1);
        bitSet.set(1);
        bitSet.set(2);
        healthInfo.setMac("d8b04cf29580");
        healthInfo.setTemperatureStatus(bitSet);
        healthInfo.setLightStatus(bitSet);
        healthInfo.setCurrentTimestamp(1523510732000L);


        Class<? extends RedisTerminalHealthOperator> aClass = redisTerminalHealthOperator.getClass();
        Method update = aClass.getDeclaredMethod("update", HealthInfo.class);
        update.setAccessible(true);
        update.invoke(redisTerminalHealthOperator,healthInfo);
        HealthInfo healthInfo1 = redisTerminalHealthOperator.loadHealthInfo("d8b04cf29580");

        System.out.println(healthInfo);

    }


    @Test
    public void testBitSet(){
        BitSet bitSet = new BitSet(2);
        bitSet.set(1);
        bitSet.set(2);
        System.out.println(bitSet.cardinality());
        System.out.println(bitSet);
    }


    @Test
    public void test7(){
        Integer a=null;
        System.out.println(a==0?1:2);
    }

    @Test
    public void test8(){
        Map entries = redisTemplate.opsForHash().entries("TS:CC:3c3300f59daf");
        System.out.println(entries);
    }

}

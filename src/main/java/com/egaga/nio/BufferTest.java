package com.egaga.nio;

import java.nio.CharBuffer;

/**
 * @author yangzhilin
 * @date 2018/6/11
 * @description
 */
public class BufferTest {
    public static void main(String[] args) {
        CharBuffer allocate = CharBuffer.allocate(8);
        System.out.println("capacity: "+allocate.capacity());
        System.out.println("limit:"+allocate.limit());
        System.out.println("position:"+allocate.position());
        //;


    }
}

package com.egaga.nio;

import java.nio.CharBuffer;

/**
 * @author yangzhilin
 * @date 2018/6/11
 * @description
 */
public class BufferTest {
    public static void main(String[] args) {
        CharBuffer buffer = CharBuffer.allocate(8);
        System.out.println("capacity: "+buffer.capacity());
        System.out.println("limit:"+buffer.limit());
        System.out.println("position:"+buffer.position());
        //放入元素
        buffer.put('a');
        buffer.put('b');
        buffer.put('c');
        System.out.println("加入三个元素后，position="+buffer.position());
        //调用flip（）方法
        buffer.flip();
        System.out.println("flip方法后,positon"+buffer.position());
        System.out.println("capacity: "+buffer.capacity());
        System.out.println("limit:"+buffer.limit());
        System.out.println("position:"+buffer.position());

        //取出第一个元素
        System.out.println("第一个元素（position=0）:"+buffer.get());
        System.out.println("capacity: "+buffer.capacity());
        System.out.println("limit:"+buffer.limit());
        System.out.println("position:"+buffer.position());

        //调用clear
        buffer.clear();
        System.out.println("执行clear后,limit="+buffer.limit());
        System.out.println("capacity: "+buffer.capacity());
        System.out.println("limit:"+buffer.limit());
        System.out.println("position:"+buffer.position());
    }
}

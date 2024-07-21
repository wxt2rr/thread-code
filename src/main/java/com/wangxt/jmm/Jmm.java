package com.wangxt.jmm;

public class Jmm {
    /**
     * JMM java内存模型，为了解决java多线程环境下对内存中共享资源并发读写的问题（指令重排序问题、可见性问题，原子性问题）
     *
     * 从计算机组成上来说，使用多级缓存提高了cpu从主存中读写数据的效率，但是也带来了一致性的问题，当然从硬件上已经解决了缓存一致性的问题
     * 从操作系统上来说，操作系统提供了一些原语解决多线程的问题，但是这些原语比较底层，使用起来很不方便，需要对其非常了解
     * 从跨平台上来说，java被设计成跨平台的语言，如果不对内存模型进行抽象的话，那开发人员还需要关注不同平台的兼容性问题
     * 从编译上来说，编译器会对指令进行重排序
     */
    public static void main(String[] args) {

    }
}
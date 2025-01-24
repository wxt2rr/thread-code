package com.wangxt.thread.volatilex;

public class Demo {
    public static boolean stop = false;

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            int i=0;
            while (!stop){
                i++;
            }
        }, "aaa").start();
        System.out.println("begin");
        Thread.sleep(1000);
        stop=true;
    }
}

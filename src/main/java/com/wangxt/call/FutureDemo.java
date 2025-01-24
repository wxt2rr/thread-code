package com.wangxt.call;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureDemo {
    public static void main(String[] args) throws Exception{
        System.out.println("main start");
        FutureTask f = new FutureTask(() -> {
            return Thread.currentThread().getName() + " aaa";
        });

        new Thread(f, "aa").start();

        System.out.println(f.get());
        System.out.println("main end");
    }
}

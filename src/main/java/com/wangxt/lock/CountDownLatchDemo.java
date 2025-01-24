package com.wangxt.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for(int i=0;i< 11;i++){
            int finalI = i;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                countDownLatch.countDown();
                System.out.println(finalI);
            }).start();
        }

        System.out.println("await");
        countDownLatch.await();
        System.out.println("over");
    }
}

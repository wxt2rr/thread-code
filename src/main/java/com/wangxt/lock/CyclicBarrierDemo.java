package com.wangxt.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) throws Exception {
        int count = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "到了");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + "await 完了");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }, "a" + i).start();
        }

        System.out.println("main");
    }
}

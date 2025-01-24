package com.wangxt.thread.thread;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupted {

    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("a run" + Thread.currentThread().isInterrupted());
                try {
                    Thread.sleep(1000 * 1000);
                } catch (InterruptedException e) {
                    // 底层是维护了一个volatile类型的int变量
                    // sleep或者wait状态的线程，在响应中断之前，首先要先被唤醒
                    // 在抛出 InterruptedException 之前会将 flag复位，这样做的目的是将操作权交给run方法自己判断
                    // 处理方式有多种：可以捕获异常；可以将异常抛出；可以通过Thread.currentThread().interrupt()设置flag为已中断
                    System.out.println("a 被中断了, sleep响应了中断");
                    System.out.println(Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().isInterrupted());
                }

                // 由run方法自己处理中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("run 知道自己被中断了，直接返回" + Thread.currentThread().isInterrupted());
                    return;
                }
                System.out.println("a 还在执行" + Thread.currentThread().isInterrupted());
            }
        });

        Thread a1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // run方法没有响应中断，所以a1不会被b线程中断
                System.out.println("a1 run");
                for (; ; ) {
                }
            }
        });


        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("b run");

                if (!a.isInterrupted()) {
                    a.interrupt();
                    System.out.println("b 想要 中断  a");
                }

                if (!a1.isInterrupted()) {
                    a1.interrupt();
                    System.out.println("b 想要 中断  a1");
                }
            }
        });

        a.start();
        a1.start();
        b.start();
    }
}

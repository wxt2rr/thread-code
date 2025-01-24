package com.wangxt.thread;

/**
 * @author wangxt
 * @date 2025/1/18 14:15
 * @desc
 */
public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
        objectThreadLocal.set("");

        System.out.println(Thread.currentThread().getId());
        SubThread subThread = new SubThread();
        subThread.start();

        Thread.sleep(1000);
        // 我想要中断这个线程
        subThread.interrupt();
    }
}


class SubThread extends Thread {

    @Override
    public synchronized void start() {
        long id = Thread.currentThread().getId();
        System.out.println("aaaa" + id);
    }

    @Override
    public void run() {
        boolean interrupted = this.isInterrupted();
        System.out.println("开始执行，" + interrupted);
        try {
            Thread.sleep(1000 * 1000);
        } catch (InterruptedException e) {
            interrupted = this.isInterrupted();
            System.out.println("超时" + interrupted);
            // 抛出异常，通知到调用方
            //throw new RuntimeException(e);

            this.interrupt();
        }

        interrupted = this.isInterrupted();
        System.out.println("执行结束" + interrupted);
    }
}

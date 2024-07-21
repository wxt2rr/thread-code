package com.wangxt.thread.thread;

public class ThreadDaemon {
    /**
     * 主线程结束，守护线程不管是否执行完，自动结束
     */
    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("a run over");
            }
        },"t-a");

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("b run");
                try {
                    Thread.sleep(1000 * 10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally { // finally不能保证守护线程一定执行
                    System.out.println("b run over");
                }
            }
        }, "t-b");

        b.setDaemon(true);
        a.start();
        b.start();
    }
}

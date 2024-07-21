package com.wangxt.thread.thread;

import java.util.concurrent.TimeUnit;

public class ThreadJoin {
    public static void main(String[] args) {
        /**
         * Waits at most {@code millis} milliseconds for this thread to
         * die. A timeout of {@code 0} means to wait forever.
         *
         * <p> This implementation uses a loop of {@code this.wait} calls
         * conditioned on {@code this.isAlive}. As a thread terminates the
         * {@code this.notifyAll} method is invoked. It is recommended that
         * applications not use {@code wait}, {@code notify}, or
         * {@code notifyAll} on {@code Thread} instances.
         *
         * @param  millis
         *         the time to wait in milliseconds
         *
         * @throws  IllegalArgumentException
         *          if the value of {@code millis} is negative
         *
         * @throws  InterruptedException
         *          if any thread has interrupted the current thread. The
         *          <i>interrupted status</i> of the current thread is
         *          cleared when this exception is thrown.
         *
         *  public final synchronized void join(final long millis)
         *     throws InterruptedException {
         *             if (millis > 0) {
         *                 if (isAlive()) {
         *                     final long startTime = System.nanoTime();
         *                     long delay = millis;
         *                     do {
         *                         wait(delay);
         *                     } while (isAlive() && (delay = millis -
         *                             TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime)) > 0);
         *                 }
         *             } else if (millis == 0) {
         *                 while (isAlive()) {
         *                     wait(0);
         *                 }
         *             } else {
         *                 throw new IllegalArgumentException("timeout value is negative");
         *             }
         *         }
         *
         *   throws InterruptedException 响应中断
         *   底层调用wait方法，相当于this.wait()，所以释放锁释放的当前对象的锁，其它对象的锁不释放
         *   sync(obj){ //释放锁
         *       obj.join();
         *   }
         *
         *   sync(obj){// 不释放锁
         *       thread.join()
         *   }
         *
         *   释放cpu
         */
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("a run");
                try {
                    Thread.sleep(1000*5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t-a");


        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName());
                    a.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("b sync run");
            }
        }, "t-b");

        a.start();
        b.start();


        System.out.println("main over");
    }
}

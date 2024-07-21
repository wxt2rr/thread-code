package com.wangxt.thread.thread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ObjectWaitNotify {
    /**
     * Causes the current thread to wait until it is awakened, typically
     * by being <em>notified</em> or <em>interrupted</em>, or until a
     * certain amount of real time has elapsed.
     * <p>
     * In all respects, this method behaves as if {@code wait(timeoutMillis, 0)}
     * had been called. See the specification of the {@link #wait(long, int)} method
     * for details.
     *
     * @param  timeoutMillis the maximum time to wait, in milliseconds
     * @throws IllegalArgumentException if {@code timeoutMillis} is negative
     * @throws IllegalMonitorStateException if the current thread is not
     *         the owner of the object's monitor
     * @throws InterruptedException if any thread interrupted the current thread before or
     *         while the current thread was waiting. The <em>interrupted status</em> of the
     *         current thread is cleared when this exception is thrown.
     * @see    #notify()
     * @see    #notifyAll()
     * @see    #wait()
     * @see    #wait(long, int)
     *
     * public final native void wait(long timeoutMillis) throws InterruptedException;
     *
     *  释放锁，响应中断，释放cpu，因为wait方法是实例方法，所以只有实例化的对象可以创建，这时就可以对对象锁进行处理，所以会释放锁
     *
     *  sleep就是睡一下，但是资源还被你持有；wait就是你等一会，叫别人先干，所以要把资源给到别人
     */

    public static void main(String[] args){
        Object obj = new Object();

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (obj){
                        // 等待 1000s
                        System.out.println("a wait");
                        obj.wait(0);
                    }
                    System.out.println("a 被 notify");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    try {
                        Thread.sleep(1000 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    obj.notify();
                    System.out.println("b 执行 notify");
                }
            }
        });

        BlockingDeque<Thread> queue = new LinkedBlockingDeque<>();
        queue.add(a);
        queue.add(b);

        try {
            Thread thread = queue.takeFirst();
            thread.start();
            Thread thread1 = queue.takeLast();
            thread1.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

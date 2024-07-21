package com.wangxt.thread.thread;

public class ThreadSleep {
    /**
     * Causes the currently executing thread to sleep (temporarily cease
     * execution) for the specified number of milliseconds, subject to
     * the precision and accuracy of system timers and schedulers. The thread
     * does not lose ownership of any monitors.
     *
     * @param  millis
     *         the length of time to sleep in milliseconds
     *
     * @throws  IllegalArgumentException
     *          if the value of {@code millis} is negative
     *
     * @throws  InterruptedException
     *          if any thread has interrupted the current thread. The
     *          <i>interrupted status</i> of the current thread is
     *          cleared when this exception is thrown.
     *
     *  public static native void sleep(long millis) throws InterruptedException;
     *
     *  The thread does not lose ownership of any monitors.   不会释放锁
     *  throws InterruptedException;   响应中断
     *  会释放cpu执行权
     *
     *  sleep方法只是一个静态方法，无法控制锁是否释放的逻辑，因为sync是对象锁
     */
    public static void main(String[] args) {
        Object obj = new Object();

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    System.out.println("a run");
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    // b会一直阻塞，表示线程a未释放 obj 锁
                    System.out.println("b run");
                }
            }
        });

        a.start();
        b.start();
    }
}

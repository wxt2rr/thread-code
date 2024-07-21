package com.wangxt.thread.thread;

public class ThreadWithThread {
    /**
     * 线程之间的通信方式
     * 1.volitate、synchronize、lock
     * 2.wait、notify、await、signal
     * 3.管道输入输出流
     * 4.join() 隐式唤醒
     * 5.ThreadLocal 父子线程通信
     * 6.线程中断
     *
     * 1. 共享内存（Synchronized blocks/methods）
     * 这是最基本的线程间通信方式。通过使用synchronized关键字来修饰方法或代码块，你可以确保同一时刻只有一个线程可以访问被同步的代码区域。这实际上是一种隐式的信号量机制，允许线程在访问共享资源时进行互斥。
     *
     * Java
     * 深色版本
     * public class Counter {
     *     private int count = 0;
     *
     *     public synchronized void increment() {
     *         count++;
     *     }
     *
     *     public synchronized int getCount() {
     *         return count;
     *     }
     * }
     * 2. wait() 和 notify()/notifyAll()
     * 这些方法是Object类的一部分，用于线程间的同步和通信。一个线程可以调用对象的wait()方法使自己等待，直到另一个线程调用该对象的notify()或notifyAll()方法唤醒它。
     *
     * Java
     * 深色版本
     * public class WaitNotify {
     *     private boolean ready = false;
     *     private Object monitor = new Object();
     *
     *     public void waitMethod() {
     *         synchronized (monitor) {
     *             System.out.println("Waiting started.");
     *             try {
     *                 monitor.wait();
     *             } catch (InterruptedException e) {
     *                 Thread.currentThread().interrupt();
     *             }
     *             System.out.println("Waiting ended.");
     *         }
     *     }
     *
     *     public void notifyMethod() {
     *         synchronized (monitor) {
     *             System.out.println("Notifying.");
     *             monitor.notify();
     *         }
     *     }
     * }
     * 3. volatile 变量
     * volatile关键字用于标记一个变量，使得所有线程都能看到对该变量的最新修改。这可以用于线程间的简单通信，但不能保证原子性操作。
     *
     * Java
     * 深色版本
     * public class VolatileExample {
     *     private volatile boolean flag = false;
     *
     *     public void writer() {
     *         flag = true;
     *     }
     *
     *     public void reader() {
     *         while (!flag) {
     *             // 等待flag变为true
     *         }
     *         System.out.println("Flag is true.");
     *     }
     * }
     * 4. AtomicVariables
     * java.util.concurrent.atomic包提供了原子变量类，如AtomicInteger，这些类提供线程安全的更新操作，可以用于线程间的简单通信和数据交换。
     *
     * Java
     * 深色版本
     * import java.util.concurrent.atomic.AtomicInteger;
     *
     * public class AtomicIntegerExample {
     *     private AtomicInteger counter = new AtomicInteger(0);
     *
     *     public void increment() {
     *         counter.incrementAndGet();
     *     }
     *
     *     public int getCounter() {
     *         return counter.get();
     *     }
     * }
     * 5. Concurrent Collections
     * java.util.concurrent包中的并发集合类，如ConcurrentHashMap和CopyOnWriteArrayList，提供了线程安全的数据结构，可以用于线程间的数据交换。
     *
     * 6. Future and Callable
     * 通过使用Future和Callable，一个线程可以异步地执行一个任务，并在完成后获取结果。这可以视为线程间的一种通信形式。
     *
     * Java
     * 深色版本
     * import java.util.concurrent.Callable;
     * import java.util.concurrent.FutureTask;
     *
     * public class FutureCallableExample {
     *     public static void main(String[] args) {
     *         Callable<Integer> callable = () -> {
     *             // 执行一些计算
     *             return 42;
     *         };
     *
     *         FutureTask<Integer> futureTask = new FutureTask<>(callable);
     *         new Thread(futureTask).start();
     *
     *         try {
     *             int result = futureTask.get(); // 等待结果
     *             System.out.println("Result: " + result);
     *         } catch (Exception e) {
     *             e.printStackTrace();
     *         }
     *     }
     * }
     * 7. ThreadLocal
     * ThreadLocal变量提供了一种线程隔离的机制，每个线程都有自己的变量副本，可以用于线程间的非通信场景，如线程内的状态保存。
     *
     * 8. CountDownLatch, CyclicBarrier, and Semaphore
     * 这些类在java.util.concurrent包中，提供了更复杂的线程同步和通信机制，如计数器、栅栏和信号量，可用于复杂的线程间协调。
     *
     * 9. BlockingQueues
     * BlockingQueue接口提供了一种线程安全的方式来存储和检索元素，当队列为空或满时，操作将阻塞，直到队列变得非空或非满。
     *
     * 每种通信方式都有其特定的用途和限制，选择哪种方式取决于具体的使用场景和需求。在设计多线程应用时，理解这些机制的差异和如何正确使用它们是非常重要的。
     */
    public static void main(String[] args) {

    }
}

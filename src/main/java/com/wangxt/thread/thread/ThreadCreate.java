package com.wangxt.thread.thread;

import java.util.concurrent.*;

public class ThreadCreate {

    public static void main(String[] args) throws Exception {
        // 单独创建线程
        Thread thread0 = new Thread0();
        thread0.start();

        Thread thread1 = new Thread1();
        thread1.start();

        Thread2 thread2 = new Thread2();
        new Thread(thread2, "线程2").start();

        Thread3 thread3 = new Thread3();
        // 最终还是要提交给线程执行才能使用新线程
        FutureTask futureTask = new FutureTask<>(thread3);
        futureTask.run();
        Object object = futureTask.get();
        System.out.println(object.toString());

        FutureTask futureTask1 = new FutureTask<>(thread3);
        new Thread(futureTask1, "线程3").start();

        Object object1 = futureTask1.get();
        System.out.println(object1.toString());

        // 通过线程池
        FutureTask futureTask2 = new FutureTask<>(thread3);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<?> submit = executorService.submit(thread3);

        System.out.println(submit.get());
        // 不进行shutdown的话，线程池中的线程会进行waiting
        executorService.shutdown();
    }
}

class Thread0 extends Thread {

}

class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 1 execute");
    }
}

class Thread2 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 2 execute ");
    }
}

class Thread3 implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "call 执行了");
        return Thread.currentThread().getName() + "3 execute";
    }
}

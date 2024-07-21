package com.wangxt.thread.thread;

public class ThreadSourceCode {
    public static void main(String[] args) {
        /**
         * 1.首先设置线程组，早些年的java还没juc中多线程框架，所以使用group对线程进行批管理
         * 优先判断创建线程时是否指定了线程组，如果指定了就用，如果没有指定，则使用安全管理器的线程组，这是为了安全管理器可能使用线程组对线程进行安全管理
         * 如果安全管理器也没有，则使用当前线程的线程组，这样对异常打印和资源管理方便进行统一处理
         * 2.将父线程的属性设置给当前线程
         * 3.将线程对象放入堆中
         */

        Thread thread = new Thread();

        /**
         * 1.synchronized修饰，同步执行
         * 2.如果线程状态不为0，抛出异常
         * 3.调用start0本地方法，通知os时间片可以执行了，至于什么时间执行，看os调度
         *
         */
        thread.start();
    }
}

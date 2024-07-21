package com.wangxt.thread.thread;

public class ThreadState {
    public static void main(String[] args) {
        // 创建静态的Thead实例
        Thread.State aNew = Thread.State.NEW;
        // 调用start方法（就绪），等待os时间片（运行）
        Thread.State runnable = Thread.State.RUNNABLE;
        // 只针对sync锁
        Thread.State blocked = Thread.State.BLOCKED;

        Thread.State waiting = Thread.State.WAITING;
        Thread.State terminated = Thread.State.TERMINATED;
        Thread.State timedWaiting = Thread.State.TIMED_WAITING;
    }
}

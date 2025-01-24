package com.wangxt.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();

        reentrantLock.lock();
    }
}

package com.wangxt.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {
    private static final Sync sync = new Sync();

    static class Sync extends AbstractQueuedSynchronizer {
        /**
         * 尝试获取锁，arg为目标值，使用cas尝试将state改为目标值
         */
        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            // 未加锁,因为state是volatile的，所以获取state时不需要加锁
            if (state == 0) {
                // 尝试cas获取锁，如果获取不到则快速失败
                if (compareAndSetState(0, arg)) {
                    // 获取锁成功，设置当前线程
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            } else {
                // 如果不为0，说明有线程获取到了锁，判断是否需要重入
                if (getExclusiveOwnerThread() == Thread.currentThread()) {
                    state++;
                    setState(state);
                    return true;
                }
            }

            return false;
        }

        /**
         * 尝试释放锁，arg为目标值
         */
        @Override
        protected boolean tryRelease(int arg) {
            // 哪个线程加的锁，只能由哪个线程释放
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }

            // 取消重入次数，如果state=0说明是最后一次重入，则将独占线程置为null
            int state = getState() - arg;
            if (state == 0) {
                setExclusiveOwnerThread(null);
                return true;
            }

            // 如果不是最后一次重入，则更新state
            setState(state);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return Thread.currentThread() == getExclusiveOwnerThread();
        }

        public Condition newCondition() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        // 尝试cas将state由0改成1
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(0);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public static void main(String[] args) {
        MyLock myLock = new MyLock();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始了");
            myLock.lock();
            System.out.println(Thread.currentThread().getName() + "执行了");

            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                myLock.unlock();
            }
        }, "A").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始了");
            myLock.lock();
            System.out.println(Thread.currentThread().getName() + "执行了");

            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                myLock.unlock();
            }
        }, "B").start();
    }
}

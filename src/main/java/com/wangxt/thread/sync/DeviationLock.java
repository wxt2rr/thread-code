package com.wangxt.thread.sync;

import org.openjdk.jol.info.ClassLayout;

public class DeviationLock {
    static Object obj = new Object();

    /**
     * 偏向锁默认延迟开启，不设置的话可能会直接变为轻量级锁，通过-XX:BiasedLockingStartupDelay=0关闭延迟，新创建的对象的markword默认就是偏向锁
     * 只不过这个时候没有线程争抢
     */
    public static void main(String[] args) {
        System.out.println("============= 无锁 ============");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        synchronized (obj){
            System.out.println("============ 偏向锁 ===========");
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
    }
}

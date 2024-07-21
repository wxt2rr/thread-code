package com.wangxt;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

public class Fist {
    /**
     * 1, main 主线程
     * 2，Attach Listener 附件监听器 守护线程 开启方式：-XX:StartAttachListener，比如进行dump，jstack
     * 2, Reference Handler 处理引用的线程，强软弱虚
     * 3, Finalizer 垃圾回收器
     * 4, Signal Dispatcher 指令分发器
     * 12, Common-Cleaner
     * 13, JDWP Transport Listener: dt_socket
     * 14, JDWP Event Helper Thread
     * 15, JDWP Command Reader
     * 16, Notification Thread
     */
    public static void main(String[] args) throws Exception{
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + ", " + threadInfo.getThreadName());
        }

        TimeUnit.SECONDS.sleep(100000);
    }

    /**
     * "main" #1 prio=5 os_prio=31 cpu=57.76ms elapsed=26.47s tid=0x0000000128808e00 nid=0x2803 waiting on condition  [0x000000016d9fa000]
     *    java.lang.Thread.State: TIMED_WAITING (sleeping)
     *         at java.lang.Thread.sleep(java.base@17.0.10/Native Method)
     *         at java.lang.Thread.sleep(java.base@17.0.10/Thread.java:344)
     *         at java.util.concurrent.TimeUnit.sleep(java.base@17.0.10/TimeUnit.java:446)
     *         at com.wangxt.Fist.main(Fist.java:28)
     *
     * "Reference Handler" #2 daemon prio=10 os_prio=31 cpu=0.10ms elapsed=26.46s tid=0x000000011e8c9600 nid=0x4a03 waiting on condition  [0x000000016e84e000]
     *    java.lang.Thread.State: RUNNABLE
     *         at java.lang.ref.Reference.waitForReferencePendingList(java.base@17.0.10/Native Method)
     *         at java.lang.ref.Reference.processPendingReferences(java.base@17.0.10/Reference.java:253)
     *         at java.lang.ref.Reference$ReferenceHandler.run(java.base@17.0.10/Reference.java:215)
     *
     * "Finalizer" #3 daemon prio=8 os_prio=31 cpu=0.21ms elapsed=26.46s tid=0x000000011e8cbe00 nid=0x4d03 in Object.wait()  [0x000000016ea5a000]
     *    java.lang.Thread.State: WAITING (on object monitor)
     *         at java.lang.Object.wait(java.base@17.0.10/Native Method)
     *         - waiting on <0x000000061fc02f30> (a java.lang.ref.ReferenceQueue$Lock)
     *         at java.lang.ref.ReferenceQueue.remove(java.base@17.0.10/ReferenceQueue.java:155)
     *         - locked <0x000000061fc02f30> (a java.lang.ref.ReferenceQueue$Lock)
     *         at java.lang.ref.ReferenceQueue.remove(java.base@17.0.10/ReferenceQueue.java:176)
     *         at java.lang.ref.Finalizer$FinalizerThread.run(java.base@17.0.10/Finalizer.java:172)
     *
     * "Signal Dispatcher" #4 daemon prio=9 os_prio=31 cpu=0.15ms elapsed=26.45s tid=0x000000012d808200 nid=0x7803 waiting on condition  [0x0000000000000000]
     *    java.lang.Thread.State: RUNNABLE
     */
}

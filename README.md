# thread-code
Java并发相关代码，涵盖了线程、线程通信、Synchronized、volatile、JUC、阻塞队列、并发安全集合、线程池、异步等核心概念和原理。

## 我的理解
万事万物都是从简单到复杂，从基础到高级，Java的并发编程发展也是如此，所以我们只要理解这些概念及其出现的背景，解决了什么问题，我们才能更好的理解和使用它们。

### Jdk1.1 Runnable（任务）和Thread（执行）
将任务和执行分离，很好的体现了面向对象的思想，如果你的任务是可以复用的，那么就可以实现Runnable 接口，需要执行时可以直接执行runnable.run()方法，
那么就是主线程自己执行。如果执行new Thread(runnable).start()，那么就是创建一个新的线程去执行。但是注意，如果执行new Thread(runnable).run()
的话，还是主线程执行并非新建的线程执行。
如果你的任务和执行是绑定的，或者是指定场景用的，那么就可以继承Thread类，重写run()方法。

Java中的线程状态一定意义上参考了操作系统中线程的实现，因为最终程序还是需要在操作系统上运行。
Thread t = new Thead();  NEW 状态（创建TCB）
t.start();               RUNABLE 状态（等待操作系统调用）
run();                   RUNING 状态（执行中）
t.stop();                TERMINATED 状态（终止） 

### 

~~~
├─ Runnable  - 一个可执行任务，通常用于定义线程执行的代码。（1.1开始）
│
├─ Thread  - 代表一个线程，通过继承 Thread 类可以创建并启动新线程。(1.1开始，与Runnable绑定)
│
├─ Callable<V>  - 类似于 Runnable，但可以返回结果和抛出异常的任务。（1.5开始）
│
├─ Future<V>  - 代表一个异步计算的结果，可以通过它获取计算的结果或取消任务。（1.5开始）

├─ RunnableFuture<V>  - 一个 Future，同时也是一个 Runnable。

│          ├─ FutureTask<V>  - 一个可以被取消、暂停和恢复的异步计算任务，常用于包装 Callable 和 Runnable。
│  
│
├─ Executor  - 用于执行提交的 Runnable 任务的对象（1.5开始，解耦任务和任务执行的线程，线程复用，不用管线程生命周期。简化了多线程编程中的任务管理、调度和资源管理问题。它提供了一个灵活且可扩展的框架）
│  ├─ ExecutorService  - 扩展了 Executor，提供了管理终止和生成 Future 的方法。
│     ├─ AbstractExecutorService  - ExecutorService 的抽象实现，简化了自定义 ExecutorService 的实现。
│        ├─ ThreadPoolExecutor  - 用于管理池化线程的 ExecutorService 实现，提供了灵活的线程池配置。
│        ├─ ScheduledThreadPoolExecutor  - 一个 ExecutorService，可以调度在给定延迟后运行或定期执行的命令。
│        ├─ ForkJoinPool  - 用于执行 ForkJoinTask 的 ExecutorService，实现了工作窃取算法以提高并行性能。
│           ├─ ForkJoinTask<V>  - 一种轻量级任务，可用于并行计算。
│              ├─ RecursiveTask<V>  - ForkJoinTask 的一个子类，代表有返回值的任务。
│              ├─ RecursiveAction  - ForkJoinTask 的一个子类，代表没有返回值的任务。
│
├─ CompletionStage  - 表示计算的一个阶段，该阶段完成后可以触发其他阶段的计算。
│  ├─ CompletableFuture<V>  - 一个可手动完成的 Future，支持依赖多个阶段的异步计算。内部维护了一个stack，保存了当前Completion执行完成后需要执行的Completion链

|

├─ CompletionService<V>  - 用于管理异步任务的执行并获取其结果的服务。 （如何没有的话每次获取异步结果都要阻塞，提交任务与获取结果解耦，可以按顺序获取结果，获取结果方便不需要所有任务执行完，对比使用Future的情况）

│  ├─ ExecutorCompletionService<V>  - CompletionService 的实现，使用给定的 Executor 来执行任务。

|

├─ Lock  - 提供更广泛的锁定操作，取代 synchronized 方法和语句。
│  ├─ ReentrantLock  - 一个可重入的互斥锁，具有相同的基本行为和语义作为使用 synchronized 方法和语句访问的隐式监视器锁，但扩展了它的能力。
│  ├─ ReentrantReadWriteLock  - 维护一对相关的锁，一个用于只读操作，一个用于写入操作。
│     ├─ ReadLock  - ReentrantReadWriteLock 的读锁。
│     ├─ WriteLock  - ReentrantReadWriteLock 的写锁。
│
├─ AbstractQueuedSynchronizer  - 提供一个框架，用于实现依赖 FIFO 等待队列的阻塞锁和相关的同步器。
│  ├─ CountDownLatch  - 允许一个或多个线程等待，直到在其他线程中执行的一组操作完成。
│  ├─ Semaphore  - 用于控制对某组资源的访问数量。
│  ├─ CyclicBarrier  - 一个同步辅助工具，允许一组线程互相等待，直到到达某个公共屏障点。
│  ├─ Phaser  - 一个灵活的线程同步工具，支持分阶段的任务执行。
│  ├─ Exchanger  - 一个同步点，两个线程可以在此处交换彼此的数据。
│
├─ BlockingQueue<E>  - 支持阻塞操作的队列，用于生产者-消费者模式。
│  ├─ ArrayBlockingQueue<E>  - 一个由数组支持的有界阻塞队列。
│  ├─ LinkedBlockingQueue<E>  - 一个由链表支持的可选有界阻塞队列。
│  ├─ PriorityBlockingQueue<E>  - 一个无界的优先级阻塞队列。
│  ├─ DelayQueue<E>  - 一个实现了延迟获取元素的无界阻塞队列。
│  ├─ SynchronousQueue<E>  - 一个不存储元素的阻塞队列，每个插入操作必须等待另一个线程调用移除操作。
│  ├─ LinkedTransferQueue<E>  - 一个由链表支持的无界阻塞队列，支持传输操作。
~~~
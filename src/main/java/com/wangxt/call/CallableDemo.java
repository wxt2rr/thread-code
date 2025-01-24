package com.wangxt.call;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.LockSupport;

public class CallableDemo {
    public static void main(String[] args) throws  Exception{
        LockSupport.park();
        Call call = new Call();
        String call1 = call.call();
        System.out.println(call1);

        Call call11 = new Call();
        FutureTask<String> stringFutureTask = new FutureTask<>(call11);
        stringFutureTask.run();

        String s = stringFutureTask.get();
        System.out.println(s);
    }

    static class Call implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "call 结果为 1";
        }
    }
}

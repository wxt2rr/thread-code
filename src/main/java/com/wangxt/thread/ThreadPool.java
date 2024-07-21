package com.wangxt.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(int i=0;i<100;i++){
            executorService.submit(work(i));
        }




        //executorService.execute();

        executorService.shutdown();
    }

    private static Runnable work(int i){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("haha" + i);
            }
        };
    }
}

package com.wangxt;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Completion
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception{
        CompletableFuture<Void> aExecute = CompletableFuture.runAsync(() -> {
            System.out.println("a execute");
        });

        CompletableFuture<String> uCompletableFuture = aExecute.thenApply(aa -> {
            return "aaaa";
        });

        CompletableFuture<Void> voidCompletableFuture = uCompletableFuture.thenAccept(e -> {
            System.out.println(e);
        });

        voidCompletableFuture.thenRun(() -> {
            System.out.println("b execute");
        });

        voidCompletableFuture.

        get();
    }

    private static void get() {
        CompletableFuture<Map<Integer, String>> info = CompletableFuture.supplyAsync(() -> {
            // 获取模板id和信息
            Map<Integer, String> map = new HashMap<>();
            map.put(1, "哈哈");
            return map;
        });

        CompletableFuture<List<Integer>> tids = CompletableFuture.supplyAsync(() -> {
            // 获取模板
            return new ArrayList(Arrays.asList(1, 2, 3, 4));
        });

        CompletableFuture<List<String>> objectCompletableFuture = CompletableFuture.allOf(info, tids).thenApply(e -> {
            try {
                Map<Integer, String> integerStringMap = info.get();
                List<Integer> integers = tids.get();
                return integers.stream().map(a -> integerStringMap.get(a)).collect(Collectors.toList());
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (ExecutionException ex) {
                throw new RuntimeException(ex);
            }
        });

        objectCompletableFuture.thenAccept(System.out::println);
    }
}

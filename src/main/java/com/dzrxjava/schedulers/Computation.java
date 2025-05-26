package com.dzrxjava.schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Computation implements Rx {
    private static final int N = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService EXEC = Executors.newFixedThreadPool(N);

    @Override
    public void schedule(Runnable task) {
        EXEC.submit(task);
    }
}


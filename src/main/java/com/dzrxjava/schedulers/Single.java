package com.dzrxjava.schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Single implements Rx {
    private static final ExecutorService EXEC = Executors.newSingleThreadExecutor();

    @Override
    public void schedule(Runnable task) {
        EXEC.submit(task);
    }
}


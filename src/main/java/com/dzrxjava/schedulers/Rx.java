package com.dzrxjava.schedulers;

public interface Rx {
    /**
     * Запланировать выполнение задачи.
     *
     * @param task Runnable-задание
     */
    void schedule(Runnable task);
}


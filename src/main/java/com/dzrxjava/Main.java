package com.dzrxjava;

import com.dzrxjava.core.Observable;
import com.dzrxjava.operators.*;
import com.dzrxjava.schedulers.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Пример 1: map + filter + subscribeOn/observeOn
        Observable<Integer> source = Observable.just(1, 2, 3, 4, 5);
        System.out.println("=== map & filter с планировщиками ===");
        Filter.apply(
                        Map.apply(source, i -> i * 10),
                        i -> i >= 30
                )
                .subscribeOn(new IO())
                .observeOn(new Computation())
                .subscribe(
                        i -> System.out.println("Received: " + i),
                        Throwable::printStackTrace,
                        () -> System.out.println("Completed\n")
                );

        // Дадим асинхронным задачам завершиться
        Thread.sleep(500);

        // Пример 2: flatMap
        System.out.println("=== flatMap пример ===");
        FlatMap.apply(source, i ->
                Observable.just(i, i * i)
        ).subscribe(i -> System.out.println("flatMap: " + i));

        // Пример 3: merge
        System.out.println("\n=== merge пример ===");
        Merge.apply(
                Observable.just("A", "B"),
                Observable.just("1", "2")
        ).subscribe(s -> System.out.println("merge: " + s));

        // Пример 4: concat
        System.out.println("\n=== concat пример ===");
        Concat.apply(
                Observable.just("X", "Y"),
                Observable.just("Z")
        ).subscribe(s -> System.out.println("concat: " + s));
    }
}

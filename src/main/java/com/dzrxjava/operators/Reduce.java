package com.dzrxjava.operators;

import com.dzrxjava.core.Observable;
import com.dzrxjava.core.Observer;
import com.dzrxjava.core.Disposable;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;

public class Reduce {
    public static <T> Observable<T> apply(
            Observable<T> source,
            BiFunction<? super T, ? super T, ? extends T> accumulator
    ) {
        return Observable.create(observer -> {
            AtomicReference<T> acc = new AtomicReference<>();
            Disposable disp = source.subscribe(new Observer<T>() {
                @Override
                public void onNext(T item) {
                    if (acc.get() == null) {
                        acc.set(item);
                    } else {
                        acc.set(accumulator.apply(acc.get(), item));
                    }
                }
                @Override
                public void onError(Throwable t) {
                    observer.onError(t);
                }
                @Override
                public void onComplete() {
                    T result = acc.get();
                    if (result != null) {
                        observer.onNext(result);
                    }
                    observer.onComplete();
                }
            });
        });
    }
}


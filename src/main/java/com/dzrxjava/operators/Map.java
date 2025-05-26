package com.dzrxjava.operators;

import com.dzrxjava.core.Observable;
import com.dzrxjava.core.Observer;
import com.dzrxjava.core.Disposable;

import java.util.function.Function;

public class Map {
    public static <T, R> Observable<R> apply(
            Observable<T> source,
            Function<? super T, ? extends R> mapper
    ) {
        return Observable.create(observer -> {
            Disposable disp = source.subscribe(new Observer<T>() {
                @Override
                public void onNext(T item) {
                    observer.onNext(mapper.apply(item));
                }
                @Override
                public void onError(Throwable t) {
                    observer.onError(t);
                }
                @Override
                public void onComplete() {
                    observer.onComplete();
                }
            });
        });
    }
}


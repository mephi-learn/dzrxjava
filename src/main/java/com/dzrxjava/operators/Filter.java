package com.dzrxjava.operators;

import com.dzrxjava.core.Observable;
import com.dzrxjava.core.Observer;
import com.dzrxjava.core.Disposable;

import java.util.function.Predicate;

public class Filter {
    public static <T> Observable<T> apply(Observable<T> source, Predicate<? super T> predicate) {
        return Observable.create(observer -> {
            Disposable disp = source.subscribe(new Observer<T>() {
                @Override
                public void onNext(T item) {
                    if (predicate.test(item)) {
                        observer.onNext(item);
                    }
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


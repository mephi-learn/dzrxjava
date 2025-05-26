package com.dzrxjava.core;

public interface Observer<T> {
    void onNext(T item);
    void onError(Throwable t);
    void onComplete();
}


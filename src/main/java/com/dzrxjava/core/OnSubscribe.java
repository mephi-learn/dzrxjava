package com.dzrxjava.core;

@FunctionalInterface
public interface OnSubscribe<T> {
    void subscribe(Observer<? super T> observer);
}

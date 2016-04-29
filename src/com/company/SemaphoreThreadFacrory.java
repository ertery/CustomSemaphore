package com.company;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ertery on 29.04.2016.
 */

public class SemaphoreThreadFacrory implements ThreadFactory {
    private String name;
    private AtomicInteger integer = new AtomicInteger(0);

    public SemaphoreThreadFacrory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, name + integer.getAndIncrement());
    }
}

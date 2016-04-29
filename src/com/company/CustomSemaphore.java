package com.company;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ertery on 29.04.2016.
 */
public class CustomSemaphore {
    private AtomicInteger counter;
    private static final AtomicInteger ZERO = new AtomicInteger(0);

    public CustomSemaphore(int counter) {
        this.counter = new AtomicInteger(counter);
    }

    public synchronized void acquire() throws InterruptedException {
        System.out.println(String.format("[%s] Try to acquire semaphore",
                Thread.currentThread().getName()));
        while (counter.get() == 0) {
            System.out.println(String.format("[%s] No free Locks!!! Waiting for",
                    Thread.currentThread().getName()));
            wait();
        }
        counter.decrementAndGet();
        System.out.println(String.format("[%s] Semaphore acquired [%d] remains",
                Thread.currentThread().getName(), counter.get()));
    }

    public synchronized void release() {
        counter.incrementAndGet();
        System.out.println(String.format("[%s] Semaphore was released [%d] remains",
                Thread.currentThread().getName(), counter.get()));
        notifyAll();
    }
}

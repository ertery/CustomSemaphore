package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main {

    public static void main(String[] args) {
        CustomSemaphore s = new CustomSemaphore(6);

        ExecutorService service = Executors.newFixedThreadPool(10, new SemaphoreThreadFacrory("Process"));
        for (int i = 0; i < 10; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        Thread.sleep(5000);
                        s.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        service.shutdown();
    }
}

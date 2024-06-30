package ru.chernevich.testAPI.crpt.service;

import ru.chernevich.testAPI.crpt.document.DocumentCrpt;

import java.util.concurrent.TimeUnit;

public class CrptApi {
    private final TimeUnit timeUnit;
    private final int requestLimit;
    private int requestCount = 0;
    private long lastRequestTime = System.currentTimeMillis();

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        this.timeUnit = timeUnit;
        this.requestLimit = requestLimit;
    }

    public synchronized void createDocument(DocumentCrpt documentCrpt, String signature) {
        long currentTime = System.currentTimeMillis();

        if(currentTime - lastRequestTime < timeUnit.toMillis(1)) {
            if(requestCount >= requestLimit) {
                long timeToWait = timeUnit.toMillis(1);
                try {
                    wait(timeToWait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        requestCount++;
        lastRequestTime = System.currentTimeMillis();
    }
}


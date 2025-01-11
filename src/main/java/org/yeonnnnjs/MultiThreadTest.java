package org.yeonnnnjs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadTest {
    public static void main(String[] args) {
        int iterations = 1000000;
        int threadCount = 4;
        long timeOfPlus;
        long timeOfConcat;
        long timeOfBuilder;
        long timeOfBuffer;

        // String +연산자
        ExecutorService executor1 = Executors.newFixedThreadPool(threadCount);
        long startTime = System.nanoTime();
        AtomicInteger lengthOfPlus = new AtomicInteger(0);
        for (int i = 0; i < threadCount; i++) {
            executor1.submit(() -> {
                String str = "";
                for (int j = 0; j < iterations; j++) {
                    str += "a";
                }
                lengthOfPlus.addAndGet(str.length());
            });
        }
        executor1.shutdown();
        while (!executor1.isTerminated()) {}
        long endTime = System.nanoTime();
        timeOfPlus = endTime - startTime;

        // String.concat()
        ExecutorService executor4 = Executors.newFixedThreadPool(threadCount);
        startTime = System.nanoTime();
        AtomicInteger lengthOfConcat = new AtomicInteger(0);
        for (int i = 0; i < threadCount; i++) {
            executor4.submit(() -> {
                String str = "";
                for (int j = 0; j < iterations; j++) {
                    str = str.concat("a");
                }
                lengthOfConcat.addAndGet(str.length());
            });
        }
        executor4.shutdown();
        while (!executor4.isTerminated()) {}
        endTime = System.nanoTime();
        timeOfConcat = endTime - startTime;

        // StringBuilder
        ExecutorService executor3 = Executors.newFixedThreadPool(threadCount);
        StringBuilder stringBuilder = new StringBuilder();
        startTime = System.nanoTime();
        for (int i = 0; i < threadCount; i++) {
            executor3.submit(() -> {
                for (int j = 0; j < iterations; j++) {
                    stringBuilder.append("a");
                }
            });
        }
        executor3.shutdown();
        while (!executor3.isTerminated()) {}
        endTime = System.nanoTime();
        timeOfBuilder = endTime - startTime;

        // StringBuffer
        ExecutorService executor2 = Executors.newFixedThreadPool(threadCount);
        StringBuffer stringBuffer = new StringBuffer();
        startTime = System.nanoTime();
        for (int i = 0; i < threadCount; i++) {
            executor2.submit(() -> {
                for (int j = 0; j < iterations; j++) {
                    stringBuffer.append("a");
                }
            });
        }
        executor2.shutdown();
        while (!executor2.isTerminated()) {}
        endTime = System.nanoTime();
        timeOfBuffer = endTime - startTime;

        System.out.println("String +연산자 길이: " + lengthOfPlus);
        System.out.println("String.concat() 길이: " + lengthOfConcat);
        System.out.println("StringBuilder 길이: " + stringBuilder.length());
        System.out.println("StringBuffer 길이: " + stringBuffer.length());

        System.out.println("String +연산자 소요 시간: " + timeOfPlus / 1000000.0 + " ms");
        System.out.println("String.concat() 소요 시간: " + timeOfConcat / 1000000.0 + " ms");
        System.out.println("StringBuilder 소요 시간: " + timeOfBuilder/ 1000000.0 + " ms");
        System.out.println("StringBuffer 소요 시간: " + timeOfBuffer / 1000000.0 + " ms");
    }
}
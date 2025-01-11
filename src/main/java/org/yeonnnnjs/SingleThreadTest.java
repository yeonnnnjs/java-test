package org.yeonnnnjs;

public class SingleThreadTest {
    public static void main(String[] args) {
        int iterations = 1000000;
        int lengthOfPlus;
        int lengthOfConcat;
        long timeOfPlus;
        long timeOfConcat;
        long timeOfBuilder;
        long timeOfBuffer;

        // String +연산자
        long startTime = System.nanoTime();
        String str = "";
        for (int i = 0; i < iterations; i++) {
            str += "a";
        }
        long endTime = System.nanoTime();
        timeOfPlus = endTime - startTime;
        lengthOfPlus = str.length();

        // String.concat()
        startTime = System.nanoTime();
        str = "";
        for (int i = 0; i < iterations; i++) {
            str = str.concat("a");
        }
        endTime = System.nanoTime();
        timeOfConcat = endTime - startTime;
        lengthOfConcat = str.length();

        // StringBuilder 테스트
        startTime = System.nanoTime();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            stringBuilder.append("a");
        }
        endTime = System.nanoTime();
        timeOfBuilder = endTime - startTime;

        // StringBuffer 테스트
        startTime = System.nanoTime();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            stringBuffer.append("a");
        }
        endTime = System.nanoTime();
        timeOfBuffer = endTime - startTime;

        System.out.println("String +연산자 문자열 길이: " + lengthOfPlus);
        System.out.println("String.concat() 문자열 길이: " + lengthOfConcat);
        System.out.println("StringBuilder 문자열 길이: " + stringBuilder.length());
        System.out.println("StringBuffer 문자열 길이: " + stringBuffer.length());

        System.out.println("String +연산자 소요 시간: " + timeOfPlus / 1000000.0 + " ms");
        System.out.println("String.concat() 소요 시간: " + timeOfConcat / 1000000.0 + " ms");
        System.out.println("StringBuilder 소요 시간: " + timeOfBuilder / 1000000.0 + " ms");
        System.out.println("StringBuffer 소요 시간: " + timeOfBuffer / 1000000.0 + " ms");
    }
}
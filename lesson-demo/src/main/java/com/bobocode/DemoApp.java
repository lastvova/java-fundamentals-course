package com.bobocode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class DemoApp {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(IntStream.generate(() -> ThreadLocalRandom.current().nextInt(100))
                .limit(20)
                .boxed()
                .toList());
        System.out.println(list);
        ForkJoinPool.commonPool().invoke(new MergeSort<>(list));
        System.out.println(list);
    }
}

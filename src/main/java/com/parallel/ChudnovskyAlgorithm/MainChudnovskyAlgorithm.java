package com.parallel.ChudnovskyAlgorithm;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainChudnovskyAlgorithm {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var cp = new CalsPi(Executors.newFixedThreadPool(10));
        System.out.println(cp.cals(10000));
        
        cp.shutdown();
    }
}

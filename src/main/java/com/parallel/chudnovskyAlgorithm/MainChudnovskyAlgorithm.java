package com.parallel.chudnovskyAlgorithm;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class MainChudnovskyAlgorithm {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var cp = new CalsPi(Executors.newFixedThreadPool(8));
        System.out.println(cp.cals(10000));
        
        cp.shutdown();
    }
}

package com.parallel.ranks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AppParallel {
  public static void main(String[] args) throws InterruptedException, ExecutionException   {
    int n = 16;
    ExecutorService e = Executors.newFixedThreadPool(n);
    List<Future<Double>> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        Future<Double> f = e.submit(new СalculationsPi(i,n));        
        list.add(f);
    }
    Double pi = 0.0;
    for (Future<Double> future : list) {
        pi += future.get();
    }
    pi *= 4;
    System.out.println(Math.PI - pi);
    System.out.println(Math.PI);
    System.out.printf("Результат ПИ = %.15f\n", pi);
    e.shutdown();
  
  }  
}

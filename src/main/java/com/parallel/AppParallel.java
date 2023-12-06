package com.parallel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class AppParallel {
  public static void main(String[] args) throws InterruptedException, ExecutionException   {
    int n = 100;
    ExecutorService e = Executors.newFixedThreadPool(n);
    List<Future<BigDecimal>> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        Future<BigDecimal> f = e.submit(new СalculationsPi(i,n));        
        list.add(f);
    }
    BigDecimal pi = new BigDecimal(0.0);
    for (Future<BigDecimal> future : list) {
        pi.add(future.get());
    }
    pi.multiply(new BigDecimal(4));
    System.out.printf("Результат ПИ = %.23f\n", pi);
    e.shutdown();
  
  }  
}

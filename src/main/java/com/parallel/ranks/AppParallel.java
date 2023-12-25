package com.parallel.ranks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AppParallel {
  public static void main(String[] args) throws InterruptedException, ExecutionException   {
    int tn = Runtime.getRuntime().availableProcessors() - 1;
    long n = 99999999;
    ExecutorService e = Executors.newFixedThreadPool(tn);
    List<Future<BigDecimal>> list = new ArrayList<>();
    int tmp = 0;
    for (int i = 1,j=0; j < n; i++) {
        tmp = j;
        j+=1000;
        Future<BigDecimal> f = e.submit(new СalculationsPi(tmp,j));        
        list.add(f);
    }
    BigDecimal pi = new BigDecimal(0.0);
    for (Future<BigDecimal> future : list) {
        pi = pi.add(future.get());
    }
    pi = pi.multiply(new BigDecimal(4));
    // System.out.println(Math.PI - pi);
    System.out.println(Math.PI);
    System.out.printf("Результат ПИ = %.75f\n", pi);
    e.shutdown();
  
  }  


}

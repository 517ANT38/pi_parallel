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
    int n = 1000;
    ExecutorService e = Executors.newVirtualThreadPerTaskExecutor();
    List<Future<BigDecimal>> list = new ArrayList<>();
    for (int i = 1; i < n+1; i++) {
        Future<BigDecimal> f = e.submit(new СalculationsPi(i,n/i));        
        list.add(f);
    }
    BigDecimal pi = new BigDecimal(0.0);
    for (Future<BigDecimal> future : list) {
        pi = pi.add(future.get());
    }
    pi = pi.multiply(new BigDecimal(4));
    // System.out.println(Math.PI - pi);
    System.out.println(Math.PI);
    System.out.printf("Результат ПИ = %.89f\n", pi);
    e.shutdown();
  
  }  

  public static char getPiDigit(int n) {
    double sum = 0;
    int k = 0;
    while (true) {
        double term = (4.0 / (8 * k + 1) - 2.0 / (8 * k + 4) - 1.0 / (8 * k + 5) - 1.0 / (8 * k + 6)) / Math.pow(16, k);
        if (term == 0) {
            break;
        }
        sum += term;
        k++;
    }
    
    double result = sum * Math.pow(16, -n);
    int integerPart = (int) result;
    char digit = Integer.toHexString(integerPart).charAt(0);
    return digit;
}
}

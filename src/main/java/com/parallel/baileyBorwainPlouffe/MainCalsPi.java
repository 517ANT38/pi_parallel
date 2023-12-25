package com.parallel.baileyBorwainPlouffe;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class MainCalsPi {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
    

        int n = Runtime.getRuntime().availableProcessors() - 1;

        CalsPi calsPi = new CalsPi(Executors.newFixedThreadPool(n));

        var res = calsPi.cals(100000, 3);

        System.out.println(res);

        calsPi.shutdown();
        
        
    }
    
}

package com.parallel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class MainCalsPi {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // var res = new BigDecimal(0.0);
        // for (int i = 0; i < 10000; i++) {
        //     BigDecimal pow16 = BigDecimal.ONE
        //                     .divide(BigDecimal.valueOf(16)
        //                         .pow(i));
            
        //     var m = new MathContext(3);
            
        //     var f1 = BigDecimal.valueOf(4).divide(BigDecimal.valueOf(8 * i + 1),m);
        //     var f2 = BigDecimal.valueOf(2).divide(BigDecimal.valueOf(8 * i + 4),m);
        //     var f3 = BigDecimal.ONE.divide(BigDecimal.valueOf(8 * i + 5),m);
        //     var f4 = BigDecimal.ONE.divide(BigDecimal.valueOf(8 * i + 6),m);
            
        //     res = res.add(pow16.multiply(f1.subtract(f2).subtract(f3).subtract(f4)));
        // }
        // System.out.println(res);

        int n = Runtime.getRuntime().availableProcessors() - 1;

        CalsPi calsPi = new CalsPi(Executors.newCachedThreadPool());

        var res = calsPi.cals(10000, 1);

        System.out.println(res);

        calsPi.shutdown();
    }
    
}

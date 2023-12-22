package com.parallel.integral;


import java.math.BigDecimal;
import java.util.concurrent.Callable;

public class СalculationsPi implements Callable<BigDecimal> {

    
    private final ArgsThread aThread;
    

    public СalculationsPi(ArgsThread aThread) {
        this.aThread = aThread;
    }


    @Override
    public BigDecimal call() throws Exception {

        double x ;
        double sum = 0;
        double step = aThread.getStep();

        for (long i = aThread.getLeft(); i < aThread.getRight(); i++) {
            x = step * (i + .5);
            sum = sum + 4.0/(1 + x * x);
            
        }

        return new BigDecimal(sum * step);

    }
    
}

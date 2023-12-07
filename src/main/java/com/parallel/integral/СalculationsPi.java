package com.parallel.integral;


import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.Callable;

public class СalculationsPi implements Callable<BigDecimal> {

    
    private final ArgsThread aThread;
    

    public СalculationsPi(ArgsThread aThread) {
        this.aThread = aThread;
    }


    @Override
    public BigDecimal call() throws Exception {

        BigDecimal x ;
        BigDecimal sum = new BigDecimal(0);
        BigDecimal step = aThread.getStep();

        for (long i = aThread.getLeft(); i < aThread.getRight(); i++) {
            x = step.multiply(new BigDecimal(i + .5));
            sum = sum.add(new BigDecimal(4.0)
            .divide(new BigDecimal(1)
            .add(x.multiply(x)),
            MathContext.DECIMAL128));
        }

        return sum.multiply(step);

    }
    
}

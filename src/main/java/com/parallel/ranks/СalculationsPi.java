package com.parallel.ranks;


import java.math.BigDecimal;
import java.util.concurrent.Callable;

public class СalculationsPi implements Callable<BigDecimal> {

    private final int num;

    private final int nt;

    private static final long steps = 500000;

    public СalculationsPi(int num, int nt) {
        this.num = num;
        this.nt = nt;
    }

    @Override
    public BigDecimal call() throws Exception {
        BigDecimal pi = new BigDecimal(0.0);
        for (long i = num; i < steps; i += nt) {            
            pi = pi.add(new BigDecimal(1.0/(i*4.0 + 1.0)));
            pi = pi.subtract(new BigDecimal(1.0/(i*4.0 + 3.0)));
        }
        return pi;

    }
    
}

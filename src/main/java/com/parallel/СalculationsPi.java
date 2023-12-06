package com.parallel;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

public class СalculationsPi implements Callable<BigDecimal> {

    private final int num;

    private final int nt;

    private static final long steps=2000000000;

    public СalculationsPi(int num, int nt) {
        this.num = num;
        this.nt = nt;
    }

    @Override
    public BigDecimal call() throws Exception {
        BigDecimal res = new BigDecimal(0.0);
        for (long i = num; i < steps; i += nt) {
            res.add(new BigDecimal( 1.0/(i*4.0 + 1.0)));
            res.subtract(new BigDecimal(1.0/(i*4.0 + 3.0)));
        }
        return res;

    }
    
}

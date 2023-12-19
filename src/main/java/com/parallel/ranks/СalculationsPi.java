package com.parallel.ranks;


import java.math.BigDecimal;
import java.util.concurrent.Callable;

public class СalculationsPi implements Callable<BigDecimal> {

    private final int st;
    private final int ed;
   

    public СalculationsPi(int st,int ed) {
        this.st = st;
        this.ed = ed;
    }

    @Override
    public BigDecimal call() throws Exception {
        BigDecimal pi = new BigDecimal(0.0);
        for (long i = st; i < ed; i++ ) {            
            pi = pi.add(new BigDecimal(1.0/(i*4.0 + 1.0)));
            pi = pi.subtract(new BigDecimal(1.0/(i*4.0 + 3.0)));
        }
        return pi;

    }
    
}

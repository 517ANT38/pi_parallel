package com.parallel.ranks;


import java.util.concurrent.Callable;

public class СalculationsPi implements Callable<Double> {

    private final int num;

    private final int nt;

    private static final long steps = 50000000;

    public СalculationsPi(int num, int nt) {
        this.num = num;
        this.nt = nt;
    }

    @Override
    public Double call() throws Exception {
        Double res = 0.0;
        for (long i = num; i < steps; i += nt) {            
            res += 1.0/(i*4.0 + 1.0);
            res -= 1.0/(i*4.0 + 3.0);
        }
        return res;

    }
    
}

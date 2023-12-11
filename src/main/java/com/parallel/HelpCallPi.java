package com.parallel;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.Callable;

public class HelpCallPi implements Callable<BigDecimal> {

    private final int eps;
    private final int start;
    private final int end;

    public HelpCallPi(int eps, int start, int end) {
        this.eps = eps;
        this.start = start;
        this.end = end;
    }

    @Override
    public BigDecimal call() throws Exception {
        var res = new BigDecimal(0.0);
        
        for (int i = start; i < end; i++) {
            BigDecimal pow16 = BigDecimal.ONE
                            .divide(BigDecimal.valueOf(16)
                                .pow(i));
            
            var m = new MathContext(eps);
            
            var f1 = BigDecimal.valueOf(4).divide(BigDecimal.valueOf(8 * i + 1),m);
            var f2 = BigDecimal.valueOf(2).divide(BigDecimal.valueOf(8 * i + 4),m);
            var f3 = BigDecimal.ONE.divide(BigDecimal.valueOf(8 * i + 5),m);
            var f4 = BigDecimal.ONE.divide(BigDecimal.valueOf(8 * i + 6),m);
            
            res = res.add(pow16.multiply(f1.subtract(f2).subtract(f3).subtract(f4)));
        }
       
        return res;
    }
    
}

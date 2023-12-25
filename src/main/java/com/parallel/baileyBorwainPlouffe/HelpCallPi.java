package com.parallel.baileyBorwainPlouffe;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.Callable;

public class HelpCallPi implements Callable<BigDecimal> {

    private final int end;
    private final int start;
    private final MathContext m;
    private static final BigDecimal P16 = BigDecimal.valueOf(16);
    private static final BigDecimal P4 = BigDecimal.valueOf(4);

    public HelpCallPi(MathContext m, int start, int end) {
        this.m = m;
        this.start = start;
        this.end = end;
    }

    @Override
    public BigDecimal call() throws Exception {
        
        var res = new BigDecimal(0.0);
        
        for (int i = start; i < end; i++) {

            BigDecimal pow16 = BigDecimal.ONE.divide(P16.pow(i));           
                        
            var f1 = P4.divide(BigDecimal.valueOf(8 * i + 1),m);
            var f2 = BigDecimal.TWO.divide(BigDecimal.valueOf(8 * i + 4),m);
            var f3 = BigDecimal.ONE.divide(BigDecimal.valueOf(8 * i + 5),m);
            var f4 = BigDecimal.ONE.divide(BigDecimal.valueOf(8 * i + 6),m);
            
            res = res.add(pow16.multiply(f1.subtract(f2).subtract(f3).subtract(f4)));
        }
       
        return res;
    }
   
}

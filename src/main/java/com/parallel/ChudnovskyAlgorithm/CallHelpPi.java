package com.parallel.ChudnovskyAlgorithm;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.BigDecimal;
import java.util.concurrent.Callable;

public class CallHelpPi implements Callable<BigDecimal>{

    private final int s;
    private final int e;
    private final int eps;
    private static final BigDecimal B = BigDecimal.valueOf(545140134);
    private static final BigDecimal A = BigDecimal.valueOf(13591409);
    private static final BigDecimal C = BigDecimal.valueOf(640320).pow(3);

    public CallHelpPi(int s, int e, int eps) {
        this.s = s;
        this.e = e;
        this.eps = eps;
    }



    @Override
    public BigDecimal call() throws Exception {
        var res = new BigDecimal(0.0);
        
        for (int i = s; i < e; i++) {
            var v1 = BigDecimal.ONE
                .negate()
                .pow(i)
                .multiply(factTree(6*i)
                .multiply(A.add(B.multiply(BigDecimal.valueOf(i),new MathContext(eps)))));
            var v2 = factTree(i*3).multiply(factTree(i).pow(3)).multiply(C).pow(3).sqrt(new MathContext(eps));
            var v3 = v1.divide(v2,new MathContext(eps));
            res = res.add(v3);
        }
        return BigDecimal.ONE.divide(res,new MathContext(eps));
    }
    
    private  BigDecimal prodTree(int l, int r)
    {
        if (l > r)
            return BigDecimal.ONE;
        if (l == r)
            return BigDecimal.ONE;
        if (r - l == 1)
            return BigDecimal.valueOf(l * r);
        int m = (l + r) / 2;
        return prodTree(l, m).multiply(prodTree(m + 1, r),new MathContext(eps));
    }
            
    private BigDecimal factTree(int n)
    {
        if (n < 0)
            return BigDecimal.ZERO;
        if (n == 0)
            return BigDecimal.ONE;
        if (n == 1 || n == 2)
            return BigDecimal.valueOf(n);
        return prodTree(2, n);
    }  


}

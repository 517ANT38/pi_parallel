package com.parallel.ChudnovskyAlgorithm;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.BigDecimal;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class CallHelpPi implements Callable<BigDecimal>{

    private final int s;
    private final int e;
    private final int eps;
    private static final int B = 545140134;
    private static final int A = 13591409;
    private static final BigDecimal C = BigDecimal.valueOf(640320);

    public CallHelpPi(int s, int e, int eps) {
        this.s = s;
        this.e = e;
        this.eps = eps;
    }



    @Override
    public BigDecimal call() throws Exception {
        var res = new BigDecimal(0);
        Function<Integer,Integer> abs = x -> (x > 0)? x : -x;
        for (int i = s; i < e; i++) {
            var i3 = factTree(i) * factTree(i) * factTree(i);
            var v1 = BigDecimal.valueOf(abs.apply(-1) * factTree(6 * i)*(B * i + A));
            var v2 = C.pow(3*i+1);
            v2 = v2.multiply(BigDecimal.valueOf(factTree(3*i)*i3));
            v2 = v2.add(BigDecimal.valueOf(0.000000000001)); 
            v1 = v1.divide(v2,MathContext.DECIMAL128);
            res = res.add(v1);
            
        }
       
        return res;
        // return BigDecimal.ONE.divide(res,new MathContext(eps));
    }
    
    private  long prodTree(int l, int r)
    {
        if (l > r)
            return 1;
        if (l == r)
            return 1;
        if (r - l == 1)
            return l * r;
        int m = (l + r) / 2;
        return prodTree(l, m)*prodTree(m + 1, r);
    }
            
    private long factTree(int n)
    {
        if (n < 0)
            return 0;
        if (n == 0)
            return 1;
        if (n == 1 || n == 2)
            return n;
        return prodTree(2, n);
    }  


}

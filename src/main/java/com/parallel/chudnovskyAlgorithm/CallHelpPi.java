package com.parallel.chudnovskyAlgorithm;

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
        for (int i = s + 1 ; i < e; i++) {
            var i3 = factTree(i).multiply(factTree(i)).multiply(factTree(i));
            var v1 = BigDecimal.valueOf(abs.apply(-1)).multiply(factTree(6 * i)).multiply(BigDecimal.valueOf(B * i + A));
            var v2 = C.pow(3*i+1);
            v2 = v2.multiply(factTree(3*i).multiply(i3),MathContext.DECIMAL128);
            // System.out.printf("res=%s, i=%d 3*i=%d\n",factTree(3*i).toString(),i,i*3);
            v1 = v1.divide(v2,MathContext.DECIMAL128);
            res = res.add(v1);
            
        }
       
        return res;
        // return BigDecimal.ONE.divide(res,new MathContext(eps));
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

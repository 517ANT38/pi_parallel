package com.parallel.ranks;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class AppСonsistently 
{
    public static void main( String[] args )
    {
        BigDecimal pi = new BigDecimal(0.0);
        long n = 100000;
        for (int i = 0; i < n; i++) {
            pi = pi.add(new BigDecimal(1.0/(i*4.0 + 1.0)));
            pi = pi.subtract(new BigDecimal(1.0/(i*4.0 + 3.0)));
        }
        pi = pi.multiply(new BigDecimal(4));
        System.out.printf("Результат ПИ = %.17g \n", pi); 
    }
}

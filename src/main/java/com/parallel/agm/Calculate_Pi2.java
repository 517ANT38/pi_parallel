package com.parallel.agm;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Calculate_Pi2 {
    private static final MathContext con1024 = new MathContext(100000);
    private static final BigDecimal bigTwo = new BigDecimal(2);
    private static final BigDecimal bigFour = new BigDecimal(4);
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    private static BigDecimal bigSqrt(BigDecimal bd, MathContext con) {
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal x1 = BigDecimal.valueOf(Math.sqrt(bd.doubleValue()));
        while (!Objects.equals(x0, x1)) {
            x0 = x1;
            x1 = bd.divide(x0, con).add(x0).divide(bigTwo, con);
        }
        return x1;
    }

    private static class CalculatePiTask implements Runnable {
        private final BigDecimal a;
        private final BigDecimal g;
        private final BigDecimal pow;
        private final BigDecimal sum;

        public CalculatePiTask(BigDecimal a, BigDecimal g, BigDecimal pow, BigDecimal sum) {
            this.a = a;
            this.g = g;
            this.pow = pow;
            this.sum = sum;
        }

        @Override
        public void run() {
            BigDecimal t;
            BigDecimal localA = a;
            BigDecimal localG = g;
            BigDecimal localPow = pow;
            BigDecimal localSum = sum;

            while (!Objects.equals(localA, localG)) {
                t = localA.add(localG).divide(bigTwo, con1024);
                localG = bigSqrt(localA.multiply(localG), con1024);
                localA = t;
                localPow = localPow.multiply(bigTwo);
                localSum = localSum.add(localA.multiply(localA).subtract(localG.multiply(localG)).multiply(localPow));
            }

            synchronized (CalculatePiTask.class) {
                sum.add(localSum);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BigDecimal a = BigDecimal.ONE;
        BigDecimal g = a.divide(bigSqrt(bigTwo, con1024), con1024);
        BigDecimal t;

        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal pow = bigTwo;

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i = 0; i < NUM_THREADS; i++) {
            executorService.execute(new CalculatePiTask(a, g, pow, sum));
            pow = pow.multiply(bigTwo);
        }

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        BigDecimal pi = bigFour.multiply(a.multiply(a)).divide(BigDecimal.ONE.subtract(sum), con1024);
        System.out.println(pi);
    }
}

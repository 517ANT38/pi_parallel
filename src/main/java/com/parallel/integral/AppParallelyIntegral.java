package com.parallel.integral;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AppParallelyIntegral {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long numSteps = 999999999;
        int cntThreads = Runtime.getRuntime().availableProcessors();
        BigDecimal step = new BigDecimal(1./numSteps);
        long cntStepsPerThread= Math.round((numSteps / cntThreads) / 100);

        List<Future<BigDecimal>> list = new ArrayList<>();
        ExecutorService e = Executors.newFixedThreadPool(cntThreads);

        for (int i = 0; i < cntThreads; i++) {
            ArgsThread a = new ArgsThread(
                i*cntStepsPerThread,
                (i+1)*cntStepsPerThread,
                step
            );
            
            Future<BigDecimal> d = e.submit(new СalculationsPi(a));
            list.add(d);
        }

        BigDecimal pi = new BigDecimal(0.0);

        for (Future<BigDecimal> future : list) {
            pi = pi.add(future.get());
        }
        System.out.println(pi);
        e.shutdown();
    }
}

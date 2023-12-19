package com.parallel.montekarlo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class PiCalculation {
    public static void main(String[] args) throws Exception {
        long numPoints = 999999999; // Количество точек для генерации
        int numThreads = Runtime.getRuntime().availableProcessors() - 1; // Количество доступных ядер процессора
        int taskN = 100000;
        List<Future<Long>> list = new ArrayList<>();
        ExecutorService ex = Executors.newFixedThreadPool(numThreads);

        

        for (int i = 0; i < taskN; i++) {
            
            list.add(ex.submit(new PointCounter(numPoints / taskN)));
        }

        

        // Суммирование результата всех потоков
        BigDecimal totalPointsInsideCircle = new BigDecimal(0);
        for (var i :list) {
             totalPointsInsideCircle = totalPointsInsideCircle.add(new BigDecimal(i.get()));
        }

        final BigDecimal bigDecimal = new BigDecimal(4);
        BigDecimal pi = bigDecimal
                .multiply(totalPointsInsideCircle
                    .divide(BigDecimal
                        .valueOf(numPoints)
                            ,MathContext.DECIMAL128),
                                    MathContext.DECIMAL128);
        System.out.println("Pi: " + pi);
        ex.shutdown();
    }
}

class PointCounter implements Callable<Long> {
    private long numPoints; // Количество точек для генерации

    public PointCounter(long numPoints) {
        this.numPoints = numPoints;
    }


    @Override
    public Long call() throws Exception {
        long pointsInsideCircle = 0;

        for (long i = 0; i < numPoints; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            if (x * x + y * y <= 1) {
                pointsInsideCircle++;
            }
        }
        return pointsInsideCircle;
    }
}


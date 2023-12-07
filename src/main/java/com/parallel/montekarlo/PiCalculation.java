package com.parallel.montekarlo;

import java.util.concurrent.ThreadLocalRandom;

public class PiCalculation {
    public static void main(String[] args) throws InterruptedException {
        long numPoints = 500000000; // Количество точек для генерации
        int numThreads = Runtime.getRuntime().availableProcessors()*10; // Количество доступных ядер процессора
        System.out.println(numThreads);
        PointCounter[] counters = new PointCounter[numThreads];
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            counters[i] = new PointCounter(numPoints / numThreads);
            threads[i] = new Thread(counters[i]);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            threads[i].join(); // Ожидание завершения всех потоков
        }

        // Суммирование результата всех потоков
        long totalPointsInsideCircle = 0;
        for (int i = 0; i < numThreads; i++) {
            totalPointsInsideCircle += counters[i].getPointsInsideCircle();
        }

        double pi = 4.0 * totalPointsInsideCircle / numPoints;
        System.out.println("Pi: " + pi);
    }
}

class PointCounter implements Runnable {
    private long numPoints; // Количество точек для генерации
    private long pointsInsideCircle; // Количество точек, попавших в окружность

    public PointCounter(long numPoints) {
        this.numPoints = numPoints;
    }

    public long getPointsInsideCircle() {
        return pointsInsideCircle;
    }

    @Override
    public void run() {
        pointsInsideCircle = 0;

        for (long i = 0; i < numPoints; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            if (x * x + y * y <= 1) {
                pointsInsideCircle++;
            }
        }
    }
}


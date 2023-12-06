package com.parallel;

/**
 * Hello world!
 *
 */
public class AppСonsistently 
{
    public static void main( String[] args )
    {
        double pi = 0.0;
        int n = 10000000;
        for (int i = 0; i < n; i++) {
            pi += 1.0/(i*4.0 + 1.0); // положительные члены
            pi -= 1.0/(i*4.0 + 3.0); // отрицательные члены
        }
        pi = pi * 4.0;
        System.out.printf("Результат ПИ = %.15g \n", pi); 
    }
}

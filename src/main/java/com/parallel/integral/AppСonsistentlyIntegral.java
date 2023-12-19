package com.parallel.integral;

/**
 * AppСonsistentlyIntegral
 */
public class AppСonsistentlyIntegral {

    public static void main(String[] args) {
        long cntSteps = 10000;
        double step = 1./cntSteps;
        double pi = 0;
        double sum = 0.0; 
        double x = 0;        
       
        for (long i=0; i<cntSteps; i++)
        {
            x = (i + .5)*step;
            sum = sum + 4.0/(1.+ x*x);
        }
        pi = sum*step;

        System.out.printf("%.15g \n", pi);
        // System.out.println(Math.abs(Math.PI - pi));
    }

}
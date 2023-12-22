package com.parallel.integral;


public class ArgsThread {
    private final long left;
    private final long right;
    private final double step;
    
    public long getLeft() {
        return left;
    }

    public long getRight() {
        return right;
    }

    public double getStep() {
        return step;
    }

    public ArgsThread(long left, long right, double step) {
        this.left = left;
        this.right = right;
        this.step = step;
    }
   
}

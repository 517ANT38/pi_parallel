package com.parallel.integral;

import java.math.BigDecimal;

public class ArgsThread {
    private long left;
    private long right;
    private BigDecimal step;
    
    public ArgsThread() {
    }

    public ArgsThread(long left, long right, BigDecimal step) {
        this.left = left;
        this.right = right;
        this.step = step;
    }

    public long getLeft() {
        return left;
    }

    public void setLeft(long left) {
        this.left = left;
    }

    public long getRight() {
        return right;
    }

    public void setRight(long right) {
        this.right = right;
    }

    public BigDecimal getStep() {
        return step;
    }

    public void setStep(BigDecimal step) {
        this.step = step;
    }
    

    
}

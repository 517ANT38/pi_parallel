package com.parallel.ChudnovskyAlgorithm;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * CalsPi
 */
public class CalsPi {

    private final ExecutorService ex;
   
   
    public CalsPi(ExecutorService ex) {
        this.ex = ex;
    }

    public BigDecimal cals(int count) throws InterruptedException, ExecutionException{
        int k = Math.round(count*0.01f); 
        int s = 0;
        BigDecimal bigDecimal = new BigDecimal(0);

        List<Future<BigDecimal>> fList = new ArrayList<>();

        for (int i = k; i < count; i+=k) {
        
            fList.add(ex.submit(new CallHelpPi(s, i,count)));
            s = i;
            
            if(Math.round(k*0.3f) != 0){
                k=Math.round(k - k*0.3f);
            }
        }
        for (Future<BigDecimal> future : fList) {
            bigDecimal = bigDecimal.add(future.get());
        }

        return bigDecimal;
    }

    public void shutdown(){
        ex.shutdown();
    }
    
}
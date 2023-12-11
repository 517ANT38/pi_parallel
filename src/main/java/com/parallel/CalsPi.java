package com.parallel;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class CalsPi {
    private final ExecutorService ex;



    public CalsPi(ExecutorService ex) {
        this.ex = ex;
    }


    public BigDecimal cals(int count, int eps) throws InterruptedException, ExecutionException{
        
        List<Future<BigDecimal>> fList = new ArrayList<>(); 
        int pc = Math.round(count * 0.5f);        
        BigDecimal bg = new BigDecimal(0.0);

        int s = 0;
        int k = Math.round(count * 0.0001f);
        int j = 0;

        for (int i = k; i < count; i += k) {
        
            
            fList.add(ex.submit(new HelpCallPi(eps, s, i)));        
            System.out.printf("%d %d\n",s,i);
            s=i;
            j++;
            
            var d = Math.round((count - i)*0.1f);
            
            if(j == 3 && k > d){
                k = k - Math.round(k*0.03f);
                j = 0;
            }
                       
           

        }
        for (int i = s+1; i < count; i++) {
            fList.add(ex.submit(new HelpCallPi(eps, s, i)));
            s=i;
        }
        for(Future<BigDecimal> ff : fList){
            bg = bg.add(ff.get());
        }
        return bg;
    }

   


    

    public void shutdown(){
        ex.shutdown();
    }

    public List<Runnable> shutdownNow(){
        return ex.shutdownNow();
    } 
}

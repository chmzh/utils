package com.cmz.concur.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;

public class ThenCombine {
	public static void main(String[] args) {

	    ExecutorService executor = Executors.newFixedThreadPool(5);  
	    CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {  
	        return "zero";  
	    }, executor);  
	    CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {  
	        return "hello";  
	    }, executor);  
	      
	    CompletableFuture<String> reslutFuture =  
	            f1.thenCombine(f2, new BiFunction<String, String, String>() {  
	      
	                @Override  
	                public String apply(String t, String u) {  
	                    return t.concat(u);  
	                }  
	            });  
	      
	    try {
			System.out.println(reslutFuture.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//zerohello  
	}
}

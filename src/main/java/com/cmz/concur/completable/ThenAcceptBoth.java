package com.cmz.concur.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public class ThenAcceptBoth {
	public static void main(String[] args) {

	    ExecutorService executor = Executors.newFixedThreadPool(5);  
	    CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {  
	        try {  
	            TimeUnit.SECONDS.sleep(1);  
	        } catch (Exception e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return "zero";  
	    }, executor);  
	    CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {  
	        try {  
	            TimeUnit.SECONDS.sleep(3);  
	        } catch (Exception e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return "hello";  
	    }, executor);  
	      
	    CompletableFuture<Void> reslutFuture = f1.thenAcceptBoth(f2, new BiConsumer<String, String>() {  
	    	 		@Override  
		        public void accept(String t, String u) {  
		            System.out.println(t + " over");  
		            System.out.println(u + " over");  
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
		}  
	}
}

package com.cmz.concur.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ThenAccept {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
			@Override
			public String get() {
				try {
					TimeUnit.SECONDS.sleep(1);
					System.out.println(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "hello";
			}
		}, executor);
		//计算线程不同线程 如果要在同一个线程完成，则调用 thenAccept
		resultCompletableFuture.thenAcceptAsync(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
				System.out.println(Thread.currentThread().getName());
			}
		}, executor);
		System.out.println(123);
	}
}

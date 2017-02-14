package com.cmz.concur.completable;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FailAfter {
	public static void main(String[] args) {
		CompletableFuture<String> responseFuture = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("over");
			return "hello world!";
		});
		failAfter(Duration.ofSeconds(2)).acceptEither(responseFuture, (x) -> {
			System.out.println("responseFuture is over successed! the response is " + x);

		}).exceptionally(throwable -> { // exceptionally必须在最后
			System.out.println("responseFuture is not over on time!");
			return null;
		});
	}

	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public static <T> CompletableFuture<T> failAfter(Duration duration) {
		final CompletableFuture<T> promise = new CompletableFuture<>();
		scheduler.schedule(() -> {
			final TimeoutException ex = new TimeoutException("Timeout after " + duration);
			return promise.completeExceptionally(ex);
		}, duration.toMillis(), TimeUnit.MILLISECONDS);
		return promise;
	}
}

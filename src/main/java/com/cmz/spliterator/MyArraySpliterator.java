package com.cmz.spliterator;

import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MyArraySpliterator implements Spliterator<Long> {

	private final long[] numbers;
	private int currentPosition, endPosition;

	public MyArraySpliterator(long[] numbers) {
		this(numbers, 0, numbers.length);
	}

	public MyArraySpliterator(long[] numbers, int start, int end) {
		this.numbers = numbers;
		currentPosition = start;
		endPosition = end;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Long> action) {
		if (currentPosition < endPosition) {
			action.accept(numbers[currentPosition++]);
			return true;
		}
		return false;
	}

	@Override
	public long estimateSize() {
		return endPosition - currentPosition;
	}

	@Override
	public int characteristics() {
		return ORDERED | NONNULL | SIZED | SUBSIZED;
	}

	@Override
	public void forEachRemaining(Consumer<? super Long> action) {
		int pos = currentPosition, end = endPosition;
		currentPosition = end;
		for (; pos < end; pos++)
			action.accept(numbers[pos]);
	}

	@Override
	public Spliterator<Long> trySplit() {
		if (estimateSize() <= 100)
			return null;
		int middle = (endPosition + currentPosition) >>> 1;
		MyArraySpliterator prefix = new MyArraySpliterator(numbers, currentPosition, middle);
		currentPosition = middle;
		return prefix;
	}

	private static long sumValues(Stream<Long> stream) {
		Optional<Long> optional = stream.reduce((t, u) -> t + u);
		//return optional.get() != null ? optional.get() : Long.valueOf(0);  //这样调用会引起异常
		return optional.orElse(0L);
	}

	public static void main(String[] args) {

		long[] twoThousandNumbers = LongStream.rangeClosed(1, 10_000_000).toArray();

		Spliterator<Long> spliterator = new MyArraySpliterator(twoThousandNumbers);
		Stream<Long> stream = StreamSupport.stream(spliterator, false);

		System.out.println(sumValues(stream));
	}
}

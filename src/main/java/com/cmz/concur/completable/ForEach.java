package com.cmz.concur.completable;

import java.util.concurrent.CountedCompleter;

public class ForEach<E> extends CountedCompleter<Void> {

	/**
	* 
	*/
	private static final long serialVersionUID = -6499169420434451377L;

	public static <E> void forEach(E[] array, MyOperation<E> op) {
		new ForEach<E>(null, array, op, 0, array.length).invoke();
	}

	final E[] array;
	final MyOperation<E> op;
	final int lo, hi;

	ForEach(CountedCompleter<?> p, E[] array, MyOperation<E> op, int lo, int hi) {
		super(p);
		this.array = array;
		this.op = op;
		this.lo = lo;
		this.hi = hi;
	}

	public void compute() { // version 1
		if (hi - lo >= 2) {
			int mid = (lo + hi) >>> 1;
			setPendingCount(2); // must set pending count before fork
			new ForEach<E>(this, array, op, mid, hi).fork(); // right child
			new ForEach<E>(this, array, op, lo, mid).fork(); // left child
		} else if (hi > lo)
			op.apply(array[lo]);
		tryComplete();
	}
	
	public static void main(String[] args) {
		Integer[] array = {1,2,3,4,5};
		ForEach<Integer> forEach = new ForEach<Integer>(null, array, new MyOperation<Integer>(), 0, 5);
		forEach.compute();
	}
}

class MyOperation<E> {
	void apply(E e) {
		System.out.println(e);
	}
}
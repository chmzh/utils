package com.cmz.spliterator;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ArraysTest {
	public static void main(String[] args) {
		double[] array = {1,2,3,4,5};
		DoubleStream stream = StreamSupport.doubleStream(Arrays.spliterator(array), false);
		stream.map((d)->{return d*d;}).forEach((d)->System.out.println(d));
		
	}
}

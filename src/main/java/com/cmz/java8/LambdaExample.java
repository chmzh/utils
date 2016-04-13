package com.cmz.java8;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

public class LambdaExample {
	public static void main(String[] args) {
		List<String> names = new ArrayList<>();

		names.add("TaoBao");

		names.add("TaoBaO");

		List<String> lowercaseNames = FluentIterable.from(names).transform(new Function<String,String>() {

		  @Override
		  public String apply(String name) {

		    return name.toLowerCase();

		  }

		}).toList();
		
		System.out.println(lowercaseNames.toArray().toString());

	}
}

package com.cmz.concur.completable;

import java.util.concurrent.CountedCompleter;

public class Main{
	public static void main(String[] args) {
		SendPacker sendPacker = new SendPacker();
		new HeaderBuilder(sendPacker).fork();
		new BodyBuilder(sendPacker).fork();
	}
}


class HeaderBuilder extends CountedCompleter<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2552453106104631161L;

	public HeaderBuilder(CountedCompleter<String> caller) {
		super(caller,1);
	}
	
	@Override
	public void compute() {
		try {
			Thread.sleep(2000);
			System.out.println("HeaderBuilder Completer!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class BodyBuilder extends CountedCompleter<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1058510991086464956L;

	public BodyBuilder(CountedCompleter<String> caller) {
		super(caller,1);
	}
	
	@Override
	public void compute() {
		try {
			Thread.sleep(2000);
			System.out.println("BodyBuilder Completer!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class SendPacker extends CountedCompleter<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6439614192565192268L;

	@Override
	public void compute() {
		try {
			Thread.sleep(2000);
			System.out.println("SendPacker Completer!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SendPacker() {
		super(null,1);
	}
	
	@Override
	public void onCompletion(CountedCompleter<?> caller) {
		System.out.println(caller);
	}
}

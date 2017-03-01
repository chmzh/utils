package com.cmz.concur.completable;

import java.util.concurrent.CountedCompleter;

public class Main{
	public static void main(String[] args) {
		SendPacker sendPacker = new SendPacker();
		new HeaderBuilder(sendPacker).fork();
		new BodyBuilder(sendPacker).fork();
		sendPacker.invoke();
		
	}
}


class HeaderBuilder extends CountedCompleter<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2552453106104631161L;

	public HeaderBuilder(CountedCompleter<String> caller) {
		super(caller);
	}
	
	@Override
	public void compute() {

			System.out.println("HeaderBuilder Completer!");
		
	}

}

class BodyBuilder extends CountedCompleter<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1058510991086464956L;

	public BodyBuilder(CountedCompleter<String> caller) {
		super(caller);
	}
	
	@Override
	public void compute() {

			System.out.println("BodyBuilder Completer!");
		
	}
	
}

class SendPacker extends CountedCompleter<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6439614192565192268L;

	@Override
	public void compute() {

			System.out.println("SendPacker Completer!");
			propagateCompletion();
	}
	
	public SendPacker() {
		super(null,1);
	}
	
	@Override
	public void onCompletion(CountedCompleter<?> caller) {
		System.out.println(caller);
	}
}

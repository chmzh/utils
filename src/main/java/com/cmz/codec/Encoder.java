package com.cmz.codec;

public class Encoder {
	private byte[] Key = {'a','b','c','d','f'};
	public byte[] encode(String msg){
		
		byte[] i1 = msg.getBytes();
		byte[] o = new byte[i1.length];
		for(int i=0;i<i1.length;i++){
			o[i] = (byte) (i1[i] ^ Key[4%(i+1)]);
		}
		return o;
	}
	
	public String decode(byte[] msg){
		byte[] o = new byte[msg.length];
		for(int i=0;i<msg.length;i++){
			o[i] = (byte) (msg[i] ^ Key[4%(i+1)]);
		}
		return new String(o);
	}
	
	public static void main(String[] args) {
		Encoder encoder = new Encoder();
		byte[] msg = encoder.encode("你好啊加厚fdlkjfewij夺不回肖里你好啊加厚fdlkjfewij夺不回肖里ij夺不回肖里你");
		System.out.println(new String(msg));
		String msg1 = encoder.decode(msg);
		System.out.println(msg1);
	}
}

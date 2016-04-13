package com.cmz.utils.jedis;

public class SubClient {
	public static void main(String[] args) {
		RedisClient client = RedisClient.getInstance(); 
		final MyJedisPubSub listener = new MyJedisPubSub();  
		client.psubscribe(listener, new String[]{"hello_*"});//使用模式匹配的方式设置频道 
	}
}

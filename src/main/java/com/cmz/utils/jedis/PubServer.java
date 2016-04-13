package com.cmz.utils.jedis;

import redis.clients.jedis.Jedis;

public class PubServer {
	public static void main(String[] args) {
		RedisClient client = RedisClient.getInstance(); 
 
		client.publish("hello_test", "hello watson"); 
	}
}

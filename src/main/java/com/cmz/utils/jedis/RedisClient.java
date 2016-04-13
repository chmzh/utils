package com.cmz.utils.jedis;

import redis.clients.jedis.JedisPubSub;


public class RedisClient {
	
	private final static RedisClient instance = new RedisClient();
	
	private RedisService service;
	
	private RedisClient() {
		service = new RedisService();
	}
	
	public static  RedisClient getInstance(){
		return instance;
	}
	
	//发布消息
	public void publish(String channel,String message){
		service.getConnection().publish(channel, message);
	}
	//订阅得到信息在lister的onMessage(...)方法中进行处理
	public void subscribe(JedisPubSub lister,String... channels){
		
	}
	//这里启动了订阅监听，线程将在这里被阻塞  
	//订阅得到信息在lister的onPMessage(...)方法中进行处理  
	public void psubscribe(JedisPubSub lister,String[] channels){
		service.getConnection().psubscribe(lister, channels);
	}
	
}

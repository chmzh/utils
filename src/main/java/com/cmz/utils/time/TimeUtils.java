package com.cmz.utils.time;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class TimeUtils {
	
	private final static ZoneId zoneId = ZoneId.of("UTC+8");
	private final static Clock clock = Clock.system(zoneId);
	
	public static void main(String[] args) {
		System.out.println(toDateTime(timestamps()));
	}
	
	/**
	 * 
	 * @param zone UTC+8
	 * @return
	 */
	public static int timestamps(){
		return (int)(clock.millis() * 0.001);
	}
	
	public static String toDateTime(int second){
		int timestamp = timestamps();
		Instant instant = Instant.ofEpochSecond(timestamp);
		ZonedDateTime zonedDateTime = instant.atZone(zoneId);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		return zonedDateTime.format(format);
	}
	
	
}

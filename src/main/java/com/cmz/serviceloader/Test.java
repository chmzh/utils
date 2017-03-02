package com.cmz.serviceloader;

import java.util.ServiceLoader;

public class Test {
	public static void main(String[] args) {  
        //need to define related class full name in /META-INF/services/....  
//        ServiceLoader<IService> serviceLoader = ServiceLoader  
//                .load(IService.class);  
//        for (IService service : serviceLoader) {  
//            System.out.println(service.getScheme()+"="+service.sayHello());  
//        }  
		
		String[] aStrings = "10,30,50,100,200,500,1000,2000,5000".split(",");
		for (int i = 0; i < aStrings.length; i++) {
			System.out.println(aStrings[i]);
		}
    }  
}

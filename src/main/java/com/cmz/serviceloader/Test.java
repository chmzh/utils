package com.cmz.serviceloader;

import java.util.ServiceLoader;

public class Test {
	public static void main(String[] args) {  
        //need to define related class full name in /META-INF/services/....  
        ServiceLoader<IService> serviceLoader = ServiceLoader  
                .load(IService.class);  
        for (IService service : serviceLoader) {  
            System.out.println(service.getScheme()+"="+service.sayHello());  
        }  
    }  
}

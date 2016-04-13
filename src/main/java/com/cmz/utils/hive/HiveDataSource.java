package com.cmz.utils.hive;

import java.util.Map;

import com.cmz.utils.PropertiesUtil;

public class HiveDataSource extends BaseDataSource {

	static String JDBCDriver = "com.cloudera.hive.jdbc41.HS2Driver";
	private static HiveDataSource instance = new HiveDataSource();
	private static final String CONNECTION_URL = "jdbc:hive2://${host}/;auth=noSasl";
	public static HiveDataSource getInstance(){
		if(DS == null){
			Map<String, String> conf = PropertiesUtil.getConf("comm");
    		String connectURI = CONNECTION_URL.replace("${host}", conf.get("impala"));
    		String username = "";
    		String pswd = "";
    		//String pswd = "";
    		String driverClass = JDBCDriver;
    		int initialSize = 20;
    		int maxtotal = 30;
    		int maxIdle = 30;
    		int maxWaitMillis = 1000;
    		int minIdle = 10;
    		initDS(connectURI, username, pswd, driverClass, initialSize, maxtotal, maxIdle, maxWaitMillis, minIdle);
    	}
    	return instance;
	}
	
	private HiveDataSource(){
		
	}
	
}

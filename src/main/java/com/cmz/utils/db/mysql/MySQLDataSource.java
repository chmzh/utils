package com.cmz.utils.db.mysql;


import java.sql.Connection;

/**
 * 数据库连接池
 * @author mingzhou.chen
 * 28458942@qq.com
 * 2016年1月4日 下午3:24:12
 */
public final class MySQLDataSource extends BaseDataSource {
	
	private static MySQLDataSource instance = new MySQLDataSource();
	
    public static MySQLDataSource getInstance(){
    	if(DS == null){
    		//Map<String, String> conf = PropertiesUtil.getConf("comm");
    		String host = "127.0.0.1";//conf.get("mysql_host");
    		String port = "3306";//conf.get("mysql_port");
    		String connectURI = "jdbc:mysql://"+host+":"+port+"/";
    		String username = "jstorm";//conf.get("mysql_username");
    		String pswd = "123456";//conf.get("mysql_pwd");
    		
    		
    		//String pswd = "";
    		String driverClass = "com.mysql.jdbc.Driver";
    		int initialSize = 20;
    		int maxtotal = 50;
    		int maxIdle = 30;
    		int maxWaitMillis = 1000;
    		int minIdle = 10;
    		initDS(connectURI, username, pswd, driverClass, initialSize, maxtotal, maxIdle, maxWaitMillis, minIdle);
    	}
    	return instance;
    }
    
    private MySQLDataSource(){
    	
    }

    
    
    public static void main(String[] args) {  
    	
    	MySQLDataSource db = MySQLDataSource.getInstance();
    	Connection connection = db.getConn();
    	
    
 
    } 
}

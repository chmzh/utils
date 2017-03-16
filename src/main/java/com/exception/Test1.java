package com.exception;

public class Test1 implements AutoCloseable {
	
	public static void main(String[] args) {
		
		
		
		int i=-2;
		assert i>0 : "错误";
		System.out.println("abc3");
		try {
			method();
		}
		catch (ArithmeticException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e){
			
		}
		
		try(Test1 test1 = new Test1()){
			throw new Exception();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	public static void method(){
		//int i = 5/0;
		throw new ArithmeticException("abc");
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("close");
	}
}

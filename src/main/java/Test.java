import java.util.ArrayList;
import java.util.List;

import com.annotation.Version;


public class Test {
	private volatile Obj obj;
	public static void main(String[] args) {
		int l = 1024*1024*50;
		byte[] m = new byte[l];
		
		List<Integer> list = new ArrayList<>();
		list.parallelStream();
		
		Test test = new Test();
		Obj obj1 = null;
		test.obj = obj1;
		obj1 = new Obj();
		if(test.obj==null){
			System.out.println("null");
		}else{
			System.out.println("not null");
		}
	}
}

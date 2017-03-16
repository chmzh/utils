

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */

public class App {
	public static void main(String[] args) {
		// FileSystemXmlApplicationContext context = new
		// FileSystemXmlApplicationContext("application.xml");
		// TestController testController =
		// (TestController)context.getBean(TestController.class);
		// //System.out.println(testController);
		// testController.println("");
		/*
		String myString = "abcvd\\r\\n dfkjjkj\\r\\n \r";
		Pattern CRLF = Pattern.compile("(\\\\r\\\\n)");
		Matcher m = CRLF.matcher(myString);
		System.out.println(System.getProperty("abc"));
		if (m.find()) {
			String newString = m.replaceAll("<br>");
			System.out.println(newString);
		}
		*/
		
		Integer num = new Integer(1);
		Integer num1 = new Integer(1);
		System.out.println("abc".intern() == "abc".intern());
		
		Object obj1 = new Object();
		Object obj2 = obj1;
		obj1 = null;
		System.out.println(obj2);
	}
}

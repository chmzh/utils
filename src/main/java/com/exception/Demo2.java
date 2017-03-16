package com.exception;

import java.io.IOException;

class Demo {
    void show() throws ClassNotFoundException,IOException{}
    }
public class Demo2 extends Demo {
    void show()
    {
System.out.println("In Demo1 Show");
}
public static void main(String ar[]) { try{
    Demo2 d = new Demo2();
    d.show();
    }
  catch(Exception e){}
    } }

package com.exception;

class AssertDemo {
    static void check(int i)
      {
           assert i> 0: "Value must be positive";
           System.out.println("value fine "+i);
      }
      public static void main(String args[])
      {
      check(Integer.parseInt(args[0]));
      }}


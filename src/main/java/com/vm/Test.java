package com.vm;

import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Properties;

import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.tools.SysPropsDumper;
import sun.jvm.hotspot.tools.Tool;

public class Test extends Tool {

	public void run() {
		Properties sysProps = VM.getVM().getSystemProperties();
		VM vm = VM.getVM();
		PrintStream out = System.out;
		if (sysProps != null) {
			Enumeration keys = sysProps.keys();
			while (keys.hasMoreElements()) {
				Object key = keys.nextElement();
				out.print(key);
				out.print(" = ");
				out.println(sysProps.get(key));
			}
		} else {
			out.println("System Properties info not available!");
		}
	}

	public static void main(String[] args) {
		SysPropsDumper pd = new SysPropsDumper();
		pd.start();
		pd.stop();
	}

}

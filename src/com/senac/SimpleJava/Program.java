package com.senac.SimpleJava;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The Program class will execute another class and change the
 * entry point of a Java program from the main() static method
 * to a run() instance method.
 * 
 * For easier use, it should be used along with the SimpleJava
 * launcher, currently only available for the Eclipse IDE.
 */

final class Program {

	public static void main(String[] args)
	{
		//System.err.println("Running:" + args[0]);
		try {
			Class<?> cls = Class.forName(args[0]);
			Method method = cls.getMethod("run");
			method.invoke(cls.newInstance());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.err.println("Method 'run()' not found.");
			System.err.println("Are you sure you wanted to run class '"+
			                    args[0]+"'?");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.getTargetException().printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
}

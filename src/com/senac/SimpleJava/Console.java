package com.senac.SimpleJava;

import java.util.Scanner;

/**
 * Abstracts the operations on a console for text based applications.
 */
public final class Console {

	private static final Scanner keyboard = new Scanner(System.in);

    private static
    StringBuilder createStringFromObjectList(Object... args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
	    		Object o = args[i];
	    		if (o == null)
	    			sb.append("(null)");
	    		else
	    			sb.append (args[i].toString());
	    }
        return sb;
    }

	/**
	 * Print the string representations of all arguments to the
	 * standard output.
	 * @param arguments The list of arguments.
	 */
	public static void print(Object... arguments) {
		System.out.print(createStringFromObjectList(arguments));
	}
	
	/**
	 * Print the string representations of all arguments to the
	 * standard output and add a 'new line' at the end.
	 * @param arguments The list of arguments.
	 */
	public static void println(Object... arguments) {
		System.out.println(createStringFromObjectList(arguments));
	}

	/**
	 * Prints a newline character on the standard output.
	 */
	public static void println() {
		System.out.println();
	}

	/**
	 * Prompts for  a line of text from the standard input and returns
	 * it as a single string.
	 * @return The text from the line read as input.
	 */
	public static String readLine(String prompt) {
		System.out.print(prompt);
		keyboard.skip("[\r\n]*");
		return keyboard.nextLine();
	}
	
	/**
	 * Reads a line of text from the standard input and returns
	 * it as a single string.
	 * @return The text from the line read as input.
	 */
	public static String readLine() {
		keyboard.skip("[\r\n]*");
		return keyboard.nextLine();
	}
	
	/**
	 * Prompts for a string from the standard input and returns it.
	 * The string returned will be limited by any of TAB, space, new
	 * line or carriage return characters.
	 * @param prompt The prompt to show.
	 * @return The string read as input.
	 */
	public static String readString(String prompt) {
		System.out.print(prompt);
		return keyboard.next();
	}
	
	/**
	 * Reads a string from the standard input and returns it.
	 * The string returned will be limited by any of TAB, space, new
	 * line or carriage return characters.
	 * @return The string read as input.
	 */
	public static String readString() {
		return keyboard.next();
	}
	
	/**
	 * Prompts for an integer number from the standard input.
	 * @param prompt The prompt to show.
	 * @return The integer read as input.
	 */
	public static int readInt(String prompt) {
		System.out.print(prompt);
		return keyboard.nextInt();
	}

	/**
	 * Reads an integer number from the standard input.
	 * @return The integer read as input.
	 */
	public static int readInt() {
		return keyboard.nextInt();
	}

	/**
	 * Prompts for a real number from the standard input and returns it.
	 * @param prompt The prompt to show.
	 * @return The double read as input.
	 */
	public static double readDouble(String prompt) {
		System.out.print(prompt);
		return keyboard.nextDouble();
	}

	/**
	 * Reads a real number from the standard input and returns it.
	 * @return The double read as input.
	 */
	public static double readDouble() {
		return keyboard.nextDouble();
	}
}

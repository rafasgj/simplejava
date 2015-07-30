package com.senac.SimpleJava.examples;
import com.senac.SimpleJava.Console;

public class HelloYou {

	public String leNome() {
		return Console.readLine("Seu nome:");
	}
	
	public void run() {
		Console.println("Hello, " + leNome());
	}
	
}

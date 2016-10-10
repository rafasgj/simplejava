package com.senac.SimpleJava.Audio;

public class AudioException extends Exception {
	private static final long serialVersionUID = -5234693632388686616L;

	public AudioException(String error, String message) {
		super(error+"\n("+message+")");
	}
}

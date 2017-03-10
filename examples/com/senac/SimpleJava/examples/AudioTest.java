package com.senac.SimpleJava.examples;

import com.senac.SimpleJava.Console;
import com.senac.SimpleJava.Audio.AudioClip;
import com.senac.SimpleJava.Audio.AudioException;

public class AudioTest {
	private boolean running = true;
	public void run() throws AudioException {
		Console.println("Playing sound in the background.");
		try {
			AudioClip.playSound("audio/level-start.wav", () -> running = false );
		} catch (AudioException e) {
			e.printStackTrace();
		}
		while (running) {
			Console.println("Doing some work");
			// This will only waste CPU time.
			for (int i = 0; i < Integer.MAX_VALUE>>2; i++)
			{ i <<= 1; i++; i--; i >>=1; }
		}
		Console.println("Done working and playing sound.");
		
		Console.println("Playing again, but waiting for it to finish.");
		try {
			AudioClip.playSound("audio/level-start.wav");
		} catch (AudioException e) {
			e.printStackTrace();
		}
		Console.println("Program finished.");
	}
}

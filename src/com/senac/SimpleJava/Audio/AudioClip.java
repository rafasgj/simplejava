package com.senac.SimpleJava.Audio;

import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.LineEvent.Type;

import com.senac.SimpleJava.Action;

/**
 * This class adds support for playing sounds in the background,
 * or playing a sound and waiting for it to finhish playing.
 */
public class AudioClip {

	private static class AudioListener implements LineListener {
		private Action action;
		private boolean done;
		
		public AudioListener(Action action) {
			this.action = action;
			this.done = false;
		}
		
		@Override
		public synchronized void update(LineEvent event) {
			Type eventType = event.getType();
			if (eventType == Type.STOP || eventType == Type.CLOSE) {
				if (action != null)
					action.execute();
				event.getLine().close();
				done = true;
				notifyAll();
			}
		}
		
		public synchronized void waitToFinish() {
			while (!done) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Plays a sound clip and waits for it to finish.
	 * @param filename The path to the filename to be played.
	 * @throws AudioException An AudioExption in thrown if the file
	 * can't be found, opened, or if an error in the sound subsystem
	 * occurs.
	 */
	public static void playSound(String filename) throws AudioException {
		playSound(filename, null);
	}

	/**
	 * Plays a sound clip in the background and execute the action
	 * provided when the sound finishes execution. If the action
	 * objectis 'null', then the method blocks and only returns
	 * after the sound clip finishes.
	 * @param filename The path to the filename to be played.
	 * @throws AudioException An AudioExption in thrown if the file
	 * can't be found, opened, or if an error in the sound subsystem
	 * occurs.
	 */
	public static void playSound(String filename, Action whenFinished) throws AudioException {
		AudioInputStream audioInputStream;
		AudioListener listener;
		Clip clip;
		
		File file = new File(filename);
		try {
			listener = new AudioListener(whenFinished);
			audioInputStream = AudioSystem.getAudioInputStream(file);
		    clip = AudioSystem.getClip();
		    clip.addLineListener(listener);
		    clip.open(audioInputStream);
		    clip.start();
		    if (whenFinished == null)
		    	listener.waitToFinish();
		} catch (UnsupportedAudioFileException e) {
			throw new AudioException("Audio format not recognized.", e.getMessage());
		} catch (IOException e) {
			throw new AudioException("Failed to read data.", e.getMessage());
		} catch (LineUnavailableException e) {
			throw new AudioException("Failed to open sound subsystem.", e.getMessage());
		}
	}	
}


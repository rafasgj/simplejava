package com.senac.SimpleJava.Graphics;

import javax.swing.JFrame;

/**
 * This is the base class for all GraphicApplication (a.k.a. games) for
 * the SimpleJava framework.
 * All subclasses must implement methods setup(), step() and draw(), and
 * should implement the method cleanup().
 * These applications run as an infinite loop, and the client will
 * implement the parts required to execute the actual behavior through
 * the hook methods.
 */
public abstract class GraphicApplication {
	private boolean keepRunning;
	private int sleepTime;
	
	private Canvas canvas;
	private JFrame win;
	
	/**
	 * Initializes the underlying application engine, the drawing canvas,
	 * and display the main window.
	 */
	protected GraphicApplication() {
		keepRunning = true;
		sleepTime = 0;
		win = new JFrame("Simple Java");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new Canvas(Resolution.LOWRES);
		win.add(canvas);
		win.pack();
		win.setVisible(true);
	}
	
	/**
	 * Set the window title.
	 * @param title The new window title.
	 */
	protected void setTitle(String title) {
		win.setTitle(title);
	}
	
	/**
	 * Set the maximum number of frames per second that the application
	 * will have. This will force the frequency of hook methods to be
	 * controlled.
	 * @param fps The maximum number of frames per second (0 to disable).
	 */
	protected void setFramesPerSecond(int fps) {
		if (fps < 1)
			sleepTime = 0;
		else
			sleepTime = 1000/fps;
	}
	
	// Pauses the application for a small amount of time.
	private void sleepLoop(long milis) {
		if (milis > 0) {
			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method is required to comply with the SimpleJava
	 * application framework. It is the application entry point, and
	 * define the application loop.
	 */
	public void run() {
		try {
			setup();
			while (keepRunning) {
				long s = System.currentTimeMillis(), e;
				step();
				draw(canvas);
				win.repaint();
				e = System.currentTimeMillis();
				sleepLoop(sleepTime - (e-s));
			}
			cleanup();
			win.dispose();
		} catch(Exception e) {
			System.err.println("Unhandled Exception forced termination.");
			if (win != null)
				win.dispose();
			e.printStackTrace(System.err);
			System.exit(-1);
		}
	}

	/**
	 * Call this method to set the main loop to terminate as soon as
	 * possible.
	 */
	protected void endLoop() {
		keepRunning = false;
	}
	
	/**
	 * Set the application screen resolution.
	 * @param res The new screen resolution.
	 */
	protected void setResolution(Resolution res) {
		canvas.setResolution(res);
	}

	/**
	 * Retrieves the current screen resolution.
	 * @return The current screen resolution.
	 */
	protected Resolution getResolution() {
		return canvas.getResolution();
	}

	/**
	 * Bind a key to a KeyboardAction. The Key is a string representing
	 * the keyboard key to connect the action, for example, "SPACE". The
	 * action is a KeyboardAction object that will be executed when the
	 * key is pressed.
	 * The actual binding is done by the Application Canvas.
	 * @param key The key to bind the action to.
	 * @param action The KeyboardAction object that will respond to the
	 * 				event.
	 */
	public void bindKey(String key, KeyboardAction action) {
		canvas.bindKey(key, action);
	}
	
	/**
	 * This method is called before the main loop begins and should be
	 * used to setup the application.
	 */
	protected abstract void setup();
	/**
	 * This method is called in the start of the loop, and should be used
	 * to control all the data of the application.
	 */
	protected abstract void step();
	/**
	 * This method is called just before the screen is redrawn, and
	 * should be used to draw the application canvas.
	 * @param canvas The canvas used to draw elements.
	 */
	protected abstract void draw(Canvas canvas);
	/**
	 * This method is called just before the application terminates and
	 * is the last chance to recycle used resources.
	 */
	protected void cleanup() {}
}

package com.senac.SimpleJava;

public abstract
class EventDrivenApplication
{
	private boolean keepRunning;

	/**
	 * 
	 */
	protected EventDrivenApplication() {
		keepRunning = true;
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
				loop();
			}
			cleanup();
		} catch(Exception e) {
			System.err.println("Unhandled Exception forced termination.");
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
	 * Queries if the application main loop should be kept running.
	 * @return true if the main loop should iterate, false otherwise.
	 */
	protected boolean shouldRun() {
		return keepRunning;
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
	protected abstract void loop();
	/**
	 * This method is called just before the application terminates and
	 * is the last chance to recycle used resources.
	 */
	protected void cleanup() {}
}

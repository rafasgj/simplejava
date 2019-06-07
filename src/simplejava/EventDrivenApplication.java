package simplejava;

/**
 * This is the base class for all Event Driven Applications for
 * the SimpleJava framework.
 * <p>
 * All subclasses must implement methods setup() and loop() and
 * should implement the method cleanup().
 * <p>
 * These applications run as an infinite loop, and the client will
 * implement the parts required to execute the actual behavior through
 * the hook methods.
 * <p>
 * The method setup() is called once before the loop starts. The
 * method loop() is execute everytime the loop executes another
 * iteration, and the method cleanup() after the loop finishes.
 * To finish the loop an application must call endLoop();
 */
public abstract
class EventDrivenApplication
{
	private boolean keepRunning;

	/**
	 * Initializes a new EventDrivenApplication object.
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
			while (shouldRun()) {
				loop();
			}
			cleanup();
		} catch(Exception e) {
			System.err.println("Unhandled Exception forced termination.");
			e.printStackTrace(System.err);
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

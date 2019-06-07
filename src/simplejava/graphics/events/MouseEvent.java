package simplejava.graphics.events;

/**
 * Enumeration with all mouse events that can be intercepted by
 * an application.
 */
public enum MouseEvent {
	/** Simple mouse click. */
	CLICK(0),
	/** Mouse pressed down. */
	PRESS(1),
	/** Mouse release event. */
	RELEASE(2),
	/** Mouse drag event, start or end. */
	DRAG(3),
	/** Mouse move event. */
	MOVE(4),
	/** Mouse double click event. */
	DOUBLECLICK(5);

	public final int index;

	private MouseEvent(int value) {
		this.index = value;
	}
}

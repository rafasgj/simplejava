package simplejava.graphics.events;

import simplejava.graphics.Point;

/**
 * Interface used to implement Mouse Listeners.
 */
public interface MouseObserver {
	/**
	 * Called when the mouse event has happened.
	 * @param event The mouse event that has occurred.
	 * @param button The number of the button that was pressed when the
	 * event occurred. Zero for no button pressed.
	 * @param point Coordinate on the screen where the event occurred.
	 */
	void notify(MouseEvent event, int button, Point point);
}

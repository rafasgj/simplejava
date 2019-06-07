package simplejava.graphics.events;

/**
 * Interface to implement objects that will respond to keyboard
 * typing events.
 */
public interface KeyboardTypingEvent {
	/**
	 * This method is called for every key that is typed, on
	 * every object that listens to the KeyboardTypingEvent.
	 * <p>Write this method to as efficient as possible.
	 *
	 * @param key The character typed.
	 */
	void keyTyped(char key);
}

package simplejava.graphics;

/**
 * This interface must be implemented by every object that allows
 * to be resized to a specific size.
 */
public interface Resizable {
	/**
	 * Changes the size of the object to a given size.
	 * @param width The new width.
	 * @param height The new height.
	 */
	void resize(int width, int height);
}

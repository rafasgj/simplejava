package simplejava.graphics;

/**
 * Objects that represent a rectangular area on the screen.
 */
public class Rect {
	/** The x coordinate of the rectangle, the left side. */
	public final int x;
	/** The y coordinate of the rectangle, the top side. */
	public final int y;
	/** The width of the rectangle. */
	public final int width;
	/** The height of the rectangle. */
	public final int height;
	/**
	 * Creates a new rectangle given its configuration.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param width The width.
	 * @param height The height.
	 */
	public Rect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}

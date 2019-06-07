package simplejava.graphics;

/**
 * Interface for objects than know how to draw itself in a canvas.
 */
public interface Drawable {
	/**
	 * Called when the object needs to be redrawn.
	 * @param canvas The Canvas used to draw the object.
	 */
	public void draw(Canvas canvas);
}

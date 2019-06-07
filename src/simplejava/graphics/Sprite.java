package simplejava.graphics;

import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.IOException;

/**
 * This class extends an Image to implement the behavior of a Sprite.
 * A Sprite is an image that can be positioned on a screen, and that can
 * be drawn with transparency.
 */
public class Sprite
	extends Image
	implements Drawable, Movable, Resizable
{
	private double x,y;

	private Color background;

	/**
	 * Initializes an empty sprite with a given size and a
	 * background color.
	 * @param width The sprite width.
	 * @param height The sprite height.
	 * @param background The color that will be rendered transparent.
	 */
	public Sprite(int width, int height, Color background) {
		super(width, height);
		clear(background);
	}

	/**
	 * Initializes a sprite with a given image and a background color.
	 * Note that the image data is then shared between multiple image
	 * objects (the original Image and the Sprite), and changing one
	 * will affect the other.
	 * @param img The image to use as the sprite image.
	 * @param background The color that will be rendered transparent.
	 */
	public Sprite(Image img, Color background) {
		super();
		this.x = this.y = 0;
		this.background = background;
		this.img = img.img;
	}

	/**
	 * Initializes a sprite with a given image and uses black as the
	 * background color.
	 * Note that the image data is then shared between multiple image
	 * objects (the original Image and the Sprite), and changing one
	 * will affect the other.
	 * @param img The image to use as the sprite image.
	 */
	public Sprite(Image img) {
		this(img, Color.BLACK);
	}

	/**
	 * Loads an image from a file and initializes a sprite with
	 * that image and a given background color.
	 * @param filename The path to the image file.
	 * @param background The color that will be rendered transparent.
	 * @throws IOException If the file cannot be loaded.
	 */
	public Sprite(String filename, Color background)
			throws IOException
	{
		super(filename);
		this.background = background;
	}

	/**
	 * Loads an image from a file and initializes a sprite with
	 * that image and and uses black as the background color.
	 * @param filename The path to the image file.
	 * @throws IOException If the file cannot be loaded.
	 */
	public Sprite(String filename)
			throws IOException
	{
		this(filename,Color.BLACK);
	}

	/**
	 * Move the sprite by the amount of pixels given, in both directions.
	 * @param dx The amount of pixels to move on the horizontal axis.
	 * @param dy The amount of pixels to move on the vertical axis.
	 */
	@Override
	public void move(double dx, double dy) {
		x += dx;
		y += dy;
	}

	/**
	 * Set the position to render this sprite. The origin point (0,0)
	 * is the top left of the screen.
	 * @param x The horizontal coordinate.
	 * @param y The vertical coordinate.
	 */
	public void setPosition(int x, int y) {
		setPosition(new Point(x,y));
	}

	/**
	 * Set the position to render this sprite. The origin point (0,0)
	 * is the top left of the screen. The values of the Point object
	 * will be rounded down to the nearest integer less than the value
	 * given for the coordinate.
	 * @param point A Point representing the sprite position.
	 */
	public void setPosition(Point point) {
		x = Math.floor(point.x);
		y = Math.floor(point.y);
	}

	/**
	 * Retrieve the current sprite position on screen.
	 * @return A Point representing the sprite position.
	 */
	public Point getPosition() {
		return new Point(x,y);
	}

	/**
	 * Retrieves the Color used as background. This color will be
	 * rendered transparently when the Sprite is drawn.
	 * @return The current background color.
	 */
	public Color getBackground() {
		return background;
	}

	/**
	 * Define the Color to be used as background. This color will be
	 * rendered transparently when the Sprite is drawn.
	 * @param color The new background color.
	 */
	public void setBackrgound(Color color) {
		this.background = color;
	}

	/**
	 * Draw the sprite an a Canvas. This method will correctly draw
	 * the Sprite in its current location, with the current background
	 * color drawn transparently.
	 * @param canvas The Canvas to draw the Sprite.
	 */
	@Override
	public void draw(Canvas canvas) {
		int ix = (int)Math.floor(x);
		int iy = (int)Math.floor(y);
		canvas.drawImage(prepareImage(), ix, iy);
	}

	// Create an image with an alpha channel based on the background
	// color.
	private java.awt.Image prepareImage() {
		final int tmask;
		if (background != null)
			tmask = background.asInt();
		else
			tmask = -1;
		ImageFilter filter = new RGBImageFilter() {
			int mask = 0x00ffffff;
			@Override
			public int filterRGB(int x, int y, int rgb) {
				if (tmask>=0 && (rgb & mask) == tmask)
					return 0;
				else
					return (rgb | 0xFF000000);
			}
		};
		ImageProducer ip;
		ip = new FilteredImageSource(img.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}

	/**
	 * Retrieve the current position and size of this Sprite.
	 * @return A Rect with the position (x,y) and size (width,height).
	 */
	public Rect getBounds() {
		int ix = (int)Math.floor(x);
		int iy = (int)Math.floor(y);
		return new Rect(ix, iy, img.getWidth(), img.getHeight());
	}
}

package simplejava.graphics;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import simplejava.graphics.events.KeyboardAction;

/**
 * Implements a Canvas where objects are drawn to.
 */
public class Canvas
	extends JComponent
{
	/** The screen width */
	public static final int SCREEN_WIDTH = 800;
	/** The screen height */
	public static final int SCREEN_HEIGHT = 600;

	// The underlying canvas image.
	private BufferedImage img;
	// The "client" resolution.
	private Resolution res;
	// The canvas foreground color.
	private Color fg;
	// The canvas background color.
	private Color bg;

	// Required for JComponent serialization.
	private static final long serialVersionUID = 4100468775042151138L;

	/**
	 * Initializes a new Canvas given a resolution configuration.
	 *
	 * @param res The client screen configuration.
	 */
	public Canvas(Resolution res) {
		super();
		setOpaque(true);
		setForeground(Color.BLACK);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		setResolution(res);
	}

	/**
	 * Retrieves the current client resolution configuration.
	 *
	 * @return The currnet resolution.
	 */
	public synchronized
	Resolution getResolution() {
		return res;
	}

	/**
	 * Clear the canvas, filling it with the background color.
	 */
	public synchronized
	void clear() {
		Graphics2D g = img.createGraphics();
		g.setBackground(bg.asAWTColor());
		g.clearRect(0, 0, res.width, res.height);
	}

	/**
	 * Draw a pixel on the screen, with a given color and position.
	 *
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param color The color of the pixel.
	 */
	public synchronized
	void putPixel(int x, int y, Color color) {
		if (x < 0 || y < 0)
			return;
		if (x < res.width && y < res.height)
			img.setRGB(x, y, color.asInt());
	}

	/**
	 * Draw a pixel on the screen with the current foreground
	 * color, in a given position.
	 *
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 */
	public synchronized
	void putPixel(int x, int y) {
		putPixel(x,y,fg);
	}

	/**
	 * Draw a pixel on the screen with the current foreground
	 * color in the given position.
	 *
	 * @param point The position (x,y) to draw the pixel.
	 */
	public synchronized
	void putPixel(Point point) {
		putPixel((int)point.x, (int)point.y,fg);
	}

	/**
	 * Draw a line between two points using the current drawing
	 * color.
	 *
	 * @param p0 The first point.
	 * @param p1 The second point.
	 */
	public synchronized
	void drawLine(Point p0, Point p1) {
		drawLine((int)p0.x,(int)p0.y,(int)p1.x,(int)p1.y);
	}

	/**
	 * Draw a line between two points using the current drawing
	 * color, with the Bresenhan's algorithm.
	 *
	 * @param x0 The x coordinate of the first point.
	 * @param y0 The y coordinate of the first point.
	 * @param x1 The x coordinate of the second point.
	 * @param y1 The y coordinate of the second point.
	 */
	public synchronized
	void drawLine(int x0, int y0, int x1, int y1) {
		Util.bresenham(x0,y0,x1,y1,
			           (a,b) -> this.putPixel((int)a,(int)b));
	}

	/**
	 * Draw an image in a given position.
	 *
	 * @param image The image to draw.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 */
	public synchronized
	void drawImage(Image image, int x, int y) {
		drawImage(image.asAWTImage(), x, y);
	}

	/**
	 * Draw a java.awt.Image in a given position.
	 *
	 * @param image The image to draw.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 */
	public synchronized
	void drawImage(java.awt.Image image, int x, int y) {
		Graphics2D g = img.createGraphics();
		g.drawImage(image, x, y, null);
	}

	/**
	 * JComponent method paintComponent, required for Java Swing's UI
	 * components.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.drawImage(img, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
	}

	/**
	 * Changes the canvas client resolution. The canvas will be cleared
	 * after the resolution configuration is changed.
	 *
	 * @param res The new resolution.
	 */
	public synchronized
	void setResolution(Resolution res) {
		this.res = res;
		img = new BufferedImage(res.width, res.height,
		                        BufferedImage.TYPE_INT_RGB);
		clear();
	}

	/**
	 * Set the Canvas background color.
	 *
	 * @param bg The new background color.
	 */
	public void setBackground(Color bg) {
		this.bg = bg;
		super.setBackground(bg.asAWTColor());
	}

	/**
	 * Set the Canvas foreground color.
	 *
	 * @param fg The new foreground color.
	 */
	public void setForeground(Color fg) {
		this.fg = fg;
	}

	/**
	 * Get the foreground color.
	 *
	 * @return The component foreground color.
	 */
	@Override
	public java.awt.Color getForeground() {
		return fg.asAWTColor();
	}

	/**
	 * Bind a key to a KeyboardAction. The Key is a string representing
	 * the keyboard key to connect the action, for example, "SPACE". The
	 * action is a KeyboardAction object that will be executed either if
	 * the key is pressed or released.
	 *
	 * @param key The key to bind the action to.
	 * @param action The KeyboardAction object that will respond to the
	 * 				event.
	 */
	public void bindKey(String key, final KeyboardAction action) {
		bindKeyPressed(key, action);
		bindKeyReleased(key, action);
	}
	/**
	 * Remove event handler associated with a given key.
	 *
	 * @param key The key to remove the event handler.
	 */
	public void unbindKey(String key) {
		unbindKeyPressed(key);
		unbindKeyReleased(key);
	}

	/**
	 * Bind a key to a KeyboardAction that is invoked if the key is
	 * pressed. The Key is a string representing the keyboard key to
	 * connect the action, for example, "SPACE". The action is a
	 * KeyboardAction object that will be executed either if the key is
	 * pressed.
	 *
	 * @param key The key to bind the action to.
	 * @param action The KeyboardAction object that will respond to the
	 * 				event.
	 */
	public void bindKeyPressed(String key, final KeyboardAction action) {
		InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = getActionMap();
		KeyStroke ks = KeyStroke.getKeyStroke("pressed "  + key);
		im.put(ks, "pressed"+key);
		am.put("pressed"+key, new AbstractAction() {
			private static final long serialVersionUID = 1L;
			private final KeyboardAction handler = action;
			@Override
			public void actionPerformed(ActionEvent e)
			{ handler.handleEvent(); }
		});
	}

	/**
	 * Remove event handler associated with a given key, for when it is
	 * pressed.
	 *
	 * @param key The key to remove the event handler.
	 */
	public void unbindKeyPressed(String key) {
		InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = getActionMap();
		KeyStroke ks = KeyStroke.getKeyStroke("pressed " + key );
		im.remove(ks);
		am.remove("pressed"+key);
	}

	/**
	 * Bind a key to a KeyboardAction that is invoked if the key is
	 * released. The Key is a string representing the keyboard key to
	 * connect the action, for example, "SPACE". The action is a
	 * KeyboardAction object that will be executed either if the key is
	 * released.
	 *
	 * @param key The key to bind the action to.
	 * @param action The KeyboardAction object that will respond to the
	 * 				event.
	 */
	public void bindKeyReleased(String key, final KeyboardAction action) {
		InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = getActionMap();
		KeyStroke ks = KeyStroke.getKeyStroke("released " + key );
		im.put(ks, "released"+key);
		am.put("released"+key, new AbstractAction() {
			private static final long serialVersionUID = 1L;
			private final KeyboardAction handler = action;
			@Override
			public void actionPerformed(ActionEvent e)
			{ handler.handleEvent(); }
		});
	}

	/**
	 * Remove event handler associated with a given key, for when it is
	 * released.
	 *
	 * @param key The key to remove the event handler.
	 */
	public void unbindKeyReleased(String key) {
		InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = getActionMap();
		KeyStroke ks = KeyStroke.getKeyStroke("released " + key );
		im.remove(ks);
		am.remove("released"+key);
	}

	/**
	 * Draw a text in the specified position.
	 *
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @param size The size of the text.
	 * @param text The text to be drawn.
	 */
	public void putText(int x, int y, int size, String text) {
		Graphics g = img.getGraphics();
		g.setColor(fg.asAWTColor());
		g.setFont(new Font("Sans",0,size));
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D b = fm.getStringBounds(text, g);
		g.drawString(text,
				(int)(x-Math.round(b.getX())),
				(int)(y-Math.round(b.getY())));
	}
}

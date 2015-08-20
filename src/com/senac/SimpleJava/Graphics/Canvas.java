package com.senac.SimpleJava.Graphics;

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
	 * @return The currnet resolution.
	 */
	public Resolution getResolution() {
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
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 */
	public synchronized
	void putPixel(int x, int y) {	
		putPixel(x,y,fg);
	}
	
	/**
	 * Draw an image in a given position.
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
		/*
		java.awt.Image scale = img.getScaledInstance(WIDTH, HEIGHT,
		                                    BufferedImage.SCALE_FAST);
		 */
		g2.drawImage(img, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
	}

	/**
	 * Changes the canvas client resolution. The canvas will be cleared
	 * after the resolution configuration is changed.
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
	 * @param bg The new background color.
	 */
	public void setBackground(Color bg) {
		this.bg = bg;
		setBackground(bg.asAWTColor());
	}
	
	/**
	 * Set the Canvas foreground color.
	 * @param fg The new foreground color.
	 */
	public void setForeground(Color fg) {
		this.fg = fg;
		setForeground(fg.asAWTColor());
	}

	/**
	 * Bind a key to a KeyboardAction. The Key is a string representing
	 * the keyboard key to connect the action, for example, "SPACE". The
	 * action is a KeyboardAction object that will be executed when the
	 * key is pressed. 
	 * @param key The key to bind the action to.
	 * @param action The KeyboardAction object that will respond to the
	 * 				event.
	 */
	public void bindKey(String key, final KeyboardAction action) {
		InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = getActionMap();
		KeyStroke ks = KeyStroke.getKeyStroke(key);
		im.put(ks, key);
		am.put(key, new AbstractAction() {
			private static final long serialVersionUID = 1L;
			private final KeyboardAction handler = action;
			@Override
			public void actionPerformed(ActionEvent e)
			{ handler.handleEvent(); }
		});
	}
	
	public void putText(int x, int y, int size, String text)
	{
		Graphics g = img.getGraphics();
		g.setColor(getForeground());
		g.setFont(new Font("Arial",0,size));
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D b = fm.getStringBounds(text, g);
		g.drawString(text,
				(int)(x-Math.round(b.getX())),
				(int)(y-Math.round(b.getY())));
	}
}

package com.senac.SimpleJava.Graphics;

/**
 * Define the screen resolution for a GraphicApplication window,
 * by defining the horizontal and vertical pixel scales.
 */
public enum Resolution {
	/** Define a 96 x 72 window. */
	LOWRES(96,72),
	/** Define a 400 x 300 window. */
	MIDRES(400, 300),
	/** Define a 800 x 600 window. */
	HIGHRES(800, 600),
	/** Define a 256 x 192 window similar to MSX 1 computers. */
	MSX(256, 192);
	
	/** The horizontal pixel scale to full resolution. */
	public final double x;
	/** The vertical pixel scale to full resolution. */
	public final double y;
	/** The width. */
	public final int width;
	/** The height. */
	public final int height;
	
	private Resolution(int width, int height) {
		this.x = Canvas.SCREEN_WIDTH / (1.0*width);
		this.y = Canvas.SCREEN_HEIGHT / (1.0*height);
		this.width = width;
		this.height = height;
	}
}

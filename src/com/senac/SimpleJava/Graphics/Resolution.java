package com.senac.SimpleJava.Graphics;

/**
 * Define the screen resolution for a GraphicApplication window,
 * by defining the horizontal and vertical pixel scales.
 */
public enum Resolution {
	/** Define a 96 x 72 window. */
	LOWRES(96,72, 8, 8),
	/** Define a 256 x 192 window. */
	MIDRES(256, 192, 3, 3),
	/** Define a 758 x 576 window. */
	HIGHRES(758, 576, 1, 1),
	/** Define a 1024 x 768 window. */
	SUPERRES(1024, 768, 0.75, 0.75),
	/** Define a 256 x 192 window similar to MSX 1 computers. */
	MSX(256, 192, 3, 3);
	
	/** The horizontal pixel scale to full resolution. */
	public final double x;
	/** The vertical pixel scale to full resolution. */
	public final double y;
	/** The width. */
	public final int width;
	/** The height. */
	public final int height;
	
	private Resolution(int width, int height, double x, double y) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}

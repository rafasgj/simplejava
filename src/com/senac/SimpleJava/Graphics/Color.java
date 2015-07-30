package com.senac.SimpleJava.Graphics;

/**
 * Describe an RGB color or gray scale level.
 */
public class Color {
	private int red;
	private int green;
	private int blue;
	
	/**
	 * Create a new Color object holding a gray scale level.
	 * @param gray The gray level (0-255)
	 */
	public Color(int gray) {
		gray &= 0xFF;
		red = gray;
		green = gray;
		blue = gray;
	}
	/**
	 * Create a new Color object described by RGB levels (0-255).
	 * @param r The Red level.
	 * @param g The Green level.
	 * @param b The Blue level.
	 */
	public Color(int r, int g, int b) {
		this.red = r & 0xFF;
		this.green = g & 0xFF;
		this.blue = b & 0xFF;
	}
	/**
	 * Retrieve the gray level representation of this Color object.
	 * @return The gray level.
	 */
	public int getGray() {
		return ((red + green + blue) / 3) & 0xFF;
	}
	/**
	 * Retrieve the red component of this Color object.
	 * @return The Red level.
	 */
	public int getRed() {
		return red & 0xFF;
	}
	/**
	 * Retrieve the green component of this Color object.
	 * @return The Green level.
	 */
	public int getGreen() {
		return green & 0xFF;
	}
	/**
	 * Retrieve the blue component of this Color object.
	 * @return The Blue level.
	 */
	public int getBlue() {
		return blue & 0xFF;
	}
	
	/**
	 * Retrieve a java.awt.Color object based on this Color object.
	 * @return A java.awt.Color object.
	 */
	public java.awt.Color asAWTColor() {
		return new java.awt.Color(red, green, blue);
	}
	
	/**
	 * Retrieve an integer representation of this Color object,
	 * with colors organized as 0x00RRGGBB.
	 * @return An integer as 0x00RRGGBB.
	 */
	public int asInt() {
		return (red << 16) + (green << 8) + blue;
	}
	
	/** Create a Color object representing Black. */
	public static final Color BLACK = new Color(0); 
	/** Create a Color object representing White. */
	public static final Color WHITE = new Color(255); 
	/** Create a Color object representing Medium Gray. */
	public static final Color GRAY = new Color(128); 
	/** Create a Color object representing Dark Gray. */
	public static final Color DARKGRAY = new Color(64); 
	/** Create a Color object representing Light Gray. */
	public static final Color LIGHTGRAY = new Color(192);
	/** Create a Color object representing Red. */
	public static final Color RED = new Color(255,0,0);
	/** Create a Color object representing Gren. */
	public static final Color GREEN = new Color(0,255,0);
	/** Create a Color object representing Blue. */
	public static final Color BLUE = new Color(0,0,255);
	/** Create a Color object representing Yellow. */
	public static final Color YELLOW = new Color(255,255,0);
	/** Create a Color object representing Cyan. */
	public static final Color CYAN = new Color(0,255,255);
	/** Create a Color object representing Magenta. */
	public static final Color MAGENTA = new Color(255,0,255);
}

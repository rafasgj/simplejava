package com.senac.SimpleJava.Graphics;

/**
 * This interface is implemented by all objects that can be moved on
 * the screen.
 */
public interface Movable {
	/**
	 * Makes the object move on both horizontal or vertical directions
	 * by an amount of pixels. It does accept sub-pixel and negative
	 * values.
	 * @param dx The amount of pixels to move on the horizontal axis.
	 * @param dy The amount of pixels to move on the vertical axis.
	 */
	void move(double dx, double dy);
}

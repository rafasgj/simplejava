package com.senac.SimpleJava.Graphics;

/**
 * A structure to represent a 2-D point object.
 */
public class Point {
	/** The x coordinate of the point. */
	public final double x;
	/** The y coordinate of the point. */
	public final double y;
	
	/**
	 * Creates a new 2-D point.
	 * @param x The X (often horizontal) coordinate.
	 * @param y The Y (often vertical) coordinate.
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Computes the Euclidean Distance from another Point.
	 * @param p The other point to compute the distance to.
	 * @return The distance between the two points.
	 */
	public double distance(Point p) {
		double dx = x - p.x;
		double dy = y - p.y;
		return Math.sqrt(dx*dx + dy*dy);
	}
}

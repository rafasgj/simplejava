package com.senac.SimpleJava.Graphics;

/**
 * Generic interface for binary functions.
 */
interface BinaryFunction {
	/**
	 * Generic interface for binary function for parameters
	 * of type 'double'.
	 * @param a The first parameter.
	 * @param b The second parameter.
	 */
	void execute(double a, double b);
}

/**
 * Internal utility class with generic algorithms.
 */
class Util {
	/**
	 * Find the coordinates of a line between two points defined by
	 * (x0,y0) and (x1,y1), using the Bresenham's line algorithm,
	 * and call a BinaryFunction for each point found.
	 * @param x0 The x coordinate of the first point.
	 * @param y0 The y coordinate of the first point.
	 * @param x1 The x coordinate of the second point.
	 * @param y1 The y coordinate of the second point.
	 * @param func The function to be called for each coordinate
	 *             of the line.
	 */
	public static synchronized
	void bresenham(int x0, int y0, int x1, int y1, BinaryFunction func) {

		int d = 0;
		 
        int dy = Math.abs(y1 - y0);
        int dx = Math.abs(x1 - x0);
 
        int dy2 = (dy << 1); 
        int dx2 = (dx << 1);
 
        int sx = x0 < x1 ? 1 : -1; // increment direction
        int sy = y0 < y1 ? 1 : -1;
 
        if (dy <= dx) {
            for (;;) {
                func.execute(x0,y0);
                if (x0 == x1)
                    break;
                x0 += sx;
                d += dy2;
                if (d > dx) {
                    y0 += sy;
                    d -= dx2;
                }
            }
        } else {
            for (;;) {
                func.execute(x0, y0);
                if (y0 == y1)
                    break;
                y0 += sy;
                d += dx2;
                if (d > dy) {
                    x0 += sx;
                    d -= dy2;
                }
            }
        }
	}

}

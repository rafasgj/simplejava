package simplejava.examples.graphics.Pong;

import simplejava.graphics.Canvas;
import simplejava.graphics.Color;
import simplejava.graphics.Drawable;
import simplejava.graphics.Point;

public class Ball
	implements Drawable
{
	private double dx = 0.1;
	private double dy = 0.1;
	private double x, y;

	private int screenWidth;
	private int screenHeight;

	public Ball(int width, int height) {
		x = Math.random()*width;
		y = height/2 - Math.random()*20 -10;
		screenWidth = width;
		screenHeight = height;
	}

	private void moveX() {
		this.x += dx;
		if (x < 0) {
			x *= -1;
			dx *= -1;
		}
		if (x > screenWidth) {
			x = screenWidth - (x - screenWidth);
			dx *= -1;
		}
	}

	private void moveY() {
		this.y += dy;
		if (y < 0) {
			y *= 1;
			dy *= -1;
		}
		if (y > screenHeight) {
			y = screenHeight - (y - screenHeight);
			dy *= -1;
		}
	}

	public void invertY() {
		dy *= -1;
		y += dy;
	}

	public void step() {
		moveX();
		moveY();
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.putPixel((int)x, (int)y, Color.RED);
	}

	public Point getPosition() {
		return new Point((int)x,(int)y);
	}
}

package simplejava.examples.graphics.Pong;

import simplejava.graphics.Canvas;
import simplejava.graphics.Color;
import simplejava.graphics.Drawable;
import simplejava.graphics.Point;
import simplejava.graphics.Rect;
import simplejava.graphics.Sprite;

public class Bar
	implements Drawable
{
	private Sprite sprite;
	private int xres;

	public Bar(int xres) {
		this.xres = xres;
		sprite = new Sprite(8,3, Color.WHITE);
		for (int x = 1; x < 7; ++x) {
			sprite.setPixel(x, 0, Color.BLACK);
			sprite.setPixel(x, 2, Color.BLACK);
		}
		for (int x = 0; x < 8; ++x)
			sprite.setPixel(x, 1, Color.BLACK);
	}

	public void move(int dx, int dy) {
		Point xy = sprite.getPosition();
		int x = (int)xy.x + dx;
		int y = (int)xy.y + dy;
		if (x > 0 && x < xres)
			sprite.setPosition(x,y);
	}

	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}

	public Rect getBounds() {
		return sprite.getBounds();
	}
}

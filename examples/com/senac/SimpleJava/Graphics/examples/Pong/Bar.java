package com.senac.SimpleJava.Graphics.examples.Pong;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Drawable;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

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

package com.senac.SimpleJava.Graphics.examples;

import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.KeyboardAction;
import com.senac.SimpleJava.Graphics.Resolution;

public class BouncingBall
	extends GraphicApplication
{
	private int x;
	private int y;
	private int w;
	private int h;
	private int ydir;
	private int xdir;

	@Override
	public void setup() {
		setResolution(Resolution.MSX);
		w = Resolution.MSX.width;
		h = Resolution.MSX.height;
		x = (int)(Math.random() * w);
		y = (int)(Math.random() * h);
		xdir = 1;
		ydir = 1;
		
		setFramesPerSecond(120);
		bindKey("SPACE", new KeyboardAction() {
			@Override
			public void handleEvent()
			{ endLoop(); }
		});
	}
	
	@Override
	public void step() {
		moveX();
		moveY();
	}
	
	@Override
	public void draw(Canvas c) {
		c.setBackground(Color.WHITE);
		c.clear();
		c.putPixel(x,y,Color.BLUE);
	}

	@Override
	protected void cleanup() {
		System.out.println("Bouncing Ball finished!");
	}	

	private void moveX() {
		x += xdir;
		if (x < 0) {
			xdir *= -1;
			x *= -1;
		}
		if (x > w) {
			xdir *= -1;
			x = w - (x - w);
		}
	}
	private void moveY() {
		y += ydir;
		if (y < 0) {
			ydir *= -1;
			y *= -1;
		}
		if (y > h) {
			ydir *= -1;
			y = h - (y - h);
		}
	}

}

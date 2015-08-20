package com.senac.SimpleJava.Graphics.examples.Invaders;

import java.io.IOException;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Drawable;
import com.senac.SimpleJava.Graphics.Image;
import com.senac.SimpleJava.Graphics.Moveable;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Sprite;

public class GameObject
	extends Sprite
	implements Moveable, Drawable
{
	private Rect limits;
	private boolean hitXLimit = false;
	private boolean hitYLimit = false;
	
	public GameObject(String imagefile)
			throws IOException
	{
		this(imagefile, Color.BLACK);
	}
	
	public GameObject(String imagefile, Color transparency)
			throws IOException
	{
		super(imagefile, transparency);
		limits = new Rect(0,0,Canvas.SCREEN_WIDTH,Canvas.SCREEN_HEIGHT);
	}
	
	public GameObject(Image image)
	{
		this(image, Color.BLACK);
	}
	
	public GameObject(Image image, Color transparency)
	{
		super(image, transparency);
		limits = new Rect(0,0,Canvas.SCREEN_WIDTH,Canvas.SCREEN_HEIGHT);
	}
	
	public Rect getBounds() {
		Point p = getPosition();
		return new Rect((int)p.x, (int)p.y, getWidth(), getHeight());
	}

	public boolean hitHorizontal() {
		return hitXLimit;
	}
	
	public boolean hitVertical() {
		return hitYLimit;
	}
	
	@Override
	public void move(double dx, double dy) {
		Point p = getPosition();
		super.move(moveX(p.x, dx), moveY(p.y, dy));
	}

	private double moveX(double x, double dx) {
		hitXLimit = false;
		double nx = x + dx;
		if (nx < limits.x) {
			nx = limits.x;
			hitXLimit = true;
		}
		double max = nx + getWidth();
		if (max > limits.width) {
			nx = limits.width - getWidth();
			hitXLimit = true;
		}
		return nx - x;
	}
	
	private double moveY(double y, double dy) {
		hitYLimit = false;
		double ny = y + dy;
		if (ny < limits.y) {
			ny = limits.y;
			hitYLimit = true;
		}
		double max = ny + getHeight();
		if (max > limits.height) {
			ny = limits.height - getHeight();
			hitYLimit = true;
		}
		return ny - y;
	}

	public void setLimits(Rect limits) {
		this.limits = limits;
	}
	
	public boolean doCollide(Rect bounds) {
		Rect b = getBounds();
		double r1 = (b.width > b.height) ? b.width/2 : b.height/2;
		double r2 = (bounds.width > bounds.height) ?
						bounds.width/2 : bounds.height/2;
		Point p1 = getPosition();
		Point p2 = new Point(bounds.x, bounds.y);
		
		double distance = p1.distance(p2);
		
		return distance < (r1+r2);
	}
	
}

package com.senac.SimpleJava.Graphics.examples;

import java.util.ArrayList;
import java.util.List;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.events.MouseEvent;
import com.senac.SimpleJava.Graphics.events.MouseObserver;

public class MouseExample
	extends GraphicApplication
	implements MouseObserver
{
	private List<Point> points = new ArrayList<>();
	
	@Override
	protected void draw(Canvas canvas) {
		canvas.clear();
		for (Point p : points)
			canvas.putPixel(p);
	}

	@Override
	protected void setup() {
		addMouseObserver(MouseEvent.CLICK, this);
		addMouseObserver(MouseEvent.DOUBLECLICK, this);
	}

	@Override
	protected void step() {
		setResolution(Resolution.MSX);
		setFramesPerSecond(120);
	}

	@Override
	public void notify(MouseEvent event, int button, Point point) {
		if (event == MouseEvent.CLICK) {
			points.add(point);
		}
		if (event == MouseEvent.DOUBLECLICK) {
			points.clear();
		}
	}

}

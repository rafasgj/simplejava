package simplejava.examples.graphics;

import java.util.ArrayList;
import java.util.List;

import simplejava.graphics.Canvas;
import simplejava.graphics.GraphicApplication;
import simplejava.graphics.Point;
import simplejava.graphics.Resolution;
import simplejava.graphics.events.MouseEvent;
import simplejava.graphics.events.MouseObserver;

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
		redraw();
	}

	@Override
	protected void loop() {
		/* nothing to do on the loop */
	}

}

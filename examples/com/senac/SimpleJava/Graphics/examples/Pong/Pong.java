package com.senac.SimpleJava.Graphics.examples.Pong;

import com.senac.SimpleJava.Graphics.*;
import com.senac.SimpleJava.Graphics.events.KeyboardAction;

public class Pong
		extends GraphicApplication
{
	private Ball ball;
	private Bar barT, barB;
	
	private KeyboardAction moveBRight = new KeyboardAction() {
		public void handleEvent() { barB.move(5, 0); }
	};
	private KeyboardAction moveBLeft = new KeyboardAction() {
		public void handleEvent() { barB.move(-5, 0); }
	};
	private KeyboardAction moveTRight = new KeyboardAction() {
		public void handleEvent() { barT.move(5, 0); }
	};
	private KeyboardAction moveTLeft = new KeyboardAction() {
		public void handleEvent() { barT.move(-5, 0); }
	};

	@Override
	public void setup() {
		Resolution res = Resolution.MSX;
		setResolution(res);
		setFramesPerSecond(240);
		setTitle("Pong");
		
		bindKey("RIGHT", moveBRight);
		bindKey("LEFT", moveBLeft);
		bindKey("O", moveTRight); // for Dvorak Keyboards.
		bindKey("S", moveTRight); // for QWERTY Keyboards.
		bindKey("A", moveTLeft);

		ball = new Ball(res.width, res.height);
		barB = new Bar();
		barB.move(res.width / 2, res.height - 5);
		barT = new Bar();
		barT.move(res.width / 2, 2);
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.clear();
		barB.draw(canvas);
		barT.draw(canvas);
		ball.draw(canvas);
	}

	@Override
	public void loop() {
		ball.step();
		Point p = ball.getPosition();
		if (checkColision(p,barB.getBounds()))
			ball.invertY();
		if (checkColision(p,barT.getBounds()))
			ball.invertY();
		
		redraw();
	}

	private boolean checkColision(Point p, Rect bounds) {
		double maxX = bounds.x + bounds.width;
		double maxY = bounds.y + bounds.height;
		if (bounds.x <= p.x && maxX >= p.x)
			if (bounds.y <= p.y && maxY >= p.y)
				return true;
		return false;
	}
}

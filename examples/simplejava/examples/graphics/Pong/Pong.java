package simplejava.examples.graphics.Pong;

import simplejava.graphics.*;
import simplejava.graphics.events.KeyboardAction;

public class Pong
		extends GraphicApplication
{
	private Ball ball;
	private Bar barT, barB;

	private int moveB = 0;
	private int moveT = 0;

	private KeyboardAction moveBRight = new KeyboardAction() {
		public void handleEvent() { moveB = (moveB == 0) ? 1 : 0; }
	};
	private KeyboardAction moveBLeft = new KeyboardAction() {
		public void handleEvent() { moveB = (moveB == 0) ? -1 : 0; }
	};
	private KeyboardAction moveTRight = new KeyboardAction() {
		public void handleEvent() { moveT = (moveT == 0) ? 1 : 0; }
	};
	private KeyboardAction moveTLeft = new KeyboardAction() {
		public void handleEvent() { moveT = (moveT == 0) ? -1 : 0;  }
	};

	@Override
	public void setup() {
		Resolution res = Resolution.LOWRES;
		setResolution(res);
		setFramesPerSecond(240);
		setTitle("Pong");

		bindKey("RIGHT", moveBRight);
		bindKey("LEFT", moveBLeft);
		bindKey("O", moveTRight); // for Dvorak Keyboards.
		bindKey("S", moveTRight); // for QWERTY Keyboards.
		bindKey("A", moveTLeft);
		bindKey("Q", moveTLeft); // for AZERTY Keyboards.

		ball = new Ball(res.width, res.height);
		barB = new Bar(res.width);
		barB.move(res.width / 2, res.height - 5);
		barT = new Bar(res.width);
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
		barB.move(1 * moveB, 0);
		barT.move(1 * moveT, 0);

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

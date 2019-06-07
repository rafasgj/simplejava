package simplejava.examples.graphics;

import simplejava.graphics.Canvas;
import simplejava.graphics.GraphicApplication;
import simplejava.graphics.Resolution;

public class KeyTypeDemo extends GraphicApplication {

	public String words = "";

	@Override
	protected void draw(Canvas canvas) {
		canvas.putText(20, 20, 10, "Some text:");
		canvas.putText(20, 30, 10, words);
	}

	@Override
	protected void setup() {
		setResolution(Resolution.MSX);
		addKeyTypedListener((key)->{words += key;});
	}

	@Override
	protected void loop() {
		redraw();
	}

}

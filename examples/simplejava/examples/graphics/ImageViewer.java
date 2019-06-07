package simplejava.examples.graphics;

import java.io.IOException;

import simplejava.graphics.Canvas;
import simplejava.graphics.GraphicApplication;
import simplejava.graphics.Image;
import simplejava.graphics.Resolution;

public class ImageViewer
	extends GraphicApplication
{
	private Image img;

	@Override
	protected void setup() {
		setResolution(Resolution.HIGHRES);
		try {
			img = new Image("images/invaders/cthulhu.png");
		} catch (IOException e) {
			e.printStackTrace();
			endLoop();
		}
	}

	@Override
	protected void draw(Canvas canvas) {
		canvas.drawImage(img, 0, 0);
	}

	@Override
	protected void loop() {
	}

}

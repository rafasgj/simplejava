package com.senac.SimpleJava.Graphics.examples;

import java.io.IOException;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Image;
import com.senac.SimpleJava.Graphics.Resolution;

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

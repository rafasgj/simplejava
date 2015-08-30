package com.senac.SimpleJava.Graphics.examples;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Resolution;

public class DrawLines extends GraphicApplication {

	@Override
	protected void draw(Canvas canvas) {
		Resolution res = canvas.getResolution();
		canvas.setForeground(Color.RED);
		canvas.drawLine(0,0,res.width-1,res.height-1);
		canvas.setForeground(Color.BLACK);
		canvas.drawLine(0,0,res.width-1,0);
		canvas.setForeground(Color.BLUE);
		canvas.drawLine(res.width-1,res.height-1,0,50);
	}

	@Override
	protected void setup() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void step() {
		// TODO Auto-generated method stub

	}

}

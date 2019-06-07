package simplejava.examples.graphics;

import simplejava.graphics.Canvas;
import simplejava.graphics.Color;
import simplejava.graphics.GraphicApplication;
import simplejava.graphics.Resolution;

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
		canvas.setForeground(Color.MAGENTA);
		canvas.drawLine(res.width/2,res.height,res.width/2,0);
	}

	@Override
	protected void setup() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void loop() {
		// TODO Auto-generated method stub
	}

}

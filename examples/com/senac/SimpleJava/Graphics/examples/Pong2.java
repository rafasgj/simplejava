package com.senac.SimpleJava.Graphics.examples;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.Sprite;
import com.senac.SimpleJava.Graphics.events.KeyboardAction;

public class Pong2 extends GraphicApplication {

	private int PADDLE_SIZE = 10;
	
	private Resolution res;
	
	private Sprite paddle1, paddle2, ball;
	
	private int deltaX, deltaY, delta1, delta2, score1, score2;
	
		@Override
		protected void draw(Canvas canvas) {
			canvas.clear();
			
			canvas.setForeground(Color.BLACK);
			
			int mid = res.width / 2;
			canvas.drawLine(mid, 0, mid, res.height);
			canvas.putText(18, 1, 12, ((Integer)score1).toString());
			canvas.putText(70, 1, 12, ((Integer)score2).toString());
			
			paddle1.draw(canvas);
			paddle2.draw(canvas);
			ball.draw(canvas);
		}

	@Override
	protected void setup() {
		res = Resolution.LOWRES;
		this.setResolution(res);
		this.setFramesPerSecond(30);
		
		paddle1 = new Sprite(2, PADDLE_SIZE, Color.BLACK);
		paddle1.setPosition(1, res.height/2 - 5);
		paddle2 = new Sprite(2, PADDLE_SIZE, Color.BLACK);
		paddle2.setPosition(res.width-3, res.height/2 - 5);
		
		ball = new Sprite(2,2,Color.BLACK);
		ballRestart();
		
		deltaX = 1;
		deltaY = -1;
		
		delta1 = 0;
		delta2 = 0;
		
		score1 = 0;
		score2 = 0;
		
		keyboardConfiguration();
	}
	
	private void keyboardConfiguration() {
		bindKeyPressed("UP", new KeyboardAction() {
			public void handleEvent() { delta2 = -1; }
		});
		bindKeyReleased("UP", new KeyboardAction() {
			public void handleEvent() { delta2 = 0; }
		});
		bindKeyPressed("DOWN", new KeyboardAction() {
			public void handleEvent() { delta2 = +1; }
		});
		bindKeyReleased("DOWN", new KeyboardAction() {
			public void handleEvent() { delta2 = 0; }
		});
		bindKeyPressed("A", new KeyboardAction() {
			public void handleEvent() { delta1 = -1; }
		});
		bindKeyReleased("A", new KeyboardAction() {
			public void handleEvent() { delta1 = 0; }
		});
		bindKeyPressed("Z", new KeyboardAction() {
			public void handleEvent() { delta1 = +1; }
		});
		bindKeyReleased("Z", new KeyboardAction() {
			public void handleEvent() { delta1 = 0; }
		});
		// For Dvorak Keyboards
		bindKeyPressed("SEMICOLON", new KeyboardAction() {
			public void handleEvent() { delta1 = +1; }
		});
		bindKeyReleased("SEMICOLON", new KeyboardAction() {
			public void handleEvent() { delta1 = 0; }
		});
	}

	private void ballRestart() {
		deltaX *= -1;
		deltaY *= -1;
		ball.setPosition(res.width/2, res.height/2);	
	}

	@Override
	protected void loop() {
		Point pos = ball.getPosition();
		// Verifica se a bola bateu nas paredes.
		if (pos.y <= 0 || pos.y >= res.height-1)
			deltaY *= -1;
		// Verifica se foi ponto do jogador 1
		if (pos.x > res.width) {
			score1++;
			ballRestart();
		}
		// Verifica se foi ponto do jogador 2
		if (pos.x < 0) {
			score2++;
			ballRestart();
		}
		// Verifica se bola bateu no paddel1
		Point bat = paddle1.getPosition();
		if (pos.x == 2 && pos.y >= bat.y && pos.y <= bat.y+PADDLE_SIZE)
			deltaX *= -1;
		// Verifica se bola bateu no paddel2
		bat = paddle2.getPosition();
		if (pos.x == res.width-4 && pos.y >= bat.y && pos.y <= bat.y+PADDLE_SIZE)
			deltaX *= -1;
		
		// Movimenta bola.
		ball.move(deltaX, deltaY);
		// Movimenta paddle1.
		paddle1.move(0, delta1);
		// Movimenta paddle2.
		paddle2.move(0, delta2);
		// requisita a tela para se desenhar.
		redraw();
	}

}

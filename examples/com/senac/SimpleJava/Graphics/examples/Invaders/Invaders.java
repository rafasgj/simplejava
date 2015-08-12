package com.senac.SimpleJava.Graphics.examples.Invaders;

import java.io.IOException;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Image;
import com.senac.SimpleJava.Graphics.Resolution;
import com.senac.SimpleJava.Graphics.Sprite;

public class Invaders extends GraphicApplication {

	private int xhero, xherodelta;
	private int xenemy, xenemydelta;
	private Sprite spaceship;
	private Sprite[] enemies;

	private Image bg;
	
	private void die(Exception e) {
		e.printStackTrace();
		endLoop();
	}
	
	@Override
	protected void setup() {
		Resolution res = Resolution.MIDRES;

		try {
			bg = new Image("images/originals/terra.jpg");
			bg.resize(res.width, res.height);
			spaceship=new Sprite("images/invaders/SR71.png",Color.BLACK);
			Image img = new Image("images/invaders/cthulhu.png");
			enemies = new Sprite[15];
			for (int i =0; i < 15; i++) {
				enemies[i] = new Sprite(img, Color.BLACK);
				enemies[i].resize(30, 30);
			}
		} catch (IOException e) { die(e); }

		setFramesPerSecond(60);
		setResolution(res);
		setTitle("Invaders");
		
		xhero = res.width / 2;
		xenemy = 5;
		xherodelta = 3;
		xenemydelta = 1;
	}

	@Override
	protected void draw(Canvas canvas) {
		canvas.clear();
		canvas.drawImage(bg, 0, 0);
		
		spaceship.setPosition(xhero, getResolution().height-40);
		spaceship.draw(canvas);
		
		for (int i = 0; i < 15; i ++) {
			int y = 5 + 30 * (i / 5);
			int x = 35 * (i % 5);
			enemies[i].setPosition(xenemy+x, y);
			enemies[i].draw(canvas);
		}
		
		canvas.setForeground(Color.WHITE);
		canvas.putText(0, 0, 10, "Cthulhu Invaders");
	}

	@Override
	protected void step() {
		moveHero();
		moveEnemies();
	}

	private void moveHero() {
		int min = 10;
		int max = getResolution().width - 10 - 30;
		xhero += xherodelta;
		if (xhero < min) {
			xhero = min;
			xherodelta *= -1;
		}
		if (xhero > max) {
			xhero = max;
			xherodelta *= -1;
		}
	}
	
	private void moveEnemies() {
		int min = 10;
		int max = getResolution().width - 10 - 35*5;
		xenemy += xenemydelta;
		if (xenemy < min) {
			xenemy = min;
			xenemydelta *= -1;
		}
		if (xenemy > max) {
			xenemy = max;
			xenemydelta *= -1;
		}
	}

}

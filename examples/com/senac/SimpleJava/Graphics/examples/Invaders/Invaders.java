package com.senac.SimpleJava.Graphics.examples.Invaders;

import java.io.IOException;

import com.senac.SimpleJava.Graphics.Canvas;
import com.senac.SimpleJava.Graphics.Color;
import com.senac.SimpleJava.Graphics.Drawable;
import com.senac.SimpleJava.Graphics.GraphicApplication;
import com.senac.SimpleJava.Graphics.Image;
import com.senac.SimpleJava.Graphics.KeyboardAction;
import com.senac.SimpleJava.Graphics.Point;
import com.senac.SimpleJava.Graphics.Rect;
import com.senac.SimpleJava.Graphics.Resolution;

public class Invaders extends GraphicApplication {

	private int xenemydelta, xherodelta;
	private GameObject spaceship;
	private GameObject shot;
	private GameObject[] enemies;
	private int score;
	
	private Image bg;
	
	private void die(Exception e) {
		e.printStackTrace();
		endLoop();
	}
	
	private static final boolean DEMO_MODE = false;
	
	private static final String EARTH_IMAGE="images/Originals/terra.jpg";
	private static final String ALIEN_IMAGE="images/invaders/cthulhu.png";
	private static final String SHIP_FILE = "images/invaders/SR71.png";
	private static Image shotimage;

	private void configureKeyboardBindings() {
		// Configure keyboard
		if (!DEMO_MODE) {
			bindKey("SPACE", new KeyboardAction() {
				@Override
				public void handleEvent()
				{ if (shot == null) newShot(); }
			});
			bindKey("RIGHT", new KeyboardAction() {
				@Override
				public void handleEvent()
				{ spaceship.move(xherodelta, 0); }
			});
			bindKey("LEFT", new KeyboardAction() {
				@Override
				public void handleEvent()
				{ spaceship.move(-xherodelta, 0); }
			});
		}
	}
	
	private void createEnemies(Resolution res)
			throws IOException
	{
		Image img = new Image(ALIEN_IMAGE);
		int deltaMax = res.width - 35*5;
		enemies = new GameObject[15];
		for (int i =0; i < 15; i++) {
			enemies[i] = new GameObject(img);
			enemies[i].resize(30, 30);
			int x = (i%5)*35+25;
			int y = (i/5)*35+5;
			int bottom = enemies[i].getHeight();
			enemies[i].setPosition(new Point(x,y));
			enemies[i].setLimits(new Rect(x,y,x+deltaMax,y+bottom));
		}			
	}
	
	private void createSpaceship(Resolution res)
			throws IOException
	{
		spaceship = new GameObject(SHIP_FILE);
		int top = getResolution().height-40;
		spaceship.setPosition(new Point(res.width / 2,top));
		Rect bounds = spaceship.getBounds();
		Rect limits = new Rect(20,top,res.width-20,top+bounds.height);
		spaceship.setLimits(limits);
	}
	
	@Override
	protected void setup() {
		// Application
		Resolution res = Resolution.MIDRES;
		setFramesPerSecond(60);
		setResolution(res);
		setTitle("Invaders");
		
		// Control variables.
		xherodelta = 3;
		xenemydelta = 1;
		score = 0;
		
		try {
			// Background
			bg = new Image(EARTH_IMAGE);
			bg.resize(res.width, res.height);
			// Game Elements
			createShot();
			createSpaceship(res);
			createEnemies(res);
			configureKeyboardBindings();
		} catch (IOException e) { die(e); }
	}

	protected void newShot() {
		shot = new GameObject(shotimage);
		int w = shot.getWidth();
		int h = shot.getHeight();
		Point p = spaceship.getPosition();
		double x = p.x + spaceship.getWidth()/2;
		double y = p.y;
		shot.setPosition((int)x,(int)y);
		shot.setLimits(new Rect((int)x, 0, (int)x+w, (int)y+h));
	}

	@Override
	protected void draw(Canvas canvas) {
		canvas.clear();
		canvas.drawImage(bg, 0, 0);
		
		spaceship.draw(canvas);
		if (shot != null)
			shot.draw(canvas);
		for (Drawable d: enemies)
			if (d != null) d.draw(canvas);
		canvas.setForeground(Color.WHITE);
		canvas.putText(0, 0, 10, "Cthulhu Invaders");
		canvas.putText(0, 175, 10, String.format("%08d", score));
	}
	
	@Override
	protected void loop() {
		moveHero();
		moveEnemies();
		if (DEMO_MODE) {
			boolean doIt = (int)(Math.random() * 10) == 0;
			if (doIt && shot == null) newShot();
		} else if (shot != null) {
			if (!hitEnemy())
				moveShot();
		}
		
		redraw();
	}
	
	private boolean hitEnemy() {
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i] != null) {
				if (shot.doCollide(enemies[i].getBounds())) {
					score += 500;
					enemies[i] = null;
					shot = null;
					return true;
				}
			}
		}
		return false;
	}

	private void moveHero() {
		if (DEMO_MODE) {
			spaceship.move(xherodelta, 0);
			if (spaceship.hitHorizontal())
				xherodelta *= -1;
		}
	}
	
	private void moveEnemies() {
		boolean invert = false;
		for (GameObject go : enemies) {
			if (go != null) {
				go.move(xenemydelta, 0);
				invert |= go.hitHorizontal();
			}
		}
		if (invert)
			xenemydelta *= -1;
	}
	
	private void moveShot() {
		shot.move(0, -1);
		if (shot.hitVertical())
			shot = null;
	}

	private void createShot() {
		// Shot
		shotimage = new Image(7,7);
		shotimage.clear(Color.BLACK);
		shotimage.setPixel(4,6,Color.WHITE);
		shotimage.setPixel(3,6,Color.WHITE);
		shotimage.setPixel(2,6,Color.WHITE);
 		shotimage.setPixel(3,5,Color.WHITE);
		shotimage.setPixel(3,4,Color.WHITE);
		shotimage.setPixel(4,3,Color.WHITE);
		shotimage.setPixel(3,3,Color.WHITE);
		shotimage.setPixel(2,3,Color.WHITE);
		shotimage.setPixel(5,2,Color.WHITE);
		shotimage.setPixel(4,2,Color.WHITE);
		shotimage.setPixel(3,2,Color.WHITE);
		shotimage.setPixel(2,2,Color.WHITE);
		shotimage.setPixel(1,2,Color.WHITE);
		shotimage.setPixel(4,1,Color.WHITE);
		shotimage.setPixel(3,1,Color.WHITE);
		shotimage.setPixel(2,1,Color.WHITE);
		shotimage.setPixel(3,0,Color.WHITE);
		shot = null;
	}
}

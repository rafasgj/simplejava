package com.senac.SimpleJava.Graphics;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.senac.SimpleJava.EventDrivenApplication;
import com.senac.SimpleJava.Graphics.events.MouseEvent;
import com.senac.SimpleJava.Graphics.events.MouseObserver;

/*
 * This class handles all Java Swing mouse events, and route then to
 * the correct observers.
 */
class MouseAdapter extends javax.swing.event.MouseInputAdapter
{
	private List<MouseObserver>[] observers;

	private Resolution res;
	
	@SuppressWarnings("unchecked")
	public MouseAdapter(Resolution res) {
		this.res = res;
		MouseEvent[] events = MouseEvent.values();
		observers = new List[events.length];
		for (int i = 0; i < events.length; i++)
			observers[i] = new ArrayList<MouseObserver>();
	}
	
	public void setResolution(Resolution res) {
		this.res = res;
	}
	
	private void notifyEvent(MouseEvent event, java.awt.event.MouseEvent e) {
		int button = e.getButton();
		Point p = new Point((int)(e.getX()/res.x), (int)(e.getY()/res.y));
		for (MouseObserver o : observers[event.index])
			o.notify(event, button, p);
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		if (e.getClickCount() < 2)
			notifyEvent(MouseEvent.CLICK, e);
		else
			notifyEvent(MouseEvent.DOUBLECLICK, e);
	}
	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		notifyEvent(MouseEvent.DRAG, e);
	}
	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		notifyEvent(MouseEvent.MOVE, e);
	}
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		notifyEvent(MouseEvent.PRESS, e);
	}
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		notifyEvent(MouseEvent.RELEASE, e);
	}
	
	public void register(MouseEvent event, MouseObserver observer) {
		observers[event.index].add(observer);
	}
	public void unregister(MouseEvent event, MouseObserver observer) {
		observers[event.index].remove(observer);
	}
}

/**
 * This is the base class for all GraphicApplication (a.k.a. games) for
 * the SimpleJava framework.
 * All subclasses must implement methods setup(), step() and draw(), and
 * should implement the method cleanup().
 * These applications run as an infinite loop, and the client will
 * implement the parts required to execute the actual behavior through
 * the hook methods.
 */
public abstract
class GraphicApplication
	extends EventDrivenApplication
{
	private int sleepTime;
	
	private Canvas canvas;
	private JFrame win;
	
	// Handle all mouse events
	private MouseAdapter mouseAdapter;

    private boolean needRedraw = true;
	
	/**
	 * Initializes the underlying application engine, the drawing canvas,
	 * and display the main window.
	 */
	protected GraphicApplication() {
		sleepTime = 1000/30;
		win = new JFrame("Simple Java");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new Canvas(Resolution.LOWRES);
		mouseAdapter = new MouseAdapter(canvas.getResolution());
		canvas.addMouseListener(mouseAdapter);
		win.add(canvas);
		win.pack();
		win.setVisible(true);
	}
	
	/**
	 * Set the window title.
	 * @param title The new window title.
	 */
	protected void setTitle(String title) {
		win.setTitle(title);
	}
	
	/**
	 * Set the maximum number of frames per second that the application
	 * will have. This will force the frequency of hook methods to be
	 * controlled.
	 * @param fps The maximum number of frames per second (0 to disable).
	 */
	protected void setFramesPerSecond(int fps) {
		if (fps < 1)
			sleepTime = 0;
		else
			sleepTime = 1000/fps;
	}
	
	// Pauses the application for a small amount of time.
	private void sleepLoop(long milis) {
		if (milis > 0) {
			try {
				Thread.sleep(milis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Tell application to redraw its window.
	 */
	protected final void redraw() {
		needRedraw = true;
	}
	
	/**
	 * This method is required to comply with the SimpleJava
	 * application framework. It is the application entry point, and
	 * define the application loop.
	 */
	public void run() {
		try {
			setup();
			while (shouldRun()) {
				long e, s = System.currentTimeMillis();
				loop();
				if (needRedraw) {
					draw(canvas);
					win.repaint();
				}
				e = System.currentTimeMillis();
				sleepLoop(sleepTime - (e-s));
			}
			cleanup();
			win.dispose();
		} catch(Exception e) {
			System.err.println("Unhandled Exception forced termination.");
			if (win != null)
				win.dispose();
			e.printStackTrace(System.err);
			System.exit(-1);
		}
	}
	
	/**
	 * Set the application screen resolution.
	 * @param res The new screen resolution.
	 */
	protected void setResolution(Resolution res) {
		canvas.setResolution(res);
		mouseAdapter.setResolution(res);
	}

	/**
	 * Retrieves the current screen resolution.
	 * @return The current screen resolution.
	 */
	protected Resolution getResolution() {
		return canvas.getResolution();
	}

	/**
	 * Bind a key to a KeyboardAction. The Key is a string representing
	 * the keyboard key to connect the action, for example, "SPACE". The
	 * action is a KeyboardAction object that will be executed when the
	 * key is pressed.
	 * The actual binding is done by the Application Canvas.
	 * @param key The key to bind the action to.
	 * @param action The KeyboardAction object that will respond to the
	 * 				event.
	 */
	public void bindKey(String key, KeyboardAction action) {
		canvas.bindKey(key, action);
	}
	
	/**
	 * Bind a MouseObserver object to a mouse event. Remember to call
	 * unregister() when the event don't need to be monitored anymore.
	 * @param event The mouse event to observe.
	 * @param observer The observer object.
	 */
	public void addMouseObserver(MouseEvent event, MouseObserver observer) {
		mouseAdapter.register(event, observer);
	}
	
	/**
	 * Stop observing a mouse event. This method should be called when
	 * the object will not be used anymore.
	 * @param event The mouse event to observe.
	 * @param observer The observer object.
	 */
	public void removeMouseObserver(MouseEvent event, MouseObserver observer) {
		mouseAdapter.unregister(event, observer);
	}

	/**
	 * This method is called just before the screen is redrawn, and
	 * should be used to draw the application canvas.
	 * @param canvas The canvas used to draw elements.
	 */
	protected abstract void draw(Canvas canvas);
}

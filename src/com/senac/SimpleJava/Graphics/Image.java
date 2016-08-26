package com.senac.SimpleJava.Graphics;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Implements the required behavior for an Image.
 */
public class Image
	implements Resizable
{
	/**
	 * The image data is stored in a java.awt.image.BufferedImage.
	 */
	protected BufferedImage img;
	
	/**
	 * Creates an empty image with no underlying image data. This
	 * constructor is available for classes that will share control
	 * of the underlying image data.
	 */
	protected Image() {
		img = null;
	}
	
	/**
	 * Creates a new empty Image with the given size.
	 * @param width  The image width.
	 * @param height The image height.
	 */
	public Image(int width, int height) {
		img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	}
	
	/**
	 * Creates a new Image object and load its contents from a file,
	 * given its file path.
	 * @param filename The file path for the image file.
	 * @throws IOException If the file cannot be loaded.
	 */
	public Image(String filename) throws IOException {
		img = ImageIO.read(new File(filename));
	}
	
	/**
	 * Retrieve the underlying image as a Java AWT image object. This is
	 * the actual underlying image, so use it with care.
	 * @return The java.awt.Image object that is encapsulated.
	 */
	public java.awt.Image asAWTImage() {
		return img;
	}
	
	/**
	 * Set a pixel of the underlying image to a specific color.
	 * @param x The horizontal coordinate of the pixel within the image. 
	 * @param y The vertical coordinate of the pixel within the image.
	 * @param color The color to set the pixel.
	 */
	public void setPixel(int x, int y, Color color) {
		img.setRGB(x,y,color.asInt());
	}
	
	/**
	 * Clears the whole image with a given color.
	 * @param color The color to fill the whole image. 
	 */
	public void clear(Color color) {
		Graphics2D g = img.createGraphics();
		g.setBackground(color.asAWTColor());
		g.clearRect(0, 0, img.getWidth(), img.getHeight());
	}
	
	/**
	 * Resizes the image to a new width and height. The contents of the
	 * original image are scaled to the new size, using bi-linerar,
	 * anti-aliasing algorithms.
	 * @param width The new width of the image.
	 * @param height The new height of the image.
	 */
	@Override
	public void resize(int width, int height) {
		BufferedImage i;
		i= new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = i.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(img,0,0,width,height,null);
		g.dispose();
		img = i;
	}	
	
	/**
	 * Query the width of the image.
	 * @return The image width.
	 */
	public int getWidth() {
		return img.getWidth();
	}
	
	/**
	 * Query the height of the image.
	 * @return The image height.
	 */
	public int getHeight() {
		return img.getHeight();
	}
	
	/**
	 * Draw a line between two points using the provided drawing
	 * color, with the Bresenhan's algorithm.
	 * @param x0 The x coordinate of the first point.
	 * @param y0 The y coordinate of the first point.
	 * @param x1 The x coordinate of the second point.
	 * @param y1 The y coordinate of the second point.
	 * @param color The color to use to draw the line.
	 */
	public synchronized
	void drawLine(int x0, int y0, int x1, int y1, Color color) {
		Util.bresenham(x0,y0,x1,y1,
				       (a,b) -> this.setPixel((int)a,(int)b,color));
	}

}

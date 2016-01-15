package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dot extends Object {

	public Dot(double x, double y, double radius) throws IOException {
		super(x, y, radius);
	}

	public void run(Main m, Graphics g) {
		draw(g);
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.red);
		g2.fillOval((int) (x - radius), (int) (y - radius), (int) radius * 2,
				(int) radius * 2);
	}

}

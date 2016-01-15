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

public class Arrow extends Object {

	double rotate = 0;
	double speed = 0.05;
	
	BufferedImage img = null;

	public Arrow(double x, double y, double radius) throws IOException {
		super(x, y, radius);  
		img = ImageIO.read(new File("Images/download.png"));
	}

	public void run(Main m, Graphics g) {

		if (rotate > 2 * Math.PI) {
			rotate -= 2 * Math.PI;
		}
		rotate += speed;
		if (m.fired == true) {
			int collision = checkCollision(m);
			if (collision != -1) {
				speed = -speed;
				try {
					m.hit(collision);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			//m.fired = false;
		}
		
		draw(g);

	}

	public int checkCollision(Main m) {
		int collided = -1;
		for (int i = 0; i < m.dot.size(); i++) {
			// x co or of other dot = x - radius
			double xdiff = (m.dot.get(i).x) - (x + img.getWidth() / 2);
			double ydiff = (m.dot.get(i).y) - (y + img.getHeight() / 2);
			double distance = Math.sqrt((xdiff * xdiff) + (ydiff * ydiff));

			int tempx = (int) (x + (img.getWidth() / 2) + (distance * Math
					.cos(rotate)));

			int tempy = (int) (y + (img.getWidth() / 2) + (distance * Math
					.sin(rotate)));
			xdiff = (m.dot.get(i).x) - tempx;
			ydiff = (m.dot.get(i).y) - tempy;
			// System.out.println(xdiff * xdiff + ydiff * ydiff);
			if (Math.sqrt(xdiff * xdiff + ydiff * ydiff) <= m.dot.get(i).radius) {

				collided = i;

				break;

			}
		}

		return collided;
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		double rotationRequired = rotate;
		double locationX = img.getWidth() / 2;
		double locationY = img.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(
				rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		g2.setColor(Color.BLACK);
		// Drawing the rotated image at the required drawing locations
		g2.drawImage(op.filter(img, null), null, (int) x, (int) y);

	}

}

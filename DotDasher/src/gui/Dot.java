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

	int state = 0;
	double rotate = 0.9;
	double speed = 0.01;
	BufferedImage img = null;

	public Dot(double x, double y, double radius) throws IOException {
		super(x, y   , radius);
		img = ImageIO.read(new File("Images/download.png"));

	}

	public void run(Main m, Graphics g) {

		if (state == 0) {

			draw(g);
		} else {
			if (rotate > 2 * Math.PI) {
				rotate -= 2 * Math.PI;
			}
			rotate += speed;
			if (m.fired == true) {
				int collision = checkCollision(m);
				if ( collision !=  -1){
					m.hit(collision);
				}
				//m.fired = false;
			}

			draw(g);

		}

	}

	public int checkCollision(Main m) {
		int collided = -1;
		for (int i = 0; i < m.dot.size(); i++) {
			if (m.dot.get(i).getState() == 0) {
				double xdiff = m.dot.get(i).x - x;
				double ydiff = m.dot.get(i).y - y;

				double distance = Math.sqrt((xdiff * xdiff) + (ydiff * ydiff));

				int tempx = (int) (x + img.getWidth() / 2 + distance
						* Math.cos(rotate));

				int tempy = (int) (y + img.getWidth() / 2 + distance
						* Math.sin(rotate));
				xdiff = (m.dot.get(i).x) - tempx;
				ydiff = (m.dot.get(i).y) - tempy;
				if (Math.sqrt(xdiff * xdiff + ydiff * ydiff) <= m.dot.get(i).radius) {
					collided = i;
					break;
				}
			}
		}
		return collided;
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (state == 0) {
			g2.setColor(Color.red);
			g2.fillOval((int) (x - radius), (int) (y - radius),
					(int) radius * 2, (int) radius * 2);
		} else {
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

			g.drawLine((int) (x + img.getWidth() / 2),
					(int) (y + img.getWidth() / 2), (int) (x + img.getWidth()
							/ 2 + 500 * Math.cos(rotate)),
					(int) (y + img.getWidth() / 2 + 500 * Math.sin(rotate)));

		}

	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}

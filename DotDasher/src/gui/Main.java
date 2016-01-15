package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JPanel implements KeyListener {
	int dotNum, score, iWidth, iHeight;
	boolean move, fired;

	Arrow p1,p2;
	ArrayList<Dot> dot = new ArrayList<Dot>();
	ArrayList<Integer> lines = new ArrayList<Integer>();

	public Main() throws IOException {
		setSize(1000, 1000);
		setPreferredSize(new Dimension(1000, 1000));
		setFocusable(true);
		addKeyListener(this);

		p1 = new Arrow(500, 500, 50);
	
		dot.add(new Dot(250, 140, 50));
	

	}

	// looping section which redraws frame
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		iWidth = getWidth();
		iHeight = getHeight();
		
		g.fillRect(0, 0, iWidth, iHeight);
		g.setColor(Color.black);
		g.drawString("score:" + score, 50, 50);
		p1.run(this, g);
	
		for (int i = 0; i < dot.size(); i++) {
			dot.get(i).run(this, g);
		}
		g.setColor(Color.RED);

		for (int i = 0; i < lines.size() / 4; i++) {
			g.drawLine(lines.get(4 * i), lines.get(4 * i + 1),
					lines.get(4 * i + 2), lines.get(4 * i + 3));
		}

		// Balls.add(new Ball(xcoor, ycoor, 30, 0));
		g.dispose();
		repaint();
	}

	public void hit(int collision) throws InterruptedException {
		score++;
		lines.add((int) p1.x + p1.img.getWidth() / 2);
		lines.add((int) p1.y + p1.img.getHeight() / 2);
		lines.add((int) dot.get(collision).x);
		lines.add((int) dot.get(collision).y);
		p1.x = dot.get(collision).x - p1.img.getWidth() / 2;
		p1.y = dot.get(collision).y - p1.img.getHeight() / 2;
		dot.remove(collision);
		Random r = new Random();
		Thread.sleep(17);
		try {
			dot.add(new Dot(r.nextInt(iWidth), r.nextInt(iHeight), 50));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Runs on startup, creates JFrame move to paint.
	public static void main(String args[]) throws IOException {
		Main main = new Main();
		JFrame f = new JFrame();
		f.setResizable(true);
		f.add(main);
		f.pack(); // f.setBackground(Color.black); // doesn't work
		f.setTitle("physics engine");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	// key actions
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_SPACE) {
			for (int i = 0; i < dot.size(); i++) {
				fired = true;
			}
		}
	}

	// key actions
	public void keyReleased(KeyEvent e) {

	}

	// key actions
	public void keyTyped(KeyEvent e) {

	}

}

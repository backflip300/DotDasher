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
public class Main extends JPanel implements KeyListener, MouseListener {
	int iMousex, iMousey, iTruex, iTruey, iChangex, iChangey, iWidth, iHeight,
			active;
	int dotNum,score;
	boolean move, fired;
	ArrayList<Dot> dot = new ArrayList<Dot>();

	public Main() throws IOException {
		setSize(1000, 1000);
		setPreferredSize(new Dimension(1000, 1000));
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		dot.add(new Dot(250, 140, 10));
		dot.add(new Dot(100,500,100));
		dot.add(new Dot(200,780,100));
		dot.add(new Dot(300,240,100));
		dot.add(new Dot(700,153,100));
		dot.get(0).setState(1);
		active = 0;
	}

	// looping section which redraws frame
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		iWidth = getWidth();
		iHeight = getHeight();
		g.fillRect(0, 0, iWidth, iHeight);
		g.drawString("score:" +score, 50, 50);
		reallign();
		for ( dotNum = 0; dotNum < dot.size(); dotNum++) {
			dot.get(dotNum).run(this,g);
		}
		g.setColor(Color.RED);
		// Balls.add(new Ball(xcoor, ycoor, 30, 0));
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.dispose();
		repaint();
	}

	public void reallign() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		iMousex = (int) b.getX();
		iMousey = (int) b.getY();
		iTruex = iMousex + iChangex;
		iTruey = iMousey + iChangey;
	}
	
	public void hit(int collision) {
		dot.get(collision).setState(1);
		dot.remove(dotNum);
		System.out.println("collision: " + collision + "dotnum:" + dotNum);
		for (int i = 0; i < dot.size();i++){
			System.out.println(dot.get(i).state);
		}
		
		Random r = new Random();
		try {
			dot.add(new Dot(r.nextInt(iWidth ),r.nextInt(iHeight),20));
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
	
	

	public void mouseClicked(MouseEvent e) {
		iChangex = e.getX() - iMousex;
		iChangey = e.getY() - iMousey;

	}

	// mouse actions
	public void mouseEntered(MouseEvent e) {
		iChangex = e.getX() - iMousex;
		iChangey = e.getY() - iMousey;

	}

	// mouse actions
	public void mouseExited(MouseEvent e) {
		iChangex = e.getX() - iMousex;
		iChangey = e.getY() - iMousey;

	}

	// mouse actions
	public void mousePressed(MouseEvent e) {

	}

	// mouse actions
	public void mouseReleased(MouseEvent e) {

	}

	// key actions
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_SPACE) {
			for (int i = 0; i < dot.size(); i++) {
				fired = true;
				if (dot.get(i).getState() == 1) {
					
				}

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

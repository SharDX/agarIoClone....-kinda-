import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Frame extends JPanel implements ActionListener, KeyListener, Runnable{
	public static player p = new player(100,100,35,35,0,0);
	public static Frame frame = new Frame();
	public Timer t = new Timer(5,this);
	public static Font font = new Font("ariel",35,25);
	public static food[] f = new food[32];
	public static int width;
	public static int height;
	Random rand = new Random();
	public double oldVY;
	public double oldVX;
	public static void main(String[] args) throws InterruptedException {
		frame.frame();
		frame.t.start();
		Thread e = new Thread(frame);
		e.start();	
		Random rand = new Random();
		//int size = rand.nextInt(15);
		int size = 10;
		for(int i=0;i<f.length;i++){
			f[i] = new food(rand.nextInt(1900),rand.nextInt(1000),3);
		}
		Thread CollisionChecker = new Thread() {
		    int start = 0;
			public void run() {
				System.out.println("collision thread started");
		    	while(true){
		    		if(start == f.length){
		    			start = 0;
		    		}
		    		if(p.contains(f[start]) && f[start].getMass() > 1){
		    			p.addSize((f[start].getMass()));
		    			f[start].setMass(0);
		    			f[start].update();
		    			f[start] = new food(rand.nextInt(1900),rand.nextInt(1000),3);
		    		}
		    		System.out.println("Logging");
		    		start++;
		    	}
			}
		};

		CollisionChecker.start();
		
	}
	public void frame(){
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setFocusable(true);
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setUndecorated(true);
		jf.setVisible(true);
		height = jf.getHeight();
		width = jf.getWidth();
		jf.addKeyListener(frame);
		jf.add(frame);		
	}
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.setFont(font);
		for(int i=0;i < f.length;i++){
			f[i].draw(g); 
		}
		Color color = new Color(rand.nextInt(0xFFFFFF));
		g.setColor(color);
		g.drawString("Noor", (int)p.getX(), (int)(p.getY()+p.getHeight()+20));
		p.draw(g);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(p.getX() + p.getWidth() > width){
			oldVY = p.getVy();oldVX = p.getVx();
			p.setX((int)(width -p.getWidth() -1));
			p.setVx(-(oldVX)/1.2);
			p.setVy(-(oldVY)/1.2);
		}else if(p.getX() < 0){
			oldVY = p.getVy();oldVX = p.getVx();
			p.setX(1);
			p.setVx(-(oldVX)/1.2);
			p.setVy(-(oldVY)/1.2);
		}
		if(p.getY() + p.getHeight() < 0){
			oldVY = p.getVy();oldVX = p.getVx();
			p.setY((int)(1));
			p.setVx(-(oldVX)/1.2);
			p.setVy(-(oldVY)/1.2);
		}else if(p.getY() + p.getHeight() > height){
			oldVY = p.getVy();oldVX = p.getVx();
			p.setY((int)(height -p.getHeight()- 1));
			p.setVx(-(oldVX)/1.2);
			p.setVy(-(oldVY)/1.2);
		}
		repaint();
		p.move();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			p.MoveRight(1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			p.MoveLeft(1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			p.MoveUp(1);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			p.MoveDown(1);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		while(true){
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(p.getVx() > 0.1){
			p.MoveLeft(0.5);
		}else if(p.getVx() < -0.1){
			p.MoveRight(0.5);
		}
		if(p.getVy() < -0.1){
			p.MoveDown(0.5);
		}else if(p.getVy() > 0.1){
			p.MoveUp(0.5);
		}
		}
	}
}

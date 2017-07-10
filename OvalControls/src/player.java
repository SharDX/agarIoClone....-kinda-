import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

@SuppressWarnings("serial")
public class player extends Rectangle{
	private int vx;
	private int vy;
	public player(int x,int y, int width, int height,int vx, int vy){
		this.setBounds(x, y, width, height);
	}
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(this.x, this.y, this.height, this.width);
	}
	public double getX(){
		return this.x;
		
	}
	public double getY(){
		return this.y;
	}
	public void move(){

		this.x += this.vx;
		this.y += this.vy;

	}
	public void MoveUp(double vy){
		this.vy -= vy;
	}
	public void MoveDown(double vy){
		this.vy += vy;
	}
	public void MoveLeft(double vx){
		this.vx -= vx;
	}
	public void MoveRight(double vx){
		this.vx += vx;
	}
	
	public void addSize(int size){
		this.height += size;
		this.width += size;
	}
	public double getVx(){
		return this.vx;
	}
	public double getVy(){
		return this.vy;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setVx(double vx){
		this.vx = (int) vx;
	}
	public void setVy(double vy){
		this.vy = (int) vy;
	}
}

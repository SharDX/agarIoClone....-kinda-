import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

@SuppressWarnings("serial")
public class food extends Rectangle{
	Random rand = new Random();
	Color color = new Color(rand.nextInt(0xFFFFFF));
	private int Mass;
	public food(int x,int y,int Mass){
		this.setBounds(x, y, Mass*5,Mass*5);
		this.Mass = Mass;
	}
	public void draw(Graphics g){
		g.setColor(color);
		g.fillRect(this.x, this.y, this.height, this.width);
	}
	public void update(){
		this.height = this.getMass()*5;
		this.width = this.getMass()*5;
	}
	public int getMass(){
		return this.Mass;
	}
	public void setMass(int Mass){
		this.Mass = Mass;
	}
}

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Circle {
	PointLinkedList pll=new PointLinkedList();
	int size;
	double r;
	public Circle(double r, int rawsCount){//create polygon based on figure//comment non-used figure 
		double angle;
		this.r=r;
		this.size=rawsCount;
		for (int t=0;t<rawsCount;t++){
			angle=2*Math.PI*t/rawsCount;
			pll.addPoint(r*Math.cos(angle),r*Math.sin(angle));//circle
			//pll.addPoint(r*(2*Math.cos(angle)-Math.cos(2*angle)),r*(2*Math.sin(angle)-Math.sin(2*angle))); //cardioid
		}
		
	}
	
	public int getSize(){
		return size; 
	}
		

}

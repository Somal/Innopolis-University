
public class Point extends MyPoint{//I extended MyPoint class for mix key and link to next Point
	public Point nextPoint;
	
	public Point(double x, double y,Point prevPoint){//creation of non-first point
		super(x, y);
		this.nextPoint=prevPoint.nextPoint;
		prevPoint.nextPoint=this;	
	}
	
	public Point(double x,double y){//creation of first point
		super(x,y);
		this.nextPoint=this;
	}


}

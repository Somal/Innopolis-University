import java.util.Iterator;
import java.util.List;

public class PointLinkedList{
	Point lastPoint;
	int size;
	ListIterator li=new ListIterator();//Iterator of this list

	public PointLinkedList() {// list starts with first element //0th element is
								// lastpoint
		size = 0;
	}

	public void addPoint(double x, double y) {//add new Point to end of the list
		Point p;
		if (size == 0)
			p = new Point(x, y);
		else
			p = new Point(x, y, lastPoint);
		lastPoint = p;
		size++;
	}

	public int size() {
		return size;
	}

	public void insert(int i, double x, double y) {// insert new Point after i-th element
		Point prevPoint = get(i);
		Point nextPoint = prevPoint.nextPoint;
		Point p = new Point(x, y, prevPoint);
		p.nextPoint = nextPoint;
		size++;
	}

	public void insert(int i, Point p) {//insert function which have argument Point instead (x,y)
		insert(i, p.getX(), p.getY());
	}

	public void delete(int i) {// delete function i-th point
		Point prevPoint = get(i - 1);
		prevPoint.nextPoint = prevPoint.nextPoint.nextPoint;
		size--;
	}

	public Point get(int i) {
		Point point = lastPoint;
		for (int j = 0; j < i; j++)
			point = point.nextPoint;
		return point;
	}

	public void set(int i, double x, double y) {
		Point p = get(i);
		p.setLocation(x, y);
	}

	public void set(int i, Point p) {
		set(i, p.getX(), p.getY());
	}

	public void show() {//printing of list
		System.out.println("------------------");
		/*
		 * Point p = lastPoint.nextPoint; for (int i = 0; i < size; i++) {
		 * System.out.println(p.getX()+" "+p.getY()); p = p.nextPoint; }
		 */
		ListIterator li = new ListIterator();
		while (li.hasNext()) {
			Point p = li.next();
			System.out.println(p.getX() + " " + p.getY());
		}
	}
	
	public void newIterator(){//creation of Iterator//reset of Iterator
		li=new ListIterator();
	}
	
	public boolean hasNext(){
		return li.hasNext();
	}
	
	public Point next(){
		
		return li.next();
	}
	
	
	public class ListIterator implements Iterator<Point> {//inner Iterator class
		int index = 0;

		@Override
		public boolean hasNext() {
			return index <size;
		}

		@Override
		public Point next() {
			return get(++index);
		}

	}


}

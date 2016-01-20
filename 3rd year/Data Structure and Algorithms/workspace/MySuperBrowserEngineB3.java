/**
 * This is a template code for Assignment #1
 * for DSA course: Bachelor-3
 * 
 * @author Stanislav I. Protasov
 * @company Innopolis University
 * 
 */
public class MySuperBrowserEngineB3 {

	/**
	 * This is a class that represents a real 2D point 
	 */
	public static class Point2D {
		private double x, y;
		public double getX() { return x; }
		public double getY() { return y; }
		
		public String toString() {
			return "[" + x + ", " + y + "]";
		}

		public Point2D(double x, double y) {
			this.x = x; this.y = y;
		}
	}

	/**
	 * This is a class that represents rectangle
	 * in Cartesian coordinates
	 */
	public static class Rectangle {
		/** these are corner points of rectangle */
		private Point2D lowerLeft, upperTop;
		/** this tag is used to store additional info
		 * in our case - to save a word represented by rectangle */
		private String tag;
		public String getTag() { return tag; }
		
		/**
		 * Constructor for rectangle
		 * @param lowerLeft lower left angle of rectangle
		 * @param upperTop upper top angle or rectangle
		 * @param tag word that is represented by rectangle
		 */
		public Rectangle(Point2D lowerLeft, Point2D upperTop, String tag) {
			this.lowerLeft = lowerLeft;
			this.upperTop = upperTop;
			this.tag = tag;
		}

		@Override
		public String toString() {
			return lowerLeft.toString() + "-" + upperTop.toString();
		}
		
		/**
		 * Methods returns 4 pairs of points. Each pair represents an edge or rectangle
		 * @return returns Point2D[4][2] array
		 */
		public Point2D[][] getEdges() {
			Point2D[][] edges = new Point2D[4][2];
			edges[0][0] = lowerLeft;
			edges[0][1] = new Point2D(lowerLeft.getX(), upperTop.getY());
			edges[1][0] = edges[0][1];
			edges[1][1] = upperTop;
			edges[2][0] = upperTop;
			edges[2][1] = new Point2D(upperTop.getX(), lowerLeft.getY());
			edges[3][0] = edges[2][1];
			edges[3][1] = lowerLeft;
			return edges;
		}
	}

	/**
	 * TODO implement at least these methods
	 */
	public static class MyList {
		public void add(int i, Object o) { throw new Error("not implemented"); }
		public void addLast(Object o) { throw new Error("not implemented"); }
		public Object get(int i) { throw new Error("not implemented"); }
		public int size() { throw new Error("not implemented"); }
		public boolean isEmpty() { throw new Error("not implemented"); }
	}

	/**
	 * TODO implement at least these methods
	 */
	public static class MyStack extends MyList {
		public void push(Object o) { throw new Error("not implemented"); }
		public Object pop() { throw new Error("not implemented"); }
	}
	
	/**
	 * TODO implement
	 * Intersection method checks if section ab intersects with section cd.
	 * @param a section 1 point 1
	 * @param b section 1 point 2
	 * @param c section 2 point 1
	 * @param d section 2 point 2
	 * @return true if sections intersect
	 */
	public static boolean intersects(Point2D a, Point2D b, Point2D c, Point2D d) {
		throw new Error("not implemented");
	}
	
	/**
	 * TODO implement
	 * gets a stack that contains all rectangles that have intersection with the line
	 * @param rects rectangles to check
	 * @param lineStart starting point of the line
	 * @param lineFinish end point of the line
	 * @return
	 */
	public static MyStack getIntersected(MyList rects, Point2D lineStart, Point2D lineFinish) {
		throw new Error("not implemented");
	}

	/**
	 * Method takes text and calculates rectangles that represent words
	 * @param text input string
	 * @param oneLetterSize size of one letter in monospace font
	 * @param startCorner corner where text starts
	 * @return list of rectangles associated with words in text
	 */
	public static MyList getRectangles(String text, Point2D oneLetterSize, Point2D startCorner) {
		MyList list = new MyList();
		String[] words = text.split(" ");
		int position = 0;
		for (String word: words) {
			if (word.length() > 0) {
				Point2D ll = new Point2D(
						startCorner.getX() + position * oneLetterSize.getX(),
						startCorner.getY());
				Point2D ut = new Point2D(
						ll.getX() + word.length() * oneLetterSize.getX(),
						startCorner.getY() + oneLetterSize.getY());
				list.addLast(new Rectangle(ll, ut, word));
			}
			position += word.length() + 1;
		}
		return list;
	}
	
	
	public static void main(String[] args) {
		String inputData = "";
		Point2D textCorner, letterSize, lineStart, lineEnd;
		
		textCorner = letterSize = lineEnd = lineStart = new Point2D(0, 0);
		// TODO read input data here
		
		MyList list = getRectangles(inputData, letterSize, textCorner);
		MyStack stack = getIntersected(list, lineStart, lineEnd);
		while (!stack.isEmpty())
		{
			String word = ((Rectangle)stack.pop()).getTag(); 
			// TODO write output data	
		}
	}

}
/**
 * guid 5bd29cec-2e7a-4c93-9112-7f0ad68865c4
 */

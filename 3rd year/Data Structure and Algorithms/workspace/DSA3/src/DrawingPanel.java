import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kamil on 31/08/15. Panel for drawing polygons
 */
public class DrawingPanel extends JPanel {
	Graphics g2;
	Circle c;
	int dx = 150, dy = 150;
	int pointCount = 100000;
	int width = 300, height = 300;
	int minX=width, minY=height, maxX=0, maxY=0;

	/**
	 * Constructor for drawing panel. If you wish you may add your own
	 * parameters or create new constructor
	 */
	public DrawingPanel() {
		super();
		setBackground(Color.YELLOW); // Just to make it more beautiful:)
		c = new Circle(100, 360);//create new polygon

	}

	/**
	 * Function for drawing polygons within drawing panel
	 * 
	 * @param g
	 *            -- graphic component of drawing panel You may derive arrays of
	 *            x and y coordinates and use g2.drawPolygon method Do not
	 *            forget to move center points of your figures to the center of
	 *            the panel
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g; // more modern graphic component
		// ------------------------------------------------------------------
		// Derive coordinates for circle or cardioid and store it in your list
		// ------------------------------------------------------------------
		// int[] xs = new int[N]; // stores x coordinates of polygon points
		// int[] ys = new int[N]; // stores y coordinates of polygon points
		// g2.drawPolygon(xs, ys, precise);

		//drawing polygon
		int[] xs = new int[c.getSize()];
		int[] ys = new int[c.getSize()];
		Point p = c.pll.get(1);
		for (int i = 0; i < c.getSize(); i++) {
			xs[i] = (int) p.getX() + dx;
			ys[i] = (int) p.getY() + dy;
			minX = Math.min(minX, xs[i]);
			minY = Math.min(minY, ys[i]);
			maxX = Math.max(maxX, xs[i]);
			maxY = Math.max(maxY, ys[i]);

			p = p.nextPoint;
		}
		g2.drawPolygon(xs, ys, c.getSize());

		
		//---------Monte-Carlo algorithm
		Random r = new Random();
		int goodP = 0;
		for (int i = 0; i < pointCount; i++) {
			int x = r.nextInt(maxX - minX) - dx + minX;
			int y = r.nextInt(maxY - minY) - dy + minY;
			if (inside(c.pll, new Point(x, y))) {
				g2.setPaint(Color.RED);
				goodP++;
			} else
				g2.setPaint(Color.GREEN);
			g2.drawLine(x + dx, y + dy, x + 1 + dx, y + 1 + dy);

		}
		System.out.println( (maxX-minX)*(maxY-minY)*1.0d/pointCount * goodP );
	}

	/**
	 * Checks if there is an intersection between line ab and cd using Kramer's
	 * rule
	 * 
	 * @param a
	 *            -- beginning point of ab
	 * @param b
	 *            -- ending point of ab
	 * @param c
	 *            -- beginning point of cd
	 * @param d
	 *            -- ending point of cd
	 * @return true if there is an intersection between ab and cd and false
	 *         otherwise
	 */
	public static boolean intersects(Point a, Point b, Point c, Point d) {

		// We describe the section AB as A+(B-A)*u and CD as C+(D-C)*v
		// then we solve A + (B-A)*u = C + (D-C)*v
		// let's use Kramer's rule to solve the task (Ax = B) were x = (u, v)^T
		// build a matrix for the equation
		double[][] A = new double[2][2];
		A[0][0] = b.getX() - a.getX();
		A[1][0] = b.getY() - a.getY();
		A[0][1] = c.getX() - d.getX();
		A[1][1] = c.getY() - d.getY();
		// calculate determinant
		double det0 = A[0][0] * A[1][1] - A[1][0] * A[0][1];
		// substitute columns and calculate determinants
		double detU = (c.getX() - a.getX()) * A[1][1] - (c.getY() - a.getY()) * A[0][1];
		double detV = A[0][0] * (c.getY() - a.getY()) - A[1][0] * (c.getX() - a.getX());
		// calculate the solution
		// even if det0 == 0 (they are parallel) this will return NaN and
		// comparison will fail -> false
		double u = detU / det0;
		double v = detV / det0;
		return u > 0 && u < 1 && v > 0 && v < 1;
	}

	/**
	 * Checks if @param point lies within @param polygon
	 * 
	 * @param polygon
	 *            your
	 * @param point
	 * @return
	 */
	public static boolean inside(PointLinkedList polygon, Point point) {
		polygon.newIterator();
		Point prevPoint = polygon.next();
		int count = 0;
		while (polygon.hasNext()) {
			Point p = polygon.next();
			if (intersects(new Point(0, 0), point, prevPoint, p))
				count++;
			prevPoint = p;
		}
		return (count % 2 == 0);
	}
}

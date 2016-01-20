import java.awt.geom.Point2D;

/**
 * Created by kamil on 31/08/15.
 * Class for Points
 */
public class MyPoint extends Point2D {

    private double x,y; // coordinates

    public MyPoint(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

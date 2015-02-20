import java.lang.*;
import java.util.Stack;
import java.util.Arrays;
import java.util.Comparator;
/**
 * Pete Oram
 * ITCS-2215-003
 * Assignment #3 
 * Convex Hull (Graham's Scan)
 * #800159179
 * 4.23.2014
 */

public class PlanePoint
{
    public static final Comparator<PlanePoint>  ORDER_Y = new YOrder();
    public final Comparator<PlanePoint> P_ORDER = new PolarOrder();
    private static class YOrder implements Comparator<PlanePoint>
    {
        public int compare(PlanePoint pointkey01, PlanePoint pointkey02)
        {
            if(pointkey01.y > pointkey02.y) return 1; //Evaluate position in cartesian plane
            if(pointkey01.y < pointkey02.y) return -1;
            return 0;
        }
    }
    private class PolarOrder implements Comparator<PlanePoint>
    {
        public int compare(PlanePoint pointkey01, PlanePoint pointkey02)
        {	
            double angle1, angle2;
            angle1 = Math.atan2(convertY(pointkey01.y), convertX(pointkey01.x));
            angle2 = Math.atan2(convertY(pointkey02.y), convertX(pointkey02.x));
            if(angle1 > angle2) return 1;
            if(angle1 < angle2) return -1;
            return 0;
        }
    }    
    double x,y;
    public double convertX(double x){ return x-getX(); }
    public double convertY(double y){ return y-getY(); }
    public double getX(){ return x; }
    public double getY(){ return y; }
    
    //Takes in the coordinate positions (x and y)
    public PlanePoint(double x, double y)       
    {
        this.x = x;
        this.y = y;
    }
    
    public static int position(PlanePoint a, PlanePoint b, PlanePoint c)
    {
        double area2 = (b.x-a.x)*(c.y-a.y)-(b.y-a.y)*(c.x-a.x);
        if 		(area2 < 0) return -1; // clockwise position
        else if (area2 > 0) return +1; // counter-clockwise position
        else				return  0; // collinear position
    }
    
    public String toString() { return "point (" + x + ", " + y + ")"; } //print points
}
import java.lang.*;
import java.util.Stack;
import java.util.Arrays;
/**
 * Pete Oram
 * ITCS-2215-003
 * Assignment #3 
 * Convex Hull (Graham's Scan)
 * #800159179
 * 4.23.2014
 */
public class Program
{
    private static void print(PlanePoint []point, int num) {
            for(int i = 0; i < num; i++)
            System.out.println(point[i].toString());
    }
    
    public static void main(String args[]) 
    {
        int num = 5;
        PlanePoint []point = new PlanePoint[num];
        point[0]           = new PlanePoint(0,3);
        point[1]           = new PlanePoint(2,2);
        point[2]           = new PlanePoint(3,3);
        point[3]           = new PlanePoint(-3,0);
        point[4]           = new PlanePoint(2,-2);
        Stack<PlanePoint> hull = new Stack<PlanePoint>();
        //print input data
        System.out.println("Pre Convex-Hull Algorithm Input data: \n");
        print(point,num);
        
        Arrays.sort(point, PlanePoint.ORDER_Y);
        Arrays.sort(point, point[0].P_ORDER);
        
        hull.push(point[0]);
        hull.push(point[1]);
        
        for(int i = 2; i < num; i++)
        {
            PlanePoint top = hull.pop();
            while(PlanePoint.position(hull.peek(), top, point[i]) <= 0)
                  top = hull.pop();
            //System.out.println(top.toString());
            hull.push(top);
            hull.push(point[i]);
        }
        //print sorted data
        System.out.println("\nPost Convex-Hull Processed Data: \nClockwise order\n");
        while(!hull.empty())
                  System.out.println(hull.pop().toString());
        
    }
}

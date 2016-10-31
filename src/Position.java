
import java.util.*;

/**
 * 
 */
public class Position {

    /**
     * Default constructor
     */
    public Position() {
    }

    /**
     * 
     */
    public double x;

    /**
     * 
     */
    public double y;

    /**
     * @param x
     * @param y
     * @param ...
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Position( double angleD)
  {
    double angleRadians = Math.toRadians( angleD ) ;

    this.x = Math.cos( angleRadians ) ;
    this.y = Math.sin( angleRadians ) ;
  }
    /**
     * @return
     */
    public void normalize() {
        /*double norm = Math.sqrt( ( x * x ) + ( y * y ) ) ;
        
        x =  x/norm ;
        y = y/norm ;  */  
        try
        {
            double theta = Math.atan2(y, x);
            x = Math.cos(theta);
            y = Math.sin(theta);
        }
        catch(ArithmeticException e)
        {
        }
        
    }
    
    public void setDirection ( Position from , Position to )
  {
    x = to.x - from.x ;
    y = to.y - from.y ;

    normalize() ;
  }

    /**
     * @param other 
     * @return
     */
    public double distanceTo(Position other) {
        double deltaX = x - other.x ;
        double deltaY = y - other.y ;

        return Math.sqrt( (deltaX * deltaX) + (deltaY * deltaY) ) ;
    }

}
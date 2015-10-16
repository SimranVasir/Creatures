/**
 * Represents a direction in the xy plane.
 * <p>
 * This class includes MANY methods of constructing and transforming directions.
 * You can probably do all your direction calculations by calling the right
 * method of this class!
 * 
 * @author CPSC 111 instructors
 */
public class Direction
{
    /**
     * The direction as an angle in degrees.
     */
    private double direction;
    /**
     * Number of degrees to turn all the way around. No surprises here.
     */
    public static final double FULL_CIRCLE = 360.0;
    /**
     * Convenience method to calculate the distance between two given points.
     * 
     * @param x1 the x-coord of the first point
     * @param y1 the y-coord of the first point
     * @param x2 the x-coord of the second point
     * @param y2 the y-coord of the second point
     * @return the distance between the two points
     */
    public static double distance( double x1, double y1, double x2, double y2 )
    {
        return Math
                .sqrt( ( y2 - y1 ) * ( y2 - y1 ) + ( x2 - x1 ) * ( x2 - x1 ) );
    }

    /**
     * Construct a direction at the given angle in degrees (e.g., 0 to 360
     * degrees, although this can handle angles outside that range).
     * 
     * @param direction a direction in degrees
     */
    public Direction( double direction )
    {
        if( direction == Double.POSITIVE_INFINITY
                || direction == Double.NEGATIVE_INFINITY
                || direction == Double.NaN )
            direction = 0.0;
        direction = direction % FULL_CIRCLE;
        if( direction < 0 )
            direction = direction + FULL_CIRCLE;

        this.direction = direction;
    }

    /**
     * Construct a direction from the given vector. In other words, if one
     * object were x units right of another and y units below another, the
     * direction from the first object to the second would be the direction
     * constructed by this method.
     * <p>
     * This method DOES handle x and y values of 0. If BOTH are 0, this just
     * gives a direction pointing directly right.
     * 
     * @param x the distance in the x direction (rightward) to an object pointed
     *        to by this direction
     * @param y the distance in the y direction (downward) to an object pointed
     *        to by this direction
     */
    public Direction( double x, double y )
    {
        this( Direction.createDirectionRadians( Math.atan2( -y, x ) )
                .getDirection() );
    }

    /**
     * Get the direction in degrees represented by this direction.
     * 
     * @return a direction in degrees on the range [0, 360)
     */
    public double getDirection()
    {
        return this.direction;
    }

    /**
     * Get the direction in radians represented by this direction.
     * 
     * @return a direction in radians on the range [0, 2*Math.PI)
     */
    public double getDirectionRadians()
    {
        return this.getDirection() / (FULL_CIRCLE/2) * Math.PI;
    }

 
    /**
     * Construct a direction at the given angle in radians (e.g., 0 to 2*Math.PI
     * radians, although this can handle angles outside that range).
     * 
     * @param radians a direction in radians
     * @return the indicated direction
     */
    public static Direction createDirectionRadians( double radians )
    {
        return new Direction( radians / Math.PI * (FULL_CIRCLE/2) );
    }

 

    public boolean equals( Object obj )
    {
        // Hand off responsibility on someone else if the object is not a
        // Direction
        if( !( obj instanceof Direction ) )
            return super.equals( obj );

        // Cast the object to a CreatureInfo.
        Direction other = (Direction) obj;

        // Check that all fields match.
        return other.direction == this.direction;
    }
}

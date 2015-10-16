/**
 * A creature that takes a random walk around the screen, getting less intense the
 * older it gets and happier the further it is from whatever point it's at the
 * first time getMood is called (which is not necessarily its starting point,
 * but probably is).
 * 
 * @author CPSC 111 instructors
 */
public class RandomWalkingBrain implements ICreatureBrain
{
    /**
     * The distance beyond which the creature is perfectly happy.
     */
    public static double PERFECT_GROOVINESS_DISTANCE = 100.0;

    /**
     * Number of time steps it takes for intensity to fade.
     */
    public static double AGE_RATE = 500.0;
    
    /** Has this creature brain picked a starting point yet? */
    private boolean initialized = false;

    /** The creature brain's starting x. */
    private double firstX;

    /** The creature brain's starting y. */
    private double firstY;

    /**
     * Calculate the creature's grooviness if it's at the given point.
     * 
     * @param x the x-coord of the location
     * @param y the y-coord of the location
     * @return the appropriate grooviness level for the given point (may be more
     *         than MAX_GROOVINESS)
     */
    private double getGrooviness( double x, double y )
    {
        final double distance = Direction.distance( x, y, this.firstX,
                this.firstY );
        double groovinessRatio = distance / PERFECT_GROOVINESS_DISTANCE;
        return CreatureMood.MAX_GROOVINESS * groovinessRatio;
    }

    /**
     * Calculate the creature's intensity at the given age.
     * <p>
     * The intensity SLOWLY drops with age.
     * 
     * @param age the age of the creature
     * @return the appropriate intensity level for the given age
     */
    private double getIntensity( int age )
    {
        return CreatureMood.MAX_INTENSITY / ( age / AGE_RATE + 1.0 );
    }

    /**
     * Choose a direction at random.
     */
    public Direction chooseDirection( CreatureInfo me, IPackFinder packFinder )
    {
        return new Direction( Math.random() * Direction.FULL_CIRCLE );
    }

    /**
     * Choose a mood based on my distance from the first point where I was
     * created. Intensity goes down with age.
     */
    public CreatureMood chooseMood( CreatureInfo me, IPackFinder packFinder )
    {
        // Pick a starting point if it's never been done before.
        if( !this.initialized )
        {
            this.firstX = me.getX();
            this.firstY = me.getY();
            this.initialized = true;
        }

        return new CreatureMood( this.getGrooviness( me.getX(), me.getY() ), this
                .getIntensity( me.getAge() ) );
    }

    /**
     * @return Returns the creature's first x coordinate (if it has been
     *         initialized)
     */
    public double getFirstX()
    {
        return this.firstX;
    }

    /**
     * @return Returns the creature's first y coordinate (if it has been
     *         initialized).
     */
    public double getFirstY()
    {
        return this.firstY;
    }

    /**
     * @return Returns whether the starting point has been initialized.
     */
    public boolean isInitialized()
    {
        return this.initialized;
    }
}

import acm.graphics.*;
import java.awt.*;

/**
 * A creature that lives in our simulated world. Creatures have a physical
 * presence in the world but are guided by their brains and their packs.
 * 
 * @author CPSC 111 instructors
 */
public class Creature extends GTurtle
{
    /**
     * Color when 100% groovy at full intensity.
     */
    public static final Color GROOVY_COLOR = Color.YELLOW;

    /**
     * Color when 100% ungroovy at full intensity.
     */
    public static final Color UNGROOVY_COLOR = Color.BLUE;

    /**
     * Interpolates between min and max using level. Level must be [0, 1].
     * <p>
     * Useful for picking a mood colour!
     * 
     * @param min the value for level == 0
     * @param max the value for level == 1
     * @param level the interpolation level, [0, 1]
     * @return the interpolated value
     */
    private static double interpolate( double min, double max, double level )
    {
        return min + ( max - min ) * level;
    }

    /**
     * Default distance to move in one step, in pixels.
     */
    // TODO: You may want to change this number to have the Creatures move faster
	// or slower when debugging.
    public static final int DEFAULT_SPEED = 5;

    /**
     * Default size of a creature, in pixels.
     */
    public static final int DEFAULT_SIZE = 20; 

    /**
     * The creature's current mood.
     */
    private CreatureMood mood = new CreatureMood( 0, 0 );

    /**
     * The creature's current age (in steps taken).
     */
    private int age = 0;

    /**
     * The creature's brain: which makes decisions about direction and mood
     * changes.
     */
    private ICreatureBrain brain;

    /**
     * The creature's pack finder, which defines its pack.
     */
    private IPackFinder packFinder;

    /**
     * Construct a new creature with the given brain and packFinder.
     * 
     * @param brain a non-null brain to use for the creature
     * @param packFinder a non-null packFinder to use for the creature
     */
    public Creature( ICreatureBrain brain, IPackFinder packFinder )
    {
        this( brain, packFinder, 0.0, 0.0, null, DEFAULT_SPEED );
    }

    /**
     * Create a new creature with the given data.
     * 
     * @param brain the creature's brain (non-null)
     * @param packFinder the creature's pack finder (non-null)
     * @param x the creature's starting x position
     * @param y the creature's starting x position
     * @param mood the creature's mood (may be null for a default mood)
     * @param speed the creature's speed
     */
    public Creature( ICreatureBrain brain, IPackFinder packFinder, double x,
            double y, CreatureMood mood, double speed )
    {
        super( x, y );
        this.setSize(DEFAULT_SIZE);

        // Start at age 0 (0 steps taken).
        this.age = 0;

        // Create a default mood if necessary.
        if( mood == null )
            mood = new CreatureMood( 0, 0 );

        this.setBrain( brain );
        this.setMood( mood );
        this.setSpeed( speed );
        this.setPackFinder( packFinder );
        this.setSpeed( speed );
     }

    /**
     * Fetch the creature's current pack finder.
     * 
     * @return the current pack finder
     */
    public IPackFinder getPackFinder()
    {
        return this.packFinder;
    }

    /**
     * Set the creature's current pack finder.
     * 
     * @param packFinder the new pack finder (non-null)
     */
    public void setPackFinder( IPackFinder packFinder )
    {
        this.packFinder = packFinder;
    }

    /**
     * Fetch the creature's current info. The info fully describes the creature's
     * state without allowing changes to that state.
     * 
     * @return the creature's current info
     */
    public CreatureInfo getInfo()
    {
        return new CreatureInfo( this );
    }

    /**
     * Fetch the creature's age
     * 
     * @return the creature's age in number of steps taken
     */
    public int getAge()
    {
        return this.age;
    }

    /**
     * Fetch the creature's current brain.
     * 
     * @return the creature's current brain
     */
    public ICreatureBrain getBrain()
    {
        return this.brain;
    }

    /**
     * Set the creature's current brain.
     * 
     * @param brain the new brain (non-null)
     */
    public void setBrain( ICreatureBrain brain )
    {
        this.brain = brain;
    }

    /**
     * Fetch the creature's current mood.
     * 
     * @return the creature's current mood (may be null for default mood)
     */
    public CreatureMood getMood()
    {
        return this.mood;
    }

    /**
     * Set the creature's current mood.
     * 
     * @param mood the new mood (may be null for default mood)
     */
    public void setMood( CreatureMood mood )
    {
        if( mood == null )
            mood = new CreatureMood( 0, 0 );

        this.mood = mood;

        // Leave a mood-coloured trail and turn mood-coloured.
        this.penDown();

        // Calculate how far between ungroovy and groovy we are.
        double interpolationParameter = ( this.getMood().getGrooviness() - CreatureMood.MIN_GROOVINESS )
                / ( CreatureMood.MAX_GROOVINESS - CreatureMood.MIN_GROOVINESS);

        // Build a mood colour based on grooviness.
        double red = interpolate( UNGROOVY_COLOR.getRed(), GROOVY_COLOR
                .getRed(), interpolationParameter );
        double green = interpolate( UNGROOVY_COLOR.getGreen(), GROOVY_COLOR
                .getGreen(), interpolationParameter );
        double blue = interpolate( UNGROOVY_COLOR.getBlue(), GROOVY_COLOR
                .getBlue(), interpolationParameter );

        // Calculate how intense we are.
        interpolationParameter = ( this.getMood().getIntensity() - CreatureMood.MIN_INTENSITY )
				/ (CreatureMood.MAX_INTENSITY - CreatureMood.MIN_INTENSITY);
        
        // Transform the mood colour based on intensity.
        red = interpolate( 0, red, interpolationParameter );
        green = interpolate( 0, green, interpolationParameter );
        blue = interpolate( 0, blue, interpolationParameter );
        
        this.setColor( new Color( (int)red, (int)green, (int)blue ) );
    }

    /**
     * Update this creature to its next step.
     * <p>
     * Note: remember that creature's MUST be supplied non-null brains and
     * packFinders in their constructors. If either was null, the error is
     * likely to show up here.
     */
    public void update()
    {
        // Ask my brain for a direction and mood.
        CreatureInfo info = this.getInfo();
        Direction newDirection = this.getBrain().chooseDirection( info,
                this.getPackFinder() );
        CreatureMood newMood = this.getBrain().chooseMood( info,
                this.getPackFinder() );

        // Update my direction and mood.
        this.setDirection( newDirection.getDirection() );
        this.setMood( newMood );

        // Move.
        this.forward( this.getSpeed() );

        // Update my age.
        this.age++;
    }

    /**
     * New instructions on how to draw the creature.
     */
    public void drawTurtle( Graphics g )
    {
        Component comp = getComponent();
        if( comp == null )
            return;
        int x = (int) Math.round( getX() );
        int y = (int) Math.round( getY() );
        int size = getTurtleSize();
        GPolygon p = new GPolygon( x, y );
        int tailFactor = 5;
        int waistFactor = 4;
        p.addVertex( size / 2, 0 );
        p.addVertex( -size / waistFactor, -size / 2 );
        p.addVertex( -size / 2 + size / tailFactor, 0 );
        p.addVertex( -size / 2, -size / tailFactor );
        p.addVertex( -size / 2, +size / tailFactor );
        p.addVertex( -size / 2 + size / tailFactor, 0 );
        p.addVertex( -size / waistFactor, size / 2 );
        p.rotate( -getDirection() );
        p.setFilled( true );
        p.setColor( this.getColor() );
        p.paint( g );
    }
}

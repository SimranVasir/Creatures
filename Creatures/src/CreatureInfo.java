import acm.graphics.GContainer;

/**
 * A full description of the state of a Creature. Why have this separate class
 * rather than looking directly at the Creature? Well, any method that gets a
 * reference to a Creature can actually CHANGE its state, which a creature brain
 * should be able to do. So, instead, we give the creature brains this info which
 * is immutable (just as a String is immutable).
 * 
 * @author CPSC 111 instructors
 */
public class CreatureInfo
{
    /**
     * The creature's unique ID. Every creature in a given simulation should have its
     * own distinct ID.
     */
    private int id;

    /** The creature's age in steps taken since creation. */
    private int age;

    /** The creature's x location. */
    private double x;

    /** The creature's x location. */
    private double y;

    /** The creature's current mood. */
    private CreatureMood mood;

    /** The creature's current direction. (The direction of its LAST step.) */
    private Direction direction;

    /** The creature's brain. */
    private ICreatureBrain brain;

    /**
     * Create a new CreatureInfo from the given Creature.
     * 
     * @param creature the creature to "scrape" info from, non-null
     */
    public CreatureInfo( Creature creature )
    {
        // Note: it's maybe a bit illegitimate to have CreatureInfo scrounging
        // around in a Creature's parent container for an ID. However, it otherwise
        // makes sense to have a CreatureInfo build itself from a Creature.

        // Pick the id as the location in the parent's order of Creatures.
        GContainer container = creature.getParent();
        int id = -1;
        if( container != null )
        {
            for( int i = 0; i < container.getElementCount(); i++ )
            {
                if( container.getElement( i ) == creature )
                {
                    id = i;
                    break;
                }
            }
        }

        this.id = id;
        this.age = creature.getAge();
        this.brain = creature.getBrain();
        this.direction = new Direction( creature.getDirection() );
        this.mood = creature.getMood();
        this.x = creature.getX();
        this.y = creature.getY();
    }

    /**
     * Get the creature's unique ID (or -1 if no unique ID could be found for the
     * creature).
     * 
     * @return an ID
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Get the creature's age in steps since creation.
     * 
     * @return the creature's age
     */
    public int getAge()
    {
        return this.age;
    }

    /**
     * Get the creature's brain.
     * 
     * @return the creature's brain
     */
    public ICreatureBrain getBrain()
    {
        return this.brain;
    }

    /**
     * Get the creature's direction
     * 
     * @return the creature's direction
     */
    public Direction getDirection()
    {
        return this.direction;
    }

    /**
     * Get the creature's mood.
     * 
     * @return the creature's mood
     */
    public CreatureMood getMood()
    {
        return this.mood;
    }

    /**
     * Get the creature's x location in the simulation.
     * 
     * @return the creature's x location
     */
    public double getX()
    {
        return this.x;
    }

    /**
     * Get the creature's y location in the simulation.
     * 
     * @return the creature's y location
     */
    public double getY()
    {
        return this.y;
    }


    public boolean equals( Object obj )
    {
        // Fob off responsibility on someone else if the object is not a
        // CreatureInfo
        if( !( obj instanceof CreatureInfo ) )
            return super.equals( obj );

        // Cast the object to a CreatureInfo.
        CreatureInfo other = (CreatureInfo) obj;

        // Check that all fields match.
        return other.age == this.age && other.brain.equals( this.brain )
                && other.direction.equals( this.direction )
                && other.id == this.id && other.mood.equals( this.mood )
                && other.x == this.x && other.y == this.y;
    }
}

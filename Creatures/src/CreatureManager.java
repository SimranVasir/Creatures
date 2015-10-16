import acm.graphics.*;

/**
 * Manages a simulation group of creatures, handling their updating and providing
 * appropriate information to their pack finders.
 * 
 * @author CPSC 111 instructors
 */
public class CreatureManager implements ICreatureProvider
{
    /**
     * The graphical container in which the creatures' reside.
     */
    private GContainer creatureContainer;

    /**
     * Create a new creature manager for the given container.
     * <p>
     * The container is used within CreatureManager to store the set of creatures.
     * 
     * @param container a non-null container that's ready for a proliferation of
     *        creatures
     */
    public CreatureManager( GContainer container )
    {
        this.creatureContainer = container;
    }

    /**
     * Add the given creature to the set of managed creatures.
     * 
     * @param creature a non-null creature to add
     */
    public void addCreature( Creature creature )
    {
        this.creatureContainer.add( creature );
    }

    /**
     * Remove the given creature from the set of managed creatures.
     * 
     * @param creature a non-null creature to remove
     */
    public void removeCreature( Creature creature )
    {
        this.creatureContainer.remove( creature );
    }

    /**
     * Provide a list of the creatures managed.
     */
    public Creature[] getCreatures()
    {
        // Prepare space for the creatures.
        Creature[] creatures = new Creature[ this.creatureContainer.getElementCount() ];
        int numCreatures = 0;

        // Find all the creatures.
        for( int i = 0; i < this.creatureContainer.getElementCount(); i++ )
        {
            if( this.creatureContainer.getElement( i ) instanceof Creature )
                creatures[ numCreatures++ ] = (Creature) this.creatureContainer
                        .getElement( i );
        }

        // Copy into an array of the right size.
        if( numCreatures > creatures.length )
        {
            Creature[] tightCreatureArray = new Creature[ numCreatures ];
            System.arraycopy( creatures, 0, tightCreatureArray, 0, numCreatures );
            creatures = tightCreatureArray;
        }

        return creatures;
    }

    /**
     * Update the state of all creatures in the simulation.
     */
    public void updateCreatures()
    {
        Creature[] creatures = this.getCreatures();

        for( int i = 0; i < creatures.length; i++ )
            creatures[ i ].update();
    }
}

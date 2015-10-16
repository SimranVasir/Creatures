/*
  Author: Simran Vasir, Esta Shahnawaz
  Ugrad lab login ID: 20373080, 
  Date: Nov 29/2008
  
  By submitting this file, I acknowledge that the person whose name appears 
  above is the sole author of this file except as acknowledged in the file below.
*/

/**
 * tests creatues of brain type of YourBrian and packFinder type of YourPackFinder
 * @author o L3atHaFaC3 o
 *
 */

public class YourSetup implements ICreatureProvider

{
    /**
     * My sweet squarephilic creatures.
     */
    private Creature[] creatureies;

    /**
     * Create a dumb creature provider to set up a boring simulation.
     * 
     * @param width the width of the simulation window
     * @param height the height of the simulation window
     */
    public YourSetup( int width, int height )
    {

        /*
         * the following lines of code test 2 YourBrain creatures with
         * PackFinders set to YourPackFinder. Since the mood of the YourBrian
         * creatures depends on the creatures closes to it, there are 3 other
         * creatures in the window that might determine the mood of the
         * YourBrain creatures.
         */

        creatureies = new Creature[ 7 ];

        creatureies[ 0 ] = new Creature( new YourBrain(), new YourPackFinder(
                this ) );
        creatureies[ 1 ] = new Creature( new FollowerBrain(), new YourPackFinder(
                this ) );
        creatureies[ 2 ] = new Creature( new RandomWalkingBrain(),
                new YourPackFinder( this ) );
        creatureies[ 3 ] = new Creature( new GroupieBrain(), new YourPackFinder(
                this ) );
        grooveCreatureRandomly( creatureies[ 3 ] );
        creatureies[ 4 ] = new Creature( new DumbBrain(), new YourPackFinder(
                this ) );
        grooveCreatureRandomly( creatureies[ 4 ] );
        creatureies[ 5 ] = new Creature( new FlockBrain(), new YourPackFinder(
                this ) );
        creatureies[ 6 ] = new Creature( new SpinnyBrain(), new YourPackFinder(
                this ) );
        

        for( int i = 0; i < creatureies.length; i++ )
        {
            placeCreatureRandomly( creatureies[ i ], width, height );
        }
    }

    /**
     * Place a creature at a random location going a random direction
     * 
     * @param creature The creature to place
     * @param maxX width range for location
     * @param maxY height range for location
     */
    private void placeCreatureRandomly( Creature creature, double maxX,
            double maxY )
    {
        double x, y, direction;

        x = Math.random() * maxX;
        y = Math.random() * maxY;
        direction = Math.random() * Direction.FULL_CIRCLE;

        creature.setLocation( x, y );
        creature.setDirection( direction );
    }

    /**
     * Set a creature's mood to random grooviness and maximum intensity.
     * 
     * @param creature The creature to emote
     */
    private void grooveCreatureRandomly( Creature creature )
    {
        double groove = Math.random()
                * ( CreatureMood.MAX_GROOVINESS - CreatureMood.MIN_GROOVINESS )
                + CreatureMood.MIN_GROOVINESS;
        creature
                .setMood( new CreatureMood( groove, CreatureMood.MAX_INTENSITY ) );
    }

    /**
     * Get the setup list of my creatures.
     */
    public Creature[] getCreatures()
    {
        return creatureies;
    }

}

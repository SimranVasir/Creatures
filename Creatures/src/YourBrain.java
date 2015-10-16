/*
  Author: Simran Vasir, Esta Shahnawaz
  Ugrad lab login ID: 20373080, 
  Date: Nov 29/2008
  
  By submitting this file, I acknowledge that the person whose name appears 
  above is the sole author of this file except as acknowledged in the file below.
*/

/**
 * A creature whose grooviness and intensity is half its leader's.
 * This creature moves in a square.
 * 
 * @author me and my partner
 */

public class YourBrain implements ICreatureBrain
{
    /** The creature brain's starting x. */
    private double firstX;

    /** The creature brain's starting y. */
    private double firstY;

    /** Has this creature brain picked a starting point yet? */
    private boolean initialized = false;

    /** the side length of the square */
    private final double SQUARE_LENGTH = 150;

    public CreatureMood chooseMood( CreatureInfo me, IPackFinder packFinder )
    {
        if (!(packFinder.getLeader( me ) == null)){
            
            
            
            CreatureInfo leader = packFinder.getLeader( me );
            
            double leader_grooviness = leader.getMood().getGrooviness();
            double leader_intensity = leader.getMood().getIntensity();
            
            double me_intensity = leader_intensity/2;
            double me_grooviness = leader_grooviness/2;
            
            CreatureMood me_mood = new CreatureMood(me_grooviness, me_intensity);
            return me_mood; 
            }
            
            return me.getMood();
        
    }

    public Direction chooseDirection( CreatureInfo me, IPackFinder packFinder )
    {
        // Pick a starting point if it's never been done before.
        if( !this.initialized )
        {
            this.firstX = me.getX();
            this.firstY = me.getY();
            this.initialized = true;
        }
        
        double distance = Direction.distance( this.firstX, this.firstY, me
                .getX(), me.getY() );

        if( distance < SQUARE_LENGTH )
            return me.getDirection();

        this.firstX = me.getX();
        this.firstY = me.getY();

        return new Direction( me.getDirection().getDirection() + 90 );
    }

}

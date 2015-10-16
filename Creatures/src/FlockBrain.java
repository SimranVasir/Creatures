/*
  Author: Simran Vasir, Esta Shahnawaz
  Ugrad lab login ID: 20373080, 
  Date: Nov 29/2008
  
  By submitting this file, I acknowledge that the person whose name appears 
  above is the sole author of this file except as acknowledged in the file below.
 */

/**
 * directs the creature towards the center of its pack. Its mood becomes
 * groovier when it is close to other pack members, with an intensity that
 * depends on the number of Creatures in the pack. If there are 10 or more pack
 * members, it reaches maximum intensity. If there are no other Creatures in its
 * pack, it wanders aimlessly by picking a random direction and its mood is
 * neutral (intensity and grooviness both set to the middle of the possible
 * range).
 * 
 * @author o L3atHaFaC3 o
 * 
 */
public class FlockBrain implements ICreatureBrain
{
    /**
     * chooses direction towards the centre of the pack
     */
    public Direction chooseDirection( CreatureInfo me, IPackFinder packFinder )
    {

        double x = 0, y = 0;

        CreatureInfo[] pack = packFinder.getPack( me );

        if( pack.length <= 1 )
            return new Direction( Math.random() * Direction.FULL_CIRCLE );

        for( int i = 0; i < pack.length; i++ )
        {

            x += pack[ i ].getX();
            y += pack[ i ].getY();

        }

        double x_centre = x - me.getX() / ( pack.length - 1 );
        double y_centre = y - me.getY() / ( pack.length - 1 );

        return new Direction( x_centre - me.getX(), y_centre - me.getY() );
    }

    /**
     * chooses mood depending upon the number of pack members
     */
    public CreatureMood chooseMood( CreatureInfo me, IPackFinder packFinder )
    {
        double me_intensity;
        double me_grooviness;
        double distance = 0;

        CreatureInfo[] pack = packFinder.getPack( me );

        int num_members = packFinder.getPack( me ).length;

        if( num_members <= 1 )
            return new CreatureMood( ( CreatureMood.MAX_GROOVINESS / 2 ),
                    ( CreatureMood.MAX_INTENSITY / 2 ) );

        if( num_members >= 10 )
            me_intensity = CreatureMood.MAX_INTENSITY;
        else
            me_intensity = ( CreatureMood.MAX_INTENSITY / 10 ) * num_members;

        for( int i = 0; i < num_members; i++ )
        {
            distance += Direction.distance( pack[ i ].getX(), pack[ i ].getY(),
                    me.getX(), me.getY() );
        }

        me_grooviness = CreatureMood.MAX_GROOVINESS - distance;

        return new CreatureMood( me_grooviness, me_intensity );
    }

}

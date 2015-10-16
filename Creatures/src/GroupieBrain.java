/*
  Author: Simran Vasir, Esta Shahnawaz
  Ugrad lab login ID: 20373080, 
  Date: Nov 29/2008
  
  By submitting this file, I acknowledge that the person whose name appears 
  above is the sole author of this file except as acknowledged in the file below.
*/


public class GroupieBrain implements ICreatureBrain
{

    public Direction chooseDirection( CreatureInfo me, IPackFinder packFinder )
    {
        CreatureInfo leader = packFinder.getLeader( me );

        if( !( leader == null ) )
        {
            double x_leader = leader.getX();
            double y_leader = leader.getY();

            double x_me = me.getX();
            double y_me = me.getY();

            return new Direction( x_leader - x_me, y_leader - y_me );

        }

        return me.getDirection();

    }

    public CreatureMood chooseMood( CreatureInfo me, IPackFinder packFinder )
    {
        final int MIN_DISTANCE = 100;
        double me_grooviness;
        double me_intensity;

        CreatureInfo pack_leader = packFinder.getLeader( me );

        if( !( pack_leader == null ) )
        {

            double x_leader = pack_leader.getX();
            double y_leader = pack_leader.getY();

            double x_me = me.getX();
            double y_me = me.getY();

            double distance = Direction.distance( x_leader, y_leader, x_me,
                    y_me );

            if( distance <= MIN_DISTANCE )
            {
                me_grooviness = 100;
            }
            else
                me_grooviness = 100 / distance;

            if( packFinder.getPack( me ).length >= 5 )
                me_intensity = 0;
            else
                me_intensity = CreatureMood.MAX_INTENSITY
                        - ( packFinder.getPack( me ).length * 20 );

            CreatureMood me_mood = new CreatureMood( me_grooviness,
                    me_intensity );

            return me_mood;
        }

        return new CreatureMood( 0, 0 ); // returns mood at 0 grooviness and 0
                                         // intensity if there is no leader

    }

}

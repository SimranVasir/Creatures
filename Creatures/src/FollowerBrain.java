/*
  Author: Simran Vasir, Esta Shahnawaz
  Ugrad lab login ID: 20373080, 
  Date: Nov 29/2008
  
  By submitting this file, I acknowledge that the person whose name appears 
  above is the sole author of this file except as acknowledged in the file below.
*/

public class FollowerBrain implements ICreatureBrain
{

    public CreatureMood chooseMood( CreatureInfo me, IPackFinder packFinder )
    {

        if( !( packFinder.getLeader( me ) == null ) )
        {

            CreatureInfo leader = packFinder.getLeader( me );

            double leader_grooviness = leader.getMood().getGrooviness();
            double leader_intensity = leader.getMood().getIntensity();

            double me_intensity = leader_intensity / 2;

            CreatureMood me_mood = new CreatureMood( leader_grooviness,
                    me_intensity );
            return me_mood;
        }

        return me.getMood();

    }

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
}

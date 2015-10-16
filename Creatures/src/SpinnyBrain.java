/*
  Author: Simran Vasir, Esta Shahnawaz
  Ugrad lab login ID: 20373080, 
  Date: Nov 29/2008
  
  By submitting this file, I acknowledge that the person whose name appears 
  above is the sole author of this file except as acknowledged in the file below.
*/

/**
 * directs the creature to spin around in circles, and leaves the mood
 * unchanged. The pack is ignored.
 * 
 * @author o L3atHaFaC3 o
 * 
 */
public class SpinnyBrain implements ICreatureBrain
{

    public CreatureMood chooseMood( CreatureInfo info, IPackFinder packFinder )
    {
        return info.getMood();
    }

    public Direction chooseDirection( CreatureInfo me, IPackFinder packFinder )
    {

        Direction direction = me.getDirection();

        double direc = direction.getDirection();

        return new Direction( direc + 10 );

    }
}

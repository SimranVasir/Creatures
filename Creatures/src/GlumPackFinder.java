/*
  Author: Simran Vasir, Esta Shahnawaz
  Ugrad lab login ID: 20373080, 
  Date: Nov 29/2008
  
  By submitting this file, I acknowledge that the person whose name appears 
  above is the sole author of this file except as acknowledged in the file below.
*/

/**
 * returns the pack of all the Creatures with grooviness below 0. The most
 * ungroovy Creature in the pack is the leader.
 * 
 * @author o L3atHaFaC3 o
 * 
 */
public class GlumPackFinder extends QualifiedPackFinder
{

    public GlumPackFinder( ICreatureProvider provider )
    {
        super( provider );

    }

    protected boolean betterPackLeader( CreatureInfo packDefiningCreature,
            CreatureInfo candidateCreature1, CreatureInfo candidateCreature2 )
    {
        if( candidateCreature1.getMood().getGrooviness() < candidateCreature2
                .getMood().getGrooviness() )
            return true;

        return false;
    }

    protected boolean shouldBeInPack( CreatureInfo packDefiningCreature,
            CreatureInfo candidateCreature )
    {
        if( candidateCreature.getMood().getGrooviness() < 0 )
            return true;

        return false;
    }

}
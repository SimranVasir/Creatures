/*
  Author: Simran Vasir, Esta Shahnawaz
  Ugrad lab login ID: 20373080, 
  Date: Nov 29/2008
  
  By submitting this file, I acknowledge that the person whose name appears 
  above is the sole author of this file except as acknowledged in the file below.
*/

/**
 * returns the pack of all Creatures within 200 pixels. The leader is the most
 * intense Creature in the pack
 * 
 * @author o L3atHaFaC3 o
 * 
 */
public class NearbyIntensePackFinder extends QualifiedPackFinder
{
    /**
     * 
     * Just call the superclass constructor.
     */
    public NearbyIntensePackFinder( ICreatureProvider provider )
    {
        super( provider );

    }

    protected boolean betterPackLeader( CreatureInfo packDefiningCreature,
            CreatureInfo candidateCreature1, CreatureInfo candidateCreature2 )
    {
        if( candidateCreature1.getMood().getIntensity() > candidateCreature2
                .getMood().getGrooviness() )
            return true;

        return false;
    }

    protected boolean shouldBeInPack( CreatureInfo packDefiningCreature,
            CreatureInfo candidateCreature )
    {

        if( Direction.distance( packDefiningCreature.getX(),
                packDefiningCreature.getY(), candidateCreature.getX(),
                candidateCreature.getY() ) <= 200 )
            return true;

        return false;
    }

}

/*
  Author: Simran Vasir, Esta Shahnawaz
  Ugrad lab login ID: 20373080, 
  Date: Nov 29/2008
  
  By submitting this file, I acknowledge that the person whose name appears 
  above is the sole author of this file except as acknowledged in the file below.
*/

/**
 * pack consists of all the creatures. leader is the creature that's closest to
 * the creature
 * 
 */

public class YourPackFinder extends QualifiedPackFinder

{
    /**
     * Just call the superclass constructor.
     */
    public YourPackFinder( ICreatureProvider provider )
    {
        super( provider );
        // TODO Auto-generated constructor stub
    }

    protected boolean betterPackLeader( CreatureInfo packDefiningCreature,
            CreatureInfo candidateCreature1, CreatureInfo candidateCreature2 )
    {
        double me_x = packDefiningCreature.getX();
        double me_y = packDefiningCreature.getY();
        double cand1_x = candidateCreature1.getX();
        double cand1_y = candidateCreature1.getY();
        double cand2_x = candidateCreature2.getX();
        double cand2_y = candidateCreature2.getY();

        double distanceme_1 = Direction.distance( me_x, me_y, cand1_x, cand1_y );
        double distanceme_2 = Direction.distance( me_x, me_y, cand2_x, cand2_y );

        if( distanceme_1 > distanceme_2 )
            return true;

        return false;
    }

    protected boolean shouldBeInPack( CreatureInfo packDefiningCreature,
            CreatureInfo candidateCreature )
    {

        return true;
    }

}

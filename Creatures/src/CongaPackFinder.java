/*
  Author: Simran Vasir, Esta Shahnawaz
  Ugrad lab login ID: 20373080, 
  Date: Nov 29/2008
  
  By submitting this file, I acknowledge that the person whose name appears 
  above is the sole author of this file except as acknowledged in the file below.
*/

/**
 * returns the pack of all creatures provided by the ICreatureProvider. However,
 * it returns a different pack leader for each creature: it returns the next
 * creature in the array
 * 
 * @author o L3atHaFaC3 o
 * 
 */
public class CongaPackFinder extends AllCreaturesPack
{
    /**
     * 
     * Just call the superclass constructor.
     */
    public CongaPackFinder( ICreatureProvider provider )
    {
        super( provider );

    }

    /**
     * Return the leader of the pack: whoever goes first. Note: this may be the
     * same as the Creature requesting a pack. Returns null for an empty world.
     */
    public CreatureInfo getLeader( CreatureInfo creature )
    {

        CreatureInfo[] packCreatures = this.getPack( creature );

        if( !( packCreatures.length == 0 ) )
        {

            int creature_position = 0;

            for( int i = 0; i < packCreatures.length; i++ )
            {

                if( creature.equals( packCreatures[ i ] ) )

                    creature_position = i;
            }

            // the following three if statements take into account cases when no
            // creatures exist, when the creature for whom the leader is to be
            // found
            // is at the end of the array, when creature for whom the leader is
            // to
            // be found before the end of the array

            if( packCreatures.length > creature_position + 1 )// body executes
            // only
            // if there is a
            // creature in the
            // array after the
            // creature for whom
            // we want to find the
            // leader
            {
                return packCreatures[ creature_position + 1 ];
            }

            if( packCreatures.length == creature_position + 1 )
                return packCreatures[ 0 ];
        }

        return null;
    }

}

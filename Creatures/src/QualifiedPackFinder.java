

/**
 * 
 * An abstract class representing a pack that is a subset of the full pack,
 * 
 * including only those creatures that fit a certain criterion. The leader is
 * the
 * 
 * one that's best by another certain criterion.
 * 
 * <p>
 * 
 * Protected abstract methods define the criteria, allowing flexible creation of
 * 
 * subclasses that create different kinds of packs.
 */

public abstract class QualifiedPackFinder extends AllCreaturesPack

{

    /**
     * 
     * Just call the superclass constructor.
     */

    public QualifiedPackFinder( ICreatureProvider provider )

    {

        super( provider );

    }

    /**
     * 
     * Return true if and only if the candidate creature should be in the pack
     * 
     * defined by the defining creature.
     */

    protected abstract boolean shouldBeInPack(
            CreatureInfo packDefiningCreature,

            CreatureInfo candidateCreature );

    /**
     * 
     * Return true if and only if the first candidate creature is more fit to be
     * 
     * leader of the pack than the second candidate creature (where the pack is
     * 
     * defined by the defining creature).
     */

    protected abstract boolean betterPackLeader(
            CreatureInfo packDefiningCreature,

            CreatureInfo candidateCreature1, CreatureInfo candidateCreature2 );

    /**
     * 
     * Get the portion of the pack that fits the shouldBeInPack criterion.
     */

    public CreatureInfo[] getPack( CreatureInfo creature )

    {

        CreatureInfo[] allInfos = this.getAllInfo();

        // Count how many creatures fit the criterion.

        int packCount = 0;

        for( int i = 0; i < allInfos.length; i++ )

            if( this.shouldBeInPack( creature, allInfos[ i ] ) )

                packCount++;

        // Make space for the creatures that fit.

        CreatureInfo[] packInfos = new CreatureInfo[ packCount ];

        // Copy in the creatures that fit.

        packCount = 0;

        for( int i = 0; i < allInfos.length; i++ )

            if( this.shouldBeInPack( creature, allInfos[ i ] ) )

                packInfos[ packCount++ ] = allInfos[ i ];

        return packInfos;

    }

    /**
     * 
     * Get the leader, the creature that's best according to the
     * betterPackLeader
     * 
     * criterion.
     */

    public CreatureInfo getLeader( CreatureInfo creature )

    {

        CreatureInfo[] infos = this.getPack( creature );

        if( infos.length == 0 )

            return null;

        int bestIndex = 0;

        for( int i = 1; i < infos.length; i++ )

            if( this
                    .betterPackLeader( creature, infos[ i ], infos[ bestIndex ] ) )

                bestIndex = i;

        return infos[ bestIndex ];

    }

}

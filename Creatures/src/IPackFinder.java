/**
 * Pack finders help find information about a creature's pack. Every class
 * implementing IPackFinder should include a constructor that takes an
 * ICreatureProvider. It should then find the pack from the universe of creatures
 * provided by the ICreatureProvider.
 * 
 * @author CPSC 111 instructors
 */
public interface IPackFinder
{
    /**
	 * Get the pack of the provided creature.
	 * 
	 * @param creature
	 *            info about the creature whose pack we're constructing
	 * @return a non-null (but possibly empty) list of non-null CreatureInfo
	 *         objects for the creatures in the given creature's pack. Note: the pack
	 *         may or may not contain the provided creature.
	 */
    CreatureInfo[] getPack( CreatureInfo creature );

    /**
     * Get the leader of the provided creature's pack (or null if no leader
     * exists). 
     * 
     * @param creature info about the creature whose pack leader we're finding
     * @return the leader of the given creature's pack, or null if no leader was
     *         found. Note: the leader may or may not be the provided creature.
     */
    CreatureInfo getLeader( CreatureInfo creature );
}

/**
 * A pack that just gives back all the creatures in the world.
 * 
 * @author CPSC 111 instructors
 */
public class AllCreaturesPack implements IPackFinder {
	/**
	 * The provider that defines the "world" of creatures.
	 */
	private ICreatureProvider creatureProvider;
	
	/**
	 * Construct a new AllCreaturesPack.
	 * 
	 * @param provider a non-null provider defining the world of creatures
	 *        available
	 */
	public AllCreaturesPack(ICreatureProvider provider) {
		this.creatureProvider = provider;
	}
	
	/**
	 * Helper class that does all the work of fetching the creature info from the
	 * creatures.
	 * 
	 * @return a list of all the info for all the creatures in the world
	 */
	protected CreatureInfo[] getAllInfo() {
		// Get the creature source
		Creature[] creatures = this.creatureProvider.getCreatures();
		
		// The number of creature infos gathered so far.
		int numCreatures = 0;
		
		// An array to hold the selected creature infos.
		// For AllCreaturesPack, ALL available CreatureInfos will be used.
		// However, this code pattern works well for selecting just some of the CreatureInfos.
		CreatureInfo[] infos = new CreatureInfo[creatures.length];
		
		for (int i = 0; i < creatures.length; i++) {
			infos[numCreatures++] = creatures[i].getInfo();
		}
		
		// Copy into an array of the right size, if necessary.
		if (numCreatures < infos.length) {
			CreatureInfo[] newInfos = new CreatureInfo[numCreatures];
			System.arraycopy(infos, 0, newInfos, 0, numCreatures);
			infos = newInfos;
		}
		
		return infos;
	}
	
	/**
	 * Return a pack of all the creatures in the world.
	 */
	public CreatureInfo[] getPack(CreatureInfo creature) {
		return this.getAllInfo();
	}
	
	/**
	 * Return the leader of the pack: whoever goes first. Note: this may be the
	 * same as the Creature requesting a pack. Returns null for an empty world.
	 */
	public CreatureInfo getLeader(CreatureInfo creature) {
		CreatureInfo[] infos = this.getAllInfo();
		if (infos.length == 0)
			return null;
		
		return infos[0];
	}
}

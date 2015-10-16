/**
 * A dumb creature brain that just keeps on with the same direction and emotion.
 * 
 * @author CPSC 111 instructors
 */
public class DumbBrain implements ICreatureBrain {
	/**
	 * Choose a neutral mood.
	 */
	public CreatureMood chooseMood(CreatureInfo info, IPackFinder packFinder) {
		return info.getMood();
	}

	/**
	 * Keep going whatever direction you are going.
	 */
	public Direction chooseDirection(CreatureInfo info, IPackFinder packFinder) {
		return info.getDirection();
	}
}

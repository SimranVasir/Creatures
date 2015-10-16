/**
 * A dumb setup routine that just creates a couple of dumb creatures and a random
 * walker at random locations in random directions, with boring packs.
 * <p>
 * Create your OWN creature providers as setups to test your creatureies and packs,
 * and make neat animations! Square dancing, synchronized swimming, marching
 * bands, crop circles?...
 * 
 * @author CPSC 111 instructors
 */
public class DumbSetup implements ICreatureProvider {
	/**
	 * My sweet, dumb creatures.
	 */
	private Creature[] creatureies;

	/**
	 * Create a dumb creature provider to set up a boring simulation.
	 * 
	 * @param width
	 *            the width of the simulation window
	 * @param height
	 *            the height of the simulation window
	 */
	public DumbSetup(int width, int height) {
		creatureies = new Creature[6];
		creatureies[2] = new Creature(new DumbBrain(), new AllCreaturesPack(this));
		grooveCreatureRandomly(creatureies[2]);
		creatureies[1] = new Creature(new DumbBrain(), new AllCreaturesPack(this));
		grooveCreatureRandomly(creatureies[1]);
		creatureies[0] = new Creature(new RandomWalkingBrain(), new AllCreaturesPack(
				this));
		
		creatureies[3] = new Creature(new SpinnyBrain(), new AllCreaturesPack(this));
        creatureies[4] = new Creature(new FollowerBrain(), new AllCreaturesPack(this));
        creatureies[5] = new Creature(new YourBrain(), new AllCreaturesPack(this) );
		for (int i = 0; i < creatureies.length; i++) {
			placeCreatureRandomly(creatureies[i], width, height);
		}
	}
	/**
	 * Set a creature's mood to random grooviness and maximum intensity.
	 * 
	 * @param creature The creature to emote
	 */
	private void grooveCreatureRandomly(Creature creature) {
		double groove = Math.random()
				* (CreatureMood.MAX_GROOVINESS - CreatureMood.MIN_GROOVINESS)
				+ CreatureMood.MIN_GROOVINESS;
		creature.setMood(new CreatureMood(groove, CreatureMood.MAX_INTENSITY));
	}

	/**
	 * Place a creature at a random location going a random direction
	 * 
	 * @param creature
	 *            The creature to place
	 * @param maxX
	 *            width range for location
	 * @param maxY
	 *            height range for location
	 */
	private void placeCreatureRandomly(Creature creature, double maxX, double maxY) {
		double x, y, direction;

		x = Math.random() * maxX;
		y = Math.random() * maxY;
		direction = Math.random() * Direction.FULL_CIRCLE;

		creature.setLocation(x, y);
		creature.setDirection(direction);
	}

	/**
	 * Get the setup list of dumb creatures.
	 */
	public Creature[] getCreatures() {
		return creatureies;
	}
}

/**
 * A creature's brain, capable of choosing a direction to go next and a mood to
 * assume next based on its own information (e.g., current direction, location,
 * and mood) and information about its pack.
 * 
 * @author CPSC 111 instructors
 * 
 */
public interface ICreatureBrain
{
    /**
     * Choose the next mood for this creature.
     * <p>
     * Note: there's no guarantee that each time this method is called, the
     * brain's creature will then assume the chosen mood next. For example, this
     * call could just be another brain querying this brain about what IT would
     * do in this situation.
     * <p>
     * The moral of this information is that if you need to know something about
     * the state of the creature, you should try to get it from the CreatureInfo
     * parameter. (For example, you can determine the creature's age from the
     * CreatureInfo parameter. That's a great way to figure out a mood that gets
     * better with time!)
     * 
     * @param me information about this creature (what its brain knows about its
     *        body/state), must be non-null
     * @param packFinder a pack finder to tell this creature about its pack (must
     *        be non-null)
     * @return a chosen mood to assume next, non-null
     */
    CreatureMood chooseMood( CreatureInfo me, IPackFinder packFinder );

    /**
     * Choose the next direction for this creature.
     * <p>
     * Note: there's no guarantee that each time this method is called, the
     * brain's creature will then assume the chosen direction next. For example,
     * this call could just be another brain querying this brain about what IT
     * would do in this situation.
     * <p>
     * The moral of this information is that if you need to know something about
     * the state of the creature, you should try to get it from the CreatureInfo
     * parameter. (For example, you can determine the creature's age from the
     * CreatureInfo parameter. That's a great way to figure out a direction that
     * will send the creature in circles!
     * 
     * @param me information about this creature (what its brain knows about its
     *        body/state), must be non-null
     * @param packFinder a pack finder to tell this creature about its pack (must
     *        be non-null)
     * @return a chosen direction to go next (non-null)
     */
    Direction chooseDirection( CreatureInfo me, IPackFinder packFinder );
}

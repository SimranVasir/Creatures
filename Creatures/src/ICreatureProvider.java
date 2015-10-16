/**
 * A provider of creatures. Other classes can use this list of creatures as they will.
 * We use creature providers for two purposes: to inform pack finders about the
 * entire selection of creatures out there and to set up a new simulation.
 * 
 * @author CPSC 111 instructors
 */
public interface ICreatureProvider
{
    /**
     * Get a list of all the creatures this provider contains.
     * 
     * @return a non-null (but possibly empty!) list of non-null Creatures
     */
    Creature[] getCreatures();
}

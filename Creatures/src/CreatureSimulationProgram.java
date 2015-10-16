import acm.program.*;

/**
 * Run a simulation of creature behaviour. Aren't they cute?
 * 
 * @author CPSC 111 instructors.. AND YOU!
 * 
 */
public class CreatureSimulationProgram extends GraphicsProgram
{
	private static final long serialVersionUID = 1L;

	/**
     * Time to pause between animation cycles (in milliseconds).
     * <p>
     * TODO: you may want to change this to change the simulation speed!!
     */
    private static final int PAUSE_TIME = 100;

    /**
     * Width of the game display (all coordinates are in pixels)
     */
    private static final int WIDTH = 400;

    /**
     * Height of the game display
     */
    private static final int HEIGHT = 600;

    /**
     * The manager of this creature simulation.
     */
    private CreatureManager creatureManager = new CreatureManager( this.getGCanvas() );

    /**
     * Runs the program as an application. This method differs from the simplest
     * possible boilerplate in that it passes parameters to specify the
     * dimensions of the simulation.
     */
    public static void main( String[] args )
    {
        String[] sizeArgs = { "width=" + WIDTH, "height=" + HEIGHT };
        new CreatureSimulationProgram().start( sizeArgs );
    }

    /**
     * Returns a creature provider that gives the initial creatures to use for the
     * simulation.
     * 
     * @return a non-null creature provider
     */
    protected ICreatureProvider setup()
    {
        // TODO: Write new classes that implement interface ICreatureProvider that
        // create an array of new Creatures to test your various CreatureBrains and
        // PackFinders. Change this line below to use them.

        return new YourSetup( this.getWidth(), this.getHeight() );
    }

    /**
     * Runs the program.
     */
    public void run()
    {
        ICreatureProvider setup = this.setup();

        // have the creatures move all at once
        getGCanvas().setAutoRepaintFlag( false );
        Creature[] newCreatures = setup.getCreatures();
        for( int i = 0; i < newCreatures.length; i++ )
        {
            this.creatureManager.addCreature( newCreatures[ i ] );
        }
        repaint();

        while( true )
        {
            this.pause( PAUSE_TIME );
            this.creatureManager.updateCreatures();
            repaint();
        }
    }
}

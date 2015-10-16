/**
 * A Creature's mood, defined as two parameters: grooviness and intensity. Emotions
 * like happiness, joy, and pride have positive grooviness. Emotions like anger,
 * frustration, and rage have negative grooviness. Emotions like rapture and
 * rage have higher intensities than emotions like smugness and sullenness.
 * Emotions like "hyper" and "deadpan" may vary in intensity but have zero
 * grooviness. In human-computer interaction (HCI), we do use a scale like this
 * to describe emotion, but the axes are generally called "valence" and
 * intensity rather than grooviness and intensity.
 * 
 * @author CPSC 111 instructors
 */
public class CreatureMood
{
    /**
     * The maximum allowable grooviness. 
     */
    public static double MAX_GROOVINESS = 100;
 
    /**
     * The minimum allowable grooviness. 
     */
    public static double MIN_GROOVINESS = -100;

    /**
     * The maximum allowable intensity. 
     */
    public static double MAX_INTENSITY = 100;

    /**
     * The minimum allowable intensity. 
     */
    public static double MIN_INTENSITY = 0;

    /** The grooviness of this mood, man. */
    private double grooviness;

    /** The intensity of this mood. */
    private double intensity;

    /**
     * Construct a mood reflecting the given parameters.
     * <p>
     * Note: the parameters will be clipped to the allowable ranges of
     * grooviness and intensity.
     * 
     * @param grooviness the grooviness of the mood
     * @param intensity the intensity of the mood
     */
    public CreatureMood( double grooviness, double intensity )
    {
        // Clip the values to their ranges.
        if( grooviness > MAX_GROOVINESS )
            grooviness = MAX_GROOVINESS;
        if( grooviness < MIN_GROOVINESS )
            grooviness = MIN_GROOVINESS;
        if( intensity > MAX_INTENSITY )
            intensity = MAX_INTENSITY;
        if( intensity < MIN_INTENSITY )
            intensity = MIN_INTENSITY;

        // Store the clipped values.
        this.grooviness = grooviness;
        this.intensity = intensity;
    }

    /**
     * Get the grooviness of this mood. (Positive values are groovy, man.
     * Negative values are definitely ungroovy.)
     * 
     * @return the grooviness, between MIN_GROOVINESS and MAX_GROOVINESS
     */
    public double getGrooviness()
    {
        return this.grooviness;
    }

    /**
     * Get the intensity of this mood. (Higher values are more intense.)
     * 
     * @return the intensity, between MIN_INTENSITY and MAX_INTENSITY
     */
    public double getIntensity()
    {
        return this.intensity;
    }


    public boolean equals( Object obj )
    {
        // Pass off responsibility if the other object isn't a CreatureMood.
        if( !( obj instanceof CreatureMood ) )
            return super.equals( obj );

        // Cast the other object to a creature mood.
        CreatureMood other = (CreatureMood) obj;

        // Compare all fields.
        return other.grooviness == this.grooviness
                && other.intensity == this.intensity;
    }

}

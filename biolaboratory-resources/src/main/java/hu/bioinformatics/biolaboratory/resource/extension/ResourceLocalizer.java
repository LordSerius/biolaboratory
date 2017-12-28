package hu.bioinformatics.biolaboratory.resource.extension;

/**
 * Provides an interface which can localizes external resources paths.
 *
 * @author Attila Radi
 */
public interface ResourceLocalizer {

    /**
     * Identifies and loads a resource about its name.
     *
     * @param resourceName The name of the resource.
     * @return The path of the resource.
     */
    String localizeResource(final String resourceName);
}

package hu.bioinformatics.biolaboratory.utils.resource;

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
    String localizeResource(String resourceName);
}

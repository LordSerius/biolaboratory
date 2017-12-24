package hu.bioinformatics.biolaboratory.resource.extension;

/**
 * Validates the path of the resources.
 *
 * @author Attila Radi
 */
public interface ResourceValidator {

    /**
     * Validates the given path. Throws {@link IllegalArgumentException} if the path is not correct.
     *
     * @param resourcePath The path to validate.
     * @throws IllegalArgumentException if the resource path is not well formed.
     */
    void validate(final String resourcePath);
}

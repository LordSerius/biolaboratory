package hu.bioinformatics.biolaboratory.resource.extension;

import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotBlankString;

/**
 * Validates a resources with extension.
 *
 * @author Attila Radi
 */
public abstract class ResourceExtensionValidator implements ResourceValidator {
    @Override
    public final void validate(final String resourcePath) {
        checkNotBlankString("Resource path", resourcePath);
        validateExtension(resourcePath);
    }

    /**
     * Validates the extension for the target resourcePath.
     *
     * @param resourcePath The target resourcePath.
     * @throws IllegalArgumentException If extension is not well formed.
     */
    protected abstract void validateExtension(final String resourcePath);
}

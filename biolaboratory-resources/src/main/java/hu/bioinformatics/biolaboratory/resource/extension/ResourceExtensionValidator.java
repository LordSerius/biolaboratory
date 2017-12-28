package hu.bioinformatics.biolaboratory.resource.extension;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

/**
 * Validates a resources with extension.
 *
 * @author Attila Radi
 */
public abstract class ResourceExtensionValidator implements ResourceValidator {
    @Override
    public final void validate(final String resourcePath) {
        Preconditions.checkArgument(StringUtils.isNotBlank(resourcePath), "Resource path should not be null");
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

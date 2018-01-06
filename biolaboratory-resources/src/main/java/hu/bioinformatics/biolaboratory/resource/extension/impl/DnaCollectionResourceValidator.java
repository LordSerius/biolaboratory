package hu.bioinformatics.biolaboratory.resource.extension.impl;

import hu.bioinformatics.biolaboratory.resource.extension.ResourceExtensionValidator;

import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Validate DNA collection resource extensions.
 *
 * @author Attila Radi
 */
public class DnaCollectionResourceValidator extends ResourceExtensionValidator {
    private static final Pattern DNA_COLLECTION_EXTENSION = Pattern.compile(".+\\.dnacol");

    @Override
    protected void validateExtension(final String resourcePath) {
        checkArgument(DNA_COLLECTION_EXTENSION.matcher(resourcePath).matches(), "Resource should have .dnacol extension");
    }
}

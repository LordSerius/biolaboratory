package hu.bioinformatics.biolaboratory.resource.extension.impl;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceExtensionValidator;

import java.util.regex.Pattern;

/**
 * Validate DNA collection resource extensions.
 *
 * @author Attila Radi
 */
public class DnaCollectionResourceValidator extends ResourceExtensionValidator {
    private static final Pattern DNA_COLLECTION_EXTENSION = Pattern.compile(".+\\.dnacol");

    @Override
    protected void validateExtension(String resourcePath) {
        Preconditions.checkArgument(DNA_COLLECTION_EXTENSION.matcher(resourcePath).matches(), "Resource should have .dnacol extension");
    }
}

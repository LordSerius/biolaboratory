package hu.bioinformatics.biolaboratory.resource.extension.impl;

import hu.bioinformatics.biolaboratory.resource.extension.ResourceExtensionValidator;

import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Validate DNA resource extensions.
 * 
 * @author Attila Radi
 *
 */
public class DnaResourceValidator extends ResourceExtensionValidator {
    private static final Pattern DNA_EXTENSION = Pattern.compile(".+\\.dna");

    @Override
    protected void validateExtension(final String resourcePath) {
        checkArgument(DNA_EXTENSION.matcher(resourcePath).matches(), "Resource should have .dna extension");
    }
}

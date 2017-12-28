package hu.bioinformatics.biolaboratory.resource.extension.impl;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceExtensionValidator;

import java.util.regex.Pattern;

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
        Preconditions.checkArgument(DNA_EXTENSION.matcher(resourcePath).matches(), "Resource should have .dna extension");
    }
}

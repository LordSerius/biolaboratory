package hu.bioinformatics.biolaboratory.resource.extension.impl;

import hu.bioinformatics.biolaboratory.resource.extension.ResourceExtensionValidator;

import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Validates FASTA resource extensions.
 *
 * @author Attila Radi
 */
public class FastaResourceValidator extends ResourceExtensionValidator {
    private static final Pattern FASTA_EXTENSION = Pattern.compile(".+\\.fas(ta)?");

    @Override
    protected void validateExtension(final String resourcePath) {
        checkArgument(FASTA_EXTENSION.matcher(resourcePath).matches(), "Resource should have .fas or .fasta extension");
    }
}

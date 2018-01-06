package hu.bioinformatics.biolaboratory.resource.extension.impl;

import hu.bioinformatics.biolaboratory.resource.extension.ResourceValidator;

import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotBlankString;

/**
 * Validates the resource is not blank.
 *
 * @author Attila Radi
 */
public class SimpleResourceValidator implements ResourceValidator {
    @Override
    public void validate(final String resourcePath) {
        checkNotBlankString("Resource path", resourcePath);
    }
}

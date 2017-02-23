package hu.bioinformatics.biolaboratory.utils.resource.extension.impl;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.utils.resource.extension.ResourceValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * Validates the resource is not blank.
 *
 * @author Attila Radi
 */
public class SimpleResourceValidator implements ResourceValidator {
    @Override
    public void validate(final String resourcePath) {
        Preconditions.checkArgument(StringUtils.isNotBlank(resourcePath), "Resource path should not be blank");
    }
}

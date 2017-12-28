package hu.bioinformatics.biolaboratory.resource.extension.impl;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * Loads resources from the resource directory of the local filepath.
 *
 * @author Attila Radi
 */
public class LocalFileResourceLocalizer implements ResourceLocalizer {

    /**
     * Loads a resource file from the \resources folder.
     *
     * @param resourceName The name of the resource file.
     * @return The file path of the resource file.
     * @throws IllegalArgumentException If resource does not exist.
     */
    @Override
    public String localizeResource(final String resourceName) {
        Preconditions.checkArgument(StringUtils.isNotBlank(resourceName), "Resource name should not be blank");
        return Optional.ofNullable(getClass().getClassLoader().getResource(resourceName))
                .orElseThrow(() -> new IllegalArgumentException("Resource does not exist"))
                .getFile();
    }
}

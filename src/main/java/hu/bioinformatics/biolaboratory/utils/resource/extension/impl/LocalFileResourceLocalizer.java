package hu.bioinformatics.biolaboratory.utils.resource.extension.impl;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.utils.resource.extension.ResourceLocalizer;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
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
    public String localizeResource(String resourceName) {
        Preconditions.checkArgument(StringUtils.isNotBlank(resourceName), "Resource name should not be blank");
        Optional<URL> resourceUrl = Optional.ofNullable(getClass().getClassLoader().getResource(resourceName));
        Preconditions.checkArgument(resourceUrl.isPresent(), "Resource does not exist");
        return resourceUrl.get().getFile();
    }
}
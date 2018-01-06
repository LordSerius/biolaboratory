package hu.bioinformatics.biolaboratory.resource.extension.impl;

import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.utils.ArgumentValidator;

import java.util.Optional;

/**
 * Loads resources from the resource directory of the local filepath.
 *
 * @author Attila Radi
 */
public class LocalFileResourceLocalizer implements ResourceLocalizer {

    /**
     * Loads a resource file from the /resources folder.
     *
     * @param filePath The relative path of resource.
     * @return The file path of the resource file.
     * @throws IllegalArgumentException If resource does not exist.
     */
    @Override
    public String localizeResource(final String filePath) {
        ArgumentValidator.checkNotBlankString("Relative file path", filePath);
        return Optional.ofNullable(getClass().getClassLoader().getResource(filePath))
                .orElseThrow(() -> new IllegalArgumentException("Resource does not exist"))
                .getFile();
    }
}

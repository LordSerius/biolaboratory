package hu.bioinformatics.biolaboratory.resource.extension.impl;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UncheckedIOException;

/**
 * Localizes file resources
 *
 * @author Attila Radi
 */
public class FileResourceLocalizer implements ResourceLocalizer {
    /**
     * Localizes a file resource in the file system.
     *
     * @param resourceName The name of the resource.
     * @return The absolute path of the file.
     * @throws IllegalArgumentException If resourceName is blank.
     * @throws UncheckedIOException If file not found.
     */
    @Override
    public String localizeResource(final String resourceName) {
        Preconditions.checkArgument(StringUtils.isNotBlank(resourceName), "File path should not be empty");
        File file = new File(resourceName);
        if (!file.exists()) {
            throw new UncheckedIOException(new FileNotFoundException(resourceName + " not found"));
        }
        return file.getAbsolutePath();
    }
}

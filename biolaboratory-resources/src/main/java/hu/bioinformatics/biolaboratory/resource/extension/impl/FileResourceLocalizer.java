package hu.bioinformatics.biolaboratory.resource.extension.impl;

import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UncheckedIOException;

import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotBlankString;

/**
 * Localizes file resources
 *
 * @author Attila Radi
 */
public class FileResourceLocalizer implements ResourceLocalizer {
    /**
     * Localizes a file resource in the file system.
     *
     * @param filePath The path of the file.
     * @return The absolute path of the file.
     * @throws IllegalArgumentException If filePath is blank.
     * @throws UncheckedIOException If file not found.
     */
    @Override
    public String localizeResource(final String filePath) {
        checkNotBlankString("File path", filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            throw new UncheckedIOException(new FileNotFoundException(filePath + " not found"));
        }
        return file.getAbsolutePath();
    }
}

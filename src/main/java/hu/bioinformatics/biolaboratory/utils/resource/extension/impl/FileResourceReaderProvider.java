package hu.bioinformatics.biolaboratory.utils.resource.extension.impl;

import hu.bioinformatics.biolaboratory.utils.resource.extension.ResourceReaderProvider;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Reads a resource from a file.
 *
 * @author Attila Radi
 */
public class FileResourceReaderProvider implements ResourceReaderProvider {
    @Override
    public Reader provideReader(String resourcePath) throws IOException {
        return new FileReader(resourcePath);
    }
}

package hu.bioinformatics.biolaboratory.utils.resource.file;

import hu.bioinformatics.biolaboratory.utils.resource.ResourceProvider;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Reads a resource from a file.
 *
 * @author Attila Radi
 */
public class FileResourceProvider implements ResourceProvider {
    @Override
    public Reader provideReader(String resourcePath) throws IOException {
        return new FileReader(resourcePath); }
}

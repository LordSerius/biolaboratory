package hu.bioinformatics.biolaboratory.utils.resource;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reads a resource line by line.
 *
 * @author Attila Radi
 */
public class LineReader extends ResourceReader {
    /**
     * Constructor is waiting for a {@link ResourceReaderProvider}.
     *
     * @param resourceReaderProvider {@link ResourceReaderProvider}
     */
    @Inject
    public LineReader(ResourceReaderProvider resourceReaderProvider) {
        super(resourceReaderProvider);
    }

    @Override
    protected List<String> processResource(BufferedReader reader) throws IOException {
        return reader.lines().collect(Collectors.toList());
    }
}

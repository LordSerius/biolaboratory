package hu.bioinformatics.biolaboratory.utils.resource;

import com.google.common.base.Preconditions;

import javax.inject.Inject;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reads resources which have only one line. Throws exception if it has multiple lines.
 *
 * @author Attila Radi
 */
public class RowReader extends ResourceReader {

    /**
     * Constructor is waiting for a {@link ResourceReaderProvider}.
     *
     * @param resourceReaderProvider {@link ResourceReaderProvider}
     */
    @Inject
    public RowReader(ResourceReaderProvider resourceReaderProvider) {
        super(resourceReaderProvider);
    }

    @Override
    protected List<String> processResource(BufferedReader reader) throws IOException {
        List<String> lines = reader.lines().limit(2).collect(Collectors.toList());
        Preconditions.checkArgument(lines.size() == 1, "The file should have exactly 1 lines");
        return lines;
    }
}

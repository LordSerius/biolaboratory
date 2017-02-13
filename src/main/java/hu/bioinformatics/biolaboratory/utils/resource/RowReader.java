package hu.bioinformatics.biolaboratory.utils.resource;

import com.google.common.base.Preconditions;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reads resources which have only one line. The line must be a sequence without description.
 * Throws exception if it has multiple lines.
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
    protected List<CommentedLine> processResource(BufferedReader reader) throws IOException {
        List<CommentedLine> rawBilogicalSequenceList = reader.lines()
                .map(line -> new CommentedLine("", line))
                .limit(2)
                .collect(Collectors.toCollection(ArrayList::new));
        Preconditions.checkArgument(rawBilogicalSequenceList.size() == 1, "The file should have exactly 1 lines");
        return rawBilogicalSequenceList;
    }
}

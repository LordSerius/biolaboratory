package hu.bioinformatics.biolaboratory.utils.resource;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reads fasta format files, and returns wit its row. The reader ignores the beginning prompt of the each sequences.
 * Each odd row contains the comment section of the sequence, and each even row contains the sequence.
 *
 * @author Attila Radi
 */
public class FastaReader extends ResourceReader {
    private static final String PROMPT = "<";

    /**
     * Constructor is waiting for a {@link ResourceReaderProvider}.
     *
     * @param resourceReaderProvider {@link ResourceReaderProvider}
     */
    @Inject
    public FastaReader(ResourceReaderProvider resourceReaderProvider) {
        super(resourceReaderProvider);
    }

    @Override
    protected List<CommentedLine> processResource(BufferedReader reader) throws IOException {
        List<String> lineList = reader.lines()
                                        .filter(StringUtils::isNotBlank)
                                        .collect(Collectors.toCollection(ArrayList::new));
        List<CommentedLine> commentedLineList = new ArrayList<>();

        String description = null;
        String concatenatedSequence = "";
        for (String line : lineList) {
            if (line.startsWith(PROMPT)) {
                if (description != null) {
                    Preconditions.checkArgument(concatenatedSequence.isEmpty(), "Multiple header is detected");
                    commentedLineList.add(new CommentedLine(description, concatenatedSequence));
                    concatenatedSequence = "";
                }
                description = line.substring(1, line.length());
            } else {
                concatenatedSequence += line;
            }
        }
        Preconditions.checkArgument(description != null &&concatenatedSequence.isEmpty(), "Multiple header is detected");
        commentedLineList.add(new CommentedLine(description, concatenatedSequence));

        return commentedLineList;
    }
}

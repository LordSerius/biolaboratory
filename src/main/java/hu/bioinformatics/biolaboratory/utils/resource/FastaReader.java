package hu.bioinformatics.biolaboratory.utils.resource;

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
    protected List<String> processResource(BufferedReader reader) throws IOException {
        List<String> lineList = reader.lines()
                                        .filter(StringUtils::isNotBlank)
                                        .collect(Collectors.toCollection(ArrayList::new));
        List<String> concatenatedStringList = new ArrayList<>();

        String concatenatedSequence = "";
        for (String line : lineList) {
            if (line.startsWith(PROMPT) && StringUtils.isNotBlank(concatenatedSequence)) {
                concatenatedStringList.add(concatenatedSequence);
                concatenatedSequence = "";
            } else {
                concatenatedSequence += line;
            }
        }

        return concatenatedStringList;
    }
}

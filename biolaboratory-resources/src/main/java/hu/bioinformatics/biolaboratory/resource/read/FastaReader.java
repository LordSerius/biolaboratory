package hu.bioinformatics.biolaboratory.resource.read;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.utils.CommentedString;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceReaderProvider;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceValidator;
import hu.bioinformatics.biolaboratory.resource.read.wrapper.ReaderWrapperFactory;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;
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
    private static final String PROMPT = ">";

    /**
     * Constructor is waiting for a {@link ResourceReaderProvider}.
     *
     * @param resourceValidator {@link ResourceValidator}
     * @param resourceReaderProvider {@link ResourceReaderProvider}
     * @param readerWrapperFactory {@link ReaderWrapperFactory}
     */
    @Inject
    public FastaReader(@Named(GuiceResourceModule.FASTA_VALIDATOR_NAME) final ResourceValidator resourceValidator,
                       final ResourceReaderProvider resourceReaderProvider,
                       final ReaderWrapperFactory readerWrapperFactory) {
        super(resourceValidator, resourceReaderProvider, readerWrapperFactory);
    }

    @Override
    protected List<CommentedString> processResource(BufferedReader reader) throws IOException {
        List<String> lineList = reader.lines()
                                        .filter(StringUtils::isNotBlank)
                                        .collect(Collectors.toCollection(ArrayList::new));
        List<CommentedString> commentedStringList = new ArrayList<>();

        String description = null;
        String concatenatedSequence = "";
        for (String line : lineList) {
            line = line.trim();
            if (line.startsWith(PROMPT)) {
                if (description != null) {
                    Preconditions.checkArgument(!concatenatedSequence.isEmpty(), "Multiple header is detected");
                    commentedStringList.add(new CommentedString(description, concatenatedSequence));
                    concatenatedSequence = "";
                }
                description = line.substring(1, line.length());
            } else {
                concatenatedSequence += line;
            }
        }
        Preconditions.checkArgument(description != null && !concatenatedSequence.isEmpty(), "Multiple header is detected");
        commentedStringList.add(new CommentedString(description, concatenatedSequence));

        return commentedStringList;
    }
}

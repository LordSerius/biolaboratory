package hu.bioinformatics.biolaboratory.utils.resource.read;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.utils.resource.CommentedLine;
import hu.bioinformatics.biolaboratory.utils.resource.extension.ResourceReaderProvider;
import hu.bioinformatics.biolaboratory.utils.resource.extension.ResourceValidator;
import hu.bioinformatics.biolaboratory.utils.resource.read.wrapper.ReaderWrapperFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reads a resource line by line. The lines contains only sequences.
 *
 * @author Attila Radi
 */
public class LineReader extends ResourceReader {
    /**
     * Constructor is waiting for a {@link ResourceReaderProvider}.
     *
     * @param resourceValidator {@link ResourceValidator}
     * @param resourceReaderProvider {@link ResourceReaderProvider}
     * @param readerWrapperFactory {@link ReaderWrapperFactory}
     */
    @Inject
    public LineReader(@Named(GuiceCoreModule.SIMPLE_VALIDATOR_NAME) final ResourceValidator resourceValidator,
                      final ResourceReaderProvider resourceReaderProvider,
                      final ReaderWrapperFactory readerWrapperFactory) {
        super(resourceValidator, resourceReaderProvider, readerWrapperFactory);
    }

    @Override
    protected List<CommentedLine> processResource(BufferedReader reader) throws IOException {
        List<CommentedLine> lines = reader.lines()
                .map(line -> new CommentedLine("", line))
                .collect(Collectors.toCollection(ArrayList::new));
        Preconditions.checkArgument(lines.size() >= 1, "Resource is empty");
        return lines;
    }
}

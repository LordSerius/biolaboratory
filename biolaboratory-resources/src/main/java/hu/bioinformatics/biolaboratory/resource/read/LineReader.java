package hu.bioinformatics.biolaboratory.resource.read;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceReaderProvider;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceValidator;
import hu.bioinformatics.biolaboratory.resource.read.wrapper.ReaderWrapperFactory;
import hu.bioinformatics.biolaboratory.utils.datastructures.CommentedString;

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
    public LineReader(@Named(GuiceResourceModule.SIMPLE_VALIDATOR_NAME) final ResourceValidator resourceValidator,
                      final ResourceReaderProvider resourceReaderProvider,
                      final ReaderWrapperFactory readerWrapperFactory) {
        super(resourceValidator, resourceReaderProvider, readerWrapperFactory);
    }

    @Override
    protected List<CommentedString> processResource(BufferedReader reader) throws IOException {
        List<CommentedString> lines = reader.lines()
                .map(line -> new CommentedString("", line))
                .collect(Collectors.toCollection(ArrayList::new));
        Preconditions.checkArgument(lines.size() >= 1, "Resource is empty");
        return lines;
    }
}

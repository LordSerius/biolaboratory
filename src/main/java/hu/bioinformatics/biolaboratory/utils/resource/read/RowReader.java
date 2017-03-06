package hu.bioinformatics.biolaboratory.utils.resource.read;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.utils.resource.CommentedString;
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
 * Reads resources which have only one line. The line must be a sequence without description.
 * Throws exception if it has multiple lines.
 *
 * @author Attila Radi
 */
public class RowReader extends ResourceReader {

    /**
     * Constructor is waiting for a {@link ResourceReaderProvider}.
     *
     * @param resourceValidator {@link ResourceValidator}
     * @param resourceReaderProvider {@link ResourceReaderProvider}
     * @param readerWrapperFactory {@link ReaderWrapperFactory}
     */
    @Inject
    public RowReader(@Named(GuiceCoreModule.SIMPLE_VALIDATOR_NAME) final ResourceValidator resourceValidator,
                     final ResourceReaderProvider resourceReaderProvider,
                     final ReaderWrapperFactory readerWrapperFactory) {
        super(resourceValidator, resourceReaderProvider, readerWrapperFactory);
    }

    @Override
    protected List<CommentedString> processResource(BufferedReader reader) throws IOException {
        List<CommentedString> rawBiologicalSequenceList = reader.lines()
                .map(line -> new CommentedString("", line))
                .limit(2)
                .collect(Collectors.toCollection(ArrayList::new));
        Preconditions.checkArgument(rawBiologicalSequenceList.size() == 1, "The resource should have exactly 1 lines");
        return rawBiologicalSequenceList;
    }
}

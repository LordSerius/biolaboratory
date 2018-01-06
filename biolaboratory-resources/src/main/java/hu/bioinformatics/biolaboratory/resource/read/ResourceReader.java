package hu.bioinformatics.biolaboratory.resource.read;

import hu.bioinformatics.biolaboratory.resource.extension.ResourceReaderProvider;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceValidator;
import hu.bioinformatics.biolaboratory.resource.read.wrapper.ReaderWrapperFactory;
import hu.bioinformatics.biolaboratory.utils.datastructures.CommentedString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Provides the template for reading a resource.
 *
 * @author Attila Radi
 */
public abstract class ResourceReader {

    private final ResourceValidator resourceValidator;
    private final ResourceReaderProvider resourceReaderProvider;
    private final ReaderWrapperFactory readerWrapperFactory;

    /**
     * Constructor is waiting for a {@link ResourceReaderProvider}.
     *
     * @param resourceValidator {@link ResourceValidator}
     * @param resourceReaderProvider {@link ResourceReaderProvider}
     * @param readerWrapperFactory {@link ReaderWrapperFactory}
     */
    public ResourceReader(final ResourceValidator resourceValidator,
                          final ResourceReaderProvider resourceReaderProvider,
                          final ReaderWrapperFactory readerWrapperFactory) {
        this.resourceValidator = notNull(resourceValidator, "Resource validator should not be null");
        this.resourceReaderProvider = notNull(resourceReaderProvider, "Resource provider should not be null");
        this.readerWrapperFactory = notNull(readerWrapperFactory, "ReaderWrapperFactory should not be null");
    }

    /**
     * Reads the resource about its resource path. Returns the read lines in a list.
     *
     * @param resourcePath The resource path of the target resource.
     * @return The read lines.
     * @throws UncheckedIOException If resource reading has problem.
     */
    public final List<CommentedString> read(final String resourcePath) {
        resourceValidator.validate(resourcePath);

        BufferedReader br = null;
        List<CommentedString> lines;

        try {
            br = readerWrapperFactory.wrap(resourceReaderProvider.provideReader(resourcePath));
            lines = processResource(br);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        return lines;
    }

    /**
     * Processes the resource and returns with the read data.
     *
     * @param reader The {@link Reader} which is responsible for handling the resource.
     * @return The processed input data.
     * @throws IOException If exception occurs during the resource reading.
     */
    protected abstract List<CommentedString> processResource(BufferedReader reader) throws IOException;
}

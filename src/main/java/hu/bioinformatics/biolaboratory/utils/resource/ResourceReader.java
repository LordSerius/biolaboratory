package hu.bioinformatics.biolaboratory.utils.resource;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

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

    private final ResourceReaderProvider resourceReaderProvider;

    /**
     * Constructor is waiting for a {@link ResourceReaderProvider}.
     *
     * @param resourceReaderProvider {@link ResourceReaderProvider}
     */
    public ResourceReader(ResourceReaderProvider resourceReaderProvider) {
        this.resourceReaderProvider = notNull(resourceReaderProvider, "Resource provider should not be null");
    }

    /**
     * Reads the resource about its resource path. Returns the read lines in a list.
     *
     * @param resourcePath The resource path of the target resource.
     * @return The read lines.
     */
    public final List<CommentedLine> read(String resourcePath) {
        Preconditions.checkArgument(StringUtils.isNotBlank(resourcePath), "The input resource path should not be blank");

        BufferedReader br = null;
        List<CommentedLine> lines = null;

        try {
            br = new BufferedReader(resourceReaderProvider.provideReader(resourcePath));
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
    protected abstract List<CommentedLine> processResource(BufferedReader reader) throws IOException;
}

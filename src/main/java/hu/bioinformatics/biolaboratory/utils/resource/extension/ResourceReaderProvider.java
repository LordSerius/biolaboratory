package hu.bioinformatics.biolaboratory.utils.resource.extension;

import java.io.IOException;
import java.io.Reader;

/**
 * Provides an interface to provide {@link Reader} objects. The caller is responsible to use and close the provided
 * {@link Reader} object in the proper way.
 *
 * @author Attila Radi
 */
public interface ResourceReaderProvider {

    /**
     * Provides a {@link Reader} for the resource.
     *
     * @param resourcePath The resource path.
     * @return The {@link Reader} of the target resource.
     * @throws IOException If exception occurs during the construction.
     */
    Reader provideReader(final String resourcePath) throws IOException;
}

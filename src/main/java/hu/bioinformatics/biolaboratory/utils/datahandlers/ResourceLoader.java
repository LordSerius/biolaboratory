package hu.bioinformatics.biolaboratory.utils.datahandlers;

import hu.bioinformatics.biolaboratory.utils.resource.CommentedString;
import hu.bioinformatics.biolaboratory.utils.resource.read.ResourceReader;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Provides an abstract class to load object from different external resources.
 * 
 * @author Attila Radi
 */
public abstract class ResourceLoader<RES> {
    private final ResourceReader reader;

    public ResourceLoader(final ResourceReader resourceReader) {
        this.reader = notNull(resourceReader);
    }
    /**
     * Load an object from an external resource.
     * 
     * @param resourcePath The address of the external resource.
     * @return The object from the external resource.
     * @throws UncheckedIOException If {@link IOException} occurs.
     */
    public final RES load(final String resourcePath) {
        return convert(reader.read(resourcePath));
    }

    /**
     * Converts the raw converted lines into the target {@literal<RES>} type.
     *
     * @param lines The read raw lines.
     * @return The decoded type.
     */
    protected abstract RES convert(final List<CommentedString> lines);
}

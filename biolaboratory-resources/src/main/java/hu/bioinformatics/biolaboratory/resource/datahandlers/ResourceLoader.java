package hu.bioinformatics.biolaboratory.resource.datahandlers;

import hu.bioinformatics.biolaboratory.resource.read.ResourceReader;
import hu.bioinformatics.biolaboratory.utils.CommentedString;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

/**
 * Provides an abstract class to load object from different external resources.
 * 
 * @author Attila Radi
 */
public abstract class ResourceLoader<RES> {
    private final ResourceReader reader;

    public ResourceLoader(final ResourceReader resourceReader) {
        this.reader = Validate.notNull(resourceReader);
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

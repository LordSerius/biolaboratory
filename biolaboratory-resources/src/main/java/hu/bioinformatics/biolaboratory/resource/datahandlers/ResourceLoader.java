package hu.bioinformatics.biolaboratory.resource.datahandlers;

import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.resource.read.ResourceReader;
import hu.bioinformatics.biolaboratory.utils.CommentedString;

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
    private final ResourceLocalizer resourceLocalizer;
    private final ResourceReader reader;

    public ResourceLoader(final ResourceLocalizer resourceLocalizer,
                          final ResourceReader resourceReader) {
        this.resourceLocalizer = notNull(resourceLocalizer);
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
        return convert(reader.read(resourceLocalizer.localizeResource(resourcePath)));
    }

    /**
     * Converts the raw converted lines into the target {@literal<RES>} type.
     *
     * @param lines The read raw lines.
     * @return The decoded type.
     */
    protected abstract RES convert(final List<CommentedString> lines);
}

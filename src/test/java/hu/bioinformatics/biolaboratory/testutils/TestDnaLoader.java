package hu.bioinformatics.biolaboratory.testutils;

import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaLoader;
import hu.bioinformatics.biolaboratory.utils.resource.extension.ResourceLocalizer;

import javax.inject.Inject;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Load a {@link Dna} from the test resource path.
 *
 * @author Attila Radi
 */
public class TestDnaLoader {

    private final DnaLoader dnaLoader;
    private final ResourceLocalizer localFileResourceLocalizer;

    @Inject
    public TestDnaLoader(final DnaLoader dnaLoader,
                         final ResourceLocalizer resourceLocalizer) {
        this.dnaLoader = notNull(dnaLoader);
        this.localFileResourceLocalizer = notNull(resourceLocalizer);
    }

    /**
     * Lookup the file about the file name, and loads the {@link Dna}.
     *
     * @param resourceName The name of the file.
     * @return The contained {@link Dna} of the file.
     */
    public Dna loadFromResource(final String resourceName) {
        return dnaLoader.load(localFileResourceLocalizer.localizeResource(resourceName));
    }
}

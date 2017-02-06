package hu.bioinformatics.biolaboratory.testutils;

import hu.bioinformatics.biolaboratory.guice.GuiceModule;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaDataReader;
import hu.bioinformatics.biolaboratory.utils.resource.ResourceLocalizer;

import javax.inject.Inject;
import javax.inject.Named;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Load a {@link Dna} from the test resource path.
 *
 * @author Attila Radi
 */
public class TestDnaLoader {

    private final DnaDataReader dnaDataReader;
    private final ResourceLocalizer localFileResourceLocalizer;

    @Inject
    public TestDnaLoader(@Named(value = GuiceModule.ROW_READER_NAME) DnaDataReader dnaDataReader,
                         ResourceLocalizer resourceLocalizer) {
        this.dnaDataReader = notNull(dnaDataReader);
        this.localFileResourceLocalizer = notNull(resourceLocalizer);
    }

    /**
     * Lookup the file about the file name, and loads the {@link Dna}.
     *
     * @param resourceName The name of the file.
     * @return The contained {@link Dna} of the file.
     */
    public Dna loadFromResource(String resourceName) {
        return dnaDataReader.load(localFileResourceLocalizer.localizeResource(resourceName));
    }
}

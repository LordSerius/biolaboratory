package hu.bioinformatics.biolaboratory.testutils;

import hu.bioinformatics.biolaboratory.guice.GuiceModule;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaDataReader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaRowReader;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Load a {@link Dna} from the test resource path.
 *
 * @author Attila Radi
 */
public class TestDnaLoader {

    private final DnaDataReader dnaDataReader;

    @Inject
    public TestDnaLoader(@Named(value = GuiceModule.ROW_READER_NAME) DnaDataReader dnaDataReader) {
        this.dnaDataReader = dnaDataReader;
    }

    /**
     * Lookup the file about the file name, and loads the {@link Dna}.
     *
     * @param resourceName The name of the file.
     * @return The contained {@link Dna} of the file.
     */
    public Dna loadFromResource(String resourceName) {
        return dnaDataReader.load(ResourceLoader.loadResourceFile(resourceName));
    }
}

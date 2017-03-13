package hu.bioinformatics.biolaboratory.testutils;

import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.sequence.dna.DnaArray;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaArrayLoader;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Loads test {@link DnaArray}s from the local resources folder.
 *
 * @author Attila Radi
 */
public class TestDnaArrayLoader {

    private final DnaArrayLoader resourceLoader;
    private final ResourceLocalizer localFileResourceLocalizer;

    @Inject
    public TestDnaArrayLoader(final DnaArrayLoader resourceLoader,
                         final ResourceLocalizer resourceLocalizer) {
        this.resourceLoader = Validate.notNull(resourceLoader);
        this.localFileResourceLocalizer = Validate.notNull(resourceLocalizer);
    }

    /**
     * Lookup the file about the file name, and loads the {@link DnaArray}.
     *
     * @param resourceName The name of the file.
     * @return The contained {@link DnaArray} of the file.
     */
    public DnaArray loadFromResource(final String resourceName) {
        return resourceLoader.load(localFileResourceLocalizer.localizeResource(resourceName));
    }
}

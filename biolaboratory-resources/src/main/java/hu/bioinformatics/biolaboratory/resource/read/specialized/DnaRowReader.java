package hu.bioinformatics.biolaboratory.resource.read.specialized;

import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.resource.read.wrapper.ReaderWrapperFactory;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceReaderProvider;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceValidator;
import hu.bioinformatics.biolaboratory.resource.extension.impl.DnaFileValidator;
import hu.bioinformatics.biolaboratory.resource.read.RowReader;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Gives a {@link DnaFileValidator} and a {@link ResourceValidator} at the constructor, to read .dna extension files.
 * <p>
 * NOTE: It does not need test cases.
 *
 * @author Attila Radi
 */
public class DnaRowReader extends RowReader {
    /**
     * Constructor is waiting for a {@link ResourceReaderProvider}.
     *
     * @param resourceValidator      {@link ResourceValidator}
     * @param resourceReaderProvider {@link ResourceReaderProvider}
     * @param readerWrapperFactory {@link ReaderWrapperFactory}
     */
    @Inject
    public DnaRowReader(@Named(GuiceResourceModule.DNA_VALIDATOR_NAME) final ResourceValidator resourceValidator,
                        final ResourceReaderProvider resourceReaderProvider,
                        final ReaderWrapperFactory readerWrapperFactory) {
        super(resourceValidator, resourceReaderProvider, readerWrapperFactory);
    }
}

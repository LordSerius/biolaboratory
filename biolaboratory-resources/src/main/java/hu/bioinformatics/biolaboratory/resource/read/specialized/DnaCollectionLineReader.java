package hu.bioinformatics.biolaboratory.resource.read.specialized;

import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceReaderProvider;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceValidator;
import hu.bioinformatics.biolaboratory.resource.extension.impl.DnaResourceValidator;
import hu.bioinformatics.biolaboratory.resource.read.LineReader;
import hu.bioinformatics.biolaboratory.resource.read.wrapper.ReaderWrapperFactory;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Gives a {@link DnaResourceValidator} at the constructor, to read .dnacol extension files.
 * <p>
 * NOTE: It does not need test cases.
 *
 * @author Attila Radi
 */
public class DnaCollectionLineReader extends LineReader {
    /**
     * Constructor is waiting for a {@link ResourceReaderProvider}.
     *
     * @param resourceValidator      {@link ResourceValidator}
     * @param resourceReaderProvider {@link ResourceReaderProvider}
     * @param readerWrapperFactory   {@link ReaderWrapperFactory}
     */
    @Inject
    public DnaCollectionLineReader(@Named(GuiceResourceModule.DNA_COLLECTION_VALIDATOR_NAME) final ResourceValidator resourceValidator,
                                   final ResourceReaderProvider resourceReaderProvider,
                                   final ReaderWrapperFactory readerWrapperFactory) {
        super(resourceValidator, resourceReaderProvider, readerWrapperFactory);
    }
}

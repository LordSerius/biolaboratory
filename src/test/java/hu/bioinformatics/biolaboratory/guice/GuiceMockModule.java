package hu.bioinformatics.biolaboratory.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaArrayFastaLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaRowLoader;
import hu.bioinformatics.biolaboratory.utils.resource.extension.ResourceReaderProvider;
import hu.bioinformatics.biolaboratory.utils.resource.read.MockReaderWrapperFactory;
import hu.bioinformatics.biolaboratory.utils.resource.read.wrapper.ReaderWrapperFactory;
import org.mockito.Mock;

import java.io.IOException;
import java.io.Reader;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Provides mock objects to test the operations with external resources.
 *
 * @author Attila Radi
 */
public class GuiceMockModule extends AbstractModule {

    @Mock
    private ResourceReaderProvider resourceReaderProvider;

    @Mock
    private Reader reader;

    @Override
    protected void configure() {
        initializeMocks();

        bind(ReaderWrapperFactory.class).to(MockReaderWrapperFactory.class).in(Scopes.SINGLETON);
        bind(ResourceReaderProvider.class).toInstance(resourceReaderProvider);

        bind(DnaRowLoader.class).in(Scopes.SINGLETON);
        bind(DnaArrayFastaLoader.class).in(Scopes.SINGLETON);
    }

    private void initializeMocks() {
        initMocks(this);
        try {
            when(resourceReaderProvider.provideReader(anyString())).thenReturn(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

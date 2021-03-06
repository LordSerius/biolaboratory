package hu.bioinformatics.biolaboratory.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceReaderProvider;
import hu.bioinformatics.biolaboratory.resource.read.MockReaderWrapperFactory;
import hu.bioinformatics.biolaboratory.resource.read.wrapper.ReaderWrapperFactory;
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
public class GuiceResourceMockModule extends AbstractModule {

    @Mock
    private ResourceReaderProvider resourceReaderProvider;

    @Mock
    private Reader reader;

    @Mock
    private ResourceLocalizer resourceLocalizer;

    @Override
    protected void configure() {
        initializeMocks();

        bind(ReaderWrapperFactory.class).to(MockReaderWrapperFactory.class).in(Scopes.SINGLETON);
        bind(ResourceReaderProvider.class).toInstance(resourceReaderProvider);
        bind(ResourceLocalizer.class).toInstance(resourceLocalizer);
    }

    private void initializeMocks() {
        initMocks(this);
        try {
            when(resourceReaderProvider.provideReader(anyString())).thenReturn(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        when(resourceLocalizer.localizeResource(anyString())).thenAnswer(invocation -> (String) invocation.getArguments()[0]);
    }
}

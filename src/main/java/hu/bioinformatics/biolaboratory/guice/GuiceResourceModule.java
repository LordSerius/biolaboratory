package hu.bioinformatics.biolaboratory.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import hu.bioinformatics.biolaboratory.utils.resource.extension.ResourceReaderProvider;
import hu.bioinformatics.biolaboratory.utils.resource.extension.impl.FileResourceReaderProvider;
import hu.bioinformatics.biolaboratory.utils.resource.read.wrapper.ReaderWrapperFactory;

/**
 * Provides dependencies to handle external resources.
 *
 * @author Attila Radi
 */
public class GuiceResourceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ResourceReaderProvider.class).to(FileResourceReaderProvider.class).in(Scopes.SINGLETON);
        bind(ReaderWrapperFactory.class).in(Scopes.SINGLETON);
    }
}

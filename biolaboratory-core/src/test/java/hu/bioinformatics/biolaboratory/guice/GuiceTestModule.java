package hu.bioinformatics.biolaboratory.guice;

import hu.bioinformatics.biolaboratory.testutils.TestDnaArrayLoader;
import hu.bioinformatics.biolaboratory.testutils.TestDnaLoader;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import hu.bioinformatics.biolaboratory.testutils.TestDnaCollectionLoader;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceReaderProvider;
import hu.bioinformatics.biolaboratory.resource.extension.impl.FileResourceReaderProvider;
import hu.bioinformatics.biolaboratory.resource.extension.impl.LocalFileResourceLocalizer;
import hu.bioinformatics.biolaboratory.resource.read.wrapper.ReaderWrapperFactory;

/**
 * Add dependency injection for test environment.
 *
 * @author Attila Radi
 */
public class GuiceTestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ResourceLocalizer.class).to(LocalFileResourceLocalizer.class).in(Scopes.SINGLETON);

        bind(ResourceReaderProvider.class).to(FileResourceReaderProvider.class).in(Scopes.SINGLETON);
        bind(ReaderWrapperFactory.class).in(Scopes.SINGLETON);

        bind(TestDnaCollectionLoader.class).in(Scopes.SINGLETON);
        bind(TestDnaLoader.class).in(Scopes.SINGLETON);
        bind(TestDnaArrayLoader.class).in(Scopes.SINGLETON);
    }
}

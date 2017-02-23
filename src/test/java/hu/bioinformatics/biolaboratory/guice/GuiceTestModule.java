package hu.bioinformatics.biolaboratory.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import hu.bioinformatics.biolaboratory.testutils.TestDnaCollectionLoader;
import hu.bioinformatics.biolaboratory.testutils.TestDnaLoader;

/**
 * Add dependency injection for test environment.
 *
 * @author Attila Radi
 */
public class GuiceTestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TestDnaCollectionLoader.class).in(Scopes.SINGLETON);
        bind(TestDnaLoader.class).in(Scopes.SINGLETON);
    }
}

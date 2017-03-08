package hu.bioinformatics.biolaboratory.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaArrayFastaLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaRowLoader;

/**
 * Provides mock objects to test the operations with external resources.
 *
 * @author Attila Radi
 */
public class GuiceCoreMockModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DnaRowLoader.class).in(Scopes.SINGLETON);
        bind(DnaArrayFastaLoader.class).in(Scopes.SINGLETON);
    }
}

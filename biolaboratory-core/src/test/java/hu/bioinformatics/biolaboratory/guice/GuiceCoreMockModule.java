package hu.bioinformatics.biolaboratory.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaArrayFastaLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaArrayLineLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaListFastaLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaRowLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaSetRowLoader;

/**
 * Provides mock objects to test the operations with external resources.
 *
 * @author Attila Radi
 */
public class GuiceCoreMockModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DnaRowLoader.class).in(Scopes.SINGLETON);
        bind(DnaArrayLineLoader.class).in(Scopes.SINGLETON);
        bind(DnaArrayFastaLoader.class).in(Scopes.SINGLETON);
        bind(DnaListFastaLoader.class).in(Scopes.SINGLETON);
        bind(DnaSetRowLoader.class).in(Scopes.SINGLETON);
    }
}

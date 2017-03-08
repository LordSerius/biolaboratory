package hu.bioinformatics.biolaboratory.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaArrayLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaListLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaArrayFastaLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaListFastaLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaRowLoader;

/**
 * Provides dependency injection for the normal run.
 *
 * @author Attila Radi
 */
public class GuiceCoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DnaLoader.class).to(DnaRowLoader.class).in(Scopes.SINGLETON);
        bind(DnaArrayLoader.class).to(DnaArrayFastaLoader.class).in(Scopes.SINGLETON);
        bind(DnaListLoader.class).to(DnaListFastaLoader.class).in(Scopes.SINGLETON);
    }
}

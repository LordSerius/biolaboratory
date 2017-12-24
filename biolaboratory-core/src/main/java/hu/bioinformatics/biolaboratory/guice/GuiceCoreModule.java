package hu.bioinformatics.biolaboratory.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaArrayLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaSetLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaArrayLineLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaRowLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.implementation.DnaSetRowLoader;

/**
 * Provides dependency injection for the normal run.
 *
 * @author Attila Radi
 */
public class GuiceCoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DnaLoader.class).to(DnaRowLoader.class).in(Scopes.SINGLETON);
        bind(DnaArrayLoader.class).to(DnaArrayLineLoader.class).in(Scopes.SINGLETON);
        bind(DnaSetLoader.class).to(DnaSetRowLoader.class).in(Scopes.SINGLETON);
    }
}

package hu.bioinformatics.biolaboratory.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import hu.bioinformatics.biolaboratory.utils.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.utils.resource.extension.ResourceReaderProvider;
import hu.bioinformatics.biolaboratory.utils.resource.extension.impl.FileResourceLocalizer;
import hu.bioinformatics.biolaboratory.utils.resource.extension.impl.FileResourceReaderProvider;
import hu.bioinformatics.biolaboratory.utils.resource.extension.impl.LocalFileResourceLocalizer;
import hu.bioinformatics.biolaboratory.utils.resource.read.wrapper.ReaderWrapperFactory;

/**
 * Provides dependencies to handle external resources.
 *
 * @author Attila Radi
 */
public class GuiceResourceModule extends AbstractModule {

    public static final String LOCAL_RESOURCE_LOCALIZER_NAME = "localResourceLocalizer";

    @Override
    protected void configure() {
        bind(ResourceLocalizer.class).to(FileResourceLocalizer.class).in(Scopes.SINGLETON);
        bind(ResourceLocalizer.class).annotatedWith(Names.named(LOCAL_RESOURCE_LOCALIZER_NAME)).to(LocalFileResourceLocalizer.class).in(Scopes.SINGLETON);
        bind(ResourceReaderProvider.class).to(FileResourceReaderProvider.class).in(Scopes.SINGLETON);
        bind(ReaderWrapperFactory.class).in(Scopes.SINGLETON);
    }
}

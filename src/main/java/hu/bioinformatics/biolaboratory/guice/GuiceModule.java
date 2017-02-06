package hu.bioinformatics.biolaboratory.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaDataReader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaRowReader;
import hu.bioinformatics.biolaboratory.utils.resource.*;
import hu.bioinformatics.biolaboratory.utils.resource.file.FileResourceReaderProvider;
import hu.bioinformatics.biolaboratory.utils.resource.file.LocalFileResourceLocalizer;

/**
 * Provides dependency injection for the normal run.
 *
 * @author Attila Radi
 */
public class GuiceModule extends AbstractModule {

    public static final String ROW_READER_NAME = "rowReader";
    public static final String LINE_READER_NAME = "lineReader";
    public static final String FASTA_READER_NAME = "fastaReader";

    @Override
    protected void configure() {
        bind(ResourceLocalizer.class).to(LocalFileResourceLocalizer.class).in(Scopes.SINGLETON);
        bind(ResourceReaderProvider.class).to(FileResourceReaderProvider.class).in(Scopes.SINGLETON);
        bind(ResourceReader.class).annotatedWith(Names.named(ROW_READER_NAME)).to(RowReader.class).in(Scopes.SINGLETON);
        bind(ResourceReader.class).annotatedWith(Names.named(LINE_READER_NAME)).to(LineReader.class).in(Scopes.SINGLETON);
        bind(ResourceReader.class).annotatedWith(Names.named(FASTA_READER_NAME)).to(FastaReader.class).in(Scopes.SINGLETON);
        bind(DnaDataReader.class).annotatedWith(Names.named(ROW_READER_NAME)).to(DnaRowReader.class).in(Scopes.SINGLETON);
    }
}

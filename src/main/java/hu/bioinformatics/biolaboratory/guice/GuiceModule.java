package hu.bioinformatics.biolaboratory.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaDataReader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaRowReader;
import hu.bioinformatics.biolaboratory.utils.resource.FastaReader;
import hu.bioinformatics.biolaboratory.utils.resource.ResourceProvider;
import hu.bioinformatics.biolaboratory.utils.resource.ResourceReader;
import hu.bioinformatics.biolaboratory.utils.resource.RowReader;
import hu.bioinformatics.biolaboratory.utils.resource.file.FileResourceProvider;

import javax.inject.Named;

/**
 * Provides dependency injection for the normal run.
 *
 * @author Attila Radi
 */
public class GuiceModule extends AbstractModule {

    public static final String ROW_READER_NAME = "rowReader";
    public static final String FASTA_READER_NAME = "fastaReader";

    @Override
    protected void configure() {
        bind(ResourceProvider.class).to(FileResourceProvider.class);
        bind(ResourceReader.class).annotatedWith(Names.named(ROW_READER_NAME)).to(RowReader.class);
        bind(ResourceReader.class).annotatedWith(Names.named(FASTA_READER_NAME)).to(FastaReader.class);
        bind(DnaDataReader.class).annotatedWith(Names.named(ROW_READER_NAME)).to(DnaRowReader.class);
    }
}

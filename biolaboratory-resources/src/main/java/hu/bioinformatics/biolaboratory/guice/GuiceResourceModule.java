package hu.bioinformatics.biolaboratory.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceValidator;
import hu.bioinformatics.biolaboratory.resource.extension.impl.DnaFileValidator;
import hu.bioinformatics.biolaboratory.resource.extension.impl.FastaResourceValidator;
import hu.bioinformatics.biolaboratory.resource.extension.impl.SimpleResourceValidator;
import hu.bioinformatics.biolaboratory.resource.read.FastaReader;
import hu.bioinformatics.biolaboratory.resource.read.LineReader;
import hu.bioinformatics.biolaboratory.resource.read.ResourceReader;
import hu.bioinformatics.biolaboratory.resource.read.RowReader;
import hu.bioinformatics.biolaboratory.resource.read.specialized.DnaRowReader;

/**
 * Provides dependency injection for the normal run.
 *
 * @author Attila Radi
 */
public class GuiceResourceModule extends AbstractModule {

    public static final String SIMPLE_VALIDATOR_NAME = "simpleValidator";
    public static final String DNA_VALIDATOR_NAME = "dnaValidator";
    public static final String FASTA_VALIDATOR_NAME = "fastaValidator";

    public static final String ROW_READER_NAME = "rowReader";
    public static final String DNA_ROW_READER_NAME = "dnaRowReader";
    public static final String LINE_READER_NAME = "lineReader";
    public static final String FASTA_READER_NAME = "fastaReader";

    @Override
    protected void configure() {
        bind(ResourceValidator.class).annotatedWith(Names.named(SIMPLE_VALIDATOR_NAME)).to(SimpleResourceValidator.class).in(Scopes.SINGLETON);
        bind(ResourceValidator.class).annotatedWith(Names.named(FASTA_VALIDATOR_NAME)).to(FastaResourceValidator.class).in(Scopes.SINGLETON);
        bind(ResourceValidator.class).annotatedWith(Names.named(DNA_VALIDATOR_NAME)).to(DnaFileValidator.class).in(Scopes.SINGLETON);

        bind(ResourceReader.class).annotatedWith(Names.named(ROW_READER_NAME)).to(RowReader.class).in(Scopes.SINGLETON);
        bind(ResourceReader.class).annotatedWith(Names.named(DNA_ROW_READER_NAME)).to(DnaRowReader.class).in(Scopes.SINGLETON);
        bind(ResourceReader.class).annotatedWith(Names.named(LINE_READER_NAME)).to(LineReader.class).in(Scopes.SINGLETON);
        bind(ResourceReader.class).annotatedWith(Names.named(FASTA_READER_NAME)).to(FastaReader.class).in(Scopes.SINGLETON);
    }
}

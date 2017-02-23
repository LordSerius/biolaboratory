package hu.bioinformatics.biolaboratory.utils.datahandlers;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.resource.CommentedLine;
import hu.bioinformatics.biolaboratory.utils.resource.read.ResourceReader;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.UncheckedIOException;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Read a {@link Dna} from the 
 * 
 * @author Attila Radi
 *
 */
public class DnaRowLoader implements DnaLoader {

    private final ResourceReader rowReader;

    @Inject
    public DnaRowLoader(@Named(GuiceCoreModule.DNA_ROW_READER_NAME) final ResourceReader resourceReader) {
        this.rowReader = notNull(resourceReader);
    }

    /**
     * Load a DNA from the target file. The file has to be a .dna extension.
     * 
     * @param resourcePath The file path of the .dna file.
     * @return The DNA inside the file.
     * @throws UncheckedIOException If {@link IOException} occurs.
     */
    @Override
    public Dna load(final String resourcePath) {
        CommentedLine commentedLine = rowReader.read(resourcePath).get(0);
        return Dna.build(commentedLine.getComment(), commentedLine.getLine());
    }

}

package hu.bioinformatics.biolaboratory.utils.datahandlers;

import hu.bioinformatics.biolaboratory.guice.GuiceModule;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.resource.CommentedLine;
import hu.bioinformatics.biolaboratory.utils.resource.ResourceReader;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.UncheckedIOException;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * TODO: test it
 * 
 * Read a {@link Dna} from the 
 * 
 * @author Attila Radi
 *
 */
public class DnaRowReader implements DnaDataReader {

    private final ResourceReader rowReader;

    @Inject
    public DnaRowReader(@Named(value = GuiceModule.ROW_READER_NAME) ResourceReader resourceReader) {
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
    public Dna load(String resourcePath) {
        DnaFileValidator.validateFilePath(resourcePath);
        CommentedLine commentedLine = rowReader.read(resourcePath).get(0);
        return Dna.build(commentedLine.getComment(), commentedLine.getLine());
    }

}

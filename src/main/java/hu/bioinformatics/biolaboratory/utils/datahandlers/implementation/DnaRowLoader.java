package hu.bioinformatics.biolaboratory.utils.datahandlers.implementation;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaLoader;
import hu.bioinformatics.biolaboratory.utils.resource.CommentedString;
import hu.bioinformatics.biolaboratory.utils.resource.read.ResourceReader;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Read a {@link Dna} from a .dna file.
 * 
 * @author Attila Radi
 *
 */
public class DnaRowLoader extends DnaLoader {

    @Inject
    public DnaRowLoader(@Named(GuiceCoreModule.DNA_ROW_READER_NAME) final ResourceReader resourceReader) {
        super(resourceReader);
    }

    @Override
    protected Dna convert(final List<CommentedString> lines) {
        CommentedString commentedString = lines.get(0);
        return Dna.build(commentedString.getComment(), commentedString.getString());
    }

}

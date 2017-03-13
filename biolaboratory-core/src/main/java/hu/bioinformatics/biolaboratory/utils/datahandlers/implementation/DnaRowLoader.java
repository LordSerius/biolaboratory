package hu.bioinformatics.biolaboratory.utils.datahandlers.implementation;

import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.resource.read.ResourceReader;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.CommentedString;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaLoader;

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
    public DnaRowLoader(final ResourceLocalizer resourceLocalizer,
                        @Named(GuiceResourceModule.DNA_ROW_READER_NAME) final ResourceReader resourceReader) {
        super(resourceLocalizer, resourceReader);
    }

    @Override
    protected final Dna convert(final List<CommentedString> lines) {
        CommentedString commentedString = lines.get(0);
        return Dna.build(commentedString.getComment(), commentedString.getString());
    }
}

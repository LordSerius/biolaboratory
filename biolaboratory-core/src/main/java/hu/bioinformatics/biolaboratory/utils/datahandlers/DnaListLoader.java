package hu.bioinformatics.biolaboratory.utils.datahandlers;

import hu.bioinformatics.biolaboratory.resource.datahandlers.ResourceLoader;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.resource.read.ResourceReader;

import java.util.List;

/**
 * Loads a list of {@link Dna}.
 *
 * @author Attila Radi
 */
public abstract class DnaListLoader extends ResourceLoader<List<Dna>> {
    public DnaListLoader(final ResourceLocalizer resourceLocalizer,
                         final ResourceReader resourceReader) {
        super(resourceLocalizer, resourceReader);
    }
}

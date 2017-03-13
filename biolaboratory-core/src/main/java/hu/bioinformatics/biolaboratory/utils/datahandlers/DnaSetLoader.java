package hu.bioinformatics.biolaboratory.utils.datahandlers;

import hu.bioinformatics.biolaboratory.resource.datahandlers.ResourceLoader;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.resource.read.ResourceReader;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;

import java.util.Set;

/**
 * Loads a {@link Set} of {@link Dna}s.
 *
 * @author Attila Radi
 */
public abstract class DnaSetLoader extends ResourceLoader<Set<Dna>> {
    public DnaSetLoader(ResourceLocalizer resourceLocalizer, ResourceReader resourceReader) {
        super(resourceLocalizer, resourceReader);
    }
}

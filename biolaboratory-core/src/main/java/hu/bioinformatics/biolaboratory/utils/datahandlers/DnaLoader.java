package hu.bioinformatics.biolaboratory.utils.datahandlers;

import hu.bioinformatics.biolaboratory.resource.datahandlers.ResourceLoader;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.resource.read.ResourceReader;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;

/**
 * Abstract class to load {@link Dna}s.
 *
 * @author Attila Radi
 */
public abstract class DnaLoader extends ResourceLoader<Dna> {
    public DnaLoader(final ResourceLocalizer resourceLocalizer, final ResourceReader resourceReader) {
        super(resourceLocalizer, resourceReader);
    }
}

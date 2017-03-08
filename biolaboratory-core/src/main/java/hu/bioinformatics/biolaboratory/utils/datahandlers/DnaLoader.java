package hu.bioinformatics.biolaboratory.utils.datahandlers;

import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.resource.read.ResourceReader;

/**
 * Abstract class to load {@link Dna}s.
 *
 * @author Attila Radi
 */
public abstract class DnaLoader extends ResourceLoader<Dna> {
    public DnaLoader(final ResourceReader resourceReader) {
        super(resourceReader);
    }
}

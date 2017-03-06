package hu.bioinformatics.biolaboratory.utils.datahandlers;

import hu.bioinformatics.biolaboratory.sequence.dna.DnaArray;
import hu.bioinformatics.biolaboratory.utils.resource.read.ResourceReader;

/**
 * Abstract class to load {@link DnaArray}s
 *
 * @author Attila Radi
 */
public abstract class DnaArrayLoader extends ResourceLoader<DnaArray> {
    public DnaArrayLoader(final ResourceReader resourceReader) {
        super(resourceReader);
    }
}

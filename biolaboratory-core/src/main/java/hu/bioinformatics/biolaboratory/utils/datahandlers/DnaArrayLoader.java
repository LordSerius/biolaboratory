package hu.bioinformatics.biolaboratory.utils.datahandlers;

import hu.bioinformatics.biolaboratory.resource.datahandlers.ResourceLoader;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.resource.read.ResourceReader;
import hu.bioinformatics.biolaboratory.sequence.dna.DnaArray;

/**
 * Abstract class to load {@link DnaArray}s.
 *
 * @author Attila Radi
 */
public abstract class DnaArrayLoader extends ResourceLoader<DnaArray> {
    public DnaArrayLoader(final ResourceLocalizer resourceLocalizer,
                          final ResourceReader resourceReader) {
        super(resourceLocalizer, resourceReader);
    }
}

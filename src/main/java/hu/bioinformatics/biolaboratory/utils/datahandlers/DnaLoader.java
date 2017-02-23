package hu.bioinformatics.biolaboratory.utils.datahandlers;

import hu.bioinformatics.biolaboratory.sequence.dna.Dna;

/**
 * Provides an interface to read {@link Dna} objects from different resources.
 * 
 * @author Attila Radi
 *
 */
public interface DnaLoader {

    /**
     * Load a {@link Dna} from an external resource.
     * 
     * @param resourcePath The address of the external resource.
     * @return The {@link Dna} from the external resource.
     */
    Dna load(final String resourcePath);
}

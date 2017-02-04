package hu.bioinformatics.biolaboratory.utils.datahandlers;

import hu.bioinformatics.biolaboratory.sequence.dna.Dna;

/**
 * Write the given {@link Dna} to an external resource.
 * 
 * @author Attila Radi
 *
 */
public interface DnaDataWriter {

    /**
     * Write the given {@link Dna} to an external resource. Returns with the access path of
     * the external resource in a {@link String}
     * 
     * @param dna The persistable {@link Dna}.
     * @param resourcePath The access path of the external resource.
     */
    public void save(Dna dna, String resourcePath);
}

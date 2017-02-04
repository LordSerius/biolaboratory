package hu.bioinformatics.biolaboratory.sequence;

/**
 * Defines an interface for the build elements of the different biological sequences.
 *
 * @author Attila Radi
 */
public interface SequenceElement {

    /**
     * Get the name of the sequence element.
     *
     * @return The name of the sequence element.
     */
    String getName();

    /**
     * Get the letter identifier of the sequence element.
     *
     * @return The letter identifier of the sequence element.
     */
    char getLetter();
}

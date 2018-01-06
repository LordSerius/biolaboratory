package hu.bioinformatics.biolaboratory.utils;

import java.util.stream.IntStream;

import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkEqualNumberTo;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotNegativeNumber;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotNullArgument;

/**
 * Provides extra methods for {@link String} operations.
 *
 * @author Attila Radi
 */
public class SequenceUtils {
    public static final int SMALLER = -1;
    public static final int EQUAL = 0;
    public static final int GREATER = 1;

    private SequenceUtils() {}

    /**
     * Compares two equal length {@link String}s and calculates the number of the different characters at their same
     * sequence position.
     *
     * @param sequence A sequence.
     * @param otherSequence An other sequence.
     * @return The hamming distance of the two sequences.
     * @throws IllegalArgumentException If the two sequence have different length.
     */
    public static int hammingDistance(final String sequence, final String otherSequence) {
        validateSequences(sequence, otherSequence);

        return IntStream.range(0, sequence.length())
                .parallel()
                .map(index -> sequence.charAt(index) != otherSequence.charAt(index) ? 1 : 0)
                .sum();
    }

    /**
     * Takes two equal length {@link String} and decides their hamming distance is smaller, equal or greater than
     * the given mismatch.
     *
     * @param sequence A sequence.
     * @param otherSequence An other sequence.
     * @param mismatch The mismatch which is compared with the hamming distance.
     * @return -1 if the hamming distance is smaller than the mismatch
     *          0 if the hamming distance is equal than the mismatch
     *          1 if the hamming distance if greater than the mismatch
     * @throws IllegalArgumentException If the two sequence have different length.
     */
    public static int hammingDistanceMismatchComparator(final String sequence, final String otherSequence, final int mismatch) {
        validateSequences(sequence, otherSequence);
        checkNotNegativeNumber("Mismatch", mismatch);

        int length = sequence.length();
        int currentMismatch = 0;
        int index = 0;
        while (index < length && currentMismatch <= mismatch) {
            if (sequence.charAt(index) != otherSequence.charAt(index)) currentMismatch++;
            index++;
        }

        return currentMismatch < mismatch ? SMALLER :
                currentMismatch == mismatch ? EQUAL : GREATER;
    }

    private static void validateSequences(final String sequence, final String otherSequence) {
        checkNotNullArgument("Sequence", sequence);
        checkNotNullArgument("Other sequence", otherSequence);
        checkEqualNumberTo("First sequence", sequence.length(), "second sequence", otherSequence.length());
    }
}

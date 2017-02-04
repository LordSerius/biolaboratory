package hu.bioinformatics.biolaboratory.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;

/**
 * Transforms different {@link String} {@link Collection}s to target {@link Dna}
 * {@link Collection}.
 * 
 * @author Attila Radi
 *
 */
public class DnaCollectors {

    /**
     * Transforms a {@link String}s to a {@link Dna} {@link Set}.
     *
     * @param sequences Strings of DNA sequences.
     * @return A {@link Set} of {@link Dna} about the sequences.
     */
    public static Set<Dna> toDnaSet(final String... sequences) {
        validateVarargs(sequences);
        return toDnaSet(Arrays.asList(sequences));
    }

    /**
     * Transforms a {@link String} {@link Collection} to a {@link Dna} {@link Set}.
     * 
     * @param sequenceCollection A {@link Collection} of DNA sequences.
     * @return A {@link Set} of {@link Dna} about the sequences.
     */
    public static Set<Dna> toDnaSet(final Collection<String> sequenceCollection) {
        return toDnaStream(sequenceCollection).collect(Collectors.toSet());
    }

    /**
     * Transforms a {@link String}s to a {@link Dna} {@link List}.
     *
     * @param sequences Strings of DNA sequences.
     * @return A {@link List} of {@link Dna} about the sequences.
     */
    public static List<Dna> toDnaList(final String... sequences) {
        validateVarargs(sequences);
        return toDnaList(Arrays.asList(sequences));
    }

    /**
     * Transforms a {@link String} {@link Collection} to a {@link Dna} {@link List}.
     * 
     * @param sequenceCollection A {@link Collection} of DNA sequences.
     * @return A {@link List} of {@link Dna} about the sequences.
     */
    public static List<Dna> toDnaList(final Collection<String> sequenceCollection) {
        return toDnaStream(sequenceCollection).collect(Collectors.toList());
    }

    private static void validateVarargs(final String... sequences) {
        Preconditions.checkArgument(sequences != null, "Sequences should not be null");
    }

    private static Stream<Dna> toDnaStream(final Collection<String> sequenceCollection) {
        Preconditions.checkArgument(sequenceCollection != null, "Sequence collection should not be null");
        return sequenceCollection.stream()
                .map(sequence -> Dna.build(sequence));
    }
}

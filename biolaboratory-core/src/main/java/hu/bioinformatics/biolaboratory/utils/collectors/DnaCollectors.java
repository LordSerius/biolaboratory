package hu.bioinformatics.biolaboratory.utils.collectors;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.CommentedString;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Transforms different {@link String} {@link Collection}s to target {@link Dna} {@link Collection}.
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
    public static Set<Dna> stringToDnaSet(final String... sequences) {
        validateArgument(sequences);
        return Arrays.stream(sequences)
                .map(mapStringToDna())
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Transforms a {@link CommentedString}s to a {@link Dna} {@link Set}.
     *
     * @param sequences {@link CommentedString}s of DNA sequences.
     * @return A {@link Set} of {@link Dna} about the sequences.
     */
    public static Set<Dna> commentedStringToDnaSet(final CommentedString... sequences) {
        validateArgument(sequences);
        return Arrays.stream(sequences)
                .map(mapCommentedStringToDna())
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Transforms a {@link String} {@link Collection} to a {@link Dna} {@link Set}.
     * 
     * @param sequenceCollection A {@link Collection} of DNA sequences.
     * @return A {@link Set} of {@link Dna} about the sequences.
     */
    public static Set<Dna> stringToDnaSet(final Collection<String> sequenceCollection) {
        validateArgument(sequenceCollection);
        return sequenceCollection.stream()
                .map(mapStringToDna())
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Transforms a {@link CommentedString} {@link Collection} to a {@link Dna} {@link Set}.
     *
     * @param sequenceCollection A {@link Collection} of DNA sequences.
     * @return A {@link Set} of {@link Dna} about the sequences.
     */
    public static Set<Dna> commentedStringToDnaSet(final Collection<CommentedString> sequenceCollection) {
        validateArgument(sequenceCollection);
        return sequenceCollection.stream()
                .map(mapCommentedStringToDna())
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Transforms a {@link String}s to a {@link Dna} {@link List}.
     *
     * @param sequences Strings of DNA sequences.
     * @return A {@link List} of {@link Dna} about the sequences.
     */
    public static List<Dna> stringToDnaList(final String... sequences) {
        validateArgument(sequences);
        return Arrays.stream(sequences)
                .map(mapStringToDna())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Transforms a {@link CommentedString}s to a {@link Dna} {@link List}.
     *
     * @param sequences {@link CommentedString}s of DNA sequences.
     * @return A {@link Set} of {@link Dna} about the sequences.
     */
    public static List<Dna> commentedStringToDnaList(final CommentedString... sequences) {
        validateArgument(sequences);
        return Arrays.stream(sequences)
                .map(mapCommentedStringToDna())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Transforms a {@link String} {@link Collection} to a {@link Dna} {@link List}.
     * 
     * @param sequenceCollection A {@link Collection} of DNA sequences.
     * @return A {@link List} of {@link Dna} about the sequences.
     */
    public static List<Dna> stringToDnaList(final Collection<String> sequenceCollection) {
        validateArgument(sequenceCollection);
        return sequenceCollection.stream()
                .map(mapStringToDna())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Transforms a {@link CommentedString} {@link Collection} to a {@link Dna} {@link List}.
     *
     * @param sequenceCollection A {@link Collection} of DNA sequences.
     * @return A {@link Set} of {@link Dna} about the sequences.
     */
    public static List<Dna> commentedStringToDnaList(final Collection<CommentedString> sequenceCollection) {
        validateArgument(sequenceCollection);
        return sequenceCollection.stream()
                .map(mapCommentedStringToDna())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static <COL> void validateArgument(final COL sequences) {
        Preconditions.checkArgument(sequences != null, "Sequences should not be null");
    }

    private static Function<String, Dna> mapStringToDna() {
        return Dna::build;
    }

    private static Function<CommentedString, Dna> mapCommentedStringToDna() {
        return (commentedString) -> Optional.ofNullable(commentedString).isPresent()
                ? Dna.build(commentedString.getComment(), commentedString.getString())
                : Dna.build();
    }
}

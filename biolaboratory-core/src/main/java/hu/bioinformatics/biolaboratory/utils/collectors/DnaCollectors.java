package hu.bioinformatics.biolaboratory.utils.collectors;

import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.CommentedString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static hu.bioinformatics.biolaboratory.utils.Validation.validateCollection;
import static hu.bioinformatics.biolaboratory.utils.Validation.validateVarargs;

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
        return Arrays.stream(validateVarargs(sequences))
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
        return Arrays.stream(validateVarargs(sequences))
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
        return validateCollection(sequenceCollection).stream()
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
        return validateCollection(sequenceCollection).stream()
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
        return Arrays.stream(validateVarargs(sequences))
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
        validateVarargs(sequences);
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
        return validateCollection(sequenceCollection).stream()
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
        return validateCollection(sequenceCollection).stream()
                .map(mapCommentedStringToDna())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static Function<String, Dna> mapStringToDna() {
        return Dna::build;
    }

    private static Function<CommentedString, Dna> mapCommentedStringToDna() {
        return (commentedString) -> Dna.build(commentedString.getComment(), commentedString.getString());
    }
}

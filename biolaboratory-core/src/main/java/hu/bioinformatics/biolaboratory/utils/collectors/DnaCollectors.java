package hu.bioinformatics.biolaboratory.utils.collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.datastructures.CommentedString;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static hu.bioinformatics.biolaboratory.utils.Validation.notNullCollection;
import static hu.bioinformatics.biolaboratory.utils.Validation.notNullVarargs;

/**
 * Transforms different {@link String} {@link Collection}s to target {@link Dna} {@link Collection}.
 * 
 * @author Attila Radi
 *
 */
public class DnaCollectors {

    /**
     * Transforms {@link String}s to a {@link Dna} {@link Set}.
     *
     * @param sequences Strings of DNA sequences.
     * @return A {@link Set} of {@link Dna} about the sequences.
     * @throws IllegalArgumentException If varargs is null or contains null element.
     */
    public static Set<Dna> stringToDnaSet(final String... sequences) {
        return Sets.newHashSet(stringToDnas(sequences));
    }

    /**
     * Transforms {@link CommentedString}s to a {@link Dna} {@link Set}.
     *
     * @param sequences {@link CommentedString}s of DNA sequences.
     * @return A {@link Set} of {@link Dna} about the sequences.
     * @throws IllegalArgumentException If varargs is null or contains null element.
     */
    public static Set<Dna> commentedStringToDnaSet(final CommentedString... sequences) {
        return Sets.newHashSet(commentedStringToDnas(sequences));
    }

    /**
     * Transforms a {@link String} {@link Collection} to a {@link Dna} {@link Set}.
     * 
     * @param sequenceCollection A {@link Collection} of DNA sequences.
     * @return A {@link Set} of {@link Dna} about the sequences.
     * @throws IllegalArgumentException If collection is null or contains null element.
     */
    public static Set<Dna> stringToDnaSet(final Collection<String> sequenceCollection) {
        return Sets.newHashSet(stringToDnas(sequenceCollection));
    }

    /**
     * Transforms a {@link CommentedString} {@link Collection} to a {@link Dna} {@link Set}.
     *
     * @param sequenceCollection A {@link Collection} of DNA sequences.
     * @return A {@link Set} of {@link Dna} about the sequences.
     * @throws IllegalArgumentException If collection is null or contains null element.
     */
    public static Set<Dna> commentedStringToDnaSet(final Collection<CommentedString> sequenceCollection) {
        return Sets.newHashSet(commentedStringToDnas(sequenceCollection));
    }

    /**
     * Transforms {@link String}s to a {@link Dna} {@link List}.
     *
     * @param sequences Strings of DNA sequences.
     * @return A {@link List} of {@link Dna} about the sequences.
     * @throws IllegalArgumentException If varargs is null or contains null element.
     */
    public static List<Dna> stringToDnaList(final String... sequences) {
        return Lists.newArrayList(stringToDnas(sequences));
    }

    /**
     * Transforms {@link CommentedString}s to a {@link Dna} {@link List}.
     *
     * @param sequences {@link CommentedString}s of DNA sequences.
     * @return A {@link List} of {@link Dna} about the sequences.
     * @throws IllegalArgumentException If varargs is null or contains null element.
     */
    public static List<Dna> commentedStringToDnaList(final CommentedString... sequences) {
        return Lists.newArrayList(commentedStringToDnas(sequences));
    }

    /**
     * Transforms a {@link String} {@link Collection} to a {@link Dna} {@link List}.
     * 
     * @param sequenceCollection A {@link Collection} of DNA sequences.
     * @return A {@link List} of {@link Dna} about the sequences.
     * @throws IllegalArgumentException If collection is null or contains null element.
     */
    public static List<Dna> stringToDnaList(final Collection<String> sequenceCollection) {
        return Lists.newArrayList(stringToDnas(sequenceCollection));
    }

    /**
     * Transforms a {@link CommentedString} {@link Collection} to a {@link Dna} {@link List}.
     *
     * @param sequenceCollection A {@link Collection} of DNA sequences.
     * @return A {@link List} of {@link Dna} about the sequences.
     * @throws IllegalArgumentException If collection is null or contains null element.
     */
    public static List<Dna> commentedStringToDnaList(final Collection<CommentedString> sequenceCollection) {
        return Lists.newArrayList(commentedStringToDnas(sequenceCollection));
    }

    /**
     * Transforms {@link String}s to a {@link Dna} array.
     *
     * @param sequences Strings of DNA sequences.
     * @return An array of {@link Dna} about the sequences.
     * @throws IllegalArgumentException If varargs is null or contains null element.
     */
    public static Dna[] stringToDnas(final String... sequences) {
        return innerStringToDna(Lists.newArrayList(notNullVarargs(sequences)));
    }

    /**
     * Transforms {@link CommentedString}s to a {@link Dna} array.
     *
     * @param sequences {@link CommentedString}s of DNA sequences.
     * @return A n array of {@link Dna} about the sequences.
     * @throws IllegalArgumentException If varargs is null or contains null element.
     */
    public static Dna[] commentedStringToDnas(final CommentedString... sequences) {
        return innerCommentedStringToDna(Lists.newArrayList(notNullVarargs(sequences)));
    }

    /**
     * Transforms a {@link String} {@link Collection} to a {@link Dna} array.
     *
     * @param sequenceCollection A {@link Collection} of DNA sequences.
     * @return An array of {@link Dna} about the sequences.
     * @throws IllegalArgumentException If collection is null or contains null element.
     */
    public static Dna[] stringToDnas(final Collection<String> sequenceCollection) {
        return innerStringToDna(notNullCollection(sequenceCollection));
    }

    /**
     * Transforms a {@link CommentedString} {@link Collection} to a {@link Dna} array.
     *
     * @param sequenceCollection A {@link Collection} of DNA sequences.
     * @return An array of {@link Dna} about the sequences.
     * @throws IllegalArgumentException If collection is null or contains null element.
     */
    public static Dna[] commentedStringToDnas(final Collection<CommentedString> sequenceCollection) {
        return innerCommentedStringToDna(notNullCollection(sequenceCollection));
    }

    private static Dna[] innerStringToDna(final Collection<String> sequenceCollection) {
        return sequenceCollection.stream()
                .map(mapStringToDna())
                .toArray(Dna[]::new);
    }

    private static Dna[] innerCommentedStringToDna(final Collection<CommentedString> sequenceCollection) {
        return sequenceCollection.stream()
                .map(mapCommentedStringToDna())
                .toArray(Dna[]::new);
    }

    private static Function<String, Dna> mapStringToDna() {
        return Dna::build;
    }

    private static Function<CommentedString, Dna> mapCommentedStringToDna() {
        return (commentedString) -> Dna.build(commentedString.getComment(), commentedString.getString());
    }
}

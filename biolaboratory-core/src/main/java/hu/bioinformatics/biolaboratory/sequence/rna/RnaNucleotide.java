package hu.bioinformatics.biolaboratory.sequence.rna;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import hu.bioinformatics.biolaboratory.sequence.SequenceElement;
import hu.bioinformatics.biolaboratory.utils.ArgumentValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotBlankString;

/**
 * Contains the nucleotide information and data which are the building pieces of a RNA.
 *
 * @author Attila Radi
 *
 */
public enum RnaNucleotide implements SequenceElement {
    ADENINE("adenine", 'A'),
    CYTOSINE("cytosine", 'C'),
    GUANINE("guanine", 'G'),
    URACIL("uracil", 'U');

    /**
     * Set of all {@link RnaNucleotide}s.
     */
    public static final Set<RnaNucleotide> NUCLEOTIDE_SET = Sets.newHashSet(values());

    private static final Map<Character, RnaNucleotide> CHARACTER_NUCLEOTIDE_LOOKUP =
            new HashMap<>(ImmutableMap.of(
                    ADENINE.getLetter(), ADENINE,
                    CYTOSINE.getLetter(), CYTOSINE,
                    GUANINE.getLetter(), GUANINE,
                    URACIL.getLetter(), URACIL
            ));

    /**
     * Find RNA nucleotide about its nucleotide letter in {@link String} format.
     *
     * @param rnaNucleotideLetter The RNA nucleotide letter.
     * @return The corresponding RNA nucleotide.
     * @throws IllegalArgumentException If rnaNucleotideLetter is not 1 character after trim.
     */
    public static RnaNucleotide findRnaNucleotide(final String rnaNucleotideLetter) {
        checkNotBlankString("RNA nucleotide letter", rnaNucleotideLetter);
        String normalizedRnaNucleotideLetter = rnaNucleotideLetter.trim();
        ArgumentValidator.checkEqualNumberTo("Letter", normalizedRnaNucleotideLetter.length(), 1);
        return findRnaNucleotide(normalizedRnaNucleotideLetter.charAt(0));
    }

    /**
     * Find the nucleotide about its nucleotide letter.
     *
     * @param nucleotideLetter The nucleotide letter.
     * @return The corresponding nucleotide.
     * @throws IllegalArgumentException If nucleotideLetter is not part of {@link RnaNucleotide}s.
     */
    public static RnaNucleotide findRnaNucleotide(final char nucleotideLetter) {
        RnaNucleotide rnaNucleotide = CHARACTER_NUCLEOTIDE_LOOKUP.get(Character.toUpperCase(nucleotideLetter));
        checkArgument(rnaNucleotide != null, "\"" + nucleotideLetter + "\" is not a RNA nucleotide letter");
        return rnaNucleotide;
    }

    private String name;
    private char letter;

    RnaNucleotide(String name, char letter) {
        this.name = name;
        this.letter = letter;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public char getLetter() {
        return letter;
    }

    @Override
    public String toString() {
        return getName();
    }
}

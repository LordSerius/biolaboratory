package hu.bioinformatics.biolaboratory.sequence.dna;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import hu.bioinformatics.biolaboratory.sequence.SequenceElement;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * Contains the nucleotide information and data which are the building pieces of a DNA.
 *
 * @author Attila Radi
 *
 */
public enum DnaNucleotide implements SequenceElement {
    ADENINE("adenine", 'A'),
    CYTOSINE("cytosine", 'C'),
    GUANINE("guanine", 'G'),
    THYMINE("thymine", 'T');

    /**
     * Set of all {@link DnaNucleotide}s.
     */
    public static final Set<DnaNucleotide> NUCLEOTIDE_SET = Sets.newHashSet(values());

    /**
     * {@link DnaNucleotide} - {@link DnaNucleotide} {@link Map} about the complements.
     */
    public static final Map<DnaNucleotide, DnaNucleotide> NUCLEOTIDE_COMPLEMENTS =
            Maps.newHashMap(ImmutableMap.of(
                    ADENINE, THYMINE,
                    GUANINE, CYTOSINE,
                    THYMINE, ADENINE,
                    CYTOSINE, GUANINE
            ));

    private static final Map<Character, DnaNucleotide> CHARACTER_NUCLEOTIDE_LOOKUP =
            Maps.newHashMap(ImmutableMap.of(
                    ADENINE.getLetter(), ADENINE,
                    CYTOSINE.getLetter(), CYTOSINE,
                    GUANINE.getLetter(), GUANINE,
                    THYMINE.getLetter(), THYMINE
            ));

    /**
     * Find the nucleotide complement about its {@link String} nucleotide letter.
     *
     * @param nucleotideLetter The nucleotide letter in {@link String} format.
     * @return The corresponding nucleotide.
     * @throws IllegalArgumentException If nucleotideLetter is not part of {@link DnaNucleotide}s.
     */
    public static DnaNucleotide findDnaNucleotideComplement(final String nucleotideLetter) {
        return NUCLEOTIDE_COMPLEMENTS.get(findDnaNucleotide(nucleotideLetter));
    }

    /**
     * Find the nucleotide complement about its nucleotide letter.
     *
     * @param nucleotideLetter The nucleotide letter.
     * @return The corresponding nucleotide.
     * @throws IllegalArgumentException If nucleotideLetter is not part of {@link DnaNucleotide}s.
     */
    public static DnaNucleotide findDnaNucleotideComplement(final char nucleotideLetter) {
        return NUCLEOTIDE_COMPLEMENTS.get(findDnaNucleotide(nucleotideLetter));
    }

    /**
     * Find DNA nucleotide about its nucleotide letter in {@link String} format.
     *
     * @param dnaNucleotideLetter The DNA nucleotide letter.
     * @return The corresponding DNA nucleotide.
     * @throws IllegalArgumentException If dnaNucleotideLetter is not 1 character after trim.
     */
    public static DnaNucleotide findDnaNucleotide(final String dnaNucleotideLetter) {
        Preconditions.checkArgument(StringUtils.isNotBlank(dnaNucleotideLetter), "DNA nucleotide letter should not be empty");
        String normalizedDnaNucleotideLetter = dnaNucleotideLetter.trim();
        Preconditions.checkArgument(normalizedDnaNucleotideLetter.length() == 1, "This is not a letter");
        return findDnaNucleotide(normalizedDnaNucleotideLetter.charAt(0));
    }

    /**
     * Find the nucleotide about its nucleotide letter.
     *
     * @param nucleotideLetter The nucleotide letter.
     * @return The corresponding nucleotide.
     * @throws IllegalArgumentException If nucleotideLetter is not part of {@link DnaNucleotide}s.
     */
    public static DnaNucleotide findDnaNucleotide(final char nucleotideLetter) {
        DnaNucleotide dnaNucleotide = CHARACTER_NUCLEOTIDE_LOOKUP.get(Character.toUpperCase(nucleotideLetter));
        Preconditions.checkArgument(dnaNucleotide != null, "\"" + nucleotideLetter + "\" is not a DNA nucleotide letter");
        return dnaNucleotide;
    }

    private String name;
    private char letter;

    DnaNucleotide(String name, char letter) {
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

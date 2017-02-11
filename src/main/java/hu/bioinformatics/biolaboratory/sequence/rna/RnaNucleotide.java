package hu.bioinformatics.biolaboratory.sequence.rna;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import hu.bioinformatics.biolaboratory.sequence.SequenceElement;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Set;

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

    public static final Set<RnaNucleotide> NUCLEOTIDE_SET = Sets.newHashSet(values());

    private static final Map<Character, RnaNucleotide> CHARACTER_NUCLEOTIDE_LOOKUP =
            Maps.newHashMap(ImmutableMap.of(
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
     */
    public static RnaNucleotide findRnaNucleotide(final String rnaNucleotideLetter) {
        Preconditions.checkArgument(StringUtils.isNotBlank(rnaNucleotideLetter), "RNA nucleotide letter should not be empty");
        Preconditions.checkArgument(rnaNucleotideLetter.length() == 1, "This is not a letter");
        return findRnaNucleotide(rnaNucleotideLetter.charAt(0));
    }

    /**
     * Find the nucleotide about its nucleotide letter.
     *
     * @param nucleotideLetter The nucleotide letter.
     * @return The corresponding nucleotide.
     */
    public static RnaNucleotide findRnaNucleotide(final char nucleotideLetter) {
        RnaNucleotide rnaNucleotide = CHARACTER_NUCLEOTIDE_LOOKUP.get(Character.toUpperCase(nucleotideLetter));
        Preconditions.checkArgument(rnaNucleotide != null, "\"" + nucleotideLetter + "\" is not a RNA nucleotide letter");
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

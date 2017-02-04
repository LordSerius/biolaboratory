package hu.bioinformatics.biolaboratory.sequence.rna;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import hu.bioinformatics.biolaboratory.sequence.SequenceElement;

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
     * Find the nucleotide about its nucleotide letter.
     *
     * @param nucleotideLetter The nucleotide letter.
     * @return The corresponding nucleotide.
     */
    public static RnaNucleotide findDnaNucleotide(final char nucleotideLetter) {
        RnaNucleotide dnaNucleotide = CHARACTER_NUCLEOTIDE_LOOKUP.get(Character.toUpperCase(nucleotideLetter));
        Preconditions.checkArgument(dnaNucleotide != null, "\"" + nucleotideLetter + "\" is not a RNA nucleotide letter");
        return dnaNucleotide;
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

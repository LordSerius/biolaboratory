package hu.bioinformatics.biolaboratory.sequence.protein;

import com.google.common.collect.Sets;
import hu.bioinformatics.biolaboratory.sequence.SequenceElement;
import hu.bioinformatics.biolaboratory.utils.ArgumentValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotBlankString;

/**
 * Contains the nucleotide information and data which are the building pieces of a protein.
 *
 * @author Attila Radi
 */
public enum AminoAcid implements SequenceElement {
    ARGININE("arginine", "Arg", 'R', 156),
    HISTIDINE("histidine", "His", 'H', 137),
    LYSINE("lysine", "Lys", 'K', 128),
    ASPARTIC_ACID("aspartic acid", "Asp", 'D', 115),
    GLUTAMIC_ACID("glutamic acid", "Glu", 'E', 129),
    SERINE("serine", "Ser", 'S', 87),
    THREONINE("threonine", "Thr", 'T', 101),
    ASPARAGINE("asparagine", "Asn", 'N', 114),
    GLUTAMINE("glutamine", "Gln", 'Q', 128),
    CYSTEINE("cysteine", "Cys", 'C', 103),
    GLYCINE("glycine", "Gly", 'G', 57),
    PROLINE("proline", "Pro", 'P', 97),
    ALANINE("alanine", "Ala", 'A', 71),
    ISOLEUCINE("isoleucine", "Ile", 'I', 113),
    LEUCINE("leucine", "Leu", 'L', 113),
    METHIONINE("methionine", "Met", 'M', 131),
    PHENYLALANINE("phenylalanine", "Phe", 'F', 147),
    TRYPTOPHAN("tryptophan", "Trp", 'W', 186),
    TYROSIN("tyrosin", "Tyr", 'Y', 163),
    VALINE("valine", "Val", 'V', 99);

    private static final Map<Character, AminoAcid> CHARACTER_AMINO_ACD_LOOKUP = initializeLookupTable();

    /**
     * Set of all {@link AminoAcid}s.
     */
    public static final Set<AminoAcid> AMINO_ACID_SET = Sets.newHashSet(values());

    private static Map<Character, AminoAcid> initializeLookupTable() {
        Object[][] rawMapElements = new Object[][] {
                { ARGININE.getLetter(), ARGININE },
                { HISTIDINE.getLetter(), HISTIDINE },
                { LYSINE.getLetter(), LYSINE },
                { ASPARTIC_ACID.getLetter(), ASPARTIC_ACID },
                { GLUTAMIC_ACID.getLetter(), GLUTAMIC_ACID },
                { SERINE.getLetter(), SERINE },
                { THREONINE.getLetter(), THREONINE },
                { ASPARAGINE.getLetter(), ASPARAGINE },
                { GLUTAMINE.getLetter(), GLUTAMINE },
                { CYSTEINE.getLetter(), CYSTEINE },
                { GLYCINE.getLetter(), GLYCINE },
                { PROLINE.getLetter(), PROLINE },
                { ALANINE.getLetter(), ALANINE },
                { ISOLEUCINE.getLetter(), ISOLEUCINE },
                { LEUCINE.getLetter(), LEUCINE },
                { METHIONINE.getLetter(), METHIONINE },
                { PHENYLALANINE.getLetter(), PHENYLALANINE },
                { TRYPTOPHAN.getLetter(), TRYPTOPHAN },
                { TYROSIN.getLetter(), TYROSIN },
                { VALINE.getLetter(), VALINE }
        };

        Map<Character, AminoAcid> lookupTable = new HashMap<>();
        for (Object[] row : rawMapElements) {
            lookupTable.put((Character) row[0], (AminoAcid) row[1]);
        }
        return lookupTable;
    }

    /**
     * Find amino acid about its amino acid letter in {@link String} format.
     *
     * @param aminoAcidLetter The amino acid letter.
     * @return The corresponding amino acid.
     * @throws IllegalArgumentException If aminoAcidLetter is not 1 character after trim.
     */
    public static AminoAcid findAminoAcid(final String aminoAcidLetter) {
        checkNotBlankString("Amino acid letter", aminoAcidLetter);
        String normalizedAminoAcidLetter = aminoAcidLetter.trim();
        ArgumentValidator.checkEqualNumberTo("Letter", normalizedAminoAcidLetter.length(), 1);
        return findAminoAcid(normalizedAminoAcidLetter.charAt(0));
    }

    /**
     * Find the amino acid about its amino acid letter.
     *
     * @param aminoAcidLetter The amino acid letter.
     * @return The corresponding amino acid.
     * @throws IllegalArgumentException If aminoAcidLetter is not part of {@link AminoAcid}s.
     */
    public static AminoAcid findAminoAcid(final char aminoAcidLetter) {
        AminoAcid aminoAcid = CHARACTER_AMINO_ACD_LOOKUP.get(Character.toUpperCase(aminoAcidLetter));
        checkArgument(aminoAcid != null, "\"" + aminoAcidLetter + "\" is not a amino acid letter");
        return aminoAcid;
    }

    private String name;
    private String abbreviation;
    private char letter;
    private int mass;

    AminoAcid(String name, String abbreviation, char letter, int mass) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.letter = letter;
        this.mass = mass;
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public int getMass() {
        return mass;
    }
}

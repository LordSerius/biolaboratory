package hu.bioinformatics.biolaboratory.sequence.protein;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.sequence.BiologicalSequence;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Represents a single protein about the amino acid sequence.
 *
 * @author Attila Radi
 */
public class Protein extends BiologicalSequence<Protein, AminoAcid> {
    private static final Pattern sequenceValidator = Pattern.compile(initializeSequenceValidator());

    private static String initializeSequenceValidator() {
        AminoAcid[] aminoAcidList = AminoAcid.values();
        String regExp = "[";
        for (AminoAcid aminoAcid : aminoAcidList) {
            regExp += aminoAcid.getLetter();
        }
        return regExp + "]+";
    }

    /**
     * Build a {@link Protein} from the given sequence. The sequence can be only the letters of the
     * amino acids, case insensitive, and can contains blank characters at the beginning and the
     * end of the input.
     *
     * @param sequence The input nucleotide sequence.
     * @return A new {@link Protein} object which contains the nucleotide sequence in uppercase.
     */
    public static Protein build(final String sequence) {
        return new Protein(Protein.validateSequence(sequence));
    }

    private static String validateSequence(final String sequence) {
        Preconditions.checkArgument(StringUtils.isNotBlank(sequence), "Protein sequence cannot be blank");
        String uppercaseSequence = sequence.trim().toUpperCase();
        Preconditions.checkArgument(sequenceValidator.matcher(uppercaseSequence).matches(), "Protein should contains only the letters of nucleotides");
        return uppercaseSequence;
    }

    private Protein(String sequence) {
        super(sequence);
    }

    @Override
    protected Protein construct(String sequence) {
        return new Protein(sequence);
    }

    @Override
    protected AminoAcid[] getElementArray() {
        return AminoAcid.values();
    }

    @Override
    protected Set<AminoAcid> getElementSet() {
        return AminoAcid.AMINO_ACID_SET;
    }

    @Override
    protected AminoAcid findSequenceElement(final char sequenceElementLetter) {
        return AminoAcid.findAminoAcid(sequenceElementLetter);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) return false;
        Protein rightHand = (Protein) obj;
        return sequenceLength == rightHand.sequenceLength && sequence.equals(rightHand.sequence);
    }

    @Override
    protected String getName() {
        return "Protein";
    }
}

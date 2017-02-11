package hu.bioinformatics.biolaboratory.sequence.protein;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.sequence.BiologicalSequence;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.Validate.noNullElements;

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

    /**
     * Build an {@link Protein} from the given {@link AminoAcid}s. The amino acids should not contain null value.
     *
     * @param aminoAcids The input amino acids.
     * @return A new {@link Protein} object which stands from the amino acids.
     */
    public static Protein build(final AminoAcid... aminoAcids) {
        return new Protein(validateElements(aminoAcids));
    }

    /**
     * Build an {@link Protein} from the given {@link AminoAcid} {@link List}. The list should not contain null element.
     *
     * @param aminoAcidList The input nucleotides in a {@link List}.
     * @return A new {@link Protein} object which stand from the nucleotides
     */
    public static Protein build(final List<AminoAcid> aminoAcidList) {
        return new Protein(validateElementList(aminoAcidList));
    }

    private static String validateSequence(final String sequence) {
        Preconditions.checkArgument(StringUtils.isNotBlank(sequence), "Protein sequence cannot be blank");
        String uppercaseSequence = sequence.trim().toUpperCase();
        Preconditions.checkArgument(sequenceValidator.matcher(uppercaseSequence).matches(), "Protein should contains only the letters of nucleotides");
        return uppercaseSequence;
    }

    private static AminoAcid[] validateElements(final AminoAcid[] elements) {
        Preconditions.checkArgument(elements != null && elements.length > 0, "Protein elements should not be empty");
        return noNullElements(elements, "Protein elements should not contain null element");
    }

    private static List<AminoAcid> validateElementList(final List<AminoAcid> elementList) {
        Preconditions.checkArgument(elementList != null && !elementList.isEmpty(), "Protein element list should not be empty");
        return noNullElements(elementList, "Protein element list should not contain null element");
    }

    private Protein(String sequence) {
        super(sequence);
    }

    private Protein(final AminoAcid... aminoAcids) {
        super(aminoAcids);
    }

    private Protein(final List<AminoAcid> aminoAcidList) {
        super(aminoAcidList);
    }

    @Override
    protected Protein construct(String sequence) {
        return new Protein(sequence);
    }

    @Override
    protected AminoAcid[] createEmptyElementArray() {
        return new AminoAcid[sequenceLength];
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

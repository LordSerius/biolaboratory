package hu.bioinformatics.biolaboratory.sequence.protein;

import hu.bioinformatics.biolaboratory.sequence.BiologicalSequence;
import com.google.common.base.Preconditions;
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
     * @param sequence The input amino acid sequence.
     * @return A new {@link Protein} object which contains the amino acid sequence in uppercase.
     */
    public static Protein build(final String sequence) {
        return new Protein(Protein.validateSequence(sequence));
    }

    /**
     * Build a {@link Protein} from the given sequence. The sequence can be only the letters of the
     * amino acids, case insensitive, and can contains blank characters at the beginning and the
     * end of the input.
     *
     * @param name The name of the sequence.
     * @param sequence The input amino acid sequence.
     * @return A new {@link Protein} object which contains the amino acid sequence in uppercase.
     */
    public static Protein build(final String name, final String sequence) {
        return new Protein(validateName(name), validateSequence(sequence));
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
     * Build an {@link Protein} from the given {@link AminoAcid}s. The amino acids should not contain null value.
     *
     * @param name The name of the sequence.
     * @param aminoAcids The input amino acids.
     * @return A new {@link Protein} object which stands from the amino acids.
     */
    public static Protein build(final String name, final AminoAcid... aminoAcids) {
        return new Protein(validateName(name), validateElements(aminoAcids));
    }

    /**
     * Build an {@link Protein} from the given {@link AminoAcid} {@link List}. The list should not contain null element.
     *
     * @param aminoAcidList The input amino acids in a {@link List}.
     * @return A new {@link Protein} object which stand from the amino acids.
     */
    public static Protein build(final List<AminoAcid> aminoAcidList) {
        return new Protein(validateElementList(aminoAcidList));
    }

    /**
     * Build an {@link Protein} from the given {@link AminoAcid} {@link List}. The list should not contain null element.
     *
     * @param name The name of the sequence.
     * @param aminoAcidList The input amino acids in a {@link List}.
     * @return A new {@link Protein} object which stand from the amino acids.
     */
    public static Protein build(final String name, final List<AminoAcid> aminoAcidList) {
        return new Protein(validateName(name), validateElementList(aminoAcidList));
    }

    private static String validateName(final String name) {
        Preconditions.checkArgument(name != null, "Name should not be null");
        return name.trim();
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

    private Protein(final String sequence) {
        super(sequence);
    }

    private Protein(final String name, final String sequence) {
        super(name, sequence);
    }

    private Protein(final AminoAcid... aminoAcids) {
        super(aminoAcids);
    }

    private Protein(final String name, AminoAcid... aminoAcids) {
        super(name, aminoAcids);
    }

    private Protein(final List<AminoAcid> aminoAcidList) {
        super(aminoAcidList);
    }

    private Protein(final String name, final List<AminoAcid> aminoAcidList) {
        super(name, aminoAcidList);
    }

    @Override
    protected Protein construct(final String name, final String sequence) {
        return new Protein(name, sequence);
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
    protected String getBiologicalSequenceTypeName() {
        return "Protein";
    }
}

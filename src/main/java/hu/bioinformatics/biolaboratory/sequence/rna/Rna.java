package hu.bioinformatics.biolaboratory.sequence.rna;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.sequence.BiologicalSequence;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Represents a single RNA about the genome sequence.
 *
 * @author Attila Radi
 *
 */
public class Rna extends BiologicalSequence<Rna, RnaNucleotide> {
    private static final Pattern sequenceValidator = Pattern.compile(
                                                                "["
                                                                + RnaNucleotide.ADENINE.getLetter()
                                                                + RnaNucleotide.CYTOSINE.getLetter()
                                                                + RnaNucleotide.GUANINE.getLetter()
                                                                + RnaNucleotide.URACIL.getLetter()
                                                                + "]+");

    /**
     * Build a {@link Rna} from the given sequence. The sequence can be only the letters of the
     * nucleotides, case insensitive, and can contains blank characters at the beginning and the
     * end of the input.
     *
     * @param sequence The input nucleotide sequence.
     * @return A new {@link Rna} object which contains the nucleotide sequence in uppercase.
     */
    public static Rna build(final String sequence) {
        return new Rna(Rna.validateSequence(sequence));
    }

    private static String validateSequence(final String sequence) {
        Preconditions.checkArgument(StringUtils.isNotBlank(sequence), "RNA sequence cannot be blank");
        String uppercaseSequence = sequence.trim().toUpperCase();
        Preconditions.checkArgument(sequenceValidator.matcher(uppercaseSequence).matches(), "RNA should contains only the letters of nucleotides");
        return uppercaseSequence;
    }

    private Rna(String sequence) {
        super(sequence);
    }

    @Override
    protected Rna construct(final String sequence) {
        return new Rna(sequence);
    }

    @Override
    protected RnaNucleotide[] getElementArray() {
        return RnaNucleotide.values();
    }

    @Override
    protected Set<RnaNucleotide> getElementSet() {
        return RnaNucleotide.NUCLEOTIDE_SET;
    }

    @Override
    protected RnaNucleotide findSequenceElement(final char sequenceElementLetter) {
        return RnaNucleotide.findDnaNucleotide(sequenceElementLetter);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) return false;
        Rna rightHand = (Rna) obj;
        return sequenceLength == rightHand.sequenceLength && sequence.equals(rightHand.sequence);
    }

    @Override
    protected String getName() {
        return "RNA";
    }
}

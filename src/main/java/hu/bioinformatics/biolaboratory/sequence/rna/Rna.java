package hu.bioinformatics.biolaboratory.sequence.rna;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.sequence.BiologicalSequence;
import hu.bioinformatics.biolaboratory.sequence.protein.AminoAcid;
import hu.bioinformatics.biolaboratory.sequence.protein.Protein;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.Validate.noNullElements;
import static org.apache.commons.lang3.Validate.notEmpty;

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
     * Build an {@link Rna} from the given sequence. The sequence can be only the letters of the
     * nucleotides, case insensitive, and can contains blank characters at the beginning and the
     * end of the input.
     *
     * @param sequence The input nucleotide sequence.
     * @return A new {@link Rna} object which contains the nucleotide sequence in uppercase.
     */
    public static Rna build(final String sequence) {
        return new Rna(validateSequence(sequence));
    }

    /**
     * Build an {@link Rna} from the given {@link RnaNucleotide}s. The nucleotides should not contain null value.
     *
     * @param nucleotides The input nucleotides.
     * @return A new {@link Rna} object which stands from the nucleotides.
     */
    public static Rna build(final RnaNucleotide... nucleotides) {return new Rna(validateElements(nucleotides));
    }

    /**
     * Build an {@link Rna} from the given {@link RnaNucleotide} {@link List}. The list should not contain null element.
     *
     * @param nucleotideList The input nucleotides in a {@link List}.
     * @return A new {@link Rna} object which stand from the nucleotides
     */
    public static Rna build(final List<RnaNucleotide> nucleotideList) {
        return new Rna(validateElementList(nucleotideList));
    }

    private static String validateSequence(final String sequence) {
        Preconditions.checkArgument(StringUtils.isNotBlank(sequence), "RNA sequence cannot be blank");
        String uppercaseSequence = sequence.trim().toUpperCase();
        Preconditions.checkArgument(sequenceValidator.matcher(uppercaseSequence).matches(), "RNA should contains only the letters of nucleotides");
        return uppercaseSequence;
    }

    private static RnaNucleotide[] validateElements(final RnaNucleotide[] elements) {
        Preconditions.checkArgument(elements != null && elements.length > 0, "RNA elements should not be empty");
        return noNullElements(elements, "RNA elements should not contain null element");
    }

    private static List<RnaNucleotide> validateElementList(final List<RnaNucleotide> elementList) {
        Preconditions.checkArgument(elementList != null && !elementList.isEmpty(), "RNA element list should not be empty");
        return noNullElements(elementList, "RNA element list should not contain null element");
    }

    private Rna(String sequence) {
        super(sequence);
    }

    private Rna(final RnaNucleotide... rnaNucleotides) {
        super(rnaNucleotides);
    }

    private Rna(final List<RnaNucleotide> rnaNucleotideList) {
        super(rnaNucleotideList);
    }

    @Override
    protected Rna construct(final String sequence) {
        return new Rna(sequence);
    }

    @Override
    protected RnaNucleotide[] createEmptyElementArray() {
        return new RnaNucleotide[sequenceLength];
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
        return RnaNucleotide.findRnaNucleotide(sequenceElementLetter);
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

    /**
     * Translate the RNA into a single protein if it is possible. If the translation is not possible the method throws
     * a {@link RnaTranslationException}.
     *
     * @return The translated protein.
     * @throws RnaTranslationException If translation is not possible.
     */
    public Protein translate() throws RnaTranslationException {
        validateTranslation();

        int translationSize = sequenceLength - 3;
        AminoAcid[] aminoAcids = new AminoAcid[translationSize / 3];
        int i  = 0;
        int index = 0;
        while (i < translationSize) {
            int j = i + 3;
            aminoAcids[index++] = RnaCodonTable.lookup(cut(i, j)).orElseThrow(() ->
                    new RnaTranslationException("Found STOP codon inside RNA. Use alternate splicing for translation"));
            i = j;
        }

        return Protein.build(aminoAcids);
    }

    private void validateTranslation() throws RnaTranslationException {
        if (sequenceLength < 6) {
            throw new RnaTranslationException("RNA is too sort to translate");
        }
        if (sequenceLength % 3 != 0) {
            throw new RnaTranslationException("RNA cannot divided by 3");
        }
        if (RnaCodonTable.lookup(cut(sequenceLength - 3, sequenceLength)).isPresent()) {
            throw new RnaTranslationException("Last codon is not STOP codon");
        }
    }
}

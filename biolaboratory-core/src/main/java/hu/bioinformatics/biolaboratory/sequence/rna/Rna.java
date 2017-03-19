package hu.bioinformatics.biolaboratory.sequence.rna;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.sequence.BiologicalSequence;
import hu.bioinformatics.biolaboratory.sequence.protein.AminoAcid;
import hu.bioinformatics.biolaboratory.sequence.protein.Protein;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static hu.bioinformatics.biolaboratory.utils.Validation.*;

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

    private Protein translatedProtein = null;

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
     * Build an {@link Rna} from the given sequence. The sequence can be only the letters of the
     * nucleotides, case insensitive, and can contains blank characters at the beginning and the
     * end of the input.
     *
     * @param name The name of the sequence.
     * @param sequence The input nucleotide sequence.
     * @return A new {@link Rna} object which contains the nucleotide sequence in uppercase.
     */
    public static Rna build(final String name, final String sequence) {
        return new Rna(validateName(name), validateSequence(sequence));
    }

    /**
     * Build an {@link Rna} from the given {@link RnaNucleotide}s. The nucleotides should not contain null value.
     *
     * @param nucleotides The input nucleotides.
     * @return A new {@link Rna} object which stands from the nucleotides.
     */
    public static Rna build(final RnaNucleotide... nucleotides) {
        return new Rna(validateNotEmptyVarargs(nucleotides));
    }

    /**
     * Build an {@link Rna} from the given {@link RnaNucleotide}s. The nucleotides should not contain null value.
     *
     * @param name The name of the sequence.
     * @param nucleotides The input nucleotides.
     * @return A new {@link Rna} object which stands from the nucleotides.
     */
    public static Rna build(final String name, final RnaNucleotide... nucleotides) {
        return new Rna(validateName(name), validateVarargs(nucleotides));
    }

    /**
     * Build an {@link Rna} from the given {@link RnaNucleotide} {@link List}. The list should not contain null element.
     *
     * @param nucleotideList The input nucleotides in a {@link List}.
     * @return A new {@link Rna} object which stand from the nucleotides
     */
    public static Rna build(final List<RnaNucleotide> nucleotideList) {
        return new Rna(validateNotEmptyCollection(nucleotideList));
    }

    /**
     * Build an {@link Rna} from the given {@link RnaNucleotide} {@link List}. The list should not contain null element.
     *
     * @param name The name of the sequence.
     * @param nucleotideList The input nucleotides in a {@link List}.
     * @return A new {@link Rna} object which stand from the nucleotides
     */
    public static Rna build(final String name, final List<RnaNucleotide> nucleotideList) {
        return new Rna(validateName(name), validateCollection(nucleotideList));
    }

    private static String validateSequence(final String sequence) {
        String uppercaseSequence = formatSequence(sequence);
        Preconditions.checkArgument(sequenceValidator.matcher(uppercaseSequence).matches(), "RNA should contains only the letters of nucleotides");
        return uppercaseSequence;
    }

    private Rna(final String sequence) {
        super(sequence);
    }

    private Rna(final String name, final String sequence) {
        super(name, sequence);
    }

    private Rna(final RnaNucleotide... rnaNucleotides) {
        super(rnaNucleotides);
    }

    private Rna(final String name, final RnaNucleotide... rnaNucleotides) {
        super(name, rnaNucleotides);
    }

    private Rna(final List<RnaNucleotide> rnaNucleotideList) {
        super(rnaNucleotideList);
    }

    private Rna(final String name, final List<RnaNucleotide> rnaNucleotideList) {
        super(name, rnaNucleotideList);
    }

    @Override
    protected Rna construct(final String name, final String sequence) {
        return new Rna(name, sequence);
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
    protected String getBiologicalSequenceTypeName() {
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
        if (translatedProtein == null) {
            validateTranslation();

            int translationSize = sequenceLength - 3;
            AminoAcid[] aminoAcids = new AminoAcid[translationSize / 3];
            int i = 0;
            int index = 0;
            while (i < translationSize) {
                int j = i + 3;
                aminoAcids[index++] = RnaCodonTable.lookup(cut(i, j)).orElseThrow(() ->
                        new RnaTranslationException("Found STOP codon inside RNA. Use alternate splicing for translation"));
                i = j;
            }
            translatedProtein = Protein.build(aminoAcids);
        }

        return translatedProtein;
    }

    private void validateTranslation() throws RnaTranslationException {
        if (sequenceLength < 6) {
            throw new RnaTranslationException("RNA is too sort to translate");
        }
        if (sequenceLength % 3 != 0) {
            throw new RnaTranslationException("RNA cannot divided by 3");
        }
        if (!cut(0, 3).equals(RnaCodonTable.START_CODON)) {
            throw new RnaTranslationException("RNA should start with AUG sequence");
        }
        if (RnaCodonTable.lookup(cut(sequenceLength - 3, sequenceLength)).isPresent()) {
            throw new RnaTranslationException("Last codon is not STOP codon");
        }
    }
}

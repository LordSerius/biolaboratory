package hu.bioinformatics.biolaboratory.sequence.dna;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.UncheckedExecutionException;
import hu.bioinformatics.biolaboratory.sequence.BiologicalSequence;
import hu.bioinformatics.biolaboratory.sequence.rna.Rna;
import hu.bioinformatics.biolaboratory.sequence.rna.RnaNucleotide;
import hu.bioinformatics.biolaboratory.utils.datastructures.OccurrenceMap;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

/**
 * Represents a single DNA about the genome sequence in 5' -> 3' order.
 *
 * @author Attila Radi
 *
 */
public class Dna extends BiologicalSequence<Dna, DnaNucleotide> {

    private static final Pattern sequenceValidator = Pattern.compile(
                                                                "["
                                                                + DnaNucleotide.ADENINE.getLetter()
                                                                + DnaNucleotide.CYTOSINE.getLetter()
                                                                + DnaNucleotide.GUANINE.getLetter()
                                                                + DnaNucleotide.THYMINE.getLetter()
                                                                + "]+");

    private Dna reverseThread = null;
    private Dna reverseComplementThread = null;
    private List<Integer> skew = null;
    private Rna transcriptRna = null;

    /**
     * Build a {@link Dna} from the given sequence. The sequence can be only the letters of the
     * nucleotides, case insensitive, and can contains blank characters at the beginning and the
     * end of the input.
     *
     * @param sequence The input nucleotide sequence.
     * @return A new {@link Dna} object which contains the nucleotide sequence in uppercase.
     */
    public static Dna build(final String sequence) {
        return new Dna(Dna.validateSequence(sequence));
    }

    private static String validateSequence(final String sequence) {
        Preconditions.checkArgument(StringUtils.isNotBlank(sequence), "DNA sequence cannot be blank");
        String uppercaseSequence = sequence.trim().toUpperCase();
        Preconditions.checkArgument(sequenceValidator.matcher(uppercaseSequence).matches(), "DNA should contains only the letters of nucleotides");
        return uppercaseSequence;
    }

    private Dna(String sequence) {
        super(sequence);
    }

    @Override
    protected Dna construct(String sequence) {
        return new Dna(sequence);
    }

    @Override
    protected DnaNucleotide[] getElementArray() {
        return DnaNucleotide.values();
    }

    @Override
    protected Set<DnaNucleotide> getElementSet() {
        return DnaNucleotide.NUCLEOTIDE_SET;
    }

    @Override
    protected DnaNucleotide findSequenceElement(final char sequenceElementLetter) {
        return DnaNucleotide.findDnaNucleotide(sequenceElementLetter);
    }

    @Override
    protected String getName() {
        return "DNA";
    }

    /**
     * Get the number adenine nucleotides inside the sequence.
     *
     * @return The number of adenine nucleotides.
     */
    public int getAdenineNumber() {
        return collectSequenceElementOccurrences().getOccurrence(DnaNucleotide.ADENINE);
    }

    /**
     * Get the number cytosine nucleotides inside the sequence.
     *
     * @return The number of cytosine nucleotides.
     */
    public int getCytosineNumber() {
        return collectSequenceElementOccurrences().getOccurrence(DnaNucleotide.CYTOSINE);
    }

    /**
     * Get the number guanine nucleotides inside the sequence.
     *
     * @return The number of guanine nucleotides.
     */
    public int getGuanineNumber() {
        return collectSequenceElementOccurrences().getOccurrence(DnaNucleotide.GUANINE);
    }

    /**
     * Get the number thymine nucleotides inside the sequence.
     *
     * @return The number of thymine nucleotides.
     */
    public int getThymineNumber() {
        return collectSequenceElementOccurrences().getOccurrence(DnaNucleotide.THYMINE);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != Dna.class) return false;
        Dna rightHand = (Dna) obj;
        boolean returnValue = sequenceLength == rightHand.sequenceLength
                && sequence.equals(rightHand.sequence);
        return returnValue;
    }

    @Override
    public int hashCode() {
        return sequence.hashCode();
    }

    /**
     * Get all <i>k</i> long straight or reverse complement sequences which has at most
     * <i>d</i> different nucleotides and are greater or equals than <i>t</i> in the DNA
     * sequence.
     *
     * @param k The findable <i>k</i> long sequences.
     * @param d The maximum permitted different nucleotides.
     * @param t The threshold of the occurrences of the <i>k</i> long sequences in the DNA.
     * @return All <i>k</i> long straight or reverse complement sequences at most <i>d</i>
     *             mismatches which occurrences are greater or equals than <i>t</i>.
     */
    public Set<Dna> findFrequentMismatchPatterns(final int k, final int d, final int t) {
        return getPatternOccurrenceMap(k, d).filterGreaterOrEqualsOccurrences(t);
    }

    private OccurrenceMap<Dna> getPatternOccurrenceMap(final int k, final int d) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<OccurrenceMap<Dna>> straightOccurrenceMap = executor.submit(() -> getMismatchOccurrenceMap(k, d));
        Future<OccurrenceMap<Dna>> reverseOccurrenceMap = executor.submit(() -> getReverseComplementMismatchOccurrenceMap(k, d));
        executor.shutdown();
        try {
            return straightOccurrenceMap.get().merge(reverseOccurrenceMap.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof IllegalArgumentException)
                throw (IllegalArgumentException) e.getCause();
            throw new UncheckedExecutionException(e);
        }
    }

    /**
     * The algorithm works similar than getMismatchNumber() but in the reverse thread and uses
     * different rules:
     * <p>
     * The work of the algorithm:
     * <ol>
     *     <li>Generates the maximum <i>d</i> mismatches for the reverse complement of the first
     *     <i>k</i> long subsequence in a mismatch map.</li>
     *     <li>A sliding window separates the mismatch set's elements according to the last
     *     disappearing character is equals with the <i>i</i>. element in the sequence.
     *         <ul>
     *             <li>If the last element of the pattern equals with the complement of <i>i</i>.
     *             element of the sequence, the Hamming-distance doesn't change, the new
     *             pattern will appear in the next run (new mismatch set).</li>
     *             <li>If the last element of the pattern is not equal than the complement of the
     *             <i>i</i>. element of the sequence, the Hamming-distance decreases, so the
     *             algorithm will have to generate mismatch instances from the new pattern
     *             (generator set).</li>
     *         </ul>
     *     The new element will be the complement of <i>i</i>. element of the sequence and the
     *     pattern excluded with the last character.
     *     </li>
     *     <li>If <i>d</i> greater than 0 add the reverse complement subsequence from i + 1. to k + i.
     *     to the generator set.</li>
     *     <li>Generates the next mismatch pattern from the generator set. The algorithm
     *     iterates over every generator element and substitute every nucleobas at the first
     *     position in the pattern.</li>
     *     <li>Add these 4 element to the new mismatch set.</li>
     *     <li>Continue at 4. point until the last generator element.</li>
     *     <li>The new mismatch set will be the next mismatch map in the next run.</li>
     *    <li>Continue from 2. point until the sliding widow reaches the end of the sequence.</li>
     * </ol>
     *
     * @see Dna#getMismatchOccurrenceMap(int, int)
     * @param k The <i>k</i> long subsequences.
     * @param d The maximum permitted different nucleotides.
     * @return The occurrence map of the reverse complement mismatches.
     */
    private OccurrenceMap<Dna> getReverseComplementMismatchOccurrenceMap(final int k, final int d) {
        Preconditions.checkArgument(k > 0, "Findable subsequence length (k) should be greater than 0");
        Preconditions.checkArgument(k <= sequenceLength, "Findable subsequence length (k) should be smaller or equals to the sequence length");

        OccurrenceMap<Dna> occurrenceMap = OccurrenceMap.build();
        int lengthDiff = sequenceLength - k;

        Set<Dna> mismatchSet = new Dna(sequence.substring(0, k))
                .getReverseComplementThread()
                .generateMismatches(d);

        for(int i = 0; i < lengthDiff; i++) {
            Set<Dna> nextMismatchesSet = Sets.newHashSet();
            Set<Dna> generatedMismatchesSet = Sets.newHashSet();

            for (Dna mismatchPattern : mismatchSet) {
                occurrenceMap.increase(mismatchPattern);
                Dna nextMismatchPattern = new Dna(DnaNucleotide.findDnaNucleotideComplement(sequence.charAt(i + k)).getLetter()
                        + mismatchPattern.sequence.substring(0, k - 1));
                if (DnaNucleotide.findDnaNucleotideComplement(sequence.charAt(i)).getLetter() == mismatchPattern.sequence.charAt(k - 1))
                    nextMismatchesSet.add(nextMismatchPattern);
                else
                    generatedMismatchesSet.add(nextMismatchPattern);
            }

            if (d > 0) {
                generatedMismatchesSet.add(new Dna(sequence.substring(i + 1, i + k) + sequence.charAt(i + k))
                        .getReverseComplementThread());
            }

            for (Dna generatedMismatchPattern : generatedMismatchesSet) {
                char[] charArray = generatedMismatchPattern.sequence.toCharArray();
                DnaNucleotide[] nucleotideArray = DnaNucleotide.values();
                for (DnaNucleotide nucleotideLetter : nucleotideArray) {
                    charArray[0] = nucleotideLetter.getLetter();
                    nextMismatchesSet.add(new Dna(new String(charArray)));
                }
            }
            mismatchSet = nextMismatchesSet;
        }
        mismatchSet.forEach(occurrenceMap::increase);
        return occurrenceMap;
    }

    /**
     * Returns with the complement thread of the {@link Dna} (3' -> 5' order).
     *
     * @return The complement sequence in 3' -> 5' order.
     */
    public Dna getComplementThread() {
        return getOrConstructComplementThread().copy();
    }

    private synchronized Dna getOrConstructComplementThread() {
        if (reverseThread == null) {
            reverseThread = new Dna(new String(mapSequenceComplement()));
        }
        return reverseThread;
    }

    /**
     * Returns with the reverse complement thread of the {@link Dna} (5' -> 3' order).
     *
     * @return The reverse complement sequence in 5' -> 3' order.
     */
    public Dna getReverseComplementThread() {
        return getOrConstructReverseComplementThread().copy();
    }

    private synchronized Dna getOrConstructReverseComplementThread() {
        if (reverseComplementThread == null) {
            char[] reverseComplementChars = mapSequenceComplement();
            ArrayUtils.reverse(reverseComplementChars);
            reverseComplementThread = new Dna(new String(reverseComplementChars));
        }
        return reverseComplementThread;
    }

    private char[] mapSequenceComplement() {
        char[] reverseChars = new char[sequenceLength];
        for (int i = 0; i < sequenceLength; i++) {
            reverseChars[i] = DnaNucleotide.findDnaNucleotideComplement(sequence.charAt(i)).getLetter();
        }
        return reverseChars;
    }

    /**
     * Compares the given {@link Dna} with the complement {@link Dna}.
     *
     * @param otherDna The given {@link Dna} against the complement {@link Dna}.
     * @return True if the otherDna equals with the complement {@link Dna}.
     */
    public boolean isComplement(final Dna otherDna) {
        return otherDna.sequenceLength == sequenceLength && getOrConstructComplementThread().equals(otherDna);
    }

    /**
     * Compares the given {@link Dna} with the reverse complement {@link Dna}.
     *
     * @param otherDna The given {@link Dna} against the reverse complement {@link Dna}.
     * @return True if the otherDna equals with the reverse complement {@link Dna}.
     */
    public boolean isReverseComplement(Dna otherDna) {
        return otherDna.sequenceLength == sequenceLength && getOrConstructReverseComplementThread().equals(otherDna);
    }

    /**
     * Get the most frequent <i>k</i> straight or reverse complement occurrences which has
     * at most <i>d</i> different nucleotides in the DNA sequence.
     *
     * @param k The findable <i>k</i> long sequences.
     * @param d The maximum permitted different nucleotides.
     * @return The most frequent <i>k</i> long straight or reverse complement occurrences
     * with at most <i>d</i> mismatches.
     */
    public Set<Dna> findMostFrequentMismatchPatterns(final int k, final int d) {
        return getPatternOccurrenceMap(k, d).filterMostFrequentOccurrences();
    }

    /**
     * Returns the possible starting positions of the <i>ori</i> of the DNA.
     * <p>
     * The theory examines the deaminated cytosine into thymine density. Each guanine in the
     * sequence positions add 1 to the skew value and each cytosine subtract 1 from the skew
     * value. Where the skew is minimal, there is a high chance the <i>ori</i> point is
     * nearby.
     *
     * @return The positions which skew are minimal.
     */
    public synchronized List<Integer> minimumSkew() {
        if (skew == null) {
            skew = createSkew();
        }
        return Lists.newArrayList(skew);
    }

    private List<Integer> createSkew() {
        int guanineCitosyneRatio = 0;
        int minimumGuanineCytosineRatio = guanineCitosyneRatio;
        List<Integer> minimumRatioGuanineCitosyneIndexList = Lists.newLinkedList();
        for (int i = 0; i < sequenceLength; i++) {
            switch (DnaNucleotide.findDnaNucleotide(sequence.charAt(i))) {
                case GUANINE:
                    guanineCitosyneRatio++;
                    break;
                case CYTOSINE:
                    guanineCitosyneRatio--;
                    if (guanineCitosyneRatio < minimumGuanineCytosineRatio) {
                        minimumGuanineCytosineRatio = guanineCitosyneRatio;
                        minimumRatioGuanineCitosyneIndexList.clear();
                    }
                    if (guanineCitosyneRatio == minimumGuanineCytosineRatio) {
                        minimumRatioGuanineCitosyneIndexList.add(i);
                    }
                    break;
                default:
                    if (guanineCitosyneRatio == minimumGuanineCytosineRatio) {
                        minimumRatioGuanineCitosyneIndexList.add(i);
                        break;
                    }
            }
        }
        if (minimumRatioGuanineCitosyneIndexList.isEmpty()) {
            minimumRatioGuanineCitosyneIndexList.add(0);
        }
        return minimumRatioGuanineCitosyneIndexList;
    }

    /**
     * Return the {@link Rna} representation of this DNA sequence and its reverse complement.
     * The first element is always the RNA about the straight thread, and the second element is about the reverse
     * thread.
     *
     * @return The {@link Rna} representation of the straight and reverse thread.
     */
    public Rna[] transcript() {
        return new Rna[] { transcriptStraight(), transcriptReverseComplement() };
    }

    /**
     * Return the {@link Rna} representation of the reverse complement thread.
     *
     * @return The {@link Rna} representation of the reverse complement thread.
     */
    public Rna transcriptReverseComplement() {
        return getOrConstructReverseComplementThread().transcriptStraight();
    }

    /**
     * Return the {@link Rna} representation of the straight thread.
     *
     * @return The {@link Rna} representation of the straight thread.
     */
    public Rna transcriptStraight() {
        if (transcriptRna == null) {
            transcriptRna = Rna.build(sequence.replaceAll(Character.toString(DnaNucleotide.THYMINE.getLetter()),
                    Character.toString(RnaNucleotide.URACIL.getLetter())));
        }
        return transcriptRna;
    }
}

package hu.bioinformatics.biolaboratory.sequence.dna;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import hu.bioinformatics.biolaboratory.utils.Validation;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A collection of {@link Dna}s which provide motif finding methods on them. Every included {@link Dna} should be the
 * same sequence length. The order of each {@link Dna}s inside the {@link DnaArray} does not matter.
 *
 * @author Attila Radi
 */
public class DnaArray {

    private final List<Dna> sampleList;
    private final int sampleNumber;
    private final int samplesLength;
    private final Motifs motifs;

    /**
     * Build a {@link DnaArray} from the given {@link Dna}s.
     *
     * @param dnas The input {@link Dna}s
     * @return A new {@link DnaArray}
     */
    public static DnaArray build(final Dna... dnas) {
        Validation.validateNotEmptyVarargs(dnas);
        return innerBuild(Arrays.asList(dnas));
    }

    /**
     * Build a {@link DnaArray} from the given DNA {@link List}.
     *
     * @param dnaList The assignable elements of the {@link DnaArray}.
     * @return A new {@link DnaArray}.
     */
    public static DnaArray build(final List<Dna> dnaList) {
        Validation.validateNotEmptyCollection(dnaList);
        return innerBuild(dnaList);
    }

    private static DnaArray innerBuild(final List<Dna> dnaList) {
        validateDnaArray(dnaList);
        return new DnaArray(dnaList);
    }

    private static void validateDnaArray(final List<Dna> dnaList) {
        int length = dnaList.get(0).getSequenceLength();
        Preconditions.checkArgument(dnaList.stream()
                .allMatch(dna -> dna.getSequenceLength() == length), "DNA lengths inside DNA array should be the same");
    }

    private DnaArray(final List<Dna> sampleList) {
        this.sampleList = Lists.newArrayList(sampleList);
        this.sampleNumber = sampleList.size();
        this.samplesLength = sampleList.get(0).getSequenceLength();
        this.motifs = Motifs.build(this);
    }

    /**
     * Creates the copy of the {@link DnaArray}
     *
     * @return The copy of the {@link DnaArray}
     */
    public DnaArray copy() {
        return new DnaArray(sampleList);
    }

    /**
     * Getter of the DNA array elements.
     *
     * @return The copy of the DNA array elements.
     */
    public List<Dna> getSampleList() {
        return Lists.newArrayList(sampleList);
    }

    /**
     * Getter of the sample number.
     *
     * @return The sample number.
     */
    public int getSampleNumber() {
        return sampleNumber;
    }

    /**
     * Getter of the motifs
     *
     * @return The motifs.
     */
    public Motifs getMotifs() {
        return motifs;
    }

    /**
     * Getter of the length of sample lengths.
     *
     * @return The length of one sample inside the DNA array.
     */
    public int getSamplesLength() {
        return samplesLength;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        return equalsWithDnaArray((DnaArray) obj);
    }

    private boolean equalsWithDnaArray(DnaArray rightHand) {
        if (sampleList.size() != rightHand.sampleNumber) return false;

        List<Dna> rightHandCopy = rightHand.getSampleList();
        for (Dna dna : sampleList) {
            if (!rightHandCopy.remove(dna)) return false;
        }
        return true;
    }

    private <T> boolean equalsWithList(List<T> rightHand) {
        if (sampleList.size() != rightHand.size()) return false;

        List<T> rightHandCopy = Lists.newArrayList(rightHand);
        for (Dna dna : sampleList) {
            if (!rightHandCopy.remove(dna)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sampleList);
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < sampleNumber; i++) {
            output += sampleList.get(i).toString();
            if (i < sampleNumber - 1)
                output += "\n";
        }
        return output;
    }

    /**
     * Exhaustive search about which <i>k</i> long patterns with <i>d</i> mismatches fit the best in all members of the
     * {@link DnaArray}.
     * <p>
     * <b>Attention!</b> This is a very slow algorithm. Please use it only for small problems.
     *
     * @param k Gives the size of the findable pattern.
     * @param d Gives the maximum mismatch number.
     * @return The most frequent patterns in the samples.
     */
    public Set<Dna> findMostFrequentMotifsExhausting(final int k, final int d) {
        validateFindablePatternNumber(k);
        Preconditions.checkArgument(d >= 0, "Maximum mismatch number (d) should be greater or equals than 0");

        Set<Set<Dna>> mostFrequentSampleSet = sampleList.stream()
                                                        .parallel()
                                                        .map(dna -> dna.getMismatchSubSequences(k, d))
                                                        .collect(Collectors.toSet());

        Set<Dna> commonMotifSet = null;
        for (Set<Dna> samples : mostFrequentSampleSet) {
            if (commonMotifSet == null) commonMotifSet = samples;
            else commonMotifSet.retainAll(samples);
        }
        return commonMotifSet;
    }

    /**
     * TODO
     *
     * @param k
     * @return
     */
    public Set<Dna> findMostFrequentMotifsMedianString(final int k) {
        validateFindablePatternNumber(k);

        Set<Dna> mismatchSet = generatePatternDnas(k);

        int minimumArrayHammingDistance = Integer.MAX_VALUE;
        Set<Dna> minimumPatternSet = new HashSet<>();
        for (Dna mismatch : mismatchSet) {
            int arrayHammingDistance = sampleList.stream()
                    .parallel()
                    .mapToInt(dna -> dna.findMinimumMismatchSubSequenceNumber(mismatch))
                    .sum();
            if (arrayHammingDistance < minimumArrayHammingDistance) {
                minimumArrayHammingDistance = arrayHammingDistance;
                minimumPatternSet.clear();
                minimumPatternSet.add(mismatch);
            }
            else if (arrayHammingDistance == minimumArrayHammingDistance) {
                minimumPatternSet.add(mismatch);
            }
        }
        return minimumPatternSet;
    }

    private void validateFindablePatternNumber(final int k) {
        Preconditions.checkArgument(k > 0, "Findable subsequence length (k) should be greater than 0");
        Preconditions.checkArgument(k <= samplesLength, "Findable subsequence length (k) should be smaller or equals to the samples length");
    }

    private Set<Dna> generatePatternDnas(final int length) {
        return Dna.build(IntStream.range(0, length)
                .mapToObj(index -> DnaNucleotide.ADENINE)
                .toArray(DnaNucleotide[]::new)).generateMismatches(Integer.MAX_VALUE);
    }
}

package hu.bioinformatics.biolaboratory.sequence.dna;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import hu.bioinformatics.biolaboratory.utils.Validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
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
     * @throws IllegalArgumentException If dna elements have different lengths.
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
     * @throws IllegalArgumentException If dna elements have different lengths.
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
     * Getter of the motifs.
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
     * @throws IllegalArgumentException If <i>k</i> is smaller than 1.
     * @throws IllegalArgumentException If <i>k</i> is bigger than samples length.
     * @throws IllegalArgumentException If <i>d</i> is negative number.
     */
    public Set<Dna> findMostFrequentMotifsExhausting(final int k, final int d) {
        Preconditions.checkArgument(k > 0, "Findable subsequence length (k) should be greater than 0");
        Preconditions.checkArgument(k <= samplesLength, "Findable subsequence length (k) should be smaller or equals to the samples length");
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
     * Find the most frequent <i>k</i> long patterns in the samples. The found patterns are the closest in the samples
     * subsequence.
     *
     * @param k Gives the size of the findable pattern.
     * @return The most frequent patterns in the samples.
     * @throws IllegalArgumentException If <i>k</i> is smaller than 1.
     * @throws IllegalArgumentException If <i>k</i> is bigger than samples length.
     */
    public Set<Dna> findMostFrequentMotifsMedianString(final int k) {
        Preconditions.checkArgument(k <= samplesLength, "Findable subsequence length (k) should be smaller or equals to the samples length");
        Preconditions.checkArgument(k <= samplesLength, "Findable subsequence length (k) should be smaller or equals to the samples length");

        Set<Dna> mismatchSet = Dna.generatePatternDnas(k);

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
            } else if (arrayHammingDistance == minimumArrayHammingDistance) {
                minimumPatternSet.add(mismatch);
            }
        }
        return minimumPatternSet;
    }

    /**
     * Given a {@link Dna}, which subsequences are examined against the {@link DnaArray} motifs. Return with the {@link Set}
     * of subsequences which are most likely (most probable) occur in the {@link DnaArray}.
     *
     * @param dna The {@link Dna} which subsequences are examined against the {@link DnaArray}'s probability.
     * @return The most probable subsequences.
     * @throws IllegalArgumentException If dna sequence length is smaller than samples length.
     */
    public Set<Dna> profileMostProbableSubSequence(final Dna dna) {
        Preconditions.checkArgument(dna != null, "Dna should not be null");
        Preconditions.checkArgument(dna.getSequenceLength() >=  samplesLength,
                "Dna length should greater or equal than sample length");

        Map<Dna, Double> patternProbabilityMap = dna.getSubSequences(samplesLength).stream()
                .collect(Collectors.toMap(Function.identity(), this::innerPatternProbability));

        double highestProbability = 0.0;
        Set<Dna> mostProbableSamples = Sets.newHashSet();

        for (Map.Entry<Dna, Double> patternProbabilityPair : patternProbabilityMap.entrySet()) {
            double patternProbability = patternProbabilityPair.getValue();
            if (patternProbability > highestProbability) {
                highestProbability = patternProbability;
                mostProbableSamples.clear();
            }
            if (patternProbability == highestProbability) {
                mostProbableSamples.add(patternProbabilityPair.getKey());
            }
        }

        return mostProbableSamples;
    }

    /**
     * Returns the probability of the given sequence in this motif.
     *
     * @param sample The given sample.
     * @return The probability of the sample.
     * @throws IllegalArgumentException If dna sequence length is not equal than samples length.
     */
    public double patternProbability(final Dna sample) {
        Preconditions.checkArgument(sample != null, "Sample should not be null");
        Preconditions.checkArgument(sample.getSequenceLength() ==  samplesLength,
                "Sample length differs than array length");
        return innerPatternProbability(sample);
    }

    private double innerPatternProbability(final Dna sample) {
        List<Map<DnaNucleotide, Double>> profile = motifs.profile();
        DnaNucleotide[] sequenceElements = sample.getSequenceAsElements();

        return IntStream.range(0, samplesLength)
                .parallel()
                .mapToDouble(i -> profile.get(i).get(sequenceElements[i]))
                .reduce(1.0, (a, b) -> a * b);
    }
}

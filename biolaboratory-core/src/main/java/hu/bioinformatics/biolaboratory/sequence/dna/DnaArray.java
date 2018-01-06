package hu.bioinformatics.biolaboratory.sequence.dna;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkEqualNumberTo;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkGreaterOrEqualNumberTo;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotEmptyCollection;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotEmptyVarargs;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotNegativeNumber;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotNullArgument;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkPositiveNumber;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkSmallerOrEqualNumberTo;

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
        return innerBuild(Arrays.asList(checkNotEmptyVarargs(dnas)));
    }

    /**
     * Build a {@link DnaArray} from the given DNA {@link List}.
     *
     * @param dnaList The assignable elements of the {@link DnaArray}.
     * @return A new {@link DnaArray}.
     * @throws IllegalArgumentException If dna elements have different lengths.
     */
    public static DnaArray build(final List<Dna> dnaList) {
        return innerBuild(checkNotEmptyCollection(dnaList));
    }

    private static DnaArray innerBuild(final List<Dna> dnaList) {
        return new DnaArray(validateDnaArray(dnaList));
    }

    private static List<Dna> validateDnaArray(final List<Dna> dnaList) {
        int length = dnaList.get(0).getSequenceLength();
        checkArgument(dnaList.stream()
                .allMatch(dna -> dna.getSequenceLength() == length), "DNA lengths inside DNA array should be the same");
        return dnaList;
    }

    private DnaArray(final List<Dna> sampleList) {
        this.sampleList = new ArrayList<>(sampleList);
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
        return new ArrayList<>(sampleList);
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

        List<T> rightHandCopy = new ArrayList<>(rightHand);
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
     * Return a {@link DnaArray} which is the sum of the {@link DnaArray}'s element and the given {@link Dna}s.
     *
     * @param dnas The {@link Dna}s to add to the {@link DnaArray}.
     * @return A new {@link DnaArray} which is the sum of the existing elements and the given {@link Dna}s.
     * @throws IllegalArgumentException If input varargs contains null value.
     * @throws IllegalArgumentException If input varargs elements length differ than sequence length.
     */
    public DnaArray add(final Dna... dnas) {
        checkNotNullArgument("DNA varargs", dnas);
        return dnas.length == 0 ? copy() : innerAdd(DnaArray.build(dnas));
    }

    /**
     * Return a {@link DnaArray} which is the sum of the {@link DnaArray}'s element and the given {@link Dna} list.
     *
     * @param dnaList The {@link Dna} list which elements add to the {@link DnaArray}.
     * @return A new {@link DnaArray} which is the sum of the existing elements and the given {@link Dna} list's elements.
     * @throws IllegalArgumentException If input list contains null element.
     * @throws IllegalArgumentException If input list elements length differ than sequence length.
     */
    public DnaArray add(final List<Dna> dnaList) {
        checkNotNullArgument("DNA list", dnaList);
        return dnaList.isEmpty() ? copy() : innerAdd(DnaArray.build(dnaList));
    }

    /**
     * Return a {@link DnaArray} which is the sum of the {@link DnaArray}'s element and the given {@link DnaArray}'s elements.
     *
     * @param otherDnaArray The other {@link DnaArray} which elements add to the {@link DnaArray}.
     * @return A new {@link DnaArray} which is the sum of the existing elements and the given {@link DnaArray}.
     * @throws IllegalArgumentException If other {@link DnaArray} is null.
     * @throws IllegalArgumentException If other {@link DnaArray} sequence length is differ than sequence length.
     */
    public DnaArray add(final DnaArray otherDnaArray) {
        return innerAdd(checkNotNullArgument("Other DNA array", otherDnaArray));
    }

    private DnaArray innerAdd(final DnaArray otherDnaArray) {
        checkEqualNumberTo("Other DNA array samples length", otherDnaArray.samplesLength, "DNA array samples length", samplesLength);
        List<Dna> addedList = new ArrayList<>(sampleNumber + otherDnaArray.samplesLength);
        addedList.addAll(sampleList);
        addedList.addAll(otherDnaArray.sampleList);
        return new DnaArray(addedList);
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
        checkPositiveNumber("Findable subsequence length (k)", k);
        checkSmallerOrEqualNumberTo("Findable subsequence length (k)", k, "samples length", samplesLength);
        checkNotNegativeNumber("Maximum mismatch number (d)", d);

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
        checkPositiveNumber("Findable subsequences length (k)", k);
        checkSmallerOrEqualNumberTo("Findable subsequences length (k)", k, "samples length", samplesLength);

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
        checkNotNullArgument("DNA", dna);
        checkGreaterOrEqualNumberTo("DNA length", dna.getSequenceLength(), "samples length", samplesLength);

        Map<Dna, Double> patternProbabilityMap = dna.getSubSequences(samplesLength).stream()
                .collect(Collectors.toMap(Function.identity(), this::innerPatternProbability));

        double highestProbability = 0.0;
        Set<Dna> mostProbableSamples = new HashSet<>();

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
        checkNotNullArgument("Sample", sample);
        checkEqualNumberTo("Sample length", sample.getSequenceLength(), "DNA array length", samplesLength);
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

//    public Set<Dna> greedyMotifSearch(final int k, final int t) {
//        Set<Dna> subSequenceSet = sampleList.get(0).getSubSequences(k);
//
//        Set<DnaArray> generatorDnaArraySet = subSequenceSet.stream()
//                .map(dna -> new DnaArray(Lists.newArrayList(dna)))
//                .collect(Collectors.toCollection(HashSet::new));
//
//        for (Dna dna : sampleList.subList(1, samplesLength)) {
//            Set<DnaArray> newGeneratorDnaArraySet = new HashSet<>();
//            for (DnaArray generatorDnaArray : generatorDnaArraySet) {
//                Set<Dna> dnas = generatorDnaArray.profileMostProbableSubSequence(dna);
//                dnas.stream()
//                        .map()
//            }
//        }
//        return null;
//    }
}

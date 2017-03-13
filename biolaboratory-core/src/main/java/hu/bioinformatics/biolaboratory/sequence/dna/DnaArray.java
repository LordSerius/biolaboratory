package hu.bioinformatics.biolaboratory.sequence.dna;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import hu.bioinformatics.biolaboratory.utils.datastructures.CountableOccurrenceMap;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.Validate.noNullElements;

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

    private DnaNucleotide[][] motifs;
    private List<CountableOccurrenceMap<DnaNucleotide>> motifCounts;
    private List<Map<DnaNucleotide, Double>> motifProfile;
    private int[] motifScores;
    private int totalScore = -1;

    /**
     * Build a {@link DnaArray} from the given {@link Dna}s.
     *
     * @param dnas The input {@link Dna}s
     * @return A new {@link DnaArray}
     */
    public static DnaArray build(final Dna... dnas) {
        Preconditions.checkArgument(dnas != null, "Input DNAs should not be null");
        return build(Arrays.asList(dnas));
    }

    /**
     * Build a {@link DnaArray} from the given DNA {@link List}.
     *
     * @param dnaList The assignable elements of the {@link DnaArray}.
     * @return A new {@link DnaArray}.
     */
    public static DnaArray build(final List<Dna> dnaList) {
        validateDnaArray(dnaList);
        return new DnaArray(dnaList);
    }

    private static void validateDnaArray(final List<Dna> dnaList) {
        Preconditions.checkArgument(dnaList != null && !dnaList.isEmpty(), "DNA list should not be empty");
        noNullElements(dnaList, "DNA list should not contain null element");
        int length = dnaList.get(0).getSequenceLength();
        Preconditions.checkArgument(dnaList.stream()
                .allMatch(dna -> dna.getSequenceLength() == length), "DNA lengths inside DNA array should be the same");
    }

    private DnaArray(final List<Dna> sampleList) {
        this.sampleList = Lists.newArrayList(sampleList);
        this.sampleNumber = sampleList.size();
        this.samplesLength = sampleList.get(0).getSequenceLength();
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
    public Set<Dna> findMostFrequentMotifs(final int k, final int d) {
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
     * Returns an immutable profile about the {@link DnaNucleotide} based on each column.
     *
     * @return The profile of the {@link DnaArray}.
     */
    public List<Map<DnaNucleotide, Double>> profile() {
        return new ArrayList<>(createProfile());
    }

    private synchronized List<Map<DnaNucleotide, Double>> createProfile() {
        if (motifProfile == null) {
            motifProfile = createCountMotifs().stream()
                    .map(occurrenceMap -> {
                        Map<DnaNucleotide, Double> columnProfileMap = new HashMap<>();
                        occurrenceMap.getOccurrencesInMap()
                                .entrySet()
                                .forEach(nucleotideOccurrence -> columnProfileMap.put(
                                        nucleotideOccurrence.getKey(), (double) nucleotideOccurrence.getValue() / sampleNumber));
                        return columnProfileMap;
                    })
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return motifProfile;
    }

    /**
     * Sums the column scores.
     *
     * @return The sum of column scores.
     */
    public synchronized int totalScore() {
        if (totalScore == -1) {
            totalScore = Arrays.stream(createScore()).sum();
        }
        return totalScore;
    }

    /**
     * Creates the score vector from the {@link DnaArray}.
     * <p>
     * A score element shows how many different amino acids than the most dominant are presented.
     * E.g. If the column contains 2 adenine, 1 cytosine, and 7 thymine, the element value will be 3.
     *
     * @return The score array.
     */
    public int[] score() {
        return Arrays.copyOf(createScore(), motifScores.length);
    }

    private synchronized int[] createScore() {
        if (motifScores == null) {
            motifScores = createCountMotifs().stream()
                    .parallel()
                    .mapToInt(occurrenceMap -> sampleNumber - occurrenceMap.maximumOccurrenceValue())
                    .toArray();
        }
        return motifScores;
    }

    /**
     * Counts the total occurrences of every {@link DnaNucleotide} in every {@link Dna}.
     *
     * @return Immutable {@link List} of occurrences.
     */
    public List<CountableOccurrenceMap<DnaNucleotide>> count() {
        return new ArrayList<>(createCountMotifs());
    }

    private synchronized List<CountableOccurrenceMap<DnaNucleotide>> createCountMotifs() {
        if (motifCounts == null) {
            motifCounts = Lists.newArrayListWithCapacity(samplesLength);
            DnaNucleotide[][] motifs = createMotifs();
            for (int j = 0; j < samplesLength; j++) {
                CountableOccurrenceMap<DnaNucleotide> nucleotideOccurrenceMap = CountableOccurrenceMap.build(DnaNucleotide.NUCLEOTIDE_SET);
                for (int i = 0; i < sampleNumber; i++) {
                    nucleotideOccurrenceMap.increase(motifs[i][j]);
                }
                motifCounts.add(nucleotideOccurrenceMap);
            }
        }
        return motifCounts;
    }

    private synchronized DnaNucleotide[][] createMotifs() {
        if (motifs == null) {
            motifs = sampleList.stream()
                    .parallel()
                    .map(dna -> dna.getSequence().chars()
                            .mapToObj(nucleotide -> DnaNucleotide.findDnaNucleotide((char) nucleotide))
                            .toArray(DnaNucleotide[]::new))
                    .toArray(DnaNucleotide[][]::new);
        }
        return motifs;
    }
}

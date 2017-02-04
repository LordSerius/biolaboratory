package hu.bioinformatics.biolaboratory.sequence.dna;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import hu.bioinformatics.biolaboratory.utils.datastructures.OccurrenceMap;

import java.util.*;
import java.util.stream.Collectors;

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

    private List<List<DnaNucleotide>> motifs;
    private List<OccurrenceMap<DnaNucleotide>> motifCounts;

    /**
     * Build a {@link DnaArray} from the given {@link Dna}s
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
        Preconditions.checkArgument(dnaList.stream()
                .allMatch(dna -> dna != null), "DNA list should not contain null element");
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
        if (obj instanceof List) return equalsWithList((List) obj);
        if (obj instanceof DnaArray) return equalsWithDnaArray((DnaArray) obj);
        return false;
    }

    private boolean equalsWithDnaArray(DnaArray rightHand) {
        return equalsWithList(rightHand.getSampleList());
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

//    public List<Integer> score() {
//        int[][] nucleobaseMatrix = count(dnaArray);
//        int[] scoreArray = new int[nucleobaseMatrix[0].length];
//        for (int j = 0; j < nucleobaseMatrix[0].length; j++) {
//            int max = nucleobaseMatrix[0][j];
//            for (int i = 1; i < nucleobaseMatrix.length; i++) {
//                if (nucleobaseMatrix[i][j] > max) max = nucleobaseMatrix[i][j];
//            }
//            scoreArray[j] = dnaArray.size() - max;
//        }
//        return scoreArray;
//    }

    public synchronized List<OccurrenceMap<DnaNucleotide>> countMotifs() {
        if (motifCounts == null) {
            motifCounts = Lists.newArrayListWithCapacity(sampleNumber);
            for (List<DnaNucleotide> nucleotideList : createMotifs()) {
                OccurrenceMap<DnaNucleotide> nucleotideOccurrenceMap = OccurrenceMap.build();
                nucleotideList.forEach(nucleotide -> nucleotideOccurrenceMap.increase(nucleotide));
                motifCounts.add(nucleotideOccurrenceMap);
            }
        }
        return motifCounts;
    }

    private synchronized List<List<DnaNucleotide>> createMotifs() {
        if (motifs == null) {
            motifs = sampleList.stream()
                    .map(dna -> dna.getSequence().chars()
                            .mapToObj(nucleotide -> DnaNucleotide.findDnaNucleotide((char) nucleotide))
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());
        }
        return motifs;
    }
}

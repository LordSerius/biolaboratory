package hu.bioinformatics.biolaboratory.sequence.dna;

import com.google.common.base.*;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import hu.bioinformatics.biolaboratory.utils.datastructures.CountableOccurrenceMap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stores motif information about the given {@link DnaArray}.
 *
 * @author Attila Radi
 */
public class Motifs {
    private final DnaArray dnaArray;

    private DnaNucleotide[][] motifs;
    private List<CountableOccurrenceMap<DnaNucleotide>> motifCounts;
    private List<Map<DnaNucleotide, Double>> motifProfile;
    private int[] motifScores;
    private int totalScore = -1;
    private List<List<DnaNucleotide>> extractedConsensus;

    public static Motifs build(final DnaArray dnaArray) {
        Preconditions.checkArgument(dnaArray != null, "DNA array should not be null");
        return new Motifs(dnaArray);
    }

    private Motifs(final DnaArray dnaArray) {
        this.dnaArray = dnaArray;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Motifs rightHand = (Motifs) obj;
        return dnaArray.equals(rightHand.dnaArray);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dnaArray);
    }

    @Override
    public String toString() {
        return "Motifs{" +
                "motifScores=" + Arrays.toString(createMotifs()) +
                '}';
    }

    /**
     * Return the copy fot the motifs field.
     *
     * @return motifs
     */
    public DnaNucleotide[][] getMotifs() {
        return Arrays.copyOf(createMotifs(), motifs.length);
    }

    //    private synchronized List<List<DnaNucleotide>> createExtractedConsensus() {
//        extractedConsensus = Lists.newArrayListWithCapacity(dnaArray.getSamplesLength());
//        createProfile().forEach(entry -> en);
//
//        for (int j = 0; j < profileArray[0].length; j++) {
//            double maxValue = 0;
//            for (int i = 0; i < profileArray.length; i++) {
//                if (maxValue < profileArray[i][j]) {
//                    maxValue = profileArray[i][j];
//                    consensusArray[j] = indexToNucleobase.get(i);
//                }
//            }
//        }
//        return consensusArray;
//    }

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
                                        nucleotideOccurrence.getKey(), (double) nucleotideOccurrence.getValue() / dnaArray.getSampleNumber()));
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
                    .mapToInt(occurrenceMap -> dnaArray.getSampleNumber() - occurrenceMap.maximumOccurrenceValue())
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
            DnaNucleotide[][] motifs = createMotifs();
            motifCounts = Lists.newArrayListWithCapacity(dnaArray.getSamplesLength());
            for (int j = 0; j < dnaArray.getSamplesLength(); j++) {
                CountableOccurrenceMap<DnaNucleotide> nucleotideOccurrenceMap = CountableOccurrenceMap.build(DnaNucleotide.NUCLEOTIDE_SET);
                for (int i = 0; i < dnaArray.getSampleNumber(); i++) {
                    nucleotideOccurrenceMap.increase(motifs[i][j]);
                }
                motifCounts.add(nucleotideOccurrenceMap);
            }
        }
        return motifCounts;
    }

    private synchronized DnaNucleotide[][] createMotifs() {
        if (motifs == null) {
            motifs = dnaArray.getSampleList().stream()
                        .parallel()
                        .map(dna -> dna.getSequence().chars()
                                .mapToObj(nucleotide -> DnaNucleotide.findDnaNucleotide((char) nucleotide))
                                .toArray(DnaNucleotide[]::new))
                        .toArray(DnaNucleotide[][]::new);
        }
        return motifs;
    }
}

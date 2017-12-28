package hu.bioinformatics.biolaboratory.sequence.dna;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import hu.bioinformatics.biolaboratory.utils.DoubleUtils;
import hu.bioinformatics.biolaboratory.utils.datastructures.CountableOccurrenceMap;
import hu.bioinformatics.biolaboratory.utils.datastructures.OccurrenceMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Stores motif information about the given {@link DnaArray}.
 *
 * @author Attila Radi
 */
class Motifs {
    private final DnaArray dnaArray;

    private DnaNucleotide[][] motifs;
    private List<CountableOccurrenceMap<DnaNucleotide>> motifCounts;
    private List<Map<DnaNucleotide, Double>> motifProfile;
    private int[] motifScores;
    private int totalScore = Integer.MIN_VALUE;
    private double[] motifEntropies;
    private double totalEntropy = Double.NEGATIVE_INFINITY;
    private Set<Dna> consensusDnaSet;

    /**
     * Creates {@link Motifs} about the given {@link DnaArray}.
     *
     * @param dnaArray The {@link DnaArray} to make {@link Motifs} from.
     * @return The {@link Motifs} representation of the {@link DnaArray}.
     */
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

    /**
     * Returns immutable with the consensus set.
     *
     * @return The consensus {@link Dna}s in {@link Set}.
     */
    public Set<Dna> consensus() {
        return Sets.newHashSet(createConsensusDnaSet());
    }

    /**
     * Queries the consensus (most possible) {@link Dna}s for the {@link Motifs}.
     * <p>
     * The work of the algorithm:
     * <ol>
     *     <li>Get the maximum occurrences from each column. Each column will have a {@link Set} of
     *     {@link DnaNucleotide} for it.</li>
     *     <li>Initialize a prefix {@link Set} with the 0. index of {@link DnaNucleotide}s of maximumColumnOccurrences.</li>
     *     <li>Iterates maximumColumnOccurrences from 1. index and append this to the prefix.</li>
     *     <li>Continue the extension with this new prefix {@link Set} until the end.</li>
     *     <li>The last prefix {@link Set} will be the consensus {@link Set}</li>
     * </ol>
     *
     * @return The new consensus {@link Dna}s in a {@link Set}.
     */
    private synchronized Set<Dna> createConsensusDnaSet() {
        if (consensusDnaSet == null) {
            List<Set<DnaNucleotide>> maximumColumnOccurrences = createCountMotifs().stream()
                    .map(OccurrenceMap::filterMostFrequentOccurrences)
                    .collect(Collectors.toCollection(ArrayList::new));

            Set<List<DnaNucleotide>> consensusPrefixes = Sets.newHashSet();
            for (DnaNucleotide dnaNucleotide : maximumColumnOccurrences.get(0)) {
                consensusPrefixes.add(Lists.newArrayList(dnaNucleotide));
            }
            int length = maximumColumnOccurrences.size();

            for (int i = 1; i < length; i++) {
                Set<List<DnaNucleotide>> newConsensusPrefixes = Sets.newHashSet();
                for (List<DnaNucleotide> prefix : consensusPrefixes) {
                    for (DnaNucleotide dnaNucleotide : maximumColumnOccurrences.get(i)) {
                        List<DnaNucleotide> extendedPrefix = Lists.newArrayList(prefix);
                        extendedPrefix.add(dnaNucleotide);
                        newConsensusPrefixes.add(extendedPrefix);
                    }
                }
                consensusPrefixes = newConsensusPrefixes;
            }
            consensusDnaSet = consensusPrefixes.stream()
                    .map(Dna::build)
                    .collect(Collectors.toCollection(HashSet::new));
        }
        return consensusDnaSet;
    }

    /**
     * Returns with the sum of column entropies.
     * <p>
     * Calculating entropy:
     * <br>
     * Σ column entropy
     *
     * @return The sum of column entropies.
     */
    public double totalEntropy() {
        if (totalEntropy < 0.0d) {
            totalEntropy = Arrays.stream(createColumnEntropy()).sum();
        }
        return totalEntropy;
    }

    /**
     * Returns with the column entropies.
     * <p>
     * Calculating entropy for each column:
     * <br>
     * - Σ ratio * log2(ratio) for each element
     *
     * @return Column entropies.
     */
    public double[] entropy() {
        return Arrays.copyOf(createColumnEntropy(), motifEntropies.length);
    }

    private synchronized double[] createColumnEntropy() {
        if (motifEntropies == null) {
            motifEntropies = createProfile().stream()
                    .parallel()
                    .mapToDouble(nucleotideDoubleMap -> nucleotideDoubleMap.entrySet().stream()
                            .mapToDouble(entry -> {
                                final double profile = entry.getValue();
                                return profile == 0.0 ? 0.0 : profile * Math.log(profile) / DoubleUtils.LOG_2;
                            })
                            .map(invertedEntropy -> -invertedEntropy)
                            .sum())
                    .toArray();
        }
        return motifEntropies;
    }

    /**
     * Returns an immutable profile about the {@link DnaNucleotide} based on each column.
     *
     * @return The profile of the {@link DnaArray}.
     */
    public List<Map<DnaNucleotide, Double>> profile() {
        return Lists.newArrayList(createProfile());
    }

    private synchronized List<Map<DnaNucleotide, Double>> createProfile() {
        if (motifProfile == null) {
            motifProfile = createCountMotifs().stream()
                    .map(OccurrenceMap::allOccurrenceRatios)
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
        if (totalScore < 0) {
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
        return Lists.newArrayList(createCountMotifs());
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

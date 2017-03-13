package hu.bioinformatics.biolaboratory.sequence.dna;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.ImmutableMap;
import hu.bioinformatics.biolaboratory.utils.collectors.DnaCollectors;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaArrayLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaSetLoader;
import hu.bioinformatics.biolaboratory.utils.datastructures.CountableOccurrenceMap;
import org.testng.annotations.DataProvider;

import javax.inject.Inject;

/**
 * Test data provider for the {@link DnaArrayTest} test class.
 *
 * @author Attila Radi
 */
public class DnaArrayTestDataProvider {

    @Inject
    private DnaSetLoader testDnaSetLoader;
    @Inject
    private DnaArrayLoader testDnaArrayLoader;

    static final String INVALID_DNA_ARRAY_LIST_DATA_PROVIDER_NAME = "invalidDnaArrayListDataProvider";

    @DataProvider(name = INVALID_DNA_ARRAY_LIST_DATA_PROVIDER_NAME)
    Object[][] invalidDnaArrayListDataProvider() {
        return new Object[][] {
                { null },
                { Lists.newArrayList(Dna.build("A"), null) },
                { DnaCollectors.stringToDnaList("A", "AC") }
        };
    }

    static final String VALID_DNA_ARRAY_LIST_DATA_PROVIDER_NAME = "validDnaArrayListDataProvider";

    @DataProvider(name = VALID_DNA_ARRAY_LIST_DATA_PROVIDER_NAME)
    Object[][] validDnaArrayListDataProvider() {
        return new Object[][] {
                { DnaCollectors.stringToDnaList("A", "C", "G", "T"), Lists.newArrayList("A", "C", "G", "T") }
        };
    }

    static final String DNA_ARRAY_COPY_DATA_PROVIDER_NAME = "dnaArrayCopyDataProvider";

    @DataProvider(name = DNA_ARRAY_COPY_DATA_PROVIDER_NAME)
    Object[][] copyDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")) }
        };
    }

    static final String DNA_ARRAY_EQUALS_DATA_PROVDER_NAME = "dnaArrayEqualsDataProvider";

    @DataProvider(name = DNA_ARRAY_EQUALS_DATA_PROVDER_NAME)
    Object[][] equalsDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "G", "T")), null, false },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "G", "T")), DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "G")), false },
                { DnaArray.build(DnaCollectors.stringToDnaList("AA", "AC", "AG", "AT")), DnaArray.build(DnaCollectors.stringToDnaList("AA", "AC", "AG", "AT", "CA")), false },
                { DnaArray.build(DnaCollectors.stringToDnaList("AA", "AC", "AG", "AT")), DnaArray.build(DnaCollectors.stringToDnaList("AA", "AA", "AG", "CT")), false },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "G", "T")), DnaArray.build(DnaCollectors.stringToDnaList("A", "A", "G", "T")), false },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "G", "T")), DnaArray.build(DnaCollectors.stringToDnaList(Lists.newArrayList("A", "C", "G", "T"))), true },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "G", "T")), DnaArray.build(DnaCollectors.stringToDnaList(Lists.newArrayList("A", "G", "C", "T"))), true }
        };
    }

    static final String INVALID_FIND_MOST_FREQUENT_MOTIFS_DATA_PROVIDER_NAME = "invalidFindMostFrequentMotifsDataProvider";

    @DataProvider(name = INVALID_FIND_MOST_FREQUENT_MOTIFS_DATA_PROVIDER_NAME)
    Object[][] invalidFindMostFrequentMotifsDataProvider() {
        return new Object[][] {
                { DnaArray.build(Dna.build("ACGT")), 0, 1 },
                { DnaArray.build(Dna.build("ACGT")), 5, 1 },
                { DnaArray.build(Dna.build("ACGT")), 2, -1 }
        };
    }

    static final String VALID_FIND_MOST_FREQUENT_MOTIFS_DATA_PROVIDER_NAME = "validFindMostFrequentMotifsDataProvider";

    @DataProvider(name = VALID_FIND_MOST_FREQUENT_MOTIFS_DATA_PROVIDER_NAME)
    Object[][] validFindMostFrequentMotifsDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("AAAAAA", "AAAAAA", "AAAAAA", "AAAAAA")), 2, 0, DnaCollectors.stringToDnaSet("AA") },
                { DnaArray.build(DnaCollectors.stringToDnaList("AAAAAA")), 2, 1, DnaCollectors.stringToDnaSet("AA", "AC", "AG", "AT", "CA", "GA", "TA") },
                { DnaArray.build(DnaCollectors.stringToDnaList("AAAAAA", "ACGTAC")), 2, 3, DnaCollectors.stringToDnaSet("AA", "AC", "AG", "AT", "CA", "CC", "CG", "CT", "GA", "GC", "GG", "GT", "TA", "TC", "TG", "TT") },
                { DnaArray.build(DnaCollectors.stringToDnaList("AAAAAA", "CCCCCC", "GGGGGG", "TTTTTT")), 2, 0, DnaCollectors.stringToDnaSet() },
                { DnaArray.build(DnaCollectors.stringToDnaList("ATTTGGC", "TGCCTTA", "CGGTATC", "GAAAATT")), 3, 1, DnaCollectors.stringToDnaSet("ATA", "ATT", "GTT", "TTT") },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "ACGT", "ACGT")), 3, 0, DnaCollectors.stringToDnaSet("ACG", "CGT") },
                { DnaArray.build(DnaCollectors.stringToDnaList("AAAAA", "AAAAA", "AAAAA")), 3, 1, DnaCollectors.stringToDnaSet("AAA", "AAC", "AAG", "AAT", "ACA", "AGA", "ATA", "CAA", "GAA", "TAA") },
                { DnaArray.build(DnaCollectors.stringToDnaList("AAAAA", "AAAAA", "AAAAA")), 3, 3, testDnaSetLoader.load("find-most-frequent-motifs-test-dataset-3-solution.txt") },
                { DnaArray.build(DnaCollectors.stringToDnaList("AAAAA", "AAAAA", "AACAA")), 3, 0, DnaCollectors.stringToDnaSet() },
                { DnaArray.build(DnaCollectors.stringToDnaList("AACAA", "AAAAA", "AAAAA")), 3, 0, DnaCollectors.stringToDnaSet() },
                { DnaArray.build(DnaCollectors.stringToDnaList("TCTGAGCTTGCGTTATTTTTAGACC", "GTTTGACGGGAACCCGACGCCTATA", "TTTTAGATTTCCTCAGTCCACTATA", "CTTACAATTTCGTTATTTATCTAAT", "CAGTAGGAATAGCCACTTTGTTGTA", "AAATCCATTAAGGAAAGACGACCGT")), 5, 2, testDnaSetLoader.load("find-most-frequent-motifs-extra-dataset-solution.txt") }
        };
    }

    static final String COUNT_DATA_PROVIDER_NAME = "countDataProviderName";

    @DataProvider(name = COUNT_DATA_PROVIDER_NAME)
    Object[][] countMotifsDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), Lists.newArrayList(CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 1, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 1, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 1, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 1))) },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "ACGT")), Lists.newArrayList(CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 2, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 2, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 2, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 2))) },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "TGCA")), Lists.newArrayList(CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 1, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 1)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 1, DnaNucleotide.GUANINE, 1, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 1, DnaNucleotide.GUANINE, 1, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 1, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 1))) },
                { testDnaArrayLoader.load("motif-calculation.fas"), Lists.newArrayList(CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 2, DnaNucleotide.CYTOSINE, 1, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 7)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 2, DnaNucleotide.CYTOSINE, 6, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 2)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 10, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 10, DnaNucleotide.THYMINE, 0)),
                        CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 9, DnaNucleotide.THYMINE, 1)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 9, DnaNucleotide.THYMINE, 1)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 9, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 1, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 1, DnaNucleotide.CYTOSINE, 4, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 5)),
                        CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 1, DnaNucleotide.CYTOSINE, 1, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 8)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 1, DnaNucleotide.CYTOSINE, 2, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 7)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 3, DnaNucleotide.CYTOSINE, 4, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 3)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 6, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 4))) }
        };
    }

    static final String SCORE_DATA_PROVIDER_NAME = "scoreDataProviderName";

    @DataProvider(name = SCORE_DATA_PROVIDER_NAME)
    Object[][] scoreDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), new int[] {0, 0, 0, 0} },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "ACGT")), new int[] {0, 0, 0, 0} },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "TGCA")), new int[] {1, 1, 1, 1} },
                { testDnaArrayLoader.load("motif-calculation.fas"), new int[] {3, 4, 0, 0, 1, 1, 1, 5, 2, 3, 6, 4} }
        };
    }

    static final String TOTAL_SCORE_DATA_PROVIDER_NAME = "totalScoreDataProviderName";

    @DataProvider(name = TOTAL_SCORE_DATA_PROVIDER_NAME)
    Object[][] totalScoreDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), 0 },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "ACGT")), 0 },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "TGCA")), 4 },
                { testDnaArrayLoader.load("motif-calculation.fas"), 30 }
        };
    }

    static final String PROFILE_DATA_PROVIDER_NAME = "profileDataProvider";

    @DataProvider(name = PROFILE_DATA_PROVIDER_NAME)
    Object[][] profileDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), Lists.newArrayList(ImmutableMap.of(DnaNucleotide.ADENINE, 1.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 1.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 1.0, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 1.0)) },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "ACGT")), Lists.newArrayList(ImmutableMap.of(DnaNucleotide.ADENINE, 1.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 1.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 1.0, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 1.0)) },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "TGCA")), Lists.newArrayList(ImmutableMap.of(DnaNucleotide.ADENINE, 0.5, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.5), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.5, DnaNucleotide.GUANINE, 0.5, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.5, DnaNucleotide.GUANINE, 0.5, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.5, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.5)) },
                { testDnaArrayLoader.load("motif-calculation.fas"), Lists.newArrayList(ImmutableMap.of(DnaNucleotide.ADENINE, 0.2, DnaNucleotide.CYTOSINE, 0.1, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.7), ImmutableMap.of(DnaNucleotide.ADENINE, 0.2, DnaNucleotide.CYTOSINE, 0.6, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.2), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 1.0, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 1.0, DnaNucleotide.THYMINE, 0.0),
                        ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.9, DnaNucleotide.THYMINE, 0.1), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.9, DnaNucleotide.THYMINE, 0.1), ImmutableMap.of(DnaNucleotide.ADENINE, 0.9, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.1, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.1, DnaNucleotide.CYTOSINE, 0.4, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.5),
                        ImmutableMap.of(DnaNucleotide.ADENINE, 0.1, DnaNucleotide.CYTOSINE, 0.1, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.8), ImmutableMap.of(DnaNucleotide.ADENINE, 0.1, DnaNucleotide.CYTOSINE, 0.2, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.7), ImmutableMap.of(DnaNucleotide.ADENINE, 0.3, DnaNucleotide.CYTOSINE, 0.4, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.3), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.6, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.4)) }
        };
    }
}

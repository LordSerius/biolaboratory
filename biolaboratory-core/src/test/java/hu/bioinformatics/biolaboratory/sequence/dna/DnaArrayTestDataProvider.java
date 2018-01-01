package hu.bioinformatics.biolaboratory.sequence.dna;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import hu.bioinformatics.biolaboratory.utils.collectors.DnaCollectors;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaArrayLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaSetLoader;
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
    private Object[][] invalidDnaArrayListDataProvider() {
        return new Object[][] {
                { null },
                { Lists.newArrayList(Dna.build("A"), null) },
                { DnaCollectors.stringToDnaList("A", "AC") }
        };
    }

    static final String VALID_DNA_ARRAY_LIST_DATA_PROVIDER_NAME = "validDnaArrayListDataProvider";

    @DataProvider(name = VALID_DNA_ARRAY_LIST_DATA_PROVIDER_NAME)
    private Object[][] validDnaArrayListDataProvider() {
        return new Object[][] {
                { DnaCollectors.stringToDnaList("A", "C", "G", "T"), ImmutableList.of("A", "C", "G", "T") }
        };
    }

    static final String DNA_ARRAY_COPY_DATA_PROVIDER_NAME = "dnaArrayCopyDataProvider";

    @DataProvider(name = DNA_ARRAY_COPY_DATA_PROVIDER_NAME)
    private Object[][] copyDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")) }
        };
    }

    static final String DNA_ARRAY_EQUALS_DATA_PROVIDER_NAME = "dnaArrayEqualsDataProvider";

    @DataProvider(name = DNA_ARRAY_EQUALS_DATA_PROVIDER_NAME)
    private Object[][] equalsDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "G", "T")), null, false },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "G", "T")), DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "G")), false },
                { DnaArray.build(DnaCollectors.stringToDnaList("AA", "AC", "AG", "AT")), DnaArray.build(DnaCollectors.stringToDnaList("AA", "AC", "AG", "AT", "CA")), false },
                { DnaArray.build(DnaCollectors.stringToDnaList("AA", "AC", "AG", "AT")), DnaArray.build(DnaCollectors.stringToDnaList("AA", "AA", "AG", "CT")), false },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "G", "T")), DnaArray.build(DnaCollectors.stringToDnaList("A", "A", "G", "T")), false },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "G", "T")), DnaArray.build(DnaCollectors.stringToDnaList(ImmutableList.of("A", "C", "G", "T"))), true },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "G", "T")), DnaArray.build(DnaCollectors.stringToDnaList(ImmutableList.of("A", "G", "C", "T"))), true }
        };
    }

    static final String INVALID_ADD_ELEMENTS_DATA_PROVIDER_NAME = "invalidAddElementsDataProvider";

    @DataProvider(name = INVALID_ADD_ELEMENTS_DATA_PROVIDER_NAME)
    private Object[][] invalidAddElementsDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), null },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), new Dna[] { Dna.build("A"), null } },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), DnaCollectors.stringToDnas("AA") },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), DnaCollectors.stringToDnas("ACG") }
        };
    }

    static final String VALID_ADD_ELEMENTS_DATA_PROVIDER_NAME = "validAddElementsDataProvider";

    @DataProvider(name = VALID_ADD_ELEMENTS_DATA_PROVIDER_NAME)
    private Object[][] validAddElementsDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), DnaCollectors.stringToDnas(), DnaArray.build(DnaCollectors.stringToDnaList("A", "C")) },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), DnaCollectors.stringToDnas("A"), DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "A")) },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), DnaCollectors.stringToDnas("A", "G"), DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "A", "G")) }
        };
    }

    static final String INVALID_ADD_LIST_DATA_PROVIDER_NAME = "invalidAddListDataProvider";

    @DataProvider(name = INVALID_ADD_LIST_DATA_PROVIDER_NAME)
    private Object[][] invalidAddListDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), null },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), Lists.newArrayList(Dna.build("A"), null) },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), DnaCollectors.stringToDnaList("AA") },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), DnaCollectors.stringToDnaList("ACG") }
        };
    }

    static final String VALID_ADD_LIST_DATA_PROVIDER_NAME = "validAddListDataProvider";

    @DataProvider(name = VALID_ADD_LIST_DATA_PROVIDER_NAME)
    private Object[][] validAddListDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), DnaCollectors.stringToDnaList(), DnaArray.build(DnaCollectors.stringToDnaList("A", "C")) },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), DnaCollectors.stringToDnaList("A"), DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "A")) },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), DnaCollectors.stringToDnaList("A", "G"), DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "A", "G")) }

        };
    }

    static final String INVALID_ADD_DNA_ARRAY_DATA_PROVIDER_NAME = "invalidAddDnaArrayDataProvider";

    @DataProvider(name = INVALID_ADD_DNA_ARRAY_DATA_PROVIDER_NAME)
    private Object[][] invalidAddDnaArrayDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), null },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), DnaArray.build(DnaCollectors.stringToDnaList("AA")) },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), DnaArray.build(DnaCollectors.stringToDnas("ACG")) }
        };
    }

    static final String VALID_ADD_DNA_ARRAY_DATA_PROVIDER_NAME = "validAddDnaArrayDataProvider";

    @DataProvider(name = VALID_ADD_DNA_ARRAY_DATA_PROVIDER_NAME)
    private Object[][] validAddDnaArrayDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), DnaArray.build(DnaCollectors.stringToDnaList("A")), DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "A")) },
                { DnaArray.build(DnaCollectors.stringToDnaList("A", "C")), DnaArray.build(DnaCollectors.stringToDnaList("A", "G")), DnaArray.build(DnaCollectors.stringToDnaList("A", "C", "A", "G")) }
        };
    }

    static final String INVALID_FIND_MOST_FREQUENT_MOTIFS_DATA_PROVIDER_NAME = "invalidFindMostFrequentMotifsDataProvider";

    @DataProvider(name = INVALID_FIND_MOST_FREQUENT_MOTIFS_DATA_PROVIDER_NAME)
    private Object[][] invalidFindMostFrequentMotifsDataProvider() {
        return new Object[][] {
                { DnaArray.build(Dna.build("ACGT")), 0, 1 },
                { DnaArray.build(Dna.build("ACGT")), 5, 1 },
                { DnaArray.build(Dna.build("ACGT")), 2, -1 }
        };
    }

    static final String VALID_FIND_MOST_FREQUENT_MOTIFS_EXHAUSTING_DATA_PROVIDER_NAME = "validFindMostFrequentMotifsExhaustingDataProvider";

    @DataProvider(name = VALID_FIND_MOST_FREQUENT_MOTIFS_EXHAUSTING_DATA_PROVIDER_NAME)
    private Object[][] validFindMostFrequentMotifsExhaustingDataProvider() {
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

    static final String INVALID_FIND_MOST_FREQUENT_MOTIFS_MEDIAN_STRING_DATA_PROVIDER_NAME = "invalidFindMostFrequentMotifsMedianStringDataProvider";

    @DataProvider(name = INVALID_FIND_MOST_FREQUENT_MOTIFS_MEDIAN_STRING_DATA_PROVIDER_NAME)
    private Object[][] invalidFindMostFrequentMotifsMedianStringDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "ACGT")), 0 },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "ACGT")), 5 }
        };
    }

    static final String VALID_FIND_MOST_FREQUENT_MOTIFS_MEDIAN_STRING_DATA_PROVIDER_NAME = "validFindMostFrequentMotifsMedianStringDataProvider";

    @DataProvider(name = VALID_FIND_MOST_FREQUENT_MOTIFS_MEDIAN_STRING_DATA_PROVIDER_NAME)
    private Object[][] validFindMostFrequentMotifsMedianStringDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), 1, DnaCollectors.stringToDnaSet("A", "C", "G", "T") },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), 4, DnaCollectors.stringToDnaSet("ACGT") },
                { DnaArray.build(DnaCollectors.stringToDnaList("AAATTGACGCAT", "GACGACCACGTT", "CGTCAGCGCCTG", "GCTGAGCACCGG", "AGTACGGGACAG")), 3, DnaCollectors.stringToDnaSet("ACG", "GAC") },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "ACGT", "ACGT")), 3, DnaCollectors.stringToDnaSet("ACG", "CGT") },
                { DnaArray.build(DnaCollectors.stringToDnaList("ATA", "ACA", "AGA", "AAT", "AAC")), 3, DnaCollectors.stringToDnaSet("AAA") },
                { DnaArray.build(DnaCollectors.stringToDnaList("AAG", "AAT")), 3, DnaCollectors.stringToDnaSet("AAG", "AAT") },
                { testDnaArrayLoader.load("median-string-extra-dataset.dnacol"), 6, DnaCollectors.stringToDnaSet("CGGCGA") }
        };
    }

    static final String INVALID_PROFILE_MOST_PROBABLE_SUB_SEQUENCE_DATA_PROVIDER_NAME = "invalidProfileMostProbableSubSequenceDataProvider";

    @DataProvider(name = INVALID_PROFILE_MOST_PROBABLE_SUB_SEQUENCE_DATA_PROVIDER_NAME)
    private Object[][] invalidProfileMostProbableSubSequenceDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), null},
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), Dna.build("ACG")}
        };
    }

    static final String VALID_PROFILE_MOST_PROBABLE_SUB_SEQUENCE_DATA_PROVIDER_NAME = "validProfileMostProbableSubSequenceDataProvider";

    @DataProvider(name = VALID_PROFILE_MOST_PROBABLE_SUB_SEQUENCE_DATA_PROVIDER_NAME)
    private Object[][] validProfileMostProbableSubSequenceDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), Dna.build("AAAA"), DnaCollectors.stringToDnaSet("AAAA") },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), Dna.build("AAAAA"), DnaCollectors.stringToDnaSet("AAAA") },
                { testDnaArrayLoader.load("profile-most-probable-sub-sequence-sample-dataset.dnacol"), Dna.build("ACCTGTTTATTGCCTAAGTTCCGAACAAACCCAATATAGCCCGAGGGCCT"), DnaCollectors.stringToDnaSet("CCGAG") },
                { testDnaArrayLoader.load("profile-most-probable-sub-sequence-debug-dataset-1.dnacol"), Dna.build("AGCAGCTTTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATCTGAACTGGTTACCTGCCGTGAGTAAAT"), DnaCollectors.stringToDnaSet("AGCAGCTT") },
                { testDnaArrayLoader.load("profile-most-probable-sub-sequence-debug-dataset-2.dnacol"), Dna.build("TTACCATGGGACCGCTGACTGATTTCTGGCGTCAGCGTGATGCTGGTGTGGATGACATTCCGGTGCGCTTTGTAAGCAGAGTTTA"), DnaCollectors.stringToDnaSet("AAGCAGAGTTTA") }
        };
    }

    static final String INVALID_PATTERN_PROBABILITY_DATA_PROVIDER_NAME = "invalidPatternProbabilityDataProvider";

    @DataProvider(name = INVALID_PATTERN_PROBABILITY_DATA_PROVIDER_NAME)
    private Object[][] invalidPatternProbabilityDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), null },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), Dna.build("ACG") },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT")), Dna.build("ACGTA") }
        };
    }

    static final String VALID_PATTERN_PROBABILITY_DATA_PROVIDER_NAME = "validPatternProbabilityDataProvider";

    @DataProvider(name = VALID_PATTERN_PROBABILITY_DATA_PROVIDER_NAME)
    private Object[][] validPatternProbabilityDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.stringToDnaList("AAAA")), Dna.build("AAAA"), 1.0d },
                { DnaArray.build(DnaCollectors.stringToDnaList("AAAA")), Dna.build("TTTT"), 0.0d },
                { DnaArray.build(DnaCollectors.stringToDnaList("AAAA", "AAAA")), Dna.build("AAAA"), 1.0d },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "TGCA")), Dna.build("AAAA"), 0.0d },
                { DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "TGCA")), Dna.build("ACGT"), 0.0625d },
                { testDnaArrayLoader.load("pattern-probability-dataset.dnacol"), Dna.build("ACGGGGATTACC"), 0.000839808d },
                { testDnaArrayLoader.load("pattern-probability-dataset.dnacol"), Dna.build("TCGGGGATTTCC"), 0.0205753d },
                { testDnaArrayLoader.load("pattern-probability-dataset.dnacol"), Dna.build("TCGTGGATTTCC"), 0.0d }
        };
    }
}

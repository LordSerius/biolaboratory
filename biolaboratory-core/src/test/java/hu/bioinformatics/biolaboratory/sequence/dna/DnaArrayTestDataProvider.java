package hu.bioinformatics.biolaboratory.sequence.dna;

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

    static final String DNA_ARRAY_EQUALS_DATA_PROVIDER_NAME = "dnaArrayEqualsDataProvider";

    @DataProvider(name = DNA_ARRAY_EQUALS_DATA_PROVIDER_NAME)
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

    static final String VALID_FIND_MOST_FREQUENT_MOTIFS_EXHAUSTING_DATA_PROVIDER_NAME = "validFindMostFrequentMotifsExhaustingDataProvider";

    @DataProvider(name = VALID_FIND_MOST_FREQUENT_MOTIFS_EXHAUSTING_DATA_PROVIDER_NAME)
    Object[][] validFindMostFrequentMotifsExhaustingDataProvider() {
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

    static final String VALID_FIND_MOST_FREQUENT_MOTIFS_MEDIAN_STRING_DATA_PROVIDER_NAME = "validFindMostFrequentMotifsMedianStringDataProvider";

    @DataProvider(name = VALID_FIND_MOST_FREQUENT_MOTIFS_MEDIAN_STRING_DATA_PROVIDER_NAME)
    Object[][] validFindMostFrequentMotifsMedianStringDataProvider() {
        return new Object[][] {
                {}
        };
    }
}

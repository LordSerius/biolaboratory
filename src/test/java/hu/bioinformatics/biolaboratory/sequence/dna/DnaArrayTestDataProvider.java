package hu.bioinformatics.biolaboratory.sequence.dna;

import com.beust.jcommander.internal.Lists;
import hu.bioinformatics.biolaboratory.testutils.TestDnaCollectionLoader;
import hu.bioinformatics.biolaboratory.utils.DnaCollectors;
import org.testng.annotations.DataProvider;

import javax.inject.Inject;

/**
 * Test data provider for the {@link DnaArrayTest} test class.
 *
 * @author Attila Radi
 */
public class DnaArrayTestDataProvider {

    @Inject
    private TestDnaCollectionLoader testDnaCollectionLoader;

    static final String INVALID_DNA_ARRAY_LIST_DATA_PROVIDER_NAME = "invalidDnaArrayListDataProvider";

    @DataProvider(name = INVALID_DNA_ARRAY_LIST_DATA_PROVIDER_NAME)
    Object[][] invalidDnaArrayListDataProvider() {
        return new Object[][] {
                { null },
                { Lists.newArrayList(Dna.build("A"), null) },
                { DnaCollectors.toDnaList("A", "AC") }
        };
    }

    static final String VALID_DNA_ARRAY_LIST_DATA_PROVIDER_NAME = "validDnaArrayListDataProvider";

    @DataProvider(name = VALID_DNA_ARRAY_LIST_DATA_PROVIDER_NAME)
    Object[][] validDnaArrayListDataProvider() {
        return new Object[][] {
                { DnaCollectors.toDnaList("A", "C", "G", "T"), Lists.newArrayList("A", "C", "G", "T") }
        };
    }

    static final String DNA_ARRAY_COPY_DATA_PROVIDER_NAME = "dnaArrayCopyDataProvider";

    @DataProvider(name = DNA_ARRAY_COPY_DATA_PROVIDER_NAME)
    Object[][] copyDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.toDnaList("A", "C")) }
        };
    }

    static final String DNA_ARRAY_EQUALS_DATA_PROVDER_NAME = "dnaArrayEqualsDataProvider";

    @DataProvider(name = DNA_ARRAY_EQUALS_DATA_PROVDER_NAME)
    Object[][] equalsDataProvider() {
        return new Object[][] {
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), null, false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), Lists.newArrayList(), false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), Lists.newArrayList("A", "C", null), false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), Lists.newArrayList("A", "C", ""), false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), Lists.newArrayList("A", "C", "G"), false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), Lists.newArrayList("A", "C", "G", "T", "AA"), false },
                { DnaArray.build(DnaCollectors.toDnaList("AA", "AC", "AG", "AT")), Lists.newArrayList("AA", "AC", "AG", "CT"), false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), Lists.newArrayList("A", "A", "G", "T"), false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), Lists.newArrayList(Dna.build("A"), Dna.build("C"), null), false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), DnaCollectors.toDnaList("A", "C", "G"), false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), DnaCollectors.toDnaList("A", "C", "G", "T", "AA"), false },
                { DnaArray.build(DnaCollectors.toDnaList("AA", "AC", "AG", "AT")), DnaCollectors.toDnaList("AA", "AC", "AG", "CT"), false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), DnaCollectors.toDnaList("A", "A", "G", "T"), false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), DnaArray.build(DnaCollectors.toDnaList("A", "C", "G")), false },
                { DnaArray.build(DnaCollectors.toDnaList("AA", "AC", "AG", "AT")), DnaArray.build(DnaCollectors.toDnaList("AA", "AC", "AG", "AT", "CA")), false },
                { DnaArray.build(DnaCollectors.toDnaList("AA", "AC", "AG", "AT")), DnaArray.build(DnaCollectors.toDnaList("AA", "AA", "AG", "CT")), false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), DnaArray.build(DnaCollectors.toDnaList("A", "A", "G", "T")), false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), Lists.newArrayList("A", "C", "G", "T"), false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), Lists.newArrayList("A", "G", "C", "T"), false },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), DnaCollectors.toDnaList(Lists.newArrayList("A", "C", "G", "T")), true },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), DnaCollectors.toDnaList(Lists.newArrayList("A", "G", "C", "T")), true },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), DnaArray.build(DnaCollectors.toDnaList(Lists.newArrayList("A", "C", "G", "T"))), true },
                { DnaArray.build(DnaCollectors.toDnaList("A", "C", "G", "T")), DnaArray.build(DnaCollectors.toDnaList(Lists.newArrayList("A", "G", "C", "T"))), true }
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
                { DnaArray.build(DnaCollectors.toDnaList("AAAAAA", "AAAAAA", "AAAAAA", "AAAAAA")), 2, 0, DnaCollectors.toDnaSet("AA") },
                { DnaArray.build(DnaCollectors.toDnaList("AAAAAA")), 2, 1, DnaCollectors.toDnaSet("AA", "AC", "AG", "AT", "CA", "GA", "TA") },
                { DnaArray.build(DnaCollectors.toDnaList("AAAAAA", "ACGTAC")), 2, 3, DnaCollectors.toDnaSet("AA", "AC", "AG", "AT", "CA", "CC", "CG", "CT", "GA", "GC", "GG", "GT", "TA", "TC", "TG", "TT") },
                { DnaArray.build(DnaCollectors.toDnaList("AAAAAA", "CCCCCC", "GGGGGG", "TTTTTT")), 2, 0, DnaCollectors.toDnaSet() },
                { DnaArray.build(DnaCollectors.toDnaList("ATTTGGC", "TGCCTTA", "CGGTATC", "GAAAATT")), 3, 1, DnaCollectors.toDnaSet("ATA", "ATT", "GTT", "TTT") },
                { DnaArray.build(DnaCollectors.toDnaList("ACGT", "ACGT", "ACGT")), 3, 0, DnaCollectors.toDnaSet("ACG", "CGT") },
                { DnaArray.build(DnaCollectors.toDnaList("AAAAA", "AAAAA", "AAAAA")), 3, 1, DnaCollectors.toDnaSet("AAA", "AAC", "AAG", "AAT", "ACA", "AGA", "ATA", "CAA", "GAA", "TAA") },
                { DnaArray.build(DnaCollectors.toDnaList("AAAAA", "AAAAA", "AAAAA")), 3, 3, testDnaCollectionLoader.loadDnaSetFromFile("find-most-frequent-motifs-test-dataset-3-solution.txt") },
                { DnaArray.build(DnaCollectors.toDnaList("AAAAA", "AAAAA", "AACAA")), 3, 0, DnaCollectors.toDnaSet() },
                { DnaArray.build(DnaCollectors.toDnaList("AACAA", "AAAAA", "AAAAA")), 3, 0, DnaCollectors.toDnaSet() },
                { DnaArray.build(DnaCollectors.toDnaList("TCTGAGCTTGCGTTATTTTTAGACC", "GTTTGACGGGAACCCGACGCCTATA", "TTTTAGATTTCCTCAGTCCACTATA", "CTTACAATTTCGTTATTTATCTAAT", "CAGTAGGAATAGCCACTTTGTTGTA", "AAATCCATTAAGGAAAGACGACCGT")), 5, 2, testDnaCollectionLoader.loadDnaSetFromFile("find-most-frequent-motifs-extra-dataset-solution.txt") }
        };
    }
}

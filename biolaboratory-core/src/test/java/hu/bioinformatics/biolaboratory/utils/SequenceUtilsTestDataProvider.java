package hu.bioinformatics.biolaboratory.utils;

import org.testng.annotations.DataProvider;

import static hu.bioinformatics.biolaboratory.utils.SequenceUtils.EQUAL;
import static hu.bioinformatics.biolaboratory.utils.SequenceUtils.GREATER;
import static hu.bioinformatics.biolaboratory.utils.SequenceUtils.SMALLER;

/**
 * Test data provider for {@link SequenceUtilsTest} test class.
 *
 * @author Attila Radi
 */
public class SequenceUtilsTestDataProvider {

    static final String INVALID_HAMMING_DISTANCE_DATA_PROVIDER_NAME = "invalidHammingDistanceDataProvider";

    @DataProvider(name = INVALID_HAMMING_DISTANCE_DATA_PROVIDER_NAME)
    Object[][] invalidHammingDistanceDataProvider() {
        return new Object[][] {
                { null, "ABCD" },
                { "ABCD", null },
                { "ABC", "ABCD" },
                { "ABCD", "ABC" }
        };
    }

    static final String VALID_HAMMING_DISTANCE_DATA_PROVIDER_NAME = "validHammingDistanceDataProvider";

    @DataProvider(name = VALID_HAMMING_DISTANCE_DATA_PROVIDER_NAME)
    Object[][] validHammingDistanceDataProvider() {
        return new Object[][] {
                { "", "", 0 },
                { "ABCD", "ABCD", 0 },
                { "ABCD", "ABCE", 1 },
                { "ABCD", "DCBA", 4 }
        };
    }

    static final String INVALID_HAMMING_DISTANCE_MISMATCH_COMPARATOR_DATA_PROVIDER_NAME = "invalidHammingDistanceMismatchComparatorDataProvider";

    @DataProvider(name = INVALID_HAMMING_DISTANCE_MISMATCH_COMPARATOR_DATA_PROVIDER_NAME)
    Object[][] invalidHammingDistanceThresholdComparatorDataProvider() {
        return new Object[][] {
                { null, "ABCD", 0 },
                { "ABCD", null, 1 },
                { "ABC", "ABCD", 2 },
                { "ABCD", "ABC", 3 },
                { "ABCD", "ABCD", -1 }
        };
    }

    static final String VALID_HAMMING_DISTANCE_MISMATCH_COMPARATOR_DATA_PROVIDER_NAME = "validHammingDistanceMismatchComparatorDataProvider";

    @DataProvider(name = VALID_HAMMING_DISTANCE_MISMATCH_COMPARATOR_DATA_PROVIDER_NAME)
    Object[][] validHammingDistanceThresholdComparatorDataProvider() {
        return new Object[][] {
                { "", "", 0, EQUAL },
                { "", "", 1, SMALLER },
                { "ABCD", "ABCD", 0, EQUAL },
                { "ABCD", "ABCD", 1, SMALLER },
                { "ABCD", "BBCD", 0, GREATER },
                { "ABCD", "BBCD", 1, EQUAL },
                { "ABCD", "BBCD", 2, SMALLER }
        };
    }
}

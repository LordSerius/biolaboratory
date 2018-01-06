package hu.bioinformatics.biolaboratory.utils;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.fail;

/**
 * Unit tests for {@link SequenceUtils} class.
 *
 * @author Attila Radi
 */
@Test(dataProviderClass = SequenceUtilsTestDataProvider.class)
public class SequenceUtilsTest {

    @Test(dataProvider = SequenceUtilsTestDataProvider.INVALID_HAMMING_DISTANCE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldHammingDistanceThrowException(String sequence, String otherSequence) {
        SequenceUtils.hammingDistance(sequence, otherSequence);
    }

    @Test(dataProvider = SequenceUtilsTestDataProvider.VALID_HAMMING_DISTANCE_DATA_PROVIDER_NAME)
    public void shouldHammingDistanceReturn(String sequence, String otherSequence, int controlHammingDistance) {
        int hammingDistance = SequenceUtils.hammingDistance(sequence, otherSequence);
        assertThat(hammingDistance, is(equalTo(controlHammingDistance)));
    }

    @Test(dataProvider = SequenceUtilsTestDataProvider.INVALID_HAMMING_DISTANCE_MISMATCH_COMPARATOR_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldHammingDistanceMismatchComparatorThrowException(String sequence, String otherSequence, int mismatch) {
        SequenceUtils.hammingDistanceMismatchComparator(sequence, otherSequence, mismatch);
    }

    @Test(dataProvider = SequenceUtilsTestDataProvider.VALID_HAMMING_DISTANCE_MISMATCH_COMPARATOR_DATA_PROVIDER_NAME)
    public void shouldHammingDistanceMismatchComparatorReturn(String sequence, String otherSequence, int mismatch, int controlComparisionValue) {
        int comparisionValue = SequenceUtils.hammingDistanceMismatchComparator(sequence, otherSequence, mismatch);
        assertThat(comparisionValue, is(equalTo(controlComparisionValue)));
    }
}
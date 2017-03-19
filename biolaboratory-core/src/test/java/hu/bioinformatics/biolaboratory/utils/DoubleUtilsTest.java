package hu.bioinformatics.biolaboratory.utils;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

/**
 * Unit tests for {@link DoubleUtils}
 *
 * @author Attila Radi
 */
@Test(dataProviderClass = DoubleUtilsTestDataProvider.class)
public class DoubleUtilsTest {

    @Test(dataProvider = DoubleUtilsTestDataProvider.INVALID_COMPARE_WITH_ERROR_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCompareWithErrorThrowException(double leftHand, double rightHand, double error) {
        DoubleUtils.compareWithError(leftHand, rightHand, error);
        fail();
    }

    @Test(dataProvider = DoubleUtilsTestDataProvider.VALID_COMPARE_WITH_ERROR_DATA_PROVIDER_NAME)
    public void shouldCompareWithErrorReturn(double leftHand, double rightHand, double error, int controlAnswer) {
        int answer = DoubleUtils.compareWithError(leftHand, rightHand, error);
        assertThat(answer, is(equalTo(controlAnswer)));
    }

    @Test(dataProvider = DoubleUtilsTestDataProvider.COMPARE_DATA_PROVIDER_NAME)
    public void shouldCompareReturn(double leftHand, double rightHand, int controlAnswer) {
        int answer = DoubleUtils.compare(leftHand, rightHand);
        assertThat(answer, is(equalTo(controlAnswer)));
    }
}
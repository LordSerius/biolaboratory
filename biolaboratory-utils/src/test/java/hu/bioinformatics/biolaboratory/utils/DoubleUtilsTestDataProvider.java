package hu.bioinformatics.biolaboratory.utils;

import org.testng.annotations.DataProvider;

/**
 * Provides test data for {@link DoubleUtilsTest}.
 *
 * @author Attila Radi
 */
public class DoubleUtilsTestDataProvider {

    private static final double DEFAULT_PRECISION = 0.000001d;
    private static final double MAXIMUM_ERROR = 0.001d;
    private static final double EPSILON = 0.0000000000001d;

    static final String INVALID_COMPARE_WITH_ERROR_DATA_PROVIDER_NAME = "invalidCompareWithErrorDataProviderName";

    @DataProvider(name = INVALID_COMPARE_WITH_ERROR_DATA_PROVIDER_NAME)
    private Object[][] invalidCompareWithErrorDataProvider() {
        return new Object[][] {
                { 0.0, 0.0, MAXIMUM_ERROR * 10 },
                { 0.0, 0.0, -MAXIMUM_ERROR * 10 },
                { 0.0, 0.0, MAXIMUM_ERROR + EPSILON},
                { 0.0, 0.0, -(MAXIMUM_ERROR + EPSILON) }
        };
    }

    static final String VALID_COMPARE_WITH_ERROR_DATA_PROVIDER_NAME = "validCompareWithErrorDataProviderName";

    @DataProvider(name = VALID_COMPARE_WITH_ERROR_DATA_PROVIDER_NAME)
    private Object[][] validCompareWithErrorDataProvider() {
        return new Object[][] {
                { 0.0d, 0.0d, MAXIMUM_ERROR, 0 },
                { 0.0d, MAXIMUM_ERROR, MAXIMUM_ERROR, 0 },
                { 0.0d, MAXIMUM_ERROR - EPSILON, MAXIMUM_ERROR, 0 },
                { 0.0d, MAXIMUM_ERROR + EPSILON, MAXIMUM_ERROR, 1 },
                { MAXIMUM_ERROR, 0.0d, MAXIMUM_ERROR, 0 },
                { MAXIMUM_ERROR - EPSILON, 0.0d, MAXIMUM_ERROR, 0 },
                { MAXIMUM_ERROR + EPSILON, 0.0d, MAXIMUM_ERROR, -1 },
                { 0.0d, 0.0d, 0.0d, 0 },
                { 0.0d, EPSILON, 0.0d, 1 },
                { EPSILON, 0.0d, 0.0d, -1 }
        };
    }

    static final String COMPARE_DATA_PROVIDER_NAME = "compareDataProviderName";

    @DataProvider(name = COMPARE_DATA_PROVIDER_NAME)
    private Object[][] compareDataProvider() {
        return new Object[][] {
                { 0.0d, 0.0d, 0 },
                { -1.0d, 1.0d, 1},
                { 1.0d, -1.0d, -1 },
                { 0.0d, DEFAULT_PRECISION, 0  },
                { 0.0d, DEFAULT_PRECISION - EPSILON, 0  },
                { 0.0d, DEFAULT_PRECISION + EPSILON, 1  },
                { DEFAULT_PRECISION, 0.0, 0  },
                { DEFAULT_PRECISION + EPSILON, 0.0, -1  },
                { DEFAULT_PRECISION - EPSILON, 0.0, 0  },
                { 0.0, DEFAULT_PRECISION + EPSILON, 1  },
                { 0.0, DEFAULT_PRECISION - EPSILON, 0  },
                { 0.0, MAXIMUM_ERROR, 1 },
                { MAXIMUM_ERROR, 0.0, -1 }
        };
    }
}

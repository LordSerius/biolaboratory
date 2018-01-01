package hu.bioinformatics.biolaboratory.utils;

import com.google.common.base.Preconditions;

/**
 * Provides methods to handle double values. It defines a default error precision for comparision which is 1e-6.
 *
 * @author Attila Radi
 */
public class DoubleUtils {
    public static final double LOG_2 = Math.log(2);

    private static final double DEFAULT_PRECISION = 0.000001d;
    private static final double MAXIMUM_ERROR = 0.001d;

    private DoubleUtils() {}

    /**
     * Compares two double values with maximum 1e-6 error.
     *
     * @param leftHand The left hand of the comparision.
     * @param rightHand The right hand of the comparision.
     * @return  0, if the difference of the two values are between +/- 1e-6.
     *          -1, if the leftHand is bigger at least 1e-6 than the rightHand.
     *          1, if the rightHand is bigger at least 1e-6 than the leftHand.
     */
    public static int compare(final double leftHand, final double rightHand) {
        return compareWithError(leftHand, rightHand, DEFAULT_PRECISION);
    }

    /**
     * Compares two double values with maximum error. If the error is bigger than the maximum possible error (0.001)
     * throws an {@link IllegalArgumentException}.
     *
     * @param leftHand The left hand of the comparision.
     * @param rightHand The right hand of the comparision.
     * @param error
     * @return  0, if the difference of the two values are between +/- error.
     *          -1, if the leftHand is bigger at least error than the rightHand.
     *          1, if the rightHand is bigger at least error than the leftHand.
     * @throws IllegalArgumentException If the error is bigger than 0.001.
     */
    public static int compareWithError(final double leftHand, final double rightHand, final double error) {
        double normalizedError = validateError(error);
        double difference = rightHand - leftHand;
        if (-normalizedError <= difference && difference <= normalizedError) {
            return 0;
        } else if (normalizedError < difference) {
            return 1;
        }
        return -1;
    }

    private static double validateError(final double error) {
        double normalizedError = Math.abs(error);
        Preconditions.checkArgument(normalizedError <= MAXIMUM_ERROR, "Error should not be bigger than " + MAXIMUM_ERROR);
        return normalizedError;
    }
}

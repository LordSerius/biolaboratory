package hu.bioinformatics.biolaboratory.utils.datastructures;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import hu.bioinformatics.biolaboratory.utils.DoubleUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static hu.bioinformatics.biolaboratory.utils.Validation.validateCollection;
import static hu.bioinformatics.biolaboratory.utils.Validation.validateVarargs;

/**
 * TODO: make test cases
 *
 * @author Attila Radi
 */
public class ProbabilityMap<K> {
    private static final double DEFAULT_PRECISION = 0.000001d;
    private static final double MAXIMUM_PRECISION = 0.001d;

    private final ImmutableMap<K, Double> probabilityMap;
    private final double error;

    /**
     * Builds a new empty {@link ProbabilityMap}.
     *
     * @param <K> The key values of the occurrences.
     * @return An empty {@link ProbabilityMap}.
     */
    public static <K> ProbabilityMap<K> build() {
        return new ProbabilityMap<>();
    }

    /**
     * Builds a new {@link ProbabilityMap} about the given probabilities. The probabilities should contain
     * values between 0.0 and 1.0. The sum of probabilities should be 1.0 +/- default error.
     *
     * @param probabilities The probability map.
     * @param <K> The key type of the probabilities.
     * @return A new {@link ProbabilityMap} map with the given probabilities.
     * @throws IllegalArgumentException If the probabilities are not between 0.0 and 1.0.
     */
    public static <K> ProbabilityMap<K> build(final Map<K, Double> probabilities) {
        return build(probabilities, DEFAULT_PRECISION);
    }

    /**
     * Builds a new {@link ProbabilityMap} about the given probabilities. The probabilities should contain
     * values between 0.0 and 1.0. The sum of probabilities should be 1.0 +/- error.
     *
     * @param probabilities The probability map.
     * @param error The accepted error level.
     * @param <K> The key type of the probabilities.
     * @return A new {@link ProbabilityMap} map with the given probabilities.
     * @throws IllegalArgumentException If the probabilities are not between 0.0 and 1.0.
     */
    public static <K> ProbabilityMap<K> build(final Map<K, Double> probabilities, final double error) {
        Preconditions.checkArgument(Math.abs(error) <= MAXIMUM_PRECISION, "Error should not be less than 0.001");
        Preconditions.checkArgument(probabilities == null
                        || probabilities.entrySet().stream().allMatch(entry -> entry.getKey() != null
                                                                                && entry.getValue() != null
                                                                                && entry.getValue() >= 0.0
                                                                                && entry.getValue() <= 1.0),
                "Probabilities should be between 0.0 and 1.0");
        double sum = probabilities.values().stream().mapToDouble(value -> value).sum();
        Preconditions.checkArgument(sum >= 0.0 - error && sum <= 1.0,
            "The sum of the probabilities should be between 0.0 and 1.0");
        return new ProbabilityMap<K>(probabilities, error);
    }

    /**
     * Default constructor creates an empty {@link Map}.
     */
    private ProbabilityMap() {
        probabilityMap = ImmutableMap.of();
        error = DEFAULT_PRECISION;
    }

    /**
     * Constructor which pass through an {@link Map} with K type key and {@link Double} type value. If the {@link Map}
     * is null, the constructor provides an empty {@link Map}.
     *
     * @param probabilityMap A key - double pair map.
     */
    private ProbabilityMap(final Map<K, Double> probabilityMap, final double error) {
        this.probabilityMap = probabilityMap == null ? ImmutableMap.of() : ImmutableMap.copyOf(probabilityMap);
        this.error = error;
    }

    /**
     * Creates the copy of the {@link ProbabilityMap}.
     *
     * @return The copy of the {@link ProbabilityMap}.
     */
    public ProbabilityMap<K> copy() {
        return new ProbabilityMap<>(probabilityMap, error);
    }

    /**
     * Returns with the inner key - probability pairs.
     *
     * @return The inner probability {@link Map}.
     */
    public final ImmutableMap<K, Double> getProbabilitiesInMap() {
        return probabilityMap;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this
                || obj != null
                && obj.getClass().equals(getClass())
                && compareOccurrenceMapContent(((ProbabilityMap<K>) obj).probabilityMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(probabilityMap);
    }

    private boolean compareOccurrenceMapContent(ImmutableMap<K, Double> otherProbabilityMap) {
        return otherProbabilityMap.size() == probabilityMap.size() && probabilityMap.entrySet().stream().allMatch(entry -> {
            Double otherValue = otherProbabilityMap.get(entry.getKey());
            return otherValue != null && DoubleUtils.compareWithError(entry.getValue(), otherValue, error) == 0;
        });
    }

    @Override
    public String toString() {
        String printString = "[ ";
        int i = 0;
        int size = probabilityMap.size();
        for (Map.Entry<K, Double> entry : probabilityMap.entrySet()) {
            printString = printString + entry.getKey() + " -> " + entry.getValue();
            if (i < size - 1) {
                printString = printString + ", ";
            }
            i++;
        }
        printString = printString + " ]";
        return printString;
    }

    /**
     * Get the probability of the key.
     *
     * @param key The probability key.
     * @return The probability for the specific key.
     */
    public final double getProbability(final K key) {
        return sumProbabilities(key);
    }

    /**
     * Get the probabilities of the target keys.
     *
     * @param keys The desired keys.
     * @return The sum of the target key probabilities in the {@link ProbabilityMap}.
     */
    @SafeVarargs
    public final double sumProbabilities(final K... keys) {
        return sumProbabilitiesAboutSet(Sets.newHashSet(validateVarargs(keys)));
    }

    /**
     * Get the probabilities of the target keys.
     *
     * @param keySet The desired keys.
     * @return The sum of the target key probabilities in the {@link ProbabilityMap}.
     */
    public final double sumProbabilitiesAboutSet(final Set<K> keySet) {
        return innerSumProbabilities(validateCollection(keySet));
    }

    private double innerSumProbabilities(final Set<K> keySet) {
        return probabilityMap.entrySet().stream()
                .filter(occurrence -> keySet.contains(occurrence.getKey()))
                .mapToDouble(Map.Entry::getValue)
                .sum();
    }
}

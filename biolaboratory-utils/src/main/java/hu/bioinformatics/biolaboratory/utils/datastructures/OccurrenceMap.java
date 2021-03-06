package hu.bioinformatics.biolaboratory.utils.datastructures;

import com.google.common.collect.Sets;
import hu.bioinformatics.biolaboratory.utils.ArgumentValidator;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotNegativeNumber;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotNullArgument;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkPositiveNumber;

/**
 * A data structure which contains different not null keys and the occurrence numbers.
 * 
 * @author Attila Radi
 *
 */
public class OccurrenceMap<K> {

    protected Map<K, Integer> occurrenceMap = null;

    /**
     * Builds a new empty {@link OccurrenceMap}.
     *
     * @param <K> The key values of the occurrences.
     * @return An empty {@link OccurrenceMap}.
     */
    public static <K> OccurrenceMap<K> build() {
        return new OccurrenceMap<>();
    }

    /**
     * Builds a new {@link OccurrenceMap} with the given initial occurrences. The occurrences should not be
     * null or negative numbers.
     *
     * @param occurrences The initial occurrence map.
     * @param <K> The key type of the occurrences.
     * @return A new {@link OccurrenceMap} map with the given occurrences.
     * @throws IllegalArgumentException If occurrences contain null keys, or negative values.
     */
    public static <K> OccurrenceMap<K> build(final Map<K, Integer> occurrences) {
        if (occurrences == null) return new OccurrenceMap<>(null);
        checkArgument(occurrences.entrySet().stream()
                        .allMatch(entry -> entry.getKey() != null && (entry.getValue() == null || entry.getValue() >= 0)),
                "Occurrences should not contain null keys or negative elements");
        return new OccurrenceMap<>(
                occurrences.entrySet().stream()
                                        .filter(entry -> entry.getValue() != null && entry.getValue() > 0)
                                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    /**
     * Default constructor creates an empty {@link Map}.
     */
    protected OccurrenceMap() {
        occurrenceMap = new HashMap<>();
    }

    /**
     * Constructor which pass through an {@link Map} with K type key and {@link Integer} type value. If the {@link Map}
     * is null, the constructor provides an empty {@link Map}.
     *
     * @param occurrenceMap A key - integer pair map.
     */
    protected OccurrenceMap(final Map<K, Integer> occurrenceMap) {
        this.occurrenceMap = occurrenceMap == null ? new HashMap<>() : new HashMap<>(occurrenceMap);
    }

    /**
     * Creates the copy of the {@link OccurrenceMap}.
     *
     * @return The copy of the {@link OccurrenceMap}.
     */
    public OccurrenceMap<K> copy() {
        return new OccurrenceMap<>(occurrenceMap);
    }

    /**
     * Get the copy of inside {@link Map} which contains the keys and the occurrences. By definition, elements which
     * are not inside the map returns with 0.
     * 
     * @return The inner occurrence {@link Map}.
     */
    public final Map<K, Integer> getOccurrencesInMap() {
        Map<K, Integer> copyOccurrenceMap = new HashMap<>();
        copyOccurrenceMap.putAll(occurrenceMap);
        return copyOccurrenceMap;
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj == this
                || obj != null
                && obj.getClass().equals(getClass())
                && compareOccurrenceMapContent(((OccurrenceMap<K>) obj).occurrenceMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(occurrenceMap);
    }

    /**
     * Compares the occurrence map entries with the given map.
     *
     * @param otherOccurrenceMap A key - number pair {@link Map}.
     * @return True if the two occurrence {@link Map} elements are identical in key and value.
     */
    protected final boolean compareOccurrenceMapContent(Map<K, Integer> otherOccurrenceMap) {
        return otherOccurrenceMap.size() == occurrenceMap.size() && occurrenceMap.entrySet().stream().allMatch(entry -> {
            Integer otherValue = otherOccurrenceMap.get(entry.getKey());
            return otherValue != null && entry.getValue().equals(otherValue);
        });
    }

    @Override
    public String toString() {
        String printString = "[ ";
        int i = 0;
        int size = occurrenceMap.size();
        for (Map.Entry<K, Integer> entry : occurrenceMap.entrySet()) {
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
     * Get the occurrences of the key.
     *
     * @param key The occurrence key.
     * @return The occurrence number for the specific key.
     * @throws IllegalArgumentException If key is not valid.
     */
    public final int getOccurrence(final K key) {
        return sumOccurrences(key);
    }

    /**
     * Get the occurrences of the target keys.
     *
     * @param keys The desired keys.
     * @return The sum of the target key values in the {@link OccurrenceMap}.
     * @throws IllegalArgumentException If keys are not valid.
     */
    @SafeVarargs
    public final int sumOccurrences(final K... keys) {
        return innerSumOccurrences(Sets.newHashSet(validateKeys(keys)));
    }

    /**
     * Get the total number of occurrences inside the {@link OccurrenceMap}.
     *
     * @return The sum of all occurrences.
     */
    public final int sumTotalOccurrence() {
        return innerSumOccurrences(occurrenceMap.keySet());
    }

    /**
     * Get the occurrences of the target keys.
     *
     * @param keySet The desired keys.
     * @return The sum of the target key values in the {@link OccurrenceMap}.
     * @throws IllegalArgumentException If keys inside keySet is not valid.
     */
    public final int sumOccurrences(final Set<K> keySet) {
        return innerSumOccurrences(validateKeySet(keySet));
    }

    private int innerSumOccurrences(final Set<K> keySet) {
        return occurrenceMap.entrySet().stream()
                .filter(occurrence -> keySet.contains(occurrence.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    /**
     * Get the occurrence ratio for the target key.
     *
     * @param key The occurrence key.
     * @return The occurrence of the target key.
     * @throws IllegalArgumentException If key is not valid.
     */
    public double occurrenceRatio(final K key) {
        return accumulatedOccurrenceRatio(key);
    }

    /**
     * Get the accumulated occurrence ratio for the given keys.
     *
     * @param keys The desired keys.
     * @return The accumulated ratio of the target key values in the {@link OccurrenceMap}.
     * @throws IllegalArgumentException If keys are not valid.
     */
    @SafeVarargs
    public final double accumulatedOccurrenceRatio(final K... keys) {
        return innerAccumulatedOccurrenceRatio(Sets.newHashSet(validateKeys(keys)));
    }

    /**
     * Get the accumulated occurrence ratio for the given key set.
     *
     * @param keySet The desired keys.
     * @return The accumulated ratio of the target key values in the {@link OccurrenceMap}.
     * @throws IllegalArgumentException If keySet elements are not valid.
     */
    public final double accumulatedOccurrenceRatio(final Set<K> keySet) {
        return innerAccumulatedOccurrenceRatio(validateKeySet(keySet));
    }

    private double innerAccumulatedOccurrenceRatio(final Set<K> keySet) {
        return innerOccurrenceRatio(keySet).entrySet().stream()
                .mapToDouble(Map.Entry::getValue)
                .sum();
    }

    /**
     * Get the occurrence ratio for all keys individual in a {@link Map}.
     *
     * @return A {@link Map} about all key and ratio.
     */
    public final Map<K, Double> allOccurrenceRatios() {
        return innerOccurrenceRatio(occurrenceMap.keySet());
    }

    /**
     * Get the occurrence ratio each key individual in a {@link Map}.
     *
     * @param keys The desired keys.
     * @return A {@link Map} about each key and ratio.
     * @throws IllegalArgumentException If keys are invalid.
     */
    @SafeVarargs
    public final Map<K, Double> occurrenceRatios(final K... keys) {
        return innerOccurrenceRatio(Sets.newHashSet(validateKeys(keys)));
    }

    /**
     * Get the occurrence ratio each key individual in a {@link Map}.
     *
     * @param keySet The desired key set.
     * @return A {@link Map} about each key and ratio.
     * @throws IllegalArgumentException If keySet elements are not valid.
     */
    public Map<K, Double> occurrenceRatios(final Set<K> keySet) {
        return innerOccurrenceRatio(validateKeySet(keySet));
    }

    private Map<K, Double> innerOccurrenceRatio(final Set<K> keySet) {
        if (occurrenceMap.isEmpty()) {
            HashMap<K, Double> nullMap = new HashMap<>();
            nullMap.put(null, 1.0);
            return nullMap;
        }

        int totalOccurrence = sumTotalOccurrence();
        Map<K, Double> zeroRatio = keySet.stream()
                .collect(Collectors.toMap(key -> key, key -> 0.0));
        return new HashMap<>(totalOccurrence == 0 ? zeroRatio :
                zeroRatio.entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> (double) getOccurrence(entry.getKey()) / totalOccurrence)));
    }

    /**
     * Choose the occurrences about the given keys and return a new {@link OccurrenceMap}.
     *
     * @param keys The keys for the occurrences to choose.
     * @return The chosen occurrences.
     * @throws IllegalArgumentException If keys are not valid.
     */
    public OccurrenceMap<K> subSet(final K... keys) {
        return innerSubSet(Sets.newHashSet(validateKeys(keys)));
    }

    /**
     * Choose the occurrences about the given keys and return a new {@link OccurrenceMap}.
     *
     * @param keySet The keys for the occurrences to choose.
     * @return The chosen occurrences.
     * @throws IllegalArgumentException If elements in keySet are not valid.
     */
    public OccurrenceMap<K> subSet(final Set<K> keySet) {
        return innerSubSet(validateKeySet(keySet));
    }

    private OccurrenceMap<K> innerSubSet(final Set<K> keySet) {
        return filter(entry -> keySet.contains(entry.getKey()));
    }

    /**
     * Converts the {@link OccurrenceMap} to a {@link CountableOccurrenceMap}. The element set will be limited with
     * the given elements of the input {@link OccurrenceMap}.
     *
     * @return The converted {@link CountableOccurrenceMap}.
     */
    public CountableOccurrenceMap<K> toCountable() {
        return toCountableWith();
    }

    /**
     * Converts the {@link OccurrenceMap} to a {@link CountableOccurrenceMap}, and adds all given element to the set
     * with 0 initial occurrence.
     *
     * @param zeroElements The keys with zero occurrences.
     * @return The converted {@link CountableOccurrenceMap}.
     * @throws IllegalArgumentException If zeroElements contains null element.
     */
    @SafeVarargs
    public final CountableOccurrenceMap<K> toCountableWith(final K... zeroElements) {
        return innerToCountableWithSet(Sets.newHashSet(validateKeys(zeroElements)));
    }

    /**
     * Converts the {@link OccurrenceMap} to a {@link CountableOccurrenceMap}, and adds all given set element to the set
     * with 0 initial occurrence.
     *
     * @param zeroElementSet The keys with zero occurrences.
     * @return The converted {@link CountableOccurrenceMap}.
     * @throws IllegalArgumentException If zeroElementSet contains null element.
     */
    public CountableOccurrenceMap<K> toCountableWithSet(final Set<K> zeroElementSet) {
        return innerToCountableWithSet(validateKeySet(zeroElementSet));
    }

    private CountableOccurrenceMap<K> innerToCountableWithSet(final Set<K> zeroElementSet) {
        HashMap<K, Integer> extendedOccurrenceMap = new HashMap<>(occurrenceMap);
        zeroElementSet.forEach(key -> extendedOccurrenceMap.put(key, 0));
        return CountableOccurrenceMap.build(extendedOccurrenceMap);
    }

    /**
     * Immutable operation for merging an other {@link OccurrenceMap} with the current
     * {@link OccurrenceMap}.
     * 
     * @param otherOccurrenceMap The other {@link OccurrenceMap} to merge with.
     * @return A new {@link OccurrenceMap} with the merged values.
     * @throws IllegalArgumentException If otherOccurrenceMap is null.
     */
    public OccurrenceMap<K> merge(final OccurrenceMap<K> otherOccurrenceMap) {
        return filterMerge(otherOccurrenceMap, entry -> true);
    }

    /**
     * Merging and filtering the merged result about the other {@link OccurrenceMap} and the current
     * {@link OccurrenceMap}.
     *
     * @param otherOccurrenceMap The other {@link OccurrenceMap} to merge with.
     * @param filterPredicate The filtering predicate.
     * @return A new {@link OccurrenceMap} with the merged and filtered values.
     * @throws IllegalArgumentException If otherOccurrenceMap is null.
     * @throws IllegalArgumentException If filterPredicate is null.
     */
    public OccurrenceMap<K> filterMerge(final OccurrenceMap<K> otherOccurrenceMap, final Predicate<Map.Entry<K, Integer>> filterPredicate) {
        checkNotNullArgument("Filter predicate", filterPredicate);
        return new OccurrenceMap<>(filterMergeOccurrences(otherOccurrenceMap, filterPredicate.and(entry -> entry.getValue() > 0)));
    }

    /**
     * Merging and filtering the merged result about the other {@link OccurrenceMap} and the current
     * {@link OccurrenceMap}.
     *
     * @param otherOccurrenceMap The other {@link OccurrenceMap} to merge with.
     * @param filterPredicate The filtering predicate.
     * @return A new {@link Map} with the merged and filtered values.
     * @throws IllegalArgumentException If otherOccurrenceMap is null.
     * @throws IllegalArgumentException If filterPredicate is null.
     */
    protected final Map<K, Integer> filterMergeOccurrences(final OccurrenceMap<K> otherOccurrenceMap, final Predicate<Map.Entry<K, Integer>> filterPredicate) {
        checkNotNullArgument("Other occurrence map", otherOccurrenceMap);
        checkNotNullArgument("Filter predicate", filterPredicate);
        Map<K, Integer> mergedOccurrences = new HashMap<>();
        Map<K, Integer> otherOccurrences = otherOccurrenceMap.getOccurrencesInMap();
        for (Map.Entry<K, Integer> entry : occurrenceMap.entrySet()) {
            K key = entry.getKey();
            int mergeValue = entry.getValue() + Optional.ofNullable(otherOccurrences.remove(key)).orElse(0);
            if (filterPredicate.test(new AbstractMap.SimpleEntry<>(key, mergeValue))) {
                mergedOccurrences.put(key, mergeValue);
            }
        }
        for (Map.Entry<K, Integer> entry : otherOccurrences.entrySet()) {
            if (filterPredicate.test(entry)) {
                mergedOccurrences.put(entry.getKey(), entry.getValue());
            }
        }
        return mergedOccurrences;
    }
    
    /**
     * Decreases the occurrence of the target key by 1. Remove if the occurrence reaches
     * zero. If the key is not inside the map, do nothing and returns with 0.
     * 
     * @param key The target key.
     * @return The number of occurrence of the target key after the decreasing.
     * @throws IllegalArgumentException If key is null or does not exist.
     */
    public int decrease(final K key) {
        return subtract(key, 1);
    }

    /**
     * Decreases the occurrence of the target key by <i>number</i>. Remove if the occurrence reaches zero.
     *
     * @param key The target key.
     * @param number The number of decreased by.
     * @return The number of occurrence of the target key after the subtracting.
     * @throws IllegalArgumentException If key is null.
     * @throws IllegalArgumentException If number is negative.
     * @throws IllegalArgumentException If decreasing result smaller than 0.
     */
    public synchronized int subtract(final K key, final int number) {
        validateKey(key);
        validateInputNumber(number);
        int occurrence = occurrenceMap.getOrDefault(key, 0);
        if (number == 0) return occurrence;

        int difference = occurrence - number;
        checkArgument(difference >= 0, "Cannot subtract more occurrence than existing");
        if (difference == 0) {
            occurrenceMap.remove(key);
        } else {
            occurrenceMap.put(key, difference);
        }
        return difference;
    }
    
    /**
     * Increase the occurrence of the target key by 1. Adds the key if it is not inside the collection.
     * 
     * @param key The target key.
     * @return The number of occurrence of the target key after the increasing.
     * @throws IllegalArgumentException If key is null.
     */
    public int increase(final K key) {
        return add(key, 1);
    }

    /**
     * Increase the occurrence of the target key by <i>number</i>. Adds the key if it is not inside the collection.
     *
     * @param key The target key.
     * @param number The number of increased by.
     * @return The number of occurrence of the target key after the adding.
     * @throws IllegalArgumentException Iy key is null.
     * @throws IllegalArgumentException If number is negative.
     */
    public synchronized int add(final K key, final int number) {
        validateKey(key);
        validateInputNumber(number);
        int occurrence = occurrenceMap.getOrDefault(key, 0);
        if (number == 0) return occurrence;
        int sum = occurrence + number;
        occurrenceMap.put(key, sum);
        return sum;
    }

    /**
     * Validate the input keys.
     *
     * @param keys The keys to validate.
     * @return The same keys if they are valid.
     * @throws IllegalArgumentException If the keys contain null element.
     */
    @SafeVarargs
    protected final K[] validateKeys(final K... keys) {
        Arrays.stream(ArgumentValidator.checkNotNullVarargs(keys)).forEach(this::validateKey);
        return keys;
    }

    /**
     * Validate the input key set.
     *
     * @param keySet The key set to validate.
     * @return The same key set if it is valid.
     * @throws IllegalArgumentException If the key set contains null elements.
     */
    protected Set<K> validateKeySet(final Set<K> keySet) {
        ArgumentValidator.checkNotNullCollection(keySet).forEach(this::validateKey);
        return keySet;
    }

    /**
     * Validates the given key. Return with the same if it is valid.
     *
     * @param key The key to validate.
     * @return The same value if it is valid.
     * @throws IllegalArgumentException If the key is null.
     */
    protected K validateKey(final K key) {
        return checkNotNullArgument("Key", key);
    }

    /**
     * Validates the given number. Throws an exception if it is lesser than 0.
     *
     * @param number The number to validate.
     * @throws IllegalArgumentException If number is lesser than 0.
     */
    protected void validateInputNumber(final int number) {
        checkNotNegativeNumber("Number", number);
    }
    
    /**
     * Get the most frequent occurrences from the collection.
     * 
     * @return The most frequent keys in the collection.
     */
    public Set<K> filterMostFrequentOccurrences() {
        if (occurrenceMap.isEmpty()) return Collections.emptySet();
        return filterGreaterOrEqualsOccurrences(maximumOccurrenceValue());
    }

    /**
     * Returns the maximum occurrence value.
     *
     * @return The maximum occurrence value
     */
    public int maximumOccurrenceValue() {
        return occurrenceMap.values().stream()
                .mapToInt(occurrence -> occurrence)
                .max()
                .orElse(0);
    }

    /**
     * Returns the minimum occurrence value.
     *
     * @return It is always 0.
     */
    public int minimumOccurrenceValue() {
        return 0;
    }

    /**
     * Get the occurrences which are greater than the threshold.
     *
     * @param value The condition threshold where from the occurrences should equals.
     * @return All keys which occurrence are equals than the value.
     * @throws IllegalArgumentException If value is smaller than 1.
     */
    public Set<K> filterEqualsOccurrences(final int value) {
        validateThreshold(value);
        return filterOccurrences(entry -> entry.getValue() == value).keySet();
    }

    /**
     * Get the occurrences which are greater than the threshold.
     *
     * @param threshold The condition threshold where from the occurrences should greater.
     * @return All keys which occurrence are greater than the threshold.
     * @throws IllegalArgumentException If threshold is smaller than 1.
     */
    public Set<K> filterGreaterOccurrences(final int threshold) {
        validateThreshold(threshold);
        return filterOccurrences(entry -> entry.getValue() > threshold).keySet();
    }
    
    /**
     * Get the occurrences which are greater or equals than the threshold.
     * 
     * @param threshold The condition threshold where from the occurrences should greater or equals.
     * @return All keys which occurrence are greater or equals than the threshold.
     * @throws IllegalArgumentException If threshold is smaller than 1.
     */
    public Set<K> filterGreaterOrEqualsOccurrences(final int threshold) {
        validateThreshold(threshold);
        return filterOccurrences(entry -> entry.getValue() >= threshold).keySet();
    }

    /**
     * Validates the threshold to query the occurrences.
     *
     * @param threshold The target threshold.
     * @return The same instance if valid.
     * @throws IllegalArgumentException If threshold is smaller than 1.
     */
    protected int validateThreshold(final int threshold) {
        return checkPositiveNumber("Threshold", threshold);
    }

    /**
     * Filter the occurrences with the given {@link Function}.
     *
     * @param filterPredicate The filter {@link Predicate}, which needs an {@link java.util.Map.Entry} input.
     * @return A new filtered {@link OccurrenceMap}.
     * @throws IllegalArgumentException If filterPredicate is null.
     */
    public OccurrenceMap<K> filter(final Predicate<Map.Entry<K, Integer>> filterPredicate) {
        return new OccurrenceMap<>(filterOccurrences(filterPredicate));
    }

    /**
     * Filter the occurrences with the given {@link Predicate}, and returns with the occurrences.
     *
     * @param filterPredicate The filter {@link Predicate}, which needs an {@link java.util.Map.Entry} input.
     * @return The occurrences after filtering.
     * @throws IllegalArgumentException If filterPredicate is null.
     */
    protected final Map<K, Integer> filterOccurrences(final Predicate<Map.Entry<K, Integer>> filterPredicate) {
        checkNotNullArgument("Filter predicate", filterPredicate);
        return occurrenceMap.entrySet().stream()
                .filter(filterPredicate)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

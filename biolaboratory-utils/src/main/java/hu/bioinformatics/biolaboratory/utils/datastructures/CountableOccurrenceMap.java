package hu.bioinformatics.biolaboratory.utils.datastructures;

import com.google.common.base.Preconditions;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * An {@link OccurrenceMap} which has finite elements. It can store elements which has 0 occurrence and filtering with
 * occurrence >= 0 is a valid operation.
 *
 * @author Attila Radi
 */
public class CountableOccurrenceMap<K> extends OccurrenceMap<K> {

    /**
     * Return with the null set.
     *
     * @param <K> The null set type.
     * @return The null set.
     */
    public static <K> CountableOccurrenceMap<K> build() {
        return new CountableOccurrenceMap<>();
    }

    /**
     * Build a {@link CountableOccurrenceMap} about the input {@link Map} of <i>K</i> and {@link Integer}. The ipnut
     * {@link Map} can only contain 0 or bigger values.
     *
     * @param occurrences The key-occurrence {@link Map}.
     * @param <K> The type of the key.
     * @return A new {@link CountableOccurrenceMap} about the input {@link Map}.
     * @throws IllegalArgumentException If occurrences contain null key, null value, or negative values.
     */
    public static <K> CountableOccurrenceMap<K> build(Map<K, Integer> occurrences) {
        Preconditions.checkArgument(occurrences == null
                        || occurrences.entrySet().stream().allMatch(entry -> entry.getKey() != null && entry.getValue() != null && entry.getValue() >= 0),
                "Occurrences should not contain null keys or values");
        return new CountableOccurrenceMap<>(occurrences);
    }

    /**
     * Creates a {@link CountableOccurrenceMap} from an input key {@link Set}. All of the values will be 0
     *
     * @param elementSet The keys of the {@link CountableOccurrenceMap}.
     * @param <K> The type of the keys.
     * @return A new {@link CountableOccurrenceMap} with 0 occurrence values.
     * @throws IllegalArgumentException If elementSet contain null key, null value, or negative values.
     */
    public static <K> CountableOccurrenceMap<K> build(Set<K> elementSet) {
        Preconditions.checkArgument(elementSet == null
                        || elementSet.stream().allMatch(Objects::nonNull),
                "Null element is not permitted");
        return new CountableOccurrenceMap<>(elementSet);
    }

    private CountableOccurrenceMap() {
        super();
    }

    private CountableOccurrenceMap(Set<K> elementSet) {
        if (elementSet == null) elementSet = new HashSet<>();
        occurrenceMap = elementSet.stream()
                .map(key -> new AbstractMap.SimpleEntry<>(key, 0))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private CountableOccurrenceMap(Map<K, Integer> occurrenceMap) {
        super(occurrenceMap);
    }

    /**
     * Get the key elements of the occurrences.
     *
     * @return A {@link Set} about the occurrences' key.
     */
    public Set<K> getElementSet() {
        return new HashSet<>(occurrenceMap.keySet());
    }

    @Override
    public CountableOccurrenceMap<K> copy() {
        return new CountableOccurrenceMap<>(occurrenceMap);
    }

    @Override
    public CountableOccurrenceMap<K> subSet(final K... keys) {
        return (CountableOccurrenceMap<K>) super.subSet(keys);
    }

    @Override
    public CountableOccurrenceMap<K> subSet(final Set<K> keySet) {
        return (CountableOccurrenceMap<K>) super.subSet(keySet);
    }

    /**
     * Decreases the occurrence of the target key.
     *
     * @param key The target key.
     * @return he number of occurrence of the target key after the decreasing.
     * @throws IllegalArgumentException If key is null or does not exist.
     */
    @Override
    public int decrease(K key) {
        return subtract(key, 1);
    }

    /**
     * Decreases the occurrence of the target key by <i>number</i>.
     *
     * @param key The target key.
     * @param number The number of decreased by.
     * @return The number of occurrence of the target key after the subtracting.
     * @throws IllegalArgumentException If key is null.
     * @throws IllegalArgumentException If number is negative.
     * @throws IllegalArgumentException If decreasing result smaller than 0.
     */
    @Override
    public synchronized int subtract(K key, int number) {
        validateKey(key);
        validateInputNumber(number);
        int occurrence = occurrenceMap.getOrDefault(key, 0);
        if (number == 0) return occurrence;
        Preconditions.checkArgument(occurrence > 0, "Cannot decrease occurrence from 0");

        int difference = occurrence - number;
        Preconditions.checkArgument(difference >= 0, "Cannot subtract more occurrence than existing");
        occurrenceMap.put(key, difference);

        return difference;
    }

    /**
     * Get the most frequent occurrences from the collection.
     *
     * @return The less frequent keys in the collection.
     */
    public Set<K> filterLessFrequentOccurrences() {
        if (occurrenceMap.isEmpty()) return Collections.emptySet();
        return filterSmallerOrEqualsOccurrences(minimumOccurrenceValue());
    }

    /**
     * Returns the minimum occurrence value.
     *
     * @return The minimum occurrence value.
     */
    @Override
    public int minimumOccurrenceValue() {
        return occurrenceMap.values().stream()
                .mapToInt(occurrence -> occurrence)
                .min()
                .orElse(0);
    }

    /**
     * Execute the merge with an other {@link CountableOccurrenceMap}. The method will extend the valid keys with the
     * union of the two {@link CountableOccurrenceMap}.
     *
     * @param otherOccurrenceMap The other {@link CountableOccurrenceMap} to merge.
     * @return The union of the two {@link CountableOccurrenceMap} with an extended key set.
     * @throws IllegalArgumentException If otherOccurrenceMap is null.
     */
    public CountableOccurrenceMap<K> mergeCountable(final CountableOccurrenceMap<K> otherOccurrenceMap) {
        return filterMergeCountable(otherOccurrenceMap, entry -> true);
    }

    /**
     * Filter and merge two {@link CountableOccurrenceMap} with the filter {@link Predicate}.
     *
     * @param otherOccurrenceMap The {@link CountableOccurrenceMap} to merge with.
     * @param filterPredicate The filter condition.
     * @return A merged and filtered {@link CountableOccurrenceMap}.
     * @throws IllegalArgumentException If otherOccurrenceMa is null.
     * @throws IllegalArgumentException If filterPredicate is null.
     */
    public CountableOccurrenceMap<K> filterMergeCountable(final CountableOccurrenceMap<K> otherOccurrenceMap, final Predicate<Map.Entry<K, Integer>> filterPredicate) {
        return (CountableOccurrenceMap<K>) filterMerge(otherOccurrenceMap, filterPredicate);
    }

    /**
     * Merges with an other {@link OccurrenceMap}. If it is a {@link CountableOccurrenceMap} then the return value
     * will be {@link CountableOccurrenceMap} with keeping all zero values. If not the return value will be a regular
     * {@link OccurrenceMap} and throws all explicit zero values.
     *
     * @param otherOccurrenceMap The other {@link OccurrenceMap} to merge with.
     * @param filterPredicate The filtering predicate.
     * @return The merge of the two {@link OccurrenceMap}.
     * @throws IllegalArgumentException If otherOccurrenceMap is null.
     * @throws IllegalArgumentException If filterPredicate is null.
     */
    @Override
    public OccurrenceMap<K> filterMerge(final OccurrenceMap<K> otherOccurrenceMap, final Predicate<Map.Entry<K, Integer>> filterPredicate) {
        if (otherOccurrenceMap instanceof CountableOccurrenceMap) {
            return new CountableOccurrenceMap<>(filterMergeOccurrences(otherOccurrenceMap, filterPredicate));
        }
        return super.filterMerge(otherOccurrenceMap, filterPredicate);
    }

    /**
     * Performs a stricter validation than the superclass. The input key has to be in the key elements.
     *
     * @param key The key to validate.
     * @return The same key.
     * @throws IllegalArgumentException If key is null or not in the key elements.
     */
    @Override
    protected K validateKey(final K key) {
        super.validateKey(key);
        Preconditions.checkArgument(occurrenceMap.containsKey(key), "The given key is not member of the key set");
        return key;
    }

    /**
     * Get the occurrences which are smaller than the threshold.
     *
     * @param threshold The condition threshold where from the occurrences should smaller.
     * @return All keys which occurrence are smaller than the threshold.
     * @throws IllegalArgumentException If threshold is negative number.
     */
    public Set<K> filterSmallerOccurrences(final int threshold) {
        validateThreshold(threshold);
        return filterOccurrences(entry -> entry.getValue() < threshold).keySet();
    }

    /**
     * Get the occurrences which are smaller or equals than the threshold.
     *
     * @param threshold The condition threshold where from the occurrences should smaller or equals.
     * @return All keys which occurrence are smaller or equals than the threshold.
     * @throws IllegalArgumentException If threshold is negative number.
     */
    public Set<K> filterSmallerOrEqualsOccurrences(final int threshold) {
        validateThreshold(threshold);
        return filterOccurrences(entry -> entry.getValue() <= threshold).keySet();
    }

    /**
     * Validates the threshold to query the occurrences.
     *
     * @param threshold The target threshold.
     * @return The same instance if valid.
     * @throws IllegalArgumentException If threshold is negative number.
     */
    @Override
    protected int validateThreshold(final int threshold) {
        Preconditions.checkArgument(threshold >= 0, "Threshold should not be negative number");
        return threshold;
    }

    @Override
    public CountableOccurrenceMap<K> filter(final Predicate<Map.Entry<K, Integer>> filterPredicate) {
        return new CountableOccurrenceMap<>(filterOccurrences(filterPredicate));
    }
}

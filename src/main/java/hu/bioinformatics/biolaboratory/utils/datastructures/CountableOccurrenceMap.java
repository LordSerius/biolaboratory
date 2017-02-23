package hu.bioinformatics.biolaboratory.utils.datastructures;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

import java.util.AbstractMap;
import java.util.Map;
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
     * Converts an {@link OccurrenceMap} to a {@link CountableOccurrenceMap}. The element set will be limited with
     * the given elements of the input {@link OccurrenceMap}.
     *
     * @param occurrenceMap The input {@link OccurrenceMap}.
     * @param <K> The type of the {@link OccurrenceMap} and the return {@link CountableOccurrenceMap}.
     * @return The converted {@link CountableOccurrenceMap}.
     */
    public static <K> CountableOccurrenceMap<K> toCountable(OccurrenceMap<K> occurrenceMap) {
        Preconditions.checkArgument(occurrenceMap != null, "Input occurrence map should not be null");
        return build(occurrenceMap.occurrenceMap);
    }

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
     */
    public static <K> CountableOccurrenceMap<K> build(Set<K> elementSet) {
        Preconditions.checkArgument(elementSet == null
                        || elementSet.stream().allMatch(element -> element != null),
                "Null element is not permitted");
        return new CountableOccurrenceMap<>(elementSet);
    }

    private CountableOccurrenceMap() {
        super();
    }

    private CountableOccurrenceMap(Set<K> elementSet) {
        if (elementSet == null) elementSet = Sets.newHashSet();
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
        return Sets.newHashSet(occurrenceMap.keySet());
    }

    @Override
    public CountableOccurrenceMap<K> copy() {
        return new CountableOccurrenceMap<K>(occurrenceMap);
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
        validateKey(key);
        Integer occurrence = occurrenceMap.getOrDefault(key, 0);
        Preconditions.checkArgument(occurrence > 0, "Cannot decrease occurrence from 0");

        occurrence = occurrence - 1;
        occurrenceMap.put(key, occurrence);

        return occurrence;
    }

    /**
     * Execute the merge with an other {@link CountableOccurrenceMap}. The method will extend the valid keys with the
     * union of the two {@link CountableOccurrenceMap}.
     *
     * @param otherOccurrenceMap The other {@link CountableOccurrenceMap} to merge.
     * @return The union of the two {@link CountableOccurrenceMap} with an extended key set.
     */
    public CountableOccurrenceMap<K> merge(final CountableOccurrenceMap<K> otherOccurrenceMap) {
        return filterMerge(otherOccurrenceMap, entry -> true);
    }

    /**
     * Filter and merge two {@link CountableOccurrenceMap} with the filter {@link Predicate}.
     *
     * @param otherOccurrenceMap The {@link CountableOccurrenceMap} to merge with.
     * @param filterPredicate The filter condition.
     * @return A merged and filtered {@link CountableOccurrenceMap}.
     */
    protected CountableOccurrenceMap<K> filterMerge(final CountableOccurrenceMap<K> otherOccurrenceMap, final Predicate<Map.Entry<K, Integer>> filterPredicate) {
        OccurrenceMap<K> uncountableOccurrenceMap = filterMerge((OccurrenceMap<K>) otherOccurrenceMap, filterPredicate);
        return toCountable(uncountableOccurrenceMap);
    }

    @Override
    protected void validateKey(final K key) {
        super.validateKey(key);
        Preconditions.checkArgument(occurrenceMap.containsKey(key), "The given key is not member of the key set");
    }

    @Override
    public Set<K> filterGreaterOrEqualsOccurrences(final int threshold) {
        Preconditions.checkArgument(threshold >= 0, "Threshold should bigger or equals than 0");
        return filterOccurrences(entry -> entry.getValue() >= threshold).keySet();
    }
}

package hu.bioinformatics.biolaboratory.utils.datastructures;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

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
     * Builds a new {@link OccurrenceMap} with the given initial occurrences. The occurrences should contain
     * values which are bigger than 0 only.
     *
     * @param occurrences The initial occurrence map.
     * @param <K> The key type of the occurrences.
     * @return A new {@link OccurrenceMap} map with the given occurrences.
     * @throws IllegalArgumentException If the occurrences contain 0 or negative values.
     */
    public static <K> OccurrenceMap<K> build(Map<K, Integer> occurrences) {
        Preconditions.checkArgument(occurrences == null
                || occurrences.entrySet().stream().allMatch(entry -> entry.getKey() != null && entry.getValue() != null && entry.getValue() > 0),
                "Occurrences should not contain null keys or smaller than 1 elements");
        return new OccurrenceMap<>(occurrences);
    }

    /**
     * Default constructor creates an empty {@link Map}.
     */
    protected OccurrenceMap() {
        occurrenceMap = Maps.newHashMap();
    }

    /**
     * Constructor which pass through an {@link Map} with K type key and {@link Integer} type value. If the {@link Map}
     * if zero, the constructor provides an empty {@link Map}.
     *
     * @param occurrenceMap
     */
    protected OccurrenceMap(Map<K, Integer> occurrenceMap) {
        if (occurrenceMap == null) occurrenceMap = Maps.newHashMap();
        this.occurrenceMap = occurrenceMap;
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
     * Get the occurrences of the key.
     *
     * @param key The occurrence key.
     * @return The occurrence number for the specific key.
     */
    public final int getOccurrence(final K key) {
        Preconditions.checkArgument(key != null, "Key should not be null");
        return occurrenceMap.getOrDefault(key, 0);
    }

    /**
     * Get the copy of inside {@link Map} which contains the keys and the occurrences.
     * 
     * @return The inside occurrence {@link Map}.
     */
    public final Map<K, Integer> getOccurrences() {
        Map<K, Integer> copyOccurrenceMap = Maps.newHashMap();
        copyOccurrenceMap.putAll(occurrenceMap);
        return copyOccurrenceMap;
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj != null
                && obj.getClass().equals(OccurrenceMap.class)
                && compareOccurrenceMapContent(((OccurrenceMap<K>) obj).occurrenceMap);
    }

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
     * Immutable operation for merging an other {@link OccurrenceMap} with the current
     * {@link OccurrenceMap}.
     * 
     * @param otherOccurrenceMap The other {@link OccurrenceMap} to merge with.
     * @return A new {@link OccurrenceMap} with the merged values.
     */
    public OccurrenceMap<K> merge(final OccurrenceMap<K> otherOccurrenceMap) {
        return filterMerge(otherOccurrenceMap, entry -> entry.getValue() > 0);
    }

    /**
     * Merging and filtering the the merged result about the other {@link OccurrenceMap} and the current
     * {@link OccurrenceMap}.
     *
     * @param otherOccurrenceMap The other {@link OccurrenceMap} to merge with.
     * @param filterPredicate The filtering predicate.
     * @return A new {@link OccurrenceMap} with the merged and filtered values.
     */
    protected final OccurrenceMap<K> filterMerge(final OccurrenceMap<K> otherOccurrenceMap, final Predicate<Map.Entry<K, Integer>> filterPredicate) {
        Preconditions.checkArgument(otherOccurrenceMap != null, "Cannot merge with null");
        Preconditions.checkArgument(filterPredicate != null, "Filter predicate should not be null");
        Map<K, Integer> mergedOccurrences = Maps.newHashMap();
        Map<K, Integer> otherOccurrences = otherOccurrenceMap.getOccurrences();
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
        return new OccurrenceMap<>(mergedOccurrences);
    }
    
    /**
     * Decreases the occurrence of the target key by one. Remove if the occurrence reaches
     * zero. If the key is not inside the map, do nothing and returns with 0.
     * 
     * @param key The target key.
     * @return The number of occurrence of the target key after the decreasing.
     * @throws IllegalArgumentException If key is null or does not exist.
     */
    public int decrease(final K key) {
        validateKey(key);
        Integer occurrence = occurrenceMap.getOrDefault(key, 0);
        Preconditions.checkArgument(occurrence > 0, "Cannot decrease occurrence from 0");

        if (occurrence == 1) {
            occurrence = occurrence - 1;
            occurrenceMap.remove(key);
        }
        else if (occurrence > 1) {
            occurrence = occurrence - 1;
            occurrenceMap.put(key, occurrence);
        }
        return occurrence;
    }
    
    /**
     * Increase the occurrence of the target key. Adds the key if it is not inside the
     * collection.
     * 
     * @param key The target key.
     * @return The number of occurrence of the target key after the increasing.
     * @throws IllegalArgumentException If key is null.
     */
    public int increase(final K key) {
        validateKey(key);
        int occurrence = occurrenceMap.getOrDefault(key, 0) + 1;
        occurrenceMap.put(key, occurrence);
        return occurrence;
    }
    
    protected void validateKey(final K key) {
        Preconditions.checkArgument(key != null, "Key should not be null");
    }
    
    /**
     * Get the most frequent occurrences from the collection.
     * 
     * @return The most frequent keys in the collection.
     */
    public Set<K> filterMostFrequentOccurrences() {
        if (occurrenceMap.isEmpty()) return Collections.emptySet();
        
        int maxOccurrence = occurrenceMap.values().stream()
            .sorted(Comparator.reverseOrder())
            .limit(1)
            .collect(Collectors.toList())
            .get(0);
        return filterGreaterOrEqualsOccurrences(maxOccurrence);
    }
    
    /**
     * Get the occurrences which are greater or equals than the threshold.
     * 
     * @param threshold The condition threshold where from the occurrences should
     *                  greater or equals.
     * @return All keys which occurrence are greater or equals than the threshold.
     * @throws IllegalArgumentException If threshold is smaller than 1.
     */
    public Set<K> filterGreaterOrEqualsOccurrences(final int threshold) {
        Preconditions.checkArgument(threshold > 0, "Threshold should not smaller than 1");
        return filterOccurrences(entry -> entry.getValue() >= threshold).keySet();
    }

    /**
     * Filter the occurrences with the given {@link Predicate}, and returns with the occurrences.
     *
     * @param filterPredicate The filter {@link Predicate}, which needs an {@link java.util.Map.Entry} input.
     * @return The occurrences after filtering.
     */
    protected final Map<K, Integer> filterOccurrences(final Predicate<Map.Entry<K, Integer>> filterPredicate) {
        return filter(filterPredicate).occurrenceMap;
    }

    /**
     * Filter the occurrences with the given {@link Function}.
     *
     * @param filterPredicate The filter {@link Predicate}, which needs an {@link java.util.Map.Entry} input.
     * @return A new filtered {@link OccurrenceMap}.
     */
    protected final OccurrenceMap<K> filter(final Predicate<Map.Entry<K, Integer>> filterPredicate) {
        Preconditions.checkArgument(filterPredicate != null, "Filter predicate should not be null");
        return new OccurrenceMap<>(occurrenceMap.entrySet().stream()
                .filter(entry -> filterPredicate.test(entry))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
}

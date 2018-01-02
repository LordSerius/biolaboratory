package hu.bioinformatics.biolaboratory.utils.datastructures;

import com.google.common.base.Preconditions;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.notNullCollection;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.notNullVarargs;

/**
 * Utility class which provides multiple operations on multiple {@link OccurrenceMap}.
 *
 * @author Attila Radi
 */
public class OccurrenceMaps {

    /**
     * Merge efficiently the given {@link OccurrenceMap}s in immutable way.
     *
     * @param occurrenceMaps Vararg or array of {@link OccurrenceMap}s.
     * @param <K> Every {@link OccurrenceMap} has to have K type.
     * @return The merge of the {@link OccurrenceMap}s.
     * @throws IllegalArgumentException If occurrenceMaps contains null value.
     * @see OccurrenceMap#merge(OccurrenceMap)
     */
    @SafeVarargs
    public static <K> OccurrenceMap<K> mergeOccurrenceMaps(final OccurrenceMap<K>... occurrenceMaps) {
        Predicate<Map.Entry<K, Integer>> greaterZeroPredicate = entry -> entry.getValue() > 0;
        return filterMergeOccurrenceMaps(greaterZeroPredicate, occurrenceMaps);
    }

    /**
     * Merge efficiently a {@link Collection} of {@link OccurrenceMap} in immutable way.
     *
     * @param occurrenceMapCollection A {@link Collection} of {@link OccurrenceMap}s.
     * @param <K> Every {@link OccurrenceMap} has to have K type.
     * @return The merge of the {@link OccurrenceMap} collection.
     * @throws  IllegalArgumentException If occurrenceMapCollection contains null value.
     * @see OccurrenceMap#merge(OccurrenceMap)
     */
    public static <K> OccurrenceMap<K> mergeOccurrenceMaps(final Collection<OccurrenceMap<K>> occurrenceMapCollection) {
        Predicate<Map.Entry<K, Integer>> greaterZeroPredicate = entry -> entry.getValue() > 0;
        return filterMergeOccurrenceMaps(greaterZeroPredicate, occurrenceMapCollection);
    }


    /**
     * Merge the {@link OccurrenceMap}s and evaluate the {@link Predicate} immediately on the merged values.
     *
     * @param filterPredicate Filters the merged values before return.
     * @param occurrenceMaps Vararg or array of {@link OccurrenceMap}s.
     * @param <K> Every {@link OccurrenceMap} has to have K type.
     * @return The merge of the filtered {@link OccurrenceMap} collection.
     * @throws IllegalArgumentException If filterPredicate is null.
     * @throws IllegalArgumentException If occurrenceMaps contains null value.
     * @see OccurrenceMap#filterMerge(OccurrenceMap, Predicate)
     */
    @SafeVarargs
    private static <K> OccurrenceMap<K> filterMergeOccurrenceMaps(
            final Predicate<Map.Entry<K, Integer>> filterPredicate, final OccurrenceMap<K>... occurrenceMaps) {
        return filterMergeOccurrenceMaps(filterPredicate, Arrays.asList(notNullVarargs(occurrenceMaps)));
    }

    /**
     * Merge the {@link OccurrenceMap}s in the {@link Collection} and evaluate the {@link Predicate} immediately on the
     * merged values.
     *
     * @param filterPredicate Filters the merged values before return.
     * @param occurrenceMapCollection A {@link Collection} of {@link OccurrenceMap}s.
     * @param <K> Every {@link OccurrenceMap} has to have K type.
     * @return The merge of the filtered {@link OccurrenceMap} collection.
     * @throws IllegalArgumentException If filterPredicate is null.
     * @throws IllegalArgumentException If occurrenceMapCollection contains null value.
     * @see OccurrenceMap#filterMerge(OccurrenceMap, Predicate)
     */
    private static <K> OccurrenceMap<K> filterMergeOccurrenceMaps(
            final Predicate<Map.Entry<K, Integer>> filterPredicate, final Collection<OccurrenceMap<K>> occurrenceMapCollection) {
        Preconditions.checkArgument(filterPredicate != null, "Filter predicate should no be null");

        List<Map<K, Integer>> occurrenceMapList = getOccurrences(notNullCollection(occurrenceMapCollection));

        Map<K, Integer> mergedOccurrences = new HashMap<>();
        for (Map<K, Integer> occurrence : occurrenceMapList) {
            for (K key : occurrence.keySet()) {
                int totalOccurrence = 0;
                for (Map<K, Integer> otherOccurrence : occurrenceMapList) {
                    totalOccurrence += Optional.ofNullable(otherOccurrence.remove(key)).orElse(0);
                }
                if (filterPredicate.test(new AbstractMap.SimpleEntry<>(key, totalOccurrence))) {
                    mergedOccurrences.put(key, totalOccurrence);
                }
            }
        }
        return OccurrenceMap.build(mergedOccurrences);
    }

    /**
     * Merge the given {@link OccurrenceMap}s and keeps only the most frequent elements of the merge.
     *
     * @param occurrenceMaps Vararg or array of {@link OccurrenceMap}s.
     * @param <K> Every {@link OccurrenceMap} has to have K type.
     * @return An {@link OccurrenceMap} with the most frequent occurrences after the merging.
     * @throws IllegalArgumentException If occurrencaMaps contains null element.
     * @see OccurrenceMap#filterMostFrequentOccurrences()
     */
    @SafeVarargs
    public static <K> OccurrenceMap<K> getMostFrequentOccurrences(final OccurrenceMap<K>... occurrenceMaps) {
        return getMostFrequentOccurrences(Arrays.asList(notNullVarargs(occurrenceMaps)));
    }

    /**
     * Merge the {@link OccurrenceMap}s in the {@link Collection} and keeps only the most frequent elements of the merge.
     *
     * @param occurrenceMapCollection A {@link Collection} of {@link OccurrenceMap}s.
     * @param <K> Every {@link OccurrenceMap} has to have K type.
     * @return An {@link OccurrenceMap} with the most frequent occurrences after the merging.
     * @throws IllegalArgumentException If occurrenceMapCollection contains null element.
     * @see OccurrenceMap#filterMostFrequentOccurrences()
     */
    public static <K> OccurrenceMap<K> getMostFrequentOccurrences(final Collection<OccurrenceMap<K>> occurrenceMapCollection) {
        List<Map<K, Integer>> occurrenceMapList = getOccurrences(notNullCollection(occurrenceMapCollection));

        int mostFrequentOccurrenceNumber = 0;
        Map<K, Integer> mostFrequentOccurrences = new HashMap<>();
        for (Map<K, Integer> occurrence : occurrenceMapList) {
            for (K key : occurrence.keySet()) {
                int totalOccurrence = 0;
                for (Map<K, Integer> otherOccurrence : occurrenceMapList) {
                    totalOccurrence += Optional.ofNullable(otherOccurrence.remove(key)).orElse(0);
                }
                if (totalOccurrence > mostFrequentOccurrenceNumber) {
                    mostFrequentOccurrences.clear();
                    mostFrequentOccurrenceNumber = totalOccurrence;
                }
                if (totalOccurrence == mostFrequentOccurrenceNumber) {
                    mostFrequentOccurrences.put(key, totalOccurrence);
                }
            }
        }
        return OccurrenceMap.build(mostFrequentOccurrences);
    }

    private static <K> List<Map<K, Integer>> getOccurrences(final Collection<OccurrenceMap<K>> occurrenceMapCollection) {
        return occurrenceMapCollection.stream()
                .map(map -> {
                    Preconditions.checkArgument(map != null, "Cannot merge with null");
                    return map.getOccurrencesInMap();
                })
                .collect(Collectors.toList());
    }
}

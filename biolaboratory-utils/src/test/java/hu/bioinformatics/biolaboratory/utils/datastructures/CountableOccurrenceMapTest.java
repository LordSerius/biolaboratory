package hu.bioinformatics.biolaboratory.utils.datastructures;

import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.testng.Assert.fail;

/**
 * Test cases for the {@link CountableOccurrenceMap} class.
 *
 * @author Attila Radi
 */
@Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class)
public class CountableOccurrenceMapTest {

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_BUILD_FROM_MAP_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildFromMapThrowException(Map<String, Integer> occurrences) {
        CountableOccurrenceMap.build(occurrences);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.VALID_BUILD_FROM_MAP_DATA_PROVIDER_NAME)
    public void shouldBuildFromMapReturn(Map<String, Integer> occurrences) {
        CountableOccurrenceMap<String> occurrenceMap = CountableOccurrenceMap.build(occurrences);
        occurrences = occurrences == null ? ImmutableMap.of() : occurrences;
        assertThat(occurrenceMap.getOccurrencesInMap(), is(equalTo(occurrences)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_BUILD_FROM_SET_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildFromSetThrowException(Set<String> elementSet) {
        CountableOccurrenceMap.build(elementSet);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.VALID_BUILD_FROM_SET_DATA_PROVIDER_NAME)
    public void shouldBuildFromSetReturn(Set<String> elementSet, CountableOccurrenceMap<String> controlOccurrenceMap) {
        CountableOccurrenceMap<String> occurrenceMap = CountableOccurrenceMap.build(elementSet);
        assertThat(occurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.IS_EQUALS_DATA_PROVIDER_NAME)
    public void shouldEqualsReturn(CountableOccurrenceMap<String> occurrenceMap, Object rightHand, boolean isEquals) {
        assertThat(occurrenceMap.equals(rightHand), is(isEquals));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.COPY_DATA_PROVIDER_NAME)
    public void shouldCopyReturn(CountableOccurrenceMap<String> countableOccurrenceMap) {
        CountableOccurrenceMap<String> occurrenceMapCopy = countableOccurrenceMap.copy();
        assertThat(occurrenceMapCopy, allOf(
                is(not(sameInstance(countableOccurrenceMap))),
                is(equalTo(countableOccurrenceMap))
        ));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_GET_OCCURRENCE_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetOccurrenceThrowException(CountableOccurrenceMap<String> occurrenceMap, String key) {
        occurrenceMap.getOccurrence(key);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSumOccurrencesFromArrayThrowException(CountableOccurrenceMap<String> occurrenceMap, String[] keys) {
        occurrenceMap.sumOccurrences(keys);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_OPERATION_SET_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSumOccurrencesAboutSetThrowException(CountableOccurrenceMap<String> occurrenceMap, Set<String> keySet) {
        occurrenceMap.sumOccurrences(keySet);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSubSetThrowsException(CountableOccurrenceMap<String> occurrenceMap, String[] keys) {
        occurrenceMap.subSet(keys);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.SUB_SET_DATA_PROVIDER_NAME)
    public void shouldSubSetReturn(CountableOccurrenceMap<String> occurrenceMap, String[] keys, CountableOccurrenceMap<String> controlSubSet) {
        CountableOccurrenceMap<String> subSet = occurrenceMap.subSet(keys);
        assertThat(subSet, is(equalTo(controlSubSet)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_OPERATION_SET_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSubSetAboutSetThrowsException(CountableOccurrenceMap<String> occurrenceMap, Set<String> subSet) {
        occurrenceMap.subSet(subSet);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.SUB_SET_ABOUT_SET_DATA_PROVIDER_NAME)
    public void shouldSubSetAboutSetReturn(CountableOccurrenceMap<String> occurrenceMap, Set<String> keySet, CountableOccurrenceMap<String> controlSubSet) {
        CountableOccurrenceMap<String> subSet = occurrenceMap.subSet(keySet);
        assertThat(subSet, is(equalTo(controlSubSet)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.MINIMUM_OCCURRENCE_VALUE_DATA_PROVIDER_NAME)
    public void shouldMinimumOccurrenceValueReturn(CountableOccurrenceMap<String> occurrenceMap, int controlMinimumValue) {
        int minimumValue = occurrenceMap.minimumOccurrenceValue();
        assertThat(minimumValue, is(equalTo(controlMinimumValue)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_DECREASE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldDecreaseThrowException(CountableOccurrenceMap<String> occurrenceMap, String key) {
        occurrenceMap.decrease(key);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.VALID_DECREASE_DATA_PROVIDER_NAME)
    public void shouldDecreaseReturn(CountableOccurrenceMap<String> occurrenceMap, String key, CountableOccurrenceMap<String> controlOccurrenceMap) {
        occurrenceMap.decrease(key);
        assertThat(occurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_SUBTRACT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSubtractThrowException(CountableOccurrenceMap<String> occurrenceMap, String key, int number) {
        occurrenceMap.subtract(key, number);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.VALID_SUBTRACT_DATA_PROVIDER_NAME)
    public void shouldSubtractReturn(CountableOccurrenceMap<String> occurrenceMap, String key, int number, CountableOccurrenceMap<String> controlOccurrenceMap) {
        occurrenceMap.subtract(key, number);
        assertThat(occurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_INCREASE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldIncreaseThrowException(CountableOccurrenceMap<String> occurrenceMap, String key) {
        occurrenceMap.increase(key);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.VALID_INCREASE_DATA_PROVIDER_NAME)
    public void shouldIncreaseReturn(CountableOccurrenceMap<String> occurrenceMap, String key, CountableOccurrenceMap<String> controlOccurrenceMap) {
        occurrenceMap.increase(key);
        assertThat(occurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_ADD_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldAddThrowException(CountableOccurrenceMap<String> occurrenceMap, String key, int number) {
        occurrenceMap.add(key, number);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.VALID_ADD_DATA_PROVIDER_NAME)
    public void shouldAddReturn(CountableOccurrenceMap<String> occurrenceMap, String key, int number, CountableOccurrenceMap<String> controlOccurrenceMap) {
        occurrenceMap.add(key, number);
        assertThat(occurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.LESS_FREQUENT_OCCURRENCES_DATA_PROVIDER_NAME)
    public void shouldFilterLessFrequentOccurrencesReturn(CountableOccurrenceMap<String> occurrenceMap, Set<String> controlSet) {
        Set<String> filteredOccurrences = occurrenceMap.filterLessFrequentOccurrences();
        assertThat(filteredOccurrences, is(equalTo(controlSet)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.FILTER_DATA_PROVIDER_NAME)
    public void shouldFilterReturn(CountableOccurrenceMap<String> occurrenceMap, Predicate<Map.Entry<String, Integer>> predicate, CountableOccurrenceMap<String> controlOccurrenceMap) {
        CountableOccurrenceMap<String> filteredOccurrenceMap = occurrenceMap.filter(predicate);
        assertThat(filteredOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_FILTER_RELATIONAL_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFilterEqualsOccurrencesThrowException(CountableOccurrenceMap<String> occurrenceMap, int threshold) {
        occurrenceMap.filterGreaterOrEqualsOccurrences(threshold);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.VALID_FILTER_EQUALS_DATA_PROVIDER_NAME)
    public void shouldFilterEqualsOccurrencesReturn(CountableOccurrenceMap<String> occurrenceMap, int value, Set<String> controlSet) {
        Set<String> resultSet = occurrenceMap.filterEqualsOccurrences(value);
        assertThat(resultSet, is(equalTo(controlSet)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_FILTER_RELATIONAL_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFilterSmallerOccurrencesThrowException(CountableOccurrenceMap<String> occurrenceMap, int threshold) {
        occurrenceMap.filterSmallerOccurrences(threshold);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.VALID_FILTER_SMALLER_DATA_PROVIDER_NAME)
    public void shouldFilterSmallerOccurrencesReturn(CountableOccurrenceMap<String> occurrenceMap, int value, Set<String> controlSet) {
        Set<String> resultSet = occurrenceMap.filterSmallerOccurrences(value);
        assertThat(resultSet, is(equalTo(controlSet)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_FILTER_RELATIONAL_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFilterSmallerOrEqualsOccurrencesThrowException(CountableOccurrenceMap<String> occurrenceMap, int threshold) {
        occurrenceMap.filterSmallerOrEqualsOccurrences(threshold);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.VALID_FILTER_SMALLER_OR_EQUALS_DATA_PROVIDER_NAME)
    public void shouldFilterSmallerOrEqualsOccurrencesReturn(CountableOccurrenceMap<String> occurrenceMap, int value, Set<String> controlSet) {
        Set<String> resultSet = occurrenceMap.filterSmallerOrEqualsOccurrences(value);
        assertThat(resultSet, is(equalTo(controlSet)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_FILTER_RELATIONAL_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFilterGreaterOccurrencesThrowException(CountableOccurrenceMap<String> occurrenceMap, int threshold) {
        occurrenceMap.filterGreaterOrEqualsOccurrences(threshold);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.VALID_FILTER_GREATER_DATA_PROVIDER_NAME)
    public void shouldFilterGreaterOccurrencesReturn(CountableOccurrenceMap<String> occurrenceMap, int value, Set<String> controlSet) {
        Set<String> resultSet = occurrenceMap.filterGreaterOccurrences(value);
        assertThat(resultSet, is(equalTo(controlSet)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_FILTER_RELATIONAL_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFilterGreaterOrEqualsOccurrencesThrowException(CountableOccurrenceMap<String> occurrenceMap, int threshold) {
        occurrenceMap.filterGreaterOrEqualsOccurrences(threshold);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.VALID_FILTER_GREATER_OR_EQUALS_DATA_PROVIDER_NAME)
    public void shouldFilterGreaterOrEqualsOccurrencesReturn(CountableOccurrenceMap<String> occurrenceMap, int threshold, Set<String> controlSet) {
        Set<String> resultSet = occurrenceMap.filterGreaterOrEqualsOccurrences(threshold);
        assertThat(resultSet, is(equalTo(controlSet)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.FILTER_MERGE_COUNTABLE_DATA_PROVIDER_NAME)
    public void shouldFilterMergeCountableReturn(CountableOccurrenceMap<String> occurrenceMap, CountableOccurrenceMap<String> otherOccurrenceMap, Predicate<Map.Entry<String, Integer>> filterPredicate, CountableOccurrenceMap<String> controlOccurrenceMap) {
        CountableOccurrenceMap<String> mergedOccurrenceMap = occurrenceMap.filterMergeCountable(otherOccurrenceMap, filterPredicate);
        assertThat(mergedOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_FILTER_MERGE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFilterMergeThrowException(CountableOccurrenceMap<String> occurrenceMap, OccurrenceMap<String> otherOccurrenceMap, Predicate<Map.Entry<String, Integer>> filterPredicate) {
        occurrenceMap.filterMerge(otherOccurrenceMap, filterPredicate);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.VALID_FILTER_MERGE_DATA_PROVIDER_NAME)
    public void shouldFilterMergeReturn(CountableOccurrenceMap<String> occurrenceMap, OccurrenceMap<String> otherOccurrenceMap, Predicate<Map.Entry<String, Integer>> filterPredicate, OccurrenceMap<String> controlOccurrenceMap) {
        OccurrenceMap<String> mergedOccurrenceMap = occurrenceMap.filterMerge(otherOccurrenceMap, filterPredicate);
        assertThat(mergedOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.MERGE_COUNTABLE_DATA_PROVIDER_NAME)
    public void shouldMergeCountableReturn(CountableOccurrenceMap<String> occurrenceMap, CountableOccurrenceMap<String> otherOccurrenceMap, CountableOccurrenceMap<String> controlOccurrenceMap) {
        CountableOccurrenceMap<String> mergedOccurrenceMap = occurrenceMap.mergeCountable(otherOccurrenceMap);
        assertThat(mergedOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.MERGE_DATA_PROVIDER_NAME)
    public void shouldMergeReturn(CountableOccurrenceMap<String> occurrenceMap, OccurrenceMap<String> otherOccurrenceMap, OccurrenceMap<String> controlOccurrenceMap) {
        OccurrenceMap<String> mergedOccurrenceMap = occurrenceMap.merge(otherOccurrenceMap);
        assertThat(mergedOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_GET_OCCURRENCE_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldOccurrenceRatioThrowException(CountableOccurrenceMap<String> occurrenceMap, String key) {
        occurrenceMap.getOccurrence(key);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldAccumulatedOccurrenceRatioThrowException(CountableOccurrenceMap<String> occurrenceMap, String[] keys) {
        occurrenceMap.accumulatedOccurrenceRatio(keys);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_OPERATION_SET_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldAccumulatedOccurrenceRatioSetThrowException(CountableOccurrenceMap<String> occurrenceMap, Set<String> keySet) {
        occurrenceMap.accumulatedOccurrenceRatio(keySet);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldOccurrenceRatiosThrowException(CountableOccurrenceMap<String> occurrenceMap, String[] keys) {
        occurrenceMap.occurrenceRatios(keys);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_OPERATION_SET_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldOccurrenceRatiosSetThrowException(CountableOccurrenceMap<String> occurrenceMap, Set<String> keySet) {
        occurrenceMap.occurrenceRatios(keySet);
        fail();
    }
}
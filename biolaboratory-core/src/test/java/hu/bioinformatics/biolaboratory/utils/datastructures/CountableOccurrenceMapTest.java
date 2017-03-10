package hu.bioinformatics.biolaboratory.utils.datastructures;

import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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
        assertThat(occurrenceMap.getOccurrences(), is(equalTo(occurrences)));
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

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_MERGE_WITH_COUNTABLE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldMergeWithCountableThrowException(CountableOccurrenceMap<String> occurrenceMap, CountableOccurrenceMap<String> otherOccurrenceMap) {
        occurrenceMap.merge(otherOccurrenceMap);
        fail();
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.VALID_MERGE_WITH_COUNTABLE_DATA_PROVIDER_NAME)
    public void shouldMergeWithCountableReturn(CountableOccurrenceMap<String> occurrenceMap, CountableOccurrenceMap<String> otherOccurrenceMap, CountableOccurrenceMap<String> controlOccurrenceMap) {
        CountableOccurrenceMap<String> mergedOccurrenceMap = occurrenceMap.merge(otherOccurrenceMap);
        assertThat(mergedOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.VALID_MERGE_WITH_UNCOUNTABLE_DATA_PROVIDER_NAME)
    public void shouldMergeWithUncountableReturn(CountableOccurrenceMap<String> occurrenceMap, OccurrenceMap<String> otherOccurrenceMap, OccurrenceMap<String> controlOccurrenceMap) {
        OccurrenceMap<String> mergedOccurrenceMap = occurrenceMap.merge(otherOccurrenceMap);
        assertThat(mergedOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = CountableOccurrenceMapTestDataProvider.LESS_FREQUENT_OCCURRENCES_DATA_PROVIDER_NAME)
    public void shouldFilterLessFrequentOccurrencesReturn(CountableOccurrenceMap<String> occurrenceMap, Set<String> controlSet) {
        Set<String> filteredOccurrences = occurrenceMap.filterLessFrequentOccurrences();
        assertThat(filteredOccurrences, is(equalTo(controlSet)));
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
}
package hu.bioinformatics.biolaboratory.utils.datastructures;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

import static org.testng.Assert.*;

/**
 * Test cases for the {@link CountableOccurrenceMap} class.
 *
 * @author Attila Radi
 */
public class CountableOccurrenceMapTest {

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_BUILD_FROM_MAP_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildFromMapThrowException(Map<String, Integer> occurrences) {
        CountableOccurrenceMap.build(occurrences);
        fail();
    }

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.VALID_BUILD_FROM_MAP_DATA_PROVIDER_NAME)
    public void shouldBuildFromMapReturn(Map<String, Integer> occurrences) {
        CountableOccurrenceMap<String> occurrenceMap = CountableOccurrenceMap.build(occurrences);
        occurrences = occurrences == null ? ImmutableMap.of() : occurrences;
        assertThat(occurrenceMap.getOccurrences(), is(equalTo(occurrences)));
    }

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_BUILD_FROM_SET_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildFromSetThrowException(Set<String> elementSet) {
        CountableOccurrenceMap.build(elementSet);
        fail();
    }

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.VALID_BUILD_FROM_SET_DATA_PROVIDER_NAME)
    public void shouldBuildFromSetReturn(Set<String> elementSet, CountableOccurrenceMap<String> controlOccurrenceMap) {
        CountableOccurrenceMap<String> occurrenceMap = CountableOccurrenceMap.build(elementSet);
        assertThat(occurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_TO_COUNTABLE_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldToCountableThrowException(OccurrenceMap<String> occurrenceMap) {
        CountableOccurrenceMap.toCountable(occurrenceMap);
        fail();
    }

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.VALID_TO_COUNTABLE_DATA_PROVIDER)
    public void shouldToCountableReturn(OccurrenceMap<String> occurrenceMap) {
        CountableOccurrenceMap<String> countableOccurrenceMap = CountableOccurrenceMap.toCountable(occurrenceMap);
        CountableOccurrenceMap<String> controlOccurrenceMap = CountableOccurrenceMap.build(occurrenceMap.getOccurrences());
        assertThat(countableOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.IS_EQUALS_DATA_PROVIDER_NAME)
    public void shouldEqualsReturn(CountableOccurrenceMap<String> occurrenceMap, Object rightHand, boolean isEquals) {
        assertThat(occurrenceMap.equals(rightHand), is(isEquals));
    }

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.COPY_DATA_PROVIDER_NAME)
    public void shouldCopyReturn(CountableOccurrenceMap<String> countableOccurrenceMap) {
        CountableOccurrenceMap<String> occurrenceMapCopy = countableOccurrenceMap.copy();
        assertThat(occurrenceMapCopy, allOf(
                is(not(sameInstance(countableOccurrenceMap))),
                is(equalTo(countableOccurrenceMap))
        ));
    }

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_DECREASE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldDecreaseThrowException(CountableOccurrenceMap<String> occurrenceMap, String key) {
        occurrenceMap.decrease(key);
        fail();
    }

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.VALID_DECREASE_DATA_PROVIDER_NAME)
    public void shouldDecreaseReturn(CountableOccurrenceMap<String> occurrenceMap, String key, CountableOccurrenceMap<String> controlOccurrenceMap) {
        occurrenceMap.decrease(key);
        assertThat(occurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_MERGE_WITH_COUNTABLE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldMergeWithCountableThrowException(CountableOccurrenceMap<String> occurrenceMap, CountableOccurrenceMap<String> otherOccurrenceMap) {
        occurrenceMap.merge(otherOccurrenceMap);
        fail();
    }

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.VALID_MERGE_WITH_COUNTABLE_DATA_PROVIDER_NAME)
    public void shouldMergeWithCountableReturn(CountableOccurrenceMap<String> occurrenceMap, CountableOccurrenceMap<String> otherOccurrenceMap, CountableOccurrenceMap<String> controlOccurrenceMap) {
        CountableOccurrenceMap<String> mergedOccurrenceMap = occurrenceMap.merge(otherOccurrenceMap);
        assertThat(mergedOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.VALID_MERGE_WITH_UNCOUNTABLE_DATA_PROVIDER_NAME)
    public void shouldMergeWithUncountableReturn(CountableOccurrenceMap<String> occurrenceMap, OccurrenceMap<String> otherOccurrenceMap, OccurrenceMap<String> controlOccurrenceMap) {
        OccurrenceMap<String> mergedOccurrenceMap = occurrenceMap.merge(otherOccurrenceMap);
        assertThat(mergedOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.INVALID_FILTER_GREATER_OR_EQUALS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFilterGreaterOrEqualsThrowException(CountableOccurrenceMap<String> occurrenceMap, int threshold) {
        occurrenceMap.filterGreaterOrEqualsOccurrences(threshold);
        fail();
    }

    @Test(dataProviderClass = CountableOccurrenceMapTestDataProvider.class,
            dataProvider = CountableOccurrenceMapTestDataProvider.VALID_FILTER_GREATER_OR_EQUALS_DATA_PROVIDER_NAME)
    public void shouldFilterGreaterOrEqualsReturn(CountableOccurrenceMap<String> occurrenceMap, int threshold, Set<String> controlSet) {
        Set<String> resultSet = occurrenceMap.filterGreaterOrEqualsOccurrences(threshold);
        assertThat(resultSet, is(equalTo(controlSet)));
    }
}
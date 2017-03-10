package hu.bioinformatics.biolaboratory.utils.datastructures;

import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.fail;

/**
 * Test cases for the {@link OccurrenceMap} class.
 *
 * @author Attila Radi
 */
@Test(dataProviderClass = OccurrenceMapTestDataProvider.class)
public class OccurrenceMapTest {

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_BUILD_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildThrowException(Map<String, Integer> occurrences) {
        OccurrenceMap.build(occurrences);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.VALID_BUILD_DATA_PROVIDER_NAME)
    public void shouldBuildReturn(Map<String, Integer> occurrences) {
        OccurrenceMap<String> occurrenceMap = OccurrenceMap.build(occurrences);
        occurrences = occurrences == null ? ImmutableMap.of() : occurrences;
        assertThat(occurrenceMap.getOccurrences(), is(equalTo(occurrences)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.EMPTY_OCCURRENCE_MAP_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetOccurrenceThrowException(OccurrenceMap<String> occurrenceMap) {
        occurrenceMap.getOccurrence(null);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.GET_OCCURRENCE_DATA_PROVIDER_NAME)
    public void shouldGetOccurrenceReturn(OccurrenceMap<String> occurrenceMap, String key, Integer controlValue) {
        int occurrenceValue = occurrenceMap.getOccurrence(key);
        assertThat(occurrenceValue, is(equalTo(controlValue)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.IS_EQUALS_DATA_PROVIDER_NAME)
    public void shouldEqualsReturn(OccurrenceMap<String> occurrenceMap, Object rightHand, boolean isEquals) {
        assertThat(occurrenceMap.equals(rightHand), is(isEquals));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.COPY_DATA_PROVIDER_NAME)
    public void shouldCreateCopy(OccurrenceMap<String> occurrenceMap) {
        OccurrenceMap<String> occurrenceMapCopy = occurrenceMap.copy();
        assertThat(occurrenceMapCopy, allOf(
                is(not(sameInstance(occurrenceMap))),
                is(equalTo(occurrenceMap))
        ));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.TO_COUNTABLE_DATA_PROVIDER_NAME)
    public void shouldToCountableReturn(OccurrenceMap<String> occurrenceMap) {
        CountableOccurrenceMap<String> countableOccurrenceMap = occurrenceMap.toCountable();
        CountableOccurrenceMap<String> controlOccurrenceMap = CountableOccurrenceMap.build(occurrenceMap.getOccurrences());
        assertThat(countableOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }
    
    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_DECREASE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldDecreaseOccurrenceThrowException(OccurrenceMap<String> occurrenceMap, String key) {
        occurrenceMap.decrease(key);
        fail();
    }
    
    @Test(dataProvider = OccurrenceMapTestDataProvider.VALID_DECREASE_DATA_PROVIDER_NAME)
    public void shouldDecreaseOccurrenceReturn(OccurrenceMap<String> occurrenceMap, String key, OccurrenceMap<String> controlOccurrenceMap) {
        occurrenceMap.decrease(key);
        assertThat(occurrenceMap, is(equalTo(controlOccurrenceMap)));
    }
    
    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_INCREASE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldIncreaseOccurrenceInvalidKeyThrowException(OccurrenceMap<String> occurrenceMap, String key) {
        occurrenceMap.increase(key);
        fail();
    }
    
    @Test(dataProvider = OccurrenceMapTestDataProvider.VALID_INCREASE_DATA_PROVIDER_NAME)
    public void shouldIncreaseOccurrenceReturn(OccurrenceMap<String> occurrenceMap, String key, OccurrenceMap<String> controlOccurrenceMap) {
        occurrenceMap.increase(key);
        assertThat(occurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.MAXIMUM_OCCURRENCE_VALUE_DATA_PROVIDER_NAME)
    public void shouldMaximumOccurrenceValueReturn(OccurrenceMap<String> occurrenceMap, int controlMaximumValue) {
        int maximumValue = occurrenceMap.maximumOccurrenceValue();
        assertThat(maximumValue, is(equalTo(controlMaximumValue)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.MINIMUM_OCCURRENCE_VALUE_DATA_PROVIDER_NAME)
    public void shouldMinimumOccurrenceValueReturn(OccurrenceMap<String> occurrenceMap) {
        int minimumValue = occurrenceMap.minimumOccurrenceValue();
        assertThat(minimumValue, is(equalTo(0)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.EMPTY_OCCURRENCE_MAP_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFilterOccurrencesThrowException(OccurrenceMap<String> occurrenceMap) {
        occurrenceMap.filterOccurrences(null);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_FILTER_RELATIONAL_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFilterEqualsOccurrencesThrowException(OccurrenceMap<String> occurrences, int threshold) {
        occurrences.filterEqualsOccurrences(threshold);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.VALID_FILTER_EQUALS_DATA_PROVIDER_NAME)
    public void shouldFilterEqualsOccurrencesReturn(OccurrenceMap<String> occurrences, int value, Set<String> controlSet) {
        Set<String> filteredOccurrences = occurrences.filterEqualsOccurrences(value);
        assertThat(filteredOccurrences, is(equalTo(controlSet)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_FILTER_RELATIONAL_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFilterGreaterOccurrencesThrowException(OccurrenceMap<String> occurrences, int threshold) {
        occurrences.filterGreaterOccurrences(threshold);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.VALID_FILTER_GREATER_DATA_PROVIDER_NAME)
    public void shouldFilterGreaterOccurrencesReturn(OccurrenceMap<String> occurrences, int threshold, Set<String> controlSet) {
        Set<String> filteredOccurrences = occurrences.filterGreaterOccurrences(threshold);
        assertThat(filteredOccurrences, is(equalTo(controlSet)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_FILTER_RELATIONAL_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFilterGreaterOrEqualsOccurrencesThrowException(OccurrenceMap<String> occurrences, int threshold) {
        occurrences.filterGreaterOrEqualsOccurrences(threshold);
        fail();
    }
    
    @Test(dataProvider = OccurrenceMapTestDataProvider.VALID_FILTER_GREATER_OR_EQUALS_DATA_PROVIDER_NAME)
    public void shouldFilterGreaterOrEqualsOccurrencesReturn(OccurrenceMap<String> occurrences, int threshold, Set<String> controlSet) {
        Set<String> filteredOccurrences = occurrences.filterGreaterOrEqualsOccurrences(threshold);
        assertThat(filteredOccurrences, is(equalTo(controlSet)));
    }
    
    @Test(dataProvider = OccurrenceMapTestDataProvider.MOST_FREQUENT_OCCURRENCES_DATA_PROVIDER_NAME)
    public void shouldFilterMostFrequentOccurrencesReturn(OccurrenceMap<String> occurrenceMap, Set<String> controlSet) {
        Set<String> filteredOccurrences = occurrenceMap.filterMostFrequentOccurrences();
        assertThat(filteredOccurrences, is(equalTo(controlSet)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_MERGE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldMergeThrowException(OccurrenceMap<String> occurrenceMap, OccurrenceMap<String> otherOccurrenceMap) {
        occurrenceMap.merge(otherOccurrenceMap);
        fail();
    }
    
    @Test(dataProvider = OccurrenceMapTestDataProvider.VALID_MERGE_DATA_PROVIDER_NAME)
    public void shouldMergeReturn(OccurrenceMap<String> occurrenceMap, OccurrenceMap<String> otherOccurrenceMap, OccurrenceMap<String> controlOccurrenceMap) {
        OccurrenceMap<String> mergedOccurrenceMap = occurrenceMap.merge(otherOccurrenceMap);
        assertThat(mergedOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }
}


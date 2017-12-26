package hu.bioinformatics.biolaboratory.utils.datastructures;

import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
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
    public void shouldBuildReturn(Map<String, Integer> occurrences, Map<String, Integer> controlOccurrences) {
        OccurrenceMap<String> occurrenceMap = OccurrenceMap.build(occurrences);
        assertThat(occurrenceMap.getOccurrencesInMap(), is(equalTo(controlOccurrences)));
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

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSumOccurrencesFromArrayThrowException(OccurrenceMap<String> occurrenceMap, String[] keys) {
        occurrenceMap.sumOccurrences(keys);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.SUM_OCCURRENCES_DATA_PROVIDER_NAME)
    public void shouldSumOccurrencesFromArrayReturn(OccurrenceMap<String> occurrenceMap, String[] keys, int controlSum) {
        int sum = occurrenceMap.sumOccurrences(keys);
        assertThat(sum, is(equalTo(controlSum)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_OPERATION_SET_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSumOccurrencesAboutSetThrowException(OccurrenceMap<String> occurrenceMap, Set<String> keySet) {
        occurrenceMap.sumOccurrences(keySet);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.SUM_OCCURRENCES_ABOUT_SET_DATA_PROVIDER_NAME)
    public void shouldSumOccurrencesAboutSetReturn(OccurrenceMap<String> occurrenceMap, Set<String> keySet, int controlSum) {
        int sum = occurrenceMap.sumOccurrences(keySet);
        assertThat(sum, is(equalTo(controlSum)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.SUM_TOTAL_OCCURRENCES_DATA_PROVIDER_NAME)
    public void shouldSumTotalOccurrencesReturn(OccurrenceMap<String> occurrenceMap, int controlSum) {
        int sum = occurrenceMap.sumTotalOccurrence();
        assertThat(sum, is(equalTo(controlSum)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.EMPTY_OCCURRENCE_MAP_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldOccurrenceRatioThrowException(OccurrenceMap<String> occurrenceMap) {
        occurrenceMap.getOccurrence(null);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.OCCURRENCE_RATIO_DATA_PROVIDER)
    public void shouldOccurrenceRatioReturn(OccurrenceMap<String> occurrenceMap, String key, double controlRatio) {
        double ratio = occurrenceMap.occurrenceRatio(key);
        assertThat(ratio, is(closeTo(controlRatio, 0.001)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldAccumulatedOccurrenceRatioThrowException(OccurrenceMap<String> occurrenceMap, String[] keys) {
        occurrenceMap.accumulatedOccurrenceRatio(keys);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.ACCUMULATED_OCCURRENCE_RATIO_DATA_PROVIDER)
    public void shouldAccumulatedOccurrenceRatioReturn(OccurrenceMap<String> occurrenceMap, String[] keys, double controlRatio) {
        double ratio = occurrenceMap.accumulatedOccurrenceRatio(keys);
        assertThat(ratio, is(closeTo(controlRatio, 0.001)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_OPERATION_SET_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldAccumulatedOccurrenceRatioSetThrowException(OccurrenceMap<String> occurrenceMap, Set<String> keySet) {
        occurrenceMap.accumulatedOccurrenceRatio(keySet);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.ACCUMULATED_OCCURRENCE_RATIO_SET_DATA_PROVIDER)
    public void shouldAccumulatedOccurrenceRatioSetReturn(OccurrenceMap<String> occurrenceMap, Set<String> keySet, double controlRatio) {
        double ratio = occurrenceMap.accumulatedOccurrenceRatio(keySet);
        assertThat(ratio, is(closeTo(controlRatio, 0.001)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldOccurrenceRatiosThrowException(OccurrenceMap<String> occurrenceMap, String[] keys) {
        occurrenceMap.occurrenceRatios(keys);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.ALL_OCCURRENCE_RATIOS_DATA_PROVIDER)
    public void shouldAllOccurrenceRatiosReturn(OccurrenceMap<String> occurrenceMap, Map<String, Double> controlRatioMap) {
        Map<String, Double> allOccurrenceRatiosMap = occurrenceMap.allOccurrenceRatios();
        allOccurrenceRatiosMap.entrySet().forEach(entry -> assertThat(entry.getValue(), is(closeTo(controlRatioMap.get(entry.getKey()), 0.001))));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.OCCURRENCE_RATIOS_DATA_PROVIDER)
    public void shouldOccurrenceRatiosReturn(OccurrenceMap<String> occurrenceMap, String[] keys, Map<String, Double> controlRatioMap) {
        Map<String, Double> ratioMap = occurrenceMap.occurrenceRatios(keys);
        ratioMap.entrySet().forEach(entry -> assertThat(entry.getValue(), is(closeTo(controlRatioMap.get(entry.getKey()), 0.001))));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_OPERATION_SET_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldOccurrenceRatiosSetThrowException(OccurrenceMap<String> occurrenceMap, Set<String> keySet) {
        occurrenceMap.occurrenceRatios(keySet);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.OCCURRENCE_RATIOS_SET_DATA_PROVIDER)
    public void shouldOccurrenceRatiosSetReturn(OccurrenceMap<String> occurrenceMap, Set<String> keySet, Map<String, Double> controlRatioMap) {
        Map<String, Double> ratioMap = occurrenceMap.occurrenceRatios(keySet);
        ratioMap.entrySet().forEach(entry -> assertThat(entry.getValue(), is(closeTo(controlRatioMap.get(entry.getKey()), 0.001))));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSubSetThrowsException(OccurrenceMap<String> occurrenceMap, String[] keys) {
        occurrenceMap.subSet(keys);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.SUB_SET_DATA_PROVIDER_NAME)
    public void shouldSubSetReturn(OccurrenceMap<String> occurrenceMap, String[] keys, OccurrenceMap<String> controlSubSet) {
        OccurrenceMap<String> subSet = occurrenceMap.subSet(keys);
        assertThat(subSet, is(equalTo(controlSubSet)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_OPERATION_SET_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSubSetAboutSetThrowsException(OccurrenceMap<String> occurrenceMap, Set<String> subSet) {
        occurrenceMap.subSet(subSet);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.SUB_SET_ABOUT_SET_DATA_PROVIDER_NAME)
    public void shouldSubSetAboutSetReturn(OccurrenceMap<String> occurrenceMap, Set<String> keySet, OccurrenceMap<String> controlSubSet) {
        OccurrenceMap<String> subSet = occurrenceMap.subSet(keySet);
        assertThat(subSet, is(equalTo(controlSubSet)));
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
    public void shouldToCountableReturn(OccurrenceMap<String> occurrenceMap, CountableOccurrenceMap<String> controlOccurrenceMap) {
        CountableOccurrenceMap<String> countableOccurrenceMap = occurrenceMap.toCountable();
        assertThat(countableOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldToCountableWithThrowException(OccurrenceMap<String> occurrenceMap, String keys[]) {
        occurrenceMap.toCountableWith(keys);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.TO_COUNTABLE_WITH_DATA_PROVIDER_NAME)
    public void shouldToCountableWithReturn(OccurrenceMap<String> occurrenceMap, String keys[], CountableOccurrenceMap<String> controlOccurrenceMap) {
        CountableOccurrenceMap<String> countableOccurrenceMap = occurrenceMap.toCountableWith(keys);
        assertThat(countableOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_OPERATION_SET_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldToCountableWithSetThrowException(OccurrenceMap<String> occurrenceMap, Set<String> keySet) {
        occurrenceMap.toCountableWithSet(keySet);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.TO_COUNTABLE_WITH_SET_DATA_PROVIDER_NAME)
    public void shouldToCountableWithSetReturn(OccurrenceMap<String> occurrenceMap, Set<String> keySet, CountableOccurrenceMap<String> controlOccurrenceMap) {
        CountableOccurrenceMap<String> countableOccurrenceMap = occurrenceMap.toCountableWithSet(keySet);
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

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_SUBTRACT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSubtractThrowException(OccurrenceMap<String> occurrenceMap, String key, int number) {
        occurrenceMap.subtract(key, number);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.VALID_SUBTRACT_DATA_PROVIDER_NAME)
    public void shouldSubtractReturn(OccurrenceMap<String> occurrenceMap, String key, int number, OccurrenceMap<String> controlOccurrenceMap) {
        occurrenceMap.subtract(key, number);
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

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_ADD_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldAddThrowException(OccurrenceMap<String> occurrenceMap, String key, int number) {
        occurrenceMap.add(key, number);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.VALID_ADD_DATA_PROVIDER_NAME)
    public void shouldAddReturn(OccurrenceMap<String> occurrenceMap, String key, int number, OccurrenceMap<String> controlOccurrenceMap) {
        occurrenceMap.add(key, number);
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
    public void shouldFilterThrowException(OccurrenceMap<String> occurrenceMap) {
        occurrenceMap.filterOccurrences(null);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.FILTER_DATA_PROVIDER_NAME)
    public void shouldFilterReturn(OccurrenceMap<String> occurrenceMap, Predicate<Map.Entry<String, Integer>> predicate, OccurrenceMap<String> controlOccurrenceMap) {
        OccurrenceMap<String> filteredOccurrenceMap = occurrenceMap.filter(predicate);
        assertThat(filteredOccurrenceMap, is(equalTo(controlOccurrenceMap)));
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

    @Test(dataProvider = OccurrenceMapTestDataProvider.INVALID_FILTER_MERGE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFilterMergeThrowException(OccurrenceMap<String> occurrenceMap, OccurrenceMap<String> otherOccurrenceMap, Predicate<Map.Entry<String, Integer>> filterPredicate) {
        occurrenceMap.filterMerge(otherOccurrenceMap, filterPredicate);
        fail();
    }

    @Test(dataProvider = OccurrenceMapTestDataProvider.VALID_FILTER_MERGE_DATA_PROVIDER_NAME)
    public void shouldFilterMergeReturn(OccurrenceMap<String> occurrenceMap, OccurrenceMap<String> otherOccurrenceMap, Predicate<Map.Entry<String, Integer>> filterPredicate, OccurrenceMap<String> controlOccurrenceMap) {
        OccurrenceMap<String> mergedOccurrenceMap = occurrenceMap.filterMerge(otherOccurrenceMap, filterPredicate);
        assertThat(mergedOccurrenceMap, is(equalTo(controlOccurrenceMap)));
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


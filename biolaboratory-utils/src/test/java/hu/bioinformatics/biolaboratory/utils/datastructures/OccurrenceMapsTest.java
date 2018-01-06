package hu.bioinformatics.biolaboratory.utils.datastructures;


import org.testng.annotations.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

/**
 * Test cases for {@link OccurrenceMaps} class.
 *
 * @author Attila Radi
 */
@Test(dataProviderClass = OccurrenceMapsTestDataProvider.class)
public class OccurrenceMapsTest {

    @Test(dataProvider = OccurrenceMapsTestDataProvider.INVALID_MERGE_OCCURRENCE_MAPS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldMergeOccurrenceMapsThrowException(Collection<OccurrenceMap<String>> occurrenceMapCollection) {
        OccurrenceMaps.mergeOccurrenceMaps(occurrenceMapCollection);
    }

    @Test(dataProvider = OccurrenceMapsTestDataProvider.VALID_MERGE_OCCURRENCE_MAPS_DATA_PROVIDER_NAME)
    public void shouldMergeOccurrenceMapsReturn(Collection<OccurrenceMap<String>> occurrenceMapCollection, OccurrenceMap<String> controlOccurrenceMap) {
        OccurrenceMap<String> mergedOccurrenceMap = OccurrenceMaps.mergeOccurrenceMaps(occurrenceMapCollection);
        assertThat(mergedOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = OccurrenceMapsTestDataProvider.INVALID_MERGE_OCCURRENCE_MAPS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldMergeOccurrenceMapsVarargsThrowException(Collection<OccurrenceMap<String>> occurrenceMapCollection) {
        OccurrenceMap<String>[] occurrenceMapArray = null;
        if (occurrenceMapCollection != null) {
            occurrenceMapArray = new OccurrenceMap[occurrenceMapCollection.size()];
            occurrenceMapCollection.toArray(occurrenceMapArray);
        }
        OccurrenceMaps.mergeOccurrenceMaps(occurrenceMapArray);
    }

    @Test(dataProvider = OccurrenceMapsTestDataProvider.VALID_MERGE_OCCURRENCE_MAPS_DATA_PROVIDER_NAME)
    public void shouldMergeOccurrenceMapsVarargsReturn(Collection<OccurrenceMap<String>> occurrenceMapCollection, OccurrenceMap<String> controlOccurrenceMap) {
        OccurrenceMap<String>[] occurrenceMapArray = new OccurrenceMap[occurrenceMapCollection.size()];
        OccurrenceMap<String> mergedOccurrenceMap = OccurrenceMaps.mergeOccurrenceMaps(occurrenceMapCollection.toArray(occurrenceMapArray));
        assertThat(mergedOccurrenceMap, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = OccurrenceMapsTestDataProvider.INVALID_MERGE_OCCURRENCE_MAPS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetMostFrequentOccurrencesThrowException(Collection<OccurrenceMap<String>> occurrenceMapCollection) {
       OccurrenceMaps.getMostFrequentOccurrences(occurrenceMapCollection);
   }

    @Test(dataProvider = OccurrenceMapsTestDataProvider.VALID_GET_MOST_FREQUENT_OCCURRENCES_DATA_PROVIDER_NAME)
    public void shouldGetMostFrequentOccurrencesReturn(Collection<OccurrenceMap<String>> occurrenceMapCollection, OccurrenceMap<String> controlOccurrenceMap) {
        OccurrenceMap<String> mostFrequentOccurrences = OccurrenceMaps.getMostFrequentOccurrences(occurrenceMapCollection);
        assertThat(mostFrequentOccurrences, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = OccurrenceMapsTestDataProvider.INVALID_MERGE_OCCURRENCE_MAPS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetMostFrequentOccurrencesVarargsThrowException(Collection<OccurrenceMap<String>> occurrenceMapCollection) {
        OccurrenceMap<String>[] occurrenceMapArray = null;
        if (occurrenceMapCollection != null) {
            occurrenceMapArray = new OccurrenceMap[occurrenceMapCollection.size()];
            occurrenceMapCollection.toArray(occurrenceMapArray);
        }
        OccurrenceMaps.getMostFrequentOccurrences(occurrenceMapArray);
    }

    @Test(dataProvider = OccurrenceMapsTestDataProvider.VALID_GET_MOST_FREQUENT_OCCURRENCES_DATA_PROVIDER_NAME)
    public void shouldGetMostFrequentOccurrencesVarargsReturn(Collection<OccurrenceMap<String>> occurrenceMapCollection, OccurrenceMap<String> controlOccurrenceMap) {
        OccurrenceMap<String>[] occurrenceMapArray = new OccurrenceMap[occurrenceMapCollection.size()];
        OccurrenceMap<String> mostFrequentOccurrences = OccurrenceMaps.getMostFrequentOccurrences(occurrenceMapCollection.toArray(occurrenceMapArray));
        assertThat(mostFrequentOccurrences, is(equalTo(controlOccurrenceMap)));
    }
}
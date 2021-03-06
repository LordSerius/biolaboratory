package hu.bioinformatics.biolaboratory.utils.datastructures;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.testng.annotations.DataProvider;

/**
 * Test data provider for {@link OccurrenceMapsTest} test class.
 *
 * @author Attila Radi
 */
public class OccurrenceMapsTestDataProvider {

    static final String INVALID_MERGE_OCCURRENCE_MAPS_DATA_PROVIDER_NAME = "invalidMergeOccurrenceMapsDataProvider";

    @DataProvider(name = INVALID_MERGE_OCCURRENCE_MAPS_DATA_PROVIDER_NAME)
    private Object[][] invalidMergeOccurrenceMapsDataProvider() {
        return new Object[][] {
                { null },
                { Lists.newArrayList(null, OccurrenceMap.build(ImmutableMap.of("G", 1))) }
        };
    }

    static final String VALID_MERGE_OCCURRENCE_MAPS_DATA_PROVIDER_NAME = "validMergeOccurrenceMapsDataProvider";

    @DataProvider(name = VALID_MERGE_OCCURRENCE_MAPS_DATA_PROVIDER_NAME)
    private Object[][] validMergeOccurrenceMapsDataProvider() {
        return new Object[][] {
                { ImmutableList.of(OccurrenceMap.build(ImmutableMap.of("AG", 1)), OccurrenceMap.build(ImmutableMap.of("CG", 1)), OccurrenceMap.build(ImmutableMap.of("AA", 1)), OccurrenceMap.build(ImmutableMap.of("AG", 1)), OccurrenceMap.build()), OccurrenceMap.build(ImmutableMap.of("AA", 1, "AG", 2, "CG", 1)) },
                { ImmutableList.of(OccurrenceMap.build(), OccurrenceMap.build(), OccurrenceMap.build()), OccurrenceMap.build() }
        };
    }

    static final String VALID_GET_MOST_FREQUENT_OCCURRENCES_DATA_PROVIDER_NAME = "validGetMostFrequentOccurrencesDataProvider";

    @DataProvider(name = VALID_GET_MOST_FREQUENT_OCCURRENCES_DATA_PROVIDER_NAME)
    private Object[][] validGetMostFrequentOccurrencesDataProvider() {
        return new Object[][] {
                { ImmutableList.of(OccurrenceMap.build(ImmutableMap.of("AG", 1)), OccurrenceMap.build(ImmutableMap.of("CG", 1)), OccurrenceMap.build(ImmutableMap.of("AA", 1)), OccurrenceMap.build(ImmutableMap.of("AG", 1)), OccurrenceMap.build()), OccurrenceMap.build(ImmutableMap.of("AG", 2)) },
                { ImmutableList.of(OccurrenceMap.build(), OccurrenceMap.build(), OccurrenceMap.build()), OccurrenceMap.build() }
        };
    }
}

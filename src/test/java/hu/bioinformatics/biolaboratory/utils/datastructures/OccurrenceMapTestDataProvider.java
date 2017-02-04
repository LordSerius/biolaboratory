package hu.bioinformatics.biolaboratory.utils.datastructures;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Test data provider for {@link OccurrenceMapTest} test class.
 *
 * @author Attila Radi
 */
public class OccurrenceMapTestDataProvider {

    static final String INVALID_BUILD_DATA_PROVIDER_NAME = "invalidBuildDataProvider";

    @DataProvider(name = INVALID_BUILD_DATA_PROVIDER_NAME)
    Object[][] invalidBuildDataProvider() {
        Map<String, Integer> nullKeyMap = new HashMap<>();
        nullKeyMap.put("A", 1);
        nullKeyMap.put(null, 1);

        Map<String, Integer> nullValueMap = new HashMap<>();
        nullValueMap.put("A", 1);
        nullValueMap.put("G", null);

        return new Object[][] {
                { ImmutableMap.of("A", -1) },
                { ImmutableMap.of("A", 0) },
                { nullKeyMap },
                { nullValueMap }
        };
    }

    static final String VALID_BUILD_DATA_PROVIDER_NAME = "validBuildDataProvider";

    @DataProvider(name = VALID_BUILD_DATA_PROVIDER_NAME)
    Object[][] validBuildDataProvider() {
        return new Object[][] {
                { ImmutableMap.of() },
                { null },
                { ImmutableMap.of("A", 2) }
        };
    }

    static final String EMPTY_OCCURRENCE_MAP_DATA_PROVIDER_NAME = "emptyOccurrenceMapDataProvider";

    @DataProvider(name = EMPTY_OCCURRENCE_MAP_DATA_PROVIDER_NAME)
    Object[][] emptyOccurrenceMapDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build() }
        };
    }

    static final String COPY_DATA_PROVIDER_NAME = "copyDataProvider";

    @DataProvider(name = COPY_DATA_PROVIDER_NAME)
    Object[][] copyDataProvider() {
        return new Object[][] {
               { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)) }
        };
    }

    static final String GET_OCCURRENCE_DATA_PROVIDER_NAME = "getOccurrence";

    @DataProvider(name= GET_OCCURRENCE_DATA_PROVIDER_NAME)
    Object[][] getOccurrenceDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 2)), "A", 2 },
                { OccurrenceMap.build(ImmutableMap.of("A", 2)), "G", 0 },
                { OccurrenceMap.build(), "A", 0 }
        };
    }

    static final String IS_EQUALS_DATA_PROVIDER_NAME = "isEqualsDataProvider";
    
    @DataProvider(name = IS_EQUALS_DATA_PROVIDER_NAME)
    Object[][] isEqualsDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), null, false },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), "string", false },
                { OccurrenceMap.build(), OccurrenceMap.build(), true },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1)), true },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 2)), false },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("C", 1)), false },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(), false },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), false },
                { OccurrenceMap.build(), CountableOccurrenceMap.build(), false }
        };
    }

    static final String INVALID_DECREASE_DATA_PROVIDER_NAME = "invalidDecreaseDataProvider";

    @DataProvider(name = INVALID_DECREASE_DATA_PROVIDER_NAME)
    Object[][] invalidDecreaseOrRemoveDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), null },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), "G" }
        };
    }

    static final String VALID_DECREASE_DATA_PROVIDER_NAME = "validDecreaseDataProvider";

    @DataProvider(name = VALID_DECREASE_DATA_PROVIDER_NAME)
    Object[][] validDecreaseOrRemoveDataProvider() {
        Map<String, Integer> oneElementHashMap = Maps.newHashMap();
        oneElementHashMap.put("A", 1);
        Map<String, Integer> twoElementHashMap = Maps.newHashMap();
        twoElementHashMap.put("A", 2);
        twoElementHashMap.put("G", 1);
        return new Object[][] {
                { OccurrenceMap.build(new HashMap<>(twoElementHashMap)), "A", OccurrenceMap.build(ImmutableMap.of("A", 1, "G", 1)) },
                { OccurrenceMap.build(new HashMap<>(twoElementHashMap)), "G", OccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { OccurrenceMap.build(new HashMap<>(oneElementHashMap)), "A", OccurrenceMap.build() }
        };
    }

    static final String INVALID_INCREASE_DATA_PROVIDER_NAME = "invalidIncreaseDataProvider";

    @DataProvider(name = INVALID_INCREASE_DATA_PROVIDER_NAME)
    Object[][] invalidIncreaseOrPutDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), null }
        };
    }

    static final String VALID_INCREASE_DATA_PROVIDER_NAME = "validIncreaseDataProvider";

    @DataProvider(name = VALID_INCREASE_DATA_PROVIDER_NAME)
    Object[][] validIncreaseOrPutDataProvider() {
        Map<String, Integer> oneElementHashMap = Maps.newHashMap();
        oneElementHashMap.put("A", 1);
        return new Object[][] {
                { OccurrenceMap.build(), "A", OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(new HashMap<>(oneElementHashMap)), "A", OccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { OccurrenceMap.build(new HashMap<>(oneElementHashMap)), "G", OccurrenceMap.build(ImmutableMap.of("A", 1, "G", 1)) }

        };
    }
    
    static final String INVALID_FILTER_GREATER_OR_EQUALS_DATA_PROVIDER_NAME = "invalidFilterGreaterOrEqualsDataProvider";
    
    @DataProvider(name = INVALID_FILTER_GREATER_OR_EQUALS_DATA_PROVIDER_NAME)
    Object[][] invalidFilterDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), 0 },
                { OccurrenceMap.build(), -1 }
        };
    }
    
    static final String VALID_FILTER_GREATER_OR_EQUALS_DATA_PROVIDER_NAME = "validFilterGreaterOrEqualsDataProvider";
    
    @DataProvider(name = VALID_FILTER_GREATER_OR_EQUALS_DATA_PROVIDER_NAME)
    Object[][] validFilterGreaterOrEqualsDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 5, ImmutableSet.of("T") },
                { OccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 4, ImmutableSet.of("A", "C", "T") },
                { OccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 1, ImmutableSet.of("A", "G", "C", "T") },
                { OccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 6, ImmutableSet.of() },
                { OccurrenceMap.build(), 3, ImmutableSet.of() }
        };
    }
    
    static final String MOST_FREQUENT_OCCURRENCES_DATA_PROVIDER_NAME = "mostFrequentOccurrences";
    
    @DataProvider(name = MOST_FREQUENT_OCCURRENCES_DATA_PROVIDER_NAME)
    Object[][] mostFrequentDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 3)), ImmutableSet.of("C") },
                { OccurrenceMap.build(ImmutableMap.of("A", 2, "C", 2)), ImmutableSet.of("A", "C") },
                { OccurrenceMap.build(), ImmutableSet.of() }
        };
    }

    static final String INVALID_MERGE_DATA_PROVIDER_NAME = "invalidMergeDataProvider";

    @DataProvider(name = INVALID_MERGE_DATA_PROVIDER_NAME)
    Object[][] invalidMergeDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), null }
        };
    }

    static final String VALID_MERGE_DATA_PROVIDER_NAME = "validMergeDataProvider";
    
    @DataProvider(name = VALID_MERGE_DATA_PROVIDER_NAME)
    Object[][] validMergeDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(), OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("C", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("C", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("C", 0)), OccurrenceMap.build(ImmutableMap.of("A", 1)) }
        };
    }
}

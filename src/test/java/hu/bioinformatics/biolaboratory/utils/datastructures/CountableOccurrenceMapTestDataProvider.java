package hu.bioinformatics.biolaboratory.utils.datastructures;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Test data provider for {@link CountableOccurrenceMap} test class.
 *
 * @author Attila Radi
 */
public class CountableOccurrenceMapTestDataProvider {

    static final String INVALID_BUILD_FROM_MAP_DATA_PROVIDER_NAME = "invalidBuildFromMapDataProvider";

    @DataProvider(name = INVALID_BUILD_FROM_MAP_DATA_PROVIDER_NAME)
    Object[][] invalidBuildFromMapDataProvider() {
        Map<String, Integer> nullKeyMap = new HashMap<>();
        nullKeyMap.put("A", 1);
        nullKeyMap.put(null, 1);

        Map<String, Integer> nullValueMap = new HashMap<>();
        nullValueMap.put("A", 1);
        nullValueMap.put("G", null);

        return new Object[][] {
                { ImmutableMap.of("A", -1) },
                { nullKeyMap },
                { nullValueMap }
        };
    }

    static final String VALID_BUILD_FROM_MAP_DATA_PROVIDER_NAME = "validBuildFromMapDataProvider";

    @DataProvider(name = VALID_BUILD_FROM_MAP_DATA_PROVIDER_NAME)
    Object[][] validBuildFromMapDataProvider() {
        return new Object[][] {
                { ImmutableMap.of() },
                { null },
                { ImmutableMap.of("A", 0) },
                { ImmutableMap.of("A", 2) }
        };
    }

    static final String INVALID_BUILD_FROM_SET_DATA_PROVIDER_NAME = "invalidBuildFromSetDataProvider";

    @DataProvider(name = INVALID_BUILD_FROM_SET_DATA_PROVIDER_NAME)
    Object[][] invalidBuildFromSetDataProvider() {
        return new Object[][] {
                { Sets.newHashSet("A", null) }
        };
    }

    static final String VALID_BUILD_FROM_SET_DATA_PROVIDER_NAME = "validBuildFromSetDataProvider";

    @DataProvider(name = VALID_BUILD_FROM_SET_DATA_PROVIDER_NAME)
    Object[][] validBuildFromSetDataProvider() {
        return new Object[][] {
                { null, CountableOccurrenceMap.build() },
                { ImmutableSet.of(), CountableOccurrenceMap.build() },
                { ImmutableSet.of("A"), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) }
        };
    }

    static final String INVALID_TO_COUNTABLE_DATA_PROVIDER = "invalidToCountableDataProvider";

    @DataProvider(name = INVALID_TO_COUNTABLE_DATA_PROVIDER)
    Object[][] invalidToCountableDataProvider() {
        return new Object[][] {
                { null }
        };
    }

    static final String VALID_TO_COUNTABLE_DATA_PROVIDER = "validToCountableDataProvider";

    @DataProvider(name = VALID_TO_COUNTABLE_DATA_PROVIDER)
    Object[][] validToCountableDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) }
        };
    }

    static final String IS_EQUALS_DATA_PROVIDER_NAME = "isEqualsDataProvider";

    @DataProvider(name = IS_EQUALS_DATA_PROVIDER_NAME)
    Object[][] isEqualsDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), null, false },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), "string", false },
                { CountableOccurrenceMap.build(), CountableOccurrenceMap.build(), true },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("A", 2)), false },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("C", 1)), false },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(), false },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), true },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1)), false },
                { CountableOccurrenceMap.build(), OccurrenceMap.build(), false }
        };
    }

    static final String COPY_DATA_PROVIDER_NAME = "copyDataProvider";

    @DataProvider(name = COPY_DATA_PROVIDER_NAME)
    Object[][] copyDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)) }
        };
    }

    static final String INVALID_DECREASE_DATA_PROVIDER_NAME = "invalidDecreaseDataProvider";

    @DataProvider(name = INVALID_DECREASE_DATA_PROVIDER_NAME)
    Object[][] invalidDecreaseDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "T", 0)), null },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "T", 0)), "G" },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "T", 0)), "T" }
        };
    }

    static final String VALID_DECREASE_DATA_PROVIDER_NAME = "validDecreaseDataProvider";

    @DataProvider(name = VALID_DECREASE_DATA_PROVIDER_NAME)
    Object[][] validDecreaseDataProvider() {
        Map<String, Integer> oneElementHashMap = Maps.newHashMap();
        oneElementHashMap.put("A", 1);
        Map<String, Integer> twoElementHashMap = Maps.newHashMap();
        twoElementHashMap.put("A", 2);
        twoElementHashMap.put("G", 1);
        return new Object[][] {
                { CountableOccurrenceMap.build(new HashMap<>(twoElementHashMap)), "A", CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "G", 1)) },
                { CountableOccurrenceMap.build(new HashMap<>(twoElementHashMap)), "G", CountableOccurrenceMap.build(ImmutableMap.of("A", 2, "G", 0)) },
                { CountableOccurrenceMap.build(new HashMap<>(oneElementHashMap)), "A", CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) }
        };
    }

    static final String INVALID_MERGE_WITH_COUNTABLE_DATA_PROVIDER_NAME = "invalidMergeWithCountableDataProvider";

    @DataProvider(name = INVALID_MERGE_WITH_COUNTABLE_DATA_PROVIDER_NAME)
    Object[][] invalidMergeWithCountableDataProvider() {
        return new Object[][] {
                {CountableOccurrenceMap.build(), null }
        };
    }

    static final String VALID_MERGE_WITH_COUNTABLE_DATA_PROVIDER_NAME = "validMergeWithCountableDataProvider";

    @DataProvider(name = VALID_MERGE_WITH_COUNTABLE_DATA_PROVIDER_NAME)
    Object[][] validMergeWithCountableDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), CountableOccurrenceMap.build(), CountableOccurrenceMap.build() },
                { CountableOccurrenceMap.build(), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("G", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0,"G", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("A", 2)) }
        };
    }

    static final String VALID_MERGE_WITH_UNCOUNTABLE_DATA_PROVIDER_NAME = "validMergeWithUncountableDataProvider";

    @DataProvider(name = VALID_MERGE_WITH_UNCOUNTABLE_DATA_PROVIDER_NAME)
    Object[][] validMergeWithUncountableDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), OccurrenceMap.build(), OccurrenceMap.build() },
                { CountableOccurrenceMap.build(), OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), OccurrenceMap.build(), OccurrenceMap.build() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), OccurrenceMap.build(ImmutableMap.of("G", 1)), OccurrenceMap.build(ImmutableMap.of("G", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(), OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("G", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1, "G", 1)) }
        };
    }

    static final String INVALID_FILTER_GREATER_OR_EQUALS_DATA_PROVIDER_NAME = "invalidFilterGreaterOrEqualsDataProvider";

    @DataProvider(name = INVALID_FILTER_GREATER_OR_EQUALS_DATA_PROVIDER_NAME)
    Object[][] invalidFilterGreaterOrEqualsDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), -1 }
        };
    }

    static final String VALID_FILTER_GREATER_OR_EQUALS_DATA_PROVIDER_NAME = "validFilterGreaterOrEqualsDataProvider";

    @DataProvider(name = VALID_FILTER_GREATER_OR_EQUALS_DATA_PROVIDER_NAME)
    Object[][] validFilterGreaterOrEqualsDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), 0, ImmutableSet.of() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 0, "C", 4, "T", 5)), 5, ImmutableSet.of("T") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 4, ImmutableSet.of("A", "C", "T") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 0, ImmutableSet.of("A", "G", "C", "T") },
        };
    }
}

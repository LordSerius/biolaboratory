package hu.bioinformatics.biolaboratory.utils.datastructures;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

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

    static final String INVALID_GET_OCCURRENCE_DATA_PROVIDER = "invalidGetOccurrenceDataProvider";

    @DataProvider(name = INVALID_GET_OCCURRENCE_DATA_PROVIDER)
    Object[][] invalidGetOccurrenceDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), null },
                { CountableOccurrenceMap.build(), "A" },
                { CountableOccurrenceMap.build(Sets.newHashSet("A")), "C" }
        };
    }

    static final String INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME = "invalidOperationCollectionDataProvider";

    @DataProvider(name = INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME)
    Object[][] invalidOperationCollectionDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), null },
                { CountableOccurrenceMap.build(), new String[] {"A"} },
                { CountableOccurrenceMap.build(Sets.newHashSet("A")), new String[] { "A", null } },
                { CountableOccurrenceMap.build(Sets.newHashSet("A")), new String[] { "A", "C" } }
        };
    }

    static final String INVALID_OPERATION_SET_DATA_PROVIDER_NAME = "invalidOperationSetDataProvider";

    @DataProvider(name = INVALID_OPERATION_SET_DATA_PROVIDER_NAME)
    Object[][] invalidOperationSetDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), null },
                { CountableOccurrenceMap.build(), Sets.newHashSet("A") },
                { CountableOccurrenceMap.build(), Sets.newHashSet("A", null) },
                { CountableOccurrenceMap.build(Sets.newHashSet("A")), Sets.newHashSet("A", "C") }
        };
    }

    static final String SUB_SET_DATA_PROVIDER_NAME = "subSetDataProvider";

    @DataProvider(name = SUB_SET_DATA_PROVIDER_NAME)
    Object[][] subSetsDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), new String[] {}, CountableOccurrenceMap.build() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), new String[] {"A"}, CountableOccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), new String[] {"A"}, CountableOccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), new String[] {"A", "A"}, CountableOccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), new String[] {"A", "C"}, CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)) }
        };
    }

    static final String SUB_SET_ABOUT_SET_DATA_PROVIDER_NAME = "subSetAboutSetDataProvider";

    @DataProvider(name = SUB_SET_ABOUT_SET_DATA_PROVIDER_NAME)
    Object[][] subSetAboutSetDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), ImmutableSet.of(), CountableOccurrenceMap.build() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), ImmutableSet.of("A"), CountableOccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), ImmutableSet.of("A"), CountableOccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), ImmutableSet.of("A", "C"), CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)) }
        };
    }

    static final String MINIMUM_OCCURRENCE_VALUE_DATA_PROVIDER_NAME = "minimumOccurrenceValueDataProvider";

    @DataProvider(name = MINIMUM_OCCURRENCE_VALUE_DATA_PROVIDER_NAME)
    Object[][] minimumOccurrenceValueDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), 0 },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), 1 },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), 1 },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 2, "C", 2)), 2 },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0, "C", 2)), 0 }
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

    static final String LESS_FREQUENT_OCCURRENCES_DATA_PROVIDER_NAME = "lessFrequentOccurrences";

    @DataProvider(name = LESS_FREQUENT_OCCURRENCES_DATA_PROVIDER_NAME)
    Object[][] mostFrequentDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 3)), ImmutableSet.of("A") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0, "C", 2)), ImmutableSet.of("A") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 2, "C", 2)), ImmutableSet.of("A", "C") },
                { CountableOccurrenceMap.build(), ImmutableSet.of() }
        };
    }

    static final String FILTER_DATA_PROVIDER_NAME = "FilterDataProvider";

    @DataProvider(name = FILTER_DATA_PROVIDER_NAME)
    Object[][] filterDataProvider() {
        Predicate<Map.Entry<String, Integer>> truePredicate = entry -> true;
        Predicate<Map.Entry<String, Integer>> falsePredicate = entry -> false;
        Predicate<Map.Entry<String, Integer>> filterPredicate = entry -> entry.getKey().equals("A");

        return new Object[][] {
                { CountableOccurrenceMap.build(), truePredicate, CountableOccurrenceMap.build() },
                { CountableOccurrenceMap.build(), falsePredicate, CountableOccurrenceMap.build() },
                { CountableOccurrenceMap.build(), filterPredicate, CountableOccurrenceMap.build() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), truePredicate, CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), falsePredicate, CountableOccurrenceMap.build() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), filterPredicate, CountableOccurrenceMap.build(ImmutableMap.of("A", 1)) }
        };
    }

    static final String INVALID_FILTER_RELATIONAL_DATA_PROVIDER_NAME = "invalidFilterGreaterOrEqualsDataProvider";

    @DataProvider(name = INVALID_FILTER_RELATIONAL_DATA_PROVIDER_NAME)
    Object[][] invalidFilterGreaterOrEqualsDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), -1 }
        };
    }

    static final String VALID_FILTER_EQUALS_DATA_PROVIDER_NAME = "validFilterEqualsDataProvider";

    @DataProvider(name = VALID_FILTER_EQUALS_DATA_PROVIDER_NAME)
    Object[][] validFilterEqualsDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), 0, ImmutableSet.of() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 0, "C", 4, "T", 5)), 5, ImmutableSet.of("T") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 4, ImmutableSet.of("A", "C") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 0, ImmutableSet.of() },
        };
    }

    static final String VALID_FILTER_SMALLER_DATA_PROVIDER_NAME = "validFilterSmallerDataProvider";

    @DataProvider(name = VALID_FILTER_SMALLER_DATA_PROVIDER_NAME)
    Object[][] validFilterSmallerDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), 0, ImmutableSet.of() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 0, "C", 4, "T", 5)), 5, ImmutableSet.of("A", "G", "C") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 4, ImmutableSet.of("G") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 0, ImmutableSet.of() },
        };
    }

    static final String VALID_FILTER_SMALLER_OR_EQUALS_DATA_PROVIDER_NAME = "validFilterSmallerOrEqualsDataProvider";

    @DataProvider(name = VALID_FILTER_SMALLER_OR_EQUALS_DATA_PROVIDER_NAME)
    Object[][] validFilterSmallerOrEqualsDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), 0, ImmutableSet.of() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 0, "C", 4, "T", 5)), 5, ImmutableSet.of("A", "G", "C", "T") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 4, ImmutableSet.of("A", "G", "C") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 0, ImmutableSet.of() },
        };
    }

    static final String VALID_FILTER_GREATER_DATA_PROVIDER_NAME = "validFilterGreaterDataProvider";

    @DataProvider(name = VALID_FILTER_GREATER_DATA_PROVIDER_NAME)
    Object[][] validFilterGreaterDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), 0, ImmutableSet.of() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 0, "C", 4, "T", 5)), 5, ImmutableSet.of() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 4, ImmutableSet.of("T") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 0, ImmutableSet.of("A", "G", "C", "T") },
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

    static final String FILTER_MERGE_COUNTABLE_DATA_PROVIDER_NAME = "filterMergeCountableDataProvider";

    @DataProvider(name = FILTER_MERGE_COUNTABLE_DATA_PROVIDER_NAME)
    Object[][] filterMergeCountableDataProvider() {
        Predicate<Map.Entry<String, Integer>> filterPredicate = entry -> entry.getKey().equals("A");

        return new Object[][] {
                { CountableOccurrenceMap.build(), CountableOccurrenceMap.build(), filterPredicate, CountableOccurrenceMap.build() },
                { CountableOccurrenceMap.build(), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), filterPredicate, CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(), filterPredicate, CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), filterPredicate, CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("G", 0)), filterPredicate, CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("G", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), filterPredicate, CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), filterPredicate, CountableOccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("G", 0)), CountableOccurrenceMap.build(ImmutableMap.of("T", 0)), filterPredicate, CountableOccurrenceMap.build() }
        };
    }

    static final String INVALID_FILTER_MERGE_DATA_PROVIDER_NAME = "invalidFilterMergeDataProvider";

    @DataProvider(name = INVALID_FILTER_MERGE_DATA_PROVIDER_NAME)
    Object[][] invalidFilterMergeDataProvider() {
        Predicate<Map.Entry<String, Integer>> truePredicate = entry -> true;

        return new Object[][] {
                { CountableOccurrenceMap.build(), null, truePredicate },
                { CountableOccurrenceMap.build(), CountableOccurrenceMap.build(), null }
        };
    }

    static final String VALID_FILTER_MERGE_DATA_PROVIDER_NAME = "validFilterMergeDataProvider";

    @DataProvider(name = VALID_FILTER_MERGE_DATA_PROVIDER_NAME)
    Object[][] validFilterMergeDataProvider() {
        Predicate<Map.Entry<String, Integer>> filterPredicate = entry -> entry.getKey().equals("A");

        return new Object[][] {
                { CountableOccurrenceMap.build(), CountableOccurrenceMap.build(), filterPredicate, CountableOccurrenceMap.build() },
                { CountableOccurrenceMap.build(), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), filterPredicate, CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(), filterPredicate, CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), filterPredicate, CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("G", 0)), filterPredicate, CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("G", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), filterPredicate, CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), filterPredicate, CountableOccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("G", 0)), CountableOccurrenceMap.build(ImmutableMap.of("T", 0)), filterPredicate, CountableOccurrenceMap.build() },
                { CountableOccurrenceMap.build(), OccurrenceMap.build(), filterPredicate, OccurrenceMap.build() },
                { CountableOccurrenceMap.build(), OccurrenceMap.build(ImmutableMap.of("A", 1)), filterPredicate, OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), OccurrenceMap.build(), filterPredicate, OccurrenceMap.build() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), OccurrenceMap.build(ImmutableMap.of("A", 1)), filterPredicate, OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), OccurrenceMap.build(ImmutableMap.of("G", 1)), filterPredicate, OccurrenceMap.build() },
                { CountableOccurrenceMap.build(ImmutableMap.of("G", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1)), filterPredicate, OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(), filterPredicate, OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1)), filterPredicate, OccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("G", 1)), filterPredicate, OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("G", 1)), OccurrenceMap.build(ImmutableMap.of("T", 1)), filterPredicate, OccurrenceMap.build() }
        };
    }

    static final String MERGE_COUNTABLE_DATA_PROVIDER_NAME = "mergeCountableDataProvider";

    @DataProvider(name = MERGE_COUNTABLE_DATA_PROVIDER_NAME)
    Object[][] mergeCountableDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), CountableOccurrenceMap.build(), CountableOccurrenceMap.build() },
                { CountableOccurrenceMap.build(), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("G", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0,"G", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("A", 2)) },
        };
    }

    static final String MERGE_DATA_PROVIDER_NAME = "mergeUncountableDataProvider";

    @DataProvider(name = MERGE_DATA_PROVIDER_NAME)
    Object[][] mergeDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), CountableOccurrenceMap.build(), CountableOccurrenceMap.build() },
                { CountableOccurrenceMap.build(), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("G", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0,"G", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("A", 2)) },
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
}

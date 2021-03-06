package hu.bioinformatics.biolaboratory.utils.datastructures;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
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
    private Object[][] invalidBuildFromMapDataProvider() {
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
    private Object[][] validBuildFromMapDataProvider() {
        return new Object[][] {
                { ImmutableMap.of() },
                { null },
                { ImmutableMap.of("A", 0) },
                { ImmutableMap.of("A", 2) }
        };
    }

    static final String INVALID_BUILD_FROM_SET_DATA_PROVIDER_NAME = "invalidBuildFromSetDataProvider";

    @DataProvider(name = INVALID_BUILD_FROM_SET_DATA_PROVIDER_NAME)
    private Object[][] invalidBuildFromSetDataProvider() {
        return new Object[][] {
                { Sets.newHashSet("A", null) }
        };
    }

    static final String VALID_BUILD_FROM_SET_DATA_PROVIDER_NAME = "validBuildFromSetDataProvider";

    @DataProvider(name = VALID_BUILD_FROM_SET_DATA_PROVIDER_NAME)
    private Object[][] validBuildFromSetDataProvider() {
        return new Object[][] {
                { null, CountableOccurrenceMap.build() },
                { ImmutableSet.of(), CountableOccurrenceMap.build() },
                { ImmutableSet.of("A"), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) }
        };
    }

    static final String IS_EQUALS_DATA_PROVIDER_NAME = "isEqualsDataProvider";

    @DataProvider(name = IS_EQUALS_DATA_PROVIDER_NAME)
    private Object[][] isEqualsDataProvider() {
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

    static final String GET_ELEMENTS_SIZE_DATA_PROVIDER_NAME = "getElementsSizeDataProvider";

    @DataProvider(name = GET_ELEMENTS_SIZE_DATA_PROVIDER_NAME)
    private Object[][] getElementsSizeDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), 0 },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), 1 },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), 2 }
        };
    }

    static final String COPY_DATA_PROVIDER_NAME = "copyDataProvider";

    @DataProvider(name = COPY_DATA_PROVIDER_NAME)
    private Object[][] copyDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)) }
        };
    }

    static final String INVALID_GET_OCCURRENCE_DATA_PROVIDER = "invalidGetOccurrenceDataProvider";

    @DataProvider(name = INVALID_GET_OCCURRENCE_DATA_PROVIDER)
    private Object[][] invalidGetOccurrenceDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), null },
                { CountableOccurrenceMap.build(), "A" },
                { CountableOccurrenceMap.build(ImmutableSet.of("A")), "C" }
        };
    }

    static final String INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME = "invalidOperationCollectionDataProvider";

    @DataProvider(name = INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME)
    private Object[][] invalidOperationCollectionDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), null },
                { CountableOccurrenceMap.build(), new String[] {"A"} },
                { CountableOccurrenceMap.build(ImmutableSet.of("A")), new String[] { "A", null } },
                { CountableOccurrenceMap.build(ImmutableSet.of("A")), new String[] { "A", "C" } }
        };
    }

    static final String INVALID_OPERATION_SET_DATA_PROVIDER_NAME = "invalidOperationSetDataProvider";

    @DataProvider(name = INVALID_OPERATION_SET_DATA_PROVIDER_NAME)
    private Object[][] invalidOperationSetDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), null },
                { CountableOccurrenceMap.build(), ImmutableSet.of("A") },
                { CountableOccurrenceMap.build(), Sets.newHashSet("A", null) },
                { CountableOccurrenceMap.build(ImmutableSet.of("A")), ImmutableSet.of("A", "C") }
        };
    }

    static final String SUB_SET_DATA_PROVIDER_NAME = "subSetDataProvider";

    @DataProvider(name = SUB_SET_DATA_PROVIDER_NAME)
    private Object[][] subSetsDataProvider() {
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
    private Object[][] subSetAboutSetDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), ImmutableSet.of(), CountableOccurrenceMap.build() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), ImmutableSet.of("A"), CountableOccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), ImmutableSet.of("A"), CountableOccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), ImmutableSet.of("A", "C"), CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)) }
        };
    }

    static final String MINIMUM_OCCURRENCE_VALUE_DATA_PROVIDER_NAME = "minimumOccurrenceValueDataProvider";

    @DataProvider(name = MINIMUM_OCCURRENCE_VALUE_DATA_PROVIDER_NAME)
    private Object[][] minimumOccurrenceValueDataProvider() {
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
    private Object[][] invalidDecreaseDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "T", 0)), null },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "T", 0)), "G" },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "T", 0)), "T" }
        };
    }

    static final String VALID_DECREASE_DATA_PROVIDER_NAME = "validDecreaseDataProvider";

    @DataProvider(name = VALID_DECREASE_DATA_PROVIDER_NAME)
    private Object[][] validDecreaseDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 2, "G", 1)), "A", CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "G", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 2, "G", 1)), "G", CountableOccurrenceMap.build(ImmutableMap.of("A", 2, "G", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), "A", CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) }
        };
    }

    static final String INVALID_SUBTRACT_DATA_PROVIDER_NAME = "invalidSubtractDataProvider";

    @DataProvider(name = INVALID_SUBTRACT_DATA_PROVIDER_NAME)
    private Object[][] invalidSubtractDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "T", 0)), null, 2 },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "T", 0)), "G", 2 },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "T", 0)), "T", -1 },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "T", 0)), "T", 2 },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "T", 0)), "A", 2 }
        };
    }

    static final String VALID_SUBTRACT_DATA_PROVIDER_NAME = "validSubtractDataProvider";

    @DataProvider(name = VALID_SUBTRACT_DATA_PROVIDER_NAME)
    private Object[][] validSubtractDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 2, "G", 1)), "A", 0, CountableOccurrenceMap.build(ImmutableMap.of("A", 2, "G", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0, "G", 1)), "A", 0, CountableOccurrenceMap.build(ImmutableMap.of("A", 0, "G", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 2, "G", 1)), "A", 1, CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "G", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 2, "G", 1)), "A", 2, CountableOccurrenceMap.build(ImmutableMap.of("A", 0, "G", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 2)), "A", 2, CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) }
        };
    }

    static final String INVALID_INCREASE_DATA_PROVIDER_NAME = "invalidIncreaseDataProvider";

    @DataProvider(name = INVALID_INCREASE_DATA_PROVIDER_NAME)
    private Object[][] invalidIncreaseDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), "A" },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), null },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), "C" }
        };
    }

    static final String VALID_INCREASE_DATA_PROVIDER_NAME = "validIncreaseDataProvider";

    @DataProvider(name = VALID_INCREASE_DATA_PROVIDER_NAME)
    private Object[][] validIncreaseDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), "A", CountableOccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), "A", CountableOccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 0)), "C", CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 1)), "C", CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)) }
        };
    }

    static final String INVALID_ADD_DATA_PROVIDER_NAME = "invalidAddDataProvider";

    @DataProvider(name = INVALID_ADD_DATA_PROVIDER_NAME)
    private Object[][] invalidAddDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), "A", 2 },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), null, 2 },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), "A", -1 },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), "C", 2 }
        };
    }

    static final String VALID_ADD_DATA_PROVIDER_NAME = "validAddDataProvider";

    @DataProvider(name = VALID_ADD_DATA_PROVIDER_NAME)
    private Object[][] validAddDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), "A", 0, CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), "A", 2, CountableOccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1)), "A", 2, CountableOccurrenceMap.build(ImmutableMap.of("A", 3)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 1)), "C", 0, CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 0)), "C", 2, CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 1)), "C", 2, CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 3)) }

        };
    }

    static final String LESS_FREQUENT_OCCURRENCES_DATA_PROVIDER_NAME = "lessFrequentOccurrences";

    @DataProvider(name = LESS_FREQUENT_OCCURRENCES_DATA_PROVIDER_NAME)
    private Object[][] mostFrequentDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 3)), ImmutableSet.of("A") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0, "C", 2)), ImmutableSet.of("A") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 2, "C", 2)), ImmutableSet.of("A", "C") },
                { CountableOccurrenceMap.build(), ImmutableSet.of() }
        };
    }

    static final String FILTER_DATA_PROVIDER_NAME = "FilterDataProvider";

    @DataProvider(name = FILTER_DATA_PROVIDER_NAME)
    private Object[][] filterDataProvider() {
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
    private Object[][] invalidFilterGreaterOrEqualsDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), -1 }
        };
    }

    static final String VALID_FILTER_EQUALS_DATA_PROVIDER_NAME = "validFilterEqualsDataProvider";

    @DataProvider(name = VALID_FILTER_EQUALS_DATA_PROVIDER_NAME)
    private Object[][] validFilterEqualsDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), 0, ImmutableSet.of() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 0, "C", 4, "T", 5)), 5, ImmutableSet.of("T") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 4, ImmutableSet.of("A", "C") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 0, ImmutableSet.of() },
        };
    }

    static final String VALID_FILTER_SMALLER_DATA_PROVIDER_NAME = "validFilterSmallerDataProvider";

    @DataProvider(name = VALID_FILTER_SMALLER_DATA_PROVIDER_NAME)
    private Object[][] validFilterSmallerDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), 0, ImmutableSet.of() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 0, "C", 4, "T", 5)), 5, ImmutableSet.of("A", "G", "C") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 4, ImmutableSet.of("G") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 0, ImmutableSet.of() },
        };
    }

    static final String VALID_FILTER_SMALLER_OR_EQUALS_DATA_PROVIDER_NAME = "validFilterSmallerOrEqualsDataProvider";

    @DataProvider(name = VALID_FILTER_SMALLER_OR_EQUALS_DATA_PROVIDER_NAME)
    private Object[][] validFilterSmallerOrEqualsDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), 0, ImmutableSet.of() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 0, "C", 4, "T", 5)), 5, ImmutableSet.of("A", "G", "C", "T") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 4, ImmutableSet.of("A", "G", "C") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 0, ImmutableSet.of() },
        };
    }

    static final String VALID_FILTER_GREATER_DATA_PROVIDER_NAME = "validFilterGreaterDataProvider";

    @DataProvider(name = VALID_FILTER_GREATER_DATA_PROVIDER_NAME)
    private Object[][] validFilterGreaterDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), 0, ImmutableSet.of() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 0, "C", 4, "T", 5)), 5, ImmutableSet.of() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 4, ImmutableSet.of("T") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 0, ImmutableSet.of("A", "G", "C", "T") },
        };
    }

    static final String VALID_FILTER_GREATER_OR_EQUALS_DATA_PROVIDER_NAME = "validFilterGreaterOrEqualsDataProvider";

    @DataProvider(name = VALID_FILTER_GREATER_OR_EQUALS_DATA_PROVIDER_NAME)
    private Object[][] validFilterGreaterOrEqualsDataProvider() {
        return new Object[][] {
                { CountableOccurrenceMap.build(), 0, ImmutableSet.of() },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 0, "C", 4, "T", 5)), 5, ImmutableSet.of("T") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 4, ImmutableSet.of("A", "C", "T") },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 0, ImmutableSet.of("A", "G", "C", "T") },
        };
    }

    static final String FILTER_MERGE_COUNTABLE_DATA_PROVIDER_NAME = "filterMergeCountableDataProvider";

    @DataProvider(name = FILTER_MERGE_COUNTABLE_DATA_PROVIDER_NAME)
    private Object[][] filterMergeCountableDataProvider() {
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
    private Object[][] invalidFilterMergeDataProvider() {
        Predicate<Map.Entry<String, Integer>> truePredicate = entry -> true;

        return new Object[][] {
                { CountableOccurrenceMap.build(), null, truePredicate },
                { CountableOccurrenceMap.build(), CountableOccurrenceMap.build(), null }
        };
    }

    static final String VALID_FILTER_MERGE_DATA_PROVIDER_NAME = "validFilterMergeDataProvider";

    @DataProvider(name = VALID_FILTER_MERGE_DATA_PROVIDER_NAME)
    private Object[][] validFilterMergeDataProvider() {
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
    private Object[][] mergeCountableDataProvider() {
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
    private Object[][] mergeDataProvider() {
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

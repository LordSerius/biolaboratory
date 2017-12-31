package hu.bioinformatics.biolaboratory.utils.datastructures;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

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

        return new Object[][] {
                { ImmutableMap.of("A", -1) },
                { nullKeyMap }
        };
    }

    static final String VALID_BUILD_DATA_PROVIDER_NAME = "validBuildDataProvider";

    @DataProvider(name = VALID_BUILD_DATA_PROVIDER_NAME)
    Object[][] validBuildDataProvider() {
        Map<String, Integer> nullValueMap = new HashMap<>();
        nullValueMap.put("A", 1);
        nullValueMap.put("G", null);

        return new Object[][] {
                { ImmutableMap.of(), ImmutableMap.of() },
                { null, ImmutableMap.of() },
                { ImmutableMap.of("A", 0), ImmutableMap.of() },
                { ImmutableMap.of("A", 1), ImmutableMap.of("A", 1) },
                { ImmutableMap.of("A", 1, "G", 0), ImmutableMap.of("A", 1) },
                { nullValueMap, ImmutableMap.of("A", 1) },
                { ImmutableMap.of("A", 1, "G", 2), ImmutableMap.of("A", 1, "G", 2) }
        };
    }

    static final String EMPTY_OCCURRENCE_MAP_DATA_PROVIDER_NAME = "emptyOccurrenceMapDataProvider";

    @DataProvider(name = EMPTY_OCCURRENCE_MAP_DATA_PROVIDER_NAME)
    Object[][] emptyOccurrenceMapDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build() }
        };
    }

    static final String INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME = "invalidOperationCollectionDataProvider";

    @DataProvider(name = INVALID_OPERATION_COLLECTION_DATA_PROVIDER_NAME)
    Object[][] invalidOperationCollectionDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), null },
                { OccurrenceMap.build(), new String[] { "A", null } }
        };
    }

    static final String INVALID_OPERATION_SET_DATA_PROVIDER_NAME = "invalidOperationSetDataProvider";

    @DataProvider(name = INVALID_OPERATION_SET_DATA_PROVIDER_NAME)
    Object[][] invalidOperationSetDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), null },
                { OccurrenceMap.build(), Sets.newHashSet("A", null) }
        };
    }

    static final String COPY_DATA_PROVIDER_NAME = "copyDataProvider";

    @DataProvider(name = COPY_DATA_PROVIDER_NAME)
    Object[][] copyDataProvider() {
        return new Object[][] {
               { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)) }
        };
    }

    static final String GET_OCCURRENCE_DATA_PROVIDER_NAME = "getOccurrenceDataProvider";

    @DataProvider(name= GET_OCCURRENCE_DATA_PROVIDER_NAME)
    Object[][] getOccurrenceDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 2)), "A", 2 },
                { OccurrenceMap.build(ImmutableMap.of("A", 2)), "G", 0 },
                { OccurrenceMap.build(), "A", 0 }
        };
    }

    static final String SUM_OCCURRENCES_DATA_PROVIDER_NAME = "sumOccurrencesDataProvider";

    @DataProvider(name= SUM_OCCURRENCES_DATA_PROVIDER_NAME)
    Object[][] sumOccurrencesDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[0], 0 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] {"U"}, 0 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] {"A"}, 1 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] {"A", "A"}, 1 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] {"A", "C"}, 3 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] {"A", "C", "G", "T"}, 10 },
                { OccurrenceMap.build(), new String[] {"A"}, 0 }
        };
    }

    static final String SUM_OCCURRENCES_ABOUT_SET_DATA_PROVIDER_NAME = "sumOccurrencesAboutSetDataProvider";

    @DataProvider(name= SUM_OCCURRENCES_ABOUT_SET_DATA_PROVIDER_NAME)
    Object[][] sumOccurrencesAboutSetDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of(), 0 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of("U"), 0 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of("A"), 1 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of("A", "C"), 3 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of("A", "C", "G", "T"), 10 },
                { OccurrenceMap.build(), ImmutableSet.of("A"), 0 }
        };
    }

    static final String SUM_TOTAL_OCCURRENCES_DATA_PROVIDER_NAME = "sumTotalOccurrencesDataProvider";

    @DataProvider(name = SUM_TOTAL_OCCURRENCES_DATA_PROVIDER_NAME)
    Object[][] sumTotalOccurrencesDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), 0 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), 1 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), 3 }
        };
    }

    static final String OCCURRENCE_RATIO_DATA_PROVIDER = "occurrenceRatioDataProvider";

    @DataProvider(name = OCCURRENCE_RATIO_DATA_PROVIDER)
    Object[][] occurrenceRatioDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), "A", 0.1 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3)), "T", 0.0 }
        };
    }

    static final String ACCUMULATED_OCCURRENCE_RATIO_DATA_PROVIDER = "accumulatedOccurrenceRatioDataProvider";

    @DataProvider(name = ACCUMULATED_OCCURRENCE_RATIO_DATA_PROVIDER)
    Object[][] accumulatedOccurrenceRatioDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), new String[] {}, 1.0 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] {}, 0.0 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] { "A" }, 0.1 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] { "A", "C" }, 0.3 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] { "A", "C", "G" }, 0.6 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] { "A", "C", "G", "T" }, 1.0 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3)), new String[] { "T" }, 0.0 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3)), new String[] { "A", "T" }, 1.0/6 }
        };
    }

    static final String ACCUMULATED_OCCURRENCE_RATIO_SET_DATA_PROVIDER = "accumulatedOccurrenceRatioSetDataProvider";

    @DataProvider(name = ACCUMULATED_OCCURRENCE_RATIO_SET_DATA_PROVIDER)
    Object[][] accumulatedOccurrenceRatioSetDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), ImmutableSet.of(), 1.0 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of(), 0.0 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of("A"), 0.1 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of("A", "C"), 0.3 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of("A", "C", "G"), 0.6 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of("A", "C", "G", "T"), 1.0 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3)), ImmutableSet.of("T"), 0.0 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3)), ImmutableSet.of("A", "T"), 1.0/6 }
        };
    }

    static final String ALL_OCCURRENCE_RATIOS_DATA_PROVIDER = "allOccurrenceRatiosDataProvider";

    @DataProvider(name = ALL_OCCURRENCE_RATIOS_DATA_PROVIDER)
    Object[][] allOccurrenceRatiosDataProvider() {
        HashMap<String, Double> nullMap = new HashMap<>();
        nullMap.put(null, 1.0);
        return new Object[][] {
                { OccurrenceMap.build(), nullMap },
                { OccurrenceMap.build(ImmutableMap.of("A", 2, "C", 2, "G", 2, "T", 2)), ImmutableMap.of("A", 0.25, "C", 0.25, "G", 0.25, "T", 0.25) }
        };
    }

    static final String OCCURRENCE_RATIOS_DATA_PROVIDER = "occurrenceRatiosDataProvider";

    @DataProvider(name = OCCURRENCE_RATIOS_DATA_PROVIDER)
    Object[][] occurrenceRatiosDataProvider() {
        HashMap<String, Double> nullMap = new HashMap<>();
        nullMap.put(null, 1.0);
        return new Object[][] {
                { OccurrenceMap.build(), new String[] {}, nullMap },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] {}, ImmutableMap.of() },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] { "A" }, ImmutableMap.of("A", 0.1) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] { "A", "C" }, ImmutableMap.of("A", 0.1, "C", 0.2) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] { "A", "C", "G" }, ImmutableMap.of("A", 0.1, "C", 0.2, "G", 0.3) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), new String[] { "A", "C", "G", "T" }, ImmutableMap.of("A", 0.1, "C", 0.2, "G", 0.3, "T", 0.4) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3)), new String[] { "T" }, ImmutableMap.of("T", 0.0) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3)), new String[] { "A", "T" }, ImmutableMap.of("A", 1.0/6, "T", 0.0) }
        };
    }

    static final String OCCURRENCE_RATIOS_SET_DATA_PROVIDER = "occurrenceRatiosSetDataProvider";

    @DataProvider(name = OCCURRENCE_RATIOS_SET_DATA_PROVIDER)
    Object[][] occurrenceRatiosSetDataProvider() {
        HashMap<String, Double> nullMap = new HashMap<>();
        nullMap.put(null, 1.0);
        return new Object[][] {
                { OccurrenceMap.build(), ImmutableSet.of(), nullMap },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of(), ImmutableMap.of() },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of("A"), ImmutableMap.of("A", 0.1) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of("A", "C"), ImmutableMap.of("A", 0.1, "C", 0.2) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of("A", "C", "G"), ImmutableMap.of("A", 0.1, "C", 0.2, "G", 0.3) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3, "T", 4)), ImmutableSet.of("A", "C", "G", "T"), ImmutableMap.of("A", 0.1, "C", 0.2, "G", 0.3, "T", 0.4) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3)), ImmutableSet.of("T"), ImmutableMap.of("T", 0.0) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2, "G", 3)), ImmutableSet.of("A", "T"), ImmutableMap.of("A", 1.0/6, "T", 0.0) }
        };
    }

    static final String SUB_SET_DATA_PROVIDER_NAME = "subSetDataProvider";

    @DataProvider(name = SUB_SET_DATA_PROVIDER_NAME)
    Object[][] subSetsDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), new String[] {"A"}, OccurrenceMap.build() },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), new String[] {}, OccurrenceMap.build() },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), new String[] {"C"}, OccurrenceMap.build() },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), new String[] {"A"}, OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), new String[] {"A"}, OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), new String[] {"A", "A"}, OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), new String[] {"A", "C"}, OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)) }
        };
    }

    static final String SUB_SET_ABOUT_SET_DATA_PROVIDER_NAME = "subSetAboutSetDataProvider";

    @DataProvider(name = SUB_SET_ABOUT_SET_DATA_PROVIDER_NAME)
    Object[][] subSetAboutSetDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), ImmutableSet.of("A"), OccurrenceMap.build() },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), ImmutableSet.of(), OccurrenceMap.build() },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), ImmutableSet.of("C"), OccurrenceMap.build() },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), ImmutableSet.of("A"), OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), ImmutableSet.of("A"), OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), ImmutableSet.of("A", "C"), OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)) }
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

    static final String TO_COUNTABLE_DATA_PROVIDER_NAME = "toCountableDataProvider";

    @DataProvider(name = TO_COUNTABLE_DATA_PROVIDER_NAME)
    Object[][] toCountableDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), CountableOccurrenceMap.build() },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { CountableOccurrenceMap.build(ImmutableMap.of("A", 0)), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) }
        };
    }

    static final String TO_COUNTABLE_WITH_DATA_PROVIDER_NAME = "toCountableWithDataProvider";

    @DataProvider(name = TO_COUNTABLE_WITH_DATA_PROVIDER_NAME)
    Object[][] toCountableWithDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), new String[] {}, CountableOccurrenceMap.build() },
                { OccurrenceMap.build(), new String[] { "A" }, CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), new String[] {}, CountableOccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), new String[] { "C" }, CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 0)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), new String[] { "C", "C" }, CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 0)) }
        };
    }

    static final String TO_COUNTABLE_WITH_SET_DATA_PROVIDER_NAME = "toCountableWithSetDataProvider";

    @DataProvider(name = TO_COUNTABLE_WITH_SET_DATA_PROVIDER_NAME)
    Object[][] toCountableWithSetDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), ImmutableSet.of(), CountableOccurrenceMap.build() },
                { OccurrenceMap.build(), ImmutableSet.of("A"), CountableOccurrenceMap.build(ImmutableMap.of("A", 0)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), ImmutableSet.of(), CountableOccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), ImmutableSet.of("C"), CountableOccurrenceMap.build(ImmutableMap.of("A", 1, "C", 0)) },
        };
    }

    static final String MAXIMUM_OCCURRENCE_VALUE_DATA_PROVIDER_NAME = "maximumOccurrenceValueDataProvider";

    @DataProvider(name = MAXIMUM_OCCURRENCE_VALUE_DATA_PROVIDER_NAME)
    Object[][] maximumOccurrenceValueDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), 0 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), 1 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), 2 },
                { OccurrenceMap.build(ImmutableMap.of("A", 2, "C", 2)), 2 }
        };
    }

    static final String MINIMUM_OCCURRENCE_VALUE_DATA_PROVIDER_NAME = "minimumOccurrenceValueDataProvider";

    @DataProvider(name = MINIMUM_OCCURRENCE_VALUE_DATA_PROVIDER_NAME)
    Object[][] minimumOccurrenceValueDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build() },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 2, "C", 2)) }
        };
    }

    static final String INVALID_DECREASE_DATA_PROVIDER_NAME = "invalidDecreaseDataProvider";

    @DataProvider(name = INVALID_DECREASE_DATA_PROVIDER_NAME)
    Object[][] invalidDecreaseDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), null },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), "G" }
        };
    }

    static final String VALID_DECREASE_DATA_PROVIDER_NAME = "validDecreaseDataProvider";

    @DataProvider(name = VALID_DECREASE_DATA_PROVIDER_NAME)
    Object[][] validDecreaseDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(new HashMap<>(ImmutableMap.of("A", 2, "G", 1))), "A", OccurrenceMap.build(ImmutableMap.of("A", 1, "G", 1)) },
                { OccurrenceMap.build(new HashMap<>(ImmutableMap.of("A", 2, "G", 1))), "G", OccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { OccurrenceMap.build(new HashMap<>(ImmutableMap.of("A", 1))), "A", OccurrenceMap.build() }
        };
    }

    static final String INVALID_SUBTRACT_DATA_PROVIDER_NAME = "invalidSubtractDataProvider";

    @DataProvider(name = INVALID_SUBTRACT_DATA_PROVIDER_NAME)
    Object[][] invalidSubtractDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), null, 1 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), "G", -1 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), "A", 2 }
        };
    }

    static final String VALID_SUBTRACT_DATA_PROVIDER_NAME = "validSubtractDataProvider";

    @DataProvider(name = VALID_SUBTRACT_DATA_PROVIDER_NAME)
    Object[][] validSubtractDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), "A", 0, OccurrenceMap.build() },
                { OccurrenceMap.build(ImmutableMap.of("A", 2, "G", 1)), "A", 0, OccurrenceMap.build(ImmutableMap.of("A", 2, "G", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 2, "G", 1)), "C", 0, OccurrenceMap.build(ImmutableMap.of("A", 2, "G", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 2, "G", 1)), "A", 1, OccurrenceMap.build(ImmutableMap.of("A", 1, "G", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 2, "G", 1)), "A", 2, OccurrenceMap.build(ImmutableMap.of("G", 1)) }
        };
    }

    static final String INVALID_INCREASE_DATA_PROVIDER_NAME = "invalidIncreaseDataProvider";

    @DataProvider(name = INVALID_INCREASE_DATA_PROVIDER_NAME)
    Object[][] invalidIncreaseDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), null }
        };
    }

    static final String VALID_INCREASE_DATA_PROVIDER_NAME = "validIncreaseDataProvider";

    @DataProvider(name = VALID_INCREASE_DATA_PROVIDER_NAME)
    Object[][] validIncreaseDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), "A", OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(new HashMap<>(ImmutableMap.of("A", 1))), "A", OccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { OccurrenceMap.build(new HashMap<>(ImmutableMap.of("A", 1))), "G", OccurrenceMap.build(ImmutableMap.of("A", 1, "G", 1)) }

        };
    }

    static final String INVALID_ADD_DATA_PROVIDER_NAME = "invalidAddDataProvider";

    @DataProvider(name = INVALID_ADD_DATA_PROVIDER_NAME)
    Object[][] invalidAddDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), null, 1 },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), "C", -1 }
        };
    }

    static final String VALID_ADD_DATA_PROVIDER_NAME = "validAddDataProvider";

    @DataProvider(name = VALID_ADD_DATA_PROVIDER_NAME)
    Object[][] validAddDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), "A", 0, OccurrenceMap.build() },
                { OccurrenceMap.build(), "A", 2, OccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), "A", 1, OccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), "A", 2, OccurrenceMap.build(ImmutableMap.of("A", 3)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), "G", 0, OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), "G", 2, OccurrenceMap.build(ImmutableMap.of("A", 1, "G", 2)) }
        };
    }

    static final String FILTER_DATA_PROVIDER_NAME = "FilterDataProvider";

    @DataProvider(name = FILTER_DATA_PROVIDER_NAME)
    Object[][] filterDataProvider() {
        Predicate<Map.Entry<String, Integer>> truePredicate = entry -> true;
        Predicate<Map.Entry<String, Integer>> falsePredicate = entry -> false;
        Predicate<Map.Entry<String, Integer>> filterPredicate = entry -> entry.getKey().equals("A");

        return new Object[][] {
                { OccurrenceMap.build(), truePredicate, OccurrenceMap.build() },
                { OccurrenceMap.build(), falsePredicate, OccurrenceMap.build() },
                { OccurrenceMap.build(), filterPredicate, OccurrenceMap.build() },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), truePredicate, OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), falsePredicate, OccurrenceMap.build() },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), filterPredicate, OccurrenceMap.build(ImmutableMap.of("A", 1)) }
        };
    }
    
    static final String INVALID_FILTER_RELATIONAL_DATA_PROVIDER_NAME = "invalidFilterRelationalDataProvider";
    
    @DataProvider(name = INVALID_FILTER_RELATIONAL_DATA_PROVIDER_NAME)
    Object[][] invalidFilterRelationalDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(), 0 },
                { OccurrenceMap.build(), -1 }
        };
    }

    static final String VALID_FILTER_EQUALS_DATA_PROVIDER_NAME = "validEqualsDataProvider";

    @DataProvider(name = VALID_FILTER_EQUALS_DATA_PROVIDER_NAME)
    Object[][] validFilterEqualsDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 5, ImmutableSet.of("T") },
                { OccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 4, ImmutableSet.of("A", "C") },
                { OccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 1, ImmutableSet.of() },
                { OccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 6, ImmutableSet.of() },
                { OccurrenceMap.build(), 3, ImmutableSet.of() }
        };
    }

    static final String VALID_FILTER_GREATER_DATA_PROVIDER_NAME = "validFilterGreaterDataProvider";

    @DataProvider(name = VALID_FILTER_GREATER_DATA_PROVIDER_NAME)
    Object[][] validFilterGreaterDataProvider() {
        return new Object[][] {
                { OccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 5, ImmutableSet.of() },
                { OccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 4, ImmutableSet.of("T") },
                { OccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 1, ImmutableSet.of("A", "G", "C", "T") },
                { OccurrenceMap.build(ImmutableMap.of("A", 4, "G", 2, "C", 4, "T", 5)), 6, ImmutableSet.of() },
                { OccurrenceMap.build(), 3, ImmutableSet.of() }
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

    static final String INVALID_FILTER_MERGE_DATA_PROVIDER_NAME = "invalidFilterMergeDataProvider";

    @DataProvider(name = INVALID_FILTER_MERGE_DATA_PROVIDER_NAME)
    Object[][] invalidFilterMergeDataProvider() {
        Predicate<Map.Entry<String, Integer>> truePredicate = entry -> true;

        return new Object[][] {
                { OccurrenceMap.build(), null, truePredicate },
                { OccurrenceMap.build(), OccurrenceMap.build(), null }
        };
    }

    static final String VALID_FILTER_MERGE_DATA_PROVIDER_NAME = "validFilterMergeDataProvider";

    @DataProvider(name = VALID_FILTER_MERGE_DATA_PROVIDER_NAME)
    Object[][] validFilterMergeDataProvider() {
        Predicate<Map.Entry<String, Integer>> filterPredicate = entry -> entry.getKey().equals("A");

        return new Object[][] {
                { OccurrenceMap.build(), OccurrenceMap.build(), filterPredicate, OccurrenceMap.build() },
                { OccurrenceMap.build(), OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), filterPredicate, OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), OccurrenceMap.build(), filterPredicate, OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 2)), OccurrenceMap.build(ImmutableMap.of("A", 1)), filterPredicate, OccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 1)), filterPredicate, OccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("C", 1)), filterPredicate, OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("C", 0)), filterPredicate, OccurrenceMap.build(ImmutableMap.of("A", 1)) }
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
                { OccurrenceMap.build(), OccurrenceMap.build(), OccurrenceMap.build() },
                { OccurrenceMap.build(), OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(), OccurrenceMap.build(ImmutableMap.of("A", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("A", 2)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), OccurrenceMap.build(ImmutableMap.of("C", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("C", 1)), OccurrenceMap.build(ImmutableMap.of("A", 1, "C", 1)) },
                { OccurrenceMap.build(ImmutableMap.of("A", 1)), CountableOccurrenceMap.build(ImmutableMap.of("C", 0)), OccurrenceMap.build(ImmutableMap.of("A", 1)) }
        };
    }
}

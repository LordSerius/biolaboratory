package hu.bioinformatics.biolaboratory.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.testng.annotations.DataProvider;

/**
 * Data provider for {@link ArgumentValidatorTest}.
 *
 * @author Attila Radi
 */
public class ArgumentValidatorTestDataProvider {

    static final String INVALID_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME = "invalidNotEmptyVarargsDataProvider";

    @DataProvider(name = INVALID_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME)
    private Object[][] invalidNotEmptyVarargsDataProvider() {
        return new Object[][] {
                { null },
                { new String[] {} },
                { new String[] { "A", null } }
        };
    }

    static final String VALID_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME = "validNotEmptyVarargsDataProvider";

    @DataProvider(name = VALID_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME)
    private Object[][] validNotEmptyVarargsDataProvider() {
        return new Object[][] {
                { new String[] { "" } },
                { new String[] { "A" } },
                { new String[] { "A", "" } },
                { new String[] { "A", "C" } }
        };
    }

    static final String INVALID_NOT_EMPTY_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidNotEmptyVarargsWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_NOT_EMPTY_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidNotEmptyVarargsWithArgumentsDataProvider() {
        return new Object[][] {
                { "varargs", null },
                { "varargs", new String[] {} },
                { "varargs", new String[] { "A", null } }
        };
    }

    static final String VALID_NOT_EMPTY_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validNotEmptyVarargsWithArgumentNameDataProvider";

    @DataProvider(name = VALID_NOT_EMPTY_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validNotEmptyVarargsDataWithArgumentNameProvider() {
        return new Object[][] {
                { "varargs", new String[] { "" } },
                { "varargs", new String[] { "A" } },
                { "varargs", new String[] { "A", "" } },
                { "varargs", new String[] { "A", "C" } },
                { "", new String[] { "A", "C" } },
                { null, new String[] { "A", "C" } }
        };
    }

    static final String INVALID_NOT_NULL_VARARGS_DATA_PROVIDER_NAME = "invalidNotNullVarargsDataProvider";

    @DataProvider(name = INVALID_NOT_NULL_VARARGS_DATA_PROVIDER_NAME)
    private Object[][] invalidNotNullVarargsDataProvider() {
        return new Object[][] {
                { null },
                { new String[] { "A", null } }
        };
    }

    static final String VALID_NOT_NULL_VARARGS_DATA_PROVIDER_NAME = "validNotNullVarargsDataProvider";

    @DataProvider(name = VALID_NOT_NULL_VARARGS_DATA_PROVIDER_NAME)
    private Object[][] validNotNullVarargsDataProvider() {
        return new Object[][] {
                { new String[] {} },
                { new String[] { "" } },
                { new String[] { "A" } },
                { new String[] { "A", "" } },
                { new String[] { "A", "C" } }
        };
    }

    static final String INVALID_SAME_TYPE_WITH_NAME_DATA_PROVIDER_NAME = "invalidSameTypeWithDataProvider";

    @DataProvider(name = INVALID_SAME_TYPE_WITH_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidSameTypeWithDataProvider() {
        return new Object[][] {
                { null, "text" },
                { "text", null },
                { new Object(), "text" },
                { "text", new Object() }
        };
    }

    static final String VALID_SAME_TYPE_WITH_NAME_DATA_PROVIDER_NAME = "validSameTypeWithDataProvider";

    @DataProvider(name = VALID_SAME_TYPE_WITH_NAME_DATA_PROVIDER_NAME)
    private Object[][] validSameTypeWithDataProvider() {
        return new Object[][] {
                { new Object(), new Object() },
                { "text", "text" }
        };
    }

    static final String INVALID_SAME_TYPE_WITH_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidSameTypeWithWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_SAME_TYPE_WITH_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidSameTypeWithWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", null, "text" },
                { "argument", "text", null },
                { "argument", new Object(), "text" },
                { "argument", "text", new Object() }
        };
    }

    static final String VALID_SAME_TYPE_WITH_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validSameTypeWithWithArgumentNameDataProvider";

    @DataProvider(name = VALID_SAME_TYPE_WITH_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validSameTypeWithWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", new Object(), new Object() },
                { "argument", "text", "text" },
                { "", "text", "text" },
                { null, "text", "text" }
        };
    }

    static final String INVALID_SAME_TYPE_WITH_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "invalidSameTypeWithWithArgumentAndTargetNameDataProvider";

    @DataProvider(name = INVALID_SAME_TYPE_WITH_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidSameTypeWithWithArgumentAndTargetNameDataProvider() {
        return new Object[][] {
                { "argument", null, "target", "text" },
                { "argument", "text", "target", null },
                { "argument", new Object(), "target", "text" },
                { "argument", "text", "target", new Object() }
        };
    }

    static final String VALID_SAME_TYPE_WITH_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "validSameTypeWithWithArgumentAndTargetNameDataProvider";

    @DataProvider(name = VALID_SAME_TYPE_WITH_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] validSameTypeWithWithArgumentAndTargetNameDataProvider() {
        return new Object[][] {
                { "argument", new Object(), "target", new Object() },
                { "argument", "text", "target", "text" },
                { "", "text", "target", "text" },
                { null, "text", "target", "text" },
                { "argument", "text", "", "text" },
                { "argument", "text", null, "text" },
        };
    }

    static final String INVALID_NOT_NULL_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidNotNullVarargsWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_NOT_NULL_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidNotNullVarargsWithArgumentNameDataProvider() {
        return new Object[][] {
                { "varargs", null },
                { "varargs", new String[] { "A", null } }
        };
    }

    static final String VALID_NOT_NULL_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validNotNullVarargsWithArgumentNameDataProvider";

    @DataProvider(name = VALID_NOT_NULL_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validNotNullVarargsWithArgumentNameDataProvider() {
        return new Object[][] {
                { "varargs", new String[] {} },
                { "varargs", new String[] { "" } },
                { "varargs", new String[] { "A" } },
                { "varargs", new String[] { "A", "" } },
                { "varargs", new String[] { "A", "C" } },
                { "", new String[] { "A", "C" } },
                { null, new String[] { "A", "C" } }
        };
    }

    static final String INVALID_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME = "invalidNotEmptyCollectionDataProvider";

    @DataProvider(name = INVALID_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME)
    private Object[][] invalidNotEmptyCollectionDataProvider() {
        return new Object[][] {
                { null },
                { ImmutableList.of() },
                { Lists.newArrayList("A", null) }
        };
    }

    static final String VALID_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME = "validNotEmptyCollectionDataProvider";

    @DataProvider(name = VALID_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME)
    private Object[][] validNotEmptyCollectionDataProvider() {
        return new Object[][] {
                { ImmutableList.of("") },
                { ImmutableList.of("A") },
                { ImmutableList.of("A", "") },
                { ImmutableList.of("A", "C") }
        };
    }

    static final String INVALID_NOT_EMPTY_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidNotEmptyCollectionWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_NOT_EMPTY_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidNotEmptyCollectionWithArgumentNameDataProvider() {
        return new Object[][] {
                { "collection", null },
                { "collection", ImmutableList.of() },
                { "collection", Lists.newArrayList("A", null) }
        };
    }

    static final String VALID_NOT_EMPTY_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validNotEmptyCollectionWithArgumentNameDataProvider";

    @DataProvider(name = VALID_NOT_EMPTY_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validNotEmptyCollectionWithArgumentNameDataProvider() {
        return new Object[][] {
                { "collection", ImmutableList.of("") },
                { "collection", ImmutableList.of("A") },
                { "collection", ImmutableList.of("A", "") },
                { "collection", ImmutableList.of("A", "C") },
                { "", ImmutableList.of("A", "C") },
                { null, ImmutableList.of("A", "C") }
        };
    }

    static final String INVALID_NOT_NULL_COLLECTION_DATA_PROVIDER_NAME = "invalidNotNullCollectionDataProvider";

    @DataProvider(name = INVALID_NOT_NULL_COLLECTION_DATA_PROVIDER_NAME)
    private Object[][] invalidNotNullCollectionDataProvider() {
        return new Object[][] {
                { null },
                { Lists.newArrayList("A", null) }
        };
    }

    static final String VALID_NOT_NULL_COLLECTION_DATA_PROVIDER_NAME = "validNotNullCollectionDataProvider";

    @DataProvider(name = VALID_NOT_NULL_COLLECTION_DATA_PROVIDER_NAME)
    private Object[][] validNotNullCollectionDataProvider() {
        return new Object[][] {
                { ImmutableList.of() },
                { ImmutableList.of("") },
                { ImmutableList.of("A") },
                { ImmutableList.of("A", "") },
                { ImmutableList.of("A", "C") }
        };
    }

    static final String INVALID_NOT_NULL_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidNotNullCollectionWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_NOT_NULL_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidNotNullCollectionWithArgumentNameDataProvider() {
        return new Object[][] {
                { "collection", null },
                { "collection", Lists.newArrayList("A", null) }
        };
    }

    static final String VALID_NOT_NULL_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validNotNullCollectionWithArgumentNameDataProvider";

    @DataProvider(name = VALID_NOT_NULL_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validNotNullCollectionWithArgumentNameDataProvider() {
        return new Object[][] {
                { "collection", ImmutableList.of() },
                { "collection", ImmutableList.of("") },
                { "collection", ImmutableList.of("A") },
                { "collection", ImmutableList.of("A", "") },
                { "collection", ImmutableList.of("A", "C") },
                { "", ImmutableList.of("A", "C") },
                { null, ImmutableList.of("A", "C") }
        };
    }

    static final String INVALID_NOT_NULL_ARGUMENT_DATA_PROVIDER_NAME = "invalidNotNullArgumentDataProvider";

    @DataProvider(name = INVALID_NOT_NULL_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] invalidNotNullArgumentDataProvider() {
        return new Object[][] {
                { null }
        };
    }

    static final String VALID_NOT_NULL_ARGUMENT_DATA_PROVIDER_NAME = "validNotNullArgumentDataProvider";

    @DataProvider(name = VALID_NOT_NULL_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] validNotNullArgumentDataProvider() {
        return new Object[][] {
                { "" },
                { new Object() },
                { "object" }
        };
    }

    static final String INVALID_NOT_NULL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidNotNullArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_NOT_NULL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidNotNullArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", null }
        };
    }

    static final String VALID_NOT_NULL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validNotNullArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_NOT_NULL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validNotNullArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", "" },
                { "argument", new Object() },
                { "argument", "object" },
                { "", "object" },
                { null, "object" }
        };
    }

    static final String INVALID_NOT_BLANK_STRING_DATA_PROVIDER_NAME = "invalidNotBlankStringDataProvider";

    @DataProvider(name = INVALID_NOT_BLANK_STRING_DATA_PROVIDER_NAME)
    private Object[][] invalidNotBlankStringDataProvider() {
        return new Object[][] {
                { null },
                { "" },
                { " " }
        };
    }

    static final String VALID_NOT_BLANK_STRING_DATA_PROVIDER_NAME = "validNotBlankStringDataProvider";

    @DataProvider(name = VALID_NOT_BLANK_STRING_DATA_PROVIDER_NAME)
    private Object[][] validNotBlankStringDataProvider() {
        return new Object[][] {
                { "text" },
                { "  text  " }
        };
    }

    static final String INVALID_NOT_BLANK_STRING_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidNotBlankStringWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_NOT_BLANK_STRING_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidNotBlankStringWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", null },
                { "argument", "" },
                { "argument", " " }
        };
    }

    static final String VALID_NOT_BLANK_STRING_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validNotBlankStringWithArgumentNameDataProvider";

    @DataProvider(name = VALID_NOT_BLANK_STRING_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validNotBlankStringWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", "text" },
                { "argument", "  text  " },
                { "", "  text  " },
                { null, "  text  " }
        };
    }

    static final String INVALID_SMALLER_ARGUMENT_DATA_PROVIDER_NAME = "invalidSmallerArgumentDataProvider";

    @DataProvider(name = INVALID_SMALLER_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] invalidSmallerArgumentDataProvider() {
        return new Object[][] {
                { 1, 0 },
                { 1, 1}
        };
    }

    static final String VALID_SMALLER_ARGUMENT_DATA_PROVIDER_NAME = "validSmallerArgumentDataProvider";

    @DataProvider(name = VALID_SMALLER_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] validSmallerArgumentDataProvider() {
        return new Object[][] {
                { 0, 1 }
        };
    }

    static final String INVALID_SMALLER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidSmallerArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_SMALLER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidSmallerArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1, 0 },
                { "argument", 1, 1 }
        };
    }

    static final String VALID_SMALLER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validSmallerArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_SMALLER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validSmallerArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 0, 1 },
                { "", 0, 1 },
                { null, 0, 1 }
        };
    }

    static final String INVALID_SMALLER_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "invalidSmallerArgumentWithArgumentAndTargetNameDAtaProvider";

    @DataProvider(name = INVALID_SMALLER_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidSmallerArgumentWithArgumentAndTargetNameDAtaProvider() {
        return new Object[][] {
                { "argument", 1, "target", 0 },
                { "argument", 1, "target", 1 }
        };
    }

    static final String VALID_SMALLER_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "validSmallerArgumentWithArgumentAndTargetNameDAtaProvider";

    @DataProvider(name = VALID_SMALLER_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] validSmallerArgumentWithArgumentAndTargetNameDAtaProvider() {
        return new Object[][] {
                { "argument", 0, "target", 1 },
                { "", 0, "target", 1 },
                { null, 0, "target", 1 },
                { "argument", 0, "", 1 },
                { "argument", 0, null, 1 }
        };
    }

    static final String INVALID_SMALLER_OR_EQUAL_ARGUMENT_DATA_PROVIDER_NAME = "invalidSmallerOrEqualArgumentDataProvider";

    @DataProvider(name = INVALID_SMALLER_OR_EQUAL_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] invalidSmallerOrEqualArgumentDataProvider() {
        return new Object[][] {
                { 1, 0 }
        };
    }

    static final String VALID_SMALLER_OR_EQUAL_ARGUMENT_DATA_PROVIDER_NAME = "validSmallerOrEqualArgumentDataProvider";

    @DataProvider(name = VALID_SMALLER_OR_EQUAL_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] validSmallerOrEqualArgumentDataProvider() {
        return new Object[][] {
                { 0, 1 },
                { 1, 1 }
        };
    }

    static final String INVALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidSmallerOrEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidSmallerOrEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1, 0 }
        };
    }

    static final String VALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validSmallerOrEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validSmallerOrEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 0, 1 },
                { "argument", 1, 1 },
                { "", 1, 1 },
                { null, 1, 1 }
        };
    }

    static final String INVALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "invalidSmallerOrEqualArgumentWithArgumentAndTargetNameDAtaProvider";

    @DataProvider(name = INVALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidSmallerOrEqualArgumentWithArgumentAndTargetNameDAtaProvider() {
        return new Object[][] {
                { "argument", 1, "target", 0 }
        };
    }

    static final String VALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "validSmallerOrEqualArgumentWithArgumentAndTargetNameDAtaProvider";

    @DataProvider(name = VALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] validSmallerOrEqualArgumentWithArgumentAndTargetNameDAtaProvider() {
        return new Object[][] {
                { "argument", 0, "target", 1 },
                { "argument", 1, "target", 1 },
                { "", 1, "target", 1 },
                { null, 1, "target", 1 },
                { "argument", 1, "", 1 },
                { "argument", 1, null, 1 }
        };
    }

    static final String INVALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_DATA_PROVIDER_NAME = "invalidSmallerOrEqualDoubleArgumentDataProvider";

    @DataProvider(name = INVALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] invalidSmallerOrEqualDoubleArgumentDataProvider() {
        return new Object[][] {
                { 0.00001d, 0.000005d }
        };
    }

    static final String VALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_DATA_PROVIDER_NAME = "validSmallerOrEqualDoubleArgumentDataProvider";

    @DataProvider(name = VALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] validSmallerOrEqualDoubleArgumentDataProvider() {
        return new Object[][] {
                { 0.000005d, 0.00001d },
                { 0.00001d, 0.00001d }
        };
    }

    static final String INVALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidSmallerOrEqualDoubleArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidSmallerOrEqualDoubleArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 0.00001d, 0.000005d }
        };
    }

    static final String VALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validSmallerOrEqualDoubleArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validSmallerOrEqualDoubleArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 0.000005d, 0.00001d },
                { "argument", 0.00001d, 0.00001d },
                { "", 0.00001d, 0.00001d },
                { null, 0.00001d, 0.00001d }
        };
    }

    static final String INVALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "invalidSmallerOrEqualDoubleArgumentWithArgumentAndTargetNameDAtaProvider";

    @DataProvider(name = INVALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidSmallerOrEqualDoubleArgumentWithArgumentAndTargetNameDAtaProvider() {
        return new Object[][] {
                { "argument", 0.00001d, "target", 0.000005d }
        };
    }

    static final String VALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "validSmallerOrEqualDoubleArgumentWithArgumentAndTargetNameDAtaProvider";

    @DataProvider(name = VALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] validSmallerOrEqualDoubleArgumentWithArgumentAndTargetNameDAtaProvider() {
        return new Object[][] {
                { "argument", 0.000005d, "target", 0.00001d },
                { "argument", 0.00001d, "target", 0.00001d },
                { "", 0.00001d, "target", 0.00001d },
                { null, 0.00001d, "target", 0.00001d },
                { "argument", 0.00001d, "", 0.00001d },
                { "argument", 0.00001d, null, 0.00001d }
        };
    }

    static final String INVALID_EQUAL_ARGUMENT_DATA_PROVIDER_NAME = "invalidEqualArgumentDataProvider";

    @DataProvider(name = INVALID_EQUAL_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] invalidEqualArgumentDataProvider() {
        return new Object[][] {
                { 1, 0 },
                { 1, 2 }
        };
    }

    static final String VALID_EQUAL_ARGUMENT_DATA_PROVIDER_NAME = "validEqualArgumentDataProvider";

    @DataProvider(name = VALID_EQUAL_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] validEqualArgumentDataProvider() {
        return new Object[][] {
                { 1, 1 }
        };
    }

    static final String INVALID_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1, 0 },
                { "argument", 1, 2 }
        };
    }

    static final String VALID_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1, 1 },
                { "", 1, 1 },
                { null, 1, 1 }
        };
    }

    static final String INVALID_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "invalidEqualArgumentWithArgumentAndTargetNameDAtaProvider";

    @DataProvider(name = INVALID_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidEqualArgumentWithArgumentAndTargetNameDAtaProvider() {
        return new Object[][] {
                { "argument", 1, "target", 0 },
                { "argument", 1, "target", 2 }
        };
    }

    static final String VALID_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "validEqualArgumentWithArgumentAndTargetNameDAtaProvider";

    @DataProvider(name = VALID_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] validEqualArgumentWithArgumentAndTargetNameDAtaProvider() {
        return new Object[][] {
                { "argument", 1, "target", 1 },
                { "", 1, "target", 1 },
                { null, 1, "target", 1 },
                { "argument", 1, "", 1 },
                { "argument", 1, null, 1 }
        };
    }

    static final String INVALID_NOT_EQUAL_ARGUMENT_DATA_PROVIDER_NAME = "invalidNotEqualArgumentDataProvider";

    @DataProvider(name = INVALID_NOT_EQUAL_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] invalidNotEqualArgumentDataProvider() {
        return new Object[][] {
                { 1, 1 }
        };
    }

    static final String VALID_NOT_EQUAL_ARGUMENT_DATA_PROVIDER_NAME = "validNotEqualArgumentDataProvider";

    @DataProvider(name = VALID_NOT_EQUAL_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] validNotEqualArgumentDataProvider() {
        return new Object[][] {
                { 1, 0 },
                { 1, 2 }
        };
    }

    static final String INVALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidNotEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidNotEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1, 1 }
        };
    }

    static final String VALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validNotEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validNotEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1, 0 },
                { "argument", 1, 2 },
                { "", 1, 2 },
                { null, 1, 2 }
        };
    }

    static final String INVALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "invalidNotEqualArgumentWithArgumentAndTargetNameDAtaProvider";

    @DataProvider(name = INVALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidNotEqualArgumentWithArgumentAndTargetNameDAtaProvider() {
        return new Object[][] {
                { "argument", 1, "target", 1 }
        };
    }

    static final String VALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "validNotEqualArgumentWithArgumentAndTargetNameDAtaProvider";

    @DataProvider(name = VALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] validNotEqualArgumentWithArgumentAndTargetNameDAtaProvider() {
        return new Object[][] {
                { "argument", 1, "target", 0 },
                { "argument", 1, "target", 2 },
                { "", 1, "target", 2 },
                { null, 1, "target", 2 },
                { "argument", 1, "", 2 },
                { "argument", 1, null, 2 }
        };
    }

    static final String INVALID_GREATER_OR_EQUAL_ARGUMENT_DATA_PROVIDER_NAME = "invalidGreaterOrEqualArgumentDataProvider";

    @DataProvider(name = INVALID_GREATER_OR_EQUAL_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] invalidGreaterOrEqualArgumentDataProvider() {
        return new Object[][] {
                { 0, 1 }
        };
    }

    static final String VALID_GREATER_OR_EQUAL_ARGUMENT_DATA_PROVIDER_NAME = "validGreaterOrEqualArgumentDataProvider";

    @DataProvider(name = VALID_GREATER_OR_EQUAL_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] validGreaterOrEqualArgumentDataProvider() {
        return new Object[][] {
                { 1, 1 },
                { 2, 1 }
        };
    }

    static final String INVALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidGreaterOrEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidGreaterOrEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 0, 1 }
        };
    }

    static final String VALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validGreaterOrEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validGreaterOrEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1, 1 },
                { "argument", 2, 1 },
                { "", 2, 1 },
                { null, 2, 1 }
        };
    }

    static final String INVALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "invalidGreaterOrEqualArgumentWithArgumentAndTargetNameDAtaProvider";

    @DataProvider(name = INVALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidGreaterOrEqualArgumentWithArgumentAndTargetNameDAtaProvider() {
        return new Object[][] {
                { "argument", 0, "target", 1 }
        };
    }

    static final String VALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "validGreaterOrEqualArgumentWithArgumentAndTargetNameDAtaProvider";

    @DataProvider(name = VALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] validGreaterOrEqualArgumentWithArgumentAndTargetNameDAtaProvider() {
        return new Object[][] {
                { "argument", 1, "target", 1 },
                { "argument", 2, "target", 1 },
                { "", 2, "target", 1 },
                { null, 2, "target", 1 },
                { "argument", 2, "", 1 },
                { "argument", 2, null, 1 }
        };
    }

    static final String INVALID_GREATER_ARGUMENT_DATA_PROVIDER_NAME = "invalidGreaterArgumentDataProvider";

    @DataProvider(name = INVALID_GREATER_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] invalidGreaterArgumentDataProvider() {
        return new Object[][] {
                { 0, 1 },
                { 1, 1 }
        };
    }

    static final String VALID_GREATER_ARGUMENT_DATA_PROVIDER_NAME = "validGreaterArgumentDataProvider";

    @DataProvider(name = VALID_GREATER_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] validGreaterArgumentDataProvider() {
        return new Object[][] {
                { 2, 1 }
        };
    }

    static final String INVALID_GREATER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidGreateArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_GREATER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidGreaterArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 0, 1 },
                { "argument", 1, 1 }
        };
    }

    static final String VALID_GREATER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validGreaterArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_GREATER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validGreaterArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 2, 1 },
                { "", 2, 1 },
                { null, 2, 1 }
        };
    }

    static final String INVALID_GREATER_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "invalidGreaterArgumentWithArgumentAndTargetNameDAtaProvider";

    @DataProvider(name = INVALID_GREATER_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidGreaterArgumentWithArgumentAndTargetNameDAtaProvider() {
        return new Object[][] {
                { "argument", 0, "target", 1 },
                { "argument", 1, "target", 1 }
        };
    }

    static final String VALID_GREATER_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME = "validGreaterArgumentWithArgumentAndTargetNameDAtaProvider";

    @DataProvider(name = VALID_GREATER_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    private Object[][] validGreaterArgumentWithArgumentAndTargetNameDAtaProvider() {
        return new Object[][] {
                { "argument", 2, "target", 1 },
                { "", 2, "target", 1 },
                { null, 2, "target", 1 },
                { "argument", 2, "", 1 },
                { "argument", 2, null, 1 }
        };
    }

    static final String INVALID_NOT_NEGATIVE_ARGUMENT_DATA_PROVIDER_NAME = "invalidNotNegativeArgumentDataProvider";

    @DataProvider(name = INVALID_NOT_NEGATIVE_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] invalidNotNegativeArgumentDataProvider() {
        return new Object[][] {
                { -1 }
        };
    }

    static final String VALID_NOT_NEGATIVE_ARGUMENT_DATA_PROVIDER_NAME = "validNotNegativeArgumentDataProvider";

    @DataProvider(name = VALID_NOT_NEGATIVE_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] validNotNegativeArgumentDataProvider() {
        return new Object[][] {
                { 0 },
                { 1 }
        };
    }

    static final String INVALID_NOT_NEGATIVE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidNotNegativeArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_NOT_NEGATIVE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidNotNegativeArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", -1 }
        };
    }

    static final String VALID_NOT_NEGATIVE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validNotNegativeArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_NOT_NEGATIVE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validNotNegativeArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 0 },
                { "argument", 1 },
                { "", 1 },
                { null, 1 },
        };
    }

    static final String INVALID_POSITIVE_ARGUMENT_DATA_PROVIDER_NAME = "invalidPositiveArgumentDataProvider";

    @DataProvider(name = INVALID_POSITIVE_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] invalidPositiveArgumentDataProvider() {
        return new Object[][] {
                { -1 },
                { 0 }
        };
    }

    static final String VALID_POSITIVE_ARGUMENT_DATA_PROVIDER_NAME = "validPositiveArgumentDataProvider";

    @DataProvider(name = VALID_POSITIVE_ARGUMENT_DATA_PROVIDER_NAME)
    private Object[][] validPositiveArgumentDataProvider() {
        return new Object[][] {
                { 1 }
        };
    }

    static final String INVALID_POSITIVE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "invalidPositiveArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_POSITIVE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] invalidPositiveArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", -1 },
                { "argument", 0 }
        };
    }

    static final String VALID_POSITIVE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME = "validPositiveArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_POSITIVE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    private Object[][] validPositiveArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1 },
                { "", 1 },
                { null, 1 }
        };
    }
}

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

    static final String INVALID_SMALLER_ARGUMENT_DATA_PROVIDER = "invalidSmallerArgumentDataProvider";

    @DataProvider(name = INVALID_SMALLER_ARGUMENT_DATA_PROVIDER)
    private Object[][] invalidSmallerArgumentDataProvider() {
        return new Object[][] {
                { 1, 0 },
                { 1, 1}
        };
    }

    static final String VALID_SMALLER_ARGUMENT_DATA_PROVIDER = "validSmallerArgumentDataProvider";

    @DataProvider(name = VALID_SMALLER_ARGUMENT_DATA_PROVIDER)
    private Object[][] validSmallerArgumentDataProvider() {
        return new Object[][] {
                { 0, 1 }
        };
    }

    static final String INVALID_SMALLER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER = "invalidSmallerArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_SMALLER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    private Object[][] invalidSmallerArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1, 0 },
                { "argument", 1, 1 }
        };
    }

    static final String VALID_SMALLER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER = "validSmallerArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_SMALLER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    private Object[][] validSmallerArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 0, 1 },
                { "", 0, 1 },
                { null, 0, 1 }
        };
    }

    static final String INVALID_SMALLER_OR_EQUAL_ARGUMENT_DATA_PROVIDER = "invalidSmallerOrEqualArgumentDataProvider";

    @DataProvider(name = INVALID_SMALLER_OR_EQUAL_ARGUMENT_DATA_PROVIDER)
    private Object[][] invalidSmallerOrEqualArgumentDataProvider() {
        return new Object[][] {
                { 1, 0 }
        };
    }

    static final String VALID_SMALLER_OR_EQUAL_ARGUMENT_DATA_PROVIDER = "validSmallerOrEqualArgumentDataProvider";

    @DataProvider(name = VALID_SMALLER_OR_EQUAL_ARGUMENT_DATA_PROVIDER)
    private Object[][] validSmallerOrEqualArgumentDataProvider() {
        return new Object[][] {
                { 0, 1 },
                { 1, 1 }
        };
    }

    static final String INVALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER = "invalidSmallerOrEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    private Object[][] invalidSmallerOrEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1, 0 }
        };
    }

    static final String VALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER = "validSmallerOrEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    private Object[][] validSmallerOrEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 0, 1 },
                { "argument", 1, 1 },
                { "", 1, 1 },
                { null, 1, 1 }
        };
    }

    static final String INVALID_EQUAL_ARGUMENT_DATA_PROVIDER = "invalidEqualArgumentDataProvider";

    @DataProvider(name = INVALID_EQUAL_ARGUMENT_DATA_PROVIDER)
    private Object[][] invalidEqualArgumentDataProvider() {
        return new Object[][] {
                { 1, 0 },
                { 1, 2 }
        };
    }

    static final String VALID_EQUAL_ARGUMENT_DATA_PROVIDER = "validEqualArgumentDataProvider";

    @DataProvider(name = VALID_EQUAL_ARGUMENT_DATA_PROVIDER)
    private Object[][] validEqualArgumentDataProvider() {
        return new Object[][] {
                { 1, 1 }
        };
    }

    static final String INVALID_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER = "invalidEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    private Object[][] invalidEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1, 0 },
                { "argument", 1, 2 }
        };
    }

    static final String VALID_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER = "validEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    private Object[][] validEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1, 1 },
                { "", 1, 1 },
                { null, 1, 1 }
        };
    }

    static final String INVALID_NOT_EQUAL_ARGUMENT_DATA_PROVIDER = "invalidNotEqualArgumentDataProvider";

    @DataProvider(name = INVALID_NOT_EQUAL_ARGUMENT_DATA_PROVIDER)
    private Object[][] invalidNotEqualArgumentDataProvider() {
        return new Object[][] {
                { 1, 1 }
        };
    }

    static final String VALID_NOT_EQUAL_ARGUMENT_DATA_PROVIDER = "validNotEqualArgumentDataProvider";

    @DataProvider(name = VALID_NOT_EQUAL_ARGUMENT_DATA_PROVIDER)
    private Object[][] validNotEqualArgumentDataProvider() {
        return new Object[][] {
                { 1, 0 },
                { 1, 2 }
        };
    }

    static final String INVALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER = "invalidNotEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    private Object[][] invalidNotEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1, 1 }
        };
    }

    static final String VALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER = "validNotEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    private Object[][] validNotEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1, 0 },
                { "argument", 1, 2 },
                { "", 1, 2 },
                { null, 1, 2 }
        };
    }

    static final String INVALID_GREATER_OR_EQUAL_ARGUMENT_DATA_PROVIDER = "invalidGreaterOrEqualArgumentDataProvider";

    @DataProvider(name = INVALID_GREATER_OR_EQUAL_ARGUMENT_DATA_PROVIDER)
    private Object[][] invalidGreaterOrEqualArgumentDataProvider() {
        return new Object[][] {
                { 0, 1 }
        };
    }

    static final String VALID_GREATER_OR_EQUAL_ARGUMENT_DATA_PROVIDER = "validGreaterOrEqualArgumentDataProvider";

    @DataProvider(name = VALID_GREATER_OR_EQUAL_ARGUMENT_DATA_PROVIDER)
    private Object[][] validGreaterOrEqualArgumentDataProvider() {
        return new Object[][] {
                { 1, 1 },
                { 2, 1 }
        };
    }

    static final String INVALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER = "invalidGreaterOrEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    private Object[][] invalidGreaterOrEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 0, 1 }
        };
    }

    static final String VALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER = "validGreaterOrEqualArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    private Object[][] validGreaterOrEqualArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 1, 1 },
                { "argument", 2, 1 },
                { "", 2, 1 },
                { null, 2, 1 }
        };
    }

    static final String INVALID_GREATER_ARGUMENT_DATA_PROVIDER = "invalidGreaterArgumentDataProvider";

    @DataProvider(name = INVALID_GREATER_ARGUMENT_DATA_PROVIDER)
    private Object[][] invalidGreaterArgumentDataProvider() {
        return new Object[][] {
                { 0, 1 },
                { 1, 1 }
        };
    }

    static final String VALID_GREATER_ARGUMENT_DATA_PROVIDER = "validGreaterArgumentDataProvider";

    @DataProvider(name = VALID_GREATER_ARGUMENT_DATA_PROVIDER)
    private Object[][] validGreaterArgumentDataProvider() {
        return new Object[][] {
                { 2, 1 }
        };
    }

    static final String INVALID_GREATER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER = "invalidGreateArgumentWithArgumentNameDataProvider";

    @DataProvider(name = INVALID_GREATER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    private Object[][] invalidGreaterArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 0, 1 },
                { "argument", 1, 1 }
        };
    }

    static final String VALID_GREATER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER = "validGreaterArgumentWithArgumentNameDataProvider";

    @DataProvider(name = VALID_GREATER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    private Object[][] validGreaterArgumentWithArgumentNameDataProvider() {
        return new Object[][] {
                { "argument", 2, 1 },
                { "", 2, 1 },
                { null, 2, 1 }
        };
    }
}

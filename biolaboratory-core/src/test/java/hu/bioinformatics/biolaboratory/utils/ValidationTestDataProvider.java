package hu.bioinformatics.biolaboratory.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.testng.annotations.DataProvider;

/**
 * Data provider for {@link ValidationTest}.
 *
 * @author Attila Radi
 */
public class ValidationTestDataProvider {

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
}

package hu.bioinformatics.biolaboratory.utils;

import com.google.common.collect.Lists;
import org.testng.annotations.DataProvider;

/**
 * Data provier for {@link ValidationTest}.
 *
 * @author Attila Radi
 */
public class ValidationTestDataProvider {

    static final String INVALID_VALIDATE_VARARGS_DATA_PROVIDER_NAME = "invalidValidateVarargsDataProvider";

    @DataProvider(name = INVALID_VALIDATE_VARARGS_DATA_PROVIDER_NAME)
    Object[][] invalidValidateVarargsDataProvider() {
        return new Object[][] {
                { null },
                { new String[] { "A", null } }
        };
    }

    static final String VALID_VALIDATE_VARARGS_DATA_PROVIDER_NAME = "validValidateVarargsDataProvider";

    @DataProvider(name = VALID_VALIDATE_VARARGS_DATA_PROVIDER_NAME)
    Object[][] validValidateVarargsDataProvider() {
        return new Object[][] {
                { new String[] {} },
                { new String[] { "" } },
                { new String[] { "A" } },
                { new String[] { "A", "" } },
                { new String[] { "A", "C" } }
        };
    }

    static final String INVALID_VALIDATE_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME = "invalidValidateNotEmptyVarargsDataProvider";

    @DataProvider(name = INVALID_VALIDATE_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME)
    Object[][] invalidValidateNotEmptyVarargsDataProvider() {
        return new Object[][] {
                { null },
                { new String[] {} },
                { new String[] { "A", null } }
        };
    }

    static final String VALID_VALIDATE_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME = "validValidateNotEmptyVarargsDataProvider";

    @DataProvider(name = VALID_VALIDATE_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME)
    Object[][] validValidateNotEmptyVarargsDataProvider() {
        return new Object[][] {
                { new String[] { "" } },
                { new String[] { "A" } },
                { new String[] { "A", "" } },
                { new String[] { "A", "C" } }
        };
    }

    static final String INVALID_VALIDATE_COLLECTION_DATA_PROVIDER_NAME = "invalidValidateCollectionDataProvider";

    @DataProvider(name = INVALID_VALIDATE_COLLECTION_DATA_PROVIDER_NAME)
    Object[][] invalidValidateCollectionDataProvider() {
        return new Object[][] {
                { null },
                { Lists.newArrayList("A", null) }
        };
    }

    static final String VALID_VALIDATE_COLLECTION_DATA_PROVIDER_NAME = "validValidateCollectionDataProvider";

    @DataProvider(name = VALID_VALIDATE_COLLECTION_DATA_PROVIDER_NAME)
    Object[][] validValidateCollectionDataProvider() {
        return new Object[][] {
                { Lists.newArrayList() },
                { Lists.newArrayList("") },
                { Lists.newArrayList("A") },
                { Lists.newArrayList("A", "") },
                { Lists.newArrayList("A", "C") }
        };
    }

    static final String INVALID_VALIDATE_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME = "invalidValidateNotEmptyCollectionDataProvider";

    @DataProvider(name = INVALID_VALIDATE_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME)
    Object[][] invalidValidateNotEmptyCollectionDataProvider() {
        return new Object[][] {
                { null },
                { Lists.newArrayList() },
                { Lists.newArrayList("A", null) }
        };
    }

    static final String VALID_VALIDATE_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME = "validValidateNotEmptyCollectionDataProvider";

    @DataProvider(name = VALID_VALIDATE_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME)
    Object[][] validValidateNotEmptyCollectionDataProvider() {
        return new Object[][] {
                { Lists.newArrayList("") },
                { Lists.newArrayList("A") },
                { Lists.newArrayList("A", "") },
                { Lists.newArrayList("A", "C") }
        };
    }
}

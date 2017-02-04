package hu.bioinformatics.biolaboratory.sequence.rna;

import org.testng.annotations.DataProvider;

/**
 * Provides test data for {@link RnaTest}.
 *
 * @author Attila Radi
 */
public class RnaTestDataProvider {

    static final String INVALID_BUILD_DATA_PROVIDER_NAME = "invalidBuildDataProvider";

    @DataProvider(name = INVALID_BUILD_DATA_PROVIDER_NAME)
    static Object[][] invalidBuildDataProvider() {
        return new Object[][] {
                { null },
                { "" },
                { "            " },
                { "LOL" },
                { "ACGT" }
        };
    }

    static final String VALID_BUILD_DATA_PROVIDER_NAME = "validBuildDataProvider";

    @DataProvider(name = VALID_BUILD_DATA_PROVIDER_NAME)
    static Object[][] validBuildDataProvider() {
        return new Object[][] {
                { "A", "A" },
                { "G", "G" },
                { "U", "U" },
                { "C", "C" },
                { "AGUC", "AGUC" },
                { "aGuC", "AGUC" },
                { "        aguc            ", "AGUC" }
        };
    }

    static final String EQUALS_DATA_PROVIDER_NAME = "equalsDataProvider";

    @DataProvider(name = EQUALS_DATA_PROVIDER_NAME)
    static Object[][] equalsDataProvider() {
        return new Object[][] {
                { Rna.build("AGUC"), null, false },
                { Rna.build("AGUC"), "AGUC", false },
                { Rna.build("AGUC"), Rna.build("AGUC"), true },
                { Rna.build("AGUC"), Rna.build("U"), false },
                { Rna.build("AGUC"), Rna.build("CUGA"), false }
        };
    }
}

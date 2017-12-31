package hu.bioinformatics.biolaboratory.sequence.rna;

import com.google.common.collect.Lists;
import hu.bioinformatics.biolaboratory.sequence.protein.Protein;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;

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
                { "", null },
                { "", "" },
                { "", "            " },
                { "", "LOL" },
                { "", "ACGT" },
                { null, "ACGU" }
        };
    }

    static final String INVALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME = "invalidBuildFromElementsDataProvider";

    @DataProvider(name = INVALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    static Object[][] invalidBuildFromElementsDataProvider() {
        return new Object[][] {
                { null },
                { new RnaNucleotide[0] },
                { new RnaNucleotide[] { RnaNucleotide.ADENINE, null } }
        };
    }

    static final String INVALID_BUILD_FROM_ELEMENT_LIST_DATA_PROVIDER_NAME = "invalidBuildFromElementListDataProvider";

    @DataProvider(name = INVALID_BUILD_FROM_ELEMENT_LIST_DATA_PROVIDER_NAME)
    static Object[][] invalidBuildFromElementListDataProvider() {
        return new Object[][] {
                { null },
                { new ArrayList<>()},
                { Lists.newArrayList(RnaNucleotide.ADENINE, null) }
        };
    }

    static final String VALID_BUILD_DATA_PROVIDER_NAME = "validBuildDataProvider";

    @DataProvider(name = VALID_BUILD_DATA_PROVIDER_NAME)
    static Object[][] validBuildDataProvider() {
        return new Object[][] {
                { "", "A", "", "A" },
                { " ", "G", "", "G" },
                { "name", "U", "name", "U" },
                { " name", "C", "name", "C" },
                { "name ", "AGUC", "name", "AGUC" },
                { " name ", "aGuC", "name", "AGUC" },
                { "", "        aguc            ", "", "AGUC" }
        };
    }

    static final String VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME = "validBuildFromElementsDataProvider";

    @DataProvider(name = VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    static Object[][] validBuildFromElementsDataProvider() {
        return new Object[][] {
                { new RnaNucleotide[] { RnaNucleotide.ADENINE }, "A" },
                { new RnaNucleotide[] { RnaNucleotide.ADENINE, RnaNucleotide.CYTOSINE, RnaNucleotide.GUANINE, RnaNucleotide.URACIL }, "ACGU" }
        };
    }

    static final String INVALID_TRANSLATE_DATA_PROVIDER_NAME = "invalidTranslateDataProvider";

    @DataProvider(name = INVALID_TRANSLATE_DATA_PROVIDER_NAME)
    static Object[][] invalidTranslateDataProvider() {
        return new Object[][] {
                { Rna.build("A") },
                { Rna.build("AAA") },
                { Rna.build("AAAA") },
                { Rna.build("AAAAAAA") },
                { Rna.build("UAA") },
                { Rna.build("AAAUAAAAA") },
                { Rna.build("AUGAAA") },
                { Rna.build("AAAUGA") }
        };
    }

    static final String VALID_TRANSLATE_DATA_PROVIDER_NAME = "validTranslateDataProvider";

    @DataProvider(name = VALID_TRANSLATE_DATA_PROVIDER_NAME)
    static Object[][] validTranslateDataProvider() {
        return new Object[][] {
                { Rna.build("AUGUAA"), Protein.build("M") },
                { Rna.build("AUGACAUAA"), Protein.build("MT") },
                { Rna.build("AUGACCUAG"), Protein.build("MT") },
                { Rna.build("AUGACGUGA"), Protein.build("MT") },
                { Rna.build("AUGGCCAUGGCGCCCAGAACUGAGAUCAAUAGUACCCGUAUUAACGGGUGA"), Protein.build("MAMAPRTEINSTRING") }
        };
    }
}

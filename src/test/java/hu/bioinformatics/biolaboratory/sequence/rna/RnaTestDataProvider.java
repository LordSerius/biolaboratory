package hu.bioinformatics.biolaboratory.sequence.rna;

import com.google.common.collect.Lists;
import hu.bioinformatics.biolaboratory.sequence.protein.Protein;
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
                { Lists.newArrayList() },
                { Lists.newArrayList(RnaNucleotide.ADENINE, null) }
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

    static final String VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME = "validBuildFromElementsDataProvider";

    @DataProvider(name = VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    static Object[][] validBuildFromElementsDataProvider() {
        return new Object[][] {
                { new RnaNucleotide[] { RnaNucleotide.ADENINE }, "A" },
                { new RnaNucleotide[] { RnaNucleotide.ADENINE, RnaNucleotide.CYTOSINE, RnaNucleotide.GUANINE, RnaNucleotide.URACIL }, "ACGU" }
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

    static final String GET_SEQUENCE_AS_ELEMENTS_DATA_PROVIDER_NAME = "getSequenceAsElementsDataProvider";

    @DataProvider(name = GET_SEQUENCE_AS_ELEMENTS_DATA_PROVIDER_NAME)
    static Object[][] getSequenceAsElementsDataProvider() {
        return new Object[][] {
                { Rna.build("A"), new RnaNucleotide[] { RnaNucleotide.ADENINE } },
                { Rna.build("ACGU"), new RnaNucleotide[] { RnaNucleotide.ADENINE, RnaNucleotide.CYTOSINE, RnaNucleotide.GUANINE, RnaNucleotide.URACIL } }
        };
    }

    static final String INVALID_APPEND_DATA_PROVIDER_NAME = "invalidAppendDataProvider";

    @DataProvider(name = INVALID_APPEND_DATA_PROVIDER_NAME)
    static Object[][] invalidAppendDataProvider() {
        return new Object[][] {
                { Rna.build("A"), null }
        };
    }

    static final String INVALID_APPEND_ELEMENT_DATA_PROVIDER_NAME = "invalidAppendElementDataProvider";

    @DataProvider(name = INVALID_APPEND_ELEMENT_DATA_PROVIDER_NAME)
    static Object[][] invalidAppendElementDataProvider() {
        return new Object[][] {
                { Rna.build("A"), null }
        };
    }

    static final String VALID_APPEND_DATA_PROVIDER_NAME = "validAppendDataProvider";

    @DataProvider(name = VALID_APPEND_DATA_PROVIDER_NAME)
    static Object[][] validAppendDataProvider() {
        return new Object[][] {
                { Rna.build("ACGU"), Rna.build("A"), Rna.build("ACGUA") },
                { Rna.build("ACGU"), Rna.build("ACGU"), Rna.build("ACGUACGU") }
        };
    }

    static final String VALID_APPEND_ELEMENT_DATA_PROVIDER_NAME = "validAppendElementDataProvider";

    @DataProvider(name = VALID_APPEND_ELEMENT_DATA_PROVIDER_NAME)
    static Object[][] validAppendElementDataProvider() {
        return new Object[][] {
                { Rna.build("ACGU"), RnaNucleotide.ADENINE, Rna.build("ACGUA") }
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
                { Rna.build("AAAUAAAAA") }
        };
    }

    static final String VALID_TRANSLATE_DATA_PROVIDER_NAME = "validTranslateDataProvider";

    @DataProvider(name = VALID_TRANSLATE_DATA_PROVIDER_NAME)
    static Object[][] validTranslateDataProvider() {
        return new Object[][] {
                { Rna.build("ACAUAA"), Protein.build("T") },
                { Rna.build("ACCUAG"), Protein.build("T") },
                { Rna.build("ACGUGA"), Protein.build("T") },
                { Rna.build("AUGGCCAUGGCGCCCAGAACUGAGAUCAAUAGUACCCGUAUUAACGGGUGA"), Protein.build("MAMAPRTEINSTRING") }
        };
    }
}

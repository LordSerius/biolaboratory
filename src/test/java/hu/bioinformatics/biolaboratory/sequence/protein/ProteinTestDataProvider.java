package hu.bioinformatics.biolaboratory.sequence.protein;

import com.google.common.collect.Lists;
import org.testng.annotations.DataProvider;

/**
 * Provides test data for {@link ProteinTest}
 *
 * @author Attila Radi
 */
public class ProteinTestDataProvider {
    static final String INVALID_BUILD_PROTEIN_DATA_PROVIDER_NAME = "invalidBuildProteinDataProvider";

    @DataProvider(name = INVALID_BUILD_PROTEIN_DATA_PROVIDER_NAME)
    static Object[][] invalidBuildDataProvider() {
        return new Object[][] {
                { null },
                { "" },
                { "            " },
                { "LOL" }
        };
    }

    static final String INVALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME = "invalidBuildFromElementsDataProvider";

    @DataProvider(name = INVALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    static Object[][] invalidBuildFromElementsDataProvider() {
        return new Object[][] {
                { null },
                { new AminoAcid[0] },
                { new AminoAcid[] { AminoAcid.GLYCINE, null } }
        };
    }

    static final String INVALID_BUILD_FROM_ELEMENT_LIST_DATA_PROVIDER_NAME = "invalidBuildFromElementListDataProvider";

    @DataProvider(name = INVALID_BUILD_FROM_ELEMENT_LIST_DATA_PROVIDER_NAME)
    static Object[][] invalidBuildFromElementListDataProvider() {
        return new Object[][] {
                { null },
                { Lists.newArrayList() },
                { Lists.newArrayList(AminoAcid.GLYCINE, null) }
        };
    }

    static final String VALID_BUILD_PROTEIN_DATA_PROVIDER_NAME = "validBuildProteinDataProvider";

    @DataProvider(name = VALID_BUILD_PROTEIN_DATA_PROVIDER_NAME)
    static Object[][] validBuildDataProvider() {
        return new Object[][] {
                { "RHDESTNQCGPAILMFWYV", "RHDESTNQCGPAILMFWYV" },
                { "rhde", "RHDE" },
                { "     STNQ       ", "STNQ" },
                { "cGpA", "CGPA" }
        };
    }

    static final String VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME = "validBuildFromElementsDataProvider";

    @DataProvider(name = VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    static Object[][] validBuildFromElementsDataProvider() {
        return new Object[][] {
                { new AminoAcid[] { AminoAcid.GLYCINE }, "G" },
                { new AminoAcid[] { AminoAcid.ALANINE, AminoAcid.CYSTEINE, AminoAcid.ALANINE.GLYCINE, AminoAcid.GLUTAMIC_ACID }, "ACGE" }
        };
    }

    static final String EQUALS_DATA_PROVIDER_NAME = "equalsDataProvider";

    @DataProvider(name = EQUALS_DATA_PROVIDER_NAME)
    static Object[][] equalsDataProvider() {
        return new Object[][] {
                { Protein.build("RHDESTNQCGPAILMFWYV"), null, false },
                { Protein.build("RHDESTNQCGPAILMFWYV"), "RHDESTNQCGPAILMFWYV", false },
                { Protein.build("RHDESTNQCGPAILMFWYV"), Protein.build("RHDESTNQCGPAILMFWYV"), true },
                { Protein.build("RHDESTNQCGPAILMFWYV"), Protein.build("R"), false },
                { Protein.build("RHDESTNQCGPAILMFWYV"), Protein.build("VYWFMLIAPGCQNTSEDHR"), false }
        };
    }
}

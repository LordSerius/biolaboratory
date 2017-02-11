package hu.bioinformatics.biolaboratory.sequence.protein;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;

/**
 * Unit tests for {@link Protein}
 *
 * @author Attila Radi
 */
public class ProteinTest {

    @Test(dataProviderClass = ProteinTestDataProvider.class,
            dataProvider = ProteinTestDataProvider.INVALID_BUILD_PROTEIN_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildProteinThrowException(String sequence) {
        Protein.build(sequence);
        fail();
    }

    @Test(dataProviderClass = ProteinTestDataProvider.class,
            dataProvider = ProteinTestDataProvider.INVALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildFromNucleotidesThrowException(AminoAcid[] aminoAcids) {
        Protein.build(aminoAcids);
        fail();
    }

    @Test(dataProviderClass = ProteinTestDataProvider.class,
            dataProvider = ProteinTestDataProvider.INVALID_BUILD_FROM_ELEMENT_LIST_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildFromNucleotideListThrowException(List<AminoAcid> aminoAcidList) {
        Protein.build(aminoAcidList);
        fail();
    }

    @Test(dataProviderClass = ProteinTestDataProvider.class,
            dataProvider = ProteinTestDataProvider.VALID_BUILD_PROTEIN_DATA_PROVIDER_NAME)
    public void shouldBuildProteinReturn(String sequence, String controlSequence) {
        Protein protein = Protein.build(sequence);
        assertThat(protein.getSequence(), is(equalTo(controlSequence)));
    }

    @Test(dataProviderClass = ProteinTestDataProvider.class,
            dataProvider = ProteinTestDataProvider.EQUALS_DATA_PROVIDER_NAME)
    public void shouldEqualsReturn(Protein protein, Object rightHand, boolean isEquals) {
        boolean equalsResult = protein.equals(rightHand);
        assertThat(equalsResult, is(equalTo(isEquals)));
    }

    @Test(dataProviderClass = ProteinTestDataProvider.class,
            dataProvider = ProteinTestDataProvider.VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    public void shouldBuildFromNucleotidesReturn(AminoAcid[] aminoAcids, String controlSequence) {
        Protein protein = Protein.build(aminoAcids);
        assertThat(protein.getSequence(), is(equalTo(controlSequence)));
    }

    @Test(dataProviderClass = ProteinTestDataProvider.class,
            dataProvider = ProteinTestDataProvider.VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    public void shouldBuildFromNucleotideListReturn(AminoAcid[] aminoAcids, String controlSequence) {
        List<AminoAcid> aminoAcidList = Arrays.asList(aminoAcids);
        Protein protein = Protein.build(aminoAcidList);
        assertThat(protein.getSequence(), is(equalTo(controlSequence)));
    }
}
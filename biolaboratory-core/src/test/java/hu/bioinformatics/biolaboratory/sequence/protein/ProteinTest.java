package hu.bioinformatics.biolaboratory.sequence.protein;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

/**
 * Unit tests for {@link Protein}
 *
 * @author Attila Radi
 */
@Test(dataProviderClass = ProteinTestDataProvider.class)
public class ProteinTest {

    @Test(dataProvider = ProteinTestDataProvider.INVALID_BUILD_PROTEIN_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildProteinThrowException(String name, String sequence) {
        Protein.build(name, sequence);
    }

    @Test(dataProvider = ProteinTestDataProvider.INVALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildFromNucleotidesThrowException(AminoAcid[] aminoAcids) {
        Protein.build(aminoAcids);
    }

    @Test(dataProvider = ProteinTestDataProvider.INVALID_BUILD_FROM_ELEMENT_LIST_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildFromNucleotideListThrowException(List<AminoAcid> aminoAcidList) {
        Protein.build(aminoAcidList);
    }

    @Test(dataProvider = ProteinTestDataProvider.VALID_BUILD_PROTEIN_DATA_PROVIDER_NAME)
    public void shouldBuildProteinReturn(String name, String sequence, String controlName, String controlSequence) {
        Protein protein = Protein.build(name, sequence);
        assertThat(protein.getName(), is(equalTo(controlName)));
        assertThat(protein.getSequence(), is(equalTo(controlSequence)));
        assertThat(protein.getSequenceLength(), is(equalTo(controlSequence.length())));
    }

    @Test(dataProvider = ProteinTestDataProvider.VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    public void shouldBuildFromNucleotidesReturn(AminoAcid[] aminoAcids, String controlSequence) {
        Protein protein = Protein.build(aminoAcids);
        assertThat(protein.getSequence(), is(equalTo(controlSequence)));
        assertThat(protein.getSequenceLength(), is(equalTo(controlSequence.length())));
    }

    @Test(dataProvider = ProteinTestDataProvider.VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    public void shouldBuildFromNucleotideListReturn(AminoAcid[] aminoAcids, String controlSequence) {
        List<AminoAcid> aminoAcidList = Arrays.asList(aminoAcids);
        Protein protein = Protein.build(aminoAcidList);
        assertThat(protein.getSequence(), is(equalTo(controlSequence)));
        assertThat(protein.getSequenceLength(), is(equalTo(controlSequence.length())));
    }
}
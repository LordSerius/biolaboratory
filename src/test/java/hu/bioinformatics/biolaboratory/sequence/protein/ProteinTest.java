package hu.bioinformatics.biolaboratory.sequence.protein;

import org.testng.annotations.Test;

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
}
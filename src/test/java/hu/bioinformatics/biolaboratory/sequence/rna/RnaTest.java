package hu.bioinformatics.biolaboratory.sequence.rna;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.*;

/**
 * Unit tests for {@link Rna} class
 *
 * @author Attila Radi
 */
public class RnaTest {

    @Test(dataProviderClass = RnaTestDataProvider.class,
            dataProvider = RnaTestDataProvider.INVALID_BUILD_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildThrowException(String sequence) {
        Rna.build(sequence);
        fail();
    }

    @Test(dataProviderClass = RnaTestDataProvider.class,
            dataProvider = RnaTestDataProvider.VALID_BUILD_DATA_PROVIDER_NAME)
    public void shouldBuildReturn(String sequence, String controlSequence) {
        Rna rna = Rna.build(sequence);
        assertThat(rna.getSequence(), is(equalTo(controlSequence)));
    }

    @Test(dataProviderClass = RnaTestDataProvider.class,
            dataProvider = RnaTestDataProvider.EQUALS_DATA_PROVIDER_NAME)
    public void shouldEqualsReturn(Rna rna, Object rightHand, boolean isEquals) {
        assertThat(rna.equals(rightHand), is(equalTo(isEquals)));
    }
}
package hu.bioinformatics.biolaboratory.sequence.rna;

import hu.bioinformatics.biolaboratory.sequence.protein.AminoAcid;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

/**
 * Unit tests of {@link RnaCodonTable}.
 *
 * @author Attila Radi
 */
public class RnaCodonTableTest {

    private static final String INVALID_LOOKUP_DATA_PROVIDER_NAME = "invalidLookupDataProvider";

    @DataProvider(name = INVALID_LOOKUP_DATA_PROVIDER_NAME)
    private Object[][] invalidLookupDataProvider() {
        return new Object[][] {
                { null },
                { Rna.build("A") },
                { Rna.build("ACGU") }
        };
    }

    @Test(dataProvider = INVALID_LOOKUP_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldLookupThrowException(Rna rna) {
        RnaCodonTable.lookup(rna);
        fail();
    }

    private static final String VALID_LOOKUP_DATA_PROVIDER_NAME = "validLookupDataProvider";

    @DataProvider(name = VALID_LOOKUP_DATA_PROVIDER_NAME)
    private Object[][] validLookupDataProvider() {
        return new Object[][] {
                { Rna.build("ACG"), Optional.of(AminoAcid.findAminoAcid('T')) },
                { Rna.build("UAA"), Optional.empty() }
        };
    }

    @Test(dataProvider = VALID_LOOKUP_DATA_PROVIDER_NAME)
    public void shouldLookupReturn(Rna rna, Optional<AminoAcid> controlAminoAcid) {
        Optional<AminoAcid> aminoAcid = RnaCodonTable.lookup(rna);
        assertThat(aminoAcid, is(equalTo(controlAminoAcid)));
    }
}
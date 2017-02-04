package hu.bioinformatics.biolaboratory.sequence.protein;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;

/**
 * Test cases for {@link AminoAcid}.
 *
 * @author Attila Radi
 */
public class AminoAcidTest {

    private static final String INVALID_FIND_AMINO_ACID_DATA_PROVIDER_NAME = "invalidFindAminoAcidFindDataProvider";

    @DataProvider(name = INVALID_FIND_AMINO_ACID_DATA_PROVIDER_NAME)
    private static Object[][] invalidFindAminoAcidDataProvider() {
        return new Object[][] {
                { '-' }
        };
    }

    private static final String VALID_FIND_AMINO_ACID_DATA_PROVIDER_NAME = "validFindAminoAcidFindDataProvider";

    @DataProvider(name = VALID_FIND_AMINO_ACID_DATA_PROVIDER_NAME)
    private static Object[][] validFindAminoAcidDataProvider() {
        return new Object[][] {
                { 'R', AminoAcid.ARGININE },
                { 'h', AminoAcid.HISTIDINE },
                { 'K', AminoAcid.LYSINE },
                { 'd', AminoAcid.ASPARTIC_ACID },
                { 'E', AminoAcid.GLUTAMIC_ACID },
                { 's', AminoAcid.SERINE },
                { 'T', AminoAcid.THREONINE },
                { 'n', AminoAcid.ASPARAGINE },
                { 'Q', AminoAcid.GLUTAMINE },
                { 'c', AminoAcid.CYSTEINE },
                { 'G', AminoAcid.GLYCINE },
                { 'p', AminoAcid.PROLINE },
                { 'A', AminoAcid.ALANINE },
                { 'i', AminoAcid.ISOLEUCINE },
                { 'L', AminoAcid.LEUCINE },
                { 'm', AminoAcid.METHIONINE },
                { 'F', AminoAcid.PHENYLALANINE },
                { 'w', AminoAcid.TRYPTOPHAN },
                { 'Y', AminoAcid.TYROSIN },
                { 'v', AminoAcid.VALINE }
        };
    }

    @Test(dataProvider = INVALID_FIND_AMINO_ACID_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindAminoAcidThrowException(char aminoAcidLetter) {
        AminoAcid.findAminoAcid(aminoAcidLetter);
        fail();
    }

    @Test(dataProvider = VALID_FIND_AMINO_ACID_DATA_PROVIDER_NAME)
    public void shouldFindAminoAcidReturn(char aminoAcidLetter, AminoAcid controlAminoAcid) {
        AminoAcid aminoAcid = AminoAcid.findAminoAcid(aminoAcidLetter);
        assertThat(aminoAcid, is(equalTo(controlAminoAcid)));
    }
}
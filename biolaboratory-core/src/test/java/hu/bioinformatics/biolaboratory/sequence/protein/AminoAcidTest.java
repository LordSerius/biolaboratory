package hu.bioinformatics.biolaboratory.sequence.protein;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

/**
 * Test cases for {@link AminoAcid}.
 *
 * @author Attila Radi
 */
public class AminoAcidTest {

    private static final String INVALID_FIND_AMINO_ACID_DATA_PROVIDER_NAME = "invalidFindAminoAcidDataProvider";

    @DataProvider(name = INVALID_FIND_AMINO_ACID_DATA_PROVIDER_NAME)
    private static Object[][] invalidFindAminoAcidDataProvider() {
        return new Object[][] {
                { '-' }
        };
    }

    private static final String VALID_FIND_AMINO_ACID_DATA_PROVIDER_NAME = "validFindAminoAcidDataProvider";

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

    private static final String INVALID_FIND_AMINO_ACID_ABOUT_STRING_DATA_PROVIDER_NAME = "invalidFindAminoAcidAboutStringDataProvider";

    @DataProvider(name = INVALID_FIND_AMINO_ACID_ABOUT_STRING_DATA_PROVIDER_NAME)
    private static Object[][] invalidFindAminoAcidAboutStringDataProvider() {
        return new Object[][] {
                { null },
                { "" },
                { " " },
                { "-" },
                { "AL" }
        };
    }

    private static final String VALID_FIND_AMINO_ACID_ABOUT_STRING_DATA_PROVIDER_NAME = "validFindAminoAcidAboutStringDataProvider";

    @DataProvider(name = VALID_FIND_AMINO_ACID_ABOUT_STRING_DATA_PROVIDER_NAME)
    private static Object[][] validFindAminoAcidAboutStringDataProvider() {
        return new Object[][] {
                { "R", AminoAcid.ARGININE },
                { " h", AminoAcid.HISTIDINE },
                { "K ", AminoAcid.LYSINE },
                { " d ", AminoAcid.ASPARTIC_ACID },
                { "E", AminoAcid.GLUTAMIC_ACID },
                { " s", AminoAcid.SERINE },
                { "T ", AminoAcid.THREONINE },
                { " n ", AminoAcid.ASPARAGINE },
                { "Q", AminoAcid.GLUTAMINE },
                { " c", AminoAcid.CYSTEINE },
                { "G ", AminoAcid.GLYCINE },
                { " p ", AminoAcid.PROLINE },
                { "A", AminoAcid.ALANINE },
                { " i", AminoAcid.ISOLEUCINE },
                { "L ", AminoAcid.LEUCINE },
                { " m ", AminoAcid.METHIONINE },
                { "F", AminoAcid.PHENYLALANINE },
                { " w", AminoAcid.TRYPTOPHAN },
                { "Y ", AminoAcid.TYROSIN },
                { " v ", AminoAcid.VALINE }
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

    @Test(dataProvider = INVALID_FIND_AMINO_ACID_ABOUT_STRING_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindAminoAcidAboutStringThrowException(String aminoAcidLetter) {
        AminoAcid.findAminoAcid(aminoAcidLetter);
        fail();
    }

    @Test(dataProvider = VALID_FIND_AMINO_ACID_ABOUT_STRING_DATA_PROVIDER_NAME)
    public void shouldFindAminoAcidAboutStringReturn(String aminoAcidLetter, AminoAcid controlAminoAcid) {
        AminoAcid aminoAcid = AminoAcid.findAminoAcid(aminoAcidLetter);
        assertThat(aminoAcid, is(equalTo(controlAminoAcid)));
    }
}
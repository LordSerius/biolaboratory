package hu.bioinformatics.biolaboratory.sequence.rna;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Test cases for {@link RnaNucleotide}.
 *
 * @author Attila Radi
 */
public class RnaNucleotideTest {

    private static final String INVALID_FIND_RNA_NUCLEOTIDE_DATA_PROVIDER_NAME = "invalidFindRnaNucleotideDataProvider";

    @DataProvider(name = INVALID_FIND_RNA_NUCLEOTIDE_DATA_PROVIDER_NAME)
    private Object[][] invalidFindDnaNucleotideDataProvider() {
        return new Object[][] {
                { 'x' },
                { 'T' }
        };
    }

    private static final String VALID_FIND_RNA_NUCLEOTIDE_DATA_PROVIDER_NAME = "validFindRnaNucleotideDataProvider";

    @DataProvider(name = VALID_FIND_RNA_NUCLEOTIDE_DATA_PROVIDER_NAME)
    private Object[][] validFindRnaNucleotideReturn() {
        return new Object[][] {
                { 'A', RnaNucleotide.ADENINE },
                { 'a', RnaNucleotide.ADENINE },
                { 'C', RnaNucleotide.CYTOSINE },
                { 'c', RnaNucleotide.CYTOSINE },
                { 'G', RnaNucleotide.GUANINE },
                { 'g', RnaNucleotide.GUANINE },
                { 'U', RnaNucleotide.URACIL },
                { 'u', RnaNucleotide.URACIL }
        };
    }

    private static final String INVALID_FIND_RNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME = "invalidFindRnaNucleotideAboutStringDataProvider";

    @DataProvider(name = INVALID_FIND_RNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME)
    private Object[][] invalidFindRnaAboutStringDataProvider() {
        return new Object[][] {
                { null },
                { "" },
                { " " },
                { "-" },
                { "AG" }
        };
    }

    private static final String VALID_FIND_RNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME = "validFindRnaNucleotideAboutStringDataProvider";

    @DataProvider(name = VALID_FIND_RNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME)
    private Object[][] validFindRnaNucleotideAboutStringReturn() {
        return new Object[][] {
                { "A", RnaNucleotide.ADENINE },
                { " a", RnaNucleotide.ADENINE },
                { "C ", RnaNucleotide.CYTOSINE },
                { " c ", RnaNucleotide.CYTOSINE },
                { "G", RnaNucleotide.GUANINE },
                { " g", RnaNucleotide.GUANINE },
                { "U ", RnaNucleotide.URACIL },
                { " u ", RnaNucleotide.URACIL }
        };
    }

    @Test(dataProvider = INVALID_FIND_RNA_NUCLEOTIDE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindRnaNucleotideThrowException(char dnaLetter) {
        RnaNucleotide.findRnaNucleotide(dnaLetter);
    }

    @Test(dataProvider = VALID_FIND_RNA_NUCLEOTIDE_DATA_PROVIDER_NAME)
    public void shouldFindRnaNucleotideReturn(char dnaLetter, RnaNucleotide controlDnaNucleotide) {
        RnaNucleotide dnaNucleotide = RnaNucleotide.findRnaNucleotide(dnaLetter);
        assertThat(dnaNucleotide, is(equalTo(controlDnaNucleotide)));
    }

    @Test(dataProvider = INVALID_FIND_RNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindDnaNucleotideAboutStringThrowException(String rnaLetter) {
        RnaNucleotide.findRnaNucleotide(rnaLetter);
    }

    @Test(dataProvider = VALID_FIND_RNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME)
    public void shouldFindDnaNucleotideAboutStringReturn(String rnaLetter, RnaNucleotide controlRnaNucleotide) {
        RnaNucleotide dnaNucleotide = RnaNucleotide.findRnaNucleotide(rnaLetter);
        assertThat(dnaNucleotide, is(equalTo(controlRnaNucleotide)));
    }
}
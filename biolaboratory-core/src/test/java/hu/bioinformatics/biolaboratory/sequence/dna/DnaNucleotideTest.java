package hu.bioinformatics.biolaboratory.sequence.dna;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

/**
 * Test cases for {@link DnaNucleotide}.
 *
 * @author Attila Radi
 */
public class DnaNucleotideTest {

    private static final String INVALID_FIND_DNA_NUCLEOTIDE_DATA_PROVIDER_NAME = "invalidFindDnaNucleotideDataProvider";

    @DataProvider(name = INVALID_FIND_DNA_NUCLEOTIDE_DATA_PROVIDER_NAME)
    private Object[][] invalidFindDnaNucleotideDataProvider() {
        return new Object[][] {
                { 'x' },
                { 'U' }
        };
    }

    private static final String VALID_FIND_DNA_NUCLEOTIDE_DATA_PROVIDER_NAME = "validFindDnaNucleotideDataProvider";

    @DataProvider(name = VALID_FIND_DNA_NUCLEOTIDE_DATA_PROVIDER_NAME)
    private Object[][] validFindDnaNucleotideDataProvider() {
        return new Object[][] {
                { 'A', DnaNucleotide.ADENINE },
                { 'a', DnaNucleotide.ADENINE },
                { 'C', DnaNucleotide.CYTOSINE },
                { 'c', DnaNucleotide.CYTOSINE },
                { 'G', DnaNucleotide.GUANINE },
                { 'g', DnaNucleotide.GUANINE },
                { 'T', DnaNucleotide.THYMINE },
                { 't', DnaNucleotide.THYMINE }
        };
    }

    private static final String INVALID_FIND_DNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME = "invalidFindDnaNucleotideAboutStringDataProvider";

    @DataProvider(name = INVALID_FIND_DNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME)
    private static Object[][] invalidFindDnaNucleotideAboutStringDataProvider() {
        return new Object[][] {
                { null },
                { "" },
                { " " },
                { "-" },
                { "AG" }
        };
    }

    private static final String VALID_FIND_DNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME = "validFindDnaNucleotideAboutStringDataProvider";

    @DataProvider(name = VALID_FIND_DNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME)
    private Object[][] validFindDnaNucleotideAboutStringDataProvider() {
        return new Object[][] {
                { "A", DnaNucleotide.ADENINE },
                { " a", DnaNucleotide.ADENINE },
                { "C ", DnaNucleotide.CYTOSINE },
                { " c ", DnaNucleotide.CYTOSINE },
                { "G", DnaNucleotide.GUANINE },
                { " g", DnaNucleotide.GUANINE },
                { "T ", DnaNucleotide.THYMINE },
                { " t ", DnaNucleotide.THYMINE }
        };
    }

    private static final String VALID_FIND_DNA_NUCLEOTIDE_COMPLEMENT_DATA_PROVIDER_NAME = "validFindDnaNucleotideComplementDataProvider";

    @DataProvider(name = VALID_FIND_DNA_NUCLEOTIDE_COMPLEMENT_DATA_PROVIDER_NAME)
    private Object[][] validFindDnaNucleotideComplementReturn() {
        return new Object[][] {
                { 'A', DnaNucleotide.THYMINE },
                { 'a', DnaNucleotide.THYMINE },
                { 'C', DnaNucleotide.GUANINE },
                { 'c', DnaNucleotide.GUANINE },
                { 'G', DnaNucleotide.CYTOSINE },
                { 'g', DnaNucleotide.CYTOSINE },
                { 'T', DnaNucleotide.ADENINE },
                { 't', DnaNucleotide.ADENINE }
        };
    }

    private static final String VALID_FIND_DNA_NUCLEOTIDE_COMPLEMENT_ABOUT_STRING_DATA_PROVIDER_NAME = "validFindDnaNucleotideComplementAboutStringDataProvider";

    @DataProvider(name = VALID_FIND_DNA_NUCLEOTIDE_COMPLEMENT_ABOUT_STRING_DATA_PROVIDER_NAME)
    private Object[][] validFindDnaNucleotideComplementAboutStringReturn() {
        return new Object[][] {
                { "A", DnaNucleotide.THYMINE },
                { " a", DnaNucleotide.THYMINE },
                { "C ", DnaNucleotide.GUANINE },
                { " c ", DnaNucleotide.GUANINE },
                { "G", DnaNucleotide.CYTOSINE },
                { " g", DnaNucleotide.CYTOSINE },
                { "T ", DnaNucleotide.ADENINE },
                { " t ", DnaNucleotide.ADENINE }
        };
    }

    @Test(dataProvider = INVALID_FIND_DNA_NUCLEOTIDE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindDnaNucleotideThrowException(char dnaNucleotideLetter) {
        DnaNucleotide.findDnaNucleotide(dnaNucleotideLetter);
        fail();
    }

    @Test(dataProvider = VALID_FIND_DNA_NUCLEOTIDE_DATA_PROVIDER_NAME)
    public void shouldFindDnaNucleotideReturn(char dnaNucleotideLetter, DnaNucleotide controlDnaNucleotide) {
        DnaNucleotide dnaNucleotide = DnaNucleotide.findDnaNucleotide(dnaNucleotideLetter);
        assertThat(dnaNucleotide, is(equalTo(controlDnaNucleotide)));
    }

    @Test(dataProvider = INVALID_FIND_DNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindDnaNucleotideAboutStringThrowException(String dnaNucleotideLetter) {
        DnaNucleotide.findDnaNucleotide(dnaNucleotideLetter);
        fail();
    }

    @Test(dataProvider = VALID_FIND_DNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME)
    public void shouldFindDnaNucleotideAboutStringReturn(String dnaNucleotideLetter, DnaNucleotide controlDnaNucleotide) {
        DnaNucleotide dnaNucleotide = DnaNucleotide.findDnaNucleotide(dnaNucleotideLetter);
        assertThat(dnaNucleotide, is(equalTo(controlDnaNucleotide)));
    }

    @Test(dataProvider = INVALID_FIND_DNA_NUCLEOTIDE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindDnaNucleotideComplementThrowException(char dnaNucleotideLetter) {
        DnaNucleotide.findDnaNucleotideComplement(dnaNucleotideLetter);
        fail();
    }

    @Test(dataProvider = VALID_FIND_DNA_NUCLEOTIDE_COMPLEMENT_DATA_PROVIDER_NAME)
    public void shouldFindDnaNucleotideComplementReturn(char dnaNucleotideLetter, DnaNucleotide controlDnaNucleotide) {
        DnaNucleotide dnaNucleotide = DnaNucleotide.findDnaNucleotideComplement(dnaNucleotideLetter);
        assertThat(dnaNucleotide, is(equalTo(controlDnaNucleotide)));
    }

    @Test(dataProvider = INVALID_FIND_DNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindDnaNucleotideComplementAboutStringThrowException(String dnaNucleotideLetter) {
        DnaNucleotide.findDnaNucleotide(dnaNucleotideLetter);
        fail();
    }

    @Test(dataProvider = VALID_FIND_DNA_NUCLEOTIDE_COMPLEMENT_ABOUT_STRING_DATA_PROVIDER_NAME)
    public void shouldFindDnaNucleotideComplementAboutStringReturn(String dnaNucleotideLetter, DnaNucleotide controlDnaNucleotide) {
        DnaNucleotide dnaNucleotide = DnaNucleotide.findDnaNucleotideComplement(dnaNucleotideLetter);
        assertThat(dnaNucleotide, is(equalTo(controlDnaNucleotide)));
    }
}
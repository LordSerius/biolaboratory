package hu.bioinformatics.biolaboratory.sequence.dna;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
    private Object[][] validFindDnaNucleotideReturn() {
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

    @Test(dataProvider = INVALID_FIND_DNA_NUCLEOTIDE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindDnaNucleotideThrowException(char dnaLetter) {
        DnaNucleotide.findDnaNucleotide(dnaLetter);
        fail();
    }

    @Test(dataProvider = VALID_FIND_DNA_NUCLEOTIDE_DATA_PROVIDER_NAME)
    public void shouldFindDnaNucleotideReturn(char dnaLetter, DnaNucleotide controlDnaNucleotide) {
        DnaNucleotide dnaNucleotide = DnaNucleotide.findDnaNucleotide(dnaLetter);
        assertThat(dnaNucleotide, is(equalTo(controlDnaNucleotide)));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldFindDnaNucleotideComplementThrowException() {
        DnaNucleotide.findDnaNucleotideComplement('x');
        fail();
    }

    @Test(dataProvider = VALID_FIND_DNA_NUCLEOTIDE_COMPLEMENT_DATA_PROVIDER_NAME)
    public void shouldFindDnaNucleotideComplementReturn(char dnaLetter, DnaNucleotide controlDnaNucleotide) {
        DnaNucleotide dnaNucleotide = DnaNucleotide.findDnaNucleotideComplement(dnaLetter);
        assertThat(dnaNucleotide, is(equalTo(controlDnaNucleotide)));
    }

    @Test(dataProvider = INVALID_FIND_DNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindDnaNucleotideAboutStringThrowException(String dnaLetter) {
        DnaNucleotide.findDnaNucleotide(dnaLetter);
        fail();
    }

    @Test(dataProvider = VALID_FIND_DNA_NUCLEOTIDE_DATA_PROVIDER_NAME)
    public void shouldFindDnaNucleotideAboutStringReturn(char dnaLetter, DnaNucleotide controlDnaNucleotide) {
        DnaNucleotide dnaNucleotide = DnaNucleotide.findDnaNucleotide(Character.toString(dnaLetter));
        assertThat(dnaNucleotide, is(equalTo(controlDnaNucleotide)));
    }

    @Test(dataProvider = INVALID_FIND_DNA_NUCLEOTIDE_ABOUT_STRING_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindDnaNucleotideComlementAboutStringThrowException(String dnaLetter) {
        DnaNucleotide.findDnaNucleotide(dnaLetter);
        fail();
    }

    @Test(dataProvider = VALID_FIND_DNA_NUCLEOTIDE_COMPLEMENT_DATA_PROVIDER_NAME)
    public void shouldFindDnaNucleotideComplementAboutStringReturn(char dnaLetter, DnaNucleotide controlDnaNucleotide) {
        DnaNucleotide dnaNucleotide = DnaNucleotide.findDnaNucleotideComplement(Character.toString(dnaLetter));
        assertThat(dnaNucleotide, is(equalTo(controlDnaNucleotide)));
    }
}
package hu.bioinformatics.biolaboratory.sequence.rna;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;

/**
 * Test cases for {@link RnaNucleotide}.
 *
 * @author Attila Radi
 */
public class RnaNucleotideTest {

    private static final String INVALID_FIND_DNA_NUCLEOTIDE_DATA_PROVIDER_NAME = "invalidFindDnaNucleotideDataProvider";

    @DataProvider(name = INVALID_FIND_DNA_NUCLEOTIDE_DATA_PROVIDER_NAME)
    private Object[][] invalidFindDnaNucleotideDataProvider() {
        return new Object[][] {
                { 'x' },
                { 'T' }
        };
    }

    @Test(dataProvider = INVALID_FIND_DNA_NUCLEOTIDE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindDnaNucleotideThrowException(char dnaLetter) {
        RnaNucleotide.findDnaNucleotide(dnaLetter);
        fail();
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

    @Test(dataProvider = VALID_FIND_RNA_NUCLEOTIDE_DATA_PROVIDER_NAME)
    public void shouldFindRnaNucleotideReturn(char dnaLetter, RnaNucleotide controlDnaNucleotide) {
        RnaNucleotide dnaNucleotide = RnaNucleotide.findDnaNucleotide(dnaLetter);
        assertThat(dnaNucleotide, is(equalTo(controlDnaNucleotide)));
    }
}
package hu.bioinformatics.biolaboratory.sequence.rna;

import hu.bioinformatics.biolaboratory.sequence.protein.Protein;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.*;

/**
 * Unit tests for {@link Rna} class
 *
 * @author Attila Radi
 */
@Test(dataProviderClass = RnaTestDataProvider.class)
public class RnaTest {

    @Test(dataProvider = RnaTestDataProvider.INVALID_BUILD_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildThrowException(String name, String sequence) {
        Rna.build(name, sequence);
        fail();
    }

    @Test(dataProvider = RnaTestDataProvider.INVALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildFromNucleotidesThrowException(RnaNucleotide[] rnaNucleotides) {
        Rna.build(rnaNucleotides);
        fail();
    }

    @Test(dataProvider = RnaTestDataProvider.INVALID_BUILD_FROM_ELEMENT_LIST_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildFromNucleotideListThrowException(List<RnaNucleotide> rnaNucleotideList) {
        Rna.build(rnaNucleotideList);
        fail();
    }

    @Test(dataProvider = RnaTestDataProvider.VALID_BUILD_DATA_PROVIDER_NAME)
    public void shouldBuildReturn(String name, String sequence, String controlName, String controlSequence) {
        Rna rna = Rna.build(name, sequence);
        assertThat(rna.getName(), is(equalTo(controlName)));
        assertThat(rna.getSequence(), is(equalTo(controlSequence)));
    }

    @Test(dataProvider = RnaTestDataProvider.VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    public void shouldBuildFromNucleotidesReturn(RnaNucleotide[] rnaNucleotides, String controlSequence) {
        Rna rna = Rna.build(rnaNucleotides);
        assertThat(rna.getSequence(), is(equalTo(controlSequence)));
    }

    @Test(dataProvider = RnaTestDataProvider.VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    public void shouldBuildFromNucleotideListReturn(RnaNucleotide[] rnaNucleotides, String controlSequence) {
        List<RnaNucleotide> rnaNucleotideList = Arrays.asList(rnaNucleotides);
        Rna rna = Rna.build(rnaNucleotideList);
        assertThat(rna.getSequence(), is(equalTo(controlSequence)));
    }

    @Test(dataProvider = RnaTestDataProvider.EQUALS_DATA_PROVIDER_NAME)
    public void shouldEqualsReturn(Rna rna, Object rightHand, boolean isEquals) {
        assertThat(rna.equals(rightHand), is(equalTo(isEquals)));
    }

    @Test(dataProvider = RnaTestDataProvider.GET_SEQUENCE_AS_ELEMENTS_DATA_PROVIDER_NAME)
    public void shouldGetSequenceAsElementsReturn(Rna rna, RnaNucleotide[] controlNucleotides) {
        RnaNucleotide[] nucleotides = rna.getSequenceAsElements();
        assertThat(nucleotides, is(equalTo(controlNucleotides)));
    }

    @Test(dataProvider = RnaTestDataProvider.INVALID_APPEND_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldAppendThrowException(Rna rna, Rna otherRna) {
        rna.append(otherRna);
        fail();
    }

    @Test(dataProvider = RnaTestDataProvider.INVALID_APPEND_ELEMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldAppendElementThrowElement(Rna rna, RnaNucleotide nucleotide) {
        rna.append(nucleotide);
        fail();
    }

    @Test(dataProvider = RnaTestDataProvider.VALID_APPEND_DATA_PROVIDER_NAME)
    public void shouldAppendReturn(Rna rna, Rna otherRna, Rna controlRna) {
        Rna appendedRna = rna.append(otherRna);
        assertThat(appendedRna, is(equalTo(controlRna)));
    }

    @Test(dataProvider = RnaTestDataProvider.VALID_APPEND_ELEMENT_DATA_PROVIDER_NAME)
    public void shouldAppendElementReturn(Rna rna, RnaNucleotide nucleotide, Rna controlRna) {
        Rna appendedRna = rna.append(nucleotide);
        assertThat(appendedRna, is(equalTo(controlRna)));
    }

    @Test(dataProvider = RnaTestDataProvider.INVALID_TRANSLATE_DATA_PROVIDER_NAME,
            expectedExceptions = RnaTranslationException.class)
    public void shouldTranslateThrowException(Rna rna) throws RnaTranslationException {
        rna.translate();
        fail();
    }

    @Test(dataProvider = RnaTestDataProvider.VALID_TRANSLATE_DATA_PROVIDER_NAME)
    public void shouldTranslateReturn(Rna rna, Protein controlProtein) throws RnaTranslationException {
        Protein protein = rna.translate();
        assertThat(protein, is(equalTo(controlProtein)));
    }
}
package hu.bioinformatics.biolaboratory.sequence.rna;

import hu.bioinformatics.biolaboratory.sequence.protein.Protein;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

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
        assertThat(rna.getSequenceLength(), is(equalTo(controlSequence.length())));
    }

    @Test(dataProvider = RnaTestDataProvider.VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    public void shouldBuildFromNucleotidesReturn(RnaNucleotide[] rnaNucleotides, String controlSequence) {
        Rna rna = Rna.build(rnaNucleotides);
        assertThat(rna.getSequence(), is(equalTo(controlSequence)));
        assertThat(rna.getSequenceLength(), is(equalTo(controlSequence.length())));
    }

    @Test(dataProvider = RnaTestDataProvider.VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    public void shouldBuildFromNucleotideListReturn(RnaNucleotide[] rnaNucleotides, String controlSequence) {
        List<RnaNucleotide> rnaNucleotideList = Arrays.asList(rnaNucleotides);
        Rna rna = Rna.build(rnaNucleotideList);
        assertThat(rna.getSequence(), is(equalTo(controlSequence)));
        assertThat(rna.getSequenceLength(), is(equalTo(controlSequence.length())));
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
package hu.bioinformatics.biolaboratory.sequence.dna;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.guice.GuiceTestModule;
import hu.bioinformatics.biolaboratory.sequence.rna.Rna;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.fail;

/**
 * Test cases for the {@link Dna} class.
 * 
 * @author Attila Radi
 *
 */
@Guice(modules = {GuiceCoreModule.class, GuiceResourceModule.class, GuiceTestModule.class})
@Test(dataProviderClass = DnaTestDataProvider.class)
public class DnaTest {
    
    @Test(dataProvider = DnaTestDataProvider.INVALID_DNA_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildThrowException(String name, String sequence) {
        Dna.build(name, sequence);
        fail();
    }

    @Test(dataProvider = DnaTestDataProvider.INVALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildFromNucleotidesThrowException(DnaNucleotide[] dnaNucleotides) {
        Dna.build(dnaNucleotides);
        fail();
    }

    @Test(dataProvider = DnaTestDataProvider.INVALID_BUILD_FROM_ELEMENT_LIST_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildFromNucleotideListThrowException(List<DnaNucleotide> dnaNucleotideList) {
        Dna.build(dnaNucleotideList);
        fail();
    }
    
    @Test(dataProvider = DnaTestDataProvider.VALID_DNA_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldBuildReturn(String name, String sequence, String controlName, String controlSequence) {
        Dna dna = Dna.build(name, sequence);
        assertThat(dna.getName(), is(equalTo(controlName)));
        assertThat(dna.getSequence(), is(equalTo(controlSequence)));
        assertThat(dna.getSequenceLength(), is(equalTo(controlSequence.length())));
    }

    @Test(dataProvider = DnaTestDataProvider.VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    public void shouldBuildFromNucleotidesReturn(DnaNucleotide[] dnaNucleotides, String controlSequence) {
        Dna dna = Dna.build(dnaNucleotides);
        assertThat(dna.getSequence(), is(equalTo(controlSequence)));
        assertThat(dna.getSequenceLength(), is(equalTo(controlSequence.length())));
    }

    @Test(dataProvider = DnaTestDataProvider.VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    public void shouldBuildFromNucleotideListReturn(DnaNucleotide[] rnaNucleotides, String controlSequence) {
        List<DnaNucleotide> dnaNucleotideList = Arrays.asList(rnaNucleotides);
        Dna dna = Dna.build(dnaNucleotideList);
        assertThat(dna.getSequence(), is(equalTo(controlSequence)));
        assertThat(dna.getSequenceLength(), is(equalTo(controlSequence.length())));
    }
    
    @Test(dataProvider = DnaTestDataProvider.VALID_MOST_FREQUENT_SUBSEQUENCES_DATA_PROVIDER_NAME)
    public void shouldMostFrequentSubSequencesReturn(Dna dna, int k, Set<Dna> controlMostFrequentSubSequences) {
        Set<Dna> mostFrequentSubSequences = dna.findMostFrequentSubSequences(k);
        assertThat(mostFrequentSubSequences, is(equalTo(controlMostFrequentSubSequences)));
    }
    
    @Test(dataProvider = DnaTestDataProvider.VALID_COMPLEMENT_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldComplementThreadReturn(Dna dna, Dna complementDna) {
        Dna complementThread = dna.getComplementThread();
        assertThat(complementThread, allOf(
                is(equalTo(complementDna)),
                is(not(sameInstance(dna.getComplementThread()))),
                is(equalTo(dna.getComplementThread()))
        ));
    }
    
    @Test(dataProvider = DnaTestDataProvider.VALID_REVERSE_COMPLEMENT_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldReverseComplementThreadReturn(Dna dna, Dna reverseComplementDna) {
        Dna reverseComplementThread = dna.getReverseComplementThread();
        assertThat(reverseComplementThread, allOf(
                is(equalTo(reverseComplementDna)),
                is(not(sameInstance(dna.getReverseComplementThread()))),
                is(equalTo(dna.getReverseComplementThread()))
        ));
    }
    
    @Test(dataProvider = DnaTestDataProvider.IS_COMPLEMENT_STATEMENTS_DATA_PROVIDER_NAME)
    public void shouldIsComplementReturn(Dna dna, Dna complementDna, boolean isComplement) {
        assertThat(dna.isComplement(complementDna), is(equalTo(isComplement)));
    }
    
    @Test(dataProvider = DnaTestDataProvider.IS_REVERSE_COMPLEMENT_STATEMENTS_DATA_PROVIDER_NAME)
    public void shouldIsReverseComplementReturn(Dna dna, Dna reverseComplementDna, boolean isReverseComplement) {
        assertThat(dna.isReverseComplement(reverseComplementDna), is(equalTo(isReverseComplement)));
    }

    @Test(dataProvider = DnaTestDataProvider.GUANINE_CYTOSINE_RATIO_DATA_PROVIDER_NAME)
    public void shouldGuanineCytosineRatioReturn(Dna dna, double controlRatio) {
        double guanineCytosineRatio = dna.getGuanineCytosineRatio();
        assertThat(guanineCytosineRatio, is(closeTo(controlRatio, 0.001)));
    }
    
    @Test(dataProvider = DnaTestDataProvider.MINIMUM_SKEW_DATA_PROVIDER_NAME)
    public void shouldMinimumSkewReturn(Dna dna, List<String> controlSkewList) {
        List<Integer> minimumSkew = dna.minimumSkew();
        assertThat(minimumSkew, allOf(
                is(equalTo(controlSkewList)),
                is(not(sameInstance(dna.minimumSkew()))),
                is(equalTo(dna.minimumSkew()))
        ));
    }

    @Test(dataProvider = DnaTestDataProvider.INVALID_FIND_FREQUENT_PATTERNS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindFrequentPatternsThrowException(Dna dna, int k, int d, int t) {
        dna.findFrequentMismatchPatterns(k, d, t);
        fail();
    }

    @Test(dataProvider = DnaTestDataProvider.VALID_FIND_FREQUENT_PATTERNS_DATA_PROVIDER_NAME)
    public void shouldFindFrequentPatternsReturn(Dna dna, int k, int d, int t, Set<Dna> controlFrequentSubSequences) {
        Set<Dna> frequentSubSequences = dna.findFrequentMismatchPatterns(k, d, t);
        assertThat(frequentSubSequences, is(equalTo(controlFrequentSubSequences)));
    }
    
    @Test(dataProvider = DnaTestDataProvider.INVALID_GET_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindMostFrequentPatternsThrowException(Dna dna, int k, int d) {
        dna.findMostFrequentMismatchPatterns(k, d);
        fail();
    }
    
    @Test(dataProvider = DnaTestDataProvider.VALID_FIND_MOST_FREQUENT_PATTERNS_DATA_PROVIDER_NAME)
    public void shouldFindMostFrequentPatternsReturn(Dna dna, int k, int d, Set<Dna> controlFrequentSubSequences) {
        Set<Dna> frequentSubSequences = dna.findMostFrequentMismatchPatterns(k, d);
        assertThat(frequentSubSequences, is(equalTo(controlFrequentSubSequences)));
    }

    @Test(dataProvider = DnaTestDataProvider.TRANSCRIPT_STRAIGHT_DATA_PROVIDER_NAME)
    public void shouldTranscriptStraightReturn(Dna dna, Rna controlRna) {
        Rna transcriptRna = dna.transcriptStraight();
        assertThat(transcriptRna, is(equalTo(controlRna)));
    }

    @Test(dataProvider = DnaTestDataProvider.TRANSCRIPT_REVERSE_COMPLEMENT_DATA_PROVIDER_NAME)
    public void shouldTranscriptReverseComplementReturn(Dna dna, Rna controlRna) {
        Rna reverseComplementRna = dna.transcriptReverseComplement();
        assertThat(reverseComplementRna, is(equalTo(controlRna)));
    }

    @Test(dataProvider = DnaTestDataProvider.TRANSCRIPT_DNA_DATA_PROVIDER_NAME)
    public void shouldTranscriptReturn(Dna dna, Rna[] controlRnaArray) {
        Rna[] transcriptRnas = dna.transcript();
        assertThat(transcriptRnas, is((equalTo(controlRnaArray))));
    }
}

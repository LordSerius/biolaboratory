package hu.bioinformatics.biolaboratory.sequence.dna;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Set;

import hu.bioinformatics.biolaboratory.guice.GuiceTestModule;
import hu.bioinformatics.biolaboratory.sequence.rna.Rna;
import hu.bioinformatics.biolaboratory.utils.datastructures.CountableOccurrenceMap;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * Test cases for the {@link Dna} class.
 * 
 * @author Attila Radi
 *
 */
@Guice(modules = GuiceTestModule.class)
public class DnaTest {
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_DNA_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildThrowException(String sequence) {
        Dna.build(sequence);
        fail();
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_DNA_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldBuildReturn(String sequence, String controlSequence) {
        Dna dna = Dna.build(sequence);
        String dnaSequence = dna.getSequence();
        assertThat(dnaSequence, is(equalTo(controlSequence)));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.DNA_COPY_DATA_PROVIDER_NAME)
    public void shouldCreateCopy(Dna dna) {
        Dna dnaCopy = dna.copy();
        assertThat(dnaCopy, allOf(
                is(not(sameInstance(dna))),
                is(equalTo(dna))
        ));
        assertThat(dnaCopy.hashCode(), is(equalTo(dna.hashCode())));
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.GET_NUCLEOBASE_OCCURRENCES_DATA_PROVIDER_NAME)
    public void shouldGetNucleotideOccurrences(Dna dna, CountableOccurrenceMap<DnaNucleotide> controlNucleotideOccurrences) {
        CountableOccurrenceMap<DnaNucleotide> nucleotideOccurrences = dna.getElementOccurrences();
        int noAdenine = dna.getAdenineNumber();
        int noCytosine = dna.getCytosineNumber();
        int noGuanine = dna.getGuanineNumber();
        int noThymine = dna.getThymineNumber();
        assertThat(nucleotideOccurrences, allOf(
                is(equalTo(controlNucleotideOccurrences)),
                is(not(sameInstance(dna.getElementOccurrences())))
        ));
        assertThat(noAdenine, is(equalTo(controlNucleotideOccurrences.getOccurrence(DnaNucleotide.ADENINE))));
        assertThat(noCytosine, is(equalTo(controlNucleotideOccurrences.getOccurrence(DnaNucleotide.CYTOSINE))));
        assertThat(noGuanine, is(equalTo(controlNucleotideOccurrences.getOccurrence(DnaNucleotide.GUANINE))));
        assertThat(noThymine, is(equalTo(controlNucleotideOccurrences.getOccurrence(DnaNucleotide.THYMINE))));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.DNA_EQUALS_DATA_PROVIDER_NAME)
    public void shouldEqualsReturn(Dna dna, Object rightHand, boolean isEquals) {
        boolean equalsResult = dna.equals(rightHand);
        assertThat(equalsResult, is(equalTo(isEquals)));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.DNA_HASH_DATA_PROVIDER_NAME)
    public void shouldHashCodeReturn(Dna dna, Object controlHashValue, boolean isEquals) {
        boolean hashCodeCompareResult = dna.hashCode() == controlHashValue.hashCode();
        assertThat(hashCodeCompareResult, is(equalTo(isEquals)));
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_CUT_PARTS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCutPartThrowException(Dna dna, int startPosition, int endPosition) {
        dna.cut(startPosition, endPosition);
        fail();
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_CUT_PARTS_DATA_PROVIDER_NAME)
    public void shouldCutPartReturn(Dna dna, int startPosition, int endPosition, Dna controlPart) {
        Dna dnaPart = dna.cut(startPosition, endPosition);
        assertThat(dnaPart, is(equalTo(controlPart)));
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_CUT_TO_END_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCutToTheEndThrowException(Dna dna, int startPosition) {
        dna.cut(startPosition);
        fail();
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_CUT_TO_END_DATA_PROVIDER_NAME)
    public void shouldCutToTheEndReturn(Dna dna, int startPosition, Dna controlPart) {
        Dna dnaPart = dna.cut(startPosition);
        assertThat(dnaPart, is(equalTo(controlPart)));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldPatternCountThrowExceptionForBlankPattern(Dna dna, Dna pattern) {
        dna.patternCount(pattern);
        fail();
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME)
    public void shouldPatternMatchingReturn(Dna dna, Dna pattern, List<Integer> controlPositions) {
        List<Integer> patternPositions = dna.patternMatching(pattern);
        assertThat(patternPositions, is(equalTo(controlPositions)));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME)
    public void shouldPatternCountReturn(Dna dna, Dna pattern, List<Integer> controlPositions) {
        int patternNumber = dna.patternCount(pattern);
        assertThat(patternNumber, is(equalTo(controlPositions.size())));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_GET_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldMostFrequentSubSequencesThrowException(Dna dna, int k) {
        dna.findMostFrequentSubSequences(k);
        fail();
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_MOST_FREQUENT_SUBSEQUENCES_DATA_PROVIDER_NAME)
    public void shouldMostFrequentSubSequencesReturn(Dna dna, int k, Set<Dna> controlMostFrequentSubSequences) {
        Set<Dna> mostFrequentSubSequences = dna.findMostFrequentSubSequences(k);
        assertThat(mostFrequentSubSequences, is(equalTo(controlMostFrequentSubSequences)));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_SUBSEQUENCES_IN_CLUMPS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindPatternsInClumpsThrowException(Dna dna, int k, int L, int t) {
        dna.findPatternsInClumps(k, L, t);
        fail();
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_SUBSEQUENCES_IN_CLUMPS_DATA_PROVIDER_NAME)
    public void shouldFindPatternsInClumpsReturn(Dna dna, int k,int L, int t, Set<Dna> controlFrequentSubSequences) {
        Set<Dna> patternsInClumps = dna.findPatternsInClumps(k, L, t);
        assertThat(patternsInClumps, is(equalTo(controlFrequentSubSequences)));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_COMPLEMENT_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldComplementThreadReturn(Dna dna, Dna complementDna) {
        Dna complementThread = dna.getComplementThread();
        assertThat(complementThread, allOf(
                is(equalTo(complementDna)),
                is(not(sameInstance(dna.getComplementThread()))),
                is(equalTo(dna.getComplementThread()))
        ));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_REVERSE_COMPLEMENT_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldReverseComplementThreadReturn(Dna dna, Dna reverseComplementDna) {
        Dna reverseComplementThread = dna.getReverseComplementThread();
        assertThat(reverseComplementThread, allOf(
                is(equalTo(reverseComplementDna)),
                is(not(sameInstance(dna.getReverseComplementThread()))),
                is(equalTo(dna.getReverseComplementThread()))
        ));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.IS_COMPLEMENT_STATEMENTS_DATA_PROVIDER_NAME)
    public void shouldIsComplementReturn(Dna dna, Dna complementDna, boolean isComplement) {
        assertThat(dna.isComplement(complementDna), is(equalTo(isComplement)));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.IS_REVERSE_COMPLEMENT_STATEMENTS_DATA_PROVIDER_NAME)
    public void shouldIsReverseComplementReturn(Dna dna, Dna reverseComplementDna, boolean isReverseComplement) {
        assertThat(dna.isReverseComplement(reverseComplementDna), is(equalTo(isReverseComplement)));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.MINIMUM_SKEW_DATA_PROVIDER_NAME)
    public void shouldMinimumSkewReturn(Dna dna, List<String> controlSkewList) {
        List<Integer> minimumSkew = dna.minimumSkew();
        assertThat(minimumSkew, allOf(
                is(equalTo(controlSkewList)),
                is(not(sameInstance(dna.minimumSkew()))),
                is(equalTo(dna.minimumSkew()))
        ));
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_HAMMING_DISTANCE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForGetMismatchNumber(Dna dna1, Dna dna2) {
        dna1.getMismatchNumber(dna2);
        fail();
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_HAMMING_DISTANCE_DATA_PROVIDER_NAME)
    public void shouldGetMismatchNumberReturn(Dna dna1, Dna dna2, int controlMismatchNumber) {
        int mismatchNumber = dna1.getMismatchNumber(dna2);
        assertThat(mismatchNumber, is(equalTo(controlMismatchNumber)));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_PATTERN_ARGUMENTS_WITH_MISMATCHES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForPatternMatchingWithMismatches(Dna dna, Dna pattern, int d) {
        dna.patternMatchingWithMismatches(pattern, d);
        fail();
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_PATTERN_ARGUMENTS_WITH_MISMATCHES_DATA_PROVIDER_NAME)
    public void shouldForPatternMatchingWithMismatchesReturn(Dna dna, Dna pattern, int d, List<Integer> controlList) {
        List<Integer> patternPositions = dna.patternMatchingWithMismatches(pattern, d);
        assertThat(patternPositions, is(equalTo(controlList)));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_PATTERN_ARGUMENTS_WITH_MISMATCHES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForPatternCountWithMismatches(Dna dna, Dna pattern, int d) {
        dna.patternCountWithMismatches(pattern, d);
        fail();
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_PATTERN_ARGUMENTS_WITH_MISMATCHES_DATA_PROVIDER_NAME)
    public void shouldPatternCountWithMismatchesReturn(Dna dna, Dna pattern, int d, List<Integer> controlList) {
        int mismatchPatternNumber = dna.patternCountWithMismatches(pattern, d);
        assertThat(mismatchPatternNumber, is(equalTo(controlList.size())));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_MISMATCH_NUMBERS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGenerateMismatchesThrowException(Dna dna, int d) {
        dna.generateMismatches(d);
        fail();
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_GENERATE_MISMATCHES_DATA_PROVIDER_NAME)
    public void shouldGenerateMismatchesReturn(Dna dna, int d, Set<Dna> controlMismatchSet) {
        Set<Dna> generatedMismatchSet = dna.generateMismatches(d);
        assertEquals(generatedMismatchSet, controlMismatchSet);
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_FIND_FREQUENT_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindFrequentMismatchSubSequencesThrowException(Dna dna, int k, int d, int t) {
        dna.findFrequentMismatchSubSequences(k, d, t);
        fail();
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_FIND_FREQUENT_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldFindFrequentMismatchSubSequencesReturn(Dna dna, int k, int d, int t, Set<Dna> controlFrequentSubSequences) {
        Set<Dna> frequentSubSequences = dna.findFrequentMismatchSubSequences(k, d, t);
        assertThat(frequentSubSequences, is(equalTo(controlFrequentSubSequences)));
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_FIND_FREQUENT_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindFrequentSubSequencesThrowException(Dna dna, int k, int t) {
        dna.findFrequentSubSequences(k, t);
        fail();
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_FIND_FREQUENT_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldFindFrequentSubSequencesReturn(Dna dna, int k, int t, Set<Dna> controlSet) {
        Set<Dna> frequentSubSequences = dna.findFrequentSubSequences(k, t);
        assertThat(frequentSubSequences, is(equalTo(controlSet)));
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_GET_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetMismatchSubSequencesThrowException(Dna dna, int k, int d) {
        dna.getMismatchSubSequences(k, d);
        fail();
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_GET_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldGetMismatchSubSequencesReturn(Dna dna, int k, int d, Set<Dna> controlSet) {
        Set<Dna> mismatchSubSequences = dna.getMismatchSubSequences(k, d);
        assertThat(mismatchSubSequences, is(equalTo(controlSet)));
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_GET_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetSubSequenceThrowException(Dna dna, int k) {
        dna.getSubSequences(k);
        fail();
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_GET_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldGetSubSequencesReturn(Dna dna, int k, Set<Dna> controlSet) {
        Set<Dna> subSequences = dna.getSubSequences(k);
        assertThat(subSequences, is(equalTo(controlSet)));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_GET_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindMostFrequentMismatchSubSequencesThrowException(Dna dna, int k, int d) {
        dna.findMostFrequentMismatchSubSequences(k, d);
        fail();
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_FIND_MOST_FREQUENT_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldFindMostFrequentMismatchSubSequencesReturn(Dna dna, int k, int d, Set<Dna> controlFrequentSubSequences) {
        Set<Dna> frequentSubSequences = dna.findMostFrequentMismatchSubSequences(k, d);
        assertThat(frequentSubSequences, is(equalTo(controlFrequentSubSequences)));
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_GET_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindMostFrequentSubSequencesThrowException(Dna dna, int k) {
        dna.findMostFrequentSubSequences(k);
        fail();
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_FIND_MOST_FREQUENT_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldFindMostFrequentSubSequencesReturn(Dna dna, int k, Set<Dna> controlSet) {
        Set<Dna> mostFrequentSubSequences = dna.findMostFrequentSubSequences(k);
        assertThat(mostFrequentSubSequences, is(equalTo(controlSet)));
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_FIND_FREQUENT_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindFrequentPatternsThrowException(Dna dna, int k, int d, int t) {
        dna.findFrequentMismatchPatterns(k, d, t);
        fail();
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_FIND_FREQUENT_PATTERNS_DATA_PROVIDER_NAME)
    public void shouldFindFrequentPatternsReturn(Dna dna, int k, int d, int t, Set<Dna> controlFrequentSubSequences) {
        Set<Dna> frequentSubSequences = dna.findFrequentMismatchPatterns(k, d, t);
        assertThat(frequentSubSequences, is(equalTo(controlFrequentSubSequences)));
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.INVALID_GET_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindMostFrequentPatternsThrowException(Dna dna, int k, int d) {
        dna.findMostFrequentMismatchSubSequences(k, d);
        fail();
    }
    
    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.VALID_FIND_MOST_FREQUENT_PATTERNS_DATA_PROVIDER_NAME)
    public void shouldFindMostFrequentPatternsReturn(Dna dna, int k, int d, Set<Dna> controlFrequentSubSequences) {
        Set<Dna> frequentSubSequences = dna.findMostFrequentMismatchPatterns(k, d);
        assertThat(frequentSubSequences, is(equalTo(controlFrequentSubSequences)));
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.TRANSCRIPT_STRAIGHT_DATA_PROVIDER_NAME)
    public void shouldTranscriptStraightReturn(Dna dna, Rna controlRna) {
        Rna transcriptRna = dna.transcriptStraight();
        assertThat(transcriptRna, is(equalTo(controlRna)));
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.TRANSCRIPT_REVERSE_COMPLEMENT_DATA_PROVIDER_NAME)
    public void shouldTranscriptReverseComplementReturn(Dna dna, Rna controlRna) {
        Rna reverseComplementRna = dna.transcriptReverseComplement();
        assertThat(reverseComplementRna, is(equalTo(controlRna)));
    }

    @Test(dataProviderClass = DnaTestDataProvider.class,
            dataProvider = DnaTestDataProvider.TRANSCRIPT_DNA_DATA_PROVIDER_NAME)
    public void shouldTranscriptReturn(Dna dna, Rna[] controlRnaArray) {
        Rna[] transcriptRnas = dna.transcript();
        assertThat(transcriptRnas, is((equalTo(controlRnaArray))));
    }
}

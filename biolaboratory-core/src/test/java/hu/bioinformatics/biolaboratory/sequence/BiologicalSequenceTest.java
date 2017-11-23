package hu.bioinformatics.biolaboratory.sequence;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.guice.GuiceTestModule;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.datastructures.CountableOccurrenceMap;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.fail;

/**
 * Unit tests for the {@link BiologicalSequence} class.
 *
 * @author Attila Radi
 */
@Guice(modules = {GuiceCoreModule.class, GuiceResourceModule.class, GuiceTestModule.class})
@Test(dataProviderClass = BiologicalSequenceTestDataProvider.class)
public class BiologicalSequenceTest {

    @Test(dataProvider = BiologicalSequenceTestDataProvider.COPY_DATA_PROVIDER_NAME)
    public void shouldCreateCopy(BiologicalSequence biologicalSequence) {
        BiologicalSequence biologicalSequenceCopy = biologicalSequence.copy();
        assertThat(biologicalSequenceCopy, allOf(
                is(not(sameInstance(biologicalSequence))),
                is(equalTo(biologicalSequence))
        ));
        assertThat(biologicalSequenceCopy.hashCode(), is(equalTo(biologicalSequence.hashCode())));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_CHANGE_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldChangeNameThrowException(BiologicalSequence biologicalSequence, String name) {
        biologicalSequence.changeName(name);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_CHANGE_NAME_DATA_PROVIDER_NAME)
    public void shouldChangeNameReturn(BiologicalSequence biologicalSequence, String name, String controlName) {
        BiologicalSequence modifiedDna = biologicalSequence.changeName(name);
        assertThat(modifiedDna.getName(), allOf(
                is(equalTo(controlName)),
                is(not(equalTo(biologicalSequence.getName())))));
        assertThat(modifiedDna, allOf(
                is(equalTo(biologicalSequence)),
                is(not(sameInstance(biologicalSequence)))));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_GET_ELEMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetElementThrowException(BiologicalSequence biologicalSequence, int index) {
        biologicalSequence.getElement(index);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_GET_ELEMENT_DATA_PROVIDER_NAME)
    public void shouldGetElementReturn(BiologicalSequence biologicalSequence, int index, SequenceElement controlElement) {
        SequenceElement element = biologicalSequence.getElement(index);
        assertThat(element, is(equalTo(controlElement)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.GET_SEQUENCE_AS_ELEMENTS_DATA_PROVIDER_NAME)
    public void shouldGetSequenceAsElementsReturn(BiologicalSequence biologicalSequence, SequenceElement[] controlElements) {
        SequenceElement[] elements = biologicalSequence.getSequenceAsElements();
        assertThat(elements, is(equalTo(controlElements)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.EQUALS_DATA_PROVIDER_NAME)
    public void shouldEqualsReturn(BiologicalSequence biologicalSequence, Object rightHand, boolean isEquals) {
        boolean equalsResult = biologicalSequence.equals(rightHand);
        assertThat(equalsResult, is(equalTo(isEquals)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.HASH_DATA_PROVIDER_NAME)
    public void shouldHashCodeReturn(BiologicalSequence biologicalSequence, Object controlHashValue, boolean isEquals) {
        boolean hashCodeCompareResult = biologicalSequence.hashCode() == controlHashValue.hashCode();
        assertThat(hashCodeCompareResult, is(equalTo(isEquals)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.GET_ELEMENT_OCCURRENCES_DATA_PROVIDER_NAME)
    public void shouldGetElementOccurrencesReturn(BiologicalSequence biologicalSequence, CountableOccurrenceMap<SequenceElement> controlOccurrenceMap) {
        CountableOccurrenceMap<SequenceElement> elementOccurrences = biologicalSequence.getElementOccurrences();
        assertThat(elementOccurrences, is(equalTo(controlOccurrenceMap)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_GET_ELEMENT_NUMBER_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetElementNumberThrowException(BiologicalSequence biologicalSequence, SequenceElement sequenceElement) {
        biologicalSequence.getElementNumber(sequenceElement);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_GET_ELEMENT_NUMBER_DATA_PROVIDER_NAME)
    public void shouldGetElementNumberReturn(BiologicalSequence biologicalSequence, SequenceElement sequenceElement, int controlNumber) {
        int elementsNumber = biologicalSequence.getElementNumber(sequenceElement);
        assertThat(elementsNumber, is(equalTo(controlNumber)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_GET_ELEMENTS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetElementsNumberThrowException(BiologicalSequence biologicalSequence, SequenceElement[] sequenceElements) {
        biologicalSequence.getElementsNumber(sequenceElements);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_GET_ELEMENTS_NUMBER_DATA_PROVIDER_NAME)
    public void shouldGetElementsNumberReturn(BiologicalSequence biologicalSequence, SequenceElement[] sequenceElements, int controlNumber) {
        int elementsNumber = biologicalSequence.getElementsNumber(sequenceElements);
        assertThat(elementsNumber, is(equalTo(controlNumber)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_GET_ELEMENTS_ABOUT_SET_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetElementNumberAboutSetThrowException(BiologicalSequence biologicalSequence, Set<SequenceElement> sequenceElementSet) {
        biologicalSequence.getElementsNumber(sequenceElementSet);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_GET_ELEMENTS_NUMBER_ABOUT_SET_DATA_PROVIDER_NAME)
    public void shouldGetElementsNumberAboutSetReturn(BiologicalSequence biologicalSequence, Set<SequenceElement> sequenceElementSet, int controlNumber) {
        int elementsNumber = biologicalSequence.getElementsNumber(sequenceElementSet);
        assertThat(elementsNumber, is(equalTo(controlNumber)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_GET_ELEMENT_NUMBER_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetElementRatioThrowException(BiologicalSequence biologicalSequence, SequenceElement sequenceElement) {
        biologicalSequence.getElementRatio(sequenceElement);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_GET_RATIO_DATA_PROVIDER_NAME)
    public void shouldGetElementRatioReturn(BiologicalSequence biologicalSequence, SequenceElement sequenceElement, double controlRatio) {
        double elementRatio = biologicalSequence.getElementRatio(sequenceElement);
        assertThat(elementRatio, is(closeTo(controlRatio, 0.001)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_GET_ELEMENTS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetElementsRatioThrowException(BiologicalSequence biologicalSequence, SequenceElement[] sequenceElements) {
        biologicalSequence.getElementsRatio(sequenceElements);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_GET_RATIOS_DATA_PROVIDER_NAME)
    public void shouldGetElementsRatioReturn(BiologicalSequence biologicalSequence, SequenceElement[] sequenceElements, double controlRatio) {
        double elementRatio = biologicalSequence.getElementsRatio(sequenceElements);
        assertThat(elementRatio, is(closeTo(controlRatio, 0.001)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_GET_ELEMENTS_ABOUT_SET_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetElementRatioAboutSetThrowException(BiologicalSequence biologicalSequence, Set<SequenceElement> sequenceElementSet) {
        biologicalSequence.getElementsNumber(sequenceElementSet);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_GET_RATIOS_ABOUT_SET_DATA_PROVIDER_NAME)
    public void shouldGetElementsRatioAboutSetReturn(BiologicalSequence biologicalSequence, Set<SequenceElement> sequenceElementSet, double controlRatio) {
        double elementRatio = biologicalSequence.getElementsRatio(sequenceElementSet);
        assertThat(elementRatio, is(closeTo(controlRatio, 0.001)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_APPEND_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldAppendThrowException(BiologicalSequence biologicalSequence, BiologicalSequence otherBiologicalSequence) {
        biologicalSequence.append(otherBiologicalSequence);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_APPEND_ELEMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldAppendElementThrowElement(BiologicalSequence biologicalSequence, SequenceElement sequenceElement) {
        biologicalSequence.append(sequenceElement);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_APPEND_DATA_PROVIDER_NAME)
    public void shouldAppendReturn(BiologicalSequence biologicalSequence, BiologicalSequence otherBiologicalSequence, BiologicalSequence controlBiologicalSequence) {
        BiologicalSequence appendedSequence = biologicalSequence.append(otherBiologicalSequence);
        assertThat(appendedSequence, is(equalTo(controlBiologicalSequence)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_APPEND_ELEMENT_DATA_PROVIDER_NAME)
    public void shouldAppendElementReturn(BiologicalSequence biologicalSequence, SequenceElement sequenceElement, BiologicalSequence controlBiologicalSequence) {
        BiologicalSequence appendedSequence = biologicalSequence.append(sequenceElement);
        assertThat(appendedSequence, is(equalTo(controlBiologicalSequence)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_CUT_PARTS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCutPartThrowException(BiologicalSequence biologicalSequence, int startPosition, int endPosition) {
        biologicalSequence.cut(startPosition, endPosition);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_CUT_PARTS_DATA_PROVIDER_NAME)
    public void shouldCutPartReturn(BiologicalSequence BiologicalSequence, int startPosition, int endPosition, BiologicalSequence controlBiologicalSequence) {
        BiologicalSequence biologicalSequencePart = BiologicalSequence.cut(startPosition, endPosition);
        assertThat(biologicalSequencePart, is(equalTo(controlBiologicalSequence)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_CUT_TO_END_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCutToTheEndThrowException(BiologicalSequence biologicalSequence, int startPosition) {
        biologicalSequence.cut(startPosition);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_CUT_TO_END_DATA_PROVIDER_NAME)
    public void shouldCutToTheEndReturn(BiologicalSequence biologicalSequence, int startPosition, BiologicalSequence controlBiologicalSequence) {
        BiologicalSequence biologicalSequencePart = biologicalSequence.cut(startPosition);
        assertThat(biologicalSequencePart, is(equalTo(controlBiologicalSequence)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldPatternMatchingThrowExceptionForBlankPattern(BiologicalSequence biologicalSequence, BiologicalSequence pattern) {
        biologicalSequence.patternMatching(pattern);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME)
    public void shouldPatternMatchingReturn(BiologicalSequence biologicalSequence, BiologicalSequence pattern, List<Integer> controlPositions) {
        List<Integer> patternPositions = biologicalSequence.patternMatching(pattern);
        assertThat(patternPositions, is(equalTo(controlPositions)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindMinimumMismatchSubSequencesNumberThrowException(BiologicalSequence biologicalSequence, BiologicalSequence pattern) {
        biologicalSequence.findMinimumMismatchSubSequenceNumber(pattern);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_FIND_MINIMUM_MISMATCH_SUB_SEQUENCES_NUMBER_DATA_PROVIDER_NAME)
    public void shouldFindMinimumMismatchSubSequencesNumberReturn(BiologicalSequence biologicalSequence, BiologicalSequence pattern, int controlMinimumNumber) {
        int minimumNumber = biologicalSequence.findMinimumMismatchSubSequenceNumber(pattern);
        assertThat(minimumNumber, is(equalTo(controlMinimumNumber)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldPatternCountThrowExceptionForBlankPattern(BiologicalSequence biologicalSequence, BiologicalSequence pattern) {
        biologicalSequence.patternCount(pattern);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME)
    public void shouldPatternCountReturn(BiologicalSequence biologicalSequence, BiologicalSequence pattern, List<Integer> controlPositions) {
        int patternNumber = biologicalSequence.patternCount(pattern);
        assertThat(patternNumber, is(equalTo(controlPositions.size())));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_PATTERN_ARGUMENTS_WITH_MISMATCHES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForPatternMatchingWithMismatches(BiologicalSequence biologicalSequence, BiologicalSequence pattern, int d) {
        biologicalSequence.patternMatchingWithMismatches(pattern, d);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_PATTERN_ARGUMENTS_WITH_MISMATCHES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForPatternCountWithMismatches(BiologicalSequence biologicalSequence, BiologicalSequence pattern, int d) {
        biologicalSequence.patternCountWithMismatches(pattern, d);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_PATTERN_ARGUMENTS_WITH_MISMATCHES_DATA_PROVIDER_NAME)
    public void shouldPatternCountWithMismatchesReturn(BiologicalSequence biologicalSequence, BiologicalSequence pattern, int d, List<Integer> controlList) {
        int mismatchPatternNumber = biologicalSequence.patternCountWithMismatches(pattern, d);
        assertThat(mismatchPatternNumber, is(equalTo(controlList.size())));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_GET_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetSubSequenceThrowException(BiologicalSequence biologicalSequence, int k) {
        biologicalSequence.getSubSequences(k);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_GET_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldGetSubSequencesReturn(BiologicalSequence biologicalSequence, int k, Set<BiologicalSequence> controlSet) {
        Set<BiologicalSequence> subSequences = biologicalSequence.getSubSequences(k);
        assertThat(subSequences, is(equalTo(controlSet)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_GET_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGetMismatchSubSequencesThrowException(BiologicalSequence biologicalSequence, int k, int d) {
        biologicalSequence.getMismatchSubSequences(k, d);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_GET_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldGetMismatchSubSequencesReturn(BiologicalSequence biologicalSequence, int k, int d, Set<BiologicalSequence> controlSet) {
        Set<BiologicalSequence> mismatchSubSequences = biologicalSequence.getMismatchSubSequences(k, d);
        assertThat(mismatchSubSequences, is(equalTo(controlSet)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_GET_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindMostFrequentSubSequencesThrowException(BiologicalSequence biologicalSequence, int k) {
        biologicalSequence.findMostFrequentSubSequences(k);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_FIND_MOST_FREQUENT_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldFindMostFrequentSubSequencesReturn(BiologicalSequence biologicalSequence, int k, Set<Dna> controlSet) {
        Set<BiologicalSequence> mostFrequentSubSequences = biologicalSequence.findMostFrequentSubSequences(k);
        assertThat(mostFrequentSubSequences, is(equalTo(controlSet)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_GET_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindMostFrequentMismatchSubSequencesThrowException(BiologicalSequence biologicalSequence, int k, int d) {
        biologicalSequence.findMostFrequentMismatchSubSequences(k, d);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_FIND_MOST_FREQUENT_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldFindMostFrequentMismatchSubSequencesReturn(BiologicalSequence biologicalSequence, int k, int d, Set<BiologicalSequence> controlFrequentSubSequences) {
        Set<BiologicalSequence> frequentSubSequences = biologicalSequence.findMostFrequentMismatchSubSequences(k, d);
        assertThat(frequentSubSequences, is(equalTo(controlFrequentSubSequences)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_FIND_FREQUENT_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindFrequentSubSequencesThrowException(BiologicalSequence biologicalSequence, int k, int t) {
        biologicalSequence.findFrequentSubSequences(k, t);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_FIND_FREQUENT_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldFindFrequentSubSequencesReturn(BiologicalSequence biologicalSequence, int k, int t, Set<BiologicalSequence> controlSet) {
        Set<BiologicalSequence> frequentSubSequences = biologicalSequence.findFrequentSubSequences(k, t);
        assertThat(frequentSubSequences, is(equalTo(controlSet)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_FIND_FREQUENT_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindFrequentMismatchSubSequencesThrowException(BiologicalSequence biologicalSequence, int k, int d, int t) {
        biologicalSequence.findFrequentMismatchSubSequences(k, d, t);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_FIND_FREQUENT_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    public void shouldFindFrequentMismatchSubSequencesReturn(BiologicalSequence biologicalSequence, int k, int d, int t, Set<BiologicalSequence> controlFrequentSubSequences) {
        Set<BiologicalSequence> frequentSubSequences = biologicalSequence.findFrequentMismatchSubSequences(k, d, t);
        assertThat(frequentSubSequences, is(equalTo(controlFrequentSubSequences)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_SUBSEQUENCES_IN_CLUMPS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindPatternsInClumpsThrowException(BiologicalSequence biologicalSequence, int k, int L, int t) {
        biologicalSequence.findPatternsInClumps(k, L, t);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_SUBSEQUENCES_IN_CLUMPS_DATA_PROVIDER_NAME)
    public void shouldFindPatternsInClumpsReturn(BiologicalSequence biologicalSequence, int k,int L, int t, Set<BiologicalSequence> controlFrequentSubSequences) {
        Set<BiologicalSequence> patternsInClumps = biologicalSequence.findPatternsInClumps(k, L, t);
        assertThat(patternsInClumps, is(equalTo(controlFrequentSubSequences)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_MISMATCH_NUMBERS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGenerateMismatchesThrowException(BiologicalSequence biologicalSequence, int d) {
        biologicalSequence.generateMismatches(d);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_GENERATE_MISMATCHES_DATA_PROVIDER_NAME)
    public void shouldGenerateMismatchesReturn(BiologicalSequence biologicalSequence, int d, Set<BiologicalSequence> controlMismatchSet) {
        Set<BiologicalSequence> generatedMismatchSet = biologicalSequence.generateMismatches(d);
        assertThat(generatedMismatchSet, is(equalTo(controlMismatchSet)));
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.INVALID_HAMMING_DISTANCE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForGetMismatchNumber(BiologicalSequence biologicalSequence1, BiologicalSequence biologicalSequence2) {
        biologicalSequence1.getMismatchNumber(biologicalSequence2);
        fail();
    }

    @Test(dataProvider = BiologicalSequenceTestDataProvider.VALID_HAMMING_DISTANCE_DATA_PROVIDER_NAME)
    public void shouldGetMismatchNumberReturn(BiologicalSequence biologicalSequence1, BiologicalSequence biologicalSequence2, int controlMismatchNumber) {
        int mismatchNumber = biologicalSequence1.getMismatchNumber(biologicalSequence2);
        assertThat(mismatchNumber, is(equalTo(controlMismatchNumber)));
    }
}
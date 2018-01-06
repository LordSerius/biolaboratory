package hu.bioinformatics.biolaboratory.sequence.dna;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.guice.GuiceTestModule;
import hu.bioinformatics.biolaboratory.sequence.BiologicalSequence;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.testng.Assert.fail;

/**
 * Test cases for the {@link DnaArray} class.
 *
 * @author Attila Radi
 */
@Guice(modules = {GuiceCoreModule.class, GuiceResourceModule.class, GuiceTestModule.class})
@Test(dataProviderClass = DnaArrayTestDataProvider.class)
public class DnaArrayTest {

    @Test(dataProvider = DnaArrayTestDataProvider.INVALID_DNA_ARRAY_LIST_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildThrowException(List<Dna> dnaList) {
        DnaArray.build(dnaList);
    }

    @Test(dataProvider = DnaArrayTestDataProvider.VALID_DNA_ARRAY_LIST_DATA_PROVIDER_NAME)
    public void shouldBuildListReturn(List<Dna> dnaList, List<String> controlStringList) {
        DnaArray dnaArray = DnaArray.build(dnaList);
        List<String> sequenceList = dnaArray.getSampleList().stream().map(BiologicalSequence::getSequence).collect(Collectors.toList());
        assertThat(dnaArray.getSampleNumber(), is(equalTo(controlStringList.size())));
        assertThat(dnaArray.getSamplesLength(), is(equalTo(controlStringList.get(0).length())));
        assertThat(sequenceList, is(equalTo(controlStringList)));
    }

    @Test(dataProvider = DnaArrayTestDataProvider.VALID_DNA_ARRAY_LIST_DATA_PROVIDER_NAME)
    public void shouldBuildVarargsReturn(List<Dna> dnaList, List<String> controlStringList) {
        Dna[] dnas = new Dna[dnaList.size()];
        dnaList.toArray(dnas);
        DnaArray dnaArray = DnaArray.build(dnas);
        List<String> sequenceList = dnaArray.getSampleList().stream().map(BiologicalSequence::getSequence).collect(Collectors.toList());
        assertThat(dnaArray.getSampleNumber(), is(equalTo(controlStringList.size())));
        assertThat(dnaArray.getSamplesLength(), is(equalTo(controlStringList.get(0).length())));
        assertThat(sequenceList, is(equalTo(controlStringList)));
    }

    @Test(dataProvider = DnaArrayTestDataProvider.DNA_ARRAY_COPY_DATA_PROVIDER_NAME)
    public void shouldCreateCopy(DnaArray dnaArray) {
        DnaArray dnaArrayCopy = dnaArray.copy();
        assertThat(dnaArrayCopy, allOf(
                is(not(sameInstance(dnaArray))),
                is(equalTo(dnaArray))
        ));
    }

    @Test(dataProvider = DnaArrayTestDataProvider.DNA_ARRAY_EQUALS_DATA_PROVIDER_NAME)
    public void shouldEqualsReturn(DnaArray dnaArray, Object rightHand, boolean isEquals) {
        boolean equalsResult = dnaArray.equals(rightHand);
        assertThat(equalsResult, is(equalTo(isEquals)));
    }

    @Test(dataProvider = DnaArrayTestDataProvider.INVALID_ADD_ELEMENTS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldAddElementsThrowException(DnaArray dnaArray, Dna[] dnas) {
        dnaArray.add(dnas);
    }

    @Test(dataProvider = DnaArrayTestDataProvider.VALID_ADD_ELEMENTS_DATA_PROVIDER_NAME)
    public void shouldAddElementsReturn(DnaArray dnaArray, Dna[] dnas, DnaArray controlDnaArray) {
        DnaArray addedDnaArray = dnaArray.add(dnas);
        assertThat(addedDnaArray, is(equalTo(controlDnaArray)));
    }

    @Test(dataProvider = DnaArrayTestDataProvider.INVALID_ADD_LIST_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldAddListThrowException(DnaArray dnaArray, List<Dna> dnaList) {
        dnaArray.add(dnaList);
    }

    @Test(dataProvider = DnaArrayTestDataProvider.VALID_ADD_LIST_DATA_PROVIDER_NAME)
    public void shouldAddListReturn(DnaArray dnaArray, List<Dna> dnaList, DnaArray controlDnaArray) {
        DnaArray addedDnaList = dnaArray.add(dnaList);
        assertThat(addedDnaList, is(equalTo(controlDnaArray)));
    }

    @Test(dataProvider = DnaArrayTestDataProvider.INVALID_ADD_DNA_ARRAY_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldAddDnaArrayThrowException(DnaArray dnaArray, DnaArray otherDnaArray) {
        dnaArray.add(otherDnaArray);
    }

    @Test(dataProvider = DnaArrayTestDataProvider.VALID_ADD_DNA_ARRAY_DATA_PROVIDER_NAME)
    public void shouldAddDnaArrayReturn(DnaArray dnaArray, DnaArray otherDnaArray, DnaArray controlDnaArray) {
        DnaArray addedDnaList = dnaArray.add(otherDnaArray);
        assertThat(addedDnaList, is(equalTo(controlDnaArray)));
    }

    @Test(dataProvider = DnaArrayTestDataProvider.INVALID_FIND_MOST_FREQUENT_MOTIFS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindMostFrequentMotifsExhaustingThrowException(DnaArray dnaArray, int k, int d) {
        dnaArray.findMostFrequentMotifsExhausting(k, d);
    }

    @Test(dataProvider = DnaArrayTestDataProvider.VALID_FIND_MOST_FREQUENT_MOTIFS_EXHAUSTING_DATA_PROVIDER_NAME)
    public void shouldFindMostFrequentMotifsExhaustingReturn(DnaArray dnaArray, int k, int d, Set<Dna> controlSet) {
        Set<Dna> mostFrequentMotifs = dnaArray.findMostFrequentMotifsExhausting(k, d);
        assertThat(mostFrequentMotifs, is(equalTo(controlSet)));
    }

    @Test(dataProvider = DnaArrayTestDataProvider.INVALID_FIND_MOST_FREQUENT_MOTIFS_MEDIAN_STRING_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindMostFrequentMotifsMedianStringThrowException(DnaArray dnaArray, int k) {
        dnaArray.findMostFrequentMotifsMedianString(k);
    }

    @Test(dataProvider = DnaArrayTestDataProvider.VALID_FIND_MOST_FREQUENT_MOTIFS_MEDIAN_STRING_DATA_PROVIDER_NAME)
    public void shouldFindMostFrequentMotifsMedianStringReturn(DnaArray dnaArray, int k, Set<Dna> controlSet) {
        Set<Dna> mostFrequentMotifs = dnaArray.findMostFrequentMotifsMedianString(k);
        assertThat(mostFrequentMotifs, is(equalTo(controlSet)));
    }

    @Test(dataProvider = DnaArrayTestDataProvider.INVALID_PROFILE_MOST_PROBABLE_SUB_SEQUENCE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldProfileMostProbableSubSequenceThrowException(DnaArray dnaArray, Dna dna) {
        dnaArray.profileMostProbableSubSequence(dna);
    }

    @Test(dataProvider = DnaArrayTestDataProvider.VALID_PROFILE_MOST_PROBABLE_SUB_SEQUENCE_DATA_PROVIDER_NAME)
    public void shouldProfileMostProbableSubSequenceReturn(DnaArray dnaArray, Dna dna, Set<Dna> controlSubSet) {
        Set<Dna> probablePatternSet = dnaArray.profileMostProbableSubSequence(dna);
        assertThat(probablePatternSet, is(equalTo(controlSubSet)));
    }

    @Test(dataProvider = DnaArrayTestDataProvider.INVALID_PATTERN_PROBABILITY_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldPatternProbabilityThrowException(DnaArray dnaArray, Dna pattern) {
        dnaArray.patternProbability(pattern);
    }

    @Test(dataProvider = DnaArrayTestDataProvider.VALID_PATTERN_PROBABILITY_DATA_PROVIDER_NAME)
    public void shouldPatternProbabilityReturn(DnaArray dnaArray, Dna pattern, double controlProbability) {
        double probability = dnaArray.patternProbability(pattern);
        assertThat(probability, is(closeTo(controlProbability, 0.0000001)));
    }
}
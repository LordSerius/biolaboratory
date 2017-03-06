package hu.bioinformatics.biolaboratory.sequence.dna;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.guice.GuiceTestModule;
import hu.bioinformatics.biolaboratory.sequence.BiologicalSequence;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.fail;

/**
 * Test cases for the {@link DnaArray} class.
 *
 * @author Attila Radi
 */
@Guice(modules = {GuiceCoreModule.class, GuiceTestModule.class})
@Test(dataProviderClass = DnaArrayTestDataProvider.class)
public class DnaArrayTest {

    @Test(dataProvider = DnaArrayTestDataProvider.INVALID_DNA_ARRAY_LIST_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldBuildThrowException(List<Dna> dnaList) {
        DnaArray.build(dnaList);
        fail();
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

    @Test(dataProvider = DnaArrayTestDataProvider.DNA_ARRAY_EQUALS_DATA_PROVDER_NAME)
    public void shouldEqualsReturn(DnaArray dnaArray, Object rightHand, boolean isEquals) {
        boolean equalsResult = dnaArray.equals(rightHand);
        assertThat(equalsResult, is(equalTo(isEquals)));
    }

    @Test(dataProvider = DnaArrayTestDataProvider.INVALID_FIND_MOST_FREQUENT_MOTIFS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFindMostFrequentMotifsThrowException(DnaArray dnaArray, int k, int d) {
        dnaArray.findMostFrequentMotifs(k, d);
        fail();
    }

    @Test(dataProvider = DnaArrayTestDataProvider.VALID_FIND_MOST_FREQUENT_MOTIFS_DATA_PROVIDER_NAME)
    public void shouldFindMostFrequentMotifsReturn(DnaArray dnaArray, int k, int d, Set<Dna> controlSet) {
        Set<Dna> mostFrequentMotifs = dnaArray.findMostFrequentMotifs(k, d);
        assertThat(mostFrequentMotifs, is(equalTo(controlSet)));
    }
}
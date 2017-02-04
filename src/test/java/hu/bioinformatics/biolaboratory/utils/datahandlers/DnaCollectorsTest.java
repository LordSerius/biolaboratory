package hu.bioinformatics.biolaboratory.utils.datahandlers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import hu.bioinformatics.biolaboratory.utils.DnaCollectors;

/**
 * Test cases for {@link DnaCollectors} class.
 *
 * @author Attila Radi
 */
public class DnaCollectorsTest {
    
    private static final String INVALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME = "invalidSequenceCollectionDataProvider";

    @DataProvider(name = INVALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    private Object[][] invalidSequenceCollectionDataProvider() {
        return new Object[][] {
            { null },
            { Lists.newArrayList("A", "invalid item", "G") }
        };
    }
    
    private static final String VALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME = "validSequenceCollectionDataProvider";
    
    @DataProvider(name = VALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    private Object[][] validSequenceCollectionDataProvider() {
        return new Object[][] {
            { Lists.newArrayList() },
            { Lists.newArrayList("A") },
            { Lists.newArrayList("A", "G", "C", "T") }
        };
    }

    @Test(dataProvider = INVALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldArrayToDnaListThrowException(List<String> invalidSequenceList) {
        String sequences[] = null;
        if (invalidSequenceList != null) {
            sequences = new String[invalidSequenceList.size()];
            invalidSequenceList.toArray(sequences);
        }
        DnaCollectors.toDnaList(sequences);
        fail();
    }

    @Test(dataProvider = INVALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCollectionToDnaListThrowException(List<String> invalidSequenceList) {
        DnaCollectors.toDnaList(invalidSequenceList);
        fail();
    }

    @Test(dataProvider = VALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    public void arrayToDnaList(List<String> sequenceList) {
        String sequences[] = new String[sequenceList.size()];
        sequences = sequenceList.toArray(sequences);
        List<Dna> dnaList = DnaCollectors.toDnaList(sequences);
        List<Dna> controlList = sequenceList
                .stream()
                .map(sequence -> Dna.build(sequence))
                .collect(Collectors.toList());
        assertThat(dnaList, is(equalTo(controlList)));
    }
    
    @Test(dataProvider = VALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    public void collectionToDnaList(List<String> sequenceList) {
        List<Dna> dnaList = DnaCollectors.toDnaList(sequenceList);
        List<Dna> controlList = sequenceList
                .stream()
                .map(sequence -> Dna.build(sequence))
                .collect(Collectors.toList());
        assertThat(dnaList, is(equalTo(controlList)));
    }

    @Test(dataProvider = INVALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldArrayToDnaSetThrowException(List<String> invalidSequenceList) {
        String sequences[] = null;
        if (invalidSequenceList != null) {
            sequences = new String[invalidSequenceList.size()];
            invalidSequenceList.toArray(sequences);
        }
        DnaCollectors.toDnaSet(sequences);
        fail();
    }

    @Test(dataProvider = INVALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldToDnaSetThrowException(List<String> invalidSequenceList) {
        DnaCollectors.toDnaSet(invalidSequenceList);
        fail();
    }

    @Test(dataProvider = VALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    public void arrayToDnaSet(List<String> sequenceList) {
        String sequences[] = new String[sequenceList.size()];
        sequences = sequenceList.toArray(sequences);
        Set<Dna> dnaSet = DnaCollectors.toDnaSet(sequences);
        Set<Dna> controlSet = sequenceList
                .stream()
                .map(sequence -> Dna.build(sequence))
                .collect(Collectors.toSet());
        assertThat(dnaSet, is(equalTo(controlSet)));
    }

    @Test(dataProvider = VALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    public void collectionToDnaSet(List<String> sequenceList) {
        Set<Dna> dnaSet = DnaCollectors.toDnaSet(sequenceList);
        Set<Dna> controlSet = sequenceList
                .stream()
                .map(sequence -> Dna.build(sequence))
                .collect(Collectors.toSet());
        assertThat(dnaSet, is(equalTo(controlSet)));
    }
}

package hu.bioinformatics.biolaboratory.utils.collectors;

import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.CommentedString;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

/**
 * Test cases for {@link DnaCollectors} class.
 *
 * @author Attila Radi
 */
@Test(dataProviderClass = DnaCollectorsTestDataProvider.class)
public class DnaCollectorsTest {

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldStringArrayToDnaListThrowException(List<String> invalidSequenceList) {
        String sequences[] = null;
        if (invalidSequenceList != null) {
            sequences = new String[invalidSequenceList.size()];
            invalidSequenceList.toArray(sequences);
        }
        DnaCollectors.stringToDnaList(sequences);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldStringCollectionToDnaListThrowException(List<String> invalidSequenceList) {
        DnaCollectors.stringToDnaList(invalidSequenceList);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldStringArrayToDnaListReturn(List<String> sequenceList) {
        String sequences[] = new String[sequenceList.size()];
        sequences = sequenceList.toArray(sequences);
        List<Dna> dnaList = DnaCollectors.stringToDnaList(sequences);
        List<Dna> controlList = sequenceList
                .stream()
                .map(Dna::build)
                .collect(Collectors.toList());
        assertThat(dnaList, is(equalTo(controlList)));
    }
    
    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldStringCollectionToDnaListReturn(List<String> sequenceList) {
        List<Dna> dnaList = DnaCollectors.stringToDnaList(sequenceList);
        List<Dna> controlList = sequenceList
                .stream()
                .map(Dna::build)
                .collect(Collectors.toList());
        assertThat(dnaList, is(equalTo(controlList)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldStringArrayToDnaSetThrowException(List<String> invalidSequenceList) {
        String sequences[] = null;
        if (invalidSequenceList != null) {
            sequences = new String[invalidSequenceList.size()];
            invalidSequenceList.toArray(sequences);
        }
        DnaCollectors.stringToDnaSet(sequences);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldStringCollectionToDnaSetThrowException(List<String> invalidSequenceList) {
        DnaCollectors.stringToDnaSet(invalidSequenceList);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldStringArrayToDnaSetReturn(List<String> sequenceList) {
        String sequences[] = new String[sequenceList.size()];
        sequences = sequenceList.toArray(sequences);
        Set<Dna> dnaSet = DnaCollectors.stringToDnaSet(sequences);
        Set<Dna> controlSet = sequenceList
                .stream()
                .map(Dna::build)
                .collect(Collectors.toSet());
        assertThat(dnaSet, is(equalTo(controlSet)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldStringCollectionToDnaSetReturn(List<String> sequenceList) {
        Set<Dna> dnaSet = DnaCollectors.stringToDnaSet(sequenceList);
        Set<Dna> controlSet = sequenceList
                .stream()
                .map(Dna::build)
                .collect(Collectors.toSet());
        assertThat(dnaSet, is(equalTo(controlSet)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_COMMENTED_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCommentedStringArrayToDnaListThrowException(List<CommentedString> invalidSequenceList) {
        CommentedString sequences[] = null;
        if (invalidSequenceList != null) {
            sequences = new CommentedString[invalidSequenceList.size()];
            invalidSequenceList.toArray(sequences);
        }
        DnaCollectors.commentedStringToDnaList(sequences);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_COMMENTED_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCommentedStringCollectionToDnaListThrowException(List<CommentedString> invalidSequenceList) {
        DnaCollectors.commentedStringToDnaList(invalidSequenceList);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_COMMENTED_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldCommentedStringArrayToDnaListReturn(List<CommentedString> sequenceList) {
        CommentedString sequences[] = new CommentedString[sequenceList.size()];
        sequences = sequenceList.toArray(sequences);
        List<Dna> dnaList = DnaCollectors.commentedStringToDnaList(sequences);
        List<Dna> controlList = sequenceList
                .stream()
                .map(commentedString -> Dna.build(commentedString.getComment(), commentedString.getString()))
                .collect(Collectors.toList());
        assertThat(dnaList, is(equalTo(controlList)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_COMMENTED_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldCommentedStringCollectionToDnaListReturn(List<CommentedString> sequenceList) {
        List<Dna> dnaList = DnaCollectors.commentedStringToDnaList(sequenceList);
        List<Dna> controlList = sequenceList
                .stream()
                .map(commentedString -> Dna.build(commentedString.getComment(), commentedString.getString()))
                .collect(Collectors.toList());
        assertThat(dnaList, is(equalTo(controlList)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_COMMENTED_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCommentedStringArrayToDnaSetThrowException(List<CommentedString> invalidSequenceList) {
        CommentedString sequences[] = null;
        if (invalidSequenceList != null) {
            sequences = new CommentedString[invalidSequenceList.size()];
            invalidSequenceList.toArray(sequences);
        }
        DnaCollectors.commentedStringToDnaSet(sequences);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_COMMENTED_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCommentedStringCollectionToDnaSetThrowException(List<CommentedString> invalidSequenceList) {
        DnaCollectors.commentedStringToDnaSet(invalidSequenceList);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_COMMENTED_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldCommentedStringArrayToDnaSetReturn(List<CommentedString> sequenceList) {
        CommentedString sequences[] = new CommentedString[sequenceList.size()];
        sequences = sequenceList.toArray(sequences);
        Set<Dna> dnaSet = DnaCollectors.commentedStringToDnaSet(sequences);
        Set<Dna> controlSet = sequenceList
                .stream()
                .map(commentedString -> Dna.build(commentedString.getComment(), commentedString.getString()))
                .collect(Collectors.toSet());
        assertThat(dnaSet, is(equalTo(controlSet)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_COMMENTED_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldCommentedStringCollectionToDnaSetReturn(List<CommentedString> sequenceList) {
        Set<Dna> dnaSet = DnaCollectors.commentedStringToDnaSet(sequenceList);
        Set<Dna> controlSet = sequenceList
                .stream()
                .map(commentedString -> Dna.build(commentedString.getComment(), commentedString.getString()))
                .collect(Collectors.toSet());
        assertThat(dnaSet, is(equalTo(controlSet)));
    }
}

package hu.bioinformatics.biolaboratory.utils.collectors;

import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.CommentedString;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_STRING_ARRAY_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldStringArrayToDnaListThrowException(String[] stringArray) {
        DnaCollectors.stringToDnaList(stringArray);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_STRING_ARRAY_TO_DNA_LIST_DATA_PROVIDER_NAME)
    public void shouldStringArrayToDnaListReturn(String[] stringArray, List<Dna> controlList) {
        List<Dna> dnaList = DnaCollectors.stringToDnaList(stringArray);
        assertThat(dnaList, is(equalTo(controlList)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_COMMENTED_STRING_ARRAY_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCommentedStringArrayToDnaListThrowException(CommentedString[] commentedStringArray) {
        DnaCollectors.commentedStringToDnaList(commentedStringArray);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_COMMENTED_STRING_ARRAY_TO_DNA_LIST_DATA_PROVIDER_NAME)
    public void shouldCommentedStringArrayToDnaListReturn(CommentedString[] commentedStringArray, List<Dna> controlList) {
        List<Dna> dnaList = DnaCollectors.commentedStringToDnaList(commentedStringArray);
        assertThat(dnaList, is(equalTo(controlList)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_STRING_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldStringCollectionToDnaListThrowException(Collection<String> stringCollection) {
        DnaCollectors.stringToDnaList(stringCollection);
        fail();
    }
    
    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_STRING_COLLECTION_TO_DNA_LIST_DATA_PROVIDER_NAME)
    public void shouldStringCollectionToDnaListReturn(Collection<String> stringCollection, List<Dna> controlList) {
        List<Dna> dnaList = DnaCollectors.stringToDnaList(stringCollection);
        assertThat(dnaList, is(equalTo(controlList)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_COMMENTED_STRING_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCommentedStringCollectionToDnaListThrowException(Collection<CommentedString> commentedStringCollection) {
        DnaCollectors.commentedStringToDnaList(commentedStringCollection);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_COMMENTED_STRING_COLLECTION_TO_DNA_LIST_DATA_PROVIDER_NAME)
    public void shouldCommentedStringCollectionToDnaListReturn(Collection<CommentedString> commentedStringCollection, List<Dna> controlList) {
        List<Dna> dnaList = DnaCollectors.commentedStringToDnaList(commentedStringCollection);
        assertThat(dnaList, is(equalTo(controlList)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_STRING_ARRAY_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldStringArrayToDnaSetThrowException(String[] stringArray) {
        DnaCollectors.stringToDnaSet(stringArray);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_STRING_ARRAY_TO_DNA_SET_DATA_PROVIDER_NAME)
    public void shouldStringArrayToDnaSetReturn(String[] stringArray, Set<Dna> controlSet) {
        Set<Dna> dnaSet = DnaCollectors.stringToDnaSet(stringArray);
        assertThat(dnaSet, is(equalTo(controlSet)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_COMMENTED_STRING_ARRAY_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCommentedStringArrayToDnaSetThrowException(CommentedString[] commentedStringArray) {
        DnaCollectors.commentedStringToDnaSet(commentedStringArray);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_COMMENTED_STRING_ARRAY_TO_DNA_SET_DATA_PROVIDER_NAME)
    public void shouldCommentedStringArrayToDnaSetReturn(CommentedString[] commentedStringArray, Set<Dna> controlSet) {
        Set<Dna> dnaSet = DnaCollectors.commentedStringToDnaSet(commentedStringArray);
        assertThat(dnaSet, is(equalTo(controlSet)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_STRING_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldStringCollectionToDnaSetThrowException(Collection<String> stringCollection) {
        DnaCollectors.stringToDnaSet(stringCollection);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_STRING_COLLECTION_TO_DNA_SET_DATA_PROVIDER_NAME)
    public void shouldStringCollectionToDnaSetReturn(Collection<String> stringCollection, Set<Dna> controlSet) {
        Set<Dna> dnaSet = DnaCollectors.stringToDnaSet(stringCollection);
        assertThat(dnaSet, is(equalTo(controlSet)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_COMMENTED_STRING_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCommentedStringCollectionToDnaSetThrowException(Collection<CommentedString> comentedStringCollection) {
        DnaCollectors.commentedStringToDnaSet(comentedStringCollection);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_COMMENTED_STRING_COLLECTION_TO_DNA_SET_DATA_PROVIDER_NAME)
    public void shouldCommentedStringCollectionToDnaSetReturn(List<CommentedString> commentedStringCollection, Set<Dna> controlSet) {
        Set<Dna> dnaSet = DnaCollectors.commentedStringToDnaSet(commentedStringCollection);
        assertThat(dnaSet, is(equalTo(controlSet)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_STRING_ARRAY_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldStringArrayToDnaArrayThrowException(String[] stringArray) {
        DnaCollectors.stringToDnas(stringArray);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_STRING_ARRAY_TO_DNA_ARRAY_DATA_PROVIDER_NAME)
    public void shouldStringArrayToDnaArrayReturn(String[] stringArray, Dna[] controlArray) {
        Dna[] dnaArray = DnaCollectors.stringToDnas(stringArray);
        assertThat(dnaArray, is(equalTo(controlArray)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_COMMENTED_STRING_ARRAY_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCommentedStringArrayToDnaArrayThrowException(CommentedString[] commentedStringArray) {
        DnaCollectors.commentedStringToDnas(commentedStringArray);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_COMMENTED_STRING_ARRAY_TO_DNA_ARRAY_DATA_PROVIDER_NAME)
    public void shouldCommentedStringArrayToDnaArrayReturn(CommentedString[] commentedStringArray, Dna[] controlArray) {
        Dna[] dnaArray = DnaCollectors.commentedStringToDnas(commentedStringArray);
        assertThat(dnaArray, is(equalTo(controlArray)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_STRING_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldStringCollectionToDnaArrayThrowException(Collection<String> stringCollection) {
        DnaCollectors.stringToDnas(stringCollection);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_STRING_COLLECTION_TO_DNA_ARRAY_DATA_PROVIDER_NAME)
    public void shouldStringCollectionToDnaArrayReturn(Collection<String> stringCollection, Dna[] controlArray) {
        Dna[] dnaArray = DnaCollectors.stringToDnas(stringCollection);
        assertThat(dnaArray, is(equalTo(controlArray)));
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.INVALID_COMMENTED_STRING_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldCommentedStringCollectionToDnaArrayThrowException(Collection<CommentedString> commentedStringCollection) {
        DnaCollectors.commentedStringToDnas(commentedStringCollection);
        fail();
    }

    @Test(dataProvider = DnaCollectorsTestDataProvider.VALID_COMMENTED_STRING_COLLECTION_TO_DNA_ARRAY_DATA_PROVIDER_NAME)
    public void shouldCommentedStringCollectionToDnaArrayReturn(Collection<CommentedString> commentedStringCollection, Dna[] controlArray) {
        Dna[] dnaArray = DnaCollectors.commentedStringToDnas(commentedStringCollection);
        assertThat(dnaArray, is(equalTo(controlArray)));
    }
}

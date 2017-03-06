package hu.bioinformatics.biolaboratory.utils.collectors;

import com.google.common.collect.Lists;
import hu.bioinformatics.biolaboratory.utils.resource.CommentedString;
import org.testng.annotations.DataProvider;

/**
 * Provides test data for {@link DnaCollectorsTest}.
 *
 * @author Attila Radi
 */
public class DnaCollectorsTestDataProvider {

    static final String INVALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME = "invalidSequenceCollectionDataProvider";

    @DataProvider(name = INVALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    private Object[][] invalidSequenceCollectionDataProvider() {
        return new Object[][] {
                { null },
                { Lists.newArrayList("A", "invalid item", "G") },
                { Lists.newArrayList("A", null) }
        };
    }

    static final String VALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME = "validSequenceCollectionDataProvider";

    @DataProvider(name = VALID_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    private Object[][] validSequenceCollectionDataProvider() {
        return new Object[][] {
                { Lists.newArrayList() },
                { Lists.newArrayList("A") },
                { Lists.newArrayList("A", "G", "C", "T") }
        };
    }

    static final String INVALID_COMMENTED_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME = "invalidCommentedSequenceCollectionDataProvider";

    @DataProvider(name = INVALID_COMMENTED_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    private Object[][] invalidCommentedSequenceCollectionDataProvider() {
        return new Object[][] {
                { null },
                { Lists.newArrayList(new CommentedString("comment", "A"), new CommentedString("comment", "invalid item"), new CommentedString("comment", "G")) },
                { Lists.newArrayList(new CommentedString("comment", "A"), null) }
        };
    }

    static final String VALID_COMMENTED_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME = "validCommentedSequenceCollectionDataProvider";

    @DataProvider(name = VALID_COMMENTED_SEQUENCE_COLLECTION_DATA_PROVIDER_NAME)
    private Object[][] validCommentedSequenceCollectionDataProvider() {
        return new Object[][] {
                { Lists.newArrayList() },
                { Lists.newArrayList(new CommentedString("comment","A")) },
                { Lists.newArrayList(new CommentedString("", "A"), new CommentedString("", "G"), new CommentedString("", "C"), new CommentedString("", "T")) }
        };
    }
}

package hu.bioinformatics.biolaboratory.utils.collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.CommentedString;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Provides test data for {@link DnaCollectorsTest}.
 *
 * @author Attila Radi
 */
public class DnaCollectorsTestDataProvider {

    static final String INVALID_STRING_ARRAY_DATA_PROVIDER_NAME = "invalidStringArrayDataProvider";

    @DataProvider(name = INVALID_STRING_ARRAY_DATA_PROVIDER_NAME)
    private Object[][] invalidStringArrayDataProvider() {
        return new Object[][] {
                { null },
                { new String[] { "A", "invalid item", "G" } },
                { new String[] { "A", null } }
        };
    }

    static final String INVALID_COMMENTED_STRING_ARRAY_DATA_PROVIDER_NAME = "invalidCommentedStringArrayDataProvider";

    @DataProvider(name = INVALID_COMMENTED_STRING_ARRAY_DATA_PROVIDER_NAME)
    private Object[][] invalidCommentedStringArrayDataProvider() {
        return new Object[][] {
                { null },
                { new CommentedString[] { new CommentedString("comment", "A"), new CommentedString("comment", "invalid item"), new CommentedString("comment", "G") } },
                { new CommentedString[] { new CommentedString("comment", "A"), null } }
        };
    }

    static final String INVALID_STRING_COLLECTION_DATA_PROVIDER_NAME = "invalidStringCollectionDataProvider";

    @DataProvider(name = INVALID_STRING_COLLECTION_DATA_PROVIDER_NAME)
    private Object[][] invalidStringCollectionDataProvider() {
        return new Object[][] {
                { null },
                { Lists.newArrayList("A", "invalid item", "G") },
                { Lists.newArrayList("A", null) }
        };
    }

    static final String INVALID_COMMENTED_STRING_COLLECTION_DATA_PROVIDER_NAME = "invalidCommentedStringCollectionDataProvider";

    @DataProvider(name = INVALID_COMMENTED_STRING_COLLECTION_DATA_PROVIDER_NAME)
    private Object[][] invalidCommentedStringCollectionDataProvider() {
        return new Object[][] {
                { null },
                { Lists.newArrayList(new CommentedString("comment", "A"), new CommentedString("comment", "invalid item"), new CommentedString("comment", "G")) },
                { Lists.newArrayList(new CommentedString("comment", "A"), null) }
        };
    }

    static final String VALID_STRING_ARRAY_TO_DNA_ARRAY_DATA_PROVIDER_NAME = "validStringArrayToDnaArrayDataProvider";

    @DataProvider(name = VALID_STRING_ARRAY_TO_DNA_ARRAY_DATA_PROVIDER_NAME)
    private Object[][] validStringArrayToDnaArrayDataProvider() {
        return new Object[][] {
                { new String[] {}, new Dna[] {} },
                { new String[] { "A" }, new Dna[] { Dna.build("A") } },
                { new String[] { "A", "C", "G", "T" }, new Dna[] { Dna.build("A"), Dna.build("C"), Dna.build("G"), Dna.build("T") } }
        };
    }

    static final String VALID_STRING_COLLECTION_TO_DNA_ARRAY_DATA_PROVIDER_NAME = "validStringCollectionToDnaArrayDataProvider";

    @DataProvider(name = VALID_STRING_COLLECTION_TO_DNA_ARRAY_DATA_PROVIDER_NAME)
    private Object[][] validStringCollectionToDnaArrayDataProvider() {
        return new Object[][] {
                { new ArrayList<String>(), new Dna[] {} },
                { ImmutableList.of("A"), new Dna[] { Dna.build("A") } },
                { ImmutableList.of("A", "C", "G", "T"), new Dna[] { Dna.build("A"), Dna.build("C"), Dna.build("G"), Dna.build("T") } }
        };
    }

    static final String VALID_STRING_ARRAY_TO_DNA_LIST_DATA_PROVIDER_NAME = "validStringArrayToDnaListDataProvider";

    @DataProvider(name = VALID_STRING_ARRAY_TO_DNA_LIST_DATA_PROVIDER_NAME)
    private Object[][] validStringArrayToDnaListDataProvider() {
        return new Object[][] {
                { new String[] {}, new ArrayList<Dna>() },
                { new String[] { "A" }, ImmutableList.of(Dna.build("A")) },
                { new String[] { "A", "C", "G", "T" }, ImmutableList.of(Dna.build("A"), Dna.build("C"), Dna.build("G"), Dna.build("T")) }
        };
    }

    static final String VALID_STRING_COLLECTION_TO_DNA_LIST_DATA_PROVIDER_NAME = "validStringCollectionToDnaListDataProvider";

    @DataProvider(name = VALID_STRING_COLLECTION_TO_DNA_LIST_DATA_PROVIDER_NAME)
    private Object[][] validStringCollectionToDnaListDataProvider() {
        return new Object[][] {
                { new ArrayList<String>(), new ArrayList<Dna>() },
                { ImmutableList.of("A"), ImmutableList.of(Dna.build("A")) },
                { ImmutableList.of("A", "C", "G", "T"), ImmutableList.of(Dna.build("A"), Dna.build("C"), Dna.build("G"), Dna.build("T")) }
        };
    }

    static final String VALID_STRING_ARRAY_TO_DNA_SET_DATA_PROVIDER_NAME = "validStringArrayToDnaSetDataProvider";

    @DataProvider(name = VALID_STRING_ARRAY_TO_DNA_SET_DATA_PROVIDER_NAME)
    private Object[][] validStringArrayToDnaSetDataProvider() {
        return new Object[][] {
                { new String[] {}, new HashSet<Dna>() },
                { new String[] { "A" }, ImmutableSet.of(Dna.build("A")) },
                { new String[] { "A", "C", "G", "T" }, ImmutableSet.of(Dna.build("A"), Dna.build("C"), Dna.build("G"), Dna.build("T")) }
        };
    }

    static final String VALID_STRING_COLLECTION_TO_DNA_SET_DATA_PROVIDER_NAME = "validStringCollectionToDnaSetDataProvider";

    @DataProvider(name = VALID_STRING_COLLECTION_TO_DNA_SET_DATA_PROVIDER_NAME)
    private Object[][] validStringCollectionToDnaSetDataProvider() {
        return new Object[][] {
                { new ArrayList<String>(), new HashSet<Dna>() },
                { ImmutableList.of("A"), ImmutableSet.of(Dna.build("A")) },
                { ImmutableList.of("A", "C", "G", "T"), ImmutableSet.of(Dna.build("A"), Dna.build("C"), Dna.build("G"), Dna.build("T")) }
        };
    }

    static final String VALID_COMMENTED_STRING_ARRAY_TO_DNA_ARRAY_DATA_PROVIDER_NAME = "validCommentedStringArrayToDnaArrayDataProvider";

    @DataProvider(name = VALID_COMMENTED_STRING_ARRAY_TO_DNA_ARRAY_DATA_PROVIDER_NAME)
    private Object[][] validCommentedStringArrayToDnaArrayDataProvider() {
        return new Object[][] {
                { new CommentedString[] {}, new Dna[] {} },
                { new CommentedString[] { new CommentedString("comment", "A") }, new Dna[] { Dna.build("comment", "A") } },
                { new CommentedString[] { new CommentedString("adenine", "A"), new CommentedString("cytosine", "C"), new CommentedString("guanine", "G"), new CommentedString("thymine", "T") }, new Dna[] { Dna.build("adenine", "A"), Dna.build("cytosine", "C"), Dna.build("guanine", "G"), Dna.build("thymine", "T") } }
        };
    };

    static final String VALID_COMMENTED_STRING_COLLECTION_TO_DNA_ARRAY_DATA_PROVIDER_NAME = "validCommentedStringCollectionToDnaArrayDataProvider";

    @DataProvider(name = VALID_COMMENTED_STRING_COLLECTION_TO_DNA_ARRAY_DATA_PROVIDER_NAME)
    private Object[][] validCommentedStringCollectionToDnaArrayDataProvider() {
        return new Object[][] {
                { new ArrayList<CommentedString>(), new Dna[] {} },
                { ImmutableList.of(new CommentedString("comment", "A")), new Dna[] { Dna.build("comment", "A") } },
                { ImmutableList.of(new CommentedString("adenine", "A"), new CommentedString("cytosine", "C"), new CommentedString("guanine", "G"), new CommentedString("thymine", "T")), new Dna[] { Dna.build("adenine", "A"), Dna.build("cytosine", "C"), Dna.build("guanine", "G"), Dna.build("thymine", "T") } }
        };
    };

    static final String VALID_COMMENTED_STRING_ARRAY_TO_DNA_LIST_DATA_PROVIDER_NAME = "validCommentedStringArrayToDnaListDataProvider";

    @DataProvider(name = VALID_COMMENTED_STRING_ARRAY_TO_DNA_LIST_DATA_PROVIDER_NAME)
    private Object[][] validCommentedStringArrayToDnaListDataProvider() {
        return new Object[][] {
                { new CommentedString[] {}, new ArrayList<Dna>() {} },
                { new CommentedString[] { new CommentedString("comment", "A") }, ImmutableList.of(Dna.build("comment", "A")) },
                { new CommentedString[] { new CommentedString("adenine", "A"), new CommentedString("cytosine", "C"), new CommentedString("guanine", "G"), new CommentedString("thymine", "T") }, ImmutableList.of(Dna.build("adenine", "A"), Dna.build("cytosine", "C"), Dna.build("guanine", "G"), Dna.build("thymine", "T")) }
        };
    };

    static final String VALID_COMMENTED_STRING_COLLECTION_TO_DNA_LIST_DATA_PROVIDER_NAME = "validCommentedStringCollectionToDnaListDataProvider";

    @DataProvider(name = VALID_COMMENTED_STRING_COLLECTION_TO_DNA_LIST_DATA_PROVIDER_NAME)
    private Object[][] validCommentedStringCollectionToDnaListDataProvider() {
        return new Object[][] {
                { new ArrayList<CommentedString>(), new ArrayList<Dna>() },
                { ImmutableList.of(new CommentedString("comment", "A")), ImmutableList.of(Dna.build("comment", "A")) },
                { ImmutableList.of(new CommentedString("adenine", "A"), new CommentedString("cytosine", "C"), new CommentedString("guanine", "G"), new CommentedString("thymine", "T")), ImmutableList.of(Dna.build("adenine", "A"), Dna.build("cytosine", "C"), Dna.build("guanine", "G"), Dna.build("thymine", "T")) }
        };
    };

    static final String VALID_COMMENTED_STRING_ARRAY_TO_DNA_SET_DATA_PROVIDER_NAME = "validCommentedStringArrayToDnaSetDataProvider";

    @DataProvider(name = VALID_COMMENTED_STRING_ARRAY_TO_DNA_SET_DATA_PROVIDER_NAME)
    private Object[][] validCommentedStringArrayToDnaSetDataProvider() {
        return new Object[][] {
                { new CommentedString[] {}, new HashSet<Dna>() },
                { new CommentedString[] { new CommentedString("comment", "A") }, ImmutableSet.of(Dna.build("comment", "A")) },
                { new CommentedString[] { new CommentedString("adenine", "A"), new CommentedString("cytosine", "C"), new CommentedString("guanine", "G"), new CommentedString("thymine", "T") }, ImmutableSet.of(Dna.build("adenine", "A"), Dna.build("cytosine", "C"), Dna.build("guanine", "G"), Dna.build("thymine", "T")) }
        };
    };

    static final String VALID_COMMENTED_STRING_COLLECTION_TO_DNA_SET_DATA_PROVIDER_NAME = "validCommentedStringVollectionToDnaSetDataProvider";

    @DataProvider(name = VALID_COMMENTED_STRING_COLLECTION_TO_DNA_SET_DATA_PROVIDER_NAME)
    private Object[][] validCommentedStringCollectionToDnaSetDataProvider() {
        return new Object[][] {
                { new ArrayList<CommentedString>(), new HashSet<Dna>() },
                { ImmutableList.of(new CommentedString("comment", "A")), ImmutableSet.of(Dna.build("comment", "A")) },
                { ImmutableList.of(new CommentedString("adenine", "A"), new CommentedString("cytosine", "C"), new CommentedString("guanine", "G"), new CommentedString("thymine", "T")), ImmutableSet.of(Dna.build("adenine", "A"), Dna.build("cytosine", "C"), Dna.build("guanine", "G"), Dna.build("thymine", "T")) }
        };
    };
}

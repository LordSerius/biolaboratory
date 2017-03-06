package hu.bioinformatics.biolaboratory.utils.resource;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

/**
 * Unit tests for {@link CommentedString}.
 *
 * @author Attila Radi
 */
public class CommentedStringTest {

    private static final String INVALID_CONSTRUCTOR_DATA_PROVIDER_NAME = "invalidConstructorDataProvider";

    @DataProvider(name = INVALID_CONSTRUCTOR_DATA_PROVIDER_NAME)
    private Object[][] invalidConstructorDataProvider() {
        return new Object[][] {
                { null, "line" },
                { "comment", null },
                { "comment", "" },
                { "comment", "   " }
        };
    }

    private static final String VALID_CONSTRUCTOR_DATA_PROVIDER_NAME = "validConstructorDataProvider";

    @DataProvider(name = VALID_CONSTRUCTOR_DATA_PROVIDER_NAME)
    private Object[][] validConstructorDataProvider() {
        return new Object[][] {
                { "comment", "line" },
                { "", "line" },
                { " comment ", " line " },
                { "   ", "line" }
        };
    }

    private static final String EQUALS_DATA_PROVIDER_NAME = "equalsDataProvider";

    @DataProvider(name = EQUALS_DATA_PROVIDER_NAME)
    private Object[][] equalsDataProvider() {
        return new Object[][] {
                { new CommentedString("comment", "line"), new CommentedString("comment", "line"), true },
                { new CommentedString("", "line"), new CommentedString("", "line"), true },
                { new CommentedString(" comment ", " line "), new CommentedString("comment", "line"), true },
                { new CommentedString("comment", "line1"), new CommentedString("comment", "line2"), false },
                { new CommentedString("comment1", "line"), new CommentedString("comment2", "line"), false },
                { new CommentedString("", "line"), new CommentedString("comment", "line"), false },
                { new CommentedString("comment", "line"), new CommentedString("", "line"), false }
        };
    }

    @Test(dataProvider = INVALID_CONSTRUCTOR_DATA_PROVIDER_NAME,
        expectedExceptions = IllegalArgumentException.class)
    public void shouldConstructorThrowException(String comment, String line) {
        new CommentedString(comment, line);
        fail();
    }

    @Test(dataProvider = VALID_CONSTRUCTOR_DATA_PROVIDER_NAME)
    public void shouldConstructorCreate(String comment, String line) {
        CommentedString commentedString = new CommentedString(comment, line);
        assertThat(commentedString.getComment(), is(equalTo(comment.trim())));
        assertThat(commentedString.getString(), is(equalTo(line.trim())));
    }

    @Test(dataProvider = EQUALS_DATA_PROVIDER_NAME)
    public void shouldEqualsReturn(CommentedString leftHand, CommentedString rightHand, boolean isEquals) {
        assertThat(isEquals, allOf(
                is(equalTo(leftHand.equals(rightHand))),
                is(equalTo(leftHand.hashCode() == rightHand.hashCode()))
        ));
    }
}
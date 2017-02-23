package hu.bioinformatics.biolaboratory.utils.resource;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

/**
 * Unit tests for {@link CommentedLine}.
 *
 * @author Attila Radi
 */
public class CommentedLineTest {

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
                { new CommentedLine("comment", "line"), new CommentedLine("comment", "line"), true },
                { new CommentedLine("", "line"), new CommentedLine("", "line"), true },
                { new CommentedLine(" comment ", " line "), new CommentedLine("comment", "line"), true },
                { new CommentedLine("comment", "line1"), new CommentedLine("comment", "line2"), false },
                { new CommentedLine("comment1", "line"), new CommentedLine("comment2", "line"), false },
                { new CommentedLine("", "line"), new CommentedLine("comment", "line"), false },
                { new CommentedLine("comment", "line"), new CommentedLine("", "line"), false }
        };
    }

    @Test(dataProvider = INVALID_CONSTRUCTOR_DATA_PROVIDER_NAME,
        expectedExceptions = IllegalArgumentException.class)
    public void shouldConstructorThrowException(String comment, String line) {
        new CommentedLine(comment, line);
        fail();
    }

    @Test(dataProvider = VALID_CONSTRUCTOR_DATA_PROVIDER_NAME)
    public void shouldConstructorCreate(String comment, String line) {
        CommentedLine commentedLine = new CommentedLine(comment, line);
        assertThat(commentedLine.getComment(), is(equalTo(comment.trim())));
        assertThat(commentedLine.getLine(), is(equalTo(line.trim())));
    }

    @Test(dataProvider = EQUALS_DATA_PROVIDER_NAME)
    public void shouldEqualsReturn(CommentedLine leftHand, CommentedLine rightHand, boolean isEquals) {
        assertThat(isEquals, allOf(
                is(equalTo(leftHand.equals(rightHand))),
                is(equalTo(leftHand.hashCode() == rightHand.hashCode()))
        ));
    }
}
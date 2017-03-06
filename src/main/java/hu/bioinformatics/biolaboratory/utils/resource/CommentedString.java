package hu.bioinformatics.biolaboratory.utils.resource;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The commented string contains a comment and data information about the string.
 *
 * @author Attila Radi
 */
public class CommentedString {
    private final String comment;
    private final String string;

    public CommentedString(String comment, String string) {
        Preconditions.checkArgument(comment != null, "Comment should not be null");
        Preconditions.checkArgument(StringUtils.isNotBlank(string), "String should not be blank");
        this.comment = comment.trim();
        this.string = string.trim();
    }

    public String getComment() {
        return comment;
    }

    public String getString() {
        return string;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || !obj.getClass().equals(getClass())) return false;
        CommentedString rightHand = (CommentedString) obj;
        return this.comment.equals(rightHand.comment)
                && this.string.equals(rightHand.string);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(comment).append(string).toHashCode();
    }

    @Override
    public String toString() {
        return "{ \"" + comment + "\", " + string + " }";
    }
}

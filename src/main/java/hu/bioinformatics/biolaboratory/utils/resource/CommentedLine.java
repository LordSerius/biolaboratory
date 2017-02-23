package hu.bioinformatics.biolaboratory.utils.resource;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The commented line contains a comment and data information about the line.
 *
 * @author Attila Radi
 */
public class CommentedLine {
    private final String comment;
    private final String line;

    public CommentedLine(String comment, String line) {
        Preconditions.checkArgument(comment != null, "Comment should not be null");
        Preconditions.checkArgument(StringUtils.isNotBlank(line), "Line should not be blank");
        this.comment = comment.trim();
        this.line = line.trim();
    }

    public String getComment() {
        return comment;
    }

    public String getLine() {
        return line;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || !obj.getClass().equals(getClass())) return false;
        CommentedLine rightHand = (CommentedLine) obj;
        return this.comment.equals(rightHand.comment)
                && this.line.equals(rightHand.line);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(comment).append(line).toHashCode();
    }

    @Override
    public String toString() {
        return "{ \"" + comment + "\", " + line + " }";
    }
}

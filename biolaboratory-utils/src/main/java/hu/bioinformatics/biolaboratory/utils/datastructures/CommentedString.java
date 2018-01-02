package hu.bioinformatics.biolaboratory.utils.datastructures;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.notNullArgument;

/**
 * The commented string contains a comment and data information about the string.
 *
 * @author Attila Radi
 */
public class CommentedString {
    private final String comment;
    private final String string;

    /**
     * Construct a {@link CommentedString} from comment and string parameters. Trim these parameters for this container.
     *
     * @param comment The comment.
     * @param string The string value.
     * @throws IllegalArgumentException If comment is null.
     * @throws IllegalArgumentException If string is null.
     */
    public CommentedString(final String comment, final String string) {
        notNullArgument("Comment", comment);
        notNullArgument("String", string);
        this.comment = comment.trim();
        this.string = string.trim();
    }

    /**
     * Getter of the comment.
     *
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Getter of the string.
     *
     * @return string
     */
    public String getString() {
        return string;
    }

    /**
     * Compare with an other {@link CommentedString}.
     *
     * @param obj Other {@link CommentedString}.
     * @return If comment and string are equal.
     */
    @Override
    public boolean equals(final Object obj) {
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

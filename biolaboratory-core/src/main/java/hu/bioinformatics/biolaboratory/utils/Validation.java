package hu.bioinformatics.biolaboratory.utils;

import com.google.common.base.Preconditions;

import java.util.Collection;

import static org.apache.commons.lang3.Validate.noNullElements;

/**
 * Provides validation methods t check the validity of collections.
 *
 * @author Attila Radi
 */
public class Validation {

    /**
     * Throws {@link IllegalArgumentException} if the input varargs is empty or contains null element.
     *
     * @param varargs The input varargs to validate.
     * @param <T> The type of the elements.
     * @return The same varargs if they valid.
     * @throws IllegalArgumentException If varargs is null, empty, or contains null element.
     */
    @SafeVarargs
    public static <T> T[] validateNotEmptyVarargs(final T... varargs) {
        validateVarargs(varargs);
        Preconditions.checkArgument(varargs.length > 0, "Input varargs should be az least 1");
        return varargs;
    }

    /**
     * Throws {@link IllegalArgumentException} if the input varargs contains null element.
     *
     * @param varargs The input varargs to validate.
     * @param <T> The type of the elements.
     * @return The same varargs if they valid.
     * @throws IllegalArgumentException If varargs is null or contains null element.
     */
    @SafeVarargs
    public static <T> T[] validateVarargs(final T... varargs) {
        Preconditions.checkArgument(varargs != null, "Varargs should not be null");
        return noNullElements(varargs, "Input vararg element should not be null");
    }

    /**
     * Throws {@link IllegalArgumentException} if the input {@link Collection} is empty or contains null element.
     *
     * @param collection The input collection to validate.
     * @param <COL> {@link Collection} or its inherited types.
     * @param <T> The type of the collection.
     * @return The same collection if it is valid.
     * @throws IllegalArgumentException If collection is null, empty, or contains null element.
     */
    public static <COL extends Collection<T>, T> COL validateNotEmptyCollection(final COL collection) {
        validateCollection(collection);
        Preconditions.checkArgument(!collection.isEmpty(), "Collection size should be not empty");
        return collection;
    }

    /**
     * Throws {@link IllegalArgumentException} if the input {@link Collection} contains null element.
     *
     * @param collection The input collection to validate.
     * @param <COL> {@link Collection} or its inherited types.
     * @param <T> The type of the collection.
     * @return The same collection if it is valid.
     * @throws IllegalArgumentException If collection is null or contains null element.
     */
    public static <COL extends Collection<T>, T> COL validateCollection(final COL collection) {
        Preconditions.checkArgument(collection != null, "Collection should not be null");
        return noNullElements(collection, "Input collection element should not be null");
    }

    /**
     * TODO
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T validateNotEmpty(final T object) {
        Preconditions.checkArgument(object != null, "Input argument should not be null");
        return object;
    }

    /**
     * TODO
     *
     * @param object
     * @param parameterName
     * @param <T>
     * @return
     */
    public static <T> T validateNotEmpty(final T object, final String parameterName) {
        Preconditions.checkArgument(object != null, parameterName + " should not be null");
        return object;
    }
}

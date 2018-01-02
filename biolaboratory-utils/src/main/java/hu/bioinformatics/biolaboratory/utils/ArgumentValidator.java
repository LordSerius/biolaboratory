package hu.bioinformatics.biolaboratory.utils;

import com.google.common.base.Preconditions;
import com.sun.javafx.binding.StringFormatter;

import java.util.Collection;

import static org.apache.commons.lang3.Validate.noNullElements;

/**
 * Provides validation methods t check the validity of collections.
 *
 * @author Attila Radi
 */
public class ArgumentValidator {
    private static final String DEFAULT_INPUT_COLLECTION_NAME = "Input collection";
    private static final String DEFAULT_INPUT_ARGUMENT_NAME = "Input argument";
    private static final String DEFAULT_INPUT_NUMBER_NAME = "Input number";
    private static final String DEFAULT_INPUT_VARARGS_NAME = "Input varargs";

    /**
     * Throws {@link IllegalArgumentException} if the input varargs is empty or contains null element.
     *
     * @param varargs The input varargs to validate.
     * @param <T> The type of the elements.
     * @return The same varargs if they valid.
     * @throws IllegalArgumentException If varargs is null, empty, or contains null element.
     */
    @SafeVarargs
    public static <T> T[] notEmptyVarargs(final T... varargs) {
        return notEmptyVarargs(DEFAULT_INPUT_VARARGS_NAME, varargs);
    }

    /**
     * Throws {@link IllegalArgumentException} if the input varargs is empty or contains null element. Gives the
     * argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param varargs The input varargs to validate.
     * @param <T> The type of the elements.
     * @return The same varargs if they valid.
     * @throws IllegalArgumentException If varargs is null, empty, or contains null element.
     */
    @SafeVarargs
    public static <T> T[] notEmptyVarargs(final String argumentName, final T... varargs) {
        notNullVarargs(argumentName, varargs);
        Preconditions.checkArgument(varargs.length > 0, argumentName + " should be az least 1");
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
    public static <T> T[] notNullVarargs(final T... varargs) {
        return notNullVarargs(DEFAULT_INPUT_VARARGS_NAME, varargs);
    }

    /**
     * Throws {@link IllegalArgumentException} if the input varargs contains null element. Gives the argument's name
     * in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param varargs The input varargs to validate.
     * @param <T> The type of the elements.
     * @return The same varargs if they valid.
     * @throws IllegalArgumentException If varargs is null or contains null element.
     */
    @SafeVarargs
    public static <T> T[] notNullVarargs(final String argumentName, final T... varargs) {
        return noNullElements(notNullArgument(argumentName, varargs), argumentName + " element should not be null");
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
    public static <COL extends Collection<T>, T> COL notEmptyCollection(final COL collection) {
        return notEmptyCollection(DEFAULT_INPUT_COLLECTION_NAME, collection);
    }

    /**
     * Throws {@link IllegalArgumentException} if the input {@link Collection} is empty or contains null element.
     * Gives the argument's name in case of exception.
     *
     * @param collection The input collection to validate.
     * @param <COL> {@link Collection} or its inherited types.
     * @param <T> The type of the collection.
     * @return The same collection if it is valid.
     * @throws IllegalArgumentException If collection is null or contains null element.
     */
    public static <COL extends Collection<T>, T> COL notEmptyCollection(final String argumentName, final COL collection) {
        notNullCollection(argumentName, collection);
        Preconditions.checkArgument(!collection.isEmpty(), argumentName + " size should be not empty");
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
    public static <COL extends Collection<T>, T> COL notNullCollection(final COL collection) {
        return notNullCollection(DEFAULT_INPUT_COLLECTION_NAME, collection);
    }

    /**
     * Throws {@link IllegalArgumentException} if the input {@link Collection} contains null element. Gives the argument's
     * name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param collection The input collection to validate.
     * @param <COL> {@link Collection} or its inherited types.
     * @param <T> The type of the collection.
     * @return The same collection if it is valid.
     * @throws IllegalArgumentException If collection is null or contains null element.
     */
    public static <COL extends Collection<T>, T> COL notNullCollection(final String argumentName, final COL collection) {
        return noNullElements(notNullArgument(argumentName, collection), argumentName + " element should not be null");
    }

    /**
     * Throws {@link IllegalArgumentException} if argument is null.
     *
     * @param argument The argument to validate.
     * @param <T> The type of the argument.
     * @return The same object if it is valid.
     * @throws IllegalArgumentException If argument is null.
     */
    public static <T> T notNullArgument(final T argument) {
        return notNullArgument(DEFAULT_INPUT_ARGUMENT_NAME, argument);
    }

    /**
     * Throws {@link IllegalArgumentException} if argument is null. Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param <T> The type of the argument.
     * @return The same object if it is valid.
     * @throws IllegalArgumentException If argument is null.
     */
    public static <T> T notNullArgument(final String argumentName, final T argument) {
        Preconditions.checkArgument(argument != null, argumentName + " should not be null");
        return argument;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is smaller to target.
     *
     * @param number The number to validate.
     * @param target The number validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number >= target.
     */
    public static int smallerArgument(final int number, final int target) {
        return smallerArgument(DEFAULT_INPUT_NUMBER_NAME, number, target);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is smaller to target.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param target The number validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number >= target.
     */
    public static int smallerArgument(final String argumentName, final int number, int target) {
        Preconditions.checkArgument(number < target,
                StringFormatter.format("%s (%d) should smaller to %d", argumentName, number, target));
        return number;
    }


    /**
     * Throws {@link IllegalArgumentException} if input number is smaller or equal to target.
     *
     * @param number The number to validate.
     * @param target The number validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number > target.
     */
    public static int smallerOrEqualArgument(final int number, final int target) {
        return smallerOrEqualArgument(DEFAULT_INPUT_NUMBER_NAME, number, target);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is smaller or equal to target.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param target The number validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number > target.
     */
    public static int smallerOrEqualArgument(final String argumentName, final int number, final int target) {
        Preconditions.checkArgument(number <= target,
                StringFormatter.format("%s (%d) should smaller or equal to %d", argumentName, number, target));
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is equal to target.
     *
     * @param number The number to validate.
     * @param target The number validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number != target.
     */
    public static int equalArgument(final int number, final int target) {
        return equalArgument(DEFAULT_INPUT_NUMBER_NAME, number, target);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is equal to target.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param target The number validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number != target.
     */
    public static int equalArgument(final String argumentName, final int number, final int target) {
        Preconditions.checkArgument(number == target,
                StringFormatter.format("%s (%d) should equal to %d", argumentName, number, target));
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is not equal to target.
     *
     * @param number The number to validate.
     * @param target The number validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number == target.
     */
    public static int notEqualArgument(final int number, final int target) {
        return notEqualArgument(DEFAULT_INPUT_ARGUMENT_NAME, number, target);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is not equal to target.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param target The number validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number == target.
     */
    public static int notEqualArgument(final String argumentName, final int number, final int target) {
        Preconditions.checkArgument(number != target,
                StringFormatter.format("%s (%d) should not equal to %d", argumentName, number, target));
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is greater or equal to target.
     *
     * @param number The number to validate.
     * @param target The number validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number < target.
     */
    public static int greaterOrEqualArgument(final int number, final int target) {
        return greaterOrEqualArgument(DEFAULT_INPUT_NUMBER_NAME, number, target);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is greater or equal to target.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param target The number validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number < target.
     */
    public static int greaterOrEqualArgument(final String argumentName, final int number, final int target) {
        Preconditions.checkArgument(number >= target,
                StringFormatter.format("%s (%d) should greater or equal to %d", argumentName, number, target));
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is greater to target.
     *
     * @param number The number to validate.
     * @param target The number validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number <= target.
     */
    public static int greaterArgument(final int number, final int target) {
        return greaterArgument(DEFAULT_INPUT_NUMBER_NAME, number, target);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is greater to target.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param target The number validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number <= target.
     */
    public static int greaterArgument(final String argumentName, final int number, final int target) {
        Preconditions.checkArgument(number > target,
                StringFormatter.format("%s (%d) should greater or equal to %d", argumentName, number, target));
        return number;
    }
}

package hu.bioinformatics.biolaboratory.utils;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.Collection;

/**
 * Provides validation methods t check the validity of collections.
 *
 * @author Attila Radi
 */
public class ArgumentValidator {
    private static final String DEFAULT_INPUT_COLLECTION_NAME = "Input collection";
    private static final String DEFAULT_INPUT_ARGUMENT_NAME = "Input argument";
    private static final String DEFAULT_INPUT_NUMBER_NAME = "Input number";
    private static final String DEFAULT_INPUT_STRING_NAME = "Input string";
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
    public static <T> T[] checkNotEmptyVarargs(final T... varargs) {
        return checkNotEmptyVarargs(DEFAULT_INPUT_VARARGS_NAME, varargs);
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
    public static <T> T[] checkNotEmptyVarargs(@Nullable final String argumentName, final T... varargs) {
        checkNotNullArgument(argumentName, varargs);
        checkPositiveNumber(argumentName, varargs.length);
        return innerCheckNotNullVarargs(argumentName, varargs);
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
    public static <T> T[] checkNotNullVarargs(final T... varargs) {
        return checkNotNullVarargs(DEFAULT_INPUT_VARARGS_NAME, varargs);
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
    public static <T> T[] checkNotNullVarargs(@Nullable final String argumentName, final T... varargs) {
        checkNotNullArgument(argumentName, varargs);
        return innerCheckNotNullVarargs(argumentName, varargs);
    }

    @SafeVarargs
    private static <T> T[] innerCheckNotNullVarargs(final String argumentName, final T... varargs) {
        for (T vararg : varargs) {
            if (vararg == null) {
                throw new IllegalArgumentException(argumentName + " element should not be null");
            }
        }
        return varargs;
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
    public static <COL extends Collection<T>, T> COL checkNotEmptyCollection(final COL collection) {
        return checkNotEmptyCollection(DEFAULT_INPUT_COLLECTION_NAME, collection);
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
    public static <COL extends Collection<T>, T> COL checkNotEmptyCollection(@Nullable final String argumentName, final COL collection) {
        checkNotNullCollection(argumentName, collection);
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(argumentName + " size should be not empty");
        }
        return innerCheckNotNullCollection(argumentName, collection);
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
    public static <COL extends Collection<T>, T> COL checkNotNullCollection(final COL collection) {
        return checkNotNullCollection(DEFAULT_INPUT_COLLECTION_NAME, collection);
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
    public static <COL extends Collection<T>, T> COL checkNotNullCollection(@Nullable final String argumentName, final COL collection) {
        checkNotNullArgument(argumentName, collection);
        return innerCheckNotNullCollection(argumentName, collection);
    }

    private static <COL extends Collection<T>, T> COL innerCheckNotNullCollection(@Nullable final String argumentName, final COL collection) {
        for (T element : collection) {
            if (element == null) {
                throw new IllegalArgumentException(argumentName + " should not contain null element");
            }
        }
        return collection;
    }

    /**
     * Throws {@link IllegalArgumentException} if argument or targetObject is null. Throws {@link IllegalArgumentException}
     * if types of argument and targetObject differ.
     *
     * @param argument The argument to validate.
     * @param targetObject The target to validate against.
     * @param <T> The type of argument.
     * @return The argument object if it is valid.
     * @throws IllegalArgumentException If argument or targetObject is null.
     * @throws IllegalArgumentException If type of argument and target are not the same.
     */
    public static <T> T checkSameTypeTo(final T argument, final Object targetObject) {
        return checkSameTypeTo(DEFAULT_INPUT_ARGUMENT_NAME, argument, targetObject);
    }

    /**
     * Throws {@link IllegalArgumentException} if argument or targetObject is null. Throws {@link IllegalArgumentException}
     * if types of argument and targetObject differ. Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param argument The argument to validate.
     * @param targetObject The target to validate against.
     * @param <T> The type of argument.
     * @return The argument object if it is valid.
     * @throws IllegalArgumentException If argument or target is null.
     * @throws IllegalArgumentException If type of argument and targetObject are not the same.
     */
    public static <T> T checkSameTypeTo(@Nullable final String argumentName, final T argument, final Object targetObject) {
        Class<?> argumentsType = checkNotNullArgument(argumentName, argument).getClass();
        Class<?> targetsType = checkNotNullArgument(targetObject).getClass();
        if (argumentsType != targetsType) {
            throw new IllegalArgumentException(argumentName + "'s (" + argumentsType + ") type is differ than " + targetsType);
        }
        return argument;
    }

    /**
     * Throws {@link IllegalArgumentException} if argument or targetObject is null. Throws {@link IllegalArgumentException}
     * if types of argument and targetObject differ. Gives the argument's and target's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param argument The argument to validate.
     * @param targetObjectName The target to validate against.
     * @param targetObject The target to validate against.
     * @param <T> The type of argument.
     * @return The argument object if it is valid.
     * @throws IllegalArgumentException If argument or target is null.
     * @throws IllegalArgumentException If type of argument and targetObject are not the same.
     */
    public static <T> T checkSameTypeTo(@Nullable final String argumentName, final T argument, @Nullable final String targetObjectName, final Object targetObject) {
        Class<?> argumentsType = checkNotNullArgument(argumentName, argument).getClass();
        Class<?> targetsType = checkNotNullArgument(targetObjectName, targetObject).getClass();
        if (argumentsType != targetsType) {
            throw new IllegalArgumentException(argumentName + "'s (" + argumentsType + ") type is differ than " + targetObjectName + "'s type " + "(" + targetsType + ")");
        }
        return argument;
    }

    /**
     * Throws {@link IllegalArgumentException} if argument is null.
     *
     * @param argument The argument to validate.
     * @param <T> The type of the argument.
     * @return The same object if it is valid.
     * @throws IllegalArgumentException If argument is null.
     */
    public static <T> T checkNotNullArgument(final T argument) {
        return checkNotNullArgument(DEFAULT_INPUT_ARGUMENT_NAME, argument);
    }

    /**
     * Throws {@link IllegalArgumentException} if argument is null. Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param <T> The type of the argument.
     * @return The same object if it is valid.
     * @throws IllegalArgumentException If argument is null.
     */
    public static <T> T checkNotNullArgument(@Nullable final String argumentName, final T argument) {
        if (argument == null) {
            throw new IllegalArgumentException(argumentName + " should not be null");
        }
        return argument;
    }

    /**
     * Throws {@link IllegalArgumentException} if {@link String} is blank.
     *
     * @param string The input {@link String} to validate.
     * @return The same object if it is valid.
     * @throws IllegalArgumentException If string is blank.
     */
    public static String checkNotBlankString(final String string) {
        return checkNotBlankString(DEFAULT_INPUT_STRING_NAME, string);
    }

    /**
     * Throws {@link IllegalArgumentException} if {@link String} is blank. Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param string The input {@link String} to validate.
     * @return The same object if it is valid.
     * @throws IllegalArgumentException If string is blank.
     */
    public static String checkNotBlankString(@Nullable final String argumentName, final String string) {
        if (StringUtils.isBlank(string)) {
            throw new IllegalArgumentException(argumentName + " should not be blank");
        }
        return string;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is smaller to target.
     *
     * @param number The number to validate.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number >= target.
     */
    public static int checkSmallerNumberTo(final int number, final int target) {
        return checkSmallerNumberTo(DEFAULT_INPUT_NUMBER_NAME, number, target);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is smaller to target.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number >= target.
     */
    public static int checkSmallerNumberTo(@Nullable final String argumentName, final int number, int target) {
        if (number >= target) {
            throw new IllegalArgumentException(argumentName + " (" + number + ") should smaller to " + target);
        }
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is smaller to target.
     * Gives the argument's and target's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param targetName Specific name of the target.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number >= target.
     */
    public static int checkSmallerNumberTo(@Nullable final String argumentName, final int number, @Nullable final String targetName, int target) {
        if (number >= target) {
            throw new IllegalArgumentException(argumentName + " (" + number + ") should smaller to " + targetName + " (" + target + ")");
        }
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is smaller or equal to target.
     *
     * @param number The number to validate.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number > target.
     */
    public static int checkSmallerOrEqualNumberTo(final int number, final int target) {
        return checkSmallerOrEqualNumberTo(DEFAULT_INPUT_NUMBER_NAME, number, target);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is smaller or equal to target.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number > target.
     */
    public static int checkSmallerOrEqualNumberTo(@Nullable final String argumentName, final int number, final int target) {
        if (number > target) {
            throw new IllegalArgumentException(argumentName + " (" + number + ") should equal to " + target);
        }
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is smaller or equal to target.
     * Gives the argument's and target's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param targetName Specific name of the target.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number > target.
     */
    public static int checkSmallerOrEqualNumberTo(@Nullable final String argumentName, final int number, @Nullable final String targetName, final int target) {
        if (number > target) {
            throw new IllegalArgumentException(argumentName + " (" + number + ") should equal to " + targetName + " (" + target + ")");
        }
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is smaller or equal to target.
     *
     * @param number The number to validate.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number > target.
     */
    public static double checkSmallerOrEqualNumberTo(final double number, final double target) {
        return checkSmallerOrEqualNumberTo(DEFAULT_INPUT_ARGUMENT_NAME, number, target);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is smaller or equal to target.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number > target.
     */
    public static double checkSmallerOrEqualNumberTo(@Nullable final String argumentName, final double number, final double target) {
        if (number > target) {
            throw new IllegalArgumentException(argumentName + " (" + number + ") should equal to " + target);
        }
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is smaller or equal to target.
     * Gives the argument's and target's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param targetName Specific name of the target.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number > target.
     */
    public static double checkSmallerOrEqualNumberTo(@Nullable final String argumentName, final double number, @Nullable final String targetName, final double target) {
        if (number > target) {
            throw new IllegalArgumentException(argumentName + " (" + number + ") should equal to " + targetName + " (" + target + ")");
        }
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is equal to target.
     *
     * @param number The number to validate.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number != target.
     */
    public static int checkEqualNumberTo(final int number, final int target) {
        return checkEqualNumberTo(DEFAULT_INPUT_NUMBER_NAME, number, target);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is equal to target.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number != target.
     */
    public static int checkEqualNumberTo(@Nullable final String argumentName, final int number, final int target) {
        if (number != target) {
            throw new IllegalArgumentException(argumentName + " (" + number + ") should equal to " + target);
        }
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is equal to target.
     * Gives the argument's and target's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param targetName Specific name of the target.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number != target.
     */
    public static int checkEqualNumberTo(@Nullable final String argumentName, final int number, @Nullable final String targetName, final int target) {
        if (number != target) {
            throw new IllegalArgumentException(argumentName + " (" + number + ") should equal to " + targetName + " (" + target + ")");
        }
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is not equal to target.
     *
     * @param number The number to validate.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number == target.
     */
    public static int checkNotEqualNumberTo(final int number, final int target) {
        return checkNotEqualNumberTo(DEFAULT_INPUT_ARGUMENT_NAME, number, target);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is not equal to target.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number == target.
     */
    public static int checkNotEqualNumberTo(@Nullable final String argumentName, final int number, final int target) {
        if (number == target) {
            throw new IllegalArgumentException(argumentName + " (" + number + ") should not equal to " + target);
        }
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is not equal to target.
     * Gives the argument's and target's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param targetName Specific name of the target.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number == target.
     */
    public static int checkNotEqualNumberTo(@Nullable final String argumentName, final int number, @Nullable final String targetName, final int target) {
        if (number == target) {
            throw new IllegalArgumentException(argumentName + " (" + number + ") should not equal to " + targetName + " (" + target + ")");
        }
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is greater or equal to target.
     *
     * @param number The number to validate.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number < target.
     */
    public static int checkGreaterOrEqualNumberTo(final int number, final int target) {
        return checkGreaterOrEqualNumberTo(DEFAULT_INPUT_NUMBER_NAME, number, target);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is greater or equal to target.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number < target.
     */
    public static int checkGreaterOrEqualNumberTo(@Nullable final String argumentName, final int number, final int target) {
        if (number < target) {
            throw new IllegalArgumentException(argumentName + " (" + number + ") should greater or equal to " + target);
        }
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is greater or equal to target.
     * Gives the argument's and target's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param targetName Specific name of the target.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number < target.
     */
    public static int checkGreaterOrEqualNumberTo(@Nullable final String argumentName, final int number, @Nullable final String targetName, final int target) {
        if (number < target) {
            throw new IllegalArgumentException(argumentName + " (" + number + ") should greater or equal to " + targetName + " (" + target + ")");
        }
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is greater to target.
     *
     * @param number The number to validate.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number <= target.
     */
    public static int checkGreaterNumberTo(final int number, final int target) {
        return checkGreaterNumberTo(DEFAULT_INPUT_NUMBER_NAME, number, target);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is greater to target.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number <= target.
     */
    public static int checkGreaterNumberTo(@Nullable final String argumentName, final int number, final int target) {
        if (number <= target) {
            throw new IllegalArgumentException(argumentName + " (" + number + ") should greater to " + target);
        }
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is greater to target.
     * Gives the argument's and target's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @param targetName Specific name of the target.
     * @param target The number to validate against.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number <= target.
     */
    public static int checkGreaterNumberTo(@Nullable final String argumentName, final int number, @Nullable final String targetName, final int target) {
        if (number <= target) {
            throw new IllegalArgumentException(argumentName + " (" + number + ") should greater to " + targetName + " (" + target + ")");
        }
        return number;
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is negative.
     *
     * @param number The number to validate.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number < 0.
     */
    public static int checkNotNegativeNumber(final int number) {
        return checkGreaterOrEqualNumberTo(DEFAULT_INPUT_ARGUMENT_NAME, number, 0);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is negative.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number < 0.
     */
    public static int checkNotNegativeNumber(@Nullable final String argumentName, final int number) {
        return checkGreaterOrEqualNumberTo(argumentName, number, 0);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is 0 or negative.
     *
     * @param number The number to validate.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number <= 0.
     */
    public static int checkPositiveNumber(final int number) {
        return checkPositiveNumber(DEFAULT_INPUT_ARGUMENT_NAME, number);
    }

    /**
     * Throws {@link IllegalArgumentException} if input number is 0 or negative.
     * Gives the argument's name in case of exception.
     *
     * @param argumentName The name of the argument.
     * @param number The number to validate.
     * @return The same number if it is valid.
     * @throws IllegalArgumentException If number <= 0.
     */
    public static int checkPositiveNumber(@Nullable final String argumentName, final int number) {
        if (number <= 0) {
            throw  new IllegalArgumentException(argumentName + " (" + number + ") should be positive");
        }
        return number;
    }
}

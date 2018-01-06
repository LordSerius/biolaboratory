package hu.bioinformatics.biolaboratory.utils;

import org.testng.annotations.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Unit tests for {@link ArgumentValidator}.
 *
 * @author Attila Radi
 */
@Test(dataProviderClass = ArgumentValidatorTestDataProvider.class)
public class ArgumentValidatorTest {

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEmptyVarargsThrowException(String[] varargs) {
        ArgumentValidator.checkNotEmptyVarargs(varargs);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME)
    public void shouldNotEmptyVarargsReturn(String[] varargs) {
        String[] returnVarargs = ArgumentValidator.checkNotEmptyVarargs(varargs);
        assertThat(returnVarargs, is(equalTo(varargs)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_EMPTY_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEmptyVarargsWithArgumentNameThrowException(String argumentName, String[] varargs) {
        ArgumentValidator.checkNotEmptyVarargs(argumentName, varargs);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_EMPTY_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotEmptyVarargsWithArgumentNameReturn(String argumentName, String[] varargs) {
        String[] returnVarargs = ArgumentValidator.checkNotEmptyVarargs(argumentName, varargs);
        assertThat(returnVarargs, is(equalTo(varargs)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_NULL_VARARGS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public  void shouldNotNullVarargsThrowException(String[] varargs) {
        ArgumentValidator.checkNotNullVarargs(varargs);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_NULL_VARARGS_DATA_PROVIDER_NAME)
    public void shouldNotNullVarargsReturn(String[] varargs) {
        String[] returnVarargs = ArgumentValidator.checkNotNullVarargs(varargs);
        assertThat(returnVarargs, is(equalTo(varargs)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_NULL_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullVarargsWithArgumentNameThrowException(String argumentName, String[] varargs) {
        ArgumentValidator.checkNotNullVarargs(argumentName, varargs);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_NULL_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotNullVarargsWithArgumentNameReturn(String argumentName, String[] varargs) {
        String[] returnVarargs = ArgumentValidator.checkNotNullVarargs(argumentName, varargs);
        assertThat(returnVarargs, is(equalTo(varargs)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEmptyCollectionThrowException(Collection<String> collection) {
        ArgumentValidator.checkNotEmptyCollection(collection);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldNotEmptyCollectionReturn(Collection<String> collection) {
        Collection<String> returnCollection = ArgumentValidator.checkNotEmptyCollection(collection);
        assertThat(returnCollection, is(equalTo(collection)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_EMPTY_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEmptyCollectionWithArgumentNameThrowException(String argumentName, Collection<String> collection) {
        ArgumentValidator.checkNotEmptyCollection(argumentName, collection);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_EMPTY_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotEmptyCollectionWithArgumentNameReturn(String argumentName, Collection<String> collection) {
        Collection<String> returnCollection = ArgumentValidator.checkNotEmptyCollection(argumentName, collection);
        assertThat(returnCollection, is(equalTo(collection)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_NULL_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullCollectionThrowException(Collection<String> collection) {
        ArgumentValidator.checkNotNullCollection(collection);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_NULL_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldNotNullCollectionReturn(Collection<String> collection) {
        Collection<String> returnCollection = ArgumentValidator.checkNotNullCollection(collection);
        assertThat(returnCollection, is(equalTo(collection)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_NULL_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullCollectionWithArgumentNameThrowException(String argumentName, Collection<String> collection) {
        ArgumentValidator.checkNotNullCollection(argumentName, collection);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_NULL_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotNullCollectionWithArgumentNameReturn(String argumentName, Collection<String> collection) {
        Collection<String> returnCollection = ArgumentValidator.checkNotNullCollection(argumentName, collection);
        assertThat(returnCollection, is(equalTo(collection)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SAME_TYPE_WITH_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSameTypeWithThrowException(Object argument, Object targetObject) {
        ArgumentValidator.checkSameTypeTo(argument, targetObject);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SAME_TYPE_WITH_NAME_DATA_PROVIDER_NAME)
    public void shouldSameTypeWithReturn(Object argument, Object targetObject) {
        Object returnArgument = ArgumentValidator.checkSameTypeTo(argument, targetObject);
        assertThat(returnArgument, is(equalTo(argument)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SAME_TYPE_WITH_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSameTypeWithWithArgumentNameThrowException(String argumentName, Object argument, Object targetObject) {
        ArgumentValidator.checkSameTypeTo(argumentName, argument, targetObject);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SAME_TYPE_WITH_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldSameTypeWithWithArgumentNameReturn(String argumentName, Object argument, Object targetObject) {
        Object returnArgument = ArgumentValidator.checkSameTypeTo(argumentName, argument, targetObject);
        assertThat(returnArgument, is(equalTo(argument)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SAME_TYPE_WITH_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSameTypeWithWithArgumentAndTargetNameThrowException(String argumentName, Object argument, String targetObjectName, Object targetObject) {
        ArgumentValidator.checkSameTypeTo(argumentName, argument, targetObjectName, targetObject);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SAME_TYPE_WITH_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    public void shouldSameTypeWithWithArgumentAndTargetNameReturn(String argumentName, Object argument, String targetObjectName, Object targetObject) {
        Object returnArgument = ArgumentValidator.checkSameTypeTo(argumentName, argument, targetObjectName, targetObject);
        assertThat(returnArgument, is(equalTo(argument)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_NULL_ARGUMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullArgumentThrowException(Object argument) {
        ArgumentValidator.checkNotNullArgument(argument);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_NULL_ARGUMENT_DATA_PROVIDER_NAME)
    public void shouldNotNullArgumentReturn(Object argument) {
        Object returnArgument = ArgumentValidator.checkNotNullArgument(argument);
        assertThat(returnArgument, is(equalTo(argument)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_NULL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullArgumentWithArgumentNameThrowException(String argumentName, Object argument) {
        ArgumentValidator.checkNotNullArgument(argumentName, argument);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_NULL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotNullArgumentWithArgumentNameReturn(String argumentName, Object argument) {
        Object returnArgument = ArgumentValidator.checkNotNullArgument(argumentName, argument);
        assertThat(returnArgument, is(equalTo(argument)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_BLANK_STRING_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotBlankStringThrowException(String string) {
        ArgumentValidator.checkNotBlankString(string);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_BLANK_STRING_DATA_PROVIDER_NAME)
    public void shouldNotBlankStringReturn(String string) {
        String returnString = ArgumentValidator.checkNotBlankString(string);
        assertThat(returnString, is(equalTo(string)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_BLANK_STRING_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotBlankStringWithArgumentNameThrowException(String argumentName, String string) {
        ArgumentValidator.checkNotBlankString(argumentName, string);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_BLANK_STRING_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotBlankStringWithArgumentNameReturn(String argumentName, String string) {
        String returnString = ArgumentValidator.checkNotBlankString(argumentName, string);
        assertThat(returnString, is(equalTo(string)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SMALLER_ARGUMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSmallerArgumentThrowException(int number, int target) {
        ArgumentValidator.checkSmallerNumberTo(number, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SMALLER_ARGUMENT_DATA_PROVIDER_NAME)
    public void shouldSmallerArgumentReturn(int number, int target) {
        int returnArgument = ArgumentValidator.checkSmallerNumberTo(number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SMALLER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSmallerArgumentWithArgumentNameThrowException(String argumentName, int number, int target) {
        ArgumentValidator.checkSmallerNumberTo(argumentName, number, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SMALLER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldSmallerArgumentWithArgumentNameReturn(String argumentName, int number, int target) {
        int returnArgument = ArgumentValidator.checkSmallerNumberTo(argumentName, number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SMALLER_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSmallerArgumentWithArgumentAndTargetNameThrowException(String argumentName, int number, String targetName, int target) {
        ArgumentValidator.checkSmallerNumberTo(argumentName, number, targetName, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SMALLER_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    public void shouldSmallerArgumentWithArgumentAndTargetNameReturn(String argumentName, int number, String targetName, int target) {
        int returnArgument = ArgumentValidator.checkSmallerNumberTo(argumentName, number, targetName, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SMALLER_OR_EQUAL_ARGUMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSmallerOrEqualArgumentThrowException(int number, int target) {
        ArgumentValidator.checkSmallerOrEqualNumberTo(number, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SMALLER_OR_EQUAL_ARGUMENT_DATA_PROVIDER_NAME)
    public void shouldSmallerOrEqualArgumentReturn(int number, int target) {
        int returnArgument = ArgumentValidator.checkSmallerOrEqualNumberTo(number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSmallerOrEqualArgumentWithArgumentNamThrowException(String argumentName, int number, int target) {
        ArgumentValidator.checkSmallerOrEqualNumberTo(argumentName, number, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldSmallerOrEqualArgumentWithArgumentNameReturn(String argumentName, int number, int target) {
        int returnArgument = ArgumentValidator.checkSmallerOrEqualNumberTo(argumentName, number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSmallerOrEqualArgumentWithArgumentAndTargetNameThrowException(String argumentName, int number, String targetName, int target) {
        ArgumentValidator.checkSmallerOrEqualNumberTo(argumentName, number, targetName, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    public void shouldSmallerOrEqualArgumentWithArgumentAndTargetNameReturn(String argumentName, int number, String targetName, int target) {
        int returnArgument = ArgumentValidator.checkSmallerOrEqualNumberTo(argumentName, number, targetName, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSmallerOrEqualArgumentThrowException(double number, double target) {
        ArgumentValidator.checkSmallerOrEqualNumberTo(number, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_DATA_PROVIDER_NAME)
    public void shouldSmallerOrEqualArgumentReturn(double number, double target) {
        double returnArgument = ArgumentValidator.checkSmallerOrEqualNumberTo(number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSmallerOrEqualArgumentWithArgumentNameThrowException(String argumentName, double number, double target) {
        ArgumentValidator.checkSmallerOrEqualNumberTo(argumentName, number, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldSmallerOrEqualArgumentWithArgumentNameReturn(String argumentName, double number, double target) {
        double returnArgument = ArgumentValidator.checkSmallerOrEqualNumberTo(argumentName, number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSmallerOrEqualArgumentWithArgumentAndTargetNameThrowException(String argumentName, double number, String targetName, double target) {
        ArgumentValidator.checkSmallerOrEqualNumberTo(argumentName, number, targetName, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SMALLER_OR_EQUAL_DOUBLE_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    public void shouldSmallerOrEqualArgumentWithArgumentAndTargetNameReturn(String argumentName, double number, String targetName, double target) {
        double returnArgument = ArgumentValidator.checkSmallerOrEqualNumberTo(argumentName, number, targetName, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_EQUAL_ARGUMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldEqualArgumentThrowException(int number, int target) {
        ArgumentValidator.checkEqualNumberTo(number, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_EQUAL_ARGUMENT_DATA_PROVIDER_NAME)
    public void shouldEqualArgumentReturn(int number, int target) {
        int returnArgument = ArgumentValidator.checkEqualNumberTo(number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldEqualArgumentWithArgumentNameThrowException(String argumentName, int number, int target) {
        ArgumentValidator.checkEqualNumberTo(argumentName, number, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldEqualArgumentWithArgumentNameReturn(String argumentName, int number, int target) {
        int returnArgument = ArgumentValidator.checkEqualNumberTo(argumentName, number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldEqualArgumentWithArgumentAndTargetNameThrowException(String argumentName, int number, String targetName, int target) {
        ArgumentValidator.checkEqualNumberTo(argumentName, number, targetName, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    public void shouldEqualArgumentWithArgumentAndTargetNameReturn(String argumentName, int number, String targetName, int target) {
        int returnArgument = ArgumentValidator.checkEqualNumberTo(argumentName, number, targetName, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_EQUAL_ARGUMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEqualArgumentThrowException(int number, int target) {
        ArgumentValidator.checkNotEqualNumberTo(number, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_EQUAL_ARGUMENT_DATA_PROVIDER_NAME)
    public void shouldNotEqualArgumentReturn(int number, int target) {
        int returnArgument = ArgumentValidator.checkNotEqualNumberTo(number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEqualArgumentWithArgumenttNameThrowException(String argumentName, int number, int target) {
        ArgumentValidator.checkNotEqualNumberTo(argumentName, number, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotEqualArgumentWithArgumenttNameReturn(String argumentName, int number, int target) {
        int returnArgument = ArgumentValidator.checkNotEqualNumberTo(argumentName, number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEqualArgumentWithArgumentAndTargetNameThrowException(String argumentName, int number, String targetName, int target) {
        ArgumentValidator.checkNotEqualNumberTo(argumentName, number, targetName, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    public void shouldNotEqualArgumentWithArgumentAndTargetNameReturn(String argumentName, int number, String targetName, int target) {
        int returnArgument = ArgumentValidator.checkNotEqualNumberTo(argumentName, number, targetName, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_GREATER_OR_EQUAL_ARGUMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGreaterOrEqualArgumentThrowException(int number, int target) {
        ArgumentValidator.checkGreaterOrEqualNumberTo(number, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_GREATER_OR_EQUAL_ARGUMENT_DATA_PROVIDER_NAME)
    public void shouldGreaterOrEqualArgumentReturn(int number, int target) {
        int returnArgument = ArgumentValidator.checkGreaterOrEqualNumberTo(number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGreaterOrEqualArgumentWithArgumentNameThrowException(String argumentName, int number, int target) {
        ArgumentValidator.checkGreaterOrEqualNumberTo(argumentName, number, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldGreaterOrEqualArgumentWithArgumentNameReturn(String argumentName, int number, int target) {
        int returnArgument = ArgumentValidator.checkGreaterOrEqualNumberTo(argumentName, number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGreaterOrEqualArgumentWithArgumentAndTargetNameThrowException(String argumentName, int number, String targetName, int target) {
        ArgumentValidator.checkGreaterOrEqualNumberTo(argumentName, number, targetName, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    public void shouldGreaterOrEqualArgumentWithArgumentAndTargetNameReturn(String argumentName, int number, String targetName, int target) {
        int returnArgument = ArgumentValidator.checkGreaterOrEqualNumberTo(argumentName, number, targetName, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_GREATER_ARGUMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGreaterArgumentThrowException(int number, int target) {
        ArgumentValidator.checkGreaterNumberTo(number, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_GREATER_ARGUMENT_DATA_PROVIDER_NAME)
    public void shouldGreaterArgumentReturn(int number, int target) {
        int returnArgument = ArgumentValidator.checkGreaterNumberTo(number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_GREATER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGreaterArgumentWithArgumentNameThrowException(String argumentName, int number, int target) {
        ArgumentValidator.checkGreaterNumberTo(argumentName, number, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_GREATER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldGreaterArgumentWithArgumentNameReturn(String argumentName, int number, int target) {
        int returnArgument = ArgumentValidator.checkGreaterNumberTo(argumentName, number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_GREATER_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGreaterArgumentWithArgumentAndTargetNameThrowException(String argumentName, int number, String targetName, int target) {
        ArgumentValidator.checkGreaterNumberTo(argumentName, number, targetName, target);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_GREATER_ARGUMENT_WITH_ARGUMENT_AND_TARGET_NAME_DATA_PROVIDER_NAME)
    public void shouldGreaterArgumentWithArgumentAndTargetNameReturn(String argumentName, int number, String targetName, int target) {
        int returnArgument = ArgumentValidator.checkGreaterNumberTo(argumentName, number, targetName, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_NEGATIVE_ARGUMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNegativeArgumentThrowException(final int number) {
        ArgumentValidator.checkNotNegativeNumber(number);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_NEGATIVE_ARGUMENT_DATA_PROVIDER_NAME)
    public void shouldNotNegativeArgumentReturn(final int number) {
        int returnArgument = ArgumentValidator.checkNotNegativeNumber(number);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_NEGATIVE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNegativeArgumentWithArgumentNameThrowException(final String argumentName, final int number) {
        ArgumentValidator.checkNotNegativeNumber(argumentName, number);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_NEGATIVE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotNegativeArgumentWithArgumentNameReturn(final String argumentName, final int number) {
        int returnArgument = ArgumentValidator.checkNotNegativeNumber(argumentName, number);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_POSITIVE_ARGUMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldPositiveArgumentThrowException(final int number) {
        ArgumentValidator.checkPositiveNumber(number);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_POSITIVE_ARGUMENT_DATA_PROVIDER_NAME)
    public void shouldPositiveArgumentReturn(final int number) {
        int returnArgument = ArgumentValidator.checkPositiveNumber(number);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_POSITIVE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldPositiveArgumentWithArgumentNameThrowException(final String argumentName, final int number) {
        ArgumentValidator.checkPositiveNumber(argumentName, number);
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_POSITIVE_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldPositiveArgumentWithArgumentNameReturn(final String argumentName, final int number) {
        int returnArgument = ArgumentValidator.checkPositiveNumber(argumentName, number);
        assertThat(returnArgument, is(equalTo(number)));
    }
}
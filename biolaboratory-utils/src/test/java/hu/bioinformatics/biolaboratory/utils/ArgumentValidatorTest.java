package hu.bioinformatics.biolaboratory.utils;

import org.testng.annotations.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

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
        ArgumentValidator.notEmptyVarargs(varargs);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME)
    public void shouldNotEmptyVarargsReturn(String[] varargs) {
        String[] returnVarargs = ArgumentValidator.notEmptyVarargs(varargs);
        assertThat(returnVarargs, is(equalTo(varargs)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_EMPTY_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEmptyVarargsThrowException(String argumentName, String[] varargs) {
        ArgumentValidator.notEmptyVarargs(argumentName, varargs);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_EMPTY_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotEmptyVarargsReturn(String argumentName, String[] varargs) {
        String[] returnVarargs = ArgumentValidator.notEmptyVarargs(argumentName, varargs);
        assertThat(returnVarargs, is(equalTo(varargs)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_NULL_VARARGS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public  void shouldNotNullVarargsThrowException(String[] varargs) {
        ArgumentValidator.notNullVarargs(varargs);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_NULL_VARARGS_DATA_PROVIDER_NAME)
    public void shouldNotNullVarargsReturn(String[] varargs) {
        String[] returnVarargs = ArgumentValidator.notNullVarargs(varargs);
        assertThat(returnVarargs, is(equalTo(varargs)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_NULL_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullVarargsThrowException(String argumentName, String[] varargs) {
        ArgumentValidator.notNullVarargs(argumentName, varargs);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_NULL_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotNullVarargsReturn(String argumentName, String[] varargs) {
        String[] returnVarargs = ArgumentValidator.notNullVarargs(argumentName, varargs);
        assertThat(returnVarargs, is(equalTo(varargs)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEmptyCollectionThrowException(Collection<String> collection) {
        ArgumentValidator.notEmptyCollection(collection);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldNotEmptyCollectionReturn(Collection<String> collection) {
        Collection<String> returnCollection = ArgumentValidator.notEmptyCollection(collection);
        assertThat(returnCollection, is(equalTo(collection)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_EMPTY_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEmptyCollectionThrowException(String argumentName, Collection<String> collection) {
        ArgumentValidator.notEmptyCollection(argumentName, collection);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_EMPTY_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotEmptyCollectionReturn(String argumentName, Collection<String> collection) {
        Collection<String> returnCollection = ArgumentValidator.notEmptyCollection(argumentName, collection);
        assertThat(returnCollection, is(equalTo(collection)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_NULL_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullCollectionThrowException(Collection<String> collection) {
        ArgumentValidator.notNullCollection(collection);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_NULL_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldNotNullCollectionReturn(Collection<String> collection) {
        Collection<String> returnCollection = ArgumentValidator.notNullCollection(collection);
        assertThat(returnCollection, is(equalTo(collection)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_NULL_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullCollectionThrowException(String argumentName, Collection<String> collection) {
        ArgumentValidator.notNullCollection(argumentName, collection);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_NULL_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotNullCollectionReturn(String argumentName, Collection<String> collection) {
        Collection<String> returnCollection = ArgumentValidator.notNullCollection(argumentName, collection);
        assertThat(returnCollection, is(equalTo(collection)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_NULL_ARGUMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullArgumentThrowException(Object argument) {
        ArgumentValidator.notNullArgument(argument);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_NULL_ARGUMENT_DATA_PROVIDER_NAME)
    public void shouldNotNullArgumentReturn(Object argument) {
        Object returnArgument = ArgumentValidator.notNullArgument(argument);
        assertThat(returnArgument, is(equalTo(argument)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_NULL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullArgumentThrowException(String argumentName, Object argument) {
        ArgumentValidator.notNullArgument(argumentName, argument);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_NULL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotNullArgumentReturn(String argumentName, Object argument) {
        Object returnArgument = ArgumentValidator.notNullArgument(argumentName, argument);
        assertThat(returnArgument, is(equalTo(argument)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SMALLER_ARGUMENT_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSmallerArgumentThrowException(int number, int target) {
        ArgumentValidator.smallerArgument(number, target);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SMALLER_ARGUMENT_DATA_PROVIDER)
    public void shouldSmallerArgumentReturn(int number, int target) {
        int returnArgument = ArgumentValidator.smallerArgument(number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SMALLER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSmallerArgumentThrowException(String argumentName, int number, int target) {
        ArgumentValidator.smallerArgument(argumentName, number, target);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SMALLER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    public void shouldSmallerArgumentReturn(String argumentName, int number, int target) {
        int returnArgument = ArgumentValidator.smallerArgument(argumentName, number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SMALLER_OR_EQUAL_ARGUMENT_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSmallerOrEqualArgumentThrowException(int number, int target) {
        ArgumentValidator.smallerOrEqualArgument(number, target);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SMALLER_OR_EQUAL_ARGUMENT_DATA_PROVIDER)
    public void shouldSmallerOrEqualArgumentReturn(int number, int target) {
        int returnArgument = ArgumentValidator.smallerOrEqualArgument(number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldSmallerOrEqualArgumentThrowException(String argumentName, int number, int target) {
        ArgumentValidator.smallerOrEqualArgument(argumentName, number, target);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_SMALLER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    public void shouldSmallerOrEqualArgumentReturn(String argumentName, int number, int target) {
        int returnArgument = ArgumentValidator.smallerOrEqualArgument(argumentName, number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_EQUAL_ARGUMENT_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldEqualArgumentThrowException(int number, int target) {
        ArgumentValidator.equalArgument(number, target);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_EQUAL_ARGUMENT_DATA_PROVIDER)
    public void shouldEqualArgumentReturn(int number, int target) {
        int returnArgument = ArgumentValidator.equalArgument(number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldEqualArgumentThrowException(String argumentName, int number, int target) {
        ArgumentValidator.equalArgument(argumentName, number, target);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    public void shouldEqualArgumentReturn(String argumentName, int number, int target) {
        int returnArgument = ArgumentValidator.equalArgument(argumentName, number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_EQUAL_ARGUMENT_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEqualArgumentThrowException(int number, int target) {
        ArgumentValidator.notEqualArgument(number, target);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_EQUAL_ARGUMENT_DATA_PROVIDER)
    public void shouldNotEqualArgumentReturn(int number, int target) {
        int returnArgument = ArgumentValidator.notEqualArgument(number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEqualArgumentThrowException(String argumentName, int number, int target) {
        ArgumentValidator.notEqualArgument(argumentName, number, target);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_NOT_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    public void shouldNotEqualArgumentReturn(String argumentName, int number, int target) {
        int returnArgument = ArgumentValidator.notEqualArgument(argumentName, number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_GREATER_OR_EQUAL_ARGUMENT_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGreaterOrEqualArgumentThrowException(int number, int target) {
        ArgumentValidator.greaterOrEqualArgument(number, target);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_GREATER_OR_EQUAL_ARGUMENT_DATA_PROVIDER)
    public void shouldGreaterOrEqualArgumentReturn(int number, int target) {
        int returnArgument = ArgumentValidator.greaterOrEqualArgument(number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGreaterOrEqualArgumentThrowException(String argumentName, int number, int target) {
        ArgumentValidator.greaterOrEqualArgument(argumentName, number, target);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_GREATER_OR_EQUAL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    public void shouldGreaterOrEqualArgumentReturn(String argumentName, int number, int target) {
        int returnArgument = ArgumentValidator.greaterOrEqualArgument(argumentName, number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_GREATER_ARGUMENT_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGreaterArgumentThrowException(int number, int target) {
        ArgumentValidator.greaterArgument(number, target);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_GREATER_ARGUMENT_DATA_PROVIDER)
    public void shouldGreaterArgumentReturn(int number, int target) {
        int returnArgument = ArgumentValidator.greaterArgument(number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.INVALID_GREATER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldGreaterArgumentThrowException(String argumentName, int number, int target) {
        ArgumentValidator.greaterArgument(argumentName, number, target);
        fail();
    }

    @Test(dataProvider = ArgumentValidatorTestDataProvider.VALID_GREATER_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER)
    public void shouldGreaterArgumentReturn(String argumentName, int number, int target) {
        int returnArgument = ArgumentValidator.greaterArgument(argumentName, number, target);
        assertThat(returnArgument, is(equalTo(number)));
    }
}
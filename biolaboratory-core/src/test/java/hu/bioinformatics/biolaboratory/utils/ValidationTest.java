package hu.bioinformatics.biolaboratory.utils;

import org.testng.annotations.Test;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

/**
 * Unit tests for {@link Validation}.
 *
 * @author Attila Radi
 */
@Test(dataProviderClass = ValidationTestDataProvider.class)
public class ValidationTest {

    @Test(dataProvider = ValidationTestDataProvider.INVALID_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEmptyVarargsThrowException(String[] varargs) {
        Validation.notEmptyVarargs(varargs);
        fail();
    }

    @Test(dataProvider = ValidationTestDataProvider.VALID_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME)
    public void shouldNotEmptyVarargsReturn(String[] varargs) {
        String[] returnVarargs = Validation.notEmptyVarargs(varargs);
        assertThat(returnVarargs, is(equalTo(varargs)));
    }

    @Test(dataProvider = ValidationTestDataProvider.INVALID_NOT_EMPTY_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEmptyVarargsThrowException(String argumentName, String[] varargs) {
        Validation.notEmptyVarargs(argumentName, varargs);
        fail();
    }

    @Test(dataProvider = ValidationTestDataProvider.VALID_NOT_EMPTY_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotEmptyVarargsReturn(String argumentName, String[] varargs) {
        String[] returnVarargs = Validation.notEmptyVarargs(argumentName, varargs);
        assertThat(returnVarargs, is(equalTo(varargs)));
    }

    @Test(dataProvider = ValidationTestDataProvider.INVALID_NOT_NULL_VARARGS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public  void shouldNotNullVarargsThrowException(String[] varargs) {
        Validation.notNullVarargs(varargs);
        fail();
    }

    @Test(dataProvider = ValidationTestDataProvider.VALID_NOT_NULL_VARARGS_DATA_PROVIDER_NAME)
    public void shouldNotNullVarargsReturn(String[] varargs) {
        String[] returnVarargs = Validation.notNullVarargs(varargs);
        assertThat(returnVarargs, is(equalTo(varargs)));
    }

    @Test(dataProvider = ValidationTestDataProvider.INVALID_NOT_NULL_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullVarargsThrowException(String argumentName, String[] varargs) {
        Validation.notNullVarargs(argumentName, varargs);
        fail();
    }

    @Test(dataProvider = ValidationTestDataProvider.VALID_NOT_NULL_VARARGS_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotNullVarargsReturn(String argumentName, String[] varargs) {
        String[] returnVarargs = Validation.notNullVarargs(argumentName, varargs);
        assertThat(returnVarargs, is(equalTo(varargs)));
    }

    @Test(dataProvider = ValidationTestDataProvider.INVALID_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEmptyCollectionThrowException(Collection<String> collection) {
        Validation.notEmptyCollection(collection);
        fail();
    }

    @Test(dataProvider = ValidationTestDataProvider.VALID_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldNotEmptyCollectionReturn(Collection<String> collection) {
        Collection<String> returnCollection = Validation.notEmptyCollection(collection);
        assertThat(returnCollection, is(equalTo(collection)));
    }

    @Test(dataProvider = ValidationTestDataProvider.INVALID_NOT_EMPTY_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotEmptyCollectionThrowException(String argumentName, Collection<String> collection) {
        Validation.notEmptyCollection(argumentName, collection);
        fail();
    }

    @Test(dataProvider = ValidationTestDataProvider.VALID_NOT_EMPTY_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotEmptyCollectionReturn(String argumentName, Collection<String> collection) {
        Collection<String> returnCollection = Validation.notEmptyCollection(argumentName, collection);
        assertThat(returnCollection, is(equalTo(collection)));
    }

    @Test(dataProvider = ValidationTestDataProvider.INVALID_NOT_NULL_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullCollectionThrowException(Collection<String> collection) {
        Validation.notNullCollection(collection);
        fail();
    }

    @Test(dataProvider = ValidationTestDataProvider.VALID_NOT_NULL_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldNotNullCollectionReturn(Collection<String> collection) {
        Collection<String> returnCollection = Validation.notNullCollection(collection);
        assertThat(returnCollection, is(equalTo(collection)));
    }

    @Test(dataProvider = ValidationTestDataProvider.INVALID_NOT_NULL_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullCollectionThrowException(String argumentName, Collection<String> collection) {
        Validation.notNullCollection(argumentName, collection);
        fail();
    }

    @Test(dataProvider = ValidationTestDataProvider.VALID_NOT_NULL_COLLECTION_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotNullCollectionReturn(String argumentName, Collection<String> collection) {
        Collection<String> returnCollection = Validation.notNullCollection(argumentName, collection);
        assertThat(returnCollection, is(equalTo(collection)));
    }

    @Test(dataProvider = ValidationTestDataProvider.INVALID_NOT_NULL_ARGUMENT_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullArgumentThrowException(Object argument) {
        Validation.notNullArgument(argument);
        fail();
    }

    @Test(dataProvider = ValidationTestDataProvider.VALID_NOT_NULL_ARGUMENT_DATA_PROVIDER_NAME)
    public void shouldNotNullArgumentReturn(Object argument) {
        Object returnArgument = Validation.notNullArgument(argument);
        assertThat(returnArgument, is(equalTo(argument)));
    }

    @Test(dataProvider = ValidationTestDataProvider.INVALID_NOT_NULL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldNotNullArgumentThrowException(String argumentName, Object argument) {
        Validation.notNullArgument(argumentName, argument);
        fail();
    }

    @Test(dataProvider = ValidationTestDataProvider.VALID_NOT_NULL_ARGUMENT_WITH_ARGUMENT_NAME_DATA_PROVIDER_NAME)
    public void shouldNotNullArgumentReturn(String argumentName, Object argument) {
        Object returnArgument = Validation.notNullArgument(argumentName, argument);
        assertThat(returnArgument, is(equalTo(argument)));
    }
}
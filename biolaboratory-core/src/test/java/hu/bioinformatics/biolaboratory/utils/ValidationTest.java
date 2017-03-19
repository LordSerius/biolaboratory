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

    @Test(dataProvider = ValidationTestDataProvider.INVALID_VALIDATE_VARARGS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public  void shouldValidateVarargsThrowException(String[] varargs) {
        Validation.validateVarargs(varargs);
        fail();
    }

    @Test(dataProvider = ValidationTestDataProvider.VALID_VALIDATE_VARARGS_DATA_PROVIDER_NAME)
    public void shouldValidateVarargsReturn(String[] varargs) {
        String[] returnVarargs = Validation.validateVarargs(varargs);
        assertThat(returnVarargs, is(equalTo(varargs)));
    }

    @Test(dataProvider = ValidationTestDataProvider.INVALID_VALIDATE_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldValidateNotEmptyVarargsThrowException(String[] varargs) {
        Validation.validateNotEmptyVarargs(varargs);
        fail();
    }

    @Test(dataProvider = ValidationTestDataProvider.VALID_VALIDATE_NOT_EMPTY_VARARGS_DATA_PROVIDER_NAME)
    public void shouldValidateNotEmptyVarargsReturn(String[] varargs) {
        String[] returnVarargs = Validation.validateNotEmptyVarargs(varargs);
        assertThat(returnVarargs, is(equalTo(varargs)));
    }

    @Test(dataProvider = ValidationTestDataProvider.INVALID_VALIDATE_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldValidateCollectionThrowException(Collection<String> collection) {
        Validation.validateCollection(collection);
        fail();
    }

    @Test(dataProvider = ValidationTestDataProvider.VALID_VALIDATE_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldValidateCollectionReturn(Collection<String> collection) {
        Collection<String> returnCollection = Validation.validateCollection(collection);
        assertThat(returnCollection, is(equalTo(collection)));
    }

    @Test(dataProvider = ValidationTestDataProvider.INVALID_VALIDATE_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldValidateNotEmptyCollectionThrowException(Collection<String> collection) {
        Validation.validateNotEmptyCollection(collection);
        fail();
    }

    @Test(dataProvider = ValidationTestDataProvider.VALID_VALIDATE_NOT_EMPTY_COLLECTION_DATA_PROVIDER_NAME)
    public void shouldValidateNotEmptyCollectionReturn(Collection<String> collection) {
        Collection<String> returnCollection = Validation.validateNotEmptyCollection(collection);
        assertThat(returnCollection, is(equalTo(collection)));
    }
}
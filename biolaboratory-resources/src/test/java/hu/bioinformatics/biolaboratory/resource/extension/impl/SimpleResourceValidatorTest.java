package hu.bioinformatics.biolaboratory.resource.extension.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

/**
 * Unit test for {@link SimpleResourceValidator}.
 *
 * @author Attila Radi
 */
public class SimpleResourceValidatorTest {

    private SimpleResourceValidator simpleResourceValidator;

    public static final String INVALID_VALIDATE_DATA_PROVIDER_NAME = "invalidValidateDataProvider";

    @DataProvider(name = INVALID_VALIDATE_DATA_PROVIDER_NAME)
    private Object[][] invalidValidateDataProvider() {
        return new Object[][] {
                { null },
                { "" },
                { " " }
        };
    }

    public static final String VALID_VALIDATE_DATA_PROVIDER_NAME = "validValidateDataProvider";

    @DataProvider(name = VALID_VALIDATE_DATA_PROVIDER_NAME)
    private Object[][] validValidateDataProvider() {
        return new Object[][] {
                { "resource" }
        };
    }

    @BeforeMethod
    public void setUp() {
        simpleResourceValidator = new SimpleResourceValidator();
    }

    @Test(dataProvider = INVALID_VALIDATE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldValidateThrowException(String fastaPath) {
        simpleResourceValidator.validate(fastaPath);
        fail();
    }

    @Test(dataProvider = VALID_VALIDATE_DATA_PROVIDER_NAME)
    public void shouldValidateReturn(String fastaPath) {
        simpleResourceValidator.validate(fastaPath);
    }
}
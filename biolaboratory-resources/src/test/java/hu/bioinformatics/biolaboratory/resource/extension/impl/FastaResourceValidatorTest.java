package hu.bioinformatics.biolaboratory.resource.extension.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

/**
 * Unit test for {@link FastaResourceValidator}.
 *
 * @author Attila Radi
 */
public class FastaResourceValidatorTest {

    private FastaResourceValidator fastaResourceValidator;

    public static final String INVALID_VALIDATE_DATA_PROVIDER_NAME = "invalidValidateDataProvider";

    @DataProvider(name = INVALID_VALIDATE_DATA_PROVIDER_NAME)
    private Object[][] invalidValidateDataProvider() {
        return new Object[][] {
                { null },
                { "" },
                { " " },
                { "something" },
                { "file.fast" },
                { ".fas" },
                { ".fasta" }
        };
    }

    public static final String VALID_VALIDATE_DATA_PROVIDER_NAME = "validValidateDataProvider";

    @DataProvider(name = VALID_VALIDATE_DATA_PROVIDER_NAME)
    private Object[][] validValidateDataProvider() {
        return new Object[][] {
                { "file.fas" },
                { "file.fasta" }
        };
    }

    @BeforeMethod
    public void setUp() {
        fastaResourceValidator = new FastaResourceValidator();
    }

    @Test(dataProvider = INVALID_VALIDATE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldValidateThrowException(String fastaPath) {
        fastaResourceValidator.validate(fastaPath);
    }

    @Test(dataProvider = VALID_VALIDATE_DATA_PROVIDER_NAME)
    public void shouldValidateReturn(String fastaPath) {
        fastaResourceValidator.validate(fastaPath);
    }
}
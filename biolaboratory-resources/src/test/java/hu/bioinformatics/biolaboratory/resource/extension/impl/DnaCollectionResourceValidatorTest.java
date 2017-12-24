package hu.bioinformatics.biolaboratory.resource.extension.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.fail;

/**
 * Test classes for {@link DnaCollectionResourceValidator}
 *
 * @author Attila Radi
 */
public class DnaCollectionResourceValidatorTest {

    private DnaCollectionResourceValidator dnaCollectionResourceValidator;

    private static final String INVALID_FILE_PATH_DATA_PROVIDER_NAME = "invalidFilePathDataProvider";

    @DataProvider(name = INVALID_FILE_PATH_DATA_PROVIDER_NAME)
    private Object[][] invalidFilePathDataProvider() {
        return new Object[][] {
                { null },
                { "" },
                { " " },
                { ".dnacol" },
                { "biolaboratory" + File.separator + "ecoli.dn" }
        };
    }

    private static final String VALID_FILE_PATH_DATA_PROVIDER_NAME = "validFilePathDataProvider";

    @DataProvider(name = VALID_FILE_PATH_DATA_PROVIDER_NAME)
    private Object[][] validFilePathDataProvider() {
        return new Object[][] {
                { "c:" + File.separator + "biolaboratory" + File.separator + "ecoli-variation.dnacol" },
                { File.separator + "biolaboratory" + File.separator + "ecoli-variation.dnacol" },
                { "biolaboratory" + File.separator + "ecoli-variation.dnacol" },
                { "ecoli-variation.dnacol" }
        };
    }

    @BeforeMethod
    public void setUp() {
        dnaCollectionResourceValidator = new DnaCollectionResourceValidator();
    }

    @Test(dataProvider = INVALID_FILE_PATH_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFailValidation(String filePath) {
        dnaCollectionResourceValidator.validate(filePath);
        fail();
    }

    @Test(dataProvider = VALID_FILE_PATH_DATA_PROVIDER_NAME)
    public void shouldPassValidation(String filePath) {
        dnaCollectionResourceValidator.validate(filePath);
    }
}
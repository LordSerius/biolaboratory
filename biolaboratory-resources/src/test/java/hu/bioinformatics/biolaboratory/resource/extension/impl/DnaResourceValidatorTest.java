package hu.bioinformatics.biolaboratory.resource.extension.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Test cases for {@link DnaResourceValidator} class.
 *
 * @author Attila Radi
 */
public class DnaResourceValidatorTest {

    private DnaResourceValidator dnaResourceValidator;

    private static final String INVALID_FILE_PATH_DATA_PROVIDER_NAME = "invalidFilePathDataProvider";
    
    @DataProvider(name = INVALID_FILE_PATH_DATA_PROVIDER_NAME)
    private Object[][] invalidFilePathDataProvider() {
        return new Object[][] {
            { null },
            { "" },
            { " " },
            { ".dna" },
            { "biolaboratory" + File.separator + "ecoli.dn" }
        };
    }

    private static final String VALID_FILE_PATH_DATA_PROVIDER_NAME = "validFilePathDataProvider";

    @DataProvider(name = VALID_FILE_PATH_DATA_PROVIDER_NAME)
    private Object[][] validFilePathDataProvider() {
        return new Object[][] {
                { "c:" + File.separator + "biolaboratory" + File.separator + "ecoli.dna" },
                { File.separator + "biolaboratory" + File.separator + "ecoli.dna" },
                { "biolaboratory" + File.separator + "ecoli.dna" },
                { "ecoli.dna" }
        };
    }

    @BeforeMethod
    public void setUp() {
        dnaResourceValidator = new DnaResourceValidator();
    }

    @Test(dataProvider = INVALID_FILE_PATH_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFailValidation(String filePath) {
        dnaResourceValidator.validate(filePath);
    }
    
    @Test(dataProvider = VALID_FILE_PATH_DATA_PROVIDER_NAME)
    public void shouldPassValidation(String filePath) {
        dnaResourceValidator.validate(filePath);
    }
}

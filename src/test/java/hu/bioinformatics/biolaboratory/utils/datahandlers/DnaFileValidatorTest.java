package hu.bioinformatics.biolaboratory.utils.datahandlers;

import static org.testng.Assert.fail;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaFileValidator;

/**
 * Test cases for {@link DnaFileValidator} class.
 *
 * @author Attila Radi
 */
public class DnaFileValidatorTest {

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
    
    @Test(dataProvider = INVALID_FILE_PATH_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldFailValidation(String filePath) {
        DnaFileValidator.validateFilePath(filePath);
        fail();
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
    
    @Test(dataProvider = VALID_FILE_PATH_DATA_PROVIDER_NAME)
    public void shouldPassValidation(String filePath) {
        DnaFileValidator.validateFilePath(filePath);
    }
}

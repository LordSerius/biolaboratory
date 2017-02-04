package hu.bioinformatics.biolaboratory.utils.datahandlers;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

/**
 * Validate the file paths for {@link Dna}-s.
 * 
 * @author Attila Radi
 *
 */
public class DnaFileValidator {

    public static final String DNA_FILE_EXTENSION = "dna";
    
    private static final Pattern dnaFileExtensionValidator = Pattern.compile(".+\\." + DNA_FILE_EXTENSION);
    
    /**
     * The given file path should have .dna extension.
     * 
     * @param filePath The checkable file path.
     * @throws IllegalArgumentException if the file path is not well formed.
     */
    static void validateFilePath(String filePath) {
        Preconditions.checkArgument(StringUtils.isNotBlank(filePath), "The input file should not be blank");
        Preconditions.checkArgument(dnaFileExtensionValidator.matcher(filePath).matches(), "The input file should .dna extension");
    }
}

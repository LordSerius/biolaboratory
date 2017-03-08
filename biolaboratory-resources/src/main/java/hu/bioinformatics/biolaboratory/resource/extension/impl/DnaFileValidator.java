package hu.bioinformatics.biolaboratory.resource.extension.impl;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceValidator;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Validate the file paths for {@link hu.bioinformatics.biolaboratory.sequence.dna.Dna}-s.
 * 
 * @author Attila Radi
 *
 */
public class DnaFileValidator implements ResourceValidator {

    private static final String DNA_FILE_EXTENSION = "dna";
    
    private static final Pattern dnaFileExtensionValidator = Pattern.compile(".+\\." + DNA_FILE_EXTENSION);
    
    /**
     * The given file path should have .dna extension.
     * 
     * @param filePath The checkable file path.
     * @throws IllegalArgumentException if the file path is not well formed.
     */
    @Override
    public void validate(final String filePath) {
        Preconditions.checkArgument(StringUtils.isNotBlank(filePath), "The input file should not be blank");
        Preconditions.checkArgument(dnaFileExtensionValidator.matcher(filePath).matches(), "The input file should .dna extension");
    }
}

package hu.bioinformatics.biolaboratory.utils.resource.extension.impl;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.utils.resource.extension.ResourceValidator;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Validates FASTA resource paths.
 *
 * @author Attila Radi
 */
public class FastaResourceValidator implements ResourceValidator {
    private static final Pattern FASTA_EXTENSION = Pattern.compile(".+\\.fas(ta)?");

    @Override
    public void validate(final String resourcePath) {
        Preconditions.checkArgument(StringUtils.isNotBlank(resourcePath), "Resource path sould not be null");
        Preconditions.checkArgument(FASTA_EXTENSION.matcher(resourcePath).matches(), "Resource should have .fas or .fasta extension");
    }
}

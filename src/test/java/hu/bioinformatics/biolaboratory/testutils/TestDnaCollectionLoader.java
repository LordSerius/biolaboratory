package hu.bioinformatics.biolaboratory.testutils;

import com.google.common.base.Preconditions;
import com.google.inject.Guice;
import hu.bioinformatics.biolaboratory.guice.GuiceModule;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.DnaCollectors;
import hu.bioinformatics.biolaboratory.utils.resource.ResourceReader;
import hu.bioinformatics.biolaboratory.utils.resource.RowReader;
import hu.bioinformatics.biolaboratory.utils.resource.file.FileResourceProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Loads the target file and translates it to a collection of {@link Dna}s
 *
 * @author Attila Radi
 */
public class TestDnaCollectionLoader {

    private static final Pattern whitespacePattern = Pattern.compile("\\s+");

    private final ResourceReader resourceReader;

    @Inject
    public TestDnaCollectionLoader(@Named(value = GuiceModule.ROW_READER_NAME) ResourceReader resourceReader) {
        this.resourceReader = Validate.notNull(resourceReader, "Resource reader should not be null");
    }

    /**
     * Lookup the target file, reads it, and returns with a {@link Set} of {@link Dna}.
     *
     * @param fileName The name of the file.
     * @return The contained {@link Dna} set of the file.
     */
    public Set<Dna> loadDnaSetFromFile(String fileName) {
        return DnaCollectors.toDnaSet(whitespacePattern.split(readFile(fileName)));
    }

    /**
     * Lookup the target file, reads it, and returns with a {@link List} of {@link Dna}.
     *
     * @param fileName The name of the file.
     * @return The contained {@link Dna} list of the file.
     */
    public List<Dna> loadDnaListFromFile(String fileName) {
        return DnaCollectors.toDnaList(whitespacePattern.split(readFile(fileName)));
    }

    private String readFile(String fileName) {
        Preconditions.checkArgument(StringUtils.isNotBlank(fileName), "The input file should not be blank");
        String filePath = ResourceLoader.loadResourceFile(fileName);
        return resourceReader.read(filePath).get(0);
    }
}

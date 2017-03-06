package hu.bioinformatics.biolaboratory.testutils;

import com.google.common.base.Preconditions;
import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.collectors.DnaCollectors;
import hu.bioinformatics.biolaboratory.utils.resource.CommentedString;
import hu.bioinformatics.biolaboratory.utils.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.utils.resource.read.ResourceReader;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Loads the target file and translates it to a collection of {@link Dna}s
 *
 * @author Attila Radi
 */
public class TestDnaCollectionLoader {

    private static final Pattern whitespacePattern = Pattern.compile("\\s+");

    private final ResourceReader resourceReader;
    private final ResourceLocalizer resourceLocalizer;

    @Inject
    public TestDnaCollectionLoader(@Named(GuiceCoreModule.ROW_READER_NAME) ResourceReader resourceReader,
                                   ResourceLocalizer resourceLocalizer) {
        this.resourceReader = notNull(resourceReader, "Resource reader should not be null");
        this.resourceLocalizer = notNull(resourceLocalizer);
    }

    /**
     * Lookup the target file, reads it, and returns with a {@link Set} of {@link Dna}.
     *
     * @param fileName The name of the file.
     * @return The contained {@link Dna} set of the file.
     */
    public Set<Dna> loadDnaSetFromFile(String fileName) {
        return DnaCollectors.stringToDnaSet(whitespacePattern.split(readFile(fileName)));
    }

    /**
     * Lookup the target file, reads it, and returns with a {@link List} of {@link Dna}.
     *
     * @param fileName The name of the file.
     * @return The contained {@link Dna} list of the file.
     */
    public List<Dna> loadDnaListFromFile(String fileName) {
        return DnaCollectors.stringToDnaList(whitespacePattern.split(readFile(fileName)));
    }

    private String readFile(String fileName) {
        Preconditions.checkArgument(StringUtils.isNotBlank(fileName), "The input file should not be blank");
        String filePath = resourceLocalizer.localizeResource(fileName);
        return resourceReader.read(filePath).stream()
                .map(CommentedString::getString)
                .collect(Collectors.toList())
                .get(0);
    }
}

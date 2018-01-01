package hu.bioinformatics.biolaboratory.utils.datahandlers.implementation;

import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.resource.read.ResourceReader;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.datastructures.CommentedString;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaSetLoader;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Loads a set of {@link Dna}s from a one liner file. The elements are separated by whitespace characters.
 *
 * @author Attila Radi
 */
public class DnaSetRowLoader extends DnaSetLoader {
    private final static Pattern WHITESPACE_SEPARATOR = Pattern.compile("\\s+");

    @Inject
    public DnaSetRowLoader(final ResourceLocalizer resourceLocalizer,
                           @Named(GuiceResourceModule.ROW_READER_NAME) final ResourceReader resourceReader) {
        super(resourceLocalizer, resourceReader);
    }

    @Override
    protected Set<Dna> convert(final List<CommentedString> lines) {
        CommentedString row = lines.get(0);
        return Arrays.stream(WHITESPACE_SEPARATOR.split(row.getString()))
                .map(Dna::build)
                .collect(Collectors.toCollection(HashSet::new));
    }
}

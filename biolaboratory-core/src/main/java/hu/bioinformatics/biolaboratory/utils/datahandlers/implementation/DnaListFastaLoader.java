package hu.bioinformatics.biolaboratory.utils.datahandlers.implementation;

import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.resource.read.ResourceReader;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.CommentedString;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaListLoader;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Loads a list of {@link Dna}s from FASTA file.
 *
 * @author Attila Radi
 */
public class DnaListFastaLoader extends DnaListLoader {
    @Inject
    public DnaListFastaLoader(final ResourceLocalizer resourceLocalizer,
                              @Named(GuiceResourceModule.FASTA_READER_NAME) final ResourceReader resourceReader) {
        super(resourceLocalizer, resourceReader);
    }

    @Override
    protected final List<Dna> convert(final List<CommentedString> lines) {
        return lines.stream()
                .map(commentedLine -> Dna.build(commentedLine.getComment(), commentedLine.getString()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

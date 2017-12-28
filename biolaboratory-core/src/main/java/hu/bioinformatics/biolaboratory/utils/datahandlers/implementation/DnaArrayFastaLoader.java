package hu.bioinformatics.biolaboratory.utils.datahandlers.implementation;

import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.resource.read.ResourceReader;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.sequence.dna.DnaArray;
import hu.bioinformatics.biolaboratory.utils.CommentedString;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaArrayLoader;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Loads a {@link DnaArray} from a FASTA file.
 *
 * @author Attila Radi
 */
public class DnaArrayFastaLoader extends DnaArrayLoader {

    @Inject
    public DnaArrayFastaLoader(final ResourceLocalizer resourceLocalizer,
                               @Named(GuiceResourceModule.FASTA_READER_NAME) final ResourceReader resourceReader) {
        super(resourceLocalizer, resourceReader);
    }

    @Override
    protected final DnaArray convert(final List<CommentedString> lines) {
        List<Dna> dnaList = lines.stream()
                .map(commentedLine -> Dna.build(commentedLine.getComment(), commentedLine.getString()))
                .collect(Collectors.toList());
        return DnaArray.build(dnaList);
    }
}

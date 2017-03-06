package hu.bioinformatics.biolaboratory.utils.datahandlers.implementation;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaListLoader;
import hu.bioinformatics.biolaboratory.utils.resource.CommentedString;
import hu.bioinformatics.biolaboratory.utils.resource.read.ResourceReader;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Loads a list of {@link Dna} from FASTA file
 *
 * @author Attila Radi
 */
public class DnaListFastaLoader extends DnaListLoader {
    @Inject
    public DnaListFastaLoader(@Named(GuiceCoreModule.FASTA_READER_NAME) final ResourceReader resourceReader) {
        super(resourceReader);
    }

    @Override
    protected List<Dna> convert(List<CommentedString> lines) {
        return lines.stream()
                .map(commentedLine -> Dna.build(commentedLine.getComment(), commentedLine.getString()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

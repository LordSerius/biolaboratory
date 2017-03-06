package hu.bioinformatics.biolaboratory.utils.datahandlers.implementation;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.sequence.dna.DnaArray;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaArrayLoader;
import hu.bioinformatics.biolaboratory.utils.resource.CommentedString;
import hu.bioinformatics.biolaboratory.utils.resource.read.ResourceReader;

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
    public DnaArrayFastaLoader(@Named(GuiceCoreModule.FASTA_READER_NAME) ResourceReader resourceReader) {
        super(resourceReader);
    }

    @Override
    protected DnaArray convert(List<CommentedString> lines) {
        List<Dna> dnaList = lines.stream()
                .map(commentedLine -> Dna.build(commentedLine.getComment(), commentedLine.getString()))
                .collect(Collectors.toList());
        return DnaArray.build(dnaList);
    }
}

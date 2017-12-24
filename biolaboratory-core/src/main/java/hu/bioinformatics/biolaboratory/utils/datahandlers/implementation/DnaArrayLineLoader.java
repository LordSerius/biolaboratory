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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Loads a {@link DnaArray} from a line seperated file.
 *
 * @author Attila Radi
 */
public class DnaArrayLineLoader extends DnaArrayLoader {
    @Inject
    public DnaArrayLineLoader(final ResourceLocalizer resourceLocalizer,
                              @Named(GuiceResourceModule.DNA_COLLECTION_LINE_READER_NAME) final ResourceReader resourceReader) {
        super(resourceLocalizer, resourceReader);
    }

    @Override
    protected DnaArray convert(List<CommentedString> lines) {
        return DnaArray.build(lines.stream()
                                    .map(line -> Dna.build(line.getString()))
                                    .collect(Collectors.toCollection(ArrayList::new)));
    }
}

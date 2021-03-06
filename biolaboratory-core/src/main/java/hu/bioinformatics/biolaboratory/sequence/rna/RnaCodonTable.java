package hu.bioinformatics.biolaboratory.sequence.rna;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import hu.bioinformatics.biolaboratory.guice.GuiceProductionModule;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.resource.read.LineReader;
import hu.bioinformatics.biolaboratory.resource.read.ResourceReader;
import hu.bioinformatics.biolaboratory.sequence.protein.AminoAcid;
import hu.bioinformatics.biolaboratory.utils.ArgumentValidator;
import hu.bioinformatics.biolaboratory.utils.datastructures.CommentedString;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotNullArgument;

/**
 * Contains the coding RNA sequences and its decoded amino acid representation. The return value is always an
 * {@link Optional}, where the STOP codons decodes an empty value.
 *
 * @author Attila Radi
 */
public class RnaCodonTable {

    private static final String RNA_CODON_TABLE_RESOURCE_NAME = "rna/rna_codon_table.tsv";
    private static final String STOP_CODON_STRING = "STOP";
    private static final Pattern TABULATOR_REGEX_PATTERN = Pattern.compile("\t");

    private static final Map<Rna, Optional<AminoAcid>> codonTable = initializeCodonTable();

    static final Rna START_CODON = Rna.build("AUG");

    private static Map<Rna, Optional<AminoAcid>> initializeCodonTable() {
        return new HashMap<>(getRawCodonTable().stream()
                .map(CommentedString::getString)
                .map(TABULATOR_REGEX_PATTERN::split)
                .map(rnaAminoAcid -> new AbstractMap.SimpleEntry<>(Rna.build(rnaAminoAcid[0]),
                        STOP_CODON_STRING.equalsIgnoreCase(rnaAminoAcid[1])
                                ? null
                                : AminoAcid.findAminoAcid(rnaAminoAcid[1])))
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), Optional.ofNullable(entry.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    private static List<CommentedString> getRawCodonTable() {
        Injector injector = Guice.createInjector(new GuiceResourceModule(), new GuiceProductionModule());
        ResourceReader resourceReader = injector.getInstance(LineReader.class);
        ResourceLocalizer resourceLocalizer = injector.getInstance(Key.get(ResourceLocalizer.class, Names.named(GuiceProductionModule.LOCAL_RESOURCE_LOCALIZER_NAME)));

        return resourceReader.read(resourceLocalizer.localizeResource(RNA_CODON_TABLE_RESOURCE_NAME));
    }

    /**
     * Return with the {@link AminoAcid} representation of the the given codon. The given codon should be 3 length
     * long {@link Rna} sequence.
     *
     * @param rna A 3 length long {@link Rna} sequence
     * @return The decoded amino acid wrapped in an {@link Optional} object. If it is a STOP codon the return value is
     *          an empty {@link Optional}.
     * @throws IllegalArgumentException If {@link Rna} is null or its sequence is not 3.
     */
    public static Optional<AminoAcid> lookup(final Rna rna) {
        checkNotNullArgument("RNA", rna);
        ArgumentValidator.checkEqualNumberTo("RNA length", rna.getSequenceLength(), 3);
        return codonTable.get(rna);
    }
}

package hu.bioinformatics.biolaboratory.sequence.rna;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;
import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.sequence.protein.AminoAcid;
import hu.bioinformatics.biolaboratory.utils.resource.CommentedLine;
import hu.bioinformatics.biolaboratory.utils.resource.read.LineReader;
import hu.bioinformatics.biolaboratory.utils.resource.extension.ResourceLocalizer;
import hu.bioinformatics.biolaboratory.utils.resource.read.ResourceReader;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        return Maps.newHashMap(getRawCodonTable().stream()
                .map(CommentedLine::getLine)
                .map(TABULATOR_REGEX_PATTERN::split)
                .map(rnaAminoAcid -> new AbstractMap.SimpleEntry<>(Rna.build(rnaAminoAcid[0]),
                        STOP_CODON_STRING.equalsIgnoreCase(rnaAminoAcid[1])
                                ? null
                                : AminoAcid.findAminoAcid(rnaAminoAcid[1])))
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), Optional.ofNullable(entry.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    private static List<CommentedLine> getRawCodonTable() {
        Injector injector = Guice.createInjector(new GuiceCoreModule(), new GuiceResourceModule());
        ResourceReader resourceReader = injector.getInstance(LineReader.class);
        ResourceLocalizer resourceLocalizer = injector.getInstance(ResourceLocalizer.class);

        List<CommentedLine> read = resourceReader.read(resourceLocalizer.localizeResource(RNA_CODON_TABLE_RESOURCE_NAME));
        return read;
    }

    /**
     * Return with the {@link AminoAcid} representation of the the given codon. The given codon should be 3 length
     * long {@link Rna} sequence.
     *
     * @param rna A 3 length long {@link Rna} sequence
     * @return The decoded amino acid wraped in an {@link Optional} object. If it is a STOP codon the return value is
     *          an empty {@link Optional}.
     */
    public static Optional<AminoAcid> lookup(final Rna rna) {
        Preconditions.checkArgument(rna != null, "RNA should not be null");
        Preconditions.checkArgument(rna.getSequenceLength() == 3, "RNA length should be 3");
        return codonTable.get(rna);
    }
}

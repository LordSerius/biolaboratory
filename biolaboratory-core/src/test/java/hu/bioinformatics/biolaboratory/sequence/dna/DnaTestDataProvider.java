package hu.bioinformatics.biolaboratory.sequence.dna;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import hu.bioinformatics.biolaboratory.sequence.rna.Rna;
import hu.bioinformatics.biolaboratory.utils.collectors.DnaCollectors;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaLoader;
import org.testng.annotations.DataProvider;

import javax.inject.Inject;

/**
 * Test data provider for {@link DnaTest} test class.
 *
 * @author Attila Radi
 */
public class DnaTestDataProvider {

    @Inject
    private DnaLoader testDnaLoader;

    static final String INVALID_DNA_SEQUENCES_DATA_PROVIDER_NAME = "invalidDnaSequencesDataProvider";

    @DataProvider(name = INVALID_DNA_SEQUENCES_DATA_PROVIDER_NAME)
    private Object[][] invalidDnaSequencesDataProvider() {
        return new Object[][] {
                { "", null },
                { "", "" },
                { "", "            " },
                { "", "LOL" },
                { "", "ACGU" },
                { null, "ACGT" }
        };
    }

    static final String INVALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME = "invalidBuildFromElementsDataProvider";

    @DataProvider(name = INVALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    static private Object[][] invalidBuildFromElementsDataProvider() {
        return new Object[][] {
                { null },
                { new DnaNucleotide[0] },
                { new DnaNucleotide[] { DnaNucleotide.ADENINE, null } }
        };
    }

    static final String INVALID_BUILD_FROM_ELEMENT_LIST_DATA_PROVIDER_NAME = "invalidBuildFromElementListDataProvider";

    @DataProvider(name = INVALID_BUILD_FROM_ELEMENT_LIST_DATA_PROVIDER_NAME)
    static private Object[][] invalidBuildFromElementListDataProvider() {
        return new Object[][] {
                { null },
                {ImmutableList.of()},
                { Lists.newArrayList(DnaNucleotide.ADENINE, null) }
        };
    }
    
    static final String VALID_DNA_SEQUENCES_DATA_PROVIDER_NAME = "validDnaSequencesDataProvider";

    @DataProvider(name = VALID_DNA_SEQUENCES_DATA_PROVIDER_NAME)
    private Object[][] validDnaSequencesDataProvider() {
        return new Object[][] {
                { "", "A", "", "A" },
                { " ", "G", "", "G" },
                { "name", "T", "name", "T" },
                { " name", "C", "name", "C" },
                { "name ", "AGTC", "name", "AGTC" },
                { " name ", "aGtC", "name", "AGTC" },
                { "", "        agtc            ", "", "AGTC" }
        };
    }

    static final String VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME = "validBuildFromElementsDataProvider";

    @DataProvider(name = VALID_BUILD_FROM_ELEMENTS_DATA_PROVIDER_NAME)
    static private Object[][] validBuildFromElementsDataProvider() {
        return new Object[][] {
                { new DnaNucleotide[] { DnaNucleotide.ADENINE }, "A" },
                { new DnaNucleotide[] { DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE, DnaNucleotide.GUANINE, DnaNucleotide.THYMINE }, "ACGT" }
        };
    }

    static final String INVALID_GENERATE_PATTERN_DNAS_DATA_PROVIDER_NAME = "invalidGeneratePatternDnasDataProvider";

    @DataProvider(name = INVALID_GENERATE_PATTERN_DNAS_DATA_PROVIDER_NAME)
    static private Object[][] invalidGeneratePatternDnasDataProvider() {
        return new Object[][] {
                { -1 },
                { 0 }
        };
    }

    static final String VALID_GENERATE_PATTERN_DNAS_DATA_PROVIDER_NAME = "validGeneratePatternDnasDataProvider";

    @DataProvider(name = VALID_GENERATE_PATTERN_DNAS_DATA_PROVIDER_NAME)
    static private Object[][] validGeneratePatternDnasDataProvider() {
        return new Object[][] {
                { 1, DnaCollectors.stringToDnaSet("A", "C", "G", "T") },
                { 2, DnaCollectors.stringToDnaSet("AA", "AC", "AG", "AT", "CA", "CC", "CG", "CT", "GA", "GC", "GG", "GT", "TA", "TC", "TG", "TT") },
                { 3, DnaCollectors.stringToDnaSet("AAA", "AAC", "AAG", "AAT", "ACA", "ACC", "ACG", "ACT", "AGA", "AGC", "AGG", "AGT", "ATA", "ATC", "ATG", "ATT", "CAA", "CAC", "CAG", "CAT", "CCA", "CCC", "CCG", "CCT", "CGA", "CGC", "CGG", "CGT", "CTA", "CTC", "CTG", "CTT", "GAA", "GAC", "GAG", "GAT", "GCA", "GCC", "GCG", "GCT", "GGA", "GGC", "GGG", "GGT", "GTA", "GTC", "GTG", "GTT", "TAA", "TAC", "TAG", "TAT", "TCA", "TCC", "TCG", "TCT", "TGA", "TGC", "TGG", "TGT", "TTA", "TTC", "TTG", "TTT") }
        };
    }

    static final String VALID_MOST_FREQUENT_SUBSEQUENCES_DATA_PROVIDER_NAME = "validMostFrequentSubsequencesDataProvider";
    
    /**
     * Provides some valid cases for <i>k</i> in the mostFrequentSubSequences() method.
     * 
     * @return Some valid cases for the mostFrequentSubSequences() method.
     */
    @DataProvider(name = VALID_MOST_FREQUENT_SUBSEQUENCES_DATA_PROVIDER_NAME)
    private Object[][] provideValidMostFrequentSubsequences() {
        return new Object[][] {
                { Dna.build("AGTC"), 4 , DnaCollectors.stringToDnaSet("AGTC") },
                { Dna.build("AGTC"), 1 , DnaCollectors.stringToDnaSet("A", "G", "T", "C") },
                { Dna.build("ACGTTGCATGTCGCATGATGCATGAGAGCT"), 4, DnaCollectors.stringToDnaSet("CATG", "GCAT") },
                { Dna.build("TGGTAGCGACGTTGGTCCCGCCGCTTGAGAATCTGGATGAACATAAGCTCCCACTTGGCTTATTCAGAGAACTGGTCAACACTTGTCTCTCCCAGCCAGGTCTGACCACCGGGCAACTTTTAGAGCACTATCGTGGTACAAATAATGCTGCCAC"), 3, DnaCollectors.stringToDnaSet("TGG") },
                { Dna.build("CAGTGGCAGATGACATTTTGCTGGTCGACTGGTTACAACAACGCCTGGGGCTTTTGAGCAACGAGACTTTTCAATGTTGCACCGTTTGCTGCATGATATTGAAAACAATATCACCAAATAAATAACGCCTTAGTAAGTAGCTTTT"), 4, DnaCollectors.stringToDnaSet("TTTT") },
                { Dna.build("ATACAATTACAGTCTGGAACCGGATGAACTGGCCGCAGGTTAACAACAGAGTTGCCAGGCACTGCCGCTGACCAGCAACAACAACAATGACTTTGACGCGAAGGGGATGGCATGAGCGAACTGATCGTCAGCCGTCAGCAACGAGTATTGTTGCTGACCCTTAACAATCCCGCCGCACGTAATGCGCTAACTAATGCCCTGCTG"), 5, DnaCollectors.stringToDnaSet("AACAA") },
                { Dna.build("CCAGCGGGGGTTGATGCTCTGGGGGTCACAAGATTGCATTTTTATGGGGTTGCAAAAATGTTTTTTACGGCAGATTCATTTAAAATGCCCACTGGCTGGAGACATAGCCCGGATGCGCGTCTTTTACAACGTATTGCGGGGTAAAATCGTAGATGTTTTAAAATAGGCGTAAC"), 5, DnaCollectors.stringToDnaSet("AAAAT", "GGGGT", "TTTTA") },
                { testDnaLoader.load("most-frequent-subsequences-extra-dataset.dna"), 12, DnaCollectors.stringToDnaSet("CGGCGGGAGATT", "CGGGAGATTCAA", "CGTGCGGCGGGA", "CGTGGAGGCGTG", "CGTGGCGTGCGG", "GCGTGCGGCGGG", "GCGTGGAGGCGT", "GCGTGGCGTGCG", "GGAGAAGCGAGA", "GGAGATTCAAGC", "GGCGGGAGATTC", "GGGAGATTCAAG", "GTGCGGCGGGAG", "TGCGGCGGGAGA") }
        };
    }
    
    static final String VALID_COMPLEMENT_SEQUENCES_DATA_PROVIDER_NAME = "validComplementSequencesDataProvider";
    
    /**
     * Provides data and solution for the getComplement() method.
     * 
     * @return Some valid solution for the getComplement() method.
     */
    @DataProvider(name = VALID_COMPLEMENT_SEQUENCES_DATA_PROVIDER_NAME)
    private Object[][] provideValidComplementSequences() {
        return new Object[][] {
                { Dna.build("A"), Dna.build("T") },
                { Dna.build("G"), Dna.build("C") },
                { Dna.build("T"), Dna.build("A") },
                { Dna.build("C"), Dna.build("G") },
                { Dna.build("AAAACCCGGT"), Dna.build("TTTTGGGCCA") },
                { Dna.build("ACACAC"), Dna.build("TGTGTG") },
                { testDnaLoader.load("straight-extra-dataset.dna"), testDnaLoader.load("complement-extra-dataset.dna") }
        };
    }
    
    static final String VALID_REVERSE_COMPLEMENT_SEQUENCES_DATA_PROVIDER_NAME = "validReverseComplementSequencesDataProvider";
    
    /**
     * Provides data and solution for the getReverseComplement() method.
     * 
     * @return Some valid solution for the getReverseComplement() method.
     */
    @DataProvider(name = VALID_REVERSE_COMPLEMENT_SEQUENCES_DATA_PROVIDER_NAME)
    private Object[][] provideValidReverseComplementSequences() {
        return new Object[][] {
                { Dna.build("A"), Dna.build("T") },
                { Dna.build("G"), Dna.build("C") },
                { Dna.build("T"), Dna.build("A") },
                { Dna.build("C"), Dna.build("G") },
                { Dna.build("AAAACCCGGT"), Dna.build("ACCGGGTTTT") },
                { Dna.build("ACACAC"), Dna.build("GTGTGT") },
                { testDnaLoader.load("straight-extra-dataset.dna"), testDnaLoader.load("reverse-complement-extra-dataset.dna") }
        };
    }
    
    static final String IS_COMPLEMENT_STATEMENTS_DATA_PROVIDER_NAME = "isComplementStatementsDataProvider";
    
    /**
     * Provides data, counter data, and answer for the isComplement() method.
     * 
     * @return Valid test cases and solution for the isComplement() method.
     */
    @DataProvider(name = IS_COMPLEMENT_STATEMENTS_DATA_PROVIDER_NAME)
    private Object[][] provideIsComplementStatements() {
        return new Object[][] {
                { Dna.build("A"), Dna.build("T"), true },
                { Dna.build("G"), Dna.build("C"), true },
                { Dna.build("T"), Dna.build("A"), true },
                { Dna.build("C"), Dna.build("G"), true },
                { Dna.build("AAAACCCGGT"), Dna.build("TTTTGGGCCA"), true },
                { Dna.build("A"), Dna.build("A"), false },
                { Dna.build("A"), Dna.build("AA"), false },
                { Dna.build("AA"), Dna.build("A"), false }
        };
    }
    
    static final String IS_REVERSE_COMPLEMENT_STATEMENTS_DATA_PROVIDER_NAME = "isReverseComplementStatementsDataProvider";
    
    /**
     * Provides data, counter data, and answer for the isReverseComplement() method.
     * 
     * @return Valid test cases and solution for the isReverseComplement() method.
     */
    @DataProvider(name = IS_REVERSE_COMPLEMENT_STATEMENTS_DATA_PROVIDER_NAME)
    private Object[][] provideIsReverseComplementStatements() {
        return new Object[][] {
                { Dna.build("A"), Dna.build("T"), true },
                { Dna.build("G"), Dna.build("C"), true },
                { Dna.build("T"), Dna.build("A"), true },
                { Dna.build("C"), Dna.build("G"), true },
                { Dna.build("AAAACCCGGT"), Dna.build("ACCGGGTTTT"), true },
                { Dna.build("A"), Dna.build("A"), false },
                { Dna.build("A"), Dna.build("AA"), false },
                { Dna.build("AA"), Dna.build("A"), false }
        };
    }

    static final String GUANINE_CYTOSINE_RATIO_DATA_PROVIDER_NAME = "guanineCytosineRatioDataProvider";

    @DataProvider(name = GUANINE_CYTOSINE_RATIO_DATA_PROVIDER_NAME)
    private Object[][] guanineCytosineDataProvider() {
        return new Object[][] {
                { Dna.build("AAAA"), 0.0 },
                { Dna.build("GGCC"), 1.0 },
                { Dna.build("ACGT"), 0.5 },
                { Dna.build("AGCTATAG"), 0.375 },
                { Dna.build("CCACCCTCGTGGTATGGCTAGGCATTCAGGAACCGGAGAACGCTTCAGACCAGCCCGGACTGGGAACCTGCGGGCAGTAGGTGGAAT"), 0.60919540 }
        };
    }
    
    static final String MINIMUM_SKEW_DATA_PROVIDER_NAME = "minimumSkewDataProvider";
    
    @DataProvider(name = MINIMUM_SKEW_DATA_PROVIDER_NAME)
    private Object[][] minimumSkewDataProvider() {
        return new Object[][] {
                { Dna.build("TAAAGACTGCCGAGAGGCCAACACGAGTGCTAGAACGAGGGGCGTAAACGCGGGTCCGAT"), ImmutableList.of(10, 23) },
                { Dna.build("ACCG"), ImmutableList.of(2) },
                { Dna.build("ACCC"), ImmutableList.of(3) },
                { Dna.build("CCGGGT"), ImmutableList.of(1) },
                { Dna.build("CCGGCCGG"), ImmutableList.of(1, 5) },
                { Dna.build("A"), ImmutableList.of(0) },
                { Dna.build("AT"), ImmutableList.of(0, 1) },
                { Dna.build("GGG"), ImmutableList.of(0) },
                { Dna.build("AAGGGAAA"), ImmutableList.of(0, 1) },
                { Dna.build("TTCGAAA"), ImmutableList.of(2) },
                { Dna.build("AAAGTTTCAAA"), ImmutableList.of(0, 1, 2, 7, 8, 9, 10) },
                { testDnaLoader.load("minimum-skew-extra-dataset.dna"), ImmutableList.of(89968, 89969, 89970, 90344, 90345) }
        };
    }

    static final String INVALID_FIND_FREQUENT_PATTERNS_DATA_PROVIDER_NAME = "invalidFindFrequentPatternsDataProvider";

    @DataProvider(name = INVALID_FIND_FREQUENT_PATTERNS_DATA_PROVIDER_NAME)
    private Object[][] invalidFindFrequentPatternsDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 0, 1, 1 },
                { Dna.build("ACGT"), 1, -1, 1 },
                { Dna.build("ACGT"), 1, 1, 0 }
        };
    }

    static final String VALID_FIND_FREQUENT_PATTERNS_DATA_PROVIDER_NAME = "findFrequentPatternsDataProvider";
    
    @DataProvider(name = VALID_FIND_FREQUENT_PATTERNS_DATA_PROVIDER_NAME)
    private Object[][] validFindFrequentPatternsDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 4, 0, 1, DnaCollectors.stringToDnaSet("ACGT") },
                { Dna.build("ACGT"), 4, 1, 2, DnaCollectors.stringToDnaSet("ACGT", "CCGT", "GCGT", "TCGT", "AAGT", "AGGT", "ATGT", "ACAT", "ACCT", "ACTT", "ACGA", "ACGC", "ACGG") },
                { Dna.build("ACGT"), 4, 1, 1, DnaCollectors.stringToDnaSet("ACGT", "CCGT", "GCGT", "TCGT", "AAGT", "AGGT", "ATGT", "ACAT", "ACCT", "ACTT", "ACGA", "ACGC", "ACGG") },
                { Dna.build("A"), 1, 0, 1, DnaCollectors.stringToDnaSet("A", "T") }
        };
    }

    static final String INVALID_GET_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME = "invalidGetMismatchSubSequencesDataProvider";

    @DataProvider(name = INVALID_GET_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    private Object[][] invalidFindMostFrequentMismatchSubSequencesDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 0, 1 },
                { Dna.build("ACGT"), 1, -1 }
        };
    }
    
    static final String VALID_FIND_MOST_FREQUENT_PATTERNS_DATA_PROVIDER_NAME = "findMostFrequentPatternsDataProvider";
    
    @DataProvider(name = VALID_FIND_MOST_FREQUENT_PATTERNS_DATA_PROVIDER_NAME)
    private Object[][] validFindMostFrequentPatternsDataProvider() {
        return new Object[][] {
                { Dna.build("ACGTTGCATGTCGCATGATGCATGAGAGCT"), 4, 1, DnaCollectors.stringToDnaSet("ATGT", "ACAT") },
                { Dna.build("AAAAAAAAAA"), 2, 1, DnaCollectors.stringToDnaSet("AT", "TA") },
                { Dna.build("AGTCAGTC"), 4, 2, DnaCollectors.stringToDnaSet("AATT", "GGCC") },
                { Dna.build("AATTAATTGGTAGGTAGGTA"), 4, 0, DnaCollectors.stringToDnaSet("AATT") },
                { Dna.build("ATA"), 3, 1, DnaCollectors.stringToDnaSet("AAA", "AAT", "ACA", "AGA", "ATA", "ATC", "ATG", "ATT", "CAT", "CTA", "GAT", "GTA", "TAA", "TAC", "TAG", "TAT", "TCT", "TGT", "TTA", "TTT") },
                { Dna.build("AAT"), 3, 0, DnaCollectors.stringToDnaSet("AAT", "ATT") },
                { Dna.build("TAGCG"), 2, 1, DnaCollectors.stringToDnaSet("CA", "CC", "GG", "TG") },
                { testDnaLoader.load("most-frequent-patterns-extra-dataset.dna"), 9, 3, DnaCollectors.stringToDnaSet("AGCGCCGCT", "AGCGGCGCT") }
        };
    }

    static final String TRANSCRIPT_STRAIGHT_DATA_PROVIDER_NAME = "transcriptStraightDataProvider";

    @DataProvider(name = TRANSCRIPT_STRAIGHT_DATA_PROVIDER_NAME)
    private Object[][] transcriptStraightDataProvider() {
        return new Object[][] {
                { Dna.build("A"), Rna.build("A") },
                { Dna.build("T"), Rna.build("U") },
                { Dna.build("ACGT"), Rna.build("ACGU") },
                { Dna.build("GATGGAACTTGACTACGTAAATT"), Rna.build("GAUGGAACUUGACUACGUAAAUU") }
        };
    }

    static final String TRANSCRIPT_REVERSE_COMPLEMENT_DATA_PROVIDER_NAME = "transcriptReverseComplementDataProvider";

    @DataProvider(name = TRANSCRIPT_REVERSE_COMPLEMENT_DATA_PROVIDER_NAME)
    private Object[][] transcriptReverseComplementDataProvider() {
        return new Object[][] {
                { Dna.build("A"), Rna.build("U") },
                { Dna.build("T"), Rna.build("A") },
                { Dna.build("ACGT"), Rna.build("ACGU") },
                { Dna.build("AACC"), Rna.build("GGUU") }
        };
    }

    static final String TRANSCRIPT_DNA_DATA_PROVIDER_NAME = "transcriptDnaDataProvider";

    @DataProvider(name = TRANSCRIPT_DNA_DATA_PROVIDER_NAME)
    private Object[][] transcriptDnaDataProvider() {
        return new Object[][] {
                { Dna.build("A"), new Rna[] { Rna.build("A"), Rna.build("U") }},
                { Dna.build("T"), new Rna[] { Rna.build("U"), Rna.build("A") }},
                { Dna.build("G"), new Rna[] { Rna.build("G"), Rna.build("C") }},
                { Dna.build("ACGT"), new Rna[] { Rna.build("ACGU"), Rna.build("ACGU") }},
                { Dna.build("AACC"), new Rna[] { Rna.build("AACC"), Rna.build("GGUU") }}
        };
    }
}

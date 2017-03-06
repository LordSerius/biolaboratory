package hu.bioinformatics.biolaboratory.sequence.dna;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import hu.bioinformatics.biolaboratory.sequence.protein.Protein;
import hu.bioinformatics.biolaboratory.sequence.rna.Rna;
import hu.bioinformatics.biolaboratory.testutils.TestDnaLoader;
import hu.bioinformatics.biolaboratory.utils.collectors.DnaCollectors;
import hu.bioinformatics.biolaboratory.utils.datastructures.CountableOccurrenceMap;
import org.testng.annotations.DataProvider;

import javax.inject.Inject;
import java.util.HashSet;

/**
 * Test data provider for {@link DnaTest} test class.
 *
 * @author Attila Radi
 */
public class DnaTestDataProvider {

    @Inject
    private TestDnaLoader testDnaLoader;

    static final String INVALID_DNA_SEQUENCES_DATA_PROVIDER_NAME = "invalidDnaSequencesDataProvider";

    @DataProvider(name = INVALID_DNA_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] invalidDnaSequencesDataProvider() {
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
    static Object[][] invalidBuildFromElementsDataProvider() {
        return new Object[][] {
                { null },
                { new DnaNucleotide[0] },
                { new DnaNucleotide[] { DnaNucleotide.ADENINE, null } }
        };
    }

    static final String INVALID_BUILD_FROM_ELEMENT_LIST_DATA_PROVIDER_NAME = "invalidBuildFromElementListDataProvider";

    @DataProvider(name = INVALID_BUILD_FROM_ELEMENT_LIST_DATA_PROVIDER_NAME)
    static Object[][] invalidBuildFromElementListDataProvider() {
        return new Object[][] {
                { null },
                { Lists.newArrayList() },
                { Lists.newArrayList(DnaNucleotide.ADENINE, null) }
        };
    }
    
    static final String VALID_DNA_SEQUENCES_DATA_PROVIDER_NAME = "validDnaSequencesDataProvider";

    @DataProvider(name = VALID_DNA_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] validDnaSequencesDataProvider() {
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
    static Object[][] validBuildFromElementsDataProvider() {
        return new Object[][] {
                { new DnaNucleotide[] { DnaNucleotide.ADENINE }, "A" },
                { new DnaNucleotide[] { DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE, DnaNucleotide.GUANINE, DnaNucleotide.THYMINE }, "ACGT" }
        };
    }

    static final String INVALID_CHANGE_NAME_DATA_PROVIDER_NAME = "invalidChangeNameDataProvider";

    @DataProvider(name = INVALID_CHANGE_NAME_DATA_PROVIDER_NAME)
    Object[][] invalidChangeNameDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), null }
        };
    }

    static final String VALID_CHANGE_NAME_DATA_PROVIDER_NAME = "validChangeNameDataProvider";

    @DataProvider(name = VALID_CHANGE_NAME_DATA_PROVIDER_NAME)
    Object[][] validChangeNameDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), "name", "name" },
                { Dna.build("ACGT"), " name", "name" },
                { Dna.build("ACGT"), "name ", "name" },
                { Dna.build("ACGT"), " name ", "name" },
                { Dna.build("name", "ACGT"), "", "" },
                { Dna.build("name", "ACGT"), " ", "" }
        };
    }
    
    static final String DNA_COPY_DATA_PROVIDER_NAME = "dnaCopyDataProvider";
    
    @DataProvider(name = DNA_COPY_DATA_PROVIDER_NAME)
    Object[][] copyDataProvider() {
        return new Object[][] {
               { Dna.build("AGTC") }
        };
    }

    static final String GET_NUCLEOBASE_OCCURRENCES_DATA_PROVIDER_NAME = "nucleotideOccurrencesDataProvider";

    @DataProvider(name = GET_NUCLEOBASE_OCCURRENCES_DATA_PROVIDER_NAME)
    Object[][] nucleotideOccurrencesDataProvider() {
        return new Object[][]{
                { Dna.build("ACCGGGTTTT"), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 1, DnaNucleotide.CYTOSINE, 2, DnaNucleotide.GUANINE, 3, DnaNucleotide.THYMINE, 4)) },
                { Dna.build("AAA"), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 3, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 0)) },
                { Dna.build("AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 20, DnaNucleotide.CYTOSINE, 12, DnaNucleotide.GUANINE, 17, DnaNucleotide.THYMINE, 21)) }
        };
    }

    static final String INVALID_QUERY_MULTIPLE_NUCLEOBASE_OCCURRENCES_DATA_PROVIDER_NAME = "invalidQueryMultipleNucleobaseOccurrencesDataProvider";

    @DataProvider(name = INVALID_QUERY_MULTIPLE_NUCLEOBASE_OCCURRENCES_DATA_PROVIDER_NAME)
    Object[][] invalidSumMultipleNucleobaseOccurrencesDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), null },
                { Dna.build("ACGT"), new DnaNucleotide[] { DnaNucleotide.ADENINE, null } }
        };
    }

    static final String VALID_QUERY_MULTIPLE_NUCLEOBASE_OCCURRENCES_DATA_PROVIDER_NAME = "validQueryMultipleNucleobaseOccurrencesDataProvider";

    @DataProvider(name = VALID_QUERY_MULTIPLE_NUCLEOBASE_OCCURRENCES_DATA_PROVIDER_NAME)
    Object[][] validSumMultipleNucleobaseOccurrencesDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), new DnaNucleotide[] {}, 0 },
                { Dna.build("ACGT"), new DnaNucleotide[] { DnaNucleotide.ADENINE }, 1 },
                { Dna.build("ACGT"), new DnaNucleotide[] { DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE }, 2 },
                { Dna.build("ACGT"), new DnaNucleotide[] { DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE, DnaNucleotide.GUANINE }, 3 },
                { Dna.build("ACGT"), new DnaNucleotide[] { DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE, DnaNucleotide.GUANINE, DnaNucleotide.THYMINE }, 4 },
                { Dna.build("ACGT"), new DnaNucleotide[] { DnaNucleotide.ADENINE, DnaNucleotide.ADENINE }, 1 },
                { Dna.build("AAA"), new DnaNucleotide[] { DnaNucleotide.GUANINE }, 0 },
        };
    }

    static final String DNA_EQUALS_DATA_PROVIDER_NAME = "dnaEqualsDataProvider";

    @DataProvider(name = DNA_EQUALS_DATA_PROVIDER_NAME)
    Object[][] provideDnaEqualsData() {
        return new Object[][] {
                { Dna.build("AGTC"), null, false },
                { Dna.build("AGTC"), "AGTC", false },
                { Dna.build("AGTC"), Dna.build("AGTC"), true },
                { Dna.build("AGTC"), Dna.build("T"), false },
                { Dna.build("AGTC"), Dna.build("CTGA"), false },
                { Dna.build("AAAA") , Rna.build("AAAA"), false },
                { Dna.build("AAAA"), Protein.build("AAAA"), false}
        };
    }
    
    static final String DNA_HASH_DATA_PROVIDER_NAME = "dnaHashDataProvider";

    @DataProvider(name = DNA_HASH_DATA_PROVIDER_NAME)
    Object[][] provideDnaHashData() {
        return new Object[][] {
                { Dna.build("AGTC"), "", false },
                { Dna.build("AGTC"), "                ", false },
                { Dna.build("AGTC"), "LOL", false },
                { Dna.build("AGTC"), "AGTC", true },
                { Dna.build("AGTC"), "agtc", false },
                { Dna.build("AGTC"), "        AGTC        ", false },
                { Dna.build("AGTC"), Dna.build("AGTC"), true },
                { Dna.build("AGTC"), "T", false },
                { Dna.build("AGTC"), Dna.build("T"), false }
        };
    }

    static final String INVALID_CUT_PARTS_DATA_PROVIDER_NAME = "invalidCutPartsDataProvider";

    @DataProvider(name = INVALID_CUT_PARTS_DATA_PROVIDER_NAME)
    Object[][] invalidCutPartsDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), -1, 1 },
                { Dna.build("ACGT"), 0, 5 },
                { Dna.build("ACGT"), 2, 1 },
                { Dna.build("ACGT"), 1, 1 }
        };
    }

    static final String VALID_CUT_PARTS_DATA_PROVIDER_NAME = "validCutPartsDataProvider";

    @DataProvider(name = VALID_CUT_PARTS_DATA_PROVIDER_NAME)
    Object[][] validCutPartsDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 0, 2, Dna.build("AC") },
                { Dna.build("ACGT"), 0, 1, Dna.build("A") },
                { Dna.build("ACGT"), 0, 4, Dna.build("ACGT") }
        };
    }

    static final String INVALID_CUT_TO_END_DATA_PROVIDER_NAME = "invalidCutToEndDataProvider";

    @DataProvider(name = INVALID_CUT_TO_END_DATA_PROVIDER_NAME)
    Object[][] invalidCutToEndDataProviderName() {
        return new Object[][] {
                { Dna.build("ACGT"), -1 },
                { Dna.build("ACGT"), 5 }
        };
    }

    static final String VALID_CUT_TO_END_DATA_PROVIDER_NAME = "validCutToEndDataProvider";

    @DataProvider(name = VALID_CUT_TO_END_DATA_PROVIDER_NAME)
    Object[][] validCutToEndDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 0, Dna.build("ACGT") },
                { Dna.build("ACGT"), 3, Dna.build("T") },
                { Dna.build("ACGT"), 2, Dna.build("GT") }
        };
    }

    static final String INVALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME = "invalidPatternArgumentsDataProvider";

    /**
     * Provide valid DNA sequences, but all DNA patterns are not well formed.
     * 
     * @return All invalid DNA pattern cases.
     */
    @DataProvider(name = INVALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME)
    Object[][] provideInvalidPatternArguments() {
        return new Object[][] {
                { Dna.build("AGTC"), null },
                { Dna.build("AGTC"), Dna.build("AGTCA") }
        };
    }
    
    static final String VALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME = "validPatternArgumentsDataProvider";
    
    /**
     * Provide valid test cases for pattern matching.
     * 
     * @return Some valid cases for pattern matching.
     */
    @DataProvider(name = VALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME)
    Object[][] provideValidPatternArguments() {
        return new Object[][] {
                { Dna.build("ACAACTATGCATACTATCGGGAACTATCCT"), Dna.build("ACTAT"), Lists.newArrayList(3, 12, 22) },
                { Dna.build("GCGCG"), Dna.build("GCG"), Lists.newArrayList(0, 2) },
                { Dna.build("GATATATGCATATACTT"), Dna.build("ATAT"), Lists.newArrayList(1, 3, 9) },
                { Dna.build("TTTTACACTTTTTTGTGTAAAAA"), Dna.build("ACAC"), Lists.newArrayList(4) },
                { Dna.build("AAAGAGTGTCTGATAGCAGCTTCTGAACTGGTTACCTGCCGTGAGTAAATTAAATTTTATTGACTTAGGTCACTAAATACTTTAACCAATATAGGCATAGCGCACAGACAGATAATAATTACAGAGTACACAACATCCAT"), Dna.build("AAA"), Lists.newArrayList(0, 46, 51, 74) },
                { Dna.build("AGCGTGCCGAAATATGCCGCCAGACCTGCTGCGGTGGCCTCGCCGACTTCACGGATGCCAAGTGCATAGAGGAAGCGAGCAAAGGTGGTTTCTTTCGCTTTATCCAGCGCGTTAACCACGTTCTGTGCCGACTTT"), Dna.build("TTT"), Lists.newArrayList(88, 92, 98, 132) },
                { Dna.build("ATATATA"), Dna.build("ATA"), Lists.newArrayList(0, 2, 4) },
                { Dna.build("AAA"), Dna.build("G"), Lists.newArrayList() },
                { Dna.build("AAA"), Dna.build("A"), Lists.newArrayList(0, 1, 2) },
                { Dna.build("AAA"), Dna.build("AAA"), Lists.newArrayList(0)}
        };
    }

    static final String INVALID_GET_SUB_SEQUENCES_DATA_PROVIDER_NAME = "invalidGetSubSequencesDataProvider";
    
    /**
     * Provide invalid <i>k</i>-s for mostFrequentSubSequences() method.
     * 
     * @return All invalid <i>k</i> cases
     */
    @DataProvider(name = INVALID_GET_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] provideInvalidFindableSequenceNumbers() {
        return new Object[][] {
                { Dna.build("AGTC"), 5 },
                { Dna.build("AGTC"), 0 }
        };
    }
    
    static final String VALID_MOST_FREQUENT_SUBSEQUENCES_DATA_PROVIDER_NAME = "validMostFrequentSubsequencesDataProvider";
    
    /**
     * Provides some valid cases for <i>k</i> in the mostFrequentSubSequences() method.
     * 
     * @return Some valid cases for the mostFrequentSubSequences() method.
     */
    @DataProvider(name = VALID_MOST_FREQUENT_SUBSEQUENCES_DATA_PROVIDER_NAME)
    Object[][] provideValidMostFrequentSubsequences() {
        return new Object[][] {
                { Dna.build("AGTC"), 4 , DnaCollectors.stringToDnaSet("AGTC") },
                { Dna.build("AGTC"), 1 , DnaCollectors.stringToDnaSet("A", "G", "T", "C") },
                { Dna.build("ACGTTGCATGTCGCATGATGCATGAGAGCT"), 4, DnaCollectors.stringToDnaSet("CATG", "GCAT") },
                { Dna.build("TGGTAGCGACGTTGGTCCCGCCGCTTGAGAATCTGGATGAACATAAGCTCCCACTTGGCTTATTCAGAGAACTGGTCAACACTTGTCTCTCCCAGCCAGGTCTGACCACCGGGCAACTTTTAGAGCACTATCGTGGTACAAATAATGCTGCCAC"), 3, DnaCollectors.stringToDnaSet("TGG") },
                { Dna.build("CAGTGGCAGATGACATTTTGCTGGTCGACTGGTTACAACAACGCCTGGGGCTTTTGAGCAACGAGACTTTTCAATGTTGCACCGTTTGCTGCATGATATTGAAAACAATATCACCAAATAAATAACGCCTTAGTAAGTAGCTTTT"), 4, DnaCollectors.stringToDnaSet("TTTT") },
                { Dna.build("ATACAATTACAGTCTGGAACCGGATGAACTGGCCGCAGGTTAACAACAGAGTTGCCAGGCACTGCCGCTGACCAGCAACAACAACAATGACTTTGACGCGAAGGGGATGGCATGAGCGAACTGATCGTCAGCCGTCAGCAACGAGTATTGTTGCTGACCCTTAACAATCCCGCCGCACGTAATGCGCTAACTAATGCCCTGCTG"), 5, DnaCollectors.stringToDnaSet("AACAA") },
                { Dna.build("CCAGCGGGGGTTGATGCTCTGGGGGTCACAAGATTGCATTTTTATGGGGTTGCAAAAATGTTTTTTACGGCAGATTCATTTAAAATGCCCACTGGCTGGAGACATAGCCCGGATGCGCGTCTTTTACAACGTATTGCGGGGTAAAATCGTAGATGTTTTAAAATAGGCGTAAC"), 5, DnaCollectors.stringToDnaSet("AAAAT", "GGGGT", "TTTTA") },
                { testDnaLoader.loadFromResource("most-frequent-subsequences-extra-dataset.dna"), 12, DnaCollectors.stringToDnaSet("CGGCGGGAGATT", "CGGGAGATTCAA", "CGTGCGGCGGGA", "CGTGGAGGCGTG", "CGTGGCGTGCGG", "GCGTGCGGCGGG", "GCGTGGAGGCGT", "GCGTGGCGTGCG", "GGAGAAGCGAGA", "GGAGATTCAAGC", "GGCGGGAGATTC", "GGGAGATTCAAG", "GTGCGGCGGGAG", "TGCGGCGGGAGA") }
        };
    }
    
    static final String INVALID_SUBSEQUENCES_IN_CLUMPS_DATA_PROVIDER_NAME = "invalidSubsequencesInClumpsDataProvider";
    
    @DataProvider(name = INVALID_SUBSEQUENCES_IN_CLUMPS_DATA_PROVIDER_NAME)
    Object[][] provideInvalidSubSequencesInClumps() {
        return new Object[][] {
                { Dna.build("ACGT"), 0, 4, 0 },
                { Dna.build("ACGT"), 1, 5, 0 },
                { Dna.build("ACGT"), 3, 2, 0 }
        };
    }

    static final String VALID_SUBSEQUENCES_IN_CLUMPS_DATA_PROVIDER_NAME = "validSubsequencesInCLumpsDataProvider";
    
    @DataProvider(name = VALID_SUBSEQUENCES_IN_CLUMPS_DATA_PROVIDER_NAME)
    Object[][] provideValidSubSequencesInClumps() {
        return new Object[][] {
                { Dna.build("A"), 1, 1, 1, DnaCollectors.stringToDnaSet("A") },
                { Dna.build("A"), 1, 1, 2, DnaCollectors.stringToDnaSet() },
                { Dna.build("CGGACTCGACAGATGTGAAGAACGACAATGTGAAGACTCGACACGACAGAGTGAAGAGAAGAGGAAACATTGTAA"), 5, 50, 4, DnaCollectors.stringToDnaSet("CGACA", "GAAGA") },
                { Dna.build("AAAACGTCGAAAAA"), 2, 4, 2, DnaCollectors.stringToDnaSet("AA") },
                { Dna.build("ACGTACGT"), 1, 5, 2, DnaCollectors.stringToDnaSet("A", "C", "G", "T") },
                { Dna.build("CCACGCGGTGTACGCTGCAAAAAGCCTTGCTGAATCAAATAAGGTTCCAGCACATCCTCAATGGTTTCACGTTCTTCGCCAATGGCTGCCGCCAGGTTATCCAGACCTACAGGTCCACCAAAGAACTTATCGATTACCGCCAGCAACAATTTGCGGTCCATATAATCGAAACCTTCAGCATCGACATTCAACATATCCAGCG"), 3, 25, 3, DnaCollectors.stringToDnaSet("AAA", "CAG", "CAT", "CCA", "GCC", "TTC") }
        };
    }
    
    static final String VALID_COMPLEMENT_SEQUENCES_DATA_PROVIDER_NAME = "validComplementSequencesDataProvider";
    
    /**
     * Provides data and solution for the getComplement() method.
     * 
     * @return Some valid solution for the getComplement() method.
     */
    @DataProvider(name = VALID_COMPLEMENT_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] provideValidComplementSequences() {
        return new Object[][] {
                { Dna.build("A"), Dna.build("T") },
                { Dna.build("G"), Dna.build("C") },
                { Dna.build("T"), Dna.build("A") },
                { Dna.build("C"), Dna.build("G") },
                { Dna.build("AAAACCCGGT"), Dna.build("TTTTGGGCCA") },
                { Dna.build("ACACAC"), Dna.build("TGTGTG") },
                { testDnaLoader.loadFromResource("straight-extra-dataset.dna"), testDnaLoader.loadFromResource("complement-extra-dataset.dna") }
        };
    }
    
    static final String VALID_REVERSE_COMPLEMENT_SEQUENCES_DATA_PROVIDER_NAME = "validReverseComplementSequencesDataProvider";
    
    /**
     * Provides data and solution for the getReverseComplement() method.
     * 
     * @return Some valid solution for the getReverseComplement() method.
     */
    @DataProvider(name = VALID_REVERSE_COMPLEMENT_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] provideValidReverseComplementSequences() {
        return new Object[][] {
                { Dna.build("A"), Dna.build("T") },
                { Dna.build("G"), Dna.build("C") },
                { Dna.build("T"), Dna.build("A") },
                { Dna.build("C"), Dna.build("G") },
                { Dna.build("AAAACCCGGT"), Dna.build("ACCGGGTTTT") },
                { Dna.build("ACACAC"), Dna.build("GTGTGT") },
                { testDnaLoader.loadFromResource("straight-extra-dataset.dna"), testDnaLoader.loadFromResource("reverse-complement-extra-dataset.dna") }
        };
    }
    
    static final String IS_COMPLEMENT_STATEMENTS_DATA_PROVIDER_NAME = "isComplementStatementsDataProvider";
    
    /**
     * Provides data, counter data, and answer for the isComplement() method.
     * 
     * @return Valid test cases and solution for the isComplement() method.
     */
    @DataProvider(name = IS_COMPLEMENT_STATEMENTS_DATA_PROVIDER_NAME)
    Object[][] provideIsComplementStatements() {
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
    Object[][] provideIsReverseComplementStatements() {
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
    Object[][] guanineCytosineDataProvider() {
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
    Object[][] minimumSkewDataProvider() {
        return new Object[][] {
                { Dna.build("TAAAGACTGCCGAGAGGCCAACACGAGTGCTAGAACGAGGGGCGTAAACGCGGGTCCGAT"), Lists.newArrayList(10, 23) },
                { Dna.build("ACCG"), Lists.newArrayList(2) },
                { Dna.build("ACCC"), Lists.newArrayList(3) },
                { Dna.build("CCGGGT"), Lists.newArrayList(1) },
                { Dna.build("CCGGCCGG"), Lists.newArrayList(1, 5) },
                { Dna.build("A"), Lists.newArrayList(0) },
                { Dna.build("AT"), Lists.newArrayList(0, 1) },
                { Dna.build("GGG"), Lists.newArrayList(0) },
                { Dna.build("AAGGGAAA"), Lists.newArrayList(0, 1) },
                { Dna.build("TTCGAAA"), Lists.newArrayList(2) },
                { Dna.build("AAAGTTTCAAA"), Lists.newArrayList(0, 1, 2, 7, 8, 9, 10) },
                { testDnaLoader.loadFromResource("minimum-skew-extra-dataset.dna"), Lists.newArrayList(89968, 89969, 89970, 90344, 90345) }
        };
    }
    
    static final String INVALID_HAMMING_DISTANCE_DATA_PROVIDER_NAME = "invalidHammingDistanceDataProvider";
    
    @DataProvider(name = INVALID_HAMMING_DISTANCE_DATA_PROVIDER_NAME)
    Object[][] invalidHammingDistanceDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), null },
                { Dna.build("A"), Dna.build("ACGT") },
                { Dna.build("ACGT"), Dna.build("A") }
        };
    }
    
    static final String VALID_HAMMING_DISTANCE_DATA_PROVIDER_NAME = "validHammingDistanceDataProvider";
    
    @DataProvider(name = VALID_HAMMING_DISTANCE_DATA_PROVIDER_NAME)
    Object[][] validHammingDistanceDataProvider() {
        return new Object[][] {
                { Dna.build("A"), Dna.build("A"), 0 },
                { Dna.build("A"), Dna.build("C"), 1 },
                { Dna.build("GGGCCGTTGGT"), Dna.build("GGACCGTTGAC"), 3 },
                { Dna.build("AAAA"), Dna.build("TTTT"), 4 },
                { Dna.build("ACGTACGT"), Dna.build("TACGTACG"), 8 },
                { Dna.build("ACGTACGT"), Dna.build("CCCCCCCC"), 6 },
                { Dna.build("ACGTACGT"), Dna.build("TGCATGCA"), 8 },
                { Dna.build("GATAGCAGCTTCTGAACTGGTTACCTGCCGTGAGTAAATTAAAATTTTATTGACTTAGGTCACTAAATACT"), Dna.build("AATAGCAGCTTCTCAACTGGTTACCTCGTATGAGTAAATTAGGTCATTATTGACTCAGGTCACTAACGTCT"), 15 },
                { Dna.build("AGAAACAGACCGCTATGTTCAACGATTTGTTTTATCTCGTCACCGGGATATTGCGGCCACTCATCGGTCAGTTGATTACGCAGGGCGTAAATCGCCAGAATCAGGCTG"), Dna.build("AGAAACCCACCGCTAAAAACAACGATTTGCGTAGTCAGGTCACCGGGATATTGCGGCCACTAAGGCCTTGGATGATTACGCAGAACGTATTGACCCAGAATCAGGCTC"), 28 },
                { testDnaLoader.loadFromResource("hamming-1.dna"), testDnaLoader.loadFromResource("hamming-2.dna"), 844 }
        };
    }
    
    static final String INVALID_PATTERN_ARGUMENTS_WITH_MISMATCHES_DATA_PROVIDER_NAME = "invalidPatternArgumentsWithMismatches";
    
    @DataProvider(name = INVALID_PATTERN_ARGUMENTS_WITH_MISMATCHES_DATA_PROVIDER_NAME)
    Object[][] invalidPatternArgumentsWithMismatchesDataProvider() {
        return new Object[][] {
                { Dna.build("AGTC"), null, 1 },
                { Dna.build("AGTC"), Dna.build("AGTCA"), 1 },
                { Dna.build("AGTC"), Dna.build("AGTC"), -1 }
        };
    }
    
    static final String VALID_PATTERN_ARGUMENTS_WITH_MISMATCHES_DATA_PROVIDER_NAME = "validPatternArgumentsWithMismatches";

    @DataProvider(name = VALID_PATTERN_ARGUMENTS_WITH_MISMATCHES_DATA_PROVIDER_NAME)
    Object[][] validPatternArgumentsWithMismatchesDataProvider() {
        return new Object[][] {
                { Dna.build("A"), Dna.build("G"), 0, Lists.newArrayList() },
                { Dna.build("A"), Dna.build("G"), 5, Lists.newArrayList(0) },
                { Dna.build("CGCCCGAATCCAGAACGCATTCCCATATTTCGGGACCACTGGCCTCCACGGTACGGACGTCAATCAAAT"), Dna.build("ATTCTGGA"), 3, Lists.newArrayList(6, 7, 26, 27) },
                { Dna.build("TTTTTTAAATTTTAAATTTTTT"), Dna.build("AAA"), 2, Lists.newArrayList(4, 5, 6, 7, 8, 11, 12, 13, 14, 15) },
                { Dna.build("GAGCGCTGGGTTAACTCGCTACTTCCCGACGAGCGCTGTGGCGCAAATTGGCGATGAAACTGCAGAGAGAACTGGTCATCCAACTGAATTCTCCCCGCTATCGCATTTTGATGCGCGCCGCGTCGATT"), Dna.build("GAGCGCTGG"), 2, Lists.newArrayList(0, 30, 66) },
                { Dna.build("CCAAATCCCCTCATGGCATGCATTCCCGCAGTATTTAATCCTTTCATTCTGCATATAAGTAGTGAAGGTATAGAAACCCGTTCAAGCCCGCAGCGGTAAAACCGAGAACCATGATGAATGCACGGCGATTGCGCCATAATCCAAACA"), Dna.build("AATCCTTTCA"), 3, Lists.newArrayList(3, 36, 74, 137) },
                { Dna.build("CCGTCATCCGTCATCCTCGCCACGTTGGCATGCATTCCGTCATCCCGTCAGGCATACTTCTGCATATAAGTACAAACATCCGTCATGTCAAAGGGAGCCCGCAGCGGTAAAACCGAGAACCATGATGAATGCACGGCGATTGC"), Dna.build("CCGTCATCC"), 3, Lists.newArrayList(0, 7, 36, 44, 48, 72, 79, 112) },
                { Dna.build("AAAAAA"), Dna.build("TTT"), 3, Lists.newArrayList(0, 1, 2, 3) },
                { Dna.build("CCACCT"), Dna.build("CCA"), 0, Lists.newArrayList(0) },
                { Dna.build("AGTC"), Dna.build("AGTC"), 5, Lists.newArrayList(0) },
                { Dna.build("TTTAGAGCCTTCAGAGG"), Dna.build("GAGG"), 2, Lists.newArrayList(2, 4, 11, 13) },
                { Dna.build("AAA"), Dna.build("AA"), 0, Lists.newArrayList(0, 1) },
                { Dna.build("ATA"), Dna.build("ATA"), 1, Lists.newArrayList(0) }
        };
    }
    
    static final String INVALID_MISMATCH_NUMBERS_DATA_PROVIDER_NAME = "invalidMismatchNumbersDataProvider";
    
    @DataProvider(name = INVALID_MISMATCH_NUMBERS_DATA_PROVIDER_NAME)
    Object[][] invalidMismatchNumbersDataProvider() {
        return new Object[][] {
            { Dna.build("ACGT"), -1 }
        };
    }
    
    static final String VALID_GENERATE_MISMATCHES_DATA_PROVIDER_NAME = "validGenerateMismatchesDataProvider";
    
    @DataProvider(name = VALID_GENERATE_MISMATCHES_DATA_PROVIDER_NAME)
    Object[][] validGenerateMismatchesDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 0, DnaCollectors.stringToDnaSet("ACGT") },
                { Dna.build("ACGT"), 1, DnaCollectors.stringToDnaSet("ACGT", "CCGT", "GCGT", "TCGT", "AAGT", "AGGT", "ATGT", "ACAT", "ACCT", "ACTT", "ACGA", "ACGC", "ACGG") },
                { Dna.build("A"), 1, DnaCollectors.stringToDnaSet("A", "C", "G", "T") },
                { Dna.build("A"), 2, DnaCollectors.stringToDnaSet("A", "C", "G", "T") },
                { Dna.build("AA"), 2, DnaCollectors.stringToDnaSet("AA", "AC", "AG", "AT", "CA", "CC", "CG", "CT", "GA", "GC", "GG", "GT", "TA", "TC", "TG", "TT") },
                { Dna.build("AAA"), 2, DnaCollectors.stringToDnaSet("AAA", "AAC", "AAG", "AAT", "ACA", "ACC", "ACG", "ACT", "AGA", "AGC", "AGG", "AGT", "ATA", "ATC", "ATG", "ATT", "CAA", "CAC", "CAG", "CAT", "CCA", "CGA", "CTA", "GAA", "GAC", "GAG", "GAT", "GCA", "GGA", "GTA", "TAA", "TAC", "TAG", "TAT", "TCA", "TGA", "TTA") }
        };
    }
    
    static final String INVALID_FIND_FREQUENT_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME = "invalidFindFrequentMismatchSubSequencesDataProvider";
    
    @DataProvider(name = INVALID_FIND_FREQUENT_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] invalidFindFrequentMismatchSubSequencesDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 0, 1, 1 },
                { Dna.build("ACGT"), 1, -1, 1 },
                { Dna.build("ACGT"), 1, 1, 0 }
        };
    }
    
    static final String VALID_FIND_FREQUENT_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME = "validFindFrequentMismatchSubSequencesDataProvider";

    @DataProvider(name = VALID_FIND_FREQUENT_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] validFindFrequentMismatchSubSequencesDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 4, 0, 1, DnaCollectors.stringToDnaSet("ACGT") },
                { Dna.build("ACGT"), 4, 1, 2, new HashSet<Dna>() },
                { Dna.build("ACGT"), 4, 1, 1, DnaCollectors.stringToDnaSet("ACGT", "CCGT", "GCGT", "TCGT", "AAGT", "AGGT", "ATGT", "ACAT", "ACCT", "ACTT", "ACGA", "ACGC", "ACGG") }
        };
    }

    static final String INVALID_FIND_FREQUENT_SUB_SEQUENCES_DATA_PROVIDER_NAME = "invalidFindFrequentSubSequencesDataProvider";

    @DataProvider(name = INVALID_FIND_FREQUENT_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] invalidFindFrequentSubSequencesDAtaProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 0, 1 },
                { Dna.build("ACGT"), 5, 1 },
                { Dna.build("ACGT"), 2, 0 }
        };
    }

    static final String VALID_FIND_FREQUENT_SUB_SEQUENCES_DATA_PROVIDER_NAME = "validFindFrequentSubSequencesDataProvider";

    @DataProvider(name = VALID_FIND_FREQUENT_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] validFindFrequentSubSequencesDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 2, 5, DnaCollectors.stringToDnaSet() },
                { Dna.build("ACGT"), 2, 1, DnaCollectors.stringToDnaSet("AC", "CG", "GT") },
                { Dna.build("AAACG"), 2, 2, DnaCollectors.stringToDnaSet("AA") },
                { Dna.build("AAACG"), 2, 1, DnaCollectors.stringToDnaSet("AA", "AC", "CG") }
        };
    }

    static final String VALID_GET_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME = "validGetMismatchSubSequencesDataProvider";

    @DataProvider(name = VALID_GET_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] validGetMismatchSubSequencesDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 2, 1, DnaCollectors.stringToDnaSet("AA", "AC", "AG", "AT", "CA", "CC", "CG", "CT", "GA", "GC", "GG", "GT", "TC", "TG", "TT") },
                { Dna.build("AAAA"), 2, 1, DnaCollectors.stringToDnaSet("AA", "CA", "GA", "TA", "AC", "AG", "AT") },
                { Dna.build("ACGT"), 4, 1, DnaCollectors.stringToDnaSet("ACGT", "CCGT", "GCGT", "TCGT", "AAGT", "AGGT", "ATGT", "ACAT", "ACCT", "ACTT", "ACGA", "ACGC", "ACGG") },
                { Dna.build("ACGT"), 2, 0, DnaCollectors.stringToDnaSet("AC", "CG", "GT") },
                { Dna.build("AAAA"), 2, 0, DnaCollectors.stringToDnaSet("AA") },
                { Dna.build("ACGT"), 4, 0, DnaCollectors.stringToDnaSet("ACGT") },
        };
    }

    static final String VALID_GET_SUB_SEQUENCES_DATA_PROVIDER_NAME = "validGetSubSequencesDataProvider";

    @DataProvider(name = VALID_GET_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    public static Object[][] getSubSequencesDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 2, DnaCollectors.stringToDnaSet("AC", "CG", "GT") },
                { Dna.build("AAAA"), 2, DnaCollectors.stringToDnaSet("AA") },
                { Dna.build("ACGT"), 4, DnaCollectors.stringToDnaSet("ACGT") },
        };
    }

    static final String INVALID_GET_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME = "invalidGetMismatchSubSequencesDataProvider";

    @DataProvider(name = INVALID_GET_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] invalidFindMostFrequentMismatchSubSequencesDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 0, 1 },
                { Dna.build("ACGT"), 1, -1 }
        };
    }
    
    static final String VALID_FIND_MOST_FREQUENT_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME = "validFindMostFrequentMismatchSubsequencesDataProvider";
    
    @DataProvider(name = VALID_FIND_MOST_FREQUENT_MISMATCH_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] validFindMostFrequentMismatchSubSequencesDataProvider() {
        return new Object[][] {
                { Dna.build("ACGTTGCATGTCGCATGATGCATGAGAGCT"), 4, 1, DnaCollectors.stringToDnaSet("GATG", "ATGC", "ATGT") },
                { Dna.build("AAAAAAAAAA"), 2, 1, DnaCollectors.stringToDnaSet("AA", "AC", "AG", "CA", "AT", "GA", "TA") },
                { Dna.build("AGTCAGTC"), 4, 2, DnaCollectors.stringToDnaSet("TCTC", "CGGC", "AAGC", "TGTG", "GGCC", "AGGT", "ATCC", "ACTG", "ACAC", "AGAG", "ATTA", "TGAC", "AATT", "CGTT", "GTTC", "GGTA", "AGCA", "CATC") },
                { Dna.build("AATTAATTGGTAGGTAGGTA"), 4, 0, DnaCollectors.stringToDnaSet("GGTA") },
                { Dna.build("ATA"), 3, 1, DnaCollectors.stringToDnaSet("GTA", "ACA", "AAA", "ATC", "ATA", "AGA", "ATT", "CTA", "TTA", "ATG") },
                { Dna.build("AAT"), 3, 0, DnaCollectors.stringToDnaSet("AAT") },
                { Dna.build("TAGCG"), 2, 1, DnaCollectors.stringToDnaSet("GG", "TG") },
                { testDnaLoader.loadFromResource("most-frequent-subsequences-with-mismatches-extra-dataset.dna"), 10, 2, DnaCollectors.stringToDnaSet("GCACACAGAC", "GCGCACACAC") }
        };
    }

    static final String VALID_FIND_MOST_FREQUENT_SUB_SEQUENCES_DATA_PROVIDER_NAME = "validFindMostFrequentSubSequencesDataProvider";

    @DataProvider(name = VALID_FIND_MOST_FREQUENT_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] validFindMostFrequentSubSequencesDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 2, DnaCollectors.stringToDnaSet("AC", "CG", "GT") },
                { Dna.build("AAAGT"), 2, DnaCollectors.stringToDnaSet("AA") }
        };
    }

    static final String VALID_FIND_FREQUENT_PATTERNS_DATA_PROVIDER_NAME = "findFrequentPatternsDataProvider";
    
    @DataProvider(name = VALID_FIND_FREQUENT_PATTERNS_DATA_PROVIDER_NAME)
    Object[][] validFindFrequentPatternsDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 4, 0, 1, DnaCollectors.stringToDnaSet("ACGT") },
                { Dna.build("ACGT"), 4, 1, 2, DnaCollectors.stringToDnaSet("ACGT", "CCGT", "GCGT", "TCGT", "AAGT", "AGGT", "ATGT", "ACAT", "ACCT", "ACTT", "ACGA", "ACGC", "ACGG") },
                { Dna.build("ACGT"), 4, 1, 1, DnaCollectors.stringToDnaSet("ACGT", "CCGT", "GCGT", "TCGT", "AAGT", "AGGT", "ATGT", "ACAT", "ACCT", "ACTT", "ACGA", "ACGC", "ACGG") },
                { Dna.build("A"), 1, 0, 1, DnaCollectors.stringToDnaSet("A", "T") }
        };
    }
    
    static final String VALID_FIND_MOST_FREQUENT_PATTERNS_DATA_PROVIDER_NAME = "findMostFrequentPatternsDataProvider";
    
    @DataProvider(name = VALID_FIND_MOST_FREQUENT_PATTERNS_DATA_PROVIDER_NAME)
    Object[][] validFindMostFrequentPatternsDataProvider() {
        return new Object[][] {
                { Dna.build("ACGTTGCATGTCGCATGATGCATGAGAGCT"), 4, 1, DnaCollectors.stringToDnaSet("ATGT", "ACAT") },
                { Dna.build("AAAAAAAAAA"), 2, 1, DnaCollectors.stringToDnaSet("AT", "TA") },
                { Dna.build("AGTCAGTC"), 4, 2, DnaCollectors.stringToDnaSet("AATT", "GGCC") },
                { Dna.build("AATTAATTGGTAGGTAGGTA"), 4, 0, DnaCollectors.stringToDnaSet("AATT") },
                { Dna.build("ATA"), 3, 1, DnaCollectors.stringToDnaSet("AAA", "AAT", "ACA", "AGA", "ATA", "ATC", "ATG", "ATT", "CAT", "CTA", "GAT", "GTA", "TAA", "TAC", "TAG", "TAT", "TCT", "TGT", "TTA", "TTT") },
                { Dna.build("AAT"), 3, 0, DnaCollectors.stringToDnaSet("AAT", "ATT") },
                { Dna.build("TAGCG"), 2, 1, DnaCollectors.stringToDnaSet("CA", "CC", "GG", "TG") },
                { testDnaLoader.loadFromResource("most-frequent-patterns-extra-dataset.dna"), 9, 3, DnaCollectors.stringToDnaSet("AGCGCCGCT", "AGCGGCGCT") }
        };
    }

    static final String TRANSCRIPT_STRAIGHT_DATA_PROVIDER_NAME = "transcriptStraightDataProvider";

    @DataProvider(name = TRANSCRIPT_STRAIGHT_DATA_PROVIDER_NAME)
    Object[][] transcriptStraightDataProvider() {
        return new Object[][] {
                { Dna.build("A"), Rna.build("A") },
                { Dna.build("T"), Rna.build("U") },
                { Dna.build("ACGT"), Rna.build("ACGU") },
                { Dna.build("GATGGAACTTGACTACGTAAATT"), Rna.build("GAUGGAACUUGACUACGUAAAUU") }
        };
    }

    static final String TRANSCRIPT_REVERSE_COMPLEMENT_DATA_PROVIDER_NAME = "transcriptReverseComplementDataProvider";

    @DataProvider(name = TRANSCRIPT_REVERSE_COMPLEMENT_DATA_PROVIDER_NAME)
    Object[][] transcriptReverseComplementDataProvider() {
        return new Object[][] {
                { Dna.build("A"), Rna.build("U") },
                { Dna.build("T"), Rna.build("A") },
                { Dna.build("ACGT"), Rna.build("ACGU") },
                { Dna.build("AACC"), Rna.build("GGUU") }
        };
    }

    static final String TRANSCRIPT_DNA_DATA_PROVIDER_NAME = "transcriptDnaDataProvider";

    @DataProvider(name = TRANSCRIPT_DNA_DATA_PROVIDER_NAME)
    Object[][] transcriptDnaDataProvider() {
        return new Object[][] {
                { Dna.build("A"), new Rna[] { Rna.build("A"), Rna.build("U") }},
                { Dna.build("T"), new Rna[] { Rna.build("U"), Rna.build("A") }},
                { Dna.build("G"), new Rna[] { Rna.build("G"), Rna.build("C") }},
                { Dna.build("ACGT"), new Rna[] { Rna.build("ACGU"), Rna.build("ACGU") }},
                { Dna.build("AACC"), new Rna[] { Rna.build("AACC"), Rna.build("GGUU") }}
        };
    }
}

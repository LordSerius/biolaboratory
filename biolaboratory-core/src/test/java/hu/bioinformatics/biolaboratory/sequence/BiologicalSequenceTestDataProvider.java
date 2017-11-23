package hu.bioinformatics.biolaboratory.sequence;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.sequence.dna.DnaNucleotide;
import hu.bioinformatics.biolaboratory.sequence.protein.AminoAcid;
import hu.bioinformatics.biolaboratory.sequence.protein.Protein;
import hu.bioinformatics.biolaboratory.sequence.rna.Rna;
import hu.bioinformatics.biolaboratory.sequence.rna.RnaNucleotide;
import hu.bioinformatics.biolaboratory.utils.collectors.DnaCollectors;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaLoader;
import hu.bioinformatics.biolaboratory.utils.datastructures.CountableOccurrenceMap;
import org.testng.annotations.DataProvider;

import javax.inject.Inject;
import java.util.HashSet;

/**
 * Test data provider for {@link BiologicalSequenceTest} test class.
 *
 * @author Attila Radi
 */
public class BiologicalSequenceTestDataProvider {

    @Inject
    private DnaLoader testDnaLoader;

    static final String COPY_DATA_PROVIDER_NAME = "copyDataProvider";

    @DataProvider(name = COPY_DATA_PROVIDER_NAME)
    Object[][] copyDataProvider() {
        return new Object[][] {
                { Dna.build("AGTC") },
                { Dna.build("name", "AGTC") }
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

    static final String INVALID_GET_ELEMENT_DATA_PROVIDER_NAME = "invalidGetElementDataProvider";

    @DataProvider(name = INVALID_GET_ELEMENT_DATA_PROVIDER_NAME)
    Object[][] invalidGetElementDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), -1 },
                { Dna.build("ACGT"), 5 }
        };
    }

    static final String VALID_GET_ELEMENT_DATA_PROVIDER_NAME = "validGetElementDataProvider";

    @DataProvider(name = VALID_GET_ELEMENT_DATA_PROVIDER_NAME)
    Object[][] validGetElementDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 0, DnaNucleotide.ADENINE },
                { Dna.build("ACGT"), 1, DnaNucleotide.CYTOSINE},
                { Dna.build("ACGT"), 3, DnaNucleotide.THYMINE }
        };
    }

    static final String GET_SEQUENCE_AS_ELEMENTS_DATA_PROVIDER_NAME = "getSequenceAsElementsDataProvider";

    @DataProvider(name = GET_SEQUENCE_AS_ELEMENTS_DATA_PROVIDER_NAME)
    static Object[][] getSequenceAsElementsDataProvider() {
        return new Object[][] {
                { Rna.build("A"), new RnaNucleotide[] { RnaNucleotide.ADENINE } },
                { Rna.build("ACGU"), new RnaNucleotide[] { RnaNucleotide.ADENINE, RnaNucleotide.CYTOSINE, RnaNucleotide.GUANINE, RnaNucleotide.URACIL } }
        };
    }

    static final String EQUALS_DATA_PROVIDER_NAME = "equalsDataProvider";

    @DataProvider(name = EQUALS_DATA_PROVIDER_NAME)
    Object[][] equalsDataProvider() {
        return new Object[][] {
                { Dna.build("AGTC"), null, false },
                { Dna.build("AGTC"), "AGTC", false },
                { Dna.build("AGTC"), Dna.build("AGTC"), true },
                { Dna.build("AGTC"), Dna.build("T"), false },
                { Dna.build("AGTC"), Dna.build("CTGA"), false },
                { Dna.build("AAAA") , Rna.build("AAAA"), false },
                { Dna.build("AAAA"), Protein.build("AAAA"), false},
                { Rna.build("AGUC"), null, false },
                { Rna.build("AGUC"), "AGUC", false },
                { Rna.build("AGUC"), Rna.build("AGUC"), true },
                { Rna.build("AGUC"), Rna.build("U"), false },
                { Rna.build("AGUC"), Rna.build("CUGA"), false },
                { Protein.build("RHDESTNQCGPAILMFWYV"), null, false },
                { Protein.build("RHDESTNQCGPAILMFWYV"), "RHDESTNQCGPAILMFWYV", false },
                { Protein.build("RHDESTNQCGPAILMFWYV"), Protein.build("RHDESTNQCGPAILMFWYV"), true },
                { Protein.build("RHDESTNQCGPAILMFWYV"), Protein.build("R"), false },
                { Protein.build("RHDESTNQCGPAILMFWYV"), Protein.build("VYWFMLIAPGCQNTSEDHR"), false }
        };
    }

    static final String HASH_DATA_PROVIDER_NAME = "hashDataProvider";

    @DataProvider(name = HASH_DATA_PROVIDER_NAME)
    Object[][] hashDataProvider() {
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

    static final String GET_ELEMENT_OCCURRENCES_DATA_PROVIDER_NAME = "getElementOccurrencesDataProvider";

    @DataProvider(name = GET_ELEMENT_OCCURRENCES_DATA_PROVIDER_NAME)
    Object[][] nucleotideOccurrencesDataProvider() {
        return new Object[][]{
                { Dna.build("ACCGGGTTTT"), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 1, DnaNucleotide.CYTOSINE, 2, DnaNucleotide.GUANINE, 3, DnaNucleotide.THYMINE, 4)) },
                { Dna.build("AAA"), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 3, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 0)) },
                { Dna.build("AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 20, DnaNucleotide.CYTOSINE, 12, DnaNucleotide.GUANINE, 17, DnaNucleotide.THYMINE, 21)) }
        };
    }

    static final String INVALID_GET_ELEMENT_NUMBER_DATA_PROVIDER_NAME = "invalidGetElementNumberDataProvider";

    @DataProvider(name = INVALID_GET_ELEMENT_NUMBER_DATA_PROVIDER_NAME)
    Object[][] invalidGetElementNumberDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), null },
                { Dna.build("ACGT"), RnaNucleotide.URACIL },
                { Dna.build("ACGT"), RnaNucleotide.ADENINE }
        };
    }

    static final String VALID_GET_ELEMENT_NUMBER_DATA_PROVIDER_NAME = "validGetElementNumberDataProvider";

    @DataProvider(name = VALID_GET_ELEMENT_NUMBER_DATA_PROVIDER_NAME)
    Object[][] validGetElementNumberDataProvider() {
        return new Object[][] {
                { Dna.build("ACCGGGTTTT"), DnaNucleotide.ADENINE, 1 },
                { Dna.build("ACCGGGTTTT"), DnaNucleotide.CYTOSINE, 2 },
                { Dna.build("ACCGGGTTTT"), DnaNucleotide.GUANINE, 3 },
                { Dna.build("ACCGGGTTTT"), DnaNucleotide.THYMINE, 4 },
                { Dna.build("AAA"), DnaNucleotide.ADENINE, 3, },
                { Dna.build("AAA"), DnaNucleotide.CYTOSINE, 0 },
                { Dna.build("AAA"), DnaNucleotide.GUANINE, 0 },
                { Dna.build("AAA"), DnaNucleotide.THYMINE, 0 },
                { Dna.build("AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"), DnaNucleotide.ADENINE, 20 },
                { Dna.build("AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"), DnaNucleotide.CYTOSINE, 12 },
                { Dna.build("AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"), DnaNucleotide.GUANINE, 17 },
                { Dna.build("AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"), DnaNucleotide.THYMINE, 21 }
        };
    }

    static final String VALID_GET_RATIO_DATA_PROVIDER_NAME = "validGetRatioDataProvider";

    @DataProvider(name = VALID_GET_RATIO_DATA_PROVIDER_NAME)
    Object[][] validGetRatioDataProvider() {
        return new Object[][] {
                { Dna.build("ACCGGGTTTT"), DnaNucleotide.ADENINE, 0.1d },
                { Dna.build("ACCGGGTTTT"), DnaNucleotide.CYTOSINE, 0.2d },
                { Dna.build("ACCGGGTTTT"), DnaNucleotide.GUANINE, 0.3d },
                { Dna.build("ACCGGGTTTT"), DnaNucleotide.THYMINE, 0.4d },
                { Dna.build("AAA"), DnaNucleotide.ADENINE, 1.0d, },
                { Dna.build("AAA"), DnaNucleotide.CYTOSINE, 0.0d },
                { Dna.build("AAA"), DnaNucleotide.GUANINE, 0.0d },
                { Dna.build("AAA"), DnaNucleotide.THYMINE, 0.0d },
                { Dna.build("AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"), DnaNucleotide.ADENINE, 20.0d / 70.0d },
                { Dna.build("AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"), DnaNucleotide.CYTOSINE, 12.0d / 70.0d },
                { Dna.build("AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"), DnaNucleotide.GUANINE, 17.0d / 70.0d },
                { Dna.build("AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"), DnaNucleotide.THYMINE, 21.0d / 70.0d }
        };
    }

    static final String INVALID_GET_ELEMENTS_DATA_PROVIDER_NAME = "invalidGetElementsDataProvider";

    @DataProvider(name = INVALID_GET_ELEMENTS_DATA_PROVIDER_NAME)
    Object[][] invalidGetElementsDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), null },
                { Dna.build("ACGT"), new DnaNucleotide[] { DnaNucleotide.ADENINE, null } },
                { Dna.build("ACGT"), new RnaNucleotide[] { RnaNucleotide.URACIL } },
                { Dna.build("ACGT"), new RnaNucleotide[] { RnaNucleotide.ADENINE } }
        };
    }

    static final String VALID_GET_ELEMENTS_NUMBER_DATA_PROVIDER_NAME = "validGetElementsNumberDataProvider";

    @DataProvider(name = VALID_GET_ELEMENTS_NUMBER_DATA_PROVIDER_NAME)
    Object[][] validGetElementsNumberDataProvider() {
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

    static final String VALID_GET_RATIOS_DATA_PROVIDER_NAME = "validGetRatiosDataProvider";

    @DataProvider(name = VALID_GET_RATIOS_DATA_PROVIDER_NAME)
    Object[][] validGetRatiosDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), new DnaNucleotide[] {}, 0.0d },
                { Dna.build("ACGT"), new DnaNucleotide[] { DnaNucleotide.ADENINE }, 0.25d },
                { Dna.build("ACGT"), new DnaNucleotide[] { DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE }, 0.5d },
                { Dna.build("ACGT"), new DnaNucleotide[] { DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE, DnaNucleotide.GUANINE }, 0.75d },
                { Dna.build("ACGT"), new DnaNucleotide[] { DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE, DnaNucleotide.GUANINE, DnaNucleotide.THYMINE }, 1.0d },
                { Dna.build("ACGT"), new DnaNucleotide[] { DnaNucleotide.ADENINE, DnaNucleotide.ADENINE }, 0.25d },
                { Dna.build("AAA"), new DnaNucleotide[] { DnaNucleotide.GUANINE }, 0.0d },
        };
    }

    static final String INVALID_GET_ELEMENTS_ABOUT_SET_DATA_PROVIDER_NAME = "invalidGetElementsAboutSet";

    @DataProvider(name = INVALID_GET_ELEMENTS_ABOUT_SET_DATA_PROVIDER_NAME)
    Object[][] invalidGetElementsAboutSetDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), null },
                { Dna.build("ACGT"), Sets.newHashSet(DnaNucleotide.ADENINE, null) },
                { Dna.build("ACGT"), Sets.newHashSet(RnaNucleotide.URACIL) },
                { Dna.build("ACGT"), Sets.newHashSet(RnaNucleotide.ADENINE) }
        };
    }

    static final String VALID_GET_ELEMENTS_NUMBER_ABOUT_SET_DATA_PROVIDER_NAME = "validGetElementsNumberAboutSetDataProvider";

    @DataProvider(name = VALID_GET_ELEMENTS_NUMBER_ABOUT_SET_DATA_PROVIDER_NAME)
    Object[][] validGetElementsNumberAboutSetDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"),Sets.newHashSet(), 0 },
                { Dna.build("ACGT"), Sets.newHashSet(DnaNucleotide.ADENINE), 1 },
                { Dna.build("ACGT"), Sets.newHashSet(DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE), 2 },
                { Dna.build("ACGT"), Sets.newHashSet(DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE, DnaNucleotide.GUANINE), 3 },
                { Dna.build("ACGT"), Sets.newHashSet(DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE, DnaNucleotide.GUANINE, DnaNucleotide.THYMINE), 4 },
                { Dna.build("AAA"),Sets.newHashSet(DnaNucleotide.GUANINE), 0 },
        };
    }

    static final String VALID_GET_RATIOS_ABOUT_SET_DATA_PROVIDER_NAME = "validGetRatiosAboutSetDataProvider";

    @DataProvider(name = VALID_GET_RATIOS_ABOUT_SET_DATA_PROVIDER_NAME)
    Object[][] validGetRatioAboutSetDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"),Sets.newHashSet(), 0.0d },
                { Dna.build("ACGT"), Sets.newHashSet(DnaNucleotide.ADENINE), 0.25d },
                { Dna.build("ACGT"), Sets.newHashSet(DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE), 0.5d },
                { Dna.build("ACGT"), Sets.newHashSet(DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE, DnaNucleotide.GUANINE), 0.75d },
                { Dna.build("ACGT"), Sets.newHashSet(DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE, DnaNucleotide.GUANINE, DnaNucleotide.THYMINE), 1.0d },
                { Dna.build("AAA"),Sets.newHashSet(DnaNucleotide.GUANINE), 0.0d },
        };
    }

    static final String INVALID_APPEND_DATA_PROVIDER_NAME = "invalidAppendDataProvider";

    @DataProvider(name = INVALID_APPEND_DATA_PROVIDER_NAME)
    static Object[][] invalidAppendDataProvider() {
        return new Object[][] {
                { Rna.build("A"), null },
                { Rna.build("A"), Dna.build("A") },
                { Rna.build("A"), Protein.build("A") }
        };
    }

    static final String INVALID_APPEND_ELEMENT_DATA_PROVIDER_NAME = "invalidAppendElementDataProvider";

    @DataProvider(name = INVALID_APPEND_ELEMENT_DATA_PROVIDER_NAME)
    static Object[][] invalidAppendElementDataProvider() {
        return new Object[][] {
                { Rna.build("A"), null },
                { Rna.build("A"), DnaNucleotide.ADENINE},
                { Rna.build("A"), AminoAcid.ALANINE},
        };
    }

    static final String VALID_APPEND_DATA_PROVIDER_NAME = "validAppendDataProvider";

    @DataProvider(name = VALID_APPEND_DATA_PROVIDER_NAME)
    static Object[][] validAppendDataProvider() {
        return new Object[][] {
                { Rna.build("ACGU"), Rna.build("A"), Rna.build("ACGUA") },
                { Rna.build("ACGU"), Rna.build("ACGU"), Rna.build("ACGUACGU") }
        };
    }

    static final String VALID_APPEND_ELEMENT_DATA_PROVIDER_NAME = "validAppendElementDataProvider";

    @DataProvider(name = VALID_APPEND_ELEMENT_DATA_PROVIDER_NAME)
    static Object[][] validAppendElementDataProvider() {
        return new Object[][] {
                { Rna.build("ACGU"), RnaNucleotide.ADENINE, Rna.build("ACGUA") }
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

    @DataProvider(name = INVALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME)
    Object[][] invalidPatternArgumentsDataProvider() {
        return new Object[][] {
                { Dna.build("AGTC"), null },
                { Dna.build("AGTC"), Dna.build("AGTCA") },
                { Dna.build("ACGT"), Rna.build("ACG") }
        };
    }

    static final String VALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME = "validPatternArgumentsDataProvider";

    @DataProvider(name = VALID_PATTERN_ARGUMENTS_DATA_PROVIDER_NAME)
    Object[][] validPatternArgumentsDataProvider() {
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

    static final String VALID_FIND_MINIMUM_MISMATCH_SUB_SEQUENCES_NUMBER_DATA_PROVIDER_NAME = "findMinimumMismatchSubSequencesNumberDataProvider";

    @DataProvider(name = VALID_FIND_MINIMUM_MISMATCH_SUB_SEQUENCES_NUMBER_DATA_PROVIDER_NAME)
    Object[][] validFindMinimumMismatchSubSequencesNumberDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), Dna.build("A"), 0},
                { Dna.build("ACGT"), Dna.build("AA"), 1 },
                { Dna.build("ACGT"), Dna.build("AAA"), 2 },
                { Dna.build("ACGT"), Dna.build("AAAA"), 3 },
                { Dna.build("AAAA"), Dna.build("CC"), 2 },
                { Dna.build("AAAA"), Dna.build("CCCC"), 4 }
        };
    }

    static final String INVALID_PATTERN_ARGUMENTS_WITH_MISMATCHES_DATA_PROVIDER_NAME = "invalidPatternArgumentsWithMismatches";

    @DataProvider(name = INVALID_PATTERN_ARGUMENTS_WITH_MISMATCHES_DATA_PROVIDER_NAME)
    Object[][] invalidPatternArgumentsWithMismatchesDataProvider() {
        return new Object[][] {
                { Dna.build("AGTC"), null, 1 },
                { Dna.build("AGTC"), Dna.build("AGTCA"), 1 },
                { Dna.build("AGTC"), Dna.build("AGTC"), -1 },
                { Dna.build("ACGT"), Rna.build("ACG"), 1 }
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

    static final String INVALID_GET_SUB_SEQUENCES_DATA_PROVIDER_NAME = "invalidGetSubSequencesDataProvider";

    @DataProvider(name = INVALID_GET_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] provideInvalidFindableSequenceNumbers() {
        return new Object[][] {
                { Dna.build("AGTC"), 5 },
                { Dna.build("AGTC"), 0 }
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

    static final String VALID_FIND_MOST_FREQUENT_SUB_SEQUENCES_DATA_PROVIDER_NAME = "validFindMostFrequentSubSequencesDataProvider";

    @DataProvider(name = VALID_FIND_MOST_FREQUENT_SUB_SEQUENCES_DATA_PROVIDER_NAME)
    Object[][] validFindMostFrequentSubSequencesDataProvider() {
        return new Object[][] {
                { Dna.build("ACGT"), 2, DnaCollectors.stringToDnaSet("AC", "CG", "GT") },
                { Dna.build("AAAGT"), 2, DnaCollectors.stringToDnaSet("AA") }
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
                { testDnaLoader.load("most-frequent-subsequences-with-mismatches-extra-dataset.dna"), 10, 2, DnaCollectors.stringToDnaSet("GCACACAGAC", "GCGCACACAC") }
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
                { testDnaLoader.load("hamming-1.dna"), testDnaLoader.load("hamming-2.dna"), 844 }
        };
    }
}

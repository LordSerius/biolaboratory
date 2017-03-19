package hu.bioinformatics.biolaboratory.sequence.dna;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.ImmutableMap;
import hu.bioinformatics.biolaboratory.utils.collectors.DnaCollectors;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaArrayLoader;
import hu.bioinformatics.biolaboratory.utils.datahandlers.DnaSetLoader;
import hu.bioinformatics.biolaboratory.utils.datastructures.CountableOccurrenceMap;
import org.testng.annotations.DataProvider;

import javax.inject.Inject;

/**
 * Data provider for {@link MotifsTest}.
 *
 * @author Attila Radi
 */
public class MotifsTestDataProvider {

    @Inject
    private DnaSetLoader testDnaSetLoader;
    @Inject
    private DnaArrayLoader testDnaArrayLoader;

    static final String GET_MOTIFS_DATA_PROVIDER = "getMotifsDataProvider";

    @DataProvider(name = GET_MOTIFS_DATA_PROVIDER)
    Object[][] getMotifsDataProvider() {
        return new Object[][] {
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT"))), new DnaNucleotide[][] { {DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE, DnaNucleotide.GUANINE, DnaNucleotide.THYMINE} } },
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "ACGT"))), new DnaNucleotide[][] { { DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE, DnaNucleotide.GUANINE, DnaNucleotide.THYMINE  }, { DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE, DnaNucleotide.GUANINE, DnaNucleotide.THYMINE } } },
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "TGCA"))), new DnaNucleotide[][] { { DnaNucleotide.ADENINE, DnaNucleotide.CYTOSINE, DnaNucleotide.GUANINE, DnaNucleotide.THYMINE  }, { DnaNucleotide.THYMINE, DnaNucleotide.GUANINE, DnaNucleotide.CYTOSINE, DnaNucleotide.ADENINE } } }
        };
    }

    static final String COUNT_DATA_PROVIDER_NAME = "countDataProviderName";

    @DataProvider(name = COUNT_DATA_PROVIDER_NAME)
    Object[][] countMotifsDataProvider() {
        return new Object[][] {
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT"))), Lists.newArrayList(CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 1, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 1, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 1, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 1))) },
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "ACGT"))), Lists.newArrayList(CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 2, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 2, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 2, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 2))) },
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "TGCA"))), Lists.newArrayList(CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 1, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 1)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 1, DnaNucleotide.GUANINE, 1, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 1, DnaNucleotide.GUANINE, 1, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 1, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 1))) },
                { Motifs.build(testDnaArrayLoader.load("motif-calculation.fas")), Lists.newArrayList(CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 2, DnaNucleotide.CYTOSINE, 1, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 7)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 2, DnaNucleotide.CYTOSINE, 6, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 2)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 10, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 10, DnaNucleotide.THYMINE, 0)),
                        CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 9, DnaNucleotide.THYMINE, 1)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 9, DnaNucleotide.THYMINE, 1)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 9, DnaNucleotide.CYTOSINE, 0, DnaNucleotide.GUANINE, 1, DnaNucleotide.THYMINE, 0)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 1, DnaNucleotide.CYTOSINE, 4, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 5)),
                        CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 1, DnaNucleotide.CYTOSINE, 1, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 8)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 1, DnaNucleotide.CYTOSINE, 2, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 7)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 3, DnaNucleotide.CYTOSINE, 4, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 3)), CountableOccurrenceMap.build(ImmutableMap.of(DnaNucleotide.ADENINE, 0, DnaNucleotide.CYTOSINE, 6, DnaNucleotide.GUANINE, 0, DnaNucleotide.THYMINE, 4))) }
        };
    }

    static final String SCORE_DATA_PROVIDER_NAME = "scoreDataProviderName";

    @DataProvider(name = SCORE_DATA_PROVIDER_NAME)
    Object[][] scoreDataProvider() {
        return new Object[][] {
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT"))), new int[] {0, 0, 0, 0} },
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "ACGT"))), new int[] {0, 0, 0, 0} },
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "TGCA"))), new int[] {1, 1, 1, 1} },
                { Motifs.build(testDnaArrayLoader.load("motif-calculation.fas")), new int[] {3, 4, 0, 0, 1, 1, 1, 5, 2, 3, 6, 4} }
        };
    }

    static final String TOTAL_SCORE_DATA_PROVIDER_NAME = "totalScoreDataProviderName";

    @DataProvider(name = TOTAL_SCORE_DATA_PROVIDER_NAME)
    Object[][] totalScoreDataProvider() {
        return new Object[][] {
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT"))), 0 },
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "ACGT"))), 0 },
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "TGCA"))), 4 },
                { Motifs.build(testDnaArrayLoader.load("motif-calculation.fas")), 30 }
        };
    }

    static final String PROFILE_DATA_PROVIDER_NAME = "profileDataProvider";

    @DataProvider(name = PROFILE_DATA_PROVIDER_NAME)
    Object[][] profileDataProvider() {
        return new Object[][] {
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT"))), Lists.newArrayList(ImmutableMap.of(DnaNucleotide.ADENINE, 1.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 1.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 1.0, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 1.0)) },
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "ACGT"))), Lists.newArrayList(ImmutableMap.of(DnaNucleotide.ADENINE, 1.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 1.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 1.0, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 1.0)) },
                { Motifs.build(DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "TGCA"))), Lists.newArrayList(ImmutableMap.of(DnaNucleotide.ADENINE, 0.5, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.5), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.5, DnaNucleotide.GUANINE, 0.5, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.5, DnaNucleotide.GUANINE, 0.5, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.5, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.5)) },
                { Motifs.build(testDnaArrayLoader.load("motif-calculation.fas")), Lists.newArrayList(ImmutableMap.of(DnaNucleotide.ADENINE, 0.2, DnaNucleotide.CYTOSINE, 0.1, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.7), ImmutableMap.of(DnaNucleotide.ADENINE, 0.2, DnaNucleotide.CYTOSINE, 0.6, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.2), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 1.0, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 1.0, DnaNucleotide.THYMINE, 0.0),
                        ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.9, DnaNucleotide.THYMINE, 0.1), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.9, DnaNucleotide.THYMINE, 0.1), ImmutableMap.of(DnaNucleotide.ADENINE, 0.9, DnaNucleotide.CYTOSINE, 0.0, DnaNucleotide.GUANINE, 0.1, DnaNucleotide.THYMINE, 0.0), ImmutableMap.of(DnaNucleotide.ADENINE, 0.1, DnaNucleotide.CYTOSINE, 0.4, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.5),
                        ImmutableMap.of(DnaNucleotide.ADENINE, 0.1, DnaNucleotide.CYTOSINE, 0.1, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.8), ImmutableMap.of(DnaNucleotide.ADENINE, 0.1, DnaNucleotide.CYTOSINE, 0.2, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.7), ImmutableMap.of(DnaNucleotide.ADENINE, 0.3, DnaNucleotide.CYTOSINE, 0.4, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.3), ImmutableMap.of(DnaNucleotide.ADENINE, 0.0, DnaNucleotide.CYTOSINE, 0.6, DnaNucleotide.GUANINE, 0.0, DnaNucleotide.THYMINE, 0.4)) }
        };
    }
}

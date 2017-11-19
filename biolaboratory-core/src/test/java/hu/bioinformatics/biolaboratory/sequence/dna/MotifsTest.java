package hu.bioinformatics.biolaboratory.sequence.dna;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.guice.GuiceTestModule;
import hu.bioinformatics.biolaboratory.utils.datastructures.CountableOccurrenceMap;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Unit tests for {@link Motifs}.
 *
 * @author Attila Radi
 */
@Guice(modules = {GuiceCoreModule.class, GuiceResourceModule.class, GuiceTestModule.class})
@Test(dataProviderClass = MotifsTestDataProvider.class)
public class MotifsTest {

    @Test(dataProvider = MotifsTestDataProvider.GET_MOTIFS_DATA_PROVIDER_NAME)
    public void shouldGetMotifsReturn(Motifs motifs, DnaNucleotide[][] controlMotifs) {
        DnaNucleotide[][] innerMotifs = motifs.getMotifs();
        assertThat(innerMotifs, allOf(is(equalTo(controlMotifs)),
                                        is(not(sameInstance(motifs.getMotifs())))));
    }

    @Test(dataProvider = MotifsTestDataProvider.CONSENSUS_DATA_PROVIDER_NAME)
    public void shouldConsensusReturn(Motifs motifs, Set<Dna> controlConsensusSet) {
        Set<Dna> consensusSet = motifs.consensus();
        assertThat(consensusSet, is(equalTo(controlConsensusSet)));
    }

    @Test(dataProvider = MotifsTestDataProvider.ENTROPY_DATA_PROVIDER_NAME)
    public void shouldEntropyReturn(Motifs motifs, double[] controlMotifEntropies) {
        double[] entropy = motifs.entropy();
        IntStream.range(0, entropy.length)
                .forEach(i -> {
                    assertThat(entropy[i], is(closeTo(controlMotifEntropies[i], 0.001)));
                });
    }

    @Test(dataProvider = MotifsTestDataProvider.TOTAL_ENTROPY_DATA_PROVIDER_NAME)
    public void shouldTotalEntropyReturn(Motifs motifs, double controlTotalEntropy) {
        double totalEntropy = motifs.totalEntropy();
        assertThat(totalEntropy, is(closeTo(controlTotalEntropy, 0.001)));
    }

    @Test(dataProvider = MotifsTestDataProvider.COUNT_DATA_PROVIDER_NAME)
    public void shouldCreateMotifsReturn(Motifs motifs, List<CountableOccurrenceMap<DnaNucleotide>> controlCountMotifs) {
        List<CountableOccurrenceMap<DnaNucleotide>> countMotifs = motifs.count();
        assertThat(countMotifs, is(equalTo(controlCountMotifs)));
    }

    @Test(dataProvider = MotifsTestDataProvider.SCORE_DATA_PROVIDER_NAME)
    public void shouldScoreReturn(Motifs motifs, int[] controlMotifScores) {
        int[] motifScores = motifs.score();
        assertThat(motifScores, is(equalTo(controlMotifScores)));
    }

    @Test(dataProvider = MotifsTestDataProvider.TOTAL_SCORE_DATA_PROVIDER_NAME)
    public void shouldTotalScoreReturn(Motifs motifs, int controlTotalScore) {
        int totalScore = motifs.totalScore();
        assertThat(totalScore, is(equalTo(controlTotalScore)));
    }

    @Test(dataProvider = MotifsTestDataProvider.PROFILE_DATA_PROVIDER_NAME)
    public void shouldProfileReturn(Motifs motifs, List<Map<DnaNucleotide, Double>> controlProfile) {
        List<Map<DnaNucleotide, Double>> profile = motifs.profile();
        assertThat(profile, is(equalTo(controlProfile)));
    }
}
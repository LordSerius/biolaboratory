package hu.bioinformatics.biolaboratory.utils.datahandlers.implementation;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.guice.GuiceMockModule;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.sequence.dna.DnaArray;
import hu.bioinformatics.biolaboratory.utils.resource.read.MockReaderWrapperFactory;
import hu.bioinformatics.biolaboratory.utils.resource.read.wrapper.ReaderWrapperFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.fail;

/**
 * Unit tests for {@link DnaArrayFastaLoader}.
 *
 * @author Attila Radi
 */
@Guice(modules = {GuiceCoreModule.class, GuiceMockModule.class})
public class DnaArrayFastaLoaderTest {

    private static final String PATH = "sample.fas";

    @Inject
    private DnaArrayFastaLoader dnaArrayFastaLoader;

    @Inject
    private ReaderWrapperFactory readerWrapperFactory;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    private static final String INVALID_LOAD_DATA_PROVIDER_NAME = "invalidLoadDataProvider";

    @DataProvider(name = INVALID_LOAD_DATA_PROVIDER_NAME)
    private Object[][] invalidLoadDataProvider() {
        return new Object[][] {
                { ".fas", Stream.builder().add("> comment").add("AGCT").build() },
                { "sample.rna", Stream.builder().add("> comment").add("AGCT").build() },
                { PATH, Stream.builder().add("AGCT").build() },
                { PATH, Stream.builder().add("> comment").add("> dulicated comment").add("AGCT").build() },
                { PATH, Stream.builder().add("> comment").add("AGCU").build() },
                { PATH, Stream.builder().add("").build() },
                { PATH, Stream.builder().add("> comment1").add("ACGTA").add("> comment2").add("TGCA").build() }

        };
    }

    private static final String VALID_LOAD_DATA_PROVIDER_NAME = "validLoadDataProvider";

    @DataProvider(name = VALID_LOAD_DATA_PROVIDER_NAME)
    private Object[][] validLoadDataProvider() {
        return new Object[][] {
                { PATH, Stream.builder().add("> comment").add("ACGT").build(), DnaArray.build(Dna.build("comment", "ACGT")) },
                { PATH, Stream.builder().add("> comment").add("ACGT").add("ACGT").build(), DnaArray.build(Dna.build("comment", "ACGTACGT")) },
                { PATH, Stream.builder().add("> comment1").add("ACGT").add("> comment2").add("TGCA").build(), DnaArray.build(Dna.build("comment1", "ACGT"), Dna.build("comment2", "TGCA")) }
        };
    }

    @Test(dataProvider = INVALID_LOAD_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldLoadThrowException(String filePath, Stream<String> lines) {
        ((MockReaderWrapperFactory) readerWrapperFactory).setLines(lines);
        dnaArrayFastaLoader.load(filePath);
        fail();
    }

    @Test(dataProvider = VALID_LOAD_DATA_PROVIDER_NAME)
    public void shouldLoadReturn(String filePath, Stream<String> lines, DnaArray controlDnaArray) {
        ((MockReaderWrapperFactory) readerWrapperFactory).setLines(lines);
        DnaArray loadedDnaArray = dnaArrayFastaLoader.load(filePath);
        assertThat(loadedDnaArray, is(equalTo(controlDnaArray)));
    }
}
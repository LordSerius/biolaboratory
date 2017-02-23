package hu.bioinformatics.biolaboratory.utils.datahandlers;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.guice.GuiceMockModule;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
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
 * Unit tests for {@link DnaRowLoader}.
 *
 * @author Attila Radi
 */
@Guice(modules = {GuiceCoreModule.class, GuiceMockModule.class})
public class DnaRowLoaderTest {

    private static final String PATH = "sample.dna";

    @Inject
    private DnaRowLoader dnaRowLoader;

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
                { ".dna", Stream.builder().add("AGCT").build() },
                { "sample.rna", Stream.builder().add("AGCT").build() },
                { PATH, Stream.builder().add("AGCT").add("AGCT").build() },
                { PATH, Stream.builder().add("AGCU").build() },
                { PATH, Stream.builder().add("").build() }
        };
    }

    private static final String VALID_LOAD_DATA_PROVIDER_NAME = "validLoadDataProvider";

    @DataProvider(name = VALID_LOAD_DATA_PROVIDER_NAME)
    private Object[][] validLoadDataProvider() {
        return new Object[][] {
                { PATH, Stream.builder().add("ACGT").build(), Dna.build("ACGT") }
        };
    }

    @Test(dataProvider = INVALID_LOAD_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldLoadThrowException(String filePath, Stream<String> lines) {
        ((MockReaderWrapperFactory) readerWrapperFactory).setLines(lines);
        dnaRowLoader.load(filePath);
        fail();
    }

    @Test(dataProvider = VALID_LOAD_DATA_PROVIDER_NAME)
    public void shouldLoadReturn(String filePath, Stream<String> lines, Dna controlDna) {
        ((MockReaderWrapperFactory) readerWrapperFactory).setLines(lines);
        Dna loadedDna = dnaRowLoader.load(filePath);
        assertThat(loadedDna, is(equalTo(controlDna)));
    }
}
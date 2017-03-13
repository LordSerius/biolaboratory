package hu.bioinformatics.biolaboratory.utils.datahandlers.implementation;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreMockModule;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceMockModule;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.resource.read.MockReaderWrapperFactory;
import hu.bioinformatics.biolaboratory.resource.read.wrapper.ReaderWrapperFactory;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.collectors.DnaCollectors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Set;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.fail;

/**
 * Unit tests for {@link DnaSetRowLoader}.
 *
 * @author Attila Radi
 */
@Guice(modules = {GuiceResourceModule.class, GuiceResourceMockModule.class, GuiceCoreMockModule.class})
public class DnaSetRowLoaderTest {

    private static final String PATH = "sample-dna-set.txt";

    @Inject
    private DnaSetRowLoader dnaSetRowLoader;

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
                { PATH, Stream.builder().add("").build() }
        };
    }

    private static final String VALID_LOAD_DATA_PROVIDER_NAME = "validLoadDataProvider";

    @DataProvider(name = VALID_LOAD_DATA_PROVIDER_NAME)
    private Object[][] validLoadDataProvider() {
        return new Object[][] {
                { PATH, Stream.builder().add("A C G T").build(), DnaCollectors.stringToDnaSet("A", "C", "G", "T") },
                { PATH, Stream.builder().add("A     C       G       T").build(), DnaCollectors.stringToDnaSet("A", "C", "G", "T") }
        };
    }

    @Test(dataProvider = INVALID_LOAD_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldLoadThrowException(String filePath, Stream<String> lines) {
        ((MockReaderWrapperFactory) readerWrapperFactory).setLines(lines);
        dnaSetRowLoader.load(filePath);
        fail();
    }

    @Test(dataProvider = VALID_LOAD_DATA_PROVIDER_NAME)
    public void shouldLoadReturn(String filePath, Stream<String> lines, Set<Dna> controlDnaSet) {
        ((MockReaderWrapperFactory) readerWrapperFactory).setLines(lines);
        Set<Dna> loadedDna = dnaSetRowLoader.load(filePath);
        assertThat(loadedDna, is(equalTo(controlDnaSet)));
    }
}
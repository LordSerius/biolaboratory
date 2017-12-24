package hu.bioinformatics.biolaboratory.utils.datahandlers.implementation;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreMockModule;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceMockModule;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.resource.read.MockReaderWrapperFactory;
import hu.bioinformatics.biolaboratory.resource.read.wrapper.ReaderWrapperFactory;
import hu.bioinformatics.biolaboratory.sequence.dna.DnaArray;
import hu.bioinformatics.biolaboratory.utils.collectors.DnaCollectors;
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
 * Unit tests for {@link DnaArrayLineLoaderTest}.
 *
 * @author Attila Radi
 */
@Guice(modules = {GuiceResourceModule.class, GuiceResourceMockModule.class, GuiceCoreMockModule.class})
public class DnaArrayLineLoaderTest {

    private static final String PATH = "sample.dnacol";

    @Inject
    private DnaArrayLineLoader dnaArrayLineLoader;

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
                { PATH, Stream.builder().add("AGCU").build() },
                { PATH, Stream.builder().add("ACGT").add("AGCU").build() },
                { PATH, Stream.builder().add("ACGT").add("ACG").build() },
                { PATH, Stream.builder().add("").build() }
        };
    }

    private static final String VALID_LOAD_DATA_PROVIDER_NAME = "validLoadDataProvider";

    @DataProvider(name = VALID_LOAD_DATA_PROVIDER_NAME)
    private Object[][] validLoadDataProvider() {
        return new Object[][] {
                { PATH, Stream.builder().add("ACGT").build(), DnaArray.build(DnaCollectors.stringToDnaList("ACGT")) },
                { PATH, Stream.builder().add("ACGT").add("ACGT").build(), DnaArray.build(DnaCollectors.stringToDnaList("ACGT", "ACGT")) }
        };
    }

    @Test(dataProvider = INVALID_LOAD_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldLoadThrowException(String filePath, Stream<String> lines) {
        ((MockReaderWrapperFactory) readerWrapperFactory).setLines(lines);
        dnaArrayLineLoader.load(filePath);
        fail();
    }

    @Test(dataProvider = VALID_LOAD_DATA_PROVIDER_NAME)
    public void shouldLoadReturn(String filePath, Stream<String> lines, DnaArray controlDnaArray) {
        ((MockReaderWrapperFactory) readerWrapperFactory).setLines(lines);
        DnaArray loadedDnaArray = dnaArrayLineLoader.load(filePath);
        assertThat(loadedDnaArray, is(equalTo(controlDnaArray)));
    }
}
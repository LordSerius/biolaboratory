package hu.bioinformatics.biolaboratory.utils.datahandlers.implementation;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreMockModule;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceMockModule;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.resource.read.MockReaderWrapperFactory;
import hu.bioinformatics.biolaboratory.resource.read.wrapper.ReaderWrapperFactory;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.datastructures.CommentedString;
import hu.bioinformatics.biolaboratory.utils.collectors.DnaCollectors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.fail;

/**
 * Unit tests for {@link DnaListFastaLoader}.
 *
 * @author Attila Radi
 */
@Guice(modules = {GuiceResourceModule.class, GuiceResourceMockModule.class, GuiceCoreMockModule.class})
public class DnaListFastaLoaderTest {

    private static final String PATH = "sample.fas";

    @Inject
    private DnaListFastaLoader dnaListFastaLoader;

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
                { PATH, Stream.builder().add("").build() }
        };
    }

    private static final String VALID_LOAD_DATA_PROVIDER_NAME = "validLoadDataProvider";

    @DataProvider(name = VALID_LOAD_DATA_PROVIDER_NAME)
    private Object[][] validLoadDataProvider() {
        return new Object[][] {
                { PATH, Stream.builder().add("> comment").add("ACGT").build(), DnaCollectors.commentedStringToDnaList(new CommentedString("comment", "ACGT")) },
                { PATH, Stream.builder().add("> comment").add("ACGT").add("ACGT").build(), DnaCollectors.commentedStringToDnaList(new CommentedString("comment", "ACGTACGT")) },
                { PATH, Stream.builder().add("> comment1").add("ACGT").add("> comment2").add("TGCA").build(), DnaCollectors.commentedStringToDnaList(new CommentedString("comment1", "ACGT"), new CommentedString("comment2", "TGCA")) }
        };
    }

    @Test(dataProvider = INVALID_LOAD_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldLoadThrowException(String filePath, Stream<String> lines) {
        ((MockReaderWrapperFactory) readerWrapperFactory).setLines(lines);
        dnaListFastaLoader.load(filePath);
        fail();
    }

    @Test(dataProvider = VALID_LOAD_DATA_PROVIDER_NAME)
    public void shouldLoadReturn(String filePath, Stream<String> lines, List<Dna> controlDnaList) {
        ((MockReaderWrapperFactory) readerWrapperFactory).setLines(lines);
        List<Dna> loadedDnaList = dnaListFastaLoader.load(filePath);
        assertThat(loadedDnaList, is(equalTo(controlDnaList)));
    }
}
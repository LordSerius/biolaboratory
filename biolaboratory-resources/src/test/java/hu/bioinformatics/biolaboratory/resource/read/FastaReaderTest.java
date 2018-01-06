package hu.bioinformatics.biolaboratory.resource.read;

import com.google.common.collect.ImmutableList;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceMockModule;
import hu.bioinformatics.biolaboratory.guice.GuiceResourceModule;
import hu.bioinformatics.biolaboratory.resource.read.wrapper.ReaderWrapperFactory;
import hu.bioinformatics.biolaboratory.utils.datastructures.CommentedString;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.fail;

/**
 * Unit tests for {@link FastaReader}.
 *
 * @author Attila Radi
 */
@Guice(modules = {GuiceResourceModule.class, GuiceResourceMockModule.class})
public class FastaReaderTest {

    private static final String PATH = "path.fas";

    @Inject
    @Named(GuiceResourceModule.FASTA_READER_NAME)
    @Spy
    private ResourceReader fastaReader;

    @Inject
    private ReaderWrapperFactory readerWrapperFactory;

    @BeforeMethod
    public void setUp() throws IOException {
        initMocks(this);
    }

    private static final String INVALID_PROCESS_RESOURCE_DATA_PROVIDER_NAME = "invalidProcessResourceDataProvider";

    @DataProvider(name = INVALID_PROCESS_RESOURCE_DATA_PROVIDER_NAME)
    private Object[][] invalidProcessResourceDataProvider() {
        return new Object[][] {
                { Stream.empty() },
                { Stream.builder().add("> comment").build() },
                { Stream.builder().add("< comment").build() },
                { Stream.builder().add("> comment").add("").build() },
                { Stream.builder().add("> comment").add(" ").build() },
                { Stream.builder().add("> comment1").add("> comment2").add("line").build() }
        };
    }

    private static final String VALID_PROCESS_RESOURCE_DATA_PROVIDER_NAME = "validProcessResourceDataProvider";

    @DataProvider(name = VALID_PROCESS_RESOURCE_DATA_PROVIDER_NAME)
    private Object[][] validProcessResourceDataProvider() {
        return new Object[][] {
                { Stream.builder().add(">comment").add("line").build(), ImmutableList.of(new CommentedString("comment", "line")) },
                { Stream.builder().add("> comment ").add("line").build(), ImmutableList.of(new CommentedString("comment", "line")) },
                { Stream.builder().add(" >comment").add("line").build(), ImmutableList.of(new CommentedString("comment", "line")) },
                { Stream.builder().add(">").add("line").build(), ImmutableList.of(new CommentedString("", "line")) },
                { Stream.builder().add(">comment").add("").add("line").build(), ImmutableList.of(new CommentedString("comment", "line")) },
                { Stream.builder().add(">comment").add("line").add("").build(), ImmutableList.of(new CommentedString("comment", "line")) },
                { Stream.builder().add(">comment").add(" line ").build(), ImmutableList.of(new CommentedString("comment", "line")) },
                { Stream.builder().add(">comment").add("line1").add("line2").build(), ImmutableList.of(new CommentedString("comment", "line1line2")) },
                { Stream.builder().add("").add(">comment").add("").add("line1").add("").add("line2").add("").build(), ImmutableList.of(new CommentedString("comment", "line1line2")) },
                { Stream.builder().add(">comment1").add("line1").add(">comment2").add("line2").build(), ImmutableList.of(new CommentedString("comment1", "line1"), new CommentedString("comment2", "line2")) },
        };
    }

    @Test(dataProvider = INVALID_PROCESS_RESOURCE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldProcessResourcesThrowException(Stream<String> content) throws IOException {
        ((MockReaderWrapperFactory) readerWrapperFactory).setLines(content);
        fastaReader.read(PATH);
    }

    @Test(dataProvider = VALID_PROCESS_RESOURCE_DATA_PROVIDER_NAME)
    public void shouldProcessResourcesReturn(Stream<String> content, List<CommentedString> controlLineList) throws IOException {
        ((MockReaderWrapperFactory) readerWrapperFactory).setLines(content);
        List<CommentedString> commentedStrings = fastaReader.read(PATH);
        assertThat(commentedStrings, is(equalTo(controlLineList)));
    }
}
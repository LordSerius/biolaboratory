package hu.bioinformatics.biolaboratory.utils.resource.read.wrapper;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.Reader;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.fail;

/**
 * Unit tests for {@link ReaderWrapperFactory}.
 *
 * @author Attila Radi
 */
public class ReaderWrapperFactoryTest {

    @Mock
    private Reader mockReader;

    private ReaderWrapperFactory readerWrapperFactory = new ReaderWrapperFactory();

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldWrapThrowsException() {
        readerWrapperFactory.wrap(null);
        fail();
    }

    @Test
    public void shouldWrapReturns() {
        readerWrapperFactory.wrap(mockReader);
    }
}
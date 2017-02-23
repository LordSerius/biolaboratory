package hu.bioinformatics.biolaboratory.utils.resource.read;

import hu.bioinformatics.biolaboratory.utils.resource.read.wrapper.ReaderWrapperFactory;
import org.mockito.Mock;
import org.mockito.exceptions.misusing.MockitoConfigurationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.stream.Stream;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Provides mock {@link java.io.BufferedReader}s.
 *
 * @author Attila Radi
 */
public class MockReaderWrapperFactory extends ReaderWrapperFactory {

    boolean isInitialized = false;

    @Mock
    private BufferedReader mockBufferedReader;

    private void initialization() {
        initMocks(this);
        try {
            doNothing().when(mockBufferedReader).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLines(Stream<String> lines) {
        if (!isInitialized) {
            initialization();
            isInitialized = true;
        }
        when(mockBufferedReader.lines()).thenReturn(lines);
    }

    @Override
    public BufferedReader wrap(Reader reader) {
        if (!isInitialized) {
            throw new MockitoConfigurationException("Call setLines() first");
        }
        return mockBufferedReader;
    }
}

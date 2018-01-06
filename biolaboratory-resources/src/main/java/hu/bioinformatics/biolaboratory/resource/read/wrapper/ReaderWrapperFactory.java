package hu.bioinformatics.biolaboratory.resource.read.wrapper;

import java.io.BufferedReader;
import java.io.Reader;

import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotNullArgument;

/**
 * Wraps a {@link java.io.Reader} inside a {@link java.io.BufferedReader}.
 *
 * @author Attila Radi
 */
public class ReaderWrapperFactory {

    /**
     * Wraps the given {@link Reader} with a {@link BufferedReader}.
     *
     * @param reader The input {@link Reader}.
     * @return The wrapped {@link Reader} in a {@link BufferedReader}.
     */
    public BufferedReader wrap(final Reader reader) {
        checkNotNullArgument("Reader", reader);
        return new BufferedReader(reader);
    }
}

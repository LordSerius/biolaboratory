package hu.bioinformatics.biolaboratory.utils.resource.read.wrapper;

import com.google.common.base.Preconditions;

import java.io.BufferedReader;
import java.io.Reader;

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
        Preconditions.checkArgument(reader != null, "Reader should not be null");
        return new BufferedReader(reader);
    }
}

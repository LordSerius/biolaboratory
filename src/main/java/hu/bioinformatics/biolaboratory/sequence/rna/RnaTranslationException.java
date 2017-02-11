package hu.bioinformatics.biolaboratory.sequence.rna;

/**
 * This exception is thrown when RNA translation is not possible.
 *
 * @author Attila Radi
 */
public class RnaTranslationException extends Exception {

    public RnaTranslationException() {
        super();
    }

    public RnaTranslationException(String message) {
        super(message);
    }

    public RnaTranslationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RnaTranslationException(Throwable cause) {
        super(cause);
    }
}

package hu.bioinformatics.biolaboratory.utils.datahandlers;

import hu.bioinformatics.biolaboratory.guice.GuiceCoreModule;
import hu.bioinformatics.biolaboratory.sequence.dna.Dna;
import hu.bioinformatics.biolaboratory.utils.resource.extension.ResourceValidator;

import javax.inject.Named;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * TODO: test it.
 * 
 * Write the {@link Dna} to a file
 * 
 * @author Attila Radi
 *
 */
public class DnaFileWriter implements DnaDataWriter {

    private final ResourceValidator resourceValidator;

    public DnaFileWriter(@Named(GuiceCoreModule.DNA_VALIDATOR_NAME) final ResourceValidator resourceValidator) {
        this.resourceValidator = resourceValidator;
    }

    /**
     * Writes the given {@link Dna} to a file.
     * 
     * @param dna The persistable {@link Dna}.
     * @param resourcePath The target file path.
     * @throws UncheckedIOException If {@link IOException} occurs.
     */
    @Override
    public void save(Dna dna, String resourcePath) {
        resourceValidator.validate(resourcePath);
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(resourcePath);
            bw = new BufferedWriter(fw);
            bw.write(dna.getSequence());
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);        
        }
        finally {
            try {
                if (bw != null) bw.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }

}

package hu.bioinformatics.biolaboratory.utils.datahandlers;

import hu.bioinformatics.biolaboratory.sequence.dna.Dna;

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

    /**
     * Writes the given {@link Dna} to a file.
     * 
     * @param dna The persistable {@link Dna}.
     * @param resourcePath The target file path.
     * @throws UncheckedIOException If {@link IOException} occurs.
     */
    @Override
    public void save(Dna dna, String resourcePath) {
        DnaFileValidator.validateFilePath(resourcePath);
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

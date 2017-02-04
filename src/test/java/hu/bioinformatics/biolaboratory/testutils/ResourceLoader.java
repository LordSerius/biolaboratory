package hu.bioinformatics.biolaboratory.testutils;

/**
 * Loads test resources.
 *
 * @author Attila Radi
 */
public class ResourceLoader {

    private static ResourceLoader instance = null;

    /**
     * Loads a resource file from the \test\resources folder.
     *
     * @param resourceName The name of the resource file.
     * @return The file path of the resource file.
     */
    public static String loadResourceFile(String resourceName) {
        return getInstance().load(resourceName);
    }

    private static ResourceLoader getInstance() {
        if (instance == null) instance = new ResourceLoader();
        return instance;
    }

    private ResourceLoader() {}

    private String load(String resourcePath) {
        return this.getClass().getClassLoader().getResource(resourcePath).getFile();
    }
}

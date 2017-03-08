package hu.bioinformatics.biolaboratory.resource.extension.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.UncheckedIOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

/**
 * Unit tests for {@link FileResourceLocalizer}.
 *
 * @author Attila Radi
 */
public class FileResourceLocalizerTest {
    private static final String LOCALE_TEST_RESOURCE_PATH = "target" + File.separator + "test-classes" + File.separator;

    private FileResourceLocalizer fileResourceLocalizer;

    private static final String INVALID_LOCALIZE_RESOURCE_DATA_PROVIDER_NAME = "invalidLocalizeResourceDataProvider";

    @DataProvider(name = INVALID_LOCALIZE_RESOURCE_DATA_PROVIDER_NAME)
    private Object[][] invalidLocalizeResourceDataProvider() {
        return new Object[][] {
                { null },
                { "" }
        };
    }

    private static final String NOT_FOUND_LOCALIZE_RESOURCE_DATA_PROVIDER_NAME = "notFoundLocalizeResourceDataProvider";

    @DataProvider(name = NOT_FOUND_LOCALIZE_RESOURCE_DATA_PROVIDER_NAME)
    private Object[][] notFoundLocalizeResourceDataProvider() {
        return new Object[][] {
                { "xd.lol" }
        };
    }

    private static final String VALID_LOCALIZE_RESOURCE_DATA_PRIVIDER_NAME = "validLocalizeResourceDataProvider";

    @DataProvider(name = VALID_LOCALIZE_RESOURCE_DATA_PRIVIDER_NAME)
    private Object[][] validLocalizeResourceDataProvider() {
        return new Object[][] {
                { LOCALE_TEST_RESOURCE_PATH + "sample.dna", new File(getClass().getClassLoader().getResource("sample.dna").getFile()).getAbsolutePath() }
        };
    }

    @BeforeMethod
    public void setUp() {
        fileResourceLocalizer = new FileResourceLocalizer();
    }

    @Test(dataProvider = INVALID_LOCALIZE_RESOURCE_DATA_PROVIDER_NAME,
            expectedExceptions = IllegalArgumentException.class)
    public void shouldLocalizeResourceThrowException(String resourceName) {
        fileResourceLocalizer.localizeResource(resourceName);
        fail();
    }

    @Test(dataProvider = NOT_FOUND_LOCALIZE_RESOURCE_DATA_PROVIDER_NAME,
            expectedExceptions = UncheckedIOException.class)
    public void shouldLocalizeResourcesThrowFileNotFoundException(String resourceName) {
        fileResourceLocalizer.localizeResource(resourceName);
        fail();
    }

    @Test(dataProvider = VALID_LOCALIZE_RESOURCE_DATA_PRIVIDER_NAME)
    public void shouldLocalizeResourceReturn(String resourceName, String controlResourcePath) {
        String localizedResource = fileResourceLocalizer.localizeResource(resourceName);
        assertThat(localizedResource, is(equalTo(controlResourcePath)));
    }
}
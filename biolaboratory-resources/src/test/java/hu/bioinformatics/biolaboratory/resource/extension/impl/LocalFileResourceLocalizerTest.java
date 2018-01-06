package hu.bioinformatics.biolaboratory.resource.extension.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.fail;

/**
 * Unit tests for {@link LocalFileResourceLocalizer}.
 *
 * @author Attila Radi
 */
public class LocalFileResourceLocalizerTest {

    private LocalFileResourceLocalizer localFileResourceLocalizer;

    private static final String INVALID_LOCALIZE_RESOURCE_DATA_PROVIDER_NAME = "invalidLocalizeResourceDataProvider";

    @DataProvider(name = INVALID_LOCALIZE_RESOURCE_DATA_PROVIDER_NAME)
    private Object[][] invalidLocalizeResourceDataProvider() {
        return new Object[][] {
                { null },
                { "" },
                { "xd.lol" }
        };
    }

    private static final String VALID_LOCALIZE_RESOURCE_DATA_PRIVIDER_NAME = "validLocalizeResourceDataProvider";

    @DataProvider(name = VALID_LOCALIZE_RESOURCE_DATA_PRIVIDER_NAME)
    private Object[][] validLocalizeResourceDataProvider() {
        return new Object[][] {
                { "sample.dna", getClass().getClassLoader().getResource("sample.dna").getFile() }
        };
    }

    @BeforeMethod
    public void setUp() {
        localFileResourceLocalizer = new LocalFileResourceLocalizer();
    }

    @Test(dataProvider = INVALID_LOCALIZE_RESOURCE_DATA_PROVIDER_NAME,
        expectedExceptions = IllegalArgumentException.class)
    public void shouldLocalizeResourceThrowException(String resourceName) {
        localFileResourceLocalizer.localizeResource(resourceName);
    }

    @Test(dataProvider = VALID_LOCALIZE_RESOURCE_DATA_PRIVIDER_NAME)
    public void shouldLocalizeResourceReturn(String resourceName, String controlResourcePath) {
        String localizedResource = localFileResourceLocalizer.localizeResource(resourceName);
        assertThat(localizedResource, is(equalTo(controlResourcePath)));
    }
}
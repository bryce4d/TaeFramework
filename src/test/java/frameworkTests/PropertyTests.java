package frameworkTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TaeProperties;

public class PropertyTests {

    @Test
    public void getProperty() {
        String example = TaeProperties.getProperty("example");
        String system = TaeProperties.getProperty("PATH");
        String secondFile = TaeProperties.getProperty("propertyReaderTestKey");

        Assert.assertEquals(example,"sample test property", "did not load the right property");
        Assert.assertEquals(secondFile, "propertyReaderTestValue");
        Assert.assertTrue(system.length() > 0, "did not find the system variable 'PATH'");
    }

    @Test
    public void setProperty() {
        TaeProperties.setProperty("MyTest", "MyTestValue {{example2}}");

        Assert.assertTrue(TaeProperties.hasProperty("MyTest"));
        Assert.assertEquals(TaeProperties.getProperty("MyTest"), "MyTestValue test");
    }

}

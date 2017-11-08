package webDriverBuilder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class SeleniumBuilder {

    private static ArrayList<WebDriver> drivers = new ArrayList<WebDriver>();

    public static WebDriver getBrowser() {
        if (drivers.get(0) == null) {
            drivers.add(0, new ChromeDriver());
        }
        return drivers.get(0);
    }
}

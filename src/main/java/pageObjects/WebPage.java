package pageObjects;

import org.openqa.selenium.WebDriver;
import webDriverBuilder.SeleniumBuilder;

public class WebPage {

    WebDriver browser;

    private WebPage(WebDriver browser) {
        this.browser = browser;
    }

    public static WebPage create() {
        return new WebPage(SeleniumBuilder.getBrowser());
    }
}

package heidl.field;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class NavigationField extends EntityField<List<WebElement>> {

    public NavigationField(String xpath, String fieldName) {
        super(xpath, fieldName);
    }

    @Override
    public List<WebElement> parse(ChromeDriver driver) {
        return driver.findElementsByXPath(getXpath());
    }
}
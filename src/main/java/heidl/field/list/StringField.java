package heidl.field.list;

import heidl.field.EntityField;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringField extends EntityField<List<String>> {

    public StringField(String xpath, String fieldName) {
        super(xpath, fieldName);
    }

    @Override
    public List<String> parse(ChromeDriver driver) {
        List<WebElement> elements = driver.findElementsByXPath(getXpath());
        return elements
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());
    }
}

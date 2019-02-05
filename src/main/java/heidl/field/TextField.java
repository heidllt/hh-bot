package heidl.field;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.Collectors;

public class TextField extends EntityField<String> {
    public TextField(String xpath, String fieldName) {
        super(xpath, fieldName);
    }

    @Override
    public String parse(ChromeDriver driver) {
        return driver.findElementsByXPath(getXpath()).stream()
                .map(e->e.getText())
                .collect(Collectors.joining());
    }
}

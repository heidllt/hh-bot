package heidl.field;

import org.openqa.selenium.chrome.ChromeDriver;

public class CurrentUrlField extends EntityField<String> {
    public CurrentUrlField(String xpath, String fieldName) {
        super(xpath, fieldName);
    }

    @Override
    public String parse(ChromeDriver driver) {
        return driver.getCurrentUrl();
    }
}

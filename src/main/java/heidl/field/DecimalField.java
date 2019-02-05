package heidl.field;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DecimalField extends EntityField<Integer> {

    public DecimalField(String xpath, String fieldName) {
        super(xpath, fieldName);
    }

    @Override
    public Integer parse(ChromeDriver driver) {
        WebElement element = driver.findElementByXPath(getXpath());
        return extractNumber(element.getText());
    }
}
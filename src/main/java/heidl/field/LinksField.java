package heidl.field;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.stream.Collectors;


public class LinksField extends EntityField<List<String>> {

    public LinksField(String xpath, String fieldName) {
        super(xpath, fieldName);
    }

    @Override
    public List<String> parse(ChromeDriver driver) {
        List<WebElement> elements = driver.findElementsByXPath(getXpath());
        /* TODO: stream refactoring */
        return elements.stream()
                .map(e -> e.getAttribute("href"))
                .collect(Collectors.toList());
    }
}

package heidl.field;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class EntityField<E>
{
    String xpath;
    String fieldName;


    public static int extractNumber(String src)
    {
        String regexp = "([0-9 ]+)";
        Matcher m = Pattern.compile(regexp).matcher(src);
        m.find();
        int result = Integer.valueOf(m.group(1).replace(" ", ""));
        return result;
    }

    public EntityField(String xpath, String fieldName){
        this.xpath = xpath;
        this.fieldName = fieldName;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public abstract E parse(ChromeDriver driver);

    public String getXpath() {
        return xpath;
    }

    @Override
    public String toString() {
        return fieldName;
    }
}

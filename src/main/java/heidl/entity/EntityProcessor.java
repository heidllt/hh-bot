package heidl.entity;

import heidl.field.EntityField;
import org.openqa.selenium.chrome.ChromeDriver;
import java.lang.reflect.Field;

import java.util.*;


public class EntityProcessor
{

    List<Field> fields;
    ChromeDriver driver;

    public EntityProcessor(ChromeDriver driver) {
        this.driver = driver;
        this.fields = new ArrayList<>();
    }

    public void resolve()
    {
        for(Field rawField : getFields()) {
            System.out.println(rawField.getName());
            try {
                heidl.annotations.Field field =
                        rawField.getAnnotation(heidl.annotations.Field.class);
                EntityField parser = field.field().getDeclaredConstructor(
                        String.class,
                        String.class)
                        .newInstance(
                            field.xpath(),
                            rawField.getName());
                Object result = parser.parse(getDriver());
                rawField.set(this, result);
            } catch (Exception e) {
                try {
                    if (rawField.getType().isAssignableFrom(Collection.class)) {
                        rawField.set(this, Collections.emptyList());
                    } else if (rawField.getType().isAssignableFrom(String.class)) {
                        rawField.set(this, "");
                    } else if (rawField.getType().isAssignableFrom(Integer.class)) {
                        rawField.set(this, 0);
                    }
                } catch (Exception e1) {}
            }
        }
    }

    public List<Field> getFields() {
        if (fields.isEmpty()) {
            fields = Arrays.asList(this.getClass().getDeclaredFields());
        }
        return fields;
    }

    public ChromeDriver getDriver() {
        return driver;
    }
}
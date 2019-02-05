package heidl.entity;

import heidl.annotations.Field;
import heidl.field.CurrentUrlField;
import heidl.field.DecimalField;
import heidl.field.EntityField;
import heidl.field.TextField;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class VacancyEntity extends EntityProcessor{

    @Field(
            xpath = "//h1[@data-qa='vacancy-title']",
            field = TextField.class)
    String header;

    @Field(
            xpath = "//span[@itemprop='name']",
            field = TextField.class)
    String title;

    @Field(
            xpath = "//div[@class='vacancy-section']",
            field = TextField.class)
    String text;


    @Field(
            xpath = "//p[@class='vacancy-salary']",
            field = DecimalField.class)
    int salary;

    @Field(
            xpath = "//span[@data-qa='vacancy-view-raw-address']",
            field = TextField.class)
    String metro;

    @Field(
            xpath = "//span[@data-qa='vacancy-experience']",
            field = DecimalField.class)
    int years;

    @Field(field = CustomDateField.class)
    Date publish;

    @Field(field = CurrentUrlField.class)
    String link;


    static class CustomDateField extends EntityField<Date> {
        public CustomDateField(String xpath, String fieldName) {
            super(xpath, fieldName);
        }
        @Override
        public Date parse(ChromeDriver driver) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String found = driver.findElementByXPath("//meta[@itemprop='datePosted']")
                        .getAttribute("content");
                return sdf.parse(found);
            } catch (Exception e) {
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(0l);
                return c.getTime();
            }
        }
    }


    public VacancyEntity(ChromeDriver driver) {
        super(driver); resolve();
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Date getPublish() {
        return publish;
    }

    public int getSalary() {
        return salary;
    }

    public String getMetro() {
        return metro;
    }

    public int getYears() {
        return years;
    }

    public String getLink() {
        return link;
    }

    public String getHeader() { return header; }
}

package heidl.entity;

import heidl.annotations.Field;
import heidl.field.DecimalField;
import org.openqa.selenium.chrome.ChromeDriver;

public class BeginEntity extends EntityProcessor {
    static final int VACANCY_PER_PAGE = 20;

    @Field(
            xpath = "//h1[@data-qa='page-title']",
            field = DecimalField.class)
    int vacancyCount;

    public BeginEntity(ChromeDriver driver) {
        super(driver); resolve();
    }

    public int getLastPage() {
        int lastPage = (int) Math.floor(vacancyCount / VACANCY_PER_PAGE);
        return lastPage > 100 ? 100 : lastPage;
    }
}

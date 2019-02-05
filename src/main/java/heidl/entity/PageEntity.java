package heidl.entity;

import heidl.annotations.Field;
import heidl.field.EntityField;
import heidl.field.LinksField;
import heidl.field.NavigationField;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class PageEntity extends EntityProcessor
{

    @Field(
            xpath = "//*[@data-qa='pager-page']",
            field = NavigationField.class)
    List<WebElement> pages;

    @Field(
            xpath = "//a[@data-qa='vacancy-serp__vacancy-title']",
            field = LinksField.class)
    List<String> vacancyLinks;

    public PageEntity(ChromeDriver d){
        super(d); resolve();
    }

    public List<WebElement> getPages() {
        return pages;
    }

    public WebElement getPage(int idx) {
        return getPages().stream()
                .filter(e->e.getText().equals(String.valueOf(idx)))
                .findFirst().get();
    }

    public int getPageNum() {
        WebElement curPage = getPages().stream()
                .filter(e->e.getTagName().equals("span"))
                .findFirst().get();
        return EntityField.extractNumber(curPage.getText());
    }

    public int getMaxPage(int lastPage) {
        return getPages().stream()
                .filter(e->e.getTagName().equals("a"))
                .map(e->EntityField.extractNumber(e.getText()))
                .filter(e->e != lastPage)
                .max(Integer::compareTo).get();
    }

    public List<String> getVacancyLinks() {
        return vacancyLinks;
    }
}
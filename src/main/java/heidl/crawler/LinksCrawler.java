package heidl.crawler;

import heidl.entity.BeginEntity;
import heidl.entity.PageEntity;
import heidl.export.PageXMLExport;
import heidl.pagination.SimplePaginator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LinksCrawler extends BaseCrawler{
    //final static String basicUrl = "https://hh.ru/search/vacancy?text=spring&only_with_salary=false&clusters=true&area=1&enable_snippets=true&salary=";
    final static String basicUrl = "https://hh.ru/search/vacancy?text=flask&only_with_salary=false&clusters=true&area=1&enable_snippets=true&salary=";
    static int lastPage = 0;


    public static void crawl() throws Exception
    {
        //delegates
        SimplePaginator paginator = SimplePaginator.getInstance();
        PageXMLExport xmlExport = new PageXMLExport();

        // init
        ChromeDriver driver = createDriver();

        // crawler logic
        driver.get(basicUrl);
        BeginEntity init = new BeginEntity(driver);
        lastPage = init.getLastPage();
        PageEntity page = new PageEntity(driver);

        paginator.setMaxPage(page.getMaxPage(lastPage));
        paginator.setCurPage(page.getPageNum());
        page = null;

        while(paginator.hasNext()) {
            PageEntity current = new PageEntity(driver);
            paginator.setMaxPage(current.getMaxPage(lastPage));
            paginator.setCurPage(current.getPageNum());
            xmlExport.add(current);
            if (current.getPageNum() > lastPage) {
                break;
            }
            current.getPage(paginator.next()).click();
            Thread.sleep(5);
        }
        xmlExport.export();
        driver.close();
    }
}

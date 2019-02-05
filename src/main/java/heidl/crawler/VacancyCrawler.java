package heidl.crawler;

import heidl.entity.VacancyEntity;
import heidl.export.VacancyXMLExport;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;

public class VacancyCrawler extends BaseCrawler {

    static final String FEED_PATH = "/home/heid/javajob/projects/piggrabber/archive/links_2019-2-2.xml";

    public static void crawl() throws Exception
    {
        VacancyXMLExport exporter = new VacancyXMLExport();
        DocumentBuilder builder = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder();
        Document doc = builder.parse(new File(FEED_PATH));
        ChromeDriver driver = createDriver();
        NodeList urls = doc.getElementsByTagName("vacancy");
        int chunkSize = 5;
        for(int i = 0; i < urls.getLength(); i++) {
            String url = urls.item(i).getTextContent();
            driver.get(url);
            Thread.sleep(3000);
            VacancyEntity entity = new VacancyEntity(driver);
            exporter.add(entity);
            if ( (i + 1) % chunkSize == 0) {
                exporter.export(String.valueOf(i+1));
                exporter.reset();
            }
        }
        driver.close();
    }
}

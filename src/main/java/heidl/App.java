package heidl;


import heidl.crawler.LinksCrawler;
import heidl.crawler.VacancyCrawler;

public class App
{
    public static void main( String[] args )
            throws Exception {
        //VacancyCrawler.crawl();
        LinksCrawler.crawl();
    }
}

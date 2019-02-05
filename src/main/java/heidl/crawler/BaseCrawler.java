package heidl.crawler;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseCrawler {
    protected static ChromeDriver createDriver() {
        // driver settings
        String driverPath = "/home/heid/drivers/chromedriver";
        String binPath = "/snap/bin/chromium";
        String profilePath = "/home/heid/snap/chromium/566/.config/chromium/Default";

        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--user-data-dir="+profilePath);
        opts.setBinary(binPath);
        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeDriver driver = null;
        driver = new ChromeDriver(opts);
        return driver;
    }
}

package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver {
    private static WebDriver driver;
    private static final int TIMEOUT = 10;

    private Driver() {
        // Singleton pattern
    }

    public static WebDriver getDriver() {


        String browser = GeneralReader.getProperty(GeneralReader.CONFIG_FILE, "browser");
        boolean headless = Boolean.parseBoolean(GeneralReader.getProperty(GeneralReader.CONFIG_FILE, "headless"));

        // Download setting
        String downloadPath = System.getProperty("user.dir") + File.separator + "downloads";
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("plugins.always_open_pdf_externally", true);
        prefs.put("pdfjs.disabled", true);

        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = setupChromeDriver(headless, prefs);
                    break;
                case "firefox":
                    driver = setupFirefoxDriver(headless, downloadPath);
                    break;
                case "edge":
                    driver = setupEdgeDriver(headless, prefs);
                    break;
                case "safari":
                    driver = setupSafariDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Geçersiz tarayıcı: " + browser);
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
            driver.manage().window().maximize();
        }
        return driver;
    }

    private static WebDriver setupChromeDriver(boolean headless, Map<String, Object> prefs) {

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
        } else {
            options.addArguments("--start-maximized");
        }

        return new ChromeDriver(options);
    }

    private static WebDriver setupFirefoxDriver(boolean headless, String downloadPath) {

        FirefoxOptions options = new FirefoxOptions();

        options.addPreference("browser.download.dir", downloadPath);
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/pdf, application/octet-stream, application/x-pdf");

        if (headless) {
            options.addArguments("--headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        }

        return new FirefoxDriver(options);
    }

    private static WebDriver setupEdgeDriver(boolean headless, Map<String, Object> prefs) {
        EdgeOptions options = new EdgeOptions();
        options.setExperimentalOption("prefs", prefs);

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }

        return new EdgeDriver(options);
    }

    private static WebDriver setupSafariDriver() {

        SafariOptions options = new SafariOptions();
        options.setAutomaticInspection(false);
        return new SafariDriver(options);
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class Commands {

    public static double speed = 1;

    public static String takeScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String targetPath = System.getProperty("user.dir") + "/target/test-output/Screenshots/"
                + testName + "_" + System.currentTimeMillis() + ".png";
        try {
            FileUtils.copyFile(source, new File(targetPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return targetPath;
    }

    public static void dropDownClick(List<WebElement> elements, String titleText) {

        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().contains(titleText)) {
                elements.get(i).click();
            }
        }


    }

    public static void selectAnItemFromDropdown(WebElement item, String selectableItem) {
        System.out.println("selectableItem = " + selectableItem);
        Wait.waitfor(1);
        Select select = new Select(item);
        for (int i = 0; i < select.getOptions().size(); i++) {
            System.out.println("select.getOptions().get(i).getText() = " + select.getOptions().get(i).getText());
            if (select.getOptions().get(i).getText().equals(selectableItem)) {
                select.getOptions().get(i).click();
                break;
            }
        }
    }

    public static void waitForClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void moveToElement(WebElement webElement) {
        Actions actions = new Actions(Driver.getDriver());
        while (true) {
            try {
                if (webElement.isDisplayed()) {
                    actions.moveToElement(webElement).perform();
                    Wait.waitfor(2);
                }
                break;
            } catch (Exception e) {
                Wait.waitfor(2);
                break;
            }
        }
    }

    public static void doubleClick(WebElement webElement, int duration, int interval) {
        waitForClickable(webElement, 10);
        int timer = 0;
        do {
            try {
                Wait.waitfor(speed);
                if (webElement.isDisplayed()) {
                    Commands.moveToElement(webElement);
                    new Actions(Driver.getDriver()).doubleClick(webElement).perform();
                    break;
                } else
                    throw new Exception();
            } catch (Exception e) {
                Wait.waitfor(1);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println(webElement + " is not clickable");
            throw new NoSuchElementException();
        }
    }

    public static void clickWithJS(WebElement webElement) {


        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
        waitForClickable(webElement, 30);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", webElement);
    }

    public static void waitForVisibility(WebElement locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(locator));
    }

}

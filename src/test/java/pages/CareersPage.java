package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class CareersPage {

    public CareersPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//div[@class='elementor-widget-container']/section")
    public List<WebElement> blocks;

    @FindBy(xpath = "//section[@id='career-find-our-calling']")
    public WebElement teamBlock;

    @FindBy(xpath = "//section[@id='career-our-location']")
    public WebElement locationBlock;

    @FindBy(xpath = "//div[@class='elementor-widget-wrap elementor-element-populated e-swiper-container']")
    public WebElement lifeBlock;

    @FindBy(xpath = "//a[text()='See all QA jobs']")
    public WebElement seeAllQAJobsButton;
}

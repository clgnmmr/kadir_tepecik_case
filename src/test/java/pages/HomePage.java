package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class HomePage {

    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[@id='wt-cli-accept-all-btn']")
    public WebElement acceptButton;

    @FindBy(xpath = "//a[@id='navbarDropdownMenuLink']")
    public List<WebElement> dropDown;

    @FindBy(xpath = "//a[text()='Careers']")
    public WebElement careersLink;
}

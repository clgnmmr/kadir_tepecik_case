package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class QAJobsPage {

    public QAJobsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[@class='selection']")
    public List<WebElement> locationFilter;

    @FindBy(xpath = "//select[@id='filter-by-location']")
    public WebElement locationSelect;


    @FindBy(css = ".position-title")
    public List<WebElement> jobPositions;

    @FindBy(css = ".position-department")
    public List<WebElement> jobDepartments;

    @FindBy(css = ".position-location")
    public List<WebElement> jobLocations;

    @FindBy(xpath = "//a[text()='View Role']")
    public WebElement viewRoleButton;
}

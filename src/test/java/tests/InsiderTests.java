package tests;

import hooks.Hook;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.HomePage;
import pages.QAJobsPage;
import utilities.Asserts;
import utilities.Commands;
import utilities.Driver;
import utilities.Wait;

import java.util.Set;

import static utilities.Commands.dropDownClick;
import static utilities.Commands.selectAnItemFromDropdown;
import static utilities.GeneralReader.CONFIG_FILE;
import static utilities.GeneralReader.getProperty;

@Listeners(Hook.class)
public class InsiderTests {

    HomePage homePage = new HomePage();
    CareersPage careersPage = new CareersPage();
    QAJobsPage qaJobsPage = new QAJobsPage();

    @BeforeClass
    public void setup() {
        Driver.getDriver().get(getProperty(CONFIG_FILE, "base_url"));
        homePage.acceptButton.click();
    }

    @Test(priority = 1)
    @Epic("Insider Website")
    @Feature("Home Page")
    @Story("Open Home Page")
    @Description("Verify that Insider home page is opened successfully")
    public void testHomePageOpened() {
        Asserts.assertTrue(Driver.getDriver().getCurrentUrl().contains("useinsider"), 2, 1);
    }

    @Test(priority = 2)
    public void testCareersPageBlocks() {
        dropDownClick(homePage.dropDown, "Company");
        homePage.careersLink.click();
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("/careers"));

        Asserts.assertTrue(careersPage.teamBlock.isDisplayed(), 2, 1);
        Asserts.assertTrue(careersPage.locationBlock.isDisplayed(), 2, 1);
        Asserts.assertTrue(careersPage.lifeBlock.isDisplayed(), 2, 1);

    }

    @Test(priority = 3)
    public void testQAJobsFilter() {
        Driver.getDriver().get(getProperty(CONFIG_FILE, "base_url") + getProperty(CONFIG_FILE, "qa_page"));
        careersPage.seeAllQAJobsButton.click();

        Wait.waitfor(15);
        Commands.clickWithJS(qaJobsPage.locationFilter.getFirst());

        selectAnItemFromDropdown(qaJobsPage.locationSelect, getProperty(CONFIG_FILE, "location"));
        Wait.waitfor(5);
        Asserts.assertTrue(!qaJobsPage.jobPositions.isEmpty(), "Job list is empty!", 2, 1);
    }

    @Test(priority = 4, dependsOnMethods = {"testQAJobsFilter"})
    public void testJobDetails() {
        for (int i = 0; i < qaJobsPage.jobPositions.size(); i++) {
            Asserts.assertTrue(qaJobsPage.jobPositions.get(i).getText().contains(getProperty(CONFIG_FILE, "department")), 2, 1);
            Asserts.assertEquals(qaJobsPage.jobDepartments.get(i).getText(), getProperty(CONFIG_FILE, "department"), 2, 1);
            Asserts.assertEquals(qaJobsPage.jobLocations.get(i).getText(), getProperty(CONFIG_FILE, "location"), 2, 1);
        }
    }

    @Test(priority = 5, dependsOnMethods = {"testQAJobsFilter"})
    public void testViewRoleRedirect() {
        String window = Driver.getDriver().getWindowHandle();
        qaJobsPage.viewRoleButton.click();
        Set<String> windows = Driver.getDriver().getWindowHandles();
        for (String each : windows) {
            if (!each.equals(window)) {
                Driver.getDriver().switchTo().window(each);
            }
        }
        Asserts.assertTrue(Driver.getDriver().getCurrentUrl().contains("lever.co"), "Not redirected to Lever!", 2, 1);
    }


    @AfterClass
    public void tearDown() {
        Driver.closeDriver();
    }
}

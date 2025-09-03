package utilities;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Asserts {
    public static void assertEquals(int expected, int actual, int duration, int interval) {
        int timer = 0;
        do {
            try {
                Assert.assertEquals(actual, expected);
                System.out.println("ASSERTION PASSED : " + "Expected: " + expected + ", Actual: " + actual);
                break;
            } catch (AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : " + "Expected: " + expected + ", Actual: " + actual);
            Assert.fail();
        }
    }

    public static void assertEquals(String expected, WebElement webElement, int duration, int interval) {
        int timer = 0;
        String actual = null;
        do {
            try {
                actual = webElement.getText();
                Assert.assertEquals(actual, expected);
                System.out.println("ASSERTION PASSED : " + "Expected: " + expected + ", WebElement.getText(): " + actual);
                break;
            } catch (AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : " + "Expected: " + expected + ", Actual: " + actual);
            Assert.fail();
        }
    }

    public static void assertEquals(String expected, String actual, int duration, int interval) {
        int timer = 0;
        do {
            try {
                Assert.assertEquals(actual, expected);
                System.out.println("ASSERTION PASSED : " + "Expected: " + expected + ", Actual: " + actual);
                break;
            } catch (AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : " + "Expected: " + expected + ", Actual: " + actual);
            Assert.fail();
        }
    }

    public static void assertEquals(List<String> expectedList, List<String> actualList, int duration, int interval) {
        int timer = 0;
        Collections.sort(expectedList);
        Collections.sort(actualList);
        do {
            try {
                Assert.assertEquals(expectedList, actualList);
                System.out.println("ASSERTION PASSED : " + "Expected: " + expectedList + ", Actual: " + actualList);
                break;
            } catch (AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : " + "Expected: " + expectedList + ", Actual: " + actualList);
            Assert.fail();
        }
    }

    public static void assertEquals(Map<String, String> expectedMap, Map<String, String> actualMap, int duration, int interval) {
        int timer = 0;
        do {
            try {
                Assert.assertEquals(expectedMap, actualMap);
                System.out.println("ASSERTION PASSED : " + "Expected: " + expectedMap + ", Actual: " + actualMap);
                break;
            } catch (AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : " + "Expected: " + expectedMap + ", Actual: " + actualMap);
            Assert.fail();
        }
    }

    public static void assertTrue(boolean condition, int duration, int interval) {
        int timer = 0;
        do {
            try {
                Assert.assertTrue(condition);
                System.out.println("ASSERTION PASSED : CONDITION : " + condition);
                break;
            } catch (AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : CONDITION : " + condition);
            Assert.fail();
        }
    }


    public static void assertTrue(boolean condition, String message, int duration, int interval) {
        int timer = 0;
        do {
            try {
                Assert.assertTrue(condition, message);
                System.out.println("ASSERTION PASSED : CONDITION : " + condition);
                break;
            } catch (AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : CONDITION : " + condition);
            Assert.fail();
        }
    }

    public static void assertIsDownloaded(String filePath, int duration, int interval) {
        int timer = 0;
        boolean isDownloaded = false;
        do {
            try {
                isDownloaded = Files.exists(Paths.get(filePath));
                Assert.assertTrue(isDownloaded);
                System.out.println("ASSERTION PASSED : CONDITION : " + isDownloaded);
                break;
            } catch (AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : CONDITION : " + isDownloaded);
            Assert.fail();
        }
    }

    public static void assertEqualsListIntegers(List<Integer> expectedList, List<Integer> actualList, int duration, int interval) {
        int timer = 0;
        Collections.sort(expectedList);
        Collections.sort(actualList);
        do {
            try {
                Assert.assertEquals(expectedList, actualList);
                System.out.println("ASSERTION PASSED : " + "Expected: " + expectedList + ", Actual: " + actualList);
                break;
            } catch (AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : " + "Expected: " + expectedList + ", Actual: " + actualList);
            Assert.fail();
        }
    }

    public static void assertIsEnabled(WebElement webElement, int duration, int interval) {
        int timer = 0;
        do {
            try {
                Assert.assertTrue(webElement.isEnabled());
                System.out.println("ASSERTION ERROR : " + webElement + " is not displayed");
                break;
            } catch (AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : " + webElement + " is not displayed");
            Assert.fail();
        }
    }

    public static void assertFalse(Boolean condition, int duration, int interval) {
        int timer = 0;
        do {
            try {
                Assert.assertFalse(condition);
                System.out.println("ASSERTION PASSED : CONDITION : " + condition);
                break;
            } catch (AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : CONDITION : " + condition);
            Assert.fail();
        }
    }

    public static void assertEquals(Double expected, Double actual, int duration, int interval) {
        int timer = 0;
        do {
            try {
                Assert.assertEquals(actual, expected);
                System.out.println("ASSERTION PASSED : " + "Expected: " + expected + ", Actual: " + actual);
                break;
            } catch (AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : " + "Expected: " + expected + ", Actual: " + actual);
            Assert.fail();
        }
    }

    public static void fail(String message) {

        Assert.fail(message);
    }

}

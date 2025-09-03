package hooks;


import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.Commands;
import utilities.Driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Hook implements ITestListener {
    private static volatile Hook instance;


    // Varsayılan değerlerle başlatılan değiştirilebilir alanlar
    private String languageType = "TR";

    // Sabit (değişmeyecek) değerler
    private static final Random random = new Random();
    private static final Faker faker = new Faker();

    // Private constructor (Dışarıdan nesne oluşturmayı engeller)
    public Hook() {
    }


    // Thread-safe Singleton
    public static Hook getInstance() {
        if (instance == null) {
            synchronized (Hook.class) {
                if (instance == null) {
                    instance = new Hook();
                }
            }
        }
        return instance;
    }

    // Getter & Setter metodları

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String languageType) {
        if (languageType != null || !languageType.isEmpty()) {
            this.languageType = languageType;
        }
    }


    // Final değişkenler için getter metodları
    public static Random getRandom() {
        return random;
    }

    public static Faker getFaker() {

        return faker;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();

        String screenshotPath = Commands.takeScreenshot(testName);
        System.out.println("Test failed! Screenshot saved at: " + screenshotPath);
        try {
            Allure.addAttachment("Screenshot", new FileInputStream(screenshotPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Driver.closeDriver();
    }


}

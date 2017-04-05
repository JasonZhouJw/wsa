package com.cucumber.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

/**
 * Created by jzhou237 on 2017-04-05.
 */
public class DriverFactory {

    public static WebDriver getDriver(Browser browser) {
        return fireFox(browser);
    }

    private static WebDriver fireFox(Browser browser) {
        File pathToBinary = new File(browser.getFilePath());
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        return new FirefoxDriver(ffBinary, firefoxProfile);
    }

    public enum Browser {
        FIRE_FOX("fireFox", "C:\\Users\\jzhou237\\AppData\\Local\\Mozilla Firefox\\Firefox.exe");

        private String name;
        private String filePath;

        Browser(String name, String filePath) {
            this.name = name;
            this.filePath = filePath;
        }

        public String getName() {
            return name;
        }

        public String getFilePath() {
            return filePath;
        }
    }
}

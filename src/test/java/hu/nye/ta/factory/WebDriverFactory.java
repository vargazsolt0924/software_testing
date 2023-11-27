package hu.nye.ta.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Objects;

public class WebDriverFactory {

    private WebDriver driver;

    private static final Duration IMPLICIT_WAIT_DURATION = Duration.ofSeconds(10);

    public WebDriver getDriver(){
        if(Objects.isNull((driver))){
            driver = setUpWebDriver();
        }
        return driver;
    }

    private WebDriver setUpWebDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(
                new ChromeOptions().addArguments("--remote-allow-origins=*")
        );
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_DURATION);
        driver.manage().window().maximize();
        return driver;
    }

    public void tearDown(){
        if(Objects.nonNull(driver)){
            driver.quit();
            driver = null;
        }
    }

}
